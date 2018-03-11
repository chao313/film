package demo.spring.boot.film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import demo.spring.boot.demospringboot.DemoSpringBootApplication;
import demo.spring.boot.demospringboot.jpa.service.CinemasJpa;
import demo.spring.boot.demospringboot.jpa.service.CommentJpa;
import demo.spring.boot.demospringboot.jpa.service.HotMoviesJpa;

import demo.spring.boot.demospringboot.jpa.service.MovieDetailJpa;
import demo.spring.boot.demospringboot.jpa.vo.CinemasJsonVo;
import demo.spring.boot.demospringboot.jpa.vo.CommentJsonVo;
import demo.spring.boot.demospringboot.jpa.vo.HotMovieJsonVo;
import demo.spring.boot.demospringboot.jpa.vo.MovieDetailJsonVo;
import demo.spring.boot.demospringboot.thrid.party.api.maoyan.CinemasFactory;
import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = DemoSpringBootApplication.class)
public class cinemasTest {

    @Autowired
    private CinemasJpa jpa;

    public static Logger LOGGER =
            LoggerFactory.getLogger(cinemasTest.class);

    @Test
    public void ipcinemas() {
//
//        String url = "http://m.maoyan.com/cinemas.json";
//        RestTemplate rt = new RestTemplate(createRequestFactory(1000));
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.add("user-agent",
//                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
//                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.3");
//        requestHeaders.add("x-forwarded-for", "120.77.37.251");
//        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);
//        ResponseEntity<String> result = rt.exchange(url, HttpMethod.GET, requestEntity, String.class);
//        LOGGER.info("执行结果{}", result);
//        LOGGER.info("执行结果string{}", result.getBody());
//        JSONObject jsonObject = JSON.parseObject(result.getBody());
//
//
//        Map<String, Object> innerMap =
//                jsonObject.getInnerMap();
//
//        Map<String, JSONArray> map = (Map<String, JSONArray>) innerMap.get("data");
//
//        for (String key : map.keySet()) {
//            LOGGER.info("key{}", key);
//            LOGGER.info("value{}", map.get(key));
//            JSONArray jsonArray = map.get(key);
//            LOGGER.info("value{}", jsonArray);
//            for (CinmasJosnVo cinmasJosnVo : jsonArray.toJavaList(CinmasJosnVo.class)) {
//                LOGGER.info("x{}", cinmasJosnVo);
//
//            }
//
//        }
//
//
//        LOGGER.info("innerMap:{}", innerMap);


    }

//    /**
//     * cinemas的细节
//     */
//    public void cinemas_detail() throws FileNotFoundException {
//        File file = new File("cinemas_detail.json");
//        FileInputStream inputStream = new FileInputStream(file);
//        inputStream.
//
//    }


    @Test
    public void jpaTest() {
        LOGGER.info("执行开始");
        jpa.findAll();
        LOGGER.info("执行结束");
    }


    @Test
    public void cinemas_details() {

        String url = "http://m.maoyan.com/showtime/wrap.json?cinemaid=9271";
        RestTemplate rt = new RestTemplate(createRequestFactory(1000));
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.3");
        requestHeaders.add("x-forwarded-for", "120.77.37.251");
        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);
        ResponseEntity<String> result = rt.exchange(url, HttpMethod.GET, requestEntity, String.class);
        LOGGER.info("执行结果{}", result);


    }

    HttpComponentsClientHttpRequestFactory createRequestFactory(int timeout) {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(10000);
        httpRequestFactory.setConnectTimeout(10000);
        if (timeout > 0)
            httpRequestFactory.setReadTimeout(timeout);
        return httpRequestFactory;
    }

    @Autowired
    private CinemasFactory cinemasFactory;

    @Autowired
    private CinemasJpa cinemasJpa;

