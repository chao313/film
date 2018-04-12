package demo.spring.boot.demospringboot.data.jpa.vo;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "t_hot_movie")
@Data
@ToString
public class HotMovieVo {
    private String showInfo;

    private int cnms;

    private int sn;

    private boolean late;

    private String src;

    private String showDate;

    private int pn;

    private boolean imax;

    private String vd;

    private String scm;

    private int snum;

    private int dur;

    private int preSale;

    private String nm;

    private String img;

    private double sc;

    private String ver;

    private String rt;

    private String dir;

    private String star;

    private String cat;

    private int wish;

    private boolean threed;

    private String time;

    @Id
    private int id;
}
