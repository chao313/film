
package demo.spring.boot.demospringboot.data.jpa.vo.other;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CurrentMovie {
    private double sc;
    private int preferential;
    private boolean isShowing;
    private String ver;
    private String img;
    private long id;
    private String nm;

}