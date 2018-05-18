package demo.spring.boot.demospringboot.data.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

import demo.spring.boot.demospringboot.data.mybatis.vo.CinemasJsonBo;

@Component
public interface CinemasMapper {

    /**
     * 处理了limit后面没有办法计算的情况
     */
    @Select(value = "select id, addr ,area ,brd  ,brd_id as brdId ,ct  ,deal ," +
            "deal_price as dealPrice,dis     ,distance,follow ,imax ,lat  ,lng    " +
            " ,nm,poi_id as poiId ,preferential,reference_price  as referencePrice  ,sell   " +
            " ,sell_price as sellPrice,sellmin ,show_count as showCount ," +
            " acos(cos(${theLat}*pi()/180 )*cos(lat*pi()/180)*cos(${thelng}*pi()/180 -lng*pi()/180)+sin(${theLat}*pi()/180 )*sin(lat*pi()/180))*6370996.81/1000  as dist" +
            " from t_cinemas where nm is not null and id in ( select cinemas_id as id from t_cinemas_details )  order by dist asc limit ${start}, ${size}")
    List<CinemasJsonBo> queryCinemasByDist(@Param(value = "theLat") String theLat,
                                           @Param(value = "thelng") String thelng,
                                           @Param(value = "start") Integer start,
                                           @Param(value = "size") Integer size);

    /**
     * 处理了limit后面没有办法计算的情况
     */
    @Select(value = "select id, addr ,area ,brd  ,brd_id as brdId ,ct  ,deal ," +
            "deal_price as dealPrice,dis     ,distance,follow ,imax ,lat  ,lng    " +
            " ,nm,poi_id as poiId ,preferential,reference_price  as referencePrice  ,sell   " +
            " ,sell_price as sellPrice,sellmin ,show_count as showCount ," +
            " acos(cos(${theLat}*pi()/180 )*cos(lat*pi()/180)*cos(${thelng}*pi()/180 -lng*pi()/180)+sin(${theLat}*pi()/180 )*sin(lat*pi()/180))*6370996.81/1000  as dist" +
            " from t_cinemas where nm is not null and id in ( select cinemas_id as id from t_cinemas_details where locate(${movieId},content)>0  )  order by dist asc limit ${start}, ${size}")
    List<CinemasJsonBo> queryCinemasByMovieIDByDist(@Param(value = "theLat") String theLat,
                                           @Param(value = "thelng") String thelng,
                                           @Param(value = "start") Integer start,
                                           @Param(value = "size") Integer size,
                                           @Param(value = "movieId") String movieId

    );


}
