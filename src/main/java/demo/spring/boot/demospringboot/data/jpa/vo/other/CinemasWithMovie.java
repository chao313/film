
package demo.spring.boot.demospringboot.data.jpa.vo.other;


import lombok.Data;
import lombok.ToString;

/**
 * 这个是作为主体存的
 */

@Data
@ToString
public class CinemasWithMovie {
    private CinemaData cinemaData;
    private ShowData showData;
    private String membercardDetail;
    private String cinemaId;
    private int movieIndex;
    private DealList dealList;
    private int channelId;
}