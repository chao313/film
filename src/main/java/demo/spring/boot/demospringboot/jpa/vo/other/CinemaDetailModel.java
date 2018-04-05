package demo.spring.boot.demospringboot.jpa.vo.other;


import java.util.List;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CinemaDetailModel {
    private String bus;
    private String note;
    private int snum;
    private String callboard;
    private int dealtp;
    private String dis;
    private int preferential;
    private String suw;
    private String brd;
    private int price;
    private List<String> tel;
    private String dri;
    private int id;
    private String addr;
    private String park;
    private double lat;
    private double s1;
    private double s2;
    private String area;
    private double s3;
    private double lng;
    private boolean sell;
    private List<FeatureTags> featureTags;
    private int sellmin;
    private int imax;
    private String ct;
    private double s;
    private String deals;
    private String nm;
}
