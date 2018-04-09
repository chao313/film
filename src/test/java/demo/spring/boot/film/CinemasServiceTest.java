package demo.spring.boot.film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import demo.spring.boot.demospringboot.DemoSpringBootApplication;
import demo.spring.boot.demospringboot.mybatis.service.CinemasService;
import demo.spring.boot.demospringboot.mybatis.vo.CinemasJsonBo;

/**
 * 2018/4/6    Created by   chao
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = DemoSpringBootApplication.class)
public class CinemasServiceTest {

    private static Logger LOGGER = LoggerFactory.getLogger(CinemasServiceTest.class);
    @Autowired
    private CinemasService cinemasService;


    @Test
    public void test() {
        String theLat = "31.201341";
        String thelng = "121.633158";
        Integer page = 1;
        Integer size = 10;
        List<CinemasJsonBo> cinemasJsonBos =
                cinemasService.queryCinemasByDist(theLat, thelng, page, size);
        LOGGER.info("the result is :{}", cinemasJsonBos);
    }

}
