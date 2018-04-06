package demo.spring.boot.film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.spring.boot.demospringboot.DemoSpringBootApplication;
import demo.spring.boot.demospringboot.thrid.party.api.maoyan.MaoyanCinemasFactory;

/**
 * 2018/4/5    Created by   chao
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = DemoSpringBootApplication.class)
public class CinemasFactory {

    @Autowired
    private MaoyanCinemasFactory maoyanCinemasFactory;

    @Test
    public void testJson() throws InterruptedException {
        Integer showId = 27582;
        String showDate = "2018-04-06";
        String ip = "202.96.128.166";
        maoyanCinemasFactory.getSeats(showId,showDate,ip);
    }


}
