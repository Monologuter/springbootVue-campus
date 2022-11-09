<template>
    <div class="main">
        <div class="left" :style="{width:isCollapse?'64px':'200px',background:themeColor.bg,color:themeColor.color}" style="transition: .3s;">
            <div class="logo">
                <img src="@s/assets/logo.jpg" style="width: 26%">
            </div>
            <el-menu
                    :collapse-transition="false"
                    :collapse="isCollapse"
                    :router="true"
                    :default-active="$route.path"
                    :background-color="themeColor.bg"
                    :text-color="themeColor.color"
                    :unique-opened="true">
                <el-menu-item index="/home/">
                    <i class="el-icon-s-home"></i>
                    <span>首页</span>
                </el-menu-item>
                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-office-building"></i>
                        <span>任务管理</span>
                    </template>
                        <el-menu-item index="/home/task">
                            <i class="el-icon-s-order"></i>
                            <span>发布任务</span>
                        </el-menu-item>
                        <el-menu-item index="/home/accept">
                            <i class="el-icon-s-order"></i>
                            <span>接受任务</span>
                        </el-menu-item>
                </el-submenu>

                <el-submenu index="4">
                    <template slot="title">
                        <i class="el-icon-paperclip"></i>
                        <span>订单管理</span>
                    </template>
                        <el-menu-item index="/home/published">
                            <i class="el-icon-s-order"></i>
                            <span>已发布任务</span>
                        </el-menu-item>
                        <el-menu-item index="/home/accepted">
                            <i class="el-icon-s-order"></i>
                            <span>已接受任务</span>
                        </el-menu-item>
                </el-submenu>

                <el-submenu index="7">
                    <template slot="title">
                        <i class="el-icon-s-custom"></i>
                        <span>公告管理</span>
                    </template>
                    <el-menu-item index="/home/noticeu">查看公告
                    </el-menu-item>
                </el-submenu>

                <el-submenu index="9">
                    <template slot="title">
                        <i class="el-icon-s-custom"></i>
                        <span>评价管理</span>
                    </template>
                    <el-menu-item index="/home/myremark">我的评价
                    </el-menu-item>
                    <el-menu-item index="/home/userremark">评价我的
                    </el-menu-item>
                </el-submenu>
                
                <el-submenu index="11">
                    <template slot="title">
                        <i class="el-icon-s-custom"></i>
                        <span>个人中心</span>
                    </template>
                    <el-menu-item index="/home/MyProfile">个人信息
                    </el-menu-item>
                </el-submenu>
            </el-menu>
        </div>

        <div class="right"
             :style="{width:isCollapse?windowWidth-64+'px':windowWidth-200+'px',left:isCollapse?'64px':'200px'}">
            <div class="top"
                 :style="{width:isCollapse?windowWidth-64+'px':windowWidth-200+'px',left:isCollapse?'64px':'200px',background:themeColor.bg}">
                <div class="icon" @click="isCollapse = !isCollapse">
                    <i :class="isCollapse?'el-icon-s-unfold':'el-icon-s-fold'" :style="{color:themeColor.color}"></i>
                </div>
                <el-menu
                        :unique-opened="true"
                        :default-active="activeIndex"
                        class="el-menu-demo"
                        mode="horizontal"
                        :background-color="themeColor.bg"
                        :text-color="themeColor.color"
                        :active-text-color="themeColor.color"
                        menu-trigger="click">

                    <el-menu-item @click="recharge(user.studentId)">充值余额</el-menu-item>
                    <el-submenu index="1">
                        <template slot="title">更换主题</template>
                        <el-menu-item v-for="item in theme" @click="changeColor(item)">
                            {{item.name}}
                        </el-menu-item>
                    </el-submenu>
                    <el-submenu index="2">
