import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: "*",
        component: () => import("@v/Error.vue")
    },

    //用户
    {
        path: "/",
        redirect: "/login"
    },

    {
        path: "/login",
        component: () => import("@v/user/Login.vue")
    },

    {
        path: "/home",
        component: () => import("@v/user/Home.vue"),
        children: [
            {
                path: "/",
                meta: {title: '首页'},
                component: () => import("@v/user/children/Index.vue")
            },
            {
                path: "task",
                meta: {title: "发布任务"},
                component:()=>import('@v/user/children/Task.vue')
            },
            {
                path: "published",
                meta: {title: "已发布任务"},
                component:()=>import('@v/user/children/Published.vue')
            },
            {
                path: "accepted",
                meta: {title: "已接收任务"},
                component:()=>import('@v/user/children/Accepted.vue')
            },
            {
                path: "accept",
                meta: {title: "已接收任务"},
                component:()=>import('@v/user/children/Accept.vue')
            },
            {
                path: "myProfile",
                meta: {title: "个人信息"},
                component:()=>import('@v/user/children/MyProfile.vue')
            },
            {
                path: "noticeu",
                meta: {title: "查看公告"},
                component:()=>import('@v/notice/adviseuser.vue')
            },
            {
                name:"myremark",
                path:"myremark",
                meta:{title:"我的评价"},
                component:() => import("@v/remark/myremark.vue")
            },
            {
                name:"userremark",
                path:"userremark",
                meta:{title:"评价我的"},
                component:() => import("@v/remark/userremark.vue")
            },
        ]
    },

    //管理员
    {
        path: "/admin",
        component: () => import("@v/admin/Login.vue"),
    },
    {
        path: "/admin/home",
        component: () => import("@v/admin/Home.vue"),
        children:[
            {
                path: "/",
                meta:{title:"首页"},
                component:()=>import("@v/admin/children/Index.vue")
            },
            {
                name: "adminSchool",
                path:"school",
                meta:{title:"学校信息"},
                component:() => import("@v/admin/children/School.vue")
            },
            {
                name:"adminStudent",
                path:"student",
                meta:{title:"用户信息"},
                component:() => import("@v/admin/children/Student.vue")
            },
            {
                name:"task",
                path:"task",
                meta:{title:"任务信息"},
                component:() => import("@v/admin/children/Task.vue")
            },
            {
                name:"notice",
                path:"notice",
                meta:{title:"公告管理"},
                component:() => import("@v/notice/advise.vue")
            },
            {
                name:"remark",
                path:"remark",
                meta:{title:"评价信息"},
                component:() => import("@v/remark/remark.vue")
            },
        ]
    },
]

const router = new VueRouter({
    routes
})

export default router
