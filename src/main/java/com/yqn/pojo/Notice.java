package com.yqn.pojo;

import com.yqn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 通知公告公告对象 notice
 *
 * @author sxc
 * @date 2022-05-13
 */
public class Notice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公告ID */
    private Integer noticeId;

    /** 公告标题 */
    private String noticeTitle;

    /** 公告内容 */
    private String noticeContent;

    public void setNoticeId(Integer noticeId)
    {
        this.noticeId = noticeId;
    }

    public Integer getNoticeId()
    {
        return noticeId;
    }
    public void setNoticeTitle(String noticeTitle)
    {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeTitle()
    {
        return noticeTitle;
    }
    public void setNoticeContent(String noticeContent)
    {
        this.noticeContent = noticeContent;
    }

    public String getNoticeContent()
    {
        return noticeContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("noticeId", getNoticeId())
            .append("noticeTitle", getNoticeTitle())
            .append("noticeContent", getNoticeContent())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .toString();
    }
}
