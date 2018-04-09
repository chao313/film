package demo.spring.boot.demospringboot.thrid.party.api.vmovie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import demo.spring.boot.demospringboot.jpa.service.VMovieJpa;
import demo.spring.boot.demospringboot.jpa.vo.VMovieVo;
import demo.spring.boot.demospringboot.thrid.party.util.Http;
import demo.spring.boot.demospringboot.util.IP;

/**
 * 2018/4/6    Created by   chao
 */
@Component
public class VmovieFactory {
    private static Logger LOGGER = LoggerFactory.getLogger(VmovieFactory.class);

    @Autowired
    private Http http;

    @Autowired
    private VMovieJpa vMovieJpa;

    private Document document = null;
    private Elements elements = null;

    public List<VMovieVo> getHotVMovie(Integer page, Integer pagepart, Config.Tab tab) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.3");
        requestHeaders.add("x-forwarded-for", IP.getNextRandow());
        ResponseEntity<String> result =
                this.http.http(Config.generateUrl(tab, page, pagepart)
                        , requestHeaders, HttpMethod.GET);
        JSONObject jsonObject = JSON.parseObject(result.getBody());
        Map<String, Object> innerMap =
                jsonObject.getInnerMap();
        Object data = innerMap.get("data");
        List<VMovieVo> vMovieVos = new ArrayList<>();
        if (null != data) {
            document = Jsoup.parse(data.toString());
            if (null != document.body().getElementsByTag("li")) {
                document.body().getElementsByTag("li").stream().forEach(li -> {
                    String title = li.getElementsByClass("index-img").get(0).attr("title");
                    String href = li.getElementsByClass("index-img").get(0).attr("href");
                    String imgUrl = li.getElementsByTag("img").get(0).attr("src");
                    VMovieVo vo = new VMovieVo(title, Config.VMOVIE_BAE_URL + href, imgUrl, tab.getTabValue());
                    vMovieVos.add(vo);
                });
            }
        }
        vMovieVos.forEach(vMovieVo -> {
            vMovieVo.setVideoUrl("-2");
            vMovieJpa.save(vMovieVo);
        });
        return vMovieVos;
    }


    public void loadInHotVMovie(Config.Tab tab) {
        int maxPagePart = 3;
        int page = 1;
        List<VMovieVo> hotVMovie = null;
        do {
            for (int pagePart = 1; pagePart <=maxPagePart; pagePart++) {
                try {
                    hotVMovie = this.getHotVMovie(page, pagePart, tab);
                } catch (Exception e) {
                    LOGGER.error("异常", e.getMessage(), e);
                }

            }
            page++;
        } while (hotVMovie.size() > 0);
        //在pagePart<=3的情况下 size=0 就是结束标记
    }

    //解析iframe 的src
    public void loadInVHotMovieSrc() {
        VMovieVo vo = new VMovieVo();
        vo.setVideoUrl("-2");
        Example<VMovieVo> example = Example.of(vo);
        vMovieJpa.findAll(example).forEach(vMovieVo -> {
            try {
                String href = vMovieVo.getHref();
                String html = this.httpGetHotVMovieSrc(href);
                Document parse = Jsoup.parse(html);
                String iframeSrc = parse.getElementsByTag("iframe").get(0).attr("src");
                parse = Jsoup.parse(this.httpGetHotVMovieSrc(iframeSrc));
                String videoSrc = parse.getElementById("player").attr("href");
                vMovieVo.setVideoUrl(videoSrc);
                vMovieJpa.save(vMovieVo);
            } catch (Exception e) {
                LOGGER.error("异常:{}", vMovieVo);
            }
        });

    }

    /**
     * 根据url获取videoUrl
     */
    private String httpGetHotVMovieSrc(String url) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.3");
        requestHeaders.add("x-forwarded-for", IP.getNextRandow());
        ResponseEntity<String> result =
                this.http.http(url
                        , requestHeaders, HttpMethod.GET);
        return result.getBody();

    }

}
