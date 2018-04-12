package demo.spring.boot.demospringboot.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.data.jpa.constant.StorageEnum;
import demo.spring.boot.demospringboot.data.jpa.service.StorageVoJpa;
import demo.spring.boot.demospringboot.data.jpa.vo.StorageVo;

/**
 * 2018/4/8    Created by   chao
 */
@RestController
@RequestMapping("/storage")
public class StorageContoller {
    @Autowired
    private StorageVoJpa storageVoJpa;

    @GetMapping(value = "/add-search-history/{userId}/{value}")
    public Response<Boolean> addSearchHistory(
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "value") String value) {
        Response<Boolean> response
                = new Response<>();
        try {
            StorageVo vo = new StorageVo();
            vo.setCategory(StorageEnum.SEARCH_HISTORY.getCategory());
            vo.setUserId(userId);
            vo.setCreateTime(new Timestamp(new Date().getTime()));
            vo.setUpdateTime(new Timestamp(new Date().getTime()));
            StorageVo save = storageVoJpa.save(vo);
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

    @GetMapping(value = "/add-find-record/{userId}/{value}")
    public Response<Boolean> addFindRecord(
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "value") String value) {
        Response<Boolean> response
                = new Response<>();
        try {
            StorageVo vo = new StorageVo();
            vo.setCategory(StorageEnum.FIND_RECORD.getCategory());
            vo.setUserId(userId);
            vo.setCreateTime(new Timestamp(new Date().getTime()));
            vo.setUpdateTime(new Timestamp(new Date().getTime()));
            StorageVo save = storageVoJpa.save(vo);
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
}
