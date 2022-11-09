package com.yqn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqn.pojo.Admin;
import com.yqn.service.AdminService;
import com.yqn.common.tools.MessageTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author sxc
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private MessageTools message;

    @GetMapping
    public Map<String, Object> checkAdminLogin(String account, String password, HttpSession session) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("account", account);
        wrapper.eq("password", password);

        Admin admin = adminService.getOne(wrapper);
        if (admin != null) {
            session.setAttribute("admin", admin);
            // return message.message(true, admin);
            return message.message(true, "请求成功", "admin", admin);
        }

        // return message.message(false, "账号或密码错误");
        return message.message(false, "账号或密码错误", "", null);
    }

    @PostMapping
    public Map<String, Object> saveAdmin(Admin admin) {
        boolean result = adminService.save(admin);
        if (result) {
            return message.message(true, "Admin,添加成功", "", null);
        }
        return message.message(false, "Admin已存在,添加失败", "", null);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delAdmin(@PathVariable Long id) {
        boolean b = adminService.removeById(id);
        if (b) {
            return message.message(true, "Admin,删除成功", "", null);
        }
        return message.message(false, "Admin,删除失败", "", null);
    }

    @PutMapping
    public Map<String, Object> putadmin(Admin admin) {
        boolean b = adminService.updateById(admin);
        if (b) {
            return message.message(true, "Admin,更新信息成功", "", null);
        }
        return message.message(false, "Admin,更新信息失败", "", null);
    }
}
