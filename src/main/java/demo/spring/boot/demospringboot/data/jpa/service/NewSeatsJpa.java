package demo.spring.boot.demospringboot.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import demo.spring.boot.demospringboot.data.jpa.vo.NewSeatsVo;

/**
 * 2018/4/14    Created by   juan
 */
@Service
public interface NewSeatsJpa extends JpaRepository<NewSeatsVo,String>{
}
