
package demo.spring.boot.demospringboot.data.jpa.vo.other;

import java.util.List;

import demo.spring.boot.demospringboot.data.jpa.vo.CinemasMoviesVo;
import demo.spring.boot.demospringboot.data.jpa.vo.CinemasVipInfoVo;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ShowData {
    private List<CinemasMoviesVo> movies;
    private List<CinemasVipInfoVo> vipInfo;
    private int modeSwitchThreshold;
    private int cinemaId;
    private String cinemaName;
    private boolean sell;
    private long poiId;
    private List<String> cityCardInfo;
    private int selectedMovieSeq;

}