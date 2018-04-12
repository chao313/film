package demo.spring.boot.demospringboot.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import demo.spring.boot.demospringboot.data.jpa.vo.CityVo;

/**
 * 2018/4/10    Created by   chao
 */
@Service
public interface CityJpa extends JpaRepository<CityVo, Integer> {

    CityVo findFirstByCity(String city);
}
