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

import demo.spring.boot.demospringboot.jpa.service.CinemasDealJpa;
import demo.spring.boot.demospringboot.jpa.service.CinemasJpa;
import demo.spring.boot.demospringboot.jpa.service.CinemasMoviePlistJpa;
import demo.spring.boot.demospringboot.jpa.service.CinemasMoviesJpa;
import demo.spring.boot.demospringboot.jpa.service.CinemasVipInfoJpa;
import demo.spring.boot.demospringboot.jpa.vo.CinemasDealVo;
import demo.spring.boot.demospringboot.jpa.vo.CinemasDetailVo;
import demo.spring.boot.demospringboot.jpa.vo.CinemasVo;
import demo.spring.boot.demospringboot.jpa.vo.HotMovieDetailCommentVo;
import demo.spring.boot.demospringboot.jpa.vo.HotMovieVo;
import demo.spring.boot.demospringboot.jpa.vo.HotMovieDetailVo;
import demo.spring.boot.demospringboot.jpa.vo.other.CinemasWithMovie;
import demo.spring.boot.demospringboot.jpa.vo.other.SeatJson;
import demo.spring.boot.demospringboot.thrid.party.util.Http;

@Component
public class MaoyanCinemasFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(MaoyanCinemasFactory.class);

    @Autowired
    private Http http;

    @Autowired
    private CinemasJpa cinemasJpa;

    @Autowired
    private CinemasDealJpa cinemasDealJpa;

    @Autowired
    private CinemasMoviesJpa cinemasMoviesJpa;

    @Autowired
    private CinemasMoviePlistJpa cinemasMoviePlistJpa;

    @Autowired
    private CinemasVipInfoJpa cinemasVipInfoJpa;


    public boolean loadInCinemas(String ip, String city) {
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
        List<CinemasVo> cinemasVos = new ArrayList<>();
        for (String key : map.keySet()) {
            LOGGER.info("key{}", key);
            LOGGER.info("value{}", map.get(key));
            JSONArray jsonArray = map.get(key);
            for (CinemasVo vo : jsonArray.toJavaList(CinemasVo.class)) {
                vo.setCity(city);
                cinemasVos.add(vo);
            }
        }
        cinemasVos.stream().forEach(vo -> {
            cinemasJpa.save(vo);
        });
        return true;
    }

    /**
     * 获取movie
     */
    public List<HotMovieVo> loadInMovies(String ip) {
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
        List<HotMovieVo> hotMovieVos = new ArrayList<>();

        JSONArray jsonArray = map.get("movies");
        for (HotMovieVo vo : jsonArray.toJavaList(HotMovieVo.class)) {
            hotMovieVos.add(vo);

        }
        return hotMovieVos;
    }

    /**
     * 获取movieDeatil
     */
    public HotMovieDetailVo loadInMoviesDetail(String ip, String movieId) {
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

        return jsonObject1.toJavaObject(HotMovieDetailVo.class);
    }

    /**
     * 获取movieDeatil
     */
    public List<HotMovieDetailCommentVo> loadInComments(String ip, Integer movieId,
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
        List<HotMovieDetailCommentVo> HotMovieDetailCommentVos = new ArrayList<>();
        Map<String, JSONObject> map = (Map<String, JSONObject>) innerMap.get("data");
        JSONObject jsonObject1 = map.get("CommentResponseModel");
        for (HotMovieDetailCommentVo vo : ((JSONArray) jsonObject1.get("cmts")).toJavaList(HotMovieDetailCommentVo.class)) {
            vo.setMovieId(movieId);
            HotMovieDetailCommentVos.add(vo);
        }
        return HotMovieDetailCommentVos;
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
                    + "?cinemaId=" + cinemasId
                    + "&movieid=" + movieId;
            Thread.sleep(4000);
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
                                          Integer cinemasId) throws InterruptedException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.3");
        requestHeaders.add("x-forwarded-for", ip);
        String url = Config.CINEMAS_DETAILS
                + "?cinemaId=" + cinemasId
                + "&movieid=";
        Thread.sleep(4000);
        ResponseEntity<String> result =
                this.http.http(url,
                        requestHeaders, HttpMethod.GET);
        CinemasWithMovie cinemasWithMovie =
                JSONObject.parseObject(result.getBody(), CinemasWithMovie.class);

        cinemasWithMovie.getDealList().getDealList().forEach(cinemasDealVo -> {
            //用于处理 商品
            cinemasDealVo.setCinemasId(Integer.valueOf(cinemasWithMovie.getCinemaId()));
            cinemasDealJpa.save(cinemasDealVo);
        });
        cinemasWithMovie.getShowData().getMovies().forEach(moviesVo -> {
            moviesVo.setCinemasId(Integer.valueOf(cinemasWithMovie.getCinemaId()));
            cinemasMoviesJpa.save(moviesVo);
            //用于处理电影院播放的电影
            moviesVo.getShows().forEach(shows -> {
                shows.getPlist().forEach(cinemasMoviePlistVo -> {
                    //用于处理 场次
                    cinemasMoviePlistVo.setCinemasId(Integer.valueOf(cinemasWithMovie.getCinemaId()));
                    cinemasMoviePlistVo.setMovieId(moviesVo.getId());
                    cinemasMoviePlistJpa.save(cinemasMoviePlistVo);
                });
            });
        });
        cinemasWithMovie.getShowData().getVipInfo().forEach(cinemasVipInfoVo -> {
            //处理会员 数据
            cinemasVipInfoVo.setCinemasId(Integer.valueOf(cinemasWithMovie.getCinemaId()));
            cinemasVipInfoJpa.save(cinemasVipInfoVo);
        });
        return null;

        //((JSONObject)(jsonObject.getInnerMap().get("showData"))).get("movies")
    }


    public SeatJson getSeats(Integer showId, String showDate, String ip) throws InterruptedException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.3");
        requestHeaders.add("x-forwarded-for", ip);
        requestHeaders.add("Host", "m.maoyan.com");
        requestHeaders.add("Upgrade-Insecure-Requests", "1");
        requestHeaders.add("Pragma", "no-cache");
        String url = Config.SEATS
                + "?showId=" + showId
                + "&showDate=" + showDate;
        //  Thread.sleep(1000);
        ResponseEntity<String> result =
                this.http.http(url,
                        requestHeaders, HttpMethod.GET);


        SeatJson seatJson = JSON.parseObject(result.getBody(), SeatJson.class);
        return seatJson;

    }

    public String getSeatsString(Integer showId, String showDate, String ip) throws InterruptedException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.3");
        requestHeaders.add("x-forwarded-for", ip);
        requestHeaders.add("Host", "m.maoyan.com");
        requestHeaders.add("Upgrade-Insecure-Requests", "1");
        requestHeaders.add("Pragma", "no-cache");
        requestHeaders.add("Cookie", "_lx_utm=utm_source%3Dbaidu%26utm_medium%3Dorganic; v=3; iuuid=1A6E888B4A4B29B16FBA1299108DBE9C3B4875CF6218F71ABAF9253534349662; _lx_utm=utm_source%3Dbaidu%26utm_medium%3Dorganic; isWebp=1; __utmz=17099173.1520147187.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __mta=143545866.1520144472913.1520769516692.1521291379848.17; __utma=17099173.512769798.1520147187.1521291379.1521296563.4; ci=10; _lxsdk_cuid=1629404bc8cc8-028eb3c9291128-33697b04-13c680-1629404bc8cc8; _lxsdk=1A6E888B4A4B29B16FBA1299108DBE9C53D2DC452615728BC9FC0C8DA28112C2; __mta=143545866.1520144472913.1521300897553.1522901761310.19; JSESSIONID=19myb6yxrpc23ip7iuhqtb6hx");
        String url = Config.SEATS
                + "?showId=" + showId
                + "&showDate=" + showDate;
        Thread.sleep(4000);
        ResponseEntity<String> result =
                this.http.http(url,
                        requestHeaders, HttpMethod.GET);
        return result.getBody();

    }

    /**
     * 根据电影院的id获取电影院播放的电影 场次...
     */
    public CinemasWithMovie geCinemasWithMovie(String ip,
                                               Integer cinemasId) throws InterruptedException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.3");
        requestHeaders.add("x-forwarded-for", ip);
        String url = Config.CINEMAS_DETAILS
                + "?cinemaId=" + cinemasId;
        Thread.sleep(4000);
        ResponseEntity<String> result =
                this.http.http(url,
                        requestHeaders, HttpMethod.GET);
        CinemasWithMovie cinemasWithMovie =
                JSONObject.parseObject(result.getBody(), CinemasWithMovie.class);
        return cinemasWithMovie;

    }



}
