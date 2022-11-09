package com.yqn.controller;

import com.yqn.common.core.controller.BaseController;
import com.yqn.common.core.domain.AjaxResult;
import com.yqn.common.core.page.TableDataInfo;
import com.yqn.pojo.Notice;
import com.yqn.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * 通知公告公告Controller
 *
 * @author sxc
 * @date 2022-05-13
 */
@RestController
@RequestMapping("/notice/advise")
public class NoticeController extends BaseController
{
    @Autowired
    private INoticeService noticeService;

    /**
     * 查询通知公告公告列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Notice notice)
    {
        startPage();
        List<Notice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }


    /**
     * 获取通知公告公告详细信息
     */
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable("noticeId") Integer noticeId)
    {
        return AjaxResult.success(noticeService.selectNoticeByNoticeId(noticeId));
    }

    /**
     * 新增通知公告公告
     */
    @PostMapping
    public AjaxResult add(@RequestBody Notice notice)
    {

        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告公告
     */
    @PutMapping
    public AjaxResult edit(@RequestBody Notice notice)
    {
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告公告
     */
	@DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Integer[] noticeIds)
    {
        return toAjax(noticeService.deleteNoticeByNoticeIds(noticeIds));
    }
}
