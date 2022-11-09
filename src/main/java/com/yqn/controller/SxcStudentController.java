package com.yqn.controller;

import com.yqn.common.core.controller.BaseController;
import com.yqn.common.core.domain.AjaxResult;
import com.yqn.common.core.page.TableDataInfo;
import com.yqn.common.tools.MessageTools;
import com.yqn.pojo.SxcStudent;
import com.yqn.service.ISxcStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 学生管理Controller
 *
 * @author sxc
 * @date 2022-05-05
 */
@RestController
@RequestMapping("/student/stuage")
public class SxcStudentController extends BaseController
{
    @Autowired
    private ISxcStudentService sxcStudentService;

    @Autowired
    private MessageTools message;

    /**
     * 查询学生管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SxcStudent sxcStudent)
    {
        startPage();
        List<SxcStudent> list = sxcStudentService.selectSxcStudentList(sxcStudent);
        return getDataTable(list);
    }

    /**
     * 获取学生管理详细信息
     */
//    @GetMapping("/{id}")
//    public AjaxResult getInfo(@PathVariable("id") Long id)
//    {
//        return AjaxResult.success(sxcStudentService.selectSxcStudentById(id));
//    }

    @GetMapping("/{id}")
    public Map<String, Object> findSxcStudentById(@PathVariable Long id) {
        SxcStudent student = sxcStudentService.selectSxcStudentById(id);
        return message.message(true, "请求成功", "student", student);
    }

    /**
     * 新增学生管理
     */
    @PostMapping
    public AjaxResult add(@RequestBody SxcStudent sxcStudent)
    {
        return toAjax(sxcStudentService.insertSxcStudent(sxcStudent));
    }

    /**
     * 修改学生管理
     */
    @PutMapping
    public AjaxResult edit(@RequestBody SxcStudent sxcStudent)
    {
        return toAjax(sxcStudentService.updateSxcStudent(sxcStudent));
    }

    /**
     * 删除学生管理
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sxcStudentService.deleteSxcStudentByIds(ids));
    }
}
