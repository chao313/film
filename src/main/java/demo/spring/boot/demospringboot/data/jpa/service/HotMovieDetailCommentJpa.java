package demo.spring.boot.demospringboot.data.jpa.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import demo.spring.boot.demospringboot.data.jpa.vo.HotMovieDetailCommentVo;

@Service
public interface HotMovieDetailCommentJpa extends JpaRepository<HotMovieDetailCommentVo, Integer> {

    List<HotMovieDetailCommentVo>
    findHotMovieDetailCommentVosByMovieIdAndAvatarurlIsNot(Integer movieId, String avatarurl, Pageable pageable);
}
