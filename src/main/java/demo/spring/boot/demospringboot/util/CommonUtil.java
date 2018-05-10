package demo.spring.boot.demospringboot.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018/4/13    Created by   juan
 */
public class CommonUtil {

    public static List<Integer> generate(Integer size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }
}
