package com.yqn.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author sxc
 */
@Data
@ToString
public class Admin {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String account;
    private String password;
    private String username;
    private Date createTime;
    private Integer state;

}
