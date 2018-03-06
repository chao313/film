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

import demo.spring.boot.demospringboot.jpa.vo.CinemasJsonVo;
import demo.spring.boot.demospringboot.thrid.party.util.Http;

@Component
public class CinemasFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(CinemasFactory.class);

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
}
