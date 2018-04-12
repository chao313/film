//package demo.spring.boot.demospringboot.data.jpa.vo;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import lombok.Data;
//import lombok.ToString;
//
//@Entity
//@Table(name = "cinemas")
//@Data
//@ToString(callSuper = true)
//public class CinemasVo {
//
//    @Id
//    @Column(name = "id", length = 11)
//    private Integer id;
//
//    @Column(name = "cinemas_id", length = 11, columnDefinition = "INT COMMENT '电影院id'")
//    private Integer cinemasId; //id
//
//    @Column(name = "name", columnDefinition = "varchar(20) COMMENT '电影院名称'")
//    public String name;
//    public String ct;
//    @Column(name = "latitude", columnDefinition = "varchar(12) COMMENT '纬度'")
//    public String latitude;
//    @Column(name = "longitude", columnDefinition = "varchar(12) COMMENT '经度'")
//    public String longitude;
//    public String poiId;
//    public String sellmin;
//    public String sell;
//    public String preferential;
//    @Column(name = "sellPrice", columnDefinition = "varchar(11) COMMENT '价格'")
//    public String sellPrice;
//    public String dis;
//    public String brd;
//    @Column(name = "address", columnDefinition = "varchar(50) COMMENT '地址'")
//    public String address;//addr
//    public String deal;
//    public String distance;
//    public String follow;
//    public String imax;
//    @Column(name = "area", columnDefinition = "varchar(50) COMMENT '地区'")
//    public String area;
//    public String brdId;
//    public String dealPrice;
//    public String referencePrice;
//    public String showCount;
//
//}
