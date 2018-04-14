/**
  * Copyright 2018 bejson.com 
  */
package demo.spring.boot.demospringboot.data.jpa.vo.other.seats;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NewSections {
    private int cols;
    private int rows;
    private List<Seats> seats;
    private String sectionId;
    private String sectionName;
}