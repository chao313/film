package demo.spring.boot.demospringboot.data.jpa.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import demo.spring.boot.demospringboot.data.jpa.vo.other.Shows;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "t_cinemas_movie")
public class CinemasMoviesVo {
    private int dur;
    private String sc;
    private long wish;
    private int preferential;
    private String img;
    private int showCount;
    @Transient
    private List<Shows> shows;
    private boolean globalReleased;
    private Long movieId;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "des")
    private String desc;
    private String nm;

    //补充
    private Integer cinemasId;

}