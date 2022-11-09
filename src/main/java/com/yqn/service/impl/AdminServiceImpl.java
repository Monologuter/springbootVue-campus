package com.yqn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqn.mapper.AdminMapper;
import com.yqn.pojo.Admin;
import com.yqn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sxc
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean save(Admin entity) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("account", entity.getAccount());

        Admin admin = adminMapper.selectOne(wrapper);
        if (admin == null) {
            adminMapper.insert(entity);
            return true;
        }
        return false;
    }
}
