package demo.spring.boot.demospringboot.controller.film;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.jpa.service.CinemasDetailJpa;
import demo.spring.boot.demospringboot.jpa.service.CinemasJpa;
import demo.spring.boot.demospringboot.jpa.service.CommentJpa;
import demo.spring.boot.demospringboot.jpa.service.HotMoviesJpa;
import demo.spring.boot.demospringboot.jpa.service.MovieDetailJpa;
import demo.spring.boot.demospringboot.jpa.vo.CinemasDetailVo;
import demo.spring.boot.demospringboot.jpa.vo.CinemasJsonVo;
import demo.spring.boot.demospringboot.jpa.vo.CommentJsonVo;
import demo.spring.boot.demospringboot.jpa.vo.CinemasDetailJosnParse;
import demo.spring.boot.demospringboot.jpa.vo.HotMovieJsonVo;
import demo.spring.boot.demospringboot.jpa.vo.MovieDetailJsonVo;
import demo.spring.boot.demospringboot.jpa.vo.other.DateShow;
import demo.spring.boot.demospringboot.jpa.vo.other.DateShowIndex;

@RestController
@RequestMapping(value = "/film")
public class FilmController {

    @Autowired
    private HotMoviesJpa hotMoviesJpa;

    @Autowired
    private MovieDetailJpa movieDetailJpa;

    @Autowired
    private CommentJpa commentJpa;


    @Autowired
    private CinemasJpa cinemasJpa;

    @Autowired
    private CinemasDetailJpa cinemasDetailJpa;


    @GetMapping(value = "/getCinems/{page}/{limit}")
    public List<CinemasJsonVo> getCinems(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "limit") Integer limit) {

        return null;


    }

    @GetMapping(value = "/getHotMovies/{page}/{size}")
    public Response<List<HotMovieJsonVo>> getHotMovies(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size) {
        Response<List<HotMovieJsonVo>> response
                = new Response<>();
        try {
            Pageable pageable = new PageRequest(page, size);
            Page<HotMovieJsonVo> result = hotMoviesJpa.findAll(pageable);
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
    public Response<MovieDetailJsonVo> getHotMovies(
            @PathVariable(value = "movieId") Integer movieId) {
        Response<MovieDetailJsonVo> response
                = new Response<>();
        try {
            MovieDetailJsonVo movieDetailJsonVo
                    = movieDetailJpa.findOne(movieId);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(movieDetailJsonVo);
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
    public Response<List<CommentJsonVo>> getComments(
            @PathVariable(value = "movieId") Integer movieId,
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size) {
        Response<List<CommentJsonVo>> response
                = new Response<>();
        try {

            //构造查询条件
            CommentJsonVo commentJsonVo = new CommentJsonVo();
            commentJsonVo.setMovieId(movieId);

            ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                    //姓名采用“开始匹配”的方式查询
                    .withIgnoreNullValues();
            Example<CommentJsonVo> example = Example.of(commentJsonVo);
            //分页查询
            Pageable pageable = new PageRequest(page, size);
            Page<CommentJsonVo> result = commentJpa.findAll(example, pageable);
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

    /**
     * 获取 电影院
     */
    @GetMapping(value = "/getCinemas/{lat}/{lng}/{page}/{size}")
    public Response<List<CinemasJsonVo>> getCinemas(
            @PathVariable(value = "lat") Integer lat,
            @PathVariable(value = "lng") Integer lng,
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size) {
        Response<List<CinemasJsonVo>> response
                = new Response<>();
        try {
            Pageable pageable = new PageRequest(page, size);
            Page<CinemasJsonVo> result = cinemasJpa.findAll(pageable);
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

    /**
     * 搜索 电影院
     */
    @GetMapping(value = "/search/{key}/{page}/{size}")
    public Response<List<CinemasJsonVo>> getCinemas(
            @PathVariable(value = "key") String key,
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size) {
        Response<List<CinemasJsonVo>> response
                = new Response<>();
        try {

            //分页查询
            Pageable pageable = new PageRequest(page, size);
            List<CinemasJsonVo> result =
                    cinemasJpa.findCinemasJsonVoByNmLike("%" + key + "%", pageable);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(Code.SystemError.SERVER_INTERNAL_ERROR_MSG);
            response.addException(e);
        }
        return response;
    }

    /**
     * 获取 电影院 正在播放的电影 电影院id必填
     */
    @GetMapping(value = "/get-cinema-and-movie/{cinemaId}")
    public Response<CinemasDetailJosnParse> getCinemaAndMovie(
            @PathVariable(value = "cinemaId") Integer cinemaId,
            @RequestParam(value = "movieId", required = false) Integer movieId) {
        Response<CinemasDetailJosnParse> response
                = new Response<>();
        try {
            CinemasDetailVo cinemasDetailVo = null;
            if (null == movieId) {
                //根据电影院id来查询
                cinemasDetailVo = cinemasDetailJpa.getFirstByCinemasId(cinemaId);
            } else {
                //根据电影院id和电影id来查询
                cinemasDetailVo = cinemasDetailJpa.
                        findCinemasDetailVoByCinemasIdAndMovieId(cinemaId, movieId);
            }
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            CinemasDetailJosnParse cinemasDetailJosnParse =
                    JSON.parseObject(cinemasDetailVo.getContent(), CinemasDetailJosnParse.class);
            List<DateShowIndex> dateShowIndexList = new ArrayList<>();
            this.getDateShows(cinemasDetailVo, dateShowIndexList);
            cinemasDetailJosnParse.setDateShow(dateShowIndexList);
             response.setContent(cinemasDetailJosnParse);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(Code.SystemError.SERVER_INTERNAL_ERROR_MSG);
            response.addException(e);
        }
        return response;
    }

    private void getDateShows(CinemasDetailVo cinemasDetailVo, List<DateShowIndex> dateShowIndexList) {
        JSONObject jsonObject = JSON.parseObject(cinemasDetailVo.getContent());
        JSONObject dateShowIndexJSONObject = (JSONObject) jsonObject.getInnerMap().get("DateShow");
        if (null != dateShowIndexJSONObject) {
            dateShowIndexJSONObject.getInnerMap().forEach((k, v) -> {
                //k就是日期  eg. 2018-4-6
                DateShowIndex dateShowIndex = new DateShowIndex();
                //填充日期
                dateShowIndex.setDate(k);
                //获取放映list
                List<DateShow> dateShows = ((JSONArray) v).toJavaList(DateShow.class);
                dateShowIndex.setDateShows(dateShows);
                dateShowIndexList.add(dateShowIndex);
            });

        }
    }
}
