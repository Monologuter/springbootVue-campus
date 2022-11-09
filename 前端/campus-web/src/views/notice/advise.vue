<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公告标题" prop="noticeTitle">
        <el-input
          v-model="queryParams.noticeTitle"
          placeholder="请输入公告标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公告内容" prop="noticeContent">
        <el-input
          v-model="queryParams.noticeContent"
          placeholder="请输入公告内容"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >发布公告</el-button>
      </el-col>
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

    <el-table v-loading="loading" :data="adviseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="noticeId" width="100" />
      <el-table-column label="公告标题" align="center" prop="noticeTitle" />
      <el-table-column label="创建者" align="center" prop="createBy" width="100" />

      <el-table-column label="创建时间" align="center" prop="createTime" width="200">
        <template slot-scope="scope">
          <span>{{ transform(scope.row.createTime) }}</span>
        </template> 
      </el-table-column>

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

    <!-- 添加或修改通知公告公告对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="743px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公告标题" prop="noticeTitle">
          <el-input v-model="form.noticeTitle" placeholder="请输入公告标题" />
        </el-form-item>
        <el-form-item label="公告内容" prop="noticeContent">
          <el-input
                        resize="none"
                        type="textarea"
                        :autosize="{ minRows: 6, maxRows: 10}"
                        placeholder="请输入公告内容"
                        v-model="form.noticeContent" style="padding: 0">
          </el-input>
          <!-- <quill-editor v-model="form.noticeContent" placeholder="请输入公告内容" /> -->
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="创建者" prop="remark">
          <el-input v-model="form.createBy" placeholder="请输入备注" />
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
  import { listAdvise, getAdvise, delAdvise, addAdvise, updateAdvise } from "@/api/notice/advise";
  import {formatDate} from '@/util/date';

  export default {
    name: "Advise",
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
        // 通知公告公告表格数据
        adviseList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          noticeTitle: null,
          noticeContent: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          noticeTitle: [
            { required: true, message: "公告标题不能为空", trigger: "blur" }
          ],
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询通知公告公告列表 */
      getList() {
        this.loading = true;
        listAdvise(this.queryParams).then(response => {
          this.adviseList = response.data.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加通知公告";
      },

      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const noticeId = row.noticeId || this.ids
        getAdvise(noticeId).then(response => {
          this.form = response.data.data;
          this.open = true;
          this.title = "修改通知公告公告";
        });
      },

      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.noticeId != null) {
              updateAdvise(this.form).then(response => {
                this.$message("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addAdvise(this.form).then(response => {
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
        const noticeIds = row.noticeId || this.ids;
        this.$confirm('是否确认删除此学生', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            }).then(() => {
            delAdvise(noticeIds).then(response => {
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
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          noticeId: null,
          noticeTitle: null,
          noticeContent: null,
          createBy: null,
          createTime: null,
          remark: null
        };
        // this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.noticeId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      transform(time) {
                let date = new Date(time);
                return formatDate(date, 'yyyy-MM-dd hh:mm');
      }
    },

  };
</script>
