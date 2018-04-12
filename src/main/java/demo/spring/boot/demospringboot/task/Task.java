package demo.spring.boot.demospringboot.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

import demo.spring.boot.demospringboot.controller.thrid.party.data.service.DataFactoryService;
import demo.spring.boot.demospringboot.util.DateUtils;

/**
 * 2018/4/8    Created by   chao
 */

@Configuration //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class Task {

    private static Logger LOGGER = LoggerFactory.getLogger(Task.class);

    @Autowired
    private DataFactoryService dataFactoryService;

    @Value("${path.videofile}")
    private String path;

    /**
     * 定时加载 hotMovie
     */
    @Scheduled(cron = "${task.movieTask}")
    public void HotMovieTask() {
        LOGGER.info("Task 载入hotMovie 开始执行时间:{}", this.getTime());
        dataFactoryService.loadInHotMovie();
        LOGGER.info("Task 载入hotMovie 结束执行时间:{}", this.getTime());
    }

    /**
     * @description 检查 hotMovie 是否正常
     * @description 需要执行多次
     */
    @Scheduled(cron = "${task.makeUpMovieTask}")
    public void makeUpHotMovieTask() {
        LOGGER.info("Task 补充hotMovie 开始执行时间:{}", this.getTime());
        dataFactoryService.makeUpHotMovie();
        LOGGER.info("Task 补充hotMovie 结束执行时间:{}", this.getTime());
    }

    /**
     * @description 定时加载 hotMovieDetail
     * @description 因为只是补全数据所以需要执行多次
     */
    @Scheduled(cron = "${task.movieDetailTask}")
    public void loadInHotMoviesDetail() {
        LOGGER.info("Task 载入hotMovieDetail 开始执行时间:{}", this.getTime());
        dataFactoryService.loadInHotMoviesDetail();
        LOGGER.info("Task 载入hotMovieDetail 结束执行时间:{}", this.getTime());
    }

    /**
     * @description 定时加载 hotMovieDetailComment
     * @description 因为只是补全数据所以需要执行多次
     */
    @Scheduled(cron = "${task.movieDetailCommontTask}")
    public void loadInHotMoviesDetailComment() {
        LOGGER.info("Task 载入hotMovieDetail 开始执行时间:{}", this.getTime());
        try {
            dataFactoryService.loadInHotMoviesDetailComment();
        } catch (InterruptedException e) {
            LOGGER.info("Task 载入hotMovieDetail 执行异常:{}", this.getTime(), e);
        }
        LOGGER.info("Task 载入hotMovieDetail 结束执行时间:{}", this.getTime());
    }


    /**
     * @description 定时加载 loadInCinemasWithFilm
     * @description 只要数据不为空 所以需要执行多次
     */
    @Scheduled(cron = "${task.loadInCinemasWithFilmTask}")
    public void loadInCinemasWithFilm() {
        LOGGER.info("Task 载入 loadInCinemasWithFilm 开始执行时间:{}", this.getTime());
        try {
            dataFactoryService.loadInCinemasWithFilm();
        } catch (Exception e) {
            LOGGER.info("Task 载入 loadInCinemasWithFilm  执行异常:{}", this.getTime(), e);
        }
        LOGGER.info("Task 载入 loadInCinemasWithFilm 结束执行时间:{}", this.getTime());
    }




    private String getTime() {
        return DateUtils.convert(new Date(), DateUtils.DATE_TIME_FORMAT);
    }

}
