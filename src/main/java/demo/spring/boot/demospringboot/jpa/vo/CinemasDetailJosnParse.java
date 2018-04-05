
package demo.spring.boot.demospringboot.jpa.vo;

import java.util.List;

import demo.spring.boot.demospringboot.jpa.vo.other.CinemaDetailModel;
import demo.spring.boot.demospringboot.jpa.vo.other.CurrentMovie;
import demo.spring.boot.demospringboot.jpa.vo.other.DateShowIndex;
import demo.spring.boot.demospringboot.jpa.vo.other.Dates;
import demo.spring.boot.demospringboot.jpa.vo.other.Movies;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class CinemasDetailJosnParse {

    private List<Movies> movies;
    private String cssLink;
    private List<Dates> dates;
    private CurrentMovie currentMovie;
    private CinemaDetailModel cinemaDetailModel;
    private List<DateShowIndex> dateShow;


}