package demo.spring.boot.demospringboot.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import demo.spring.boot.demospringboot.data.jpa.vo.CinemasMoviesShowsVo;

/**
 * 2018/4/12    Created by   juan
 */
@Service
public interface CinemasMoviesShowsJpa extends JpaRepository<CinemasMoviesShowsVo, Integer> {

    /**
     * 根据电影院id和电影id来查询放映时间
     * @param cinemasId
     * @param movieId
     * @return
     */
    List<CinemasMoviesShowsVo> findDistinctByCinemasIdAndMovieId(Integer cinemasId, Long movieId);

    /**
     * 根据热映电影来寻找数据
     */
    List<CinemasMoviesShowsVo> findDistinctByMovieId(Long movieId);
}
