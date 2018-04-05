package demo.spring.boot.demospringboot.controller.thrid.party.data.maoyan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.jpa.service.CinemasDetailJpa;
import demo.spring.boot.demospringboot.jpa.service.CinemasJpa;
import demo.spring.boot.demospringboot.jpa.service.HotMoviesJpa;
import demo.spring.boot.demospringboot.jpa.vo.CinemasDetailVo;
import demo.spring.boot.demospringboot.jpa.vo.HotMovieJsonVo;
import demo.spring.boot.demospringboot.thrid.party.api.maoyan.MaoyanCinemasFactory;

@RestController
@RequestMapping(value = "/data")
public class DataFactoryController {

    private static Logger LOGGER =
            LoggerFactory.getLogger(DataFactoryController.class);

    @Autowired
    private MaoyanCinemasFactory maoyanCinemasFactory;

    @Autowired
    private CinemasDetailJpa cinemasDetailJpa;

    @Autowired
    private HotMoviesJpa hotMoviesJpa;

    @Autowired
    private CinemasJpa cinemasJpa;

    @GetMapping("/load-in-cinemas-detail-one")
    public Response<Boolean> loadInCinemasDetailOne(String ip,
                                                    @RequestParam(name = "电影id") Integer movieId,
                                                    @RequestParam(name = "电影院id") Integer cinemasId) {
        Response<Boolean> response = new Response<>();
        try {

            ip = "202.96.128.166";

            List<String> movieIds = maoyanCinemasFactory
                    .geCinemasMovieIds(ip, movieId);
            maoyanCinemasFactory.
                    loadInCinemasDetail(ip, movieIds, cinemasId).stream()
                    .forEach(vo -> {
                        cinemasDetailJpa.save(vo);
                    });
            response.setCode(Code.System.OK);
            response.setContent(true);
        } catch (InterruptedException e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(Code.SystemError.SERVER_INTERNAL_ERROR_MSG);
            response.addException(e);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(Code.SystemError.SERVER_INTERNAL_ERROR_MSG);
            response.addException(e);
        }
        return response;
    }

    @GetMapping("/load-in-cinemas-detail-all")
    public Response<Boolean> loadInCinemasDetailAll() {
        Response<Boolean> response = new Response<>();
        try {
            final String ip = "202.96.128.166";
            cinemasJpa.findAll().stream().forEach(hotMovie -> {
                List<String> movieIds = null;
                try {
                    movieIds = maoyanCinemasFactory
                            .geCinemasMovieIds(ip, hotMovie.getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    maoyanCinemasFactory.
                            loadInCinemasDetail(ip, movieIds, hotMovie.getId()).stream()
                            .forEach(vo -> {
                                cinemasDetailJpa.save(vo);
                            });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            response.setCode(Code.System.OK);
            response.setContent(true);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(Code.SystemError.SERVER_INTERNAL_ERROR_MSG);
            response.addException(e);
        }
        return response;
    }

    @GetMapping("load-in-hotmoives")
    public Response<Boolean> loadInHotMovies(
            @RequestParam(name = "ip", defaultValue = "43.241.51.255") String ip) {
        Response<Boolean> response = new Response<>();
        try {
            hotMoviesJpa.deleteAll();
            maoyanCinemasFactory.loadInMovies(ip)
                    .stream()
                    .forEach(vo -> {
                        LOGGER.info("保存{}", vo);
                        hotMoviesJpa.save(vo);
                    });
            response.setCode(Code.System.OK);
            response.setContent(true);
        } catch (Exception e) {
            LOGGER.info("error ip:{}", ip, e);
            response.setCode(Code.System.FAIL);
            response.setContent(false);
            response.addException(e);
        }
        return response;
    }
}
