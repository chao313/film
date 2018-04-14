package demo.spring.boot.demospringboot.data.jpa.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "t_hot_movie_detail")
@Data
@ToString
public class HotMovieDetailVo {
    private String cat;

    private int dealsum;

    private String dir;
    @Lob
    private String dra;

    private int dur;

    @Id
    private int id;

    private boolean imax;

    private String img;

    private boolean isShowing;

    private boolean late;

    private int mk;

    private String nm;

    @Lob
    private String photos;

    private int pn;

    private int preSale;

    private String rt;

    private double sc;

    private String scm;

    private boolean showSnum;

    private int snum;

    private String src;

    @Lob
    private String star;

    private String vd;

    private String ver;

    private int vnum;

    private int wish;

    private int wishst;

    //星级
    @Transient
    private List<Integer> full;
    @Transient
    private List<Integer> half;
    @Transient
    private List<Integer> empty;

    private Timestamp createTime;
    private Timestamp updateTime;
}
