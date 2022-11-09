import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import admin from "./module/admin";
import user from "./module/user";
import theme from "./module/theme";
export default new Vuex.Store({
  // state中存放的就是全局共享的数据
  // state提供唯一的公共数据源，所有共享的数据都要统一放到Store的State中进行存储
  state: {
    active:0
  },
  // Mutation用于变更Store中的数据
  // 只能通过mutation变更store数据，不可以直接操作store中的数据
  mutations: {
    setActive(state,val){
      state.active = val
    }
  },
  // Action用于处理异步任务
  // 如果通过异步操作变更数据，必须通过Action，而不能使用mutations，但是在action中还是要通过触发
  // Mutation的方式间接变更数据
  actions: {
  },
  modules: {
    admin,user,theme
  },
  //相当于
  getters:{

  }
})
