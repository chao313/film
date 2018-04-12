package demo.spring.boot.demospringboot.data.jpa.vo.other;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FeatureTags {
    private String tag;
    private int type;
    private String desc;
}