//加载自己开发的插件
import {del, get, post, put} from "../util/request";
import th from "element-ui/src/locale/lang/th";

export default {
    // 插件中，必须包含一个install方法
    install: function (Vue) {
        //注入组件选项
        Vue.mixin({
            methods: {
                //get方法
                $get(url, params) {
                    return get(url, params);
                },
                //post方法
                $post(url, params) {
                    return post(url, params);
                },
                $put(url, params) {
                    return put(url, params);
                },
                $del(url, params) {
                    return del(url, params)
                },
                $msg(message, type, duration = 2000) {
                    this.$message({
                        message: message,
                        type: type,
                        duration: duration,
                        showClose: true
                    });
                },
                $notifyMsg(title, message, type, duration = 2500) {
                    this.$notify({
                        title: title,
                        message: message,
                        type: type,
                        duration: duration
                    })
                }
            }
        })
    }
}