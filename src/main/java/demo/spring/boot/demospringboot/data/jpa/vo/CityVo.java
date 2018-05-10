package demo.spring.boot.demospringboot.data.jpa.vo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * 2018/4/10    Created by   juan
 */
@Data
@ToString
@Entity
@Table(name = "t_city")
public class CityVo {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String city;

    private String log;

    private String lat;

    private String belongCity;

    private Timestamp createTime;
    private Timestamp updateTime;
}
