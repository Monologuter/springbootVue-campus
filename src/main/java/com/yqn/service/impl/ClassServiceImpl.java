package com.yqn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqn.mapper.ClassMapper;
import com.yqn.pojo.Class;
import com.yqn.service.ClassService;
import org.springframework.stereotype.Service;

/**
 * @author sxc
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {
}
