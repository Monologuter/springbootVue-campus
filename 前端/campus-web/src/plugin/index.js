// 注册element-ui组件库
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Vant from 'vant'
import 'vant/lib/index.css'
Vue.use(ElementUI);
Vue.use(Vant)

// 注册myPlugin插件
import myPlugin from "./myPlugin";

Vue.use(myPlugin)