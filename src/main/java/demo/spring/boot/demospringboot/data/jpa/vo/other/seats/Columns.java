/**
 * Copyright 2018 bejson.com
 */
package demo.spring.boot.demospringboot.data.jpa.vo.other.seats;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Columns {
    private String columnId;
    private String seatNo;
    private String st;
}