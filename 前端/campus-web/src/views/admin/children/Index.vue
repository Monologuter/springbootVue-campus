<template>
    <div class="content">
        <div class="title">
            <div class="title_left">
                <h2>{{completed.length}}</h2>
                <p>已完成任务</p>
            </div>
            <div class="title_center">
                <h2>{{tasks.length}}</h2>
                <p>任务数量</p>
            </div>
            <div class="title_right">
                <h2>{{users.length}}</h2>
                <p>总用户</p>
            </div>
        </div>
        <div class="bottom">
            <el-tabs v-model="activeName" type="card">
                <el-tab-pane label="用户管理" name="first">
                    <div class="card">
                        <el-card class="box-card">
                            <div slot="header" class="clearfix">
                                <span>用户</span>
                                <el-button style="float: right; padding: 3px 0" type="text" @click="show">查看</el-button>
                            </div>
                            <div class="bottom_txt">
                                <p>新增用户<span>{{thatDay.length}}</span></p>
                                <p>老用户<span>{{oldCustomers.length}}</span></p>
                            </div>
                        </el-card>
                        <el-card class="box-card">
                            <div slot="header" class="clearfix">
                                <span>任务</span>
                            </div>
                            <div class="bottom_txt">
                                <p>今天新增任务<span>{{newTask.length}}</span></p>
                                <p>今天已完成/金额<span>{{taskCompleted.length}}/{{money}}</span></p>
                            </div>
                        </el-card>
                        <el-card class="box-card">
                            <div slot="header" class="clearfix">
                                <span>所有任务</span>
                            </div>
                            <div class="bottom_txt">
                                <p>完成任务总数<span>{{missionAccomplished.length}}</span></p>
                                <p>总数金额<span>{{allAmounts}}</span></p>
                            </div>
                        </el-card>
                    </div>
                </el-tab-pane>
            </el-tabs>
        </div>
        
        <el-dialog
                title="提示"
                :visible.sync="dialogVisible"
                width="60%">
            <el-tabs v-model="popUps" type="card">
                <el-tab-pane label="新用户" name="first">
                    <el-table
                            :data="thatDay"
                            border
                            style="width: 100%">
                        <el-table-column
                                fixed
                                prop="studentId"
                                label="学号"
                                min-width="120">
                        </el-table-column>
                        <el-table-column
                                prop="username"
                                label="姓名"
                                min-width="100">
                        </el-table-column>
                        <el-table-column
                                prop="school.name"
                                label="学校"
                                min-width="160">
                        </el-table-column>
                        <el-table-column
                                prop="createTime"
                                label="注册时间"
                                min-width="140">
                            <template slot-scope="scope">
                                {{scope.row.createTime | formatDate}}
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="sex"
                                label="性别"
                                min-width="100">
                            <template slot-scope="scope">
                                {{scope.row.sex==0 ? '男' : '女'}}
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
                <el-tab-pane label="老用户" name="second">
                    <el-table
                            :data="oldCustomers"
                            border
                            style="width: 100%">
                        <el-table-column
                                fixed
                                prop="studentId"
                                label="学号"
                                min-width="120">
                        </el-table-column>
                        <el-table-column
                                prop="username"
                                label="姓名"
                                min-width="100">
                        </el-table-column>
                        <el-table-column
                                prop="school.name"
                                label="学校"
                                min-width="160">
                        </el-table-column>
                        <el-table-column
                                prop="createTime"
                                label="注册时间"
                                min-width="140">
                            <template slot-scope="scope">
                                {{scope.row.createTime | formatDate}}
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="sex"
                                label="性别"
                                min-width="100">
                            <template slot-scope="scope">
                                {{scope.row.sex==0 ? '男' : '女'}}
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
            </el-tabs>
        </el-dialog>
    </div>
</template>

<script>
    import {formatDate} from '@/util/date';

    export default {
        name: "Index",
        data() {
            return {
                activeName: 'first',
                popUps:'first',
                tasks: [],
                users: [],
                dialogVisible: false,
            }
        },
        methods: {
            transform(time) {
                let date = new Date(time);
                return formatDate(date, 'yyyy-MM-dd hh:mm');
            },
            show() {
                this.dialogVisible = true
            },
            //用户过滤
            userFiltering(condition){
                let arr = this.users.filter((item, index) => {
                    //获取当天时间
                    let today = new Date().getTime();
                    today = this.transform(today).substring(0, 10);
                    //获取老时间
                    let createTime = this.transform(item.createTime).substring(0, 10)
                    if (condition){
                        return today == createTime
                    }else{
                        return today != createTime
                    }
                })
                return arr
            },
            //今天任务过滤
            taskFiltering(condition){
                let arr = this.tasks.filter((item,index)=>{
                    let today = new Date().getTime();
                    today = this.transform(today).substring(0, 10);
                    //获取老时间
                    let createTime = this.transform(item.createTime).substring(0, 10)
                    if (condition){
                        return today == createTime
                    }else{
                        if (today == createTime && item.state == 2){
                            return true
                        }
                    }
                })
                return arr;
            },
            //计算任务收益
            taskRevenue(parameter){
                let num = 0;
                for (let i = 0;i<parameter.length;i++){
                    num += parameter[i].reward
                }
                return num
            }
        },
        created() {
            this.$get("/task")
            .then(res => {
                this.tasks = res.data.task
            })

            this.$get("/user")
            .then((rs) => {
                this.users = rs.data.user
            })
        },
        computed: {
            completed() {
                let arr = this.tasks.filter(function (item, index) {
                    return item.state == 2
                })
                return arr
            },
            //当天
            thatDay() {
                return this.userFiltering(true)
            },
            //老用户
            oldCustomers() {
                return this.userFiltering(false)
            },
            newTask(){
                return this.taskFiltering(true)
            },
            //已完成任务
            taskCompleted(){
                return this.taskFiltering(false)
            },
            //金额计算
            money(){
                return this.taskRevenue(this.taskCompleted)
            },
            //所有完成任务
            missionAccomplished(){
                let arr = this.tasks.filter((item,index) => {
                    if (item.state == 2){
                        return true
                    }
                })
                return arr;
            },
            //所有金额
            allAmounts(){
                return this.taskRevenue(this.missionAccomplished)
            }
        },
        filters: {
            formatDate(time) {
                let date = new Date(time);
                return formatDate(date, 'yyyy-MM-dd hh:mm');
            }
        }
    }
</script>

<style scoped lang="less">
    .content {
        background: #FFf;
        margin: 0px 15px;
        height: 100%;
        padding: 15px;

        .title {
            margin: 15px 0;
            width: 100%;
            display: flex;
            height: 100px;
            justify-content: space-between;

            div {
                border-radius: 5px;
                padding: 15px;
                color: #fff;
            }

            div p {
                font-size: 12px;
                margin-top: 10px;
            }

            .title_left {
                width: 32.5%;
                background: #e64242;
            }

            .title_center {
                width: 32.5%;
                background: #11b95c;
            }

            .title_right {
                width: 32.5%;
                background: #1f2d3d;
            }
        }

        .card {
            display: flex;
            margin-bottom: 25px;
            justify-content: space-between;

            /deep/ .el-card.is-always-shadow {
                width: 32.4%;
            }
        }

        .bottom_txt p:nth-child(1) {
            margin-top: 10px;
        }

        .bottom_txt p:nth-child(2) {
            margin: 15px 0;
        }

        .bottom_txt span {
            float: right;
        }

        /deep/ .el-card__body {
            padding: 10px 20px !important;
        }
    }

    /deep/ .el-alert--info.is-light {
        height: 50px;
    }
</style>