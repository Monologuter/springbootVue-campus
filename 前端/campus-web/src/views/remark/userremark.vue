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

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->


    <el-table v-loading="loading" :data="remarkList"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
       <el-table-column label="任务id" align="center" prop="taskId" />
      <el-table-column label="评价人id" align="center" prop="publishId" />

      <el-table-column label="评分" min-width="100" align="center" prop="star">
        <template slot-scope="scope">
          <el-rate
            v-model="scope.row.star"
            show-text>
          </el-rate>
        </template>
      </el-table-column>

      <el-table-column label="评价内容" align="center" prop="remark" />
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
      
        <el-form-item label="星级" prop="star">
            <el-rate
                v-model="form.star"
                show-text>
            </el-rate>
        </el-form-item>
        <el-form-item label="评价内容" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入评价内容" />
        </el-form-item>
      
      </el-form>

    </el-dialog>
  </div>
</template>

<script>
import { listRemark } from "@/api/remark/remark";
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
    /** 查询‘评价我的’列表 搜索接受任务人为自己 */
    getList() {
      this.loading = true;
      listRemark(
        {"acceptId" : this.user.id}
      ).then(res => {
        this.remarkList = res.data.rows;
        console.log(this.remarkList);
        this.total = res.total;
        this.loading = false;
      });
    },
    //任务列表
    retrieveData() {
        this.$get("/task/published", {id: this.user.id}).then(res => {
        // console.log(res.data.task)
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
  
    
  }
};
</script>
