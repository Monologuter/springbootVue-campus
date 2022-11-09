package com.yqn.service;

import com.yqn.pojo.SxcStudent;

import java.util.List;

/**
 * 学生管理Service接口
 *
 * @author sxc
 * @date 2022-05-05
 */
public interface ISxcStudentService
{
    /**
     * 查询学生管理
     *
     * @param id 学生管理主键
     * @return 学生管理
     */
    public SxcStudent selectSxcStudentById(Long id);

    /**
     * 查询学生管理列表
     *
     * @param sxcStudent 学生管理
     * @return 学生管理集合
     */
    public List<SxcStudent> selectSxcStudentList(SxcStudent sxcStudent);

    /**
     * 新增学生管理
     *
     * @param sxcStudent 学生管理
     * @return 结果
     */
    public int insertSxcStudent(SxcStudent sxcStudent);

    /**
     * 修改学生管理
     *
     * @param sxcStudent 学生管理
     * @return 结果
     */
    public int updateSxcStudent(SxcStudent sxcStudent);

    /**
     * 批量删除学生管理
     *
     * @param ids 需要删除的学生管理主键集合
     * @return 结果
     */
    public int deleteSxcStudentByIds(Long[] ids);

    /**
     * 删除学生管理信息
     *
     * @param id 学生管理主键
     * @return 结果
     */
    public int deleteSxcStudentById(Long id);
}
