package com.yqn.service.impl;

import com.yqn.pojo.Notice;
import com.yqn.common.utils.DateUtils;
import com.yqn.mapper.NoticeMapper;
import com.yqn.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通知公告公告Service业务层处理
 *
 * @author sxc
 * @date 2022-05-13
 */
@Service
public class NoticeServiceImpl implements INoticeService
{
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 查询通知公告公告
     *
     * @param noticeId 通知公告公告主键
     * @return 通知公告公告
     */
    @Override
    public Notice selectNoticeByNoticeId(Integer noticeId)
    {
        return noticeMapper.selectNoticeByNoticeId(noticeId);
    }

    /**
     * 查询通知公告公告列表
     *
     * @param notice 通知公告公告
     * @return 通知公告公告
     */
    @Override
    public List<Notice> selectNoticeList(Notice notice)
    {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增通知公告公告
     *
     * @param notice 通知公告公告
     * @return 结果
     */
    @Override
    public int insertNotice(Notice notice)
    {
        notice.setCreateTime(DateUtils.getNowDate());
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改通知公告公告
     *
     * @param notice 通知公告公告
     * @return 结果
     */
    @Override
    public int updateNotice(Notice notice)
    {
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 批量删除通知公告公告
     *
     * @param noticeIds 需要删除的通知公告公告主键
     * @return 结果
     */
    @Override
    public int deleteNoticeByNoticeIds(Integer[] noticeIds)
    {
        return noticeMapper.deleteNoticeByNoticeIds(noticeIds);
    }

    /**
     * 删除通知公告公告信息
     *
     * @param noticeId 通知公告公告主键
     * @return 结果
     */
    @Override
    public int deleteNoticeByNoticeId(Integer noticeId)
    {
        return noticeMapper.deleteNoticeByNoticeId(noticeId);
    }
}
