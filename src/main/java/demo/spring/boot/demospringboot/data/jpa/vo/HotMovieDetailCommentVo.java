package demo.spring.boot.demospringboot.data.jpa.vo;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "t_hot_movie_detail_comment")
@Data
@ToString
public class

HotMovieDetailCommentVo {
    private Integer movieId;

    private String vipInfo;

    private Double score;

    private Integer userId;

    private String nickName;

    private String time;

    private String nick;

    private Integer approve;

    private Integer oppose;

    private Integer reply;

    private String avatarurl;

    @Id
    private Integer id;

    @Lob
    private String content;

    //星级
    @Transient
    private List<Integer> full;
    @Transient
    private List<Integer> half;
    @Transient
    private List<Integer> empty;


}
