package demo.spring.boot.film;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2018/4/13    Created by   juan
 */
public class HtmlTest {
    private Logger LOGGER  = LoggerFactory.getLogger(HtmlTest.class);
    @Test
    public void test(){
        String s = StringEscapeUtils.unescapeHtml3("&#39;&#40;&#41;");
        LOGGER.info("s:{}",s);
    }
}
