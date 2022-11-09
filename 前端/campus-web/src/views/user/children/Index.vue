<template>
    <div class="content">
        <el-row>
        <el-col :span="24"><div class="grid-content bg-purple-dark">欢迎登录---校园跑腿管理系统</div></el-col>
        </el-row>
        <el-alert
                :title="user.school.name + '- - -任务平台共' + tasks.length + '个任务'"
                :closable="false"
                type="info">
        </el-alert>
        <div class="title">
            <div class="title_center">
                <h2>{{tasks.length}}</h2>
                <p>任务数量</p>
            </div>
            <div class="title_right">
                <h2>{{users.length}}</h2>
                <p>总用户</p>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapMutations, mapState} from "vuex";

    export default {
        name: "Index",
        data() {
            return {
                tasks: [],
                users: [],
            }
        },
        methods: {
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

            this.$get("/user")
            .then((rs) => {
                this.users = rs.data.user
            })

        },

    }
</script>

<style scoped lang="less">

    .el-row {
        margin-bottom: 20px;
        &:last-child {
        margin-bottom: 0;
        }
    }
    .el-col {
        border-radius: 4px;
    }

      .bg-purple {
    background: #d3dce6;
  }

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

            .title_center {
                width: 50%;
                background: #d8b34d;
            }

            .title_right {
                width: 50%;
                background: #1f68bb;
            }
        }

    }

</style>