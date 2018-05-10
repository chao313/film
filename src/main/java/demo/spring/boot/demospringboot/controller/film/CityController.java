package demo.spring.boot.demospringboot.controller.film;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.data.jpa.service.CityJpa;
import demo.spring.boot.demospringboot.data.jpa.vo.CityVo;

/**
 * 2018/4/10    Created by   juan
 */
@RestController
@RequestMapping("/city")
public class CityController {

    private static Logger LOGGER
            = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private CityJpa cityJpa;


    @GetMapping("/get-city-by-name/{cityName}")
    public Response<CityVo> InitCityJson(@PathVariable(value = "cityName") String cityName) {
        Response<CityVo> response = new Response<>();
        try {
            CityVo cityVo = cityJpa.findFirstByCity(cityName);
            response.setCode(Code.System.OK);
            response.setContent(cityVo);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
        }
        return response;
    }

}
