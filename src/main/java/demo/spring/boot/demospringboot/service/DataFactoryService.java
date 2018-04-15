package demo.spring.boot.demospringboot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import demo.spring.boot.demospringboot.data.jpa.service.CinemasDealJpa;
import demo.spring.boot.demospringboot.data.jpa.service.CinemasDetailJpa;
import demo.spring.boot.demospringboot.data.jpa.service.CinemasJpa;
import demo.spring.boot.demospringboot.data.jpa.service.CinemasMoviePlistJpa;
import demo.spring.boot.demospringboot.data.jpa.service.CinemasMoviesJpa;
import demo.spring.boot.demospringboot.data.jpa.service.CinemasMoviesShowsJpa;
import demo.spring.boot.demospringboot.data.jpa.service.CinemasVipInfoJpa;
import demo.spring.boot.demospringboot.data.jpa.service.CityJpa;
import demo.spring.boot.demospringboot.data.jpa.service.HotMovieDetailCommentJpa;
import demo.spring.boot.demospringboot.data.jpa.service.HotMoviesJpa;
import demo.spring.boot.demospringboot.data.jpa.service.HotMovieDetailJpa;
import demo.spring.boot.demospringboot.data.jpa.vo.CinemasMoviesShowsVo;
import demo.spring.boot.demospringboot.data.jpa.vo.HotMovieDetailCommentVo;
import demo.spring.boot.demospringboot.data.jpa.vo.HotMovieVo;
import demo.spring.boot.demospringboot.data.jpa.vo.HotMovieDetailVo;
import demo.spring.boot.demospringboot.data.jpa.vo.other.CinemasWithMovie;
import demo.spring.boot.demospringboot.data.jpa.vo.other.City;
import demo.spring.boot.demospringboot.data.jpa.vo.CityVo;
import demo.spring.boot.demospringboot.thrid.party.api.maoyan.MaoyanCinemasFactory;
import demo.spring.boot.demospringboot.util.IP;

@Service
public class DataFactoryService {
    private static Logger LOGGER =
            LoggerFactory.getLogger(DataFactoryService.class);
    @Autowired
    private HotMoviesJpa hotMoviesJpa;
    @Autowired
    private MaoyanCinemasFactory maoyanCinemasFactory;
    @Autowired
    private HotMovieDetailJpa hotMovieDetailJpa;
    @Autowired
    private HotMovieDetailCommentJpa hotMovieDetailCommentJpa;
    @Autowired
    private CinemasJpa cinemasJpa;
    @Autowired
    private CityJpa cityJpa;

    @Autowired
    private CinemasDealJpa cinemasDealJpa;

    @Autowired
    private CinemasMoviesJpa cinemasMoviesJpa;

    @Autowired
    private CinemasMoviePlistJpa cinemasMoviePlistJpa;

    @Autowired
    private CinemasVipInfoJpa cinemasVipInfoJpa;

    @Autowired
    private CinemasMoviesShowsJpa cinemasMoviesShowsJpa;

    @Autowired
    private CinemasDetailJpa cinemasDetailJpa;

    public void loadInHotMovie() {
        hotMoviesJpa.deleteAll();
        maoyanCinemasFactory.loadInMovies(IP.getNextRandow())
                .stream()
                .forEach(vo -> {
                    LOGGER.info("保存{}", vo);
                    vo.setCreateTime(this.getTimestamp());
                    vo.setUpdateTime(this.getTimestamp());
                    hotMoviesJpa.save(vo);
                });
    }

    public void makeUpHotMovie() {
        if (hotMoviesJpa.findAll().size() == 0) {
            LOGGER.info("检查hotMovie数据异常 - 补充数据start");
            maoyanCinemasFactory.loadInMovies(IP.getNextRandow())
                    .stream()
                    .forEach(vo -> {
                        LOGGER.info("保存{}", vo);
                        vo.setCreateTime(this.getTimestamp());
                        vo.setUpdateTime(this.getTimestamp());
                        hotMoviesJpa.save(vo);
                    });
            LOGGER.info("检查hotMovie数据异常 - 补充数据end");
        } else {
            LOGGER.info("检查hotMovie数据正常");
        }
    }

