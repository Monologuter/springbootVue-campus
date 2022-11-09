<template>
    <div class="main">
        <div class="warp">
            <h2>管理员登录</h2>
            <el-form
                    :model="ruleForm"
                    status-icon
                    :rules="rules"
                    ref="ruleForm"
                    label-width="100px"
                    class="demo-ruleForm"
                    label-position="top"
                    size="medium"
            >
                <el-form-item label="账号" prop="account" class="input">
                    <el-input type="text" v-model="ruleForm.account" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password" class="input">
                    <el-input type="password" v-model="ruleForm.password" autocomplete="off"
                              @keydown.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-checkbox v-model="checked">记住密码</el-checkbox>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')" class="btn">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    //导入md5加密方法
    export default {
        name: "Login",
        data() {
            var validateaccount = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入帐号'));
                } else {
                    callback();
                }
            };
            var validatepassword = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    callback();
                }
            };
            return {
                checked: false,
                ruleForm: {
                    account: '',
                    password: '',
                },
                rules: {
                    account: [
                        {validator: validateaccount, trigger: 'blur'}
                    ],
                    password: [
                        {validator: validatepassword, trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$get("/admin", this.ruleForm)
                        .then((res) => {
                            if (res.data.status) {
                                if (this.checked){
                                    localStorage.setItem("remember",JSON.stringify(this.ruleForm));
                                }
                                this.$msg(`${res.data.admin.username} ，登陆成功`, "success")
                                sessionStorage.setItem("admin", JSON.stringify(res.data.admin))
                                this.$router.push("/admin/home")
                            } else {
                                this.$msg(res.data.msg, "error")
                            }
                        })

                    } else {
                        // console.log('error submit!!');
                        return false;
                    }
                });
            },
        },
        created() {
            if (localStorage.getItem('remember')){
                let remember = JSON.parse(localStorage.getItem('remember'));
                this.ruleForm.account = remember.account
                this.ruleForm.password = remember.password
            }
        }
    }
</script>

<style scoped lang="less">
    .main {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;

        .warp {
            padding: 55px 85px;
            width: 560px;
            background: #fff;
            border-radius: 10px;
            position: relative;
            /*animation: move 0.75s;*/

            h2 {
                font-weight: normal;
                font-family: Raleway-Medium;
                font-size: 30px;
                color: #555555;
                margin-bottom: 20px;
            }

            /deep/ .input input {
                height: 50px;
            }

            /deep/ .btn {
                font-size: 16px;
                color: #fff;
                padding: 0 20px;
                min-width: 150px;
                height: 55px;
                background-color: #333333;
                border-radius: 27px;
                transition: all 0.4s;
            }

            /deep/ .btn:hover {
                background: #57b846;
            }
        }

        @keyframes move{
            25% {
                transform: rotate(-1deg);
            }
            75% {
                transform: rotate(2deg);
            }
        }
    }
</style>