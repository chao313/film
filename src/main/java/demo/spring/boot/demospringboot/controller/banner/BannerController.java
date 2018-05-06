package demo.spring.boot.demospringboot.controller.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import demo.spring.boot.demospringboot.data.jpa.service.BannerJpa;
import demo.spring.boot.demospringboot.data.jpa.vo.BannerVo;
import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;

/**
 * 2018/5/5    Created by   chao
 */
@RestController
@RequestMapping(value = "/banner")
public class BannerController {

    @Autowired
    private BannerJpa bannerJpa;

    @GetMapping(value = "")
    public Response<List<BannerVo>> getBanner() {
        Response<List<BannerVo>> response = new Response<>();
        try {
            Sort sort = new Sort(new Sort.Order("sort"));
            List<BannerVo> result = bannerJpa.findAll(sort);
            response.setCode(Code.System.OK);
            response.setContent(result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(Code.SystemError.SERVER_INTERNAL_ERROR_MSG);
            response.addException(e);
        }
        return response;
    }


}
