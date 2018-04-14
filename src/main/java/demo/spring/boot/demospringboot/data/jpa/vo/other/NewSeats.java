/**
 * Copyright 2018 bejson.com
 */
package demo.spring.boot.demospringboot.data.jpa.vo.other;

import demo.spring.boot.demospringboot.data.jpa.vo.other.seats.SeatData;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NewSeats {
    private SeatData seatData;
    private Integer[][] map;
}

