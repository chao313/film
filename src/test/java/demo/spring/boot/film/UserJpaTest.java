package demo.spring.boot.film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import demo.spring.boot.demospringboot.Application;
import demo.spring.boot.demospringboot.data.jpa.service.UserJpa;
import demo.spring.boot.demospringboot.data.jpa.vo.UserVo;

/**
 * 2018/4/8    Created by   juan
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = Application.class)
public class UserJpaTest {

    @Autowired
    private UserJpa userJpa;

    @Test
    public void testInsert() {
        UserVo userVo = new UserVo();
        userVo.setId(UUID.randomUUID().toString());
        userVo.setNickname("阳光的");
        userJpa.save(userVo);
    }
}
