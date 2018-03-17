package demo.spring.boot.demospringboot.thrid.party.api.maoyan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import demo.spring.boot.demospringboot.jpa.vo.CinemasDetailVo;
import demo.spring.boot.demospringboot.jpa.vo.CinemasJsonVo;
import demo.spring.boot.demospringboot.jpa.vo.CommentJsonVo;
import demo.spring.boot.demospringboot.jpa.vo.HotMovieJsonVo;
import demo.spring.boot.demospringboot.jpa.vo.MovieDetailJsonVo;
import demo.spring.boot.demospringboot.thrid.party.util.Http;

@Component
public class MaoyanCinemasFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(MaoyanCinemasFactory.class);

    @Autowired
    private Http http;


    public List<CinemasJsonVo> loadInCinemas(String ip) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.3");
        requestHeaders.add("x-forwarded-for", ip);
        ResponseEntity<String> result =
                this.http.http(Config.CINEMAS_URL, requestHeaders, HttpMethod.GET);
        JSONObject jsonObject = JSON.parseObject(result.getBody());
        Map<String, Object> innerMap =
                jsonObject.getInnerMap();
        Map<String, JSONArray> map = (Map<String, JSONArray>) innerMap.get("data");
        List<CinemasJsonVo> cinemasJsonVos = new ArrayList<>();
        for (String key : map.keySet()) {
            LOGGER.info("key{}", key);
            LOGGER.info("value{}", map.get(key));
            JSONArray jsonArray = map.get(key);
            for (CinemasJsonVo vo : jsonArray.toJavaList(CinemasJsonVo.class)) {
                cinemasJsonVos.add(vo);
            }
        }
        return cinemasJsonVos;
    }

    /**
     * 获取movie
     */
    public List<HotMovieJsonVo> loadInMovies(String ip) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.3");
        //   requestHeaders.add("x-forwarded-for", ip);
        ResponseEntity<String> result =
                this.http.http(Config.HOT_MOVIE_URL, requestHeaders, HttpMethod.GET);
        JSONObject jsonObject = JSON.parseObject(result.getBody());
        Map<String, Object> innerMap =
                jsonObject.getInnerMap();
        Map<String, JSONArray> map = (Map<String, JSONArray>) innerMap.get("data");
        List<HotMovieJsonVo> hotMovieJsonVos = new ArrayList<>();

        JSONArray jsonArray = map.get("movies");
        for (HotMovieJsonVo vo : jsonArray.toJavaList(HotMovieJsonVo.class)) {
            hotMovieJsonVos.add(vo);

        }
        return hotMovieJsonVos;
    }

    /**
     * 获取movieDeatil
     */
    public MovieDetailJsonVo loadInMoviesDetail(String ip, String movieId) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.3");
        requestHeaders.add("x-forwarded-for", ip);
        ResponseEntity<String> result =
                this.http.http(Config.MOVIE_DETAIL_HEAD + movieId + Config.MOVIE_DETAIL_END,
                        requestHeaders, HttpMethod.GET);
        JSONObject jsonObject = JSON.parseObject(result.getBody());
        Map<String, Object> innerMap =
                jsonObject.getInnerMap();
        Map<String, JSONObject> map = (Map<String, JSONObject>) innerMap.get("data");

        JSONObject jsonObject1 = map.get("MovieDetailModel");

        return jsonObject1.toJavaObject(MovieDetailJsonVo.class);
    }

    /**
     * 获取movieDeatil
     */
    public List<CommentJsonVo> loadInComments(String ip, Integer movieId,
                                              Integer limit, Integer offset) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.3");
        requestHeaders.add("x-forwarded-for", ip);
        ResponseEntity<String> result =
                this.http.http(Config.COMMENT_URL + "?limit="
                                + limit + "&offset="
                                + offset + "&movieid="
                                + movieId,
                        requestHeaders, HttpMethod.GET);
        JSONObject jsonObject = JSON.parseObject(result.getBody());
        Map<String, Object> innerMap =
                jsonObject.getInnerMap();
        List<CommentJsonVo> commentJsonVos = new ArrayList<>();
        Map<String, JSONObject> map = (Map<String, JSONObject>) innerMap.get("data");
        JSONObject jsonObject1 = map.get("CommentResponseModel");
        for (CommentJsonVo vo : ((JSONArray) jsonObject1.get("cmts")).toJavaList(CommentJsonVo.class)) {
            vo.setMovieId(movieId);
            commentJsonVos.add(vo);
        }
        return commentJsonVos;
    }


    /**
     * 获取CinemasDeatil
     */
    public List<CinemasDetailVo> loadInCinemasDetail(String ip, List<String> movieIds,
                                                     Integer cinemasId) throws InterruptedException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.3");
        requestHeaders.add("x-forwarded-for", ip);
        List<CinemasDetailVo> cinemasDetailVos
                = new ArrayList<>();
        for (String movieId : movieIds) {
            String url = Config.CINEMAS_DETAILS
                    + "?cinemaid=" + cinemasId
                    + "&movieid=" + movieId;
            Thread.sleep(2000);
            ResponseEntity<String> result =
                    this.http.http(url,
                            requestHeaders, HttpMethod.GET);
            JSONObject jsonObject = JSON.parseObject(result.getBody());
            Map<String, Object> innerMap =
                    jsonObject.getInnerMap();
            if (!innerMap.get("status").equals(0)) {
                //状态如果不为0
                //跳过当前进入下一个循环
                continue;
            }
            String content = ((JSONObject) innerMap.get("data")).toString();
            //数据填充
            CinemasDetailVo vo = new CinemasDetailVo();
            vo.setCinemasId(cinemasId);
            vo.setContent(content);
            vo.setMovieId(Integer.valueOf(movieId));
            cinemasDetailVos.add(vo);
        }
        return cinemasDetailVos;
    }


    /**
     * 根据电影院的id获取电影院的播放的电影id
     */
    public List<String> geCinemasMovieIds(String ip,
                                          Integer cinemasId) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.3");
        requestHeaders.add("x-forwarded-for", ip);
        String url = Config.CINEMAS_DETAILS
                + "?cinemaid=" + cinemasId
                + "&movieid=";
        ResponseEntity<String> result =
                this.http.http(url,
                        requestHeaders, HttpMethod.GET);
        JSONObject jsonObject = JSON.parseObject(result.getBody());
        Map<String, Object> innerMap =
                jsonObject.getInnerMap();
        if (!innerMap.get("status").equals(0)) {
            //状态如果不为0
            //跳过当前进入下一个循环
            return new ArrayList<>();
        }
        List<CinemasDetailVo> commentJsonVos = new ArrayList<>();
        Map<String, JSONArray> map = (Map<String, JSONArray>) innerMap.get("data");
        JSONArray jsonArray = map.get("movies");
        List<String> ids = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject vo = (JSONObject) o;
            ids.add(vo.getString("id"));
        }

        return ids;
    }


}
