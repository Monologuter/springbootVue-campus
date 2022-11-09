package com.yqn.controller;

import com.yqn.common.core.controller.BaseController;
import com.yqn.common.core.domain.AjaxResult;
import com.yqn.common.core.page.TableDataInfo;
import com.yqn.pojo.SxcRemark;
import com.yqn.service.ISxcRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * remarkController
 *
 * @author sxc
 * @date 2022-05-16
 */
@RestController
@RequestMapping("/student/remark")
public class SxcRemarkController extends BaseController
{
    @Autowired
    private ISxcRemarkService sxcRemarkService;

    /**
     * 查询remark列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SxcRemark sxcRemark)
    {
        startPage();
        List<SxcRemark> list = sxcRemarkService.selectSxcRemarkList(sxcRemark);
        return getDataTable(list);
    }

    /**
     * 获取remark详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sxcRemarkService.selectSxcRemarkById(id));
    }

    /**
     * 新增remark
     */
    @PostMapping
    public AjaxResult add(@RequestBody SxcRemark sxcRemark)
    {
        return toAjax(sxcRemarkService.insertSxcRemark(sxcRemark));
    }

    /**
     * 修改remark
     */
    @PutMapping
    public AjaxResult edit(@RequestBody SxcRemark sxcRemark)
    {
        return toAjax(sxcRemarkService.updateSxcRemark(sxcRemark));
    }

    /**
     * 删除remark
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sxcRemarkService.deleteSxcRemarkByIds(ids));
    }
}
