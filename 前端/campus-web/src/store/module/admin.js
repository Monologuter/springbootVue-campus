export default {
    namespaced:true,
    state:{
        admin:{

        }
    },
    // Mutation用于变更Store中的数据
    // 只能通过mutation变更store数据，不可以直接操作store中的数据
    mutations:{
        // 该方法用于对admin对象赋值
        // state是当前的state val是传递过来的值
        setAdmin(state,val){
            state.admin = val
        }
    }
}