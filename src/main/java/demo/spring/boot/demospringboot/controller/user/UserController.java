package demo.spring.boot.demospringboot.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import javax.ws.rs.BeanParam;

import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.jpa.service.UserJpa;
import demo.spring.boot.demospringboot.jpa.vo.UserVo;

/**
 * 2018/4/8    Created by   chao
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserJpa userJpa;

    @GetMapping(value = "/add")
    public Response<Boolean> add(@BeanParam UserVo userVo) {
        Response<Boolean> response
                = new Response<>();
        try {
            userVo.setId(UUID.randomUUID().toString());
            UserVo save = userJpa.save(userVo);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(save == null ? false : true);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
        }
        return response;
    }

    @GetMapping(value = "/query-by-openId/{openId}")
    public Response<UserVo> queryByOpenId(@PathVariable(value = "openId") String openId) {
        Response<UserVo> response
                = new Response<>();
        try {
            UserVo userVo = userJpa.findFirstByOpenid(openId);
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(userVo);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
        }
        return response;
    }
}
