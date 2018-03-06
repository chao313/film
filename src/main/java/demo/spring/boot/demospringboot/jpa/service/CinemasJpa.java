package demo.spring.boot.demospringboot.jpa.service;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import demo.spring.boot.demospringboot.jpa.vo.CinemasJsonVo;

/**
 * jpa增删改查
 */
@Service
public interface CinemasJpa extends JpaRepository<CinemasJsonVo, Integer> {
//    /**
//     * 拓展的增删改查方法 查询方法以find | read | get开头
//     */
//    public List<CinemasVo> sa
//
//    public List<CinemasVo> getJpaVoByName(String name);
//
//    /**
//     * 拓展的增删改查方法,多条件
//     */
//    public List<CinemasVo> findJpaVosByNameAndId(String name, Integer id);


}
