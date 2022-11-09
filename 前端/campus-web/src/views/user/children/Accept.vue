<template>
    <div class="content">
        <el-alert
                :title="user.school.name + '- - -任务平台共' + tasks.length + '个任务'"
                :closable="false"
                type="info">
        </el-alert>

        <div>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>任务</span>
                    <el-button style="float: right; padding: 3px 0" type="text">最新</el-button>
                </div>
                <el-card class="box-card" v-for="item in tasks" v-if="item.state == 0">
                    <div slot="header" class="clearfix"
                         style="display: flex; align-items: center; justify-content: space-between">
                        <span>
                            <el-tag type="success" style="margin-right: 5px">待解决</el-tag>
                            {{item.taskTitle}}
                            <i class="el-icon-money" style="margin-left: 20px;color: red;"> {{item.reward}}元</i>
                        </span>
                        <el-button style="float: right; padding: 3px 0" type="text"
                                   v-show="user.id != item.publish.id" @click="acceptTask(item.id)">接受任务
                        </el-button>
                        <el-button style="float: right; padding: 3px 0" type="text"
                                   v-show="user.id == item.publish.id">本人任务
                        </el-button>
                    </div>
                    <div class="text item">
                        <p class="el-icon-s-custom">{{item.publish.username}}<span style="margin-left: 10px;">{{item.taskContext}}</span>
                        </p>
                        <span style="float: right">{{item.createTime | formatDate}}</span>
                    </div>
                </el-card>
                <div style="text-align: center" v-if="tasks.length == 0">
                    <span><i class="el-icon-refresh-right"></i>暂无任务</span>
                </div>

            </el-card>
        </div>
    </div>
</template>

<script>
    import {mapMutations, mapState} from "vuex";
    import {formatDate} from '@/util/date';

    export default {
        name: "Index",
        data() {
            return {
                tasks: [],
            }
        },

        methods: {
            acceptTask(id) {
                this.$msgbox({
                    title: '提示',
                    message: '确定接受此任务吗？',
                    showCancelButton: true,
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    beforeClose: ((action, instance, done) => {
                        if (action == 'confirm') {
                            // instance.confirmButtonLoading = true;
                            instance.confirmButtonText = '执行中...';
                            this.$put('task/takerAccept', {id: id, "acceptId": this.user.id})
                                .then((res) => {
                                    done();
                                    instance.confirmButtonLoading = false;
                                    this.$msg(res.data.msg, "success");
                                    this.$router.push('/home/accepted');
                                })
                        } else {
                            done();
                        }
                    })
                }).catch(() => {
                })
            },
        },

        computed: {
            ...mapState('user', ['user'])
        },
        created() {
            // console.log(this.user)
            this.$get("/task", {id: this.user.id})
                .then((res) => {
                    this.tasks = res.data.task
            })

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

        .card h2 {
            font-weight: normal;
            font-size: 18px;

            span {
                font-size: 12px;
                display: inline-block;
                border: 1px solid red;
                padding: 1px 3px;
            }
        }
    }


    /deep/ .el-alert--info.is-light {
        height: 50px;
    }

    /deep/ .el-select .el-input {
        width: 130px;
    }

    /deep/ .input-with-select .el-input-group__prepend {
        background-color: #fff;
    }

    /deep/ .el-card {
        margin-bottom: 20px;
    }
</style>