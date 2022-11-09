package com.yqn.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 学生管理对象 sxc_student
 *
 * @author sxc
 * @date 2022-05-05
 */
public class SxcStudent implements Serializable
{
//    private static final long serialVersionUID = 1L;

    /** 学生编号 */
    private Long id;

    /** 姓名 */
    private String name;

    /** 年龄 */
    private Long age;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setAge(Long age)
    {
        this.age = age;
    }

    public Long getAge()
    {
        return age;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("age", getAge())
            .toString();
    }
}
