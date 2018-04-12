
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
@Table(name = "t_cinemas_deal")
public class CinemasDealVo {
    private int recommendPersonNum;
    private String redirectUrl;
    private String curNumberDesc;
    private int promotionPrice;
    private double price;
    private String imageUrl;
    private int cardTagType;
    private String title;
    private int buyButton;
    private String cardTag;

    //补充
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private Integer cinemasId;
}