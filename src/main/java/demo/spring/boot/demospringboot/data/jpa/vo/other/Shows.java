
package demo.spring.boot.demospringboot.data.jpa.vo.other;

import java.util.List;
import java.util.Date;

import demo.spring.boot.demospringboot.data.jpa.vo.CinemasMoviePlistVo;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class Shows {

    private int preferential;
    private String dateShow;
    private List<CinemasMoviePlistVo> plist;
    private int hasShow;
    private Date showDate;


}