package demo.spring.boot.demospringboot.data.jpa.vo.other;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * 2018/4/5    Created by   juan
 */


@Data
@ToString
class ShowInfo {
    private int showId;

    private String cinemaName;

    private String movieName;

    private int buyNumLimit;

    private String tp;

    private String lang;

    private double price;

    private String hallId;

    private String showTime;

    private String hallName;

    private int cinemaId;

    private String seqNo;

    private int movieId;

    private String showDate;

    private String desc;

}


@Data
@ToString
public class SeatJson {
    private String user;

    private ShowInfo showInfo;

    private List<Sections> sections;

}

