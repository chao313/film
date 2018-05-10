package demo.spring.boot.demospringboot.controller.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PATCH;

import demo.spring.boot.demospringboot.data.jpa.service.CinemasDetailJpa;
import demo.spring.boot.demospringboot.data.jpa.service.CinemasJpa;
import demo.spring.boot.demospringboot.data.jpa.service.HotMovieDetailCommentJpa;
import demo.spring.boot.demospringboot.service.DataFactoryService;

/**
 * 2018/4/14    Created by   juan
 */
@RestController
@RequestMapping(value = "/init")
public class InitController {
    private static Logger LOGGER = LoggerFactory.getLogger(InitController.class);

    @Autowired
    private DataFactoryService dataFactoryService;

    @Autowired
    private CinemasDetailJpa cinemasDetailJpa;

    @Autowired
    private CinemasJpa cinemasJpa;

    @PatchMapping(value = "/delete-init")
    public void deleteInit() {
        LOGGER.info("初始化开始");
        dataFactoryService.loadInHotMovie();
        dataFactoryService.makeUpHotMovie();
        dataFactoryService.loadInHotMoviesDetail();
        try {
            dataFactoryService.loadInHotMoviesDetailComment();
        } catch (InterruptedException e) {
            LOGGER.info("Task 载入hotMovieDetail 执行异常:{}", e.toString());
        }
        cinemasDetailJpa.deleteAll();
        dataFactoryService.loadInCinemasWithMovie();
        LOGGER.info("初始化结束");
    }

    @PatchMapping(value = "/makeup-init")
    public void makeupInit() {
        LOGGER.info("初始化开始");
        dataFactoryService.loadInHotMovie();
        dataFactoryService.makeUpHotMovie();
        dataFactoryService.loadInHotMoviesDetail();
        try {
            dataFactoryService.loadInHotMoviesDetailComment();
        } catch (InterruptedException e) {
            LOGGER.info("Task 载入hotMovieDetail 执行异常:{}", e.toString());
        }
        dataFactoryService.loadInCinemasWithMovie();
        LOGGER.info("初始化结束");
    }

    @PatchMapping(value = "/cinemas-movie-delete-init")
    public void cinemasWithMovieDeleteInit() {
        cinemasDetailJpa.deleteAll();
        dataFactoryService.loadInCinemasWithMovie();
        LOGGER.info("初始化结束");
    }

    @PatchMapping(value = "/cinemas-movie-makeup-init")
    public void cinemasWithMovieMakeUpInit() {
        dataFactoryService.loadInCinemasWithMovie();
        LOGGER.info("初始化结束");
    }
}
