package demo.spring.boot.demospringboot.data.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import demo.spring.boot.demospringboot.data.mybatis.mapper.CinemasMapper;
import demo.spring.boot.demospringboot.data.mybatis.vo.CinemasJsonBo;

@Service
public class CinemasService {

    @Autowired
    private CinemasMapper mapper;

    public List<CinemasJsonBo> queryCinemasByDist(String theLat, String thelng, Integer page, Integer size) {
        page = page <= 0 ? 0 : page;
        List<CinemasJsonBo> cinemasJsonBos = mapper.queryCinemasByDist(theLat, thelng, page * size, size);
        return cinemasJsonBos;
    }


    public List<CinemasJsonBo> queryCinemasByMovieIDByDist(String theLat, String thelng, Integer page, Integer size,String movieid) {
        page = page <= 0 ? 0 : page;
        List<CinemasJsonBo> cinemasJsonBos = mapper.queryCinemasByMovieIDByDist(theLat, thelng, page * size, size,movieid);
        return cinemasJsonBos;
    }

}
