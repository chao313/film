package demo.spring.boot.demospringboot.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import demo.spring.boot.demospringboot.data.jpa.vo.CinemasMoviePlistVo;

/**
 * 2018/4/12    Created by   chao
 */
@Service
public interface CinemasMoviePlistJpa extends JpaRepository<CinemasMoviePlistVo,Integer>{

    /**
     * 根据电影院id和电影id获取放映场次
     */
    List<CinemasMoviePlistVo> findCinemasMoviePlistVosByCinemasIdAndMovieId(Integer cinemasId, Long movieId);
}
