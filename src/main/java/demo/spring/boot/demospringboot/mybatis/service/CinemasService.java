package demo.spring.boot.demospringboot.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import demo.spring.boot.demospringboot.mybatis.mapper.CatMapper;
import demo.spring.boot.demospringboot.mybatis.mapper.CinemasMapper;
import demo.spring.boot.demospringboot.mybatis.vo.Cat;
import demo.spring.boot.demospringboot.mybatis.vo.CinemasJsonBo;

@Service
public class CinemasService {

    @Autowired
    private CinemasMapper mapper;

    public List<CinemasJsonBo> queryCinemasByDist(String theLat, String thelng, Integer page, Integer size) {
        page = page <= 0 ? 0 : page;
        List<CinemasJsonBo> cinemasJsonBos = mapper.queryCinemasByDist(theLat, thelng, page * size, size);
        return cinemasJsonBos;
    }

}
