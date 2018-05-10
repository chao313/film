package demo.spring.boot.init;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.spring.boot.demospringboot.Application;
import demo.spring.boot.demospringboot.service.DataFactoryService;

/**
 * 2018/4/12    Created by   juan
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = Application.class)
public class Init {

    private static Logger LOGGER = LoggerFactory.getLogger(Init.class);

    @Autowired
    private DataFactoryService dataFactoryService;

    @Test
    public void test() {
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
}