    public void loadInHotMoviesDetail() {
        List<Integer> ids = new ArrayList<>();
        hotMovieDetailJpa.findAll().stream().forEach(vo -> {
            ids.add(vo.getId());
        });
        ids.add(1);
        List<HotMovieVo> byIdNotIn =
                hotMoviesJpa.findByIdNotIn(ids);
        if (byIdNotIn.size() == 0) {
            LOGGER.info("检查hotMovieDetail数据正常");
        } else {
            LOGGER.info("检查hotMovieDetail数据异常 - 抓取数据start");
            byIdNotIn.forEach(vo -> {
                HotMovieDetailVo hotMovieDetailVo =
                        maoyanCinemasFactory.loadInMoviesDetail(IP.getNextRandow(),
                                String.valueOf(vo.getId()));
                hotMovieDetailVo.setCreateTime(this.getTimestamp());
                hotMovieDetailVo.setUpdateTime(this.getTimestamp());
                LOGGER.info("保存{}", hotMovieDetailVo);
                hotMovieDetailJpa.save(hotMovieDetailVo);
            });
            LOGGER.info("检查hotMovieDetail数据异常 - 抓取数据end");
        }
    }

    public void loadInHotMoviesDetailComment() throws InterruptedException {
        List<Integer> ids = new ArrayList<>();
        hotMovieDetailCommentJpa.findAll().stream().forEach(vo -> {
            ids.add(vo.getMovieId());
        });
        List<HotMovieVo> byIdNotIn =
                hotMoviesJpa.findByIdNotIn(ids);
        if (byIdNotIn.size() == 0) {
            LOGGER.info("检查loadInHotMoviesDetailComment数据正常");
        } else {
            LOGGER.info("检查loadInHotMoviesDetailComment数据异常 - 补充数据 start");
            byIdNotIn.stream().forEach(vo -> {
                Integer movieId = vo.getId();
                Integer limit = 14;
                Integer offset = 0;
                for (int i = 0; i < 20; i++) {
                    offset = i * limit;
                    try {
                        Thread.sleep(1000 * 2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        List<HotMovieDetailCommentVo> HotMovieDetailCommentVos
                                = maoyanCinemasFactory.loadInComments(IP.getNextRandow(), movieId, limit, offset);
                        for (HotMovieDetailCommentVo vo2 : HotMovieDetailCommentVos) {
                            try {
                                vo2.setCreateTime(this.getTimestamp());
                                vo2.setUpdateTime(this.getTimestamp());
                                vo2.setContent(filterEmoji(vo2.getContent()));
                                hotMovieDetailCommentJpa.save(vo2);
                            } catch (Exception e) {
                                LOGGER.error("数据插入异常：{}", vo, e);
                            }
                        }
                        if (HotMovieDetailCommentVos.size() == 0) {
                            break;
                        }
                    } catch (Exception e) {
                        LOGGER.error("异常：{}", e.toString(), e);
                    }

                }
            });
            LOGGER.info("检查loadInHotMoviesDetailComment数据异常 - 补充数据 end");
        }
    }

    /**
     * 用于处理热门区域的 影院下面的电影
     */
    public void loadInCinemasWithMovie() {
        List<String> citys = new ArrayList<>();
        citys.add("北京市");
        citys.add("杭州市");
        citys.add("溧阳市");
        citys.add("南京市");
        citys.add("上海市");
        citys.add("苏州市");
        citys.add("无锡市");
        citys.add("盐城市");
        List<Integer> ids = cinemasDetailJpa.findOnlyCinemasId();
        cinemasJpa.findCinemasVoByCityIsInAndIdIsNotIn(citys, ids).forEach(cinemasVo -> {
            try {
                CinemasWithMovie cinemasWithMovie = maoyanCinemasFactory.
                        geCinemasWithMovie(IP.getNextRandow(), cinemasVo.getId());
                cinemasWithMovie.getDealList().getDealList().forEach(cinemasDealVo -> {
                    //用于处理 商品
                    cinemasDealVo.setCinemasId(Integer.valueOf(cinemasWithMovie.getCinemaId()));
                    cinemasDealVo.setCreateTime(this.getTimestamp());
                    cinemasDealVo.setUpdateTime(this.getTimestamp());
                    cinemasDealJpa.save(cinemasDealVo);
                    LOGGER.info("保存cinemasDealVo：{} 成功", cinemasDealVo);
                });
                cinemasWithMovie.getShowData().getMovies().forEach(moviesVo -> {
                    moviesVo.setCinemasId(Integer.valueOf(cinemasWithMovie.getCinemaId()));
                    moviesVo.setMovieId(moviesVo.getId());
                    moviesVo.setId(null);
                    moviesVo.setCreateTime(this.getTimestamp());
                    moviesVo.setUpdateTime(this.getTimestamp());
                    cinemasMoviesJpa.save(moviesVo);
                    LOGGER.info("保存 moviesVo：{} 成功", moviesVo);
                    //用于处理电影院播放的电影
                    moviesVo.getShows().forEach(cinemasMoviesShowsVo -> {
                        //保存show
                        cinemasMoviesShowsVo.setCinemasId(Integer.valueOf(cinemasWithMovie.getCinemaId()));
                        cinemasMoviesShowsVo.setMovieId(moviesVo.getId());
                        cinemasMoviesShowsVo.setCreateTime(this.getTimestamp());
                        cinemasMoviesShowsVo.setUpdateTime(this.getTimestamp());
                        cinemasMoviesShowsJpa.save(cinemasMoviesShowsVo);
                        cinemasMoviesShowsVo.getPlist().forEach(cinemasMoviePlistVo -> {
                            //用于处理 场次
                            cinemasMoviePlistVo.setCinemasId(Integer.valueOf(cinemasWithMovie.getCinemaId()));
                            cinemasMoviePlistVo.setMovieId(moviesVo.getId());
                            cinemasMoviePlistVo.setShowDate(cinemasMoviesShowsVo.getShowDate());
                            cinemasMoviePlistVo.setCreateTime(this.getTimestamp());
                            cinemasMoviePlistVo.setUpdateTime(this.getTimestamp());
                            cinemasMoviePlistJpa.save(cinemasMoviePlistVo);
                            LOGGER.info("保存 cinemasMoviePlistVo：{} 成功", cinemasMoviePlistVo);
                        });
                    });
                });
                cinemasWithMovie.getShowData().getVipInfo().forEach(cinemasVipInfoVo -> {
                    //处理会员 数据
                    cinemasVipInfoVo.setCinemasId(Integer.valueOf(cinemasWithMovie.getCinemaId()));
                    cinemasVipInfoVo.setCreateTime(this.getTimestamp());
                    cinemasVipInfoVo.setUpdateTime(this.getTimestamp());
                    cinemasVipInfoJpa.save(cinemasVipInfoVo);
                    LOGGER.info("保存 cinemasVipInfoVo：{} 成功", cinemasVipInfoVo);
                });

            } catch (InterruptedException e) {
                LOGGER.error("loadInCinemasWithFilm 异常：{}", cinemasVo, e);
            } catch (Exception e) {
                LOGGER.error(" 异常：{}", cinemasVo, e);
            }

        });

    }


    /**
     * 用于初始化城市数据
     */
    public void InitCityJson() throws IOException {
        LOGGER.info("执行城市init开始");
        File file = ResourceUtils.getFile("classpath:city.json");
        String path = file.getPath();
        String string = FileUtil.readAsString(file);

        JSONArray jsonArray = JSON.parseArray(string);

        List<City> cities =
                jsonArray.toJavaList(City.class);

        cities.stream().forEach(vo -> {
            LOGGER.info("vo: {}", vo);
            vo.getChildren().stream().forEach(child -> {
                CityVo cityVo = new CityVo();
                cityVo.setBelongCity(vo.getName());
                cityVo.setCity(child.getName());
                cityVo.setLat(child.getLat());
                cityVo.setLog(child.getLog());
                try {
                    Thread.sleep(100);
                    cityVo.setCreateTime(this.getTimestamp());
                    cityVo.setUpdateTime(this.getTimestamp());
                    cityJpa.save(cityVo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        });
        LOGGER.info("执行城市init end");
    }

    private Timestamp getTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    public static String filterEmoji(String source) {
        if (source != null) {
            Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                    Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                source = emojiMatcher.replaceAll("*");
                return source;
            }
            return source;
        }
        return source;
    }

}
