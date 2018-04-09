package demo.spring.boot.demospringboot.jpa.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import demo.spring.boot.demospringboot.jpa.vo.VMovieVo;

/**
 * 2018/4/7    Created by   chao
 */
@Component
public interface VMovieJpa extends JpaRepository<VMovieVo, Integer> {
    /**
     * 根据
     */
    List<VMovieVo> findVMovieVosByVideoUrlIsNot(String videoUrl, Pageable pageable);
}
