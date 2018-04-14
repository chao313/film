/**
  * Copyright 2018 bejson.com 
  */
package demo.spring.boot.demospringboot.data.jpa.vo.other.seats;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SeatData {
    private Cinema cinema;
    private Hall hall;
    private Movie movie;
    private Reminder reminder;
    private Seat seat;
    private Show show;
    private User user;
    private String token;
    private String utm_content;
    private String mobile;
}