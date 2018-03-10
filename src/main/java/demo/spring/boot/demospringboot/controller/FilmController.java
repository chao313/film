package demo.spring.boot.demospringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import demo.spring.boot.demospringboot.jpa.vo.CinemasJsonVo;

@RestController
public class FilmController {

    @GetMapping(value = "{page}/{limit}")
    public List<CinemasJsonVo> getCinems(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "limit") Integer limit) {




    }
}
