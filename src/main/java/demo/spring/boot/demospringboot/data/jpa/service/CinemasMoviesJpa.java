package demo.spring.boot.demospringboot.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import demo.spring.boot.demospringboot.data.jpa.vo.CinemasMoviesVo;

/**
 * 2018/4/12    Created by   juan
 */
@Service
public interface CinemasMoviesJpa extends JpaRepository<CinemasMoviesVo,Integer>{

    /**
     * 根据电影院来获取放映的电影
     */
    List<CinemasMoviesVo> findCinemasMoviesVosByCinemasId(Integer cinemasId);
}