    @Test
    public void CinemasFactoryTest() {
        List<String> list = new ArrayList<>();
//        list.add("202.96.128.86");
//        list.add("61.235.70.98");
//        list.add("202.96.128.166");
//        list.add("202.96.128.166");
//        list.add("202.96.128.166");
//        list.add("210.21.196.5");
//        list.add("202.96.128.166");
//        list.add("202.96.128.86");
//        list.add("202.96.128.86");
//        list.add("61.235.70.98");
//        list.add("202.96.128.86");
//        list.add("221.5.88.88");
//        list.add("202.96.128.86");
//        list.add("221.4.8.1");
//        list.add("61.235.70.98");
//        list.add("202.96.128.68");
//        list.add("202.96.128.86");
//        list.add("202.103.224.68");
//        list.add("61.235.164.18");
//        list.add("202.103.224.68");
//        list.add("221.7.136.68");
//        list.add("61.235.164.13");
//        list.add("202.103.224.68");
//        list.add("220.170.64.96");
//        list.add("59.51.78.210");
//        list.add("211.98.2.4");
//        list.add("202.103.96.112");
//        list.add("219.72.225.254");
//        list.add("218.76.248.6");
//        list.add("222.246.129.81");
//        list.add("220.170.0.210");
//        list.add("59.51.78.210");
//        list.add("61.232.206.100");
//        list.add("222.246.129.81");
//        list.add("218.76.192.101");
//        list.add("59.51.78.210");
//        list.add("222.246.129.81");
//        list.add("202.101.103.55");
//        list.add("218.104.128.106");
//        list.add("202.101.107.85");
//        list.add("218.85.157.99");
//        list.add("218.85.157.99");
//        list.add("218.85.157.99");
//        list.add("202.101.113.55");
//        list.add("202.101.224.69");
//        list.add("202.101.224.69");
//        list.add("211.98.2.4");
//        list.add("202.101.224.69");
//        list.add("202.99.192.66");
//        list.add("202.99.192.66");
//        list.add("219.150.32.132");
//        list.add("211.98.32.132");
//        list.add("202.99.192.66");
//        list.add("221.228.255.1");
//        list.add("218.2.135.1");
//        list.add("218.2.135.1");
//        list.add("222.45.1.40");
//        list.add("61.147.37.1");
//        list.add("61.177.7.1");
//        list.add("61.147.37");
//        list.add("211.98.2.4");
//        list.add("221.228.255.1");
//        list.add("202.96.104.15");
//        list.add("202.96.104.16");
//        list.add("202.96.104.27");
//        list.add("218.108.248.200");
//        list.add("61.153.177.199");
//        list.add("202.101.172.35");
//        list.add("61.153.177.200");
//        list.add("202.96.104.17");
//        list.add("202.100.4.15");
//        list.add("61.134.1.4");
//        list.add("222.41.52.3");
//        list.add("218.30.19.40");
//        list.add("202.102.192.6");
//        list.add("202.102.192.68");
//        list.add("202.103.44.150");
//        list.add("202.103.24.68");
//        list.add("202.104.24.68");
//        list.add("202.103.44.150");
//        list.add("202.102.154.3");
//        list.add("202.102.154.3");
//        list.add("202.102.134.68");
//        list.add("202.102.134.68");
//        list.add("202.102.154.3");
//        list.add("219.146.0.130");
//        list.add("202.102.152.3");
//        list.add("202.102.128.68");
//        list.add("202.102.152.3");
//        list.add("219.146.0.130");
//        list.add("202.102.154.3");
//        list.add("202.102.152.3");
//        list.add("202.102.152.3");
//        list.add("202.102.154.3");
//        list.add("202.102.154.3");
//        list.add("202.97.224.69");
//        list.add("219.150.32.132");
//        list.add("202.99.160.68");
//        list.add("202.99.160.68");
//        list.add("211.98.2.4");
//        list.add("202.99.160.68");
//        list.add("202.99.166.4");
//        list.add("202.99.160.68");
//        list.add("202.99.166.4");
//        list.add("219.150.32.132");
//        list.add("202.99.160.68");
//        list.add("202.98.192.67");
//        list.add("222.172.200.68");
//        list.add("211.98.72.7");
//        list.add("221.3.131.12");
//        list.add("61.232.206.102");
//        list.add("219.150.32.132");
//        list.add("202.96.69.38");
//        list.add("219.150.32.132.");
//        list.add("202.96.69.38");
// //     list.add("219.150.32.132");
        //       list.add("222.88.88.88");
//        list.add("210.76.0.2");
//        list.add("219.150.150.150");
//        list.add("202.102.224.68");
//        list.add("211.98.192.3");
//        list.add("222.88.88.88");
//        list.add("202.102.224.68");
//        list.add("202.102.224.68");
//        list.add("61.128.128.68");
//        list.add("61.139.2.69");
//        list.add("61.236.159.99");
//        list.add("202.98.96.68");
//        list.add("202.98.96.68");
//        list.add("219.148.162.31");
//        list.add("202.224.99.68");
//        list.add("202.99.104.68");
//        list.add("61.128.114.166");
//        list.add("61.128.114.166");
//        list.add("61.128.114.166");
//        list.add("202.98.5.68");
//        list.add("202.103.96.112");
//        list.add("202.100.197.50");
//        list.add("221.13.65.34");
//        list.add("202.100.64.68");
//        list.add("211.98.121.27");
//        list.add("202.100.64.68");


        list.add("43.241.51.255");//宿迁


        for (String ip : list) {
            try {
                for (CinemasJsonVo vo : cinemasFactory.loadInCinemas(ip)) {
                    LOGGER.info("保存{}", vo);
                    cinemasJpa.save(vo);
                }
            } catch (Exception e) {
                LOGGER.info("error ip:{}", ip, e);
            }
        }
    }

