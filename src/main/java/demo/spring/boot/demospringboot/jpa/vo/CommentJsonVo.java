package demo.spring.boot.demospringboot.jpa.vo;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "comment_json")
@Data
@ToString
public class CommentJsonVo {
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
}
