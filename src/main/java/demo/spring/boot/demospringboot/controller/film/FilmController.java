package demo.spring.boot.demospringboot.controller.film;

import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import demo.spring.boot.demospringboot.data.jpa.service.CinemasDealJpa;
import demo.spring.boot.demospringboot.data.jpa.service.CinemasDetailJpa;
import demo.spring.boot.demospringboot.data.jpa.service.CinemasJpa;
import demo.spring.boot.demospringboot.data.jpa.service.CinemasMoviePlistJpa;
import demo.spring.boot.demospringboot.data.jpa.service.CinemasMoviesJpa;
import demo.spring.boot.demospringboot.data.jpa.service.CinemasMoviesShowsJpa;
import demo.spring.boot.demospringboot.data.jpa.service.CinemasVipInfoJpa;
import demo.spring.boot.demospringboot.data.jpa.service.HotMovieDetailCommentJpa;
import demo.spring.boot.demospringboot.data.jpa.service.HotMovieDetailJpa;
import demo.spring.boot.demospringboot.data.jpa.service.HotMoviesJpa;
import demo.spring.boot.demospringboot.data.jpa.service.NewSeatsJpa;
import demo.spring.boot.demospringboot.data.jpa.service.VMovieJpa;
import demo.spring.boot.demospringboot.data.jpa.vo.CinemasDetailVo;
import demo.spring.boot.demospringboot.data.jpa.vo.CinemasMoviePlistVo;
import demo.spring.boot.demospringboot.data.jpa.vo.CinemasMoviesShowsVo;
import demo.spring.boot.demospringboot.data.jpa.vo.CinemasMoviesVo;
import demo.spring.boot.demospringboot.data.jpa.vo.CinemasVo;
import demo.spring.boot.demospringboot.data.jpa.vo.HotMovieDetailCommentVo;
import demo.spring.boot.demospringboot.data.jpa.vo.HotMovieDetailVo;
import demo.spring.boot.demospringboot.data.jpa.vo.HotMovieVo;
import demo.spring.boot.demospringboot.data.jpa.vo.NewSeatsVo;
import demo.spring.boot.demospringboot.data.jpa.vo.VMovieVo;
import demo.spring.boot.demospringboot.data.jpa.vo.other.CinemasWithMovie;
import demo.spring.boot.demospringboot.data.jpa.vo.other.NewSeats;
import demo.spring.boot.demospringboot.data.jpa.vo.other.SeatJson;
import demo.spring.boot.demospringboot.data.jpa.vo.other.Sections;
import demo.spring.boot.demospringboot.data.jpa.vo.other.seats.NewSections;
import demo.spring.boot.demospringboot.data.mybatis.service.CinemasService;
import demo.spring.boot.demospringboot.data.mybatis.vo.CinemasJsonBo;
import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.thrid.party.api.maoyan.MaoyanCinemasFactory;
import demo.spring.boot.demospringboot.util.CommonUtil;
import demo.spring.boot.demospringboot.util.DateUtils;
import demo.spring.boot.demospringboot.util.IP;

@RestController
@RequestMapping(value = "/film")
public class FilmController {

    private static Logger LOGGER = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    private HotMoviesJpa hotMoviesJpa;

    @Autowired
    private HotMovieDetailJpa hotMovieDetailJpa;

    @Autowired
    private HotMovieDetailCommentJpa hotMovieDetailCommentJpa;


    @Autowired
    private CinemasJpa cinemasJpa;

    @Autowired
    private CinemasDetailJpa cinemasDetailJpa;

    @Autowired
    private MaoyanCinemasFactory maoyanCinemasFactory;

    @Autowired
    private CinemasService cinemasService;

    @Autowired
    private VMovieJpa vMovieJpa;

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
    private NewSeatsJpa newSeatsJpa;

    @GetMapping(value = "/getHotMovies/{page}/{size}")
    public Response<List<HotMovieVo>> getHotMovies(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size) {
        Response<List<HotMovieVo>> response
                = new Response<>();
        try {
            Pageable pageable = new PageRequest(page, size);
            Page<HotMovieVo> result = hotMoviesJpa.findAll(pageable);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(result.getContent());
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(Code.SystemError.SERVER_INTERNAL_ERROR_MSG);
            response.addException(e);
        }
        return response;
    }

