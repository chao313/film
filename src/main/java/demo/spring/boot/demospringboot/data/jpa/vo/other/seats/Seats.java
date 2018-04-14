/**
  * Copyright 2018 bejson.com 
  */
package demo.spring.boot.demospringboot.data.jpa.vo.other.seats;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Seats {
    private List<Columns> columns;
    private String rowId;
    private int rowNum;
}