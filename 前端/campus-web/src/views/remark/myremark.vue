<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="星级" prop="star">
        <el-input
          v-model="queryParams.star"
          placeholder="请输入星级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务id" prop="taskId">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入任务id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="送货人id" prop="acceptId">
        <el-input
          v-model="queryParams.acceptId"
          placeholder="请输入送货人id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评价人id" prop="publishId">
        <el-input
          v-model="queryParams.publishId"
          placeholder="请输入评价人id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <!-- <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar> -->
    </el-row>

    <el-table v-loading="loading" :data="remarkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务id" align="center" prop="taskId" />
      <el-table-column label="送货人id" align="center" prop="acceptId" />

      <el-table-column label="评分" min-width="100" align="center" prop="star">
        <template slot-scope="scope">
          <el-rate
            v-model="scope.row.star"
            show-text>
          </el-rate>
        </template>
      </el-table-column>

      <el-table-column label="评价内容" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <!-- <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    /> -->

    <!-- 添加或修改remark对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      
        <el-form-item label="评分" prop="star">
            <el-rate
                v-model="form.star"
                show-text
                @change="changeValue">
            </el-rate>
        </el-form-item>

        <el-form-item label="评价内容" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入评价内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRemark, getRemark, delRemark, addRemark, updateRemark } from "@/api/remark/remark";
import {mapState} from "vuex"

export default {
  name: "Remark",
  data() {
    return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // remark表格数据
        remarkList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
            pageNum: 1,
            pageSize: 10,
            star: null,
            taskId: null,
            acceptId: null,
            publishId: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        },
        tasks: [],
    };
  },
  computed: {
            ...mapState('user', ['user'])
  },
  created() {
    this.getList();
    this.retrieveData();
  },
  methods: {
    /** 查询remark列表 */
    getList() {
      this.loading = true;
      listRemark({"publishId" : this.user.id}).then(res => {
        this.remarkList = res.data.rows;
        this.total = res.total;
        this.loading = false;
      });
    },
    //任务列表
    retrieveData() {
        this.$get("/task/published", {id: this.user.id}).then(res => {
        console.log(res.data.task)
        this.tasks = res.data.task
        })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        star: null,
        remark: null,
        taskId: null,
        acceptId: null,
        publishId: null
      };
    //   this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRemark(id).then(response => {
        this.form = response.data.data;
        this.open = true;
        this.title = "修改remark";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRemark(this.form).then(response => {
              this.$message("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRemark(this.form).then(response => {
              this.$message("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除此学生', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            }).then(() => {
            delRemark(ids).then(response => {
                this.open = false;
                this.getList();
            });
            this.$message({
                type: 'success',
                message: '删除成功!'
                });

            }).catch(() => {
            this.$message({
                type: 'info',
                message: '已取消删除'
            });          
            });
    },
    //官网提供的方法，它默认有个参数value，所获取到的就是改变的值，用它进行实际需求操作
    changeValue(value){
      console.log(value);
    }
    
  }
};
</script>
