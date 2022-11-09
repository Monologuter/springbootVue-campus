package com.yqn.service.impl;

import com.yqn.mapper.SxcRemarkMapper;
import com.yqn.pojo.SxcRemark;
import com.yqn.service.ISxcRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * remarkService业务层处理
 *
 * @author sxc
 * @date 2022-05-16
 */
@Service
public class SxcRemarkServiceImpl implements ISxcRemarkService
{
    @Autowired
    private SxcRemarkMapper sxcRemarkMapper;

    /**
     * 查询remark
     *
     * @param id remark主键
     * @return remark
     */
    @Override
    public SxcRemark selectSxcRemarkById(Long id)
    {
        return sxcRemarkMapper.selectSxcRemarkById(id);
    }

    /**
     * 查询remark列表
     *
     * @param sxcRemark remark
     * @return remark
     */
    @Override
    public List<SxcRemark> selectSxcRemarkList(SxcRemark sxcRemark)
    {
        return sxcRemarkMapper.selectSxcRemarkList(sxcRemark);
    }

    /**
     * 新增remark
     *
     * @param sxcRemark remark
     * @return 结果
     */
    @Override
    public int insertSxcRemark(SxcRemark sxcRemark)
    {
        return sxcRemarkMapper.insertSxcRemark(sxcRemark);
    }

    /**
     * 修改remark
     *
     * @param sxcRemark remark
     * @return 结果
     */
    @Override
    public int updateSxcRemark(SxcRemark sxcRemark)
    {
        return sxcRemarkMapper.updateSxcRemark(sxcRemark);
    }

    /**
     * 批量删除remark
     *
     * @param ids 需要删除的remark主键
     * @return 结果
     */
    @Override
    public int deleteSxcRemarkByIds(Long[] ids)
    {
        return sxcRemarkMapper.deleteSxcRemarkByIds(ids);
    }

    /**
     * 删除remark信息
     *
     * @param id remark主键
     * @return 结果
     */
    @Override
    public int deleteSxcRemarkById(Long id)
    {
        return sxcRemarkMapper.deleteSxcRemarkById(id);
    }
}
