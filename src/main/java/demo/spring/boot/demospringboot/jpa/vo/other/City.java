
package demo.spring.boot.demospringboot.jpa.vo.other;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class City {
    private String name;
    private String log;
    private String lat;
    private List<CityChildren> children;

}