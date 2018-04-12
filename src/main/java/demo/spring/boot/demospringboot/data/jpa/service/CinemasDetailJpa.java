package demo.spring.boot.demospringboot.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import demo.spring.boot.demospringboot.data.jpa.vo.CinemasDetailVo;

@Service
public interface CinemasDetailJpa extends JpaRepository<CinemasDetailVo, Integer> {


    CinemasDetailVo getFirstByCinemasId(Integer cinemasId);

    /**
     *
     * @param cinemasId
     * @return
     */
    List<CinemasDetailVo> findCinemasDetailVoByCinemasId(Integer cinemasId);

    /**
     *
     * @param cinemasId
     * @param movieId
     * @return
     */
    CinemasDetailVo findCinemasDetailVoByCinemasIdAndMovieId(Integer cinemasId, Integer movieId);
}
