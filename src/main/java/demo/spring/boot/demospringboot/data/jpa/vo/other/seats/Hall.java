/**
  * Copyright 2018 bejson.com 
  */
package demo.spring.boot.demospringboot.data.jpa.vo.other.seats;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Hall {
    private long hallId;
    private String hallName;
}