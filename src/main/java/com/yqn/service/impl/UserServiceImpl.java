package com.yqn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqn.mapper.UserMapper;
import com.yqn.pojo.User;
import com.yqn.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author sxc
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
