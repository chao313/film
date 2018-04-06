package demo.spring.boot.demospringboot.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import demo.spring.boot.demospringboot.jpa.vo.CinemasDetailVo;
import demo.spring.boot.demospringboot.jpa.vo.SeatJson;
import demo.spring.boot.demospringboot.jpa.vo.SeatJsonVo;

@Service
public interface SeatJpa extends JpaRepository<SeatJsonVo, Integer> {

}
