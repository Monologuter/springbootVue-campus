package com.yqn.service.impl;

import com.yqn.mapper.SxcStudentMapper;
import com.yqn.pojo.SxcStudent;
import com.yqn.service.ISxcStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生管理Service业务层处理
 *
 * @author sxc
 * @date 2022-05-05
 */
@Service
public class SxcStudentServiceImpl implements ISxcStudentService
{
    @Resource
    private SxcStudentMapper sxcStudentMapper;

    /**
     * 查询学生管理
     *
     * @param id 学生管理主键
     * @return 学生管理
     */
    @Override
    public SxcStudent selectSxcStudentById(Long id)
    {
        return sxcStudentMapper.selectSxcStudentById(id);
    }

    /**
     * 查询学生管理列表
     *
     * @param sxcStudent 学生管理
     * @return 学生管理
     */
    @Override
    public List<SxcStudent> selectSxcStudentList(SxcStudent sxcStudent)
    {
        return sxcStudentMapper.selectSxcStudentList(sxcStudent);
//        return sxcStudentMapper.selectList(null);
    }

    /**
     * 新增学生管理
     *
     * @param sxcStudent 学生管理
     * @return 结果
     */
    @Override
    public int insertSxcStudent(SxcStudent sxcStudent)
    {
        return sxcStudentMapper.insertSxcStudent(sxcStudent);
    }

    /**
     * 修改学生管理
     *
     * @param sxcStudent 学生管理
     * @return 结果
     */
    @Override
    public int updateSxcStudent(SxcStudent sxcStudent)
    {
        return sxcStudentMapper.updateSxcStudent(sxcStudent);
    }

    /**
     * 批量删除学生管理
     *
     * @param ids 需要删除的学生管理主键
     * @return 结果
     */
    @Override
    public int deleteSxcStudentByIds(Long[] ids)
    {
        return sxcStudentMapper.deleteSxcStudentByIds(ids);
    }

    /**
     * 删除学生管理信息
     *
     * @param id 学生管理主键
     * @return 结果
     */
    @Override
    public int deleteSxcStudentById(Long id)
    {
        return sxcStudentMapper.deleteSxcStudentById(id);
    }
}
