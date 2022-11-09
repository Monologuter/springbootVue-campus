package com.yqn.common.tools;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yqn.pojo.User;
import com.yqn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author sxc
 */
@Component
public class PocketMoney {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageTools tools;

    // 零钱通用方法
    public Map<String, Object> transfer(String condition, Double balance, String studentId) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.setSql(condition + balance)
                .eq("student_id", studentId);
        boolean update = userService.update(wrapper);
        if (update) {
            return tools.message(true, "success", "", null);
        }
        return tools.message(false, "error", "", null);
    }
}
