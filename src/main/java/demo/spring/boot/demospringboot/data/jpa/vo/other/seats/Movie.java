/**
 * Copyright 2018 bejson.com
 */
package demo.spring.boot.demospringboot.data.jpa.vo.other.seats;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Movie {
    private long movieId;
    private String movieName;
}