export default {
    namespaced:true,
    state:{
        user:{
        }
    },
    mutations:{
        // 该方法用于对admin对象赋值
        setUser(state,val){
            state.user = val
        }
    }
}