    @GetMapping(value = "/getHotMovieDetail/{movieId}")
    public Response<HotMovieDetailVo> getHotMovies(
            @PathVariable(value = "movieId") Integer movieId) {
        Response<HotMovieDetailVo> response
                = new Response<>();
        try {
            HotMovieDetailVo hotMovieDetailVo
                    = hotMovieDetailJpa.findOne(movieId);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            String dra = hotMovieDetailVo.getDra().replace("<p>", "").replace("</p>", "");
            hotMovieDetailVo.setDra(dra);
            Double sc = hotMovieDetailVo.getSc();
            Double floor = Math.floor(sc);//取下线
            Double ceil = Math.ceil(sc);//取上线
            Integer full;
            Integer half;
            Integer empty;
            if (floor.equals(ceil)) {
                //如果相等就是没有half
                full = floor.intValue() / 2;
                half = 0;
                empty = 5 - full;
            } else {
                //否则 有一个half
                full = floor.intValue() / 2;
                half = 1;
                empty = 4 - full;
            }
            hotMovieDetailVo.setFull(CommonUtil.generate(full));
            hotMovieDetailVo.setHalf(CommonUtil.generate(half));
            hotMovieDetailVo.setEmpty(CommonUtil.generate(empty));
            response.setContent(hotMovieDetailVo);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(Code.SystemError.SERVER_INTERNAL_ERROR_MSG);
            response.addException(e);
        }
        return response;
    }

