package demo.spring.boot.demospringboot.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import demo.spring.boot.demospringboot.data.jpa.vo.BannerVo;

/**
 * 2018/5/5    Created by   juan
 */
@Service
public interface BannerJpa extends JpaRepository<BannerVo,Integer> {
}
