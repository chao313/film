package demo.spring.boot.demospringboot.data.jpa.vo;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "t_cinemas")
@Data
@ToString
public class CinemasVo {
    private String area;//地区
    private int deal;
    private int distance;//折扣
    private double lng;//经度
    private boolean sell;//是否在卖票
    private double sellPrice;//买票的价格
    private int imax;
    private int sellmin;
    private double dealPrice;
    private int follow;
    private String dis;
    private int preferential;
    private String ct;
    private int showCount;
    private double referencePrice;//参考价格
    private String brd;
    private int brdId;
    private int poiId;
    @Id
    private int id;
    private String addr;//详细地址
    private double lat;//纬度
    private String nm;//电影院名称
    private String city;//影院所在的城市

    private Timestamp createTime;
    private Timestamp updateTime;

}



