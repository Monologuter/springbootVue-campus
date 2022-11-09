package com.yqn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqn.mapper.TaskMapper;
import com.yqn.pojo.Task;
import com.yqn.service.TaskService;
import org.springframework.stereotype.Service;

/**
 * @author sxc
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
}
