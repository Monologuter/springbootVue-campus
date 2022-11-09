package com.yqn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqn.mapper.DeptMapper;
import com.yqn.pojo.Dept;
import com.yqn.service.DeptService;
import org.springframework.stereotype.Service;

/**
 * @author sxc
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
}
