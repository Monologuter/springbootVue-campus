<template>
    <div class="content">
        <el-alert
                title="发布新任务"
                :closable="false"
                type="info">
        </el-alert>
        <el-card class="box-card card">
            <div slot="header" class="clearfix">
                <span>发布任务</span>
                <el-button style="float: right; padding: 3px 0;font-size: 16px" icon="el-icon-s-promotion" type="text"
                           @click="submitTask">发布
                </el-button>
            </div>
            <div class="input">
                <div class="append">任务标题</div>
                <el-input placeholder="请输入任务标题" v-model="taskTitle"></el-input>
            </div>
            <div class="input">
                <div class="append">奖励</div>
                <el-input placeholder="请输入奖励" v-model="reward" oninput="value=value.replace(/[^\d]/g,'')"></el-input>
                <div class="append" style="border-right: 1px solid #DCDFE6;border-left: none;">余额：{{user.balance}}元
                </div>
            </div>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>详细描述</span>
                </div>
                <el-input
                        resize="none"
                        type="textarea"
                        :autosize="{ minRows: 6, maxRows: 10}"
                        placeholder="请输入内容"
                        v-model="taskContext" style="padding: 0">
                </el-input>
            </el-card>
        </el-card>
        <el-drawer
                title="任务信息"
                :visible.sync="drawer"
                direction="rtl">
            <div class="content_drawer">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>发布内容</span>
                    </div>
                    <el-collapse v-model="activeNames">
                        <el-collapse-item title="任务标题" name="1">
                            <div>{{taskTitle}}</div>
                        </el-collapse-item>
                        <el-collapse-item title="任务内容" name="2">
                            <div>{{taskContext}}</div>
                        </el-collapse-item>
                        <el-collapse-item title="任务奖励" name="3">
                            <div><i class="el-icon-money" style="color: red;"> {{reward}}元</i></div>
                        </el-collapse-item>
                        <el-collapse-item title="发布时间" name="4">
                            <div>{{createTime | formatDate}}</div>
                        </el-collapse-item>
                    </el-collapse>
                </el-card>
            </div>
        </el-drawer>
    </div>
</template>

<script>
    import {mapMutations, mapState} from "vuex";
    import {formatDate} from '@/util/date';

    export default {
        name: "Task",
        data() {
            return {
                // 任务奖励
                reward: "",
                // 任务标题
                taskTitle: "",
                // 任务内容
                taskContext: "",
                // 发布时间
                createTime: "",
                // 零钱
                balance: 0,
                // 是否弹出抽屉
                drawer: false,
                activeNames: ['1', '2', '3', '4']

            }
        },
        computed: {
            ...mapState("user", ['user'])
        },
        methods: {
            ...mapMutations('user', ['setUser']),
            
            submitTask() {
                if (this.taskTitle && this.reward > 0 && this.taskContext) {
                    // console.log(this.user)
                    this.$post("/task",
                        {
                            "publishId": this.user.id,
                            "schoolId": this.user.school.id,
                            "reward": this.reward,
                            "taskTitle": this.taskTitle,
                            "taskContext": this.taskContext
                        }
                    ).then(res => {
                        if (res.data.status) {
                            this.createTime = new Date().getTime()
                            this.drawer = true;
                            this.renew()
                            this.$msg(res.data.msg, "success")
                        } else {
                            this.$msg(res.data.msg, "error")
                        }
                    })
                } else {
                    this.$msg("请正确填写信息", "error")
                }
            },
            renew() {
                this.$get("user/" + this.user.id)
                    .then(response => {
                        sessionStorage.setItem('user', JSON.stringify(response.data.user))
                        this.setUser(JSON.parse(sessionStorage.getItem('user')))
                    })
            }
        },
        created() {
            this.renew()
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
        margin: 0 15px;
        padding: 15px;

        .card {
            margin-top: 20px;

            .input {
                margin-top: 10px;

                width: 100%;
                height: 40px;
                display: flex;
                justify-content: space-between;
                align-items: center;

                .append {
                    border: 1px solid #DCDFE6;
                    border-right: none;
                    width: 150px;
                    font-size: 14px;
                    line-height: 40px;
                    height: 100%;
                    text-align: center;
                }

                .select {
                    flex: 1;
                }

                /deep/ .el-input {
                    flex: 1;
                }


            }

            .box-card {

                /deep/ .el-textarea__inner {
                    font-family: '微软雅黑' !important;
                }

                margin-top: 10px;

                /deep/ .el-card__header {
                    border-bottom: none;
                }

                /deep/ .el-card__body {
                    padding: 0 !important;
                }
            }
        }

        .content_drawer {
            padding: 0 20px;

            p {
                margin: 10px 0;
            }

            span {
                font-size: 14px;
            }
        }

    }
</style>