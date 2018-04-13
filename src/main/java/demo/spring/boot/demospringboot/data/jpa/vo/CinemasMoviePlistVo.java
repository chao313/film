
package demo.spring.boot.demospringboot.data.jpa.vo;

import java.util.Date;

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
@Table(name = "t_cinemas_movie_plist")
//播放场次
public class CinemasMoviePlistVo {

    private boolean showTypeHighLight;
    private String seqNo;
    private String showTag;
    private String sellPr;
    private int reservedMin;
    private boolean smallFont;
    private Date dt;
    private int preferential;
    private int showClosedSeat;
    private String th;
    private int ticketStatus;
    private int enterShowSeat;
    private String vipPrice;
    private String vipPriceNameNew;
    private String tm;
    private boolean hallTypeHighLight;
    private String lang;
    private String tp;
    private String vipPriceName;
    private String extraDesc;

    //补充的关联数据
    private Integer cinemasId;
    private Long movieId;
    private Date showDate;

    //补充
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    /**
     * 为了前端展示
     */
    private String price;
    private String start;
}