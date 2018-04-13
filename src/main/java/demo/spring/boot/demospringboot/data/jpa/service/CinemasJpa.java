package demo.spring.boot.demospringboot.data.jpa.service;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import java.util.List;

import demo.spring.boot.demospringboot.data.jpa.vo.CinemasVo;

/**
 * jpa增删改查
 */
@Service
public interface CinemasJpa extends JpaRepository<CinemasVo, Integer> {
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

    /**
     * 根据nm查询
     */
    List<CinemasVo> findCinemasJsonVoByNmLike(String nm, Pageable pageable);

    List<CinemasVo> findCinemasVoByCityIsIn(List<String> citys);

    List<CinemasVo> findCinemasVoByCityIsInAndIdIsNotIn(List<String> citys, List<Integer> ids);




}
