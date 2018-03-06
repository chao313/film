package demo.spring.boot.demospringboot.thrid.party.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class Http {

    public static Logger LOGGER = LoggerFactory.getLogger(Http.class);

    public static HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory;


    static {
        HttpComponentsClientHttpRequestFactory httpRequestFactory =
                new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(10000);
        httpRequestFactory.setConnectTimeout(10000);
        if (1000 > 0)
            httpRequestFactory.setReadTimeout(1000);
        Http.httpComponentsClientHttpRequestFactory = httpRequestFactory;

    }

    public ResponseEntity<String> http(String url, HttpHeaders httpHeaders, HttpMethod httpMethod) {
        RestTemplate rt = new RestTemplate(Http.httpComponentsClientHttpRequestFactory);
        HttpEntity<String> requestEntity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> result = rt.exchange(url, HttpMethod.GET, requestEntity, String.class);
        LOGGER.info("执行结果{}", result);
        return result;
    }
}
