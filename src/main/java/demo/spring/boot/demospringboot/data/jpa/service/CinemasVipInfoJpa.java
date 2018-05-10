package demo.spring.boot.demospringboot.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import demo.spring.boot.demospringboot.data.jpa.vo.CinemasVipInfoVo;

/**
 * 2018/4/12    Created by   juan
 */
@Service
public interface CinemasVipInfoJpa extends JpaRepository<CinemasVipInfoVo,Integer>{
}

