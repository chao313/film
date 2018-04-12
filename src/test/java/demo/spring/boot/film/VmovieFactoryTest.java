package demo.spring.boot.film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import demo.spring.boot.demospringboot.Application;
import demo.spring.boot.demospringboot.data.jpa.vo.VMovieVo;
import demo.spring.boot.demospringboot.thrid.party.api.vmovie.Config;
import demo.spring.boot.demospringboot.thrid.party.api.vmovie.VmovieFactory;

/**
 * 2018/4/6    Created by   chao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = Application.class)
public class VmovieFactoryTest {

    private static Logger LOGGER = LoggerFactory.getLogger(VmovieFactoryTest.class);
    @Autowired
    private VmovieFactory vmovieFactory;

    @Test
    public void testHot() {
        List<VMovieVo> hotVMovie = vmovieFactory.getHotVMovie(1, 1, Config.Tab.HOT);
        LOGGER.info("result is {}",hotVMovie);
    }
}
