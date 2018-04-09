package demo.spring.boot.demospringboot.jpa.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 2018/4/8    Created by   chao
 */
@Entity
@Table(name = "t_user")
@Data
@ToString
@ApiModel
public class UserVo {
    @Id
    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty(value = "openId")
    private String openid;
    @ApiModelProperty(value = "nickname", required = true)
    @NotNull
    private String nickname;
    @ApiModelProperty(value = "sex")
    private int sex;
    @ApiModelProperty(value = "city")
    private String city;
    @ApiModelProperty(value = "country")
    private String country;
    @ApiModelProperty(value = "province")
    private String province;
    @ApiModelProperty(value = "language")
    private String language;
    @ApiModelProperty(value = "avatarUrl", required = true)
    @NotNull
    private String avatarUrl;
    @ApiModelProperty(value = "unionid")
    private String unionid;
}
