package demo.spring.boot.demospringboot.data.jpa.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * 2018/4/14    Created by   chao
 */
@Data
@ToString
@Entity
@Table(name = "t_new_seats")
public class NewSeatsVo {
    @Id
    private String seqNo;
    @Lob
    private String content;
}
