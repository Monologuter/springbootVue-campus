<template>
    <div class="content">
        <!-- <div class="center">
            <el-input placeholder="请输入内容" v-model="input" class="input-with-select" @keydown.enter.native="clickSearch">
                <el-select v-model="select" slot="prepend" placeholder="请选择" value="1" @change="inquiry">
                    <el-option value="entire" label="全部"></el-option>
                    <el-option :value="item.id" v-for="item in schools" :label="item.name"></el-option>
                </el-select>
                <el-button slot="append" icon="el-icon-search" @click="clickSearch"></el-button>
            </el-input>
        </div> -->
        <div class="bottom">
            <el-table
                    :data="tasks"
                    :resizable="false"
                    style="width: 100%  ">
                <el-table-column
                        prop="publish.username"
                        label="发布人"
                        min-width="140">
                </el-table-column>
                <el-table-column
                        label="接受人"
                        min-width="140">
                    <template slot-scope="scope">
                        {{scope.row.accept ? scope.row.accept.username : '暂无服务'}}
                    </template>
                </el-table-column>
                <el-table-column
                        prop="reward"
                        label="任务奖励"
                        min-width="110">
                </el-table-column>
                <el-table-column
                        prop="school.name"
                        label="任务所在学校"
                        min-width="200">
                </el-table-column>
                <el-table-column
                        prop="taskTitle"
                        label="标题"
                        min-width="180">
                </el-table-column>
                <el-table-column
                        label="发布时间"
                        min-width="160">
                    <template slot-scope="scope">
                        {{scope.row.createTime ? transform(scope.row.createTime) : '暂无时间'}}
                    </template>
                </el-table-column>
                <el-table-column
                        label="服务时间"
                        min-width="160">
                    <template slot-scope="scope">
                        {{scope.row.orderTime ? transform(scope.row.orderTime) : '暂无时间'}}
                    </template>
                </el-table-column>
                <el-table-column
                        prop="balance"
                        label="完成时间"
                        min-width="160">
                    <template slot-scope="scope">
                        {{scope.row.endTime ? transform(scope.row.endTime) : '暂无时间'}}
                    </template>
                </el-table-column>
                <el-table-column
                        label="任务状态"
                        min-width="90">
                    <template slot-scope="scope">
                        {{scope.row.state == 0?'未处理':scope.row.state == 1 ? '处理中' : '已完成'}}
                    </template>
                </el-table-column>
                <el-table-column
                        label="操作"
                        width="100">
                    <template slot-scope="scope">
                        <el-popconfirm
                                confirm-button-text='好的'
                                cancel-button-text='不用了'
                                icon="el-icon-info"
                                icon-color="red"
                                title="确定删除该任务吗？"
                                @confirm="del(scope.row.id,scope.row.state)"
                        >
                            <el-button type="text" size="small" slot="reference"><i class="el-icon-delete" style="color: red"></i></el-button>
                        </el-popconfirm>
                    </template>
                </el-table-column>
            </el-table>

        </div>
    </div>
</template>

<script>
    import {formatDate} from '@/util/date';
    export default {
        name: "Task",
        methods: {
            inquiry(){
                if (this.select == 'entire'){
                    this.newList();
                }else {
                    this.$post("/task/api/findTaskBySchoolId",{"id":this.select})
                    .then(res => {
                        this.tasks = res.data.tasks
                    })
                }
            },
            clickSearch() {
                if(this.input == '已完成'){

                }
            },

            del(id,state) {
                if (state == 0){
                    this.$del("/task/"+id)
                    .then((res) => {
                        this.$notifyMsg("成功", res.data.msg, "success")
                        this.newList()
                    })
                }else {
                    this.$msg("该任务已被接收或已完成","error")
                }
            },

            newList() {
                this.$get("/task")
                .then((rs) => {
                    this.tasks = rs.data.task
                    // console.log(this.tasks)
                })
            },
            transform(time) {
                let date = new Date(time);
                return formatDate(date, 'yyyy-MM-dd hh:mm');
            }
        },
        data() {
            return {
                //学校
                schools:[],
                tasks: [],
                ruleForm: {
                    id: 0,
                    state: true
                },
                options: [
                    {label: "正常", value: "0"},
                    {label: "禁用", value: "1"}

                ],
                input:"",
                select: 'entire'
            }
        },
        created() {
            this.newList();
            this.$get("school")
            .then(res => {
                this.schools = res.data.schools
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
        padding: 0 1%;

    }

    .center {
        width: 80%;
        margin-bottom: 30px;
    }

    /deep/ .el-select .el-input {
        width: 200px;
    }

    /deep/ .input-with-select .el-input-group__prepend {
        background-color: #fff;
    }



    .form {
        margin: 0 22px;
    }
</style>