<!--                        <template slot="title">{{user.username}}</template>-->
                        <el-avatar slot="title" style="background: #65c4a6; user-select: none;">{{firstName}}</el-avatar>
                        <el-menu-item index="2-1" @click="exit">退出</el-menu-item>
                        <el-menu-item index="2-2" @click="updPassword(user.id)">修改密码</el-menu-item>
                        <el-menu-item index="2-3" @click="personalInformation()">修改个人信息</el-menu-item>
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
                    <router-view @personalInformation="personalInformation"></router-view>
                </transition>
            </div>
        </div>

        <el-drawer
                title="完善信息"
                :visible.sync="drawer"
                direction="rtl"
                closeDrawer="false"
                :show-close="false"
                :before-close="handleClose">
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
                     class="demo-ruleForm ruleform">
                <el-form-item label="班级">
                    <el-cascader
                            v-model="value"
                            :options="school.depts"
                            :props="{
                                children:'classes',
                                label:'name',
                                value:'id'
                            }"
                    ></el-cascader>
                </el-form-item>
                <el-form-item label="姓名" prop="username">
                    <el-input v-model="ruleForm.username"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="phone">
                    <el-input v-model.number="ruleForm.phone"></el-input>
                </el-form-item>

                <el-form-item label="性别">
                    <el-radio-group v-model="sex">
                        <el-radio label="0">男</el-radio>
                        <el-radio label="1">女</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                </el-form-item>
            </el-form>
        </el-drawer>

        <el-dialog title="修改信息" :visible.sync="dialogVisible" :close-on-click-modal="false">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
                <el-form-item label="姓名" prop="username">
                    <el-input v-model.number="ruleForm.username"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="phone">
                    <el-input v-model.number="ruleForm.phone" oninput="if(value.length>11)value=value.slice(0,11)"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitChanges">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import {mapState, mapMutations} from "vuex"
    import user from "@s/store/module/user";

    export default {
        name: "Home",
        methods: {
            ...mapMutations('user', ['setUser']),
            changeColor(val){
                sessionStorage.setItem("themeColor",JSON.stringify(val))
                this.themeColor = {'bg':val.value,'color':val.color};
            },
            //面包屑
            getBreadcrumb() {
                let matched = this.$route.matched;
                if (matched[0].name != 'home') {
                    matched = [{path: "/home/", meta: {title: '首页'}}].concat(matched)
                }
                this.breadList = matched;
            },
            //关闭抽屉触发的事件
            handleClose(done) {
                this.$msg("请完善资料", "error")
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (this.value) {
                            this.$put("/user",
                                {
                                    id: this.user.id,
                                    "deptId": this.value[0],
                                    "classId": this.value[1],
                                    "username": this.ruleForm.username,
                                    "phone": this.ruleForm.phone,
                                    'sex' : this.sex
                                })
                            .then(res => {
                                // console.log(res)
                                this.drawer = false;
                                this.$notifyMsg("成功", res.data.msg, "success")
                                this.newList(this.user.id)
                            })
                        } else {
                            this.$notifyMsg("错误", "请选择班级", "error")
                        }
                    } else {
                        // console.log('error submit!!');
                        return false;
                    }
                });
            },
            //修改密码
            updPassword(id) {
                this.$prompt('请输入密码', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    inputType: 'password',
                    closeOnClickModal:false,
                    inputPattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/,
                    inputErrorMessage: '格式不对，密码只能输入6-16位英文和数字'
                }).then((res) => {
                    // console.log(res);
                    this.$put('/user', {id: id, password: res.value})
                    .then((res) => {
                        this.$notifyMsg('成功', res.data.msg, 'success')
                    })
                }).catch(() => {
                })
            },
            recharge(id) {
                this.$prompt('请输入充值金额', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    inputType: 'text',
                    closeOnClickModal:false,
                    inputPattern: /^[0-9]*$/,
                    inputErrorMessage: '格式不对，只能输入数字'
                }).then((res) => {
                    // console.log(res);
                    this.$put('/user/rollIn', {studentId: id, balance: res.value})
                    .then((res) => {
                        this.newList(this.user.id)
                        this.$notifyMsg('成功', '充值成功', 'success')
                    })
                }).catch(() => {
                })
            },
            personalInformation() {
                this.dialogVisible = true;
                this.ruleForm.username = this.user.username
                this.ruleForm.phone = this.user.phone
            },
            submitChanges(){
                this.$put("/user",{id:this.user.id,username:this.ruleForm.username,phone:this.ruleForm.phone})
                .then(res=>{
                    this.$notifyMsg("成功",res.data.msg,"success",1000);
                    this.dialogVisible = false;
                    this.newList(this.user.id)
                })
            },
            //根据当前用户查询id
            newList(id) {
                this.$get("/user/" + id)
                .then((rs) => {
                    sessionStorage.setItem("user", JSON.stringify(rs.data.user))
                    this.setUser(JSON.parse(sessionStorage.getItem("user")))
                    // 修改完名字, 清空當前firstName; 避免出現疊加
                    this.firstName = '';
                    this.textAvatar(rs.data.user.username);
                })
            },
            exit(){
                sessionStorage.removeItem('user');
                this.$router.push('/')
            },
            // 文字頭像
            textAvatar(username) {
                let arr = username.split(' ');
                for (var i in arr) {
                    this.firstName += arr[i].substr(0,1);
                }
                this.firstName = this.firstName.toLocaleUpperCase();
                console.log('firstName->' + this.firstName);
            }
        },
        computed: {
            ...mapState('user', ['user']),
            theme(){
                return this.$store.state.theme.theme
            },
        },
        data() {
            var validateUsername = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入姓名'));
                } else {
                    callback();
                }
            };
            var validatePhone = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入手机号'));
                } else {
                    callback();
                }
            };

            return {
                // 文字头像
                firstName:'',
                ruleForm: {
                    username: '',
                    phone: ''
                },
                rules: {
                    username: [
                        {validator: validateUsername, trigger: 'blur'}
                    ],
                    phone: [
                        {validator: validatePhone, trigger: 'blur'}
                    ]
                },
                //颜色
                themeColor : {'bg':'#fff','color':'#000'},
                //性别
                sex:'0',
                drawer: false,
                //当前路由
                breadList: [],
                //当前屏幕宽度
                windowWidth: document.documentElement.clientWidth,
                activeIndex: '1',
                //控制菜单是否展开
                isCollapse: false,
                admin: "",
                school: [],
                //级联选择器的值
                value: "",
                dialogVisible:false
            }
        },
        watch: {
            '$route'(to, form) {
                this.getBreadcrumb()
            }
        },
        created() {
            let theme = JSON.parse(sessionStorage.getItem("themeColor"));
            if (theme){
                this.themeColor = {'bg':theme.value,'color':theme.color}
            }
            if (sessionStorage.getItem('user')){
                this.getBreadcrumb();
                this.setUser(JSON.parse(sessionStorage.getItem("user")))
                if (!this.user.dept) {
                    this.$get("/school/" + this.user.school.id)
                    .then((res) => {
                        // console.log(res.data)
                        this.school = res.data.school;
                        this.drawer = true
                        this.$msg("请完善信息", "warning")
                    })
                }
            }else {
                this.$msg("您向未登陆,没有权限","error")
                this.$router.push("/")
            }

        },
        mounted() {
            // 获取当前屏幕宽度
            window.onresize = () => {
                this.windowWidth = document.documentElement.clientWidth
            }
            // 文字頭像
            this.textAvatar(this.user.username);
        }
    }
</script>

<style scoped lang="less">
    .main {
        display: flex;
        height: 90%;

        .left {
            position: fixed;
            height: 100%;

            .logo {
                width: 90%;
                /*color: white;*/
                font-size: 16px;
                text-align: center;
                padding: 8px 0;
                /*border: 1px solid white;*/
                margin: 9.1px auto 0 auto;
            }
        }

        .right {
            transition: all 0.3s ease 0s;
            position: relative;

            .top {
                transition: all 0.3s ease 0s;
                position: fixed;
                /*color: #fff;*/
                display: flex;
                align-items: center;
                justify-content: space-between;
                z-index: 9;

                .icon {
                    font-size: 20px;
                    cursor: pointer;
                    margin-left: 10px;
                }
            }

            .bottom {
                width: 100%;
                height: 100%;
                /*background: #fff;*/
                margin-top: 65px;
                .bottom_top {
                    padding: 20px;
                }
            }
        }

        .ruleform /deep/ .el-input {
            width: 80% !important;
        }

        /deep/ .el-cascader {
            width: 100% !important;
        }
    }
</style>
