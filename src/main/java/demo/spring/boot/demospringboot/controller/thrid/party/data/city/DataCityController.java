package demo.spring.boot.demospringboot.controller.thrid.party.data.city;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.spring.boot.demospringboot.controller.thrid.party.data.service.DataFactoryService;
import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;

/**
 * 2018/4/10    Created by   chao
 */
@RestController
@RequestMapping("/data-city")
public class DataCityController {
    private static Logger LOGGER =
            LoggerFactory.getLogger(DataCityController.class);

    @Autowired
    private DataFactoryService dataFactoryService;

    @GetMapping("/init-city-json")
    public Response<Boolean> InitCityJson() {
        Response<Boolean> response = new Response<>();
        try {
            dataFactoryService.InitCityJson();
            response.setCode(Code.System.OK);
            response.setContent(true);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(Code.SystemError.SERVER_INTERNAL_ERROR_MSG);
            response.addException(e);
        }
        return response;
    }


}
