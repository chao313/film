package demo.spring.boot.demospringboot.data.jpa.vo.other;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CinemaData {
    private double lng;
    private int cinemaId;
    private boolean sell;
    private long shopId;
    private String addr;
    private double lat;
    private String nm;
}