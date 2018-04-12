package demo.spring.boot.film;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import org.aspectj.util.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import demo.spring.boot.demospringboot.DemoSpringBootApplication;
import demo.spring.boot.demospringboot.jpa.service.CityJpa;
import demo.spring.boot.demospringboot.jpa.vo.other.City;
import demo.spring.boot.demospringboot.jpa.vo.CityVo;

/**
 * 2018/4/10    Created by   chao
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = DemoSpringBootApplication.class)
public class CityTest {

    private static Logger LOGGER = LoggerFactory.getLogger(CityTest.class);


    @Autowired
    private CityJpa cityJpa;

    @Test
    public void loadInCityJosn() throws IOException {

        File file = ResourceUtils.getFile("classpath:city.json");
        LOGGER.info("地址：{}");
        String path = file.getPath();
        String string = FileUtil.readAsString(file);

        JSONArray jsonArray = JSON.parseArray(string);

        List<City> cities =
                jsonArray.toJavaList(City.class);


        cities.stream().forEach(vo -> {
            LOGGER.info("vo: {}", vo);
            vo.getChildren().stream().forEach(child -> {
                CityVo cityVo = new CityVo();
                cityVo.setBelongCity(vo.getName());
                cityVo.setCity(child.getName());
                cityVo.setLat(child.getLat());
                cityVo.setLog(child.getLog());
                try {
                    Thread.sleep(100);
                    cityJpa.save(cityVo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        });

    }

}
