package demo.spring.boot.demospringboot.jpa.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * 2018/4/6    Created by   chao
 */
@Entity
@Table(name = "t_seat")
@Data
@ToString
public class SeatJsonVo {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Integer id;
    private Integer cinemasId;
    private String showDate;
    @Lob
    private String seatJson;
}
