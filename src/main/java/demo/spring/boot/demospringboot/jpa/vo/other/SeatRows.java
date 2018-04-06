package demo.spring.boot.demospringboot.jpa.vo.other;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * 2018/4/6    Created by   chao
 */
@Data
@ToString
public class SeatRows {
    private int columns;

    private String rowId;

    private int rowNum;

    private List<Seats> seats;

}