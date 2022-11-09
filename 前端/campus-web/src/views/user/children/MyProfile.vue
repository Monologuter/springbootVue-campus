<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>基本信息</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="$emit('personalInformation')">修改信息</el-button>
            </div>
            <div class="content_txt">
                <el-collapse v-model="activeNames">
                    <el-collapse-item title="学号" name="1">
                        <div>{{user.studentId}}</div>
                    </el-collapse-item>
                    <el-collapse-item title="姓名" name="2">
                        <div>{{user.username}}</div>
                    </el-collapse-item>
                    <el-collapse-item title="手机" name="3">
                        <div>{{user.phone}}</div>
                    </el-collapse-item>
                    <el-collapse-item title="学校" name="4">
                        <div>{{user.school.name}}</div>
                    </el-collapse-item>
                    <el-collapse-item title="系别" name="5">
                        <div>{{user.dept.name}}</div>
                    </el-collapse-item>
                    <el-collapse-item title="班级" name="6">
                        <div>{{user.aclass.name}}</div>
                    </el-collapse-item>
                    <el-collapse-item title="零钱" name="7">
                        <div><i class="el-icon-money" style="color: red;"> {{user.balance}}元</i></div>
                    </el-collapse-item>
                </el-collapse>
            </div>
        </el-card>

    </div>
</template>

<script>
    import {mapState} from 'vuex'
    export default {
        name: "MyProfile",
        data() {
            return {
                activeNames: ['1', '2', '3', '4', '5', '6', '7'],
            }
        },
        computed:{
          ...mapState('user',['user'])
        },
        methods: {
        },
        created() {
            // console.log(this.user)
        }
    }
</script>

<style scoped lang="less">
    /deep/ .box-card {
        width: 60%;
        margin: 0 auto;
    }

    /deep/ .el-card__body {
        padding: 0 20px !important;
    }

    /deep/ .el-collapse {
        border-top: none !important;
    }

    /deep/ .el-collapse-item__content {
        padding-bottom: 15px !important;
    }
</style>
