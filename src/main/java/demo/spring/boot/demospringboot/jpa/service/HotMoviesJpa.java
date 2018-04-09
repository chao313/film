package demo.spring.boot.demospringboot.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import demo.spring.boot.demospringboot.jpa.vo.HotMovieVo;

@Service
public interface HotMoviesJpa extends JpaRepository<HotMovieVo, Integer> {

    List<HotMovieVo> findByIdNotIn(List<Integer> ids);

}
