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
@TableName(resultMap = "deptResultMap")
public class Dept {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer schoolId;

    @TableField(exist = false)
    private List<Class> classes;
}
