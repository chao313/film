package demo.spring.boot.demospringboot.data.jpa.vo.other;

import lombok.Data;
import lombok.ToString;

/**
 * 2018/4/6    Created by   chao
 */

@Data
@ToString
public class Seats {
    private int columnNum;

    private String columnId;

    private String seatNo;

    private String rowId;

    private int rowNum;

    private String type;


}
