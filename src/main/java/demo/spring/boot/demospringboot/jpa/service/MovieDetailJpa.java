package demo.spring.boot.demospringboot.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import demo.spring.boot.demospringboot.jpa.vo.MovieDetail;

@Service
public interface MovieDetailJpa extends JpaRepository<MovieDetail, Integer> {
}
