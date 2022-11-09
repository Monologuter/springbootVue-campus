package com.yqn.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author sxc
 */
@ToString
@Data
@TableName(resultMap = "schoolResultMap")
public class School {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;

    @TableField(exist = false)
    private List<Dept> depts;

}
