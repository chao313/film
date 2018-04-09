package demo.spring.boot.demospringboot.controller.thrid.party.data.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import demo.spring.boot.demospringboot.jpa.service.HotMovieDetailCommentJpa;
import demo.spring.boot.demospringboot.jpa.service.HotMoviesJpa;
import demo.spring.boot.demospringboot.jpa.service.HotMovieDetailJpa;
import demo.spring.boot.demospringboot.jpa.vo.HotMovieDetailCommentVo;
import demo.spring.boot.demospringboot.jpa.vo.HotMovieVo;
import demo.spring.boot.demospringboot.jpa.vo.HotMovieDetailVo;
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


    public void loadInHotMovie() {
        hotMoviesJpa.deleteAll();
        maoyanCinemasFactory.loadInMovies(IP.getNextRandow())
                .stream()
                .forEach(vo -> {
                    LOGGER.info("保存{}", vo);
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
                    List<HotMovieDetailCommentVo> HotMovieDetailCommentVos
                            = maoyanCinemasFactory.loadInComments(IP.getNextRandow(), movieId, limit, offset);
                    for (HotMovieDetailCommentVo vo2 : HotMovieDetailCommentVos) {
                        try {
                            hotMovieDetailCommentJpa.save(vo2);
                        } catch (Exception e) {
                            LOGGER.error("数据插入异常：{}", vo, e);
                        }
                    }
                    if (HotMovieDetailCommentVos.size() == 0) {
                        break;
                    }
                }
            });
            LOGGER.info("检查loadInHotMoviesDetailComment数据异常 - 补充数据 end");

        }

    }

    public void downLoadVideo() {
        List<Integer> ids = new ArrayList<>();
        hotMovieDetailJpa.findAll().stream().forEach(vo -> {
            ids.add(vo.getId());
        });
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
                LOGGER.info("保存{}", hotMovieDetailVo);
                hotMovieDetailJpa.save(hotMovieDetailVo);
            });
            LOGGER.info("检查hotMovieDetail数据异常 - 抓取数据end");
        }
    }

}
