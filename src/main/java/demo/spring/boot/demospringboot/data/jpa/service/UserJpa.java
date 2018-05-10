package demo.spring.boot.demospringboot.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import demo.spring.boot.demospringboot.data.jpa.vo.UserVo;

/**
 * 2018/4/8    Created by   juan
 */
@Component
public interface UserJpa extends JpaRepository<UserVo, String> {

    UserVo findFirstByOpenid(String openId);
}
