package demo.spring.boot.demospringboot.controller.film;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.spring.boot.demospringboot.data.jpa.service.CinemasJpa;
import demo.spring.boot.demospringboot.data.jpa.vo.CinemasVo;
import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;

/**
 * 2018/4/12    Created by   chao
 */
@RestController
@RequestMapping(value = "/cinemas")
public class CinemasController {

    private static Logger LOGGER = LoggerFactory.getLogger(CinemasController.class);

    @Autowired
    private CinemasJpa cinemasJpa;


    @GetMapping(value = "/get-cinemas/{cinemasId}")
    public Response<CinemasVo> getCinemasById(@PathVariable(value = "cinemasId") Integer cinemasId) {
        LOGGER.info("请求查询电影院数据:{}", cinemasId);
        Response<CinemasVo> response = new Response<>();
        try {
            CinemasVo vo = cinemasJpa.findOne(cinemasId);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(vo);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(Code.SystemError.SERVER_INTERNAL_ERROR_MSG);
            response.addException(e);
        }
        return response;
    }

}
