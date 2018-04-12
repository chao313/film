package demo.spring.boot.demospringboot.jpa.vo.other;

import java.util.List;

import demo.spring.boot.demospringboot.jpa.vo.CinemasDealVo;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DealList {
    private String stid;
    private List<CinemasDealVo> dealList;

}