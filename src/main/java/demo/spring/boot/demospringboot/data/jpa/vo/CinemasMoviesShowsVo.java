
package demo.spring.boot.demospringboot.data.jpa.vo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import demo.spring.boot.demospringboot.data.jpa.vo.CinemasMoviePlistVo;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Entity
@Table(name = "t_cinemas_movie_shows")
public class CinemasMoviesShowsVo {

    private int preferential;
    private String dateShow;
    @Transient
    private List<CinemasMoviePlistVo> plist;
    private int hasShow;
    private Date showDate;


    //补充的关联数据
    private Integer cinemasId;
    private Long movieId;

    //补充
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private Timestamp createTime;
    private Timestamp updateTime;



}