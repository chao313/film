package demo.spring.boot.demospringboot.controller.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.jpa.service.CommentJpa;
import demo.spring.boot.demospringboot.jpa.service.HotMoviesJpa;
import demo.spring.boot.demospringboot.jpa.service.MovieDetailJpa;
import demo.spring.boot.demospringboot.jpa.vo.CinemasJsonVo;
import demo.spring.boot.demospringboot.jpa.vo.CommentJsonVo;
import demo.spring.boot.demospringboot.jpa.vo.HotMovieJsonVo;
import demo.spring.boot.demospringboot.jpa.vo.MovieDetailJsonVo;

@RestController
@RequestMapping(value = "/film")
public class FilmController {

    @Autowired
    private HotMoviesJpa hotMoviesJpa;

    @Autowired
    private MovieDetailJpa movieDetailJpa;

    @Autowired
    private CommentJpa commentJpa;


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
}
