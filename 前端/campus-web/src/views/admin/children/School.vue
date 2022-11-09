<template>
    <div class="content">
        <el-table
                :data="dataList"
                style="width: 100%;margin-bottom: 20px;"
                row-key="uuid"
                border
                :default-expand-all = 'false'
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
            <el-table-column
                    prop="name"
                    label="学校"
                    sortable
                    width="230">
            </el-table-column>
            <el-table-column
                    prop="deptname"
                    label="专业"
                    sortable
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="classesname"
                    label="班级">
            </el-table-column>

            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            icon="el-icon-plus"
                            size="mini"
                            @click="handleAdd(scope.$index, scope.row)">增加
                    </el-button>

                    <el-button
                            icon="el-icon-edit"
                            size="mini"
                            @click="handleUpdate(scope.$index, scope.row)">修改
                    </el-button>

                    <el-button
                            icon="el-icon-delete"
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>

            <!-- ------------新加入---------- -->


            <!-- 添加或修改学生管理对话框 -->
            <el-dialog :title="title"  :visible.sync="open" width="500px" append-to-body>
                <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                        <el-form-item label="学校" prop="name">
                        <el-input v-model="form.name" placeholder="请输入学校" />
                        </el-form-item>
                        <el-form-item label="专业" prop="deptname">
                        <el-input v-model="form.deptname" placeholder="请输入专业" />
                        </el-form-item>
                        <el-form-item label="班级"  prop="classesname">
                        <el-input  placeholder="请输入班级" v-model="form.classesname"/>
                        </el-form-item>
                    </el-form>
                         <div slot="footer" class="dialog-footer" >
                            <el-button type="primary" @click="submit()">确 定</el-button>
                            <el-button @click="cancel">取 消</el-button>
                        </div> 
            </el-dialog>

        </el-table>


        <!-- ------------新加入---------- -->
    </div>
</template>

<script>
    export default {
        name: "School",
        data() {
            return {
                value:null,

                schools : [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 表单参数
                form: {},
                // 表单校验
                rules: {},
            }
        },
        methods: {
            guid2() {
                function S4() {
                    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
                }
                return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
            },

            //-------新加入---------

            /** 新增按钮操作 */
            handleAdd(a,b) {
                console.log(b.children.length)
                // console.log(b)
                if(b.schoolId != null){
                    this.value=b;
                    this.reset();
                    this.title = "添加学校信息";
                    this.open = true;
                }
            },

            /** 提交按钮 */
            submit() {
                console.log(this.value.children.[0])
            this.$refs["form"].validate(valid => {
                if (valid) {
                    if (this.form.id != null) {
                        updateStuage(this.form).then(response => {
                        this.$message("修改成功");
                        this.open = false;
                        this.newList();
                        });
                    } else {
                        this.$post("/school",{
                            "name": this.form.name,
                        }).then(response => {
                            this.$message("新增学校成功");
                            this.newList();
                            this.$post("/dept",{
                                "schoolId":this.value.schoolId,
                                "name": this.form.deptname,
                            }).then(response => {
                                const name = this.form.deptname;
                                for(let i=0 ;i<this.value.children.length;i++){
                                    if(this.value.children.[i].deptname==name){
                                        this.$message("新增专业成功");
                                        this.newList();
                                        this.$post("/class",{
                                        "schoolId":this.value.schoolId,
                                        "deptId":this.value.children.[i].deptsId,
                                        "name": this.form.classesname,
                                        }).then(response => {
                                            this.$message("新增班级成功");
                                            this.newList();
                                        });
                                    }
                                }
                                
                            });
                            this.$message("新增成功");
                            this.open = false;
                            this.newList();
                            });
                    }
                }
            });
            },



            // 取消按钮
            cancel() {
            this.open = false;
            },

            handleDelete(a,b){
                console.log(b);                
                if(b.schoolId != null){
                    this.$del("/school/"+b.schoolId)
                    .then((res) => {
                        this.$notifyMsg("成功", res.data.msg, "success")
                        this.newList()
                    })
                }else if(b.deptsId != null){
                     this.$del("/dept/"+b.deptsId)
                        .then((res) => {
                            this.$notifyMsg("成功", res.data.msg, "success")
                            this.newList()
                        })
                }else{
                    this.$del("/class/"+b.classesId)
                        .then((res) => {
                            this.$notifyMsg("成功", res.data.msg, "success")
                            this.newList()
                        })
                }
            },


            /** 修改按钮操作 */
            handleUpdate(a,b) {
                // console.log(b);
                this.$get("/school/"+b.schoolId).then((res) => {
                    this.form = res.data.school;
                    this.open = true;
                    this.title = "修改学校管理";
                });
            },

            newList(){
                this.$get('/school')
                .then(res => {
                    // console.log(res.data)
                    // console.log(this.schools)
                    this.schools = res.data.school
                })
            },

            // 表单重置
            reset() {
            this.form = {
                id: null,
                name: null,
                age: null
            };
            // this.resetForm("form");
            },
            //-------新加入---------
        },

        created() {
            this.newList();
        },

        computed:{
            dataList(){
                return this.schools.map(item => {
                    let depts = [];
                    for (let i = 0;i<item['depts'].length;i++){
                        // console.log(item['depts'][i]['classes'])
                        depts.push(
                            {
                                deptsId : item['depts'][i].id,
                                deptname : item['depts'][i].name,
                                uuid : this.guid2()
                            }
                        )
                        // console.log(depts)
                        let children = [];
                        for (let j = 0;j<item['depts'][i]['classes'].length;j++){
                            children.push(
                                {
                                    classesId : item['depts'][i]['classes'][j].id,
                                    classesname:item['depts'][i]['classes'][j].name,
                                    uuid : this.guid2()
                                }
                            )
                        }
                        depts[i].children = children
                    }
                    return{
                            schoolId : item.id,
                            name : item.name,
                            children : depts,
                            uuid : this.guid2()
                    }
                })
            }
        }
    }
</script>

<style scoped lang="less">
    .content {
        padding: 0 1%;
    }
</style>