    @Autowired
    private HotMoviesJpa hotMoviesJpa;

    @Test
    public void moviesLoadinTest() {
        String ip = "43.241.51.255";
        try {
            for (HotMovieJsonVo vo : cinemasFactory.loadInMovies(ip)) {
                LOGGER.info("保存{}", vo);
                hotMoviesJpa.save(vo);
            }
        } catch (Exception e) {
            LOGGER.info("error ip:{}", ip, e);
        }
    }

    @Autowired
    private MovieDetailJpa movieDetailJpa;

    @Test
    public void movieDetailLoadinTest() {
        String ip = "43.241.51.255";
        try {
            for (HotMovieJsonVo hotMovieJsonVo : hotMoviesJpa.findAll()) {
                MovieDetailJsonVo movieDetailJsonVo = cinemasFactory.loadInMoviesDetail(ip,
                        String.valueOf(hotMovieJsonVo.getId()));
                LOGGER.info("保存{}", movieDetailJsonVo);
                movieDetailJpa.save(movieDetailJsonVo);
            }


        } catch (Exception e) {
            LOGGER.info("error ip:{}", ip, e);
        }
    }

    @Autowired
    CommentJpa commentJpa;

    @Test
    public void loadInComments() throws InterruptedException {
        String ip = "43.241.51.255";

        for (HotMovieJsonVo hotMovieJsonVo : hotMoviesJpa.findAll()) {
            Integer movieId = hotMovieJsonVo.getId();
            Integer limit = 14;
            Integer offset = 0;
            for (int i = 0; i < 20; i++) {
                offset = i * limit;
                Thread.sleep(1000 * 2);
                List<CommentJsonVo> commentJsonVos
                        = cinemasFactory.loadInComments(ip, movieId, limit, offset);
                for (CommentJsonVo vo : commentJsonVos) {
                    try {
                        commentJpa.save(vo);
                    } catch (Exception e) {
                        LOGGER.error("数据插入异常：{}", vo, e);
                    }

                }
                if (commentJsonVos.size() == 0) {
                    break;
                }

            }
        }


    }

}
