package com.yqn.controller;

import com.yqn.common.tools.MessageTools;
import com.yqn.pojo.School;
import com.yqn.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author sxc
 */
@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private MessageTools message;

    // 获取全部school
    @GetMapping
    public Map<String, Object> schools() {
        List<School> schools = schoolService.list();
        return message.message(true, "请求成功", "school", schools);
    }

    // 根据id获取school
    @GetMapping("/{id}")
    public Map<String, Object> findSchoolsById(@PathVariable String id) {
        School school = schoolService.getById(id);
        return message.message(true, "请求成功", "school", school);
    }

    // 添加school
    @PostMapping
    public Map<String, Object> saveSchool(School school) {
        boolean save = schoolService.save(school);
        if (save) {
            return message.message(true, "添加学校成功", "", null);
        }
        return message.message(false, "error,学校已存在", "", null);
    }


    // 删除school
    @DeleteMapping("/{id}")
    public Map<String, Object> delSchool(@PathVariable Long id) {
        boolean remove = schoolService.removeById(id);
        if (remove) {
            return message.message(true, "删除学校成功", "", null);
        }
        return message.message(false, "error,删除学校失败", "", null);
    }

    // 更新school
    @PutMapping
    public Map<String, Object> putSchool(School school) {
        boolean update = schoolService.updateById(school);
        if (update) {
            return message.message(true, "更新学校信息成功", "", null);
        }
        return message.message(false, "error,更新学校信息失败", "", null);
    }
}
