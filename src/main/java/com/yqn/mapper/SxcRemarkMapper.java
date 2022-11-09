package com.yqn.mapper;

import com.yqn.pojo.SxcRemark;
import java.util.List;

/**
 * remarkMapper接口
 *
 * @author sxc
 * @date 2022-05-16
 */
public interface SxcRemarkMapper
{
    /**
     * 查询remark
     *
     * @param id remark主键
     * @return remark
     */
    public SxcRemark selectSxcRemarkById(Long id);

    /**
     * 查询remark列表
     *
     * @param sxcRemark remark
     * @return remark集合
     */
    public List<SxcRemark> selectSxcRemarkList(SxcRemark sxcRemark);

    /**
     * 新增remark
     *
     * @param sxcRemark remark
     * @return 结果
     */
    public int insertSxcRemark(SxcRemark sxcRemark);

    /**
     * 修改remark
     *
     * @param sxcRemark remark
     * @return 结果
     */
    public int updateSxcRemark(SxcRemark sxcRemark);

    /**
     * 删除remark
     *
     * @param id remark主键
     * @return 结果
     */
    public int deleteSxcRemarkById(Long id);

    /**
     * 批量删除remark
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSxcRemarkByIds(Long[] ids);
}
