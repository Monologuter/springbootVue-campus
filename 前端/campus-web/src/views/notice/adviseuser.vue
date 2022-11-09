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


    <el-table v-loading="loading" :data="adviseList" >
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
          >查看公告</el-button>
        </template>
      </el-table-column>
    </el-table>


    <!-- 添加或修改通知公告公告对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="743px" append-to-body>

        <!-- <el-table v-loading="loading" :data="adviseList" @selection-change="handleSelectionChange">
        <el-table-column label="序号" align="center" prop="noticeId" width="100" />
        <el-table-column label="公告标题" align="center" prop="noticeTitle" />
        <el-table-column label="创建者" align="center" prop="createBy" width="100" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="200">
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
            <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
            >查看公告</el-button>
            </template>
        </el-table-column>
        </el-table> -->

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
            <el-button type="primary" @click="cancel">确 定</el-button>
        </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listAdvise, getAdvise} from "@/api/notice/advise";
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

      /** 修改按钮操作 */
      handleUpdate(row) {
        const noticeId = row.noticeId || this.ids
        getAdvise(noticeId).then(response => {
          this.form = response.data.data;
          this.open = true;
          this.title = "查看通知公告";
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      transform(time) {
                let date = new Date(time);
                return formatDate(date, 'yyyy-MM-dd');
      }

    }

  };

</script>

<style scoped lang="less">

    /deep/ .el-input__inner{
    text-align : center;
    }

</style>
