<template>
	<div class="main-content">
		<!-- 列表页 -->
		<template v-if="showFlag">
			<el-form class="center-form-pv" :style='{"width":"100%","margin":"0 0 20px 0"}' :inline="true" :model="searchForm">
				<el-row :style='{"margin":"20px 0","display":"flex","alignItems":"center","justifyContent":"space-between"}'>
					<el-col :span="12">
						<el-form-item label="主分类" :label-width="formLabelWidth">
							<el-select v-model="searchForm.dicCode" placeholder="请选择主分类" @change="handleMainCategoryChange">
								<el-option value="" label="全部主分类"></el-option>
								<el-option v-for="(item, index) in uniqueMainCategoryList" :key="index" 
									:label="item.dicName" :value="item.dicCode">
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12" :style='{"textAlign":"right"}'>
						<el-button 
							:style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 4px 0","outline":"none","color":"#fff","borderRadius":"30px","background":"#bad399","width":"auto","fontSize":"14px","height":"32px"}' 
							type="primary" 
							@click="addMainCategoryWithSubHandler">
							新增主分类及子分类
						</el-button>
						<el-button 
							:style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 4px 0","outline":"none","color":"#fff","borderRadius":"30px","background":"#bad399","width":"auto","fontSize":"14px","height":"32px"}' 
							type="primary" 
							@click="addSubCategoryHandler"
							:disabled="!searchForm.dicCode">
							新增子分类
						</el-button>
					</el-col>
				</el-row>
			</el-form>
			
			<el-table class="tables"
				:stripe='false'
				:style='{"padding":"0","borderColor":"#6F6F97","borderWidth":"1px","background":"#fff","width":"100%","borderStyle":"solid","height":"fit-content"}' 
				v-if="isAuth('dictionary','查看')"
				:data="dataList"
				v-loading="dataListLoading">
				<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
				<el-table-column :resizable='true' :sortable='false' label="索引" type="index" width="50" />
				
				<!-- 主分类列，仅在未选择具体主分类时显示 -->
				<el-table-column :resizable='true' :sortable='false' prop="dicCode" label="主分类编码" >
					<template slot-scope="scope">
						{{scope.row.dicCode}}
					</template>
				</el-table-column>
				
				<el-table-column :resizable='true' :sortable='false' prop="dicName" label="主分类名称" >
					<template slot-scope="scope">
						{{scope.row.dicName}}
					</template>
				</el-table-column>
				
				<!-- 子分类列，仅在选择了具体主分类时显示 -->
				<el-table-column :resizable='true' :sortable='false' prop="codeIndex" label="子分类编号" >
					<template slot-scope="scope">
						{{scope.row.codeIndex}}
					</template>
				</el-table-column>
				
				<el-table-column :resizable='true' :sortable='false' prop="indexName" label="子分类名称" >
					<template slot-scope="scope">
						{{scope.row.indexName}}
					</template>
				</el-table-column>
				
				<el-table-column :resizable='true' :sortable='false' prop="beizhu" label="备注">
					<template slot-scope="scope">
						{{scope.row.beizhu || '-'}}
					</template>
				</el-table-column>
				
				<el-table-column width="200" label="操作">
					<template slot-scope="scope">
						<el-button 
							:style='{"border":"0","cursor":"pointer","padding":"0 12px","margin":"0 5px 4px 0","outline":"none","color":"#000","borderRadius":"30px","background":"#E4E4E4","width":"auto","fontSize":"14px","height":"32px"}' 
							v-if="isAuth('dictionary','查看')" 
							type="success" 
							size="mini" 
							@click="infoHandler(scope.row.id)">
							详情
						</el-button>
						<el-button 
							:style='{"border":"0","cursor":"pointer","padding":"0 12px","margin":"0 5px 4px 0","outline":"none","color":"#000","borderRadius":"30px","background":"#E4E4E4","width":"auto","fontSize":"14px","height":"32px"}' 
							v-if="isAuth('dictionary','修改')" 
							type="primary" 
							size="mini" 
							@click="editHandler(scope.row.id)">
							修改
						</el-button>
						<el-button 
							:style='{"border":"0","cursor":"pointer","padding":"0 12px","margin":"0 5px 4px 0","outline":"none","color":"#000","borderRadius":"30px","background":"#E4E4E4","width":"auto","fontSize":"14px","height":"32px"}' 
							v-if="isAuth('dictionary','删除')" 
							type="danger" 
							size="mini" 
							@click="deleteHandler(scope.row.id)">
							删除
						</el-button>
					</template>
				</el-table-column>
			</el-table>
			
			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				background
				:page-sizes="[10, 20, 30, 50]"
				:page-size="pageSize"
				:layout="layouts.join()"
				:total="totalPage"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="true"
				:style='{"padding":"0","margin":"20px 0","whiteSpace":"nowrap","color":"#333","textAlign":"center","width":"100%","fontWeight":"500"}'
			></el-pagination>
		</template>
		
		<!-- 添加/修改弹窗 -->
		<el-dialog 
			:title="dialogTitle" 
			:visible.sync="dialogVisible" 
			width="500px"
			:close-on-click-modal="false">
			<el-form ref="form" :model="form" :rules="rules" label-width="120px">
				<!-- 主分类信息 -->
				<el-form-item label="主分类编码" prop="dicCode" v-if="isMainCategoryOperation || isNewMainCategory">
					<el-input v-model="form.dicCode" placeholder="请输入主分类编码" :disabled="!isNewMainCategory"></el-input>
				</el-form-item>
				<el-form-item label="主分类名称" prop="dicName" v-if="isMainCategoryOperation || isNewMainCategory">
					<el-input v-model="form.dicName" placeholder="请输入主分类名称"></el-input>
				</el-form-item>
				
				<!-- 子分类信息 -->
				<el-form-item label="主分类" prop="dicCode" v-if="!isMainCategoryOperation && !isNewMainCategory">
					<el-select v-model="form.dicCode" placeholder="请选择主分类" disabled>
						<el-option v-for="(item, index) in uniqueMainCategoryList" :key="index" 
							:label="item.dicName" :value="item.dicCode">
						</el-option>
					</el-select>
				</el-form-item>
				
				<el-form-item label="子分类编号" prop="codeIndex">
					<el-input v-model="form.codeIndex" placeholder="子分类编号" :disabled="form.id && !isNewMainCategory"></el-input>
				</el-form-item>
				
				<el-form-item label="子分类名称" prop="indexName">
					<el-input v-model="form.indexName" placeholder="请输入子分类名称"></el-input>
				</el-form-item>
				
				<el-form-item label="备注" prop="beizhu">
					<el-input type="textarea" v-model="form.beizhu" placeholder="请输入备注信息" rows="3"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dialogVisible = false">取消</el-button>
				<el-button type="primary" @click="submitForm">确定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      // 搜索表单
      searchForm: {
        dicCode: '' // 主分类编码，用于筛选子分类
      },
      formLabelWidth: '100px',
      
      // 数据列表
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      
      // 主分类列表（包含所有数据，用于去重）
      mainCategoryList: [],
      // 去重后的主分类列表
      uniqueMainCategoryList: [],
      
      // 弹窗相关
      dialogVisible: false,
      dialogTitle: '',
      form: {},
      isMainCategoryOperation: false, // 区分是操作主分类还是子分类
      isNewMainCategory: false, // 是否是新增主分类（同时需要新增子分类）
      
      // 页面状态
      showFlag: true,
      layouts: ["total","prev","pager","next","sizes","jumper"],
      
      // 表单验证规则
      rules: {
        dicCode: [
          { required: true, message: '请输入主分类编码', trigger: 'blur' }
        ],
        dicName: [
          { required: true, message: '请输入主分类名称', trigger: 'blur' }
        ],
        codeIndex: [
          { required: true, message: '请输入子分类编号', trigger: 'blur' },
          { type: 'number', message: '请输入数字', trigger: 'blur' }
        ],
        indexName: [
          { required: true, message: '请输入子分类名称', trigger: 'blur' }
        ]
      }
    };
  },
  created() {
    this.init();
    this.getMainCategoryList();
    this.getDataList();
  },
  methods: {
    init() {
      // 初始化操作
    },
    
    // 获取主分类列表并去重
    getMainCategoryList() {
      this.dataListLoading = true;
      this.$http({
        url: "dictionary/page",
        method: "get",
        params: {
          page: 1,
          limit: 1000,
		  sort: 'id',
          order: 'asc'
        }
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.mainCategoryList = data.data.list;
          // 去重处理，确保每个主分类只出现一次
          this.uniqueMainCategoryList = this.uniqueMainCategories(this.mainCategoryList);
        }
        this.dataListLoading = false;
      });
    },
    
    // 主分类去重方法
    uniqueMainCategories(categories) {
      const unique = {};
      const result = [];
      for (const item of categories) {
        if (!unique[item.dicCode]) {
          unique[item.dicCode] = true;
          result.push(item);
        }
      }
      return result;
    },
    
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'code_index',
        order: 'asc'
      };
      
      // 如果选择了主分类，则查询该主分类下的子分类
      if (this.searchForm.dicCode) {
        params.dicCode = this.searchForm.dicCode;
      } else {
        // 否则查询所有主分类的第一条子分类，用于代表主分类
        params.groupBy = 'dicCode';
      }
      
      this.$http({
        url: "dictionary/page",
        method: "get",
        params: params
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.list;
          this.totalPage = data.data.total;
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    
    // 主分类变化时触发
    handleMainCategoryChange() {
      this.pageIndex = 1;
      this.getDataList();
    },
    
    // 分页相关
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    
    // 权限检查
    isAuth(resource, action) {
      // 实际项目中应根据权限系统实现
      return true;
    },
    
    // 新增主分类及子分类
    addMainCategoryWithSubHandler() {
      this.dialogTitle = '新增主分类及子分类';
      this.isNewMainCategory = true;
      this.form = {
        dicCode: '',
        dicName: '',
        codeIndex: 1, // 新主分类的第一个子分类编号默认为1
        indexName: '',
        beizhu: ''
      };
      this.dialogVisible = true;
    },
    
    // 新增子分类
    addSubCategoryHandler() {
      if (!this.searchForm.dicCode) {
        this.$message.warning('请先选择主分类');
        return;
      }
      
      this.dialogTitle = '新增子分类';
      this.isNewMainCategory = false;
      this.isMainCategoryOperation = false;
      
      // 获取最大codeIndex
      this.$http({
        url: "dictionary/maxCodeIndex",
        method: "post",
        data: { dicCode: this.searchForm.dicCode }
      }).then(({ data }) => {
        if (data && data.code === 0) {
          // 查找主分类名称
          const mainCategory = this.uniqueMainCategoryList.find(item => item.dicCode === this.searchForm.dicCode);
          
          this.form = {
            dicCode: this.searchForm.dicCode,
            dicName: mainCategory ? mainCategory.dicName : '',
            codeIndex: data.maxCodeIndex,
            indexName: '',
            beizhu: ''
          };
          this.dialogVisible = true;
        }
      });
    },
    
    // 详情
    infoHandler(id) {
      this.$http({
        url: `dictionary/info/${id}`,
        method: "get"
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.form = data.data;
          this.isNewMainCategory = false;
          this.isMainCategoryOperation = false;
          this.dialogTitle = '详情';
          this.dialogVisible = true;
          // 详情模式下禁用所有输入框
          this.$nextTick(() => {
            const inputs = document.querySelectorAll('.el-dialog .el-input__inner, .el-dialog .el-textarea__inner');
            inputs.forEach(input => {
              input.disabled = true;
            });
          });
        }
      });
    },
    
    // 编辑
    editHandler(id) {
      this.$http({
        url: `dictionary/info/${id}`,
        method: "get"
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.form = data.data;
          this.isNewMainCategory = false;
          this.isMainCategoryOperation = false;
          this.dialogTitle = '编辑子分类';
          this.dialogVisible = true;
        }
      });
    },
    
    // 删除
    deleteHandler(id) {
      this.$confirm(`确定要删除这条数据吗?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "dictionary/delete",
          method: "post",
          data: [id]
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "删除成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.getDataList();
                this.getMainCategoryList(); // 重新加载主分类列表
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },
    
    // 提交表单
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          const url = this.form.id ? "dictionary/update" : "dictionary/save";
          this.$http({
            url: url,
            method: "post",
            data: this.form
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: this.form.id ? "修改成功" : "新增成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.dialogVisible = false;
                  this.getDataList();
                  this.getMainCategoryList(); // 重新加载主分类列表
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
	.center-form-pv {
	  .el-date-editor.el-input {
	    width: auto;
	  }
	}
	
	.el-input {
	  width: auto;
	}
	
	// form
	.center-form-pv .el-input ::v-deep .el-input__inner {
				border: 2px solid #bad399;
				border-radius: 30px;
				padding: 0 12px;
				box-shadow: 0 0 0px rgba(64, 158, 255, .5);
				outline: none;
				color: #000;
				background: #F8F6F7;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
	
	.center-form-pv .el-select ::v-deep .el-input__inner {
				border: 2px solid #bad399;
				border-radius: 30px;
				padding: 0 10px;
				box-shadow: 0 0 0px rgba(64, 158, 255, .5);
				outline: none;
				color: #000;
				background: #F8F6F7;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
	
	.center-form-pv .el-date-editor ::v-deep .el-input__inner {
				border: 2px solid #bad399;
				border-radius: 30px;
				padding: 0 10px 0 30px;
				box-shadow: 0 0 0px rgba(64, 158, 255, .5);
				outline: none;
				color: #000;
				background: #F8F6F7;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
	
	// table
	.el-table ::v-deep .el-table__header-wrapper thead {
				color: #999;
				font-weight: 500;
				width: 100%;
			}
	
	.el-table ::v-deep .el-table__header-wrapper thead tr {
				background: #fff;
			}
	
	.el-table ::v-deep .el-table__header-wrapper thead tr th {
				padding: 12px 0;
				background: #bad399;
				border-color: #fff;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: center;
			}

	.el-table ::v-deep .el-table__header-wrapper thead tr th .cell {
				padding: 0 10px;
				word-wrap: normal;
				color: #fff;
				word-break: break-all;
				white-space: normal;
				font-weight: bold;
				display: inline-block;
				vertical-align: middle;
				width: 100%;
				line-height: 24px;
				position: relative;
				text-overflow: ellipsis;
			}

	
	.el-table ::v-deep .el-table__body-wrapper tbody {
				width: 100%;
			}

	.el-table ::v-deep .el-table__body-wrapper tbody tr {
				background: #fff;
			}
	
	.el-table ::v-deep .el-table__body-wrapper tbody tr td {
				padding: 12px 0;
				color: #000;
				background: #fff;
				border-color: #6F6F97;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: center;
			}
	
		
	.el-table ::v-deep .el-table__body-wrapper tbody tr:hover td {
				padding: 12px 0;
				color: #000;
				background: rgba(103,212,178,.3);
				border-color: #6F6F97;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: center;
			}
	
	.el-table ::v-deep .el-table__body-wrapper tbody tr td {
				padding: 12px 0;
				color: #000;
				background: #fff;
				border-color: #6F6F97;
				border-width: 0 1px 1px 0;
				border-style: solid;
				text-align: center;
			}

	.el-table ::v-deep .el-table__body-wrapper tbody tr td .cell {
				padding: 0 10px;
				overflow: hidden;
				word-break: break-all;
				white-space: normal;
				line-height: 24px;
				text-overflow: ellipsis;
			}
	
	// pagination
	.main-content .el-pagination ::v-deep .el-pagination__total {
				margin: 0 10px 0 0;
				color: #666;
				font-weight: 400;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination ::v-deep .btn-prev {
				border: none;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #666;
				background: #f4f4f5;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination ::v-deep .btn-next {
				border: none;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #666;
				background: #f4f4f5;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination ::v-deep .el-pager .number {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #666;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #f4f4f5;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
	
	.main-content .el-pagination ::v-deep .el-pager .number:hover {
				background: #bad399;
				color: #409EFF;
			}
	
	.main-content .el-pagination ::v-deep .el-pager .number.active {
				background: #bad399;
				color: #FFF;
			}
			
	// dialog styles
	.el-dialog ::v-deep .el-dialog__header {
		background-color: #bad399;
		padding: 15px 20px;
	}
	
	.el-dialog ::v-deep .el-dialog__title {
		color: #fff;
		font-weight: bold;
	}
	
	.el-dialog ::v-deep .el-dialog__body {
		padding: 20px;
	}
	
	.el-dialog ::v-deep .el-form-item__label {
		color: #666;
	}
	
	.el-dialog ::v-deep .el-input__inner,
	.el-dialog ::v-deep .el-textarea__inner {
		border: 2px solid #bad399;
		border-radius: 4px;
	}
	
	.el-dialog ::v-deep .dialog-footer {
		text-align: center;
	}
	
	.el-dialog ::v-deep .dialog-footer .el-button {
		margin: 0 10px;
		padding: 0 20px;
		height: 32px;
		line-height: 32px;
	}
	
	.el-dialog ::v-deep .dialog-footer .el-button--primary {
		background-color: #bad399;
		border-color: #bad399;
		color: #fff;
	}
</style>
    