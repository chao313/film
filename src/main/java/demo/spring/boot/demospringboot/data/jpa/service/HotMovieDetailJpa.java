package demo.spring.boot.demospringboot.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import demo.spring.boot.demospringboot.data.jpa.vo.HotMovieDetailVo;

@Service
public interface HotMovieDetailJpa extends JpaRepository<HotMovieDetailVo, Integer> {
}
