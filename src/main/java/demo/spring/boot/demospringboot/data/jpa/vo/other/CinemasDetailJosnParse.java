
package demo.spring.boot.demospringboot.data.jpa.vo.other;

import java.util.List;

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