package com.yqn.common.tools;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sxc
 */
// @Configuration
@Component
public class MessageTools {
    // 返回json信息
    /*public Map<String, Object> message(boolean status, Object o) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("msg", o);
        return map;
    }*/

    public Map<String, Object> message(boolean status, String msg, String oMsg, Object o) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("msg", msg);
        if (!msg.equals("") && o != null) {
            map.put(oMsg, o);
        }
        return map;
    }
}
