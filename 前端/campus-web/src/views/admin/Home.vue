<template>
    <div class="main">
        <div class="left" :style="{width:isCollapse?'64px':'200px'}" style="transition: .3s;">
            <div class="logo">校园帮</div>

            <el-menu
                    :collapse-transition="false"
                    :collapse="isCollapse"
                    :router="true"
                    :default-active="$route.path"
                    :unique-opened="true">
                <el-menu-item index="/admin/home">
                    <i class="el-icon-s-home"></i>
                    <span>首页</span>
                </el-menu-item>

                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-office-building"></i>
                        <span>学校管理</span>
                    </template>
                    <el-menu-item index="/admin/home/school">学校信息
                    </el-menu-item>
                </el-submenu>

                <el-submenu index="4">
                    <template slot="title">
                        <i class="el-icon-s-custom"></i>
                        <span>用户管理</span>
                    </template>
                        <el-menu-item index="/admin/home/student">
                            <i class="el-icon-s-order"></i>
                            <span>用户信息</span>
                        </el-menu-item>
                </el-submenu>

                <el-submenu index="5">
                    <template slot="title">
                        <i class="el-icon-s-order"></i>
                        <span>任务管理</span>
                    </template>
                        <el-menu-item index="/admin/home/task">
                            <i class="el-icon-s-order"></i>
                            <span>任务信息</span>
                        </el-menu-item>
                </el-submenu>

                <el-submenu index="6">
                    <template slot="title">
                        <i class="el-icon-s-order"></i>
                        <span>评价管理</span>
                    </template>
                        <el-menu-item index="/admin/home/remark">
                            <i class="el-icon-s-order"></i>
                            <span>评价信息</span>
                        </el-menu-item>
                </el-submenu>

                <el-submenu index="7">
                    <template slot="title">
                        <i class="el-icon-refresh"></i>
                        <span>公告管理</span>
                    </template>
                        <el-menu-item index="/admin/home/notice">
                            <i class="el-icon-s-order"></i>
                            <span>系统公告</span>
                        </el-menu-item>
                </el-submenu>

            </el-menu>
        </div>
        <div class="right" :style="{width:isCollapse?windowWidth-64+'px':windowWidth-200+'px',left:isCollapse?'64px':'200px'}" style="transition: .3s;">
            <div class="top">
                <div class="icon" @click="isCollapse = !isCollapse">
                    <i :class="isCollapse?'el-icon-s-unfold':'el-icon-s-fold'"></i>
                </div>
                <el-menu
                        :default-active="activeIndex"
                        class="el-menu-demo"
                        mode="horizontal"
                        @select="handleSelect"
                        menu-trigger="click">
                    <el-submenu index="2">
                        <template slot="title">{{admin.account}}</template>
                        <el-menu-item index="2-1" @click="exit">退出</el-menu-item>
                    </el-submenu>
                </el-menu>
            </div>
            <div class="bottom">
                <div class="bottom_top">
                    <el-breadcrumb separator-class="el-icon-arrow-right">
                        <el-breadcrumb-item v-for="item in breadList" :to="item.path" v-if="item.meta.title">
                            {{item.meta.title}}
                        </el-breadcrumb-item>
                    </el-breadcrumb>
                </div>
                <transition name="el-fade-in" mode="out-in">
                    <router-view></router-view>
                </transition>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapState, mapMutations} from "vuex"

    export default {
        name: "Home",
        methods: {
            ...mapMutations('admin',['setAdmin']),
            handleSelect(key, keyPath) {
                // console.log(key, keyPath);
            },
            getBreadcrumb(){
                let matched = this.$route.matched;
                if(matched[0].name != 'home'){
                    matched = [{path:"/admin/home",meta:{title:'首页'}}].concat(matched)
                }
                // if (!this.isHome(matched[0])){
                //     matched = [{path:"/home",meta:{title:'首页'}}].concat(matched);
                // }
                this.breadList = matched;
            },
            exit(){
                sessionStorage.removeItem('admin');
                this.$router.push("/admin")
            }
        },
        computed: {
            ...mapState('admin',['admin'])
        },
        data() {
            return {
                //当前路由
                breadList:[],
                //当前屏幕宽度
                windowWidth: document.documentElement.clientWidth,
                activeIndex: '1',
                //控制菜单是否展开
                isCollapse: false,
            }
        },
        watch: {
            '$route'(to, form) {
                this.getBreadcrumb();
            }
        },
        created() {
            // console.log(sessionStorage.getItem('admin'))
            if (sessionStorage.getItem("admin")){
                this.setAdmin(JSON.parse(sessionStorage.getItem("admin")));
                this.getBreadcrumb();
                /*let flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
                console.log(flag)
                if (flag) {
                    this.$router.push("m/login")
                }*/
            }else {
                this.$msg("向未登陆","error")
                this.$router.push("/admin/")
            }

        },
        mounted() {
            window.onresize = () => {
                this.windowWidth = document.documentElement.clientWidth
            }
        },
    }

</script>

<style scoped lang="less">
    .main {
        display: flex;
        height: 100%;

        .left {
            background: #fff;
            position: fixed;
            height: 100%;
            .logo {
                width: 90%;
                /*color: white;*/
                font-size: 16px;
                text-align: center;
                padding: 8px 0;
                /*border: 1px solid white;*/
                margin: 9.1px auto;
            }
        }

        .right {
            position: relative;
            .top {
                /*color: #fff;*/
                display: flex;
                align-items: center;
                justify-content: space-between;
                background: #fff;

                .icon {
                    font-size: 20px;
                    cursor: pointer;
                    margin-left: 10px;
                }
            }

            .bottom {
                width: 100%;
                /*height: 100%;*/
                /*background: #fff;*/

                .bottom_top {
                    padding: 20px;
                }
            }
        }
    }
</style>