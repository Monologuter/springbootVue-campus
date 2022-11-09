package com.yqn.pojo;

import com.yqn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * remark对象 sxc_remark
 *
 * @author sxc
 * @date 2022-05-16
 */
public class SxcRemark extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评价id */
    private Long id;

    /** 星级 */
    private Long star;

    /** 任务id */
    private Long taskId;

    /** 送货人id */
    private Long acceptId;

    /** 评价人id */
    private Long publishId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setStar(Long star)
    {
        this.star = star;
    }

    public Long getStar()
    {
        return star;
    }
    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId()
    {
        return taskId;
    }
    public void setAcceptId(Long acceptId)
    {
        this.acceptId = acceptId;
    }

    public Long getAcceptId()
    {
        return acceptId;
    }
    public void setPublishId(Long publishId)
    {
        this.publishId = publishId;
    }

    public Long getPublishId()
    {
        return publishId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("star", getStar())
            .append("remark", getRemark())
            .append("taskId", getTaskId())
            .append("acceptId", getAcceptId())
            .append("publishId", getPublishId())
            .toString();
    }
}