    /**
     * 获取 评论
     */
    @GetMapping(value = "/getComments/{movieId}/{page}/{size}")
    public Response<List<HotMovieDetailCommentVo>> getComments(
            @PathVariable(value = "movieId") Integer movieId,
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size) {
        Response<List<HotMovieDetailCommentVo>> response
                = new Response<>();
        try {
            //分页查询
            Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "time");
            List<HotMovieDetailCommentVo> reslut = hotMovieDetailCommentJpa.
                    findHotMovieDetailCommentVosByMovieIdAndAvatarurlIsNot(movieId, "", pageable);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            reslut.forEach(vo -> {
                Double score = vo.getScore();
                Double floor = Math.floor(score);//取下线
                Double ceil = Math.ceil(score);//取上线
                Integer full;
                Integer half;
                Integer empty;
                if (floor.equals(ceil)) {
                    //如果相等就是没有half
                    full = floor.intValue();
                    half = 0;
                    empty = 5 - full;
                } else {
                    //否则 有一个half
                    full = floor.intValue();
                    half = 1;
                    empty = 4 - full;
                }
                vo.setFull(CommonUtil.generate(full));
                vo.setHalf(CommonUtil.generate(half));
                vo.setEmpty(CommonUtil.generate(empty));
            });
            response.setContent(reslut);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.toString());
            response.addException(e);
        }
        return response;
    }

    /**
     * 获取 电影院
     */
    @GetMapping(value = "/getCinemas/{lat}/{lng}/{page}/{size}")
    public Response<List<CinemasJsonBo>> getCinemas(
            @PathVariable(value = "lat") String lat,
            @PathVariable(value = "lng") String lng,
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size) {
        Response<List<CinemasJsonBo>> response
                = new Response<>();
        try {
            List<CinemasJsonBo> cinemasJsonBos = cinemasService.queryCinemasByDist(lat, lng, page, size);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(cinemasJsonBos);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(Code.SystemError.SERVER_INTERNAL_ERROR_MSG);
            response.addException(e);
        }
        return response;
    }

    /**
     * 获取 电影院
     */
    @GetMapping(value = "/getCinemas/{lat}/{lng}/{page}/{size}/{movieId}")
    public Response<List<CinemasJsonBo>> getCinemasByMovieId(
            @PathVariable(value = "lat") String lat,
            @PathVariable(value = "lng") String lng,
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @PathVariable(value = "movieId") Long movieId) {
        Response<List<CinemasJsonBo>> response
                = new Response<>();
        try {

            List<Integer> ids = new ArrayList<>();
            cinemasMoviesShowsJpa.findDistinctByMovieId(movieId).forEach(vo->{
                 ids.add(vo.getCinemasId());
            });

            List<CinemasJsonBo> cinemasJsonBos = cinemasService.queryCinemasByDist(lat, lng, page, size);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(cinemasJsonBos);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(Code.SystemError.SERVER_INTERNAL_ERROR_MSG);
            response.addException(e);
        }
        return response;
    }


    /**
     * 搜索 电影院
     */
    @GetMapping(value = "/search/{key}/{page}/{size}")
    public Response<List<CinemasVo>> SearchCinemas(
            @PathVariable(value = "key") String key,
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size) {
        Response<List<CinemasVo>> response
                = new Response<>();
        try {

            //分页查询
            Pageable pageable = new PageRequest(page, size);
            List<CinemasVo> result =
                    cinemasJpa.findCinemasJsonVoByNmLike("%" + key + "%", pageable);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
        }
        return response;
    }

    /**
     * @description 根据影院id获取正在放映的电影
     */
    @GetMapping(value = "/get-cinemas-movies/{cinemasId}")
    public Response<List<CinemasMoviesVo>> getCinemaMoviesByCinemasId(
            @PathVariable(value = "cinemasId") Integer cinemasId) {

        Response<List<CinemasMoviesVo>> response = new Response<>();
        try {
            List<CinemasMoviesVo> result
                    = cinemasMoviesJpa.findCinemasMoviesVosByCinemasId(cinemasId);
            result.stream().filter(cinemasMoviesVo -> {
                cinemasMoviesVo.setImg(cinemasMoviesVo.getImg().replace("w.h", "165.220"));
                return true;
            }).collect(Collectors.toList());
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
        }
        return response;
    }

    /**
     * @description 根据影院id和电影id  获取播放场次
     */
    @GetMapping(value = "/get-show/{cinemasId}/{movieid}")
    public Response<List<CinemasMoviesShowsVo>> getShowByCinemasIdAndMovieId(
            @PathVariable(value = "cinemasId") Integer cinemasId,
            @PathVariable(value = "movieid") Long movieid) {
        Response<List<CinemasMoviesShowsVo>> response = new Response<>();
        try {
            List<CinemasMoviesShowsVo> result
                    = cinemasMoviesShowsJpa.findDistinctByCinemasIdAndMovieId(cinemasId, movieid);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
        }
        return response;
    }


    /**
     * @description 根据影院id和电影id  获取播放场次
     */
    @GetMapping(value = "/get-show-movies/{cinemasId}/{movieid}")
    public Response<List<CinemasMoviePlistVo>> getCinemaShowByCinemasIdAndMovieId(
            @PathVariable(value = "cinemasId") Integer cinemasId,
            @PathVariable(value = "movieid") Long movieid) {
        Response<List<CinemasMoviePlistVo>> response = new Response<>();
        try {
            List<CinemasMoviePlistVo> result
                    = cinemasMoviePlistJpa.findCinemasMoviePlistVosByCinemasIdAndMovieId(cinemasId, movieid);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
        }
        return response;
    }


    /**
     * 获取 电影院 正在播放的电影 电影院id必填
     */
    @GetMapping(value = "/get-cinema-and-movie/{cinemaId}")
    public Response<CinemasWithMovie> getCinemaAndMovie(
            @PathVariable(value = "cinemaId") Integer cinemaId,
            @RequestParam(value = "movieId", required = false) Integer movieId) {
        Response<CinemasWithMovie> response
                = new Response<>();
        try {
            CinemasDetailVo cinemasDetailVo
                    = cinemasDetailJpa.getFirstByCinemasId(cinemaId);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            CinemasWithMovie result =
                    JSON.parseObject(cinemasDetailVo.getContent(), CinemasWithMovie.class);
            result.getShowData().getMovies().forEach(cinemasMoviesVo -> {
                cinemasMoviesVo.setImg(cinemasMoviesVo.getImg().replace("w.h", "165.220"));
            });
            result.getShowData().getMovies().forEach(cinemasMoviesVo -> {
                cinemasMoviesVo.getShows().forEach(cinemasMoviesShowsVo -> {
                    cinemasMoviesShowsVo.getPlist().forEach(cinemasMoviePlistVo -> {
                        //处理价格
                        BigDecimal bigDecimal = new BigDecimal(cinemasMoviePlistVo.getVipPrice());
                        String privce =
                                bigDecimal.add(new BigDecimal(10)).toString();

                        cinemasMoviePlistVo.setPrice(privce);
                        //处理日期
                        Date convert =
                                DateUtils.convert(cinemasMoviePlistVo.getTm(), "HH:mm");
                        Date date = DateUtils.addMinutes(convert, cinemasMoviesVo.getDur());
                        cinemasMoviePlistVo.setEnd(DateUtils.convert(date,"HH:mm"));
                    });
                });
            });

            //过滤过期
            result.getShowData().getMovies().forEach(cinemasMoviesVo -> {
                cinemasMoviesVo.getShows().forEach(cinemasMoviesShowsVo -> {
                    //过滤过期
                    LOGGER.info("data:{}", cinemasMoviesShowsVo.getDateShow());
                    if (cinemasMoviesShowsVo.getDateShow().contains("今天")) {
                        List<CinemasMoviePlistVo> collect =
                                cinemasMoviesShowsVo.getPlist().stream().filter(cinemasMoviePlistVo -> {
                            String convert = DateUtils.convert(new Date(), "HH:mm");
                            if (cinemasMoviePlistVo.getTm().compareTo(convert) < 0) {
                                LOGGER.info("本场次过期");
                                //如果播放时间小于当前时间
                                return false;
                            } else {
                                LOGGER.info("本场次没有过期");
                                return true;
                            }
                                }).collect(Collectors.toList());
                        cinemasMoviesShowsVo.setPlist(collect);
                    }
                });
            });
            response.setContent(result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
        }
        return response;
    }

    /**
     * 获取 电影院 正在播放的电影 电影院id必填 //
     */
    @GetMapping(value = "/get-seats")
    public Response<SeatJson> getSeats(
            @RequestParam(value = "showDate") String showDate,
            @RequestParam(value = "showId") Integer showId) {
        Response<SeatJson> response
                = new Response<>();
        try {
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            SeatJson seats = maoyanCinemasFactory.getSeats(showId, showDate, IP.getNextRandow());
            Sections sections = seats.getSections().get(0);
            Integer[][] map = new Integer[sections.getRows()][sections.getColumns()];
            sections.getSeatRows().stream().forEach(vo -> {
                vo.getSeats().stream().forEach(vo2 -> {
                    if ("E".equals(vo2.getType())) {
                        map[vo2.getRowNum() - 1][vo2.getColumnNum()] = 0;
                    } else if ("N".equals(vo2.getType())) {
                        map[vo2.getRowNum() - 1][vo2.getColumnNum()] = 1;
                    } else {
                        map[vo2.getRowNum() - 1][vo2.getColumnNum()] = 3;
                    }

                });
            });
            sections.setMap(map);
            response.setContent(seats);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(Code.SystemError.SERVER_INTERNAL_ERROR_MSG);
            response.addException(e);
        }
        return response;
    }


    /**
     * 获取 电影院 正在播放的电影 电影院id必填 //
     */
    @GetMapping(value = "/get-new-seats/{seqNo}")
    public Response<NewSeats> getNewSeats(
            @PathVariable(value = "seqNo") String seqNo) {
        Response<NewSeats> response
                = new Response<>();
        try {
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            String json = maoyanCinemasFactory.getNewSeats(seqNo);
            try {
                json = maoyanCinemasFactory.getNewSeats(seqNo);
                NewSeatsVo vo = new NewSeatsVo();
                vo.setSeqNo(seqNo);
                vo.setContent(json);
                newSeatsJpa.save(vo);
            } catch (Exception e) {
                LOGGER.error("异常：{}", e.toString(), e);
                NewSeatsVo one = newSeatsJpa.findOne(seqNo);
                if (null != one) {
                    json = one.getContent();
                }
            }

            NewSeats vo = JSON.parseObject(json, NewSeats.class);
            NewSections sections
                    = vo.getSeatData().getSeat().getSections().get(0);
            Integer[][] map = new Integer[sections.getRows()][sections.getCols()];
            sections.getSeats().forEach(row -> {
                Integer rowNum = row.getRowNum() - 1;
                LOGGER.info("rowNum:{}", rowNum);
                row.getColumns().forEach(column -> {
                    Integer colNum = getNotNull(map[row.getRowNum() - 1]);
                    LOGGER.info("col:{}", colNum);
                    if ("E".equals(column.getSt())) {
                        map[rowNum][colNum] = 0;
                    } else if ("N".equals((column.getSt()))) {
                        map[rowNum][colNum] = 1;
                    } else {
                        map[rowNum][colNum] = 3;
                    }
                });
            });
            sections.setSeats(null);
            vo.setMap(map);
            response.setContent(vo);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.toString());
            response.addException(e);
        }
        return response;
    }
    /**
     * 获取 电影院
     */
    @GetMapping(value = "/getVMovie/{page}/{size}")
    public Response<List<VMovieVo>> getVMovie(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size) {
        Response<List<VMovieVo>> response
                = new Response<>();
        try {
            Pageable pageable = new PageRequest(page, size);
            List<VMovieVo> vMovieVos = vMovieJpa.findVMovieVosByVideoUrlIsNot("-2", pageable);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(vMovieVos);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(Code.SystemError.SERVER_INTERNAL_ERROR_MSG);
            response.addException(e);
        }
        return response;
    }

    private Integer getNotNull(Integer[] col) {
        List<Integer> list = new ArrayList<>();
        Arrays.stream(col).forEach(vo -> {
            if (vo != null) {
                list.add(vo);
            }
        });
        return list.size();

    }



}
