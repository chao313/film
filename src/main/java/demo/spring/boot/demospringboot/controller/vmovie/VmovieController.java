package demo.spring.boot.demospringboot.controller.vmovie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.thrid.party.api.vmovie.Config;
import demo.spring.boot.demospringboot.thrid.party.api.vmovie.VmovieFactory;

/**
 * 2018/4/6    Created by   juan
 */

@RestController
@RequestMapping(value = "/data")
public class VmovieController {
    private static Logger LOGGER =
            LoggerFactory.getLogger(VmovieController.class);
    @Autowired
    private VmovieFactory vmovieFactory;

    @GetMapping("/load-in-newVMovie")
    public Response<Boolean> loadInNewVMovie() {
        Response<Boolean> response = new Response<>();
        try {
            vmovieFactory.loadInHotVMovie(Config.Tab.NEW);
            response.setCode(Code.System.OK);
            response.setContent(true);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            LOGGER.error("异常 ：{} ", e.getMessage(), e);
        }
        return response;

    }

    @GetMapping("/load-in-randVMovie")
    public Response<Boolean> loadInRandVMovie() {
        Response<Boolean> response = new Response<>();
        try {
            vmovieFactory.loadInHotVMovie(Config.Tab.RAND);
            response.setCode(Code.System.OK);
            response.setContent(true);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            LOGGER.error("异常 ：{} ", e.getMessage(), e);
        }
        return response;

    }

    @GetMapping("/load-in-startsVMovie")
    public Response<Boolean> loadInStartsVMovie() {
        Response<Boolean> response = new Response<>();
        try {
            vmovieFactory.loadInHotVMovie(Config.Tab.STARTS);
            response.setCode(Code.System.OK);
            response.setContent(true);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            LOGGER.error("异常 ：{} ", e.getMessage(), e);
        }
        return response;

    }

    @GetMapping("/load-in-hotVMovie")
    public Response<Boolean> loadInHotVMovie() {
        Response<Boolean> response = new Response<>();
        try {
            vmovieFactory.loadInHotVMovie(Config.Tab.HOT);
            response.setCode(Code.System.OK);
            response.setContent(true);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            LOGGER.error("异常 ：{} ", e.getMessage(), e);
        }
        return response;

    }

    @GetMapping("/load-in-VMovieSrc")
    public Response<Boolean> loadInVMovieSrc() {
        Response<Boolean> response = new Response<>();
        try {
            vmovieFactory.loadInVHotMovieSrc();
            response.setCode(Code.System.OK);
            response.setContent(true);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            LOGGER.error("异常 ：{} ", e.getMessage(), e);
        }
        return response;

    }
}
