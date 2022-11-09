<template>
    <div class="content">

        <div class="center">
            <el-input placeholder="请输入内容" 
                      v-model="input" 
                      class="input-with-select" 
                      @keydown.enter.native="clickSearch">
                <el-select v-model="select" slot="prepend" placeholder="请选择" value="1">
                    <el-option label="学号" value="1"></el-option>
                    <el-option label="姓名" value="2"></el-option>
                </el-select>
                <el-button slot="append" icon="el-icon-search" @click="clickSearch"></el-button>
            </el-input>
        </div>

        <div class="bottom">
            <el-table
                    :data="users"
                    :resizable="false"
                    style="width: 100%  ">
                <el-table-column
                        prop="studentId"
                        label="学号"
                        min-width="140">
                </el-table-column>
                <el-table-column
                        prop="username"
                        label="姓名"
                        min-width="120">
                </el-table-column>
                <el-table-column
                        prop="phone"
                        label="手机号"
                        min-width="110">
                </el-table-column>

                <el-table-column
                        label="性别"
                        min-width="60">
                    <template slot-scope="scope">
                        {{scope.row.sex==0?'男':'女'}}
                    </template>
                </el-table-column>

                <el-table-column
                        prop="school.name"
                        label="学校"
                        min-width="200">
                </el-table-column>
                <el-table-column
                        prop="dept.name"
                        label="所在系"
                        min-width="100">
                </el-table-column>
                <el-table-column
                        prop="aclass.name"
                        label="班级"
                        min-width="150">
                </el-table-column>
                <el-table-column
                        prop="balance"
                        label="余额"
                        min-width="50">
                </el-table-column>
                <el-table-column
                        label="禁用用户"
                        min-width="90">
                    <template slot-scope="scope">
                        <el-switch
                                :value="scope.row.state ==1"
                                active-color="#13ce66"
                                inactive-color="#ff4949"
                        @change="edit(scope.row)">
                        </el-switch>
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
                                title="确定删除该学生吗？"
                                @confirm="del(scope.row.id)"
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
    export default {
        name: "Student",
        methods: {
            clickSearch() {
                let parameter = {};
                if (this.input) {
                    if (this.select == 1) {
                        parameter = {"studentId": this.input};
                    } else {
                        parameter = {"username": this.input};
                    }
                    this.$get("/user", parameter)
                    .then((res) => {
                        this.users = res.data.user
                        this.input = ""
                    })
                } else {
                    this.newList()
                }
            },
            del(id) {
                console.log(id);
                this.$del("/user/"+id)
                .then((res) => {
                    this.$notifyMsg("成功", res.data.msg, "success")
                    this.newList()
                })
            },
            
            edit(row) {
                // console.log(row.state)
                if (row.state == 0){
                    row.state = 1;
                }else {
                    row.state = 0;
                }
                this.$put("/user", {id:row.id,state: row.state})
                .then((res) => {
                    // this.$msg(res.data.msg, "success")
                    this.$notifyMsg("成功", res.data.msg, "success")
                    this.newList()
                })
                .catch((err) => {
                    this.$notifyMsg("失败", res.data.msg, "error")
                })
            },
            newList() {
                this.$get("/user")
                .then((rs) => {
                    this.users = rs.data.user
                    // console.log(this.users)
                })
            },
        },
        data() {
            return {
                users: [],
                input: '',
                select: '1'
            }
        },
        created() {
            this.newList()
        }
    }
</script>

<style scoped lang="less">
    .content {
        padding: 0 1%;

    }

    .center {
        width: 70%;
        margin-bottom: 30px;
    }

    /deep/ .el-select .el-input {
        width: 80px;
    }

    /deep/ .input-with-select .el-input-group__prepend {
        background-color: #fff;
    }



    .form {
        margin: 0 22px;
    }
</style>
