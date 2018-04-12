
package demo.spring.boot.demospringboot.jpa.vo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "t_cinemas_vipInfp")
public class CinemasVipInfoVo {
    private int isCardSales;
    private String process;
    private String tag;
    private String title;
    private String url;

    //补充数据
    private Integer cinemasId;
    //补充
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
}