package demo.spring.boot.demospringboot.data.jpa.vo;

import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * 2018/5/5    Created by   chao
 */
@Data
@ToString
@Entity
@Table(name = "t_banner")
public class BannerVo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String link;
    @ColumnDefault("100")
    private Integer sort;
    private Timestamp createTime;
    private Timestamp updateTime;
}
