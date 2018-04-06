package demo.spring.boot.demospringboot.jpa.vo.other;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * 2018/4/6    Created by   chao
 */
public
@Data
@ToString
class Sections {
    private List<SeatRows> seatRows;

    private int rows;

    private int columns;

    private String sectionId;

    private String sectionName;

    private Integer[][] map;

}