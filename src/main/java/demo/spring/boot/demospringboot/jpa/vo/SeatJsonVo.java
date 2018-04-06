package demo.spring.boot.demospringboot.jpa.service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 2018/4/6    Created by   chao
 */
@Entity
@Table
public class SeatJsonVo {
    @Id
    private Integer id;
    private Integer cinemasId;
    private String showDate;
    private String seatJson;
}
