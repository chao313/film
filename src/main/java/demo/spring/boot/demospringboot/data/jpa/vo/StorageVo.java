package demo.spring.boot.demospringboot.data.jpa.vo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 2018/4/8    Created by   chao
 */
@Entity
@Table(name = "t_storage")
@Data
@ToString
@ApiModel
public class StorageVo {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @ApiModelProperty(value = "userId", required = true)
    private String userId;

    @ApiModelProperty(value = "category")
    private String category;

    private Timestamp createTime;

    private Timestamp updateTime;
}
