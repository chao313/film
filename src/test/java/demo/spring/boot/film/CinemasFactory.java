package demo.spring.boot.film;

import com.alibaba.fastjson.JSON;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.spring.boot.demospringboot.Application;
import demo.spring.boot.demospringboot.data.jpa.vo.other.NewSeats;
import demo.spring.boot.demospringboot.thrid.party.api.maoyan.MaoyanCinemasFactory;

/**
 * 2018/4/5    Created by   chao
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = Application.class)
public class CinemasFactory {

   private static Logger LOGGER = LoggerFactory.getLogger(CinemasFactory.class);

    @Autowired
    private MaoyanCinemasFactory maoyanCinemasFactory;

    @Test
    public void testJson() throws InterruptedException {
        Integer showId = 27582;
        String showDate = "2018-04-06";
        String ip = "202.96.128.166";
        maoyanCinemasFactory.getSeats(showId,showDate,ip);
    }

    @Test
    public void testSeat(){
        LOGGER.info("开始：");
        String seqNo = "201804150149616";
        String newSeats = maoyanCinemasFactory.getNewSeats(seqNo);
        NewSeats vo = JSON.parseObject(newSeats, NewSeats.class);
        LOGGER.info("结果：{}",vo);

    }


}
