package demo.spring.boot.demospringboot.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import demo.spring.boot.demospringboot.data.jpa.vo.CinemasDealVo;

/**
 * 2018/4/12    Created by   juan
 *
 * 处理优惠
 */
@Service
public interface CinemasDealJpa extends JpaRepository<CinemasDealVo, Integer> {
}
