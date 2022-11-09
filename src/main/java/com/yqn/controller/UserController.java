package com.yqn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqn.pojo.User;
import com.yqn.service.UserService;
import com.yqn.common.tools.MessageTools;
import com.yqn.common.tools.PocketMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author sxc
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageTools message;
    @Autowired
    private PocketMoney money;

    // 检查登录
    @GetMapping("/login")
    public Map<String, Object> checkUserLogin(User checkUser, HttpSession session) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("student_id", checkUser.getStudentId())
                .eq("password", checkUser.getPassword())
                .eq("school_id", checkUser.getSchoolId());
        User user = userService.getOne(wrapper);

        if (user != null) {
            session.setAttribute("user", user);
            return message.message(true, "请求成功", "user", user);
        }
        return message.message(false, "账号或密码错误", "", null);
    }

    // 获取全部User
    @GetMapping
    public Map<String, Object> users(String studentId, String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (studentId != null) {
            wrapper.eq("student_id", studentId);
            return message.message(true, "请求成功", "user", userService.list(wrapper));
        }
        if (username != null) {
            wrapper.like("username", username);
            return message.message(true, "请求成功", "user", userService.list(wrapper));
        }
        List<User> users = userService.list();
        return message.message(true, "请求成功", "user", users);
    }

    // 根据id获取User
    @GetMapping("/{id}")
    public Map<String, Object> user(@PathVariable String id) {
        User user = userService.getById(id);
        return message.message(true, "请求成功", "user", user);
    }

    // 添加User
    @PostMapping
    public Map<String, Object> saveUser( User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("school_id", user.getSchoolId())
                .eq("student_id", user.getStudentId());
        User one = userService.getOne(wrapper);
        if (one == null) {
            userService.save(user);
            return message.message(true, "注册成功", "", null);
        }
        return message.message(false, "error, 该学号已被注册", "", null);
    }

    // 更新信息
    @PutMapping
    public Map<String, Object> putUser(User user) {
        boolean update = userService.updateById(user);
        if (update) {
            return message.message(true, "更新信息成功", "", null);
        }
        return message.message(false, "error, 更新信息失败", "", null);
    }

    // 删除学生
    @DeleteMapping("/{id}")
    public Map<String, Object> delUser(@PathVariable Long id) {
        boolean remove = userService.removeById(id);
        if (remove) {
            return message.message(true, "删除学生成功", "", null);
        }
        return message.message(false, "error,删除学生失败", "", null);
    }

    // 零钱转入
    @PutMapping("rollIn")
    public Map<String, Object> rollIn(String studentId, Double balance) {
        return money.transfer("balance=balance+", balance, studentId);
    }

    // 零钱转出
    @PutMapping("rollOut")
    public Map<String, Object> rollOut(String studentId, Double balance) {
        return money.transfer("balance=balance-", balance, studentId);
    }
}
