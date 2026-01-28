<template>
	<div class="main-content">
		<!-- 列表页：外层添加flex容器，实现表格与筛选栏左右布局 -->
		<template v-if="showFlag">
			<div class="list-layout-container">
				<!-- 表格及分页区域：放左侧，左右留空余 -->
				<div class="table-area">
					<el-table class="tables" :stripe='false'
						:style='{ "padding": "0", "borderColor": "#6F6F97", "borderWidth": "1px", "background": "#fff", "width": "100%", "borderStyle": "solid", "height": "fit-content" }'
						v-if="true" :data="dataList" v-loading="dataListLoading" @selection-change="selectionChangeHandler">
						<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
						<el-table-column :resizable='true' :sortable='false' label="索引" type="index" width="50" />
						<el-table-column :resizable='true' :sortable='false' prop="restaurantname" label="餐厅名称">
							<template slot-scope="scope">
								{{ scope.row.restaurantname }}
							</template>
						</el-table-column>
						<el-table-column :resizable='true' :sortable='false' prop="address" label="餐厅地址">
							<template slot-scope="scope">
								{{ scope.row.address }}
							</template>
						</el-table-column>
						<el-table-column :resizable='true' :sortable='false' prop="reserveprice" label="预定价格">
							<template slot-scope="scope">
								{{ scope.row.reserveprice }}
							</template>
						</el-table-column>
						<el-table-column :resizable='true' :sortable='false' prop="reservetime" label="预订时间">
							<template slot-scope="scope">
								{{ scope.row.reservetime }}
							</template>
						</el-table-column>
						<el-table-column :resizable='true' :sortable='false' prop="reserveremark" label="备注">
							<template slot-scope="scope">
								{{ scope.row.reserveremark }}
							</template>
						</el-table-column>
						<el-table-column :resizable='true' :sortable='false' prop="username" label="账号">
							<template slot-scope="scope">
								{{ scope.row.username }}
							</template>
						</el-table-column>
						<el-table-column :resizable='true' :sortable='false' prop="name" label="姓名">
							<template slot-scope="scope">
								{{ scope.row.name }}
							</template>
						</el-table-column>
						<el-table-column :resizable='true' :sortable='false' prop="telephone" label="手机">
							<template slot-scope="scope">
								{{ scope.row.telephone }}
							</template>
						</el-table-column>
						<el-table-column :resizable='true' :sortable='false' prop="ispay" label="是否支付">
							<template slot-scope="scope">
								<span style="margin-right:10px">{{ scope.row.ispay }}</span>
								<el-button v-if="scope.row.ispay === '未支付'" type="text" size="small"
									@click="payHandler(scope.row)">支付</el-button>
							</template>
						</el-table-column>
						<el-table-column :resizable='true' :sortable='false' prop="sfsh" label="审核状态">
							<template slot-scope="scope">
								<span :style='{
									"padding": "3px 8px",
									"borderRadius": "4px",
									"fontSize": "12px",
									"background": scope.row.sfsh === "待审核" ? "rgba(249, 115, 22, 0.2)" :
										scope.row.sfsh === "已取消" ? "rgba(239, 68, 68, 0.2)" :
											scope.row.sfsh === "已通过" ? "rgba(16, 185, 129, 0.2)" :
												scope.row.sfsh === "已使用" ? "rgba(234, 179, 8, 0.2)" :
													scope.row.sfsh === "已评价" ? "rgba(59, 130, 246, 0.2)" :
														"rgba(249, 115, 22, 0.2)",
									"color": scope.row.sfsh === "待审核" ? "#f97316" :
										scope.row.sfsh === "已取消" ? "#ef4444" :
											scope.row.sfsh === "已通过" ? "#10b981" :
												scope.row.sfsh === "已使用" ? "#eab308" :
													scope.row.sfsh === "已评价" ? "#3b82f6" :
														"#f97316"
								}'>
									{{ scope.row.sfsh ? scope.row.sfsh : '待审核' }}
								</span>
							</template>
						</el-table-column>
						<el-table-column width="300" label="操作">
							<template slot-scope="scope">
								<el-button
									:style='{ "border": "0", "cursor": "pointer", "padding": "0 24px", "margin": "0 10px 4px 0", "outline": "none", "color": "#000", "borderRadius": "30px", "background": "#E4E4E4", "width": "auto", "fontSize": "14px", "height": "32px" }'
									v-if="true" type="success" size="mini"
									@click="addOrUpdateHandler(scope.row.id, 'info')">详情</el-button>
								<el-button
									:style='{ "border": "0", "cursor": "pointer", "padding": "0 24px", "margin": "0 10px 4px 0", "outline": "none", "color": "#000", "borderRadius": "30px", "background": "#E4E4E4", "width": "auto", "fontSize": "14px", "height": "32px" }'
									v-if="isAuth('restaurantreserve', '修改')" type="primary" size="mini"
									@click="addOrUpdateHandler(scope.row.id)">修改</el-button>
									<el-button
									:style='{ "border": "0", "cursor": "pointer", "padding": "0 24px", "margin": "0 10px 4px 0", "outline": "none", "color": "#000", "borderRadius": "30px", "background": "#E4E4E4", "width": "auto", "fontSize": "14px", "height": "32px" }'
									v-if="scope.row.sfsh === '已使用'" type="primary" size="mini"
									@click="shDialog(scope.row)">评价</el-button>
								<el-button
									:style='{ "border": "0", "cursor": "pointer", "padding": "0 24px", "margin": "0 10px 4px 0", "outline": "none", "color": "#000", "borderRadius": "30px", "background": "#E4E4E4", "width": "auto", "fontSize": "14px", "height": "32px" }'
									v-if="isAuth('restaurantreserve', '删除')" type="danger" size="mini"
									@click="deleteHandler(scope.row.id)">删除</el-button>
							</template>
						</el-table-column>
					</el-table>
					<el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
						background :page-sizes="[10, 20, 30, 50]" :page-size="pageSize" :layout="layouts.join()" :total="totalPage"
						prev-text="<" next-text=">" :hide-on-single-page="true"
						:style='{ "padding": "0", "margin": "20px 0", "whiteSpace": "nowrap", "color": "#333", "textAlign": "center", "width": "100%", "fontWeight": "500" }'></el-pagination>
				</div>

				<!-- 筛选栏：放右侧，独立区域 -->
				<div class="filter-area">
					<el-form class="center-form-pv" :style='{ "width": "100%", "margin": "0 0 20px 0" }' :inline="true"
						:model="searchForm">
						<el-row :style='{ "flexDirection": "column", "display": "flex" }'>
							<div :style='{ "margin": "0", "flexDirection": "column", "display": "flex" }'>
								<label
									:style='{ "margin": "0 10px 0 0", "color": "#000", "textAlign": "center", "display": "inline-block", "width": "100%", "lineHeight": "40px", "fontSize": "14px", "fontWeight": "500", "height": "40px" }'
									class="item-label">餐厅名称</label>
								<el-input v-model="searchForm.restaurantname" placeholder="餐厅名称" clearable></el-input>
							</div>
							<div :style='{ "margin": "0", "flexDirection": "column", "display": "flex" }'>
								<label
									:style='{ "margin": "0 10px 0 0", "color": "#000", "textAlign": "center", "display": "inline-block", "width": "100%", "lineHeight": "40px", "fontSize": "14px", "fontWeight": "500", "height": "40px" }'
									class="item-label">餐厅地址</label>
								<el-input v-model="searchForm.address" placeholder="餐厅地址" clearable></el-input>
							</div>
							<el-button
								:style='{ "border": "0", "cursor": "pointer", "padding": "0", "boxShadow": "0px 2px 6px 0px rgba(0,0,0,0.3020)", "outline": "none", "margin": "6px 0 0", "color": "#fff", "borderRadius": "30px", "background": "#bad399", "width": "100%", "fontSize": "14px", "height": "40px" }'
								type="success" @click="search()">查询</el-button>
						</el-row>

						<el-row :style='{ "margin": "20px 0", "flexDirection": "column", "display": "flex" }'>
							<el-button
								:style='{ "border": "0", "cursor": "pointer", "padding": "0", "boxShadow": "0px 2px 6px 0px rgba(0,0,0,0.3020)", "margin": "0 0 10px", "outline": "none", "color": "#fff", "borderRadius": "30px", "background": "#bad399", "width": "100%", "fontSize": "14px", "height": "40px" }'
								v-if="isAuth('restaurantreserve', '新增')" type="success" @click="addOrUpdateHandler()">新增</el-button>
							<el-button
								:style='{ "border": "0", "cursor": "pointer", "padding": "0", "boxShadow": "0px 2px 6px 0px rgba(0,0,0,0.3020)", "margin": "0 0 10px", "outline": "none", "color": "#fff", "borderRadius": "30px", "background": "#bad399", "width": "100%", "fontSize": "14px", "height": "40px" }'
								v-if="isAuth('restaurantreserve', '删除')" :disabled="dataListSelections.length <= 0" type="danger"
								@click="deleteHandler()">删除</el-button>
						</el-row>
					</el-form>
				</div>
			</div>
		</template>

		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>

		<el-dialog title="评价" :visible.sync="sfshVisiable" width="50%"
			:style='{ "background": "rgba(15, 23, 42, 0.2)", "borderRadius": "8px", "border": "1px solid rgba(59, 130, 246, 0.3)" }'>
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item label="评分">
					<el-select v-model="shForm.rating" placeholder="评分">
						<el-option label="★" value="2"></el-option>
						<el-option label="★★" value="4"></el-option>
						<el-option label="★★★" value="6"></el-option>
						<el-option label="★★★★" value="8"></el-option>
						<el-option label="★★★★★" value="10"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="内容">
					<el-input type="textarea" :rows="8" v-model="shForm.review"></el-input>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button
					:style='{ "border": "1px solid rgba(100, 116, 139, 0.5)", "cursor": "pointer", "padding": "0 15px", "margin": "0 10px 0 0", "outline": "none", "color": "#94a3b8", "borderRadius": "4px", "background": "rgba(30, 41, 59, 0.6)", "width": "auto", "fontSize": "14px", "height": "32px" }'
					@click="shDialog">
					取消
				</el-button>
				<el-button
					:style='{ "border": "1px solid rgba(59, 130, 246, 0.5)", "cursor": "pointer", "padding": "0 15px", "margin": "0 10px 0 0", "outline": "none", "color": "#fff", "borderRadius": "4px", "background": "linear-gradient(90deg, rgba(59, 130, 246, 0.7), rgba(37, 99, 235, 0.7))", "width": "auto", "fontSize": "14px", "height": "32px" }'
					type="primary" @click="shHandler">
					确定
				</el-button>
			</span>
		</el-dialog>
	</div>
</template>

<script>
import axios from 'axios'
import AddOrUpdate from "./add-or-update";
export default {
	data() {
		return {
			searchForm: {
				key: ""
			},
			form: {},
			dataList: [],
			pageIndex: 1,
			pageSize: 10,
			totalPage: 0,
			dataListLoading: false,
			dataListSelections: [],
			showFlag: true,
			sfshVisiable: false,
			shForm: {},
			chartVisiable: false,
			chartVisiable1: false,
			chartVisiable2: false,
			chartVisiable3: false,
			chartVisiable4: false,
			chartVisiable5: false,
			addOrUpdateFlag: false,
			layouts: ["total", "prev", "pager", "next", "sizes", "jumper"],

		};
	},
	created() {
		this.init();
		this.getDataList();
		this.contentStyleChange()
	},
	mounted() {
	},
	filters: {
		htmlfilter: function (val) {
			return val.replace(/<[^>]*>/g).replace(/undefined/g, '');
		}
	},
	components: {
		AddOrUpdate,
	},
	methods: {
// 审核窗口
shDialog(row) {
			this.sfshVisiable = !this.sfshVisiable;
			if (row) {
				this.shForm = {
					...row,
					sfsh: '已评价',
					rating: row.rating,
					review: row.review,
					id: row.id
				}
			}
		},
		// 审核
		shHandler() {
			this.$confirm(`确定操作?`, "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
				type: "warning"
			}).then(() => {
				this.$http.post("restaurantreserve/update",this.shForm).then(({ data }) => {
					if (data && data.code === 0) {
						this.$message({
							message: "操作成功",
							type: "success",
							duration: 1500,
							onClose: () => {
								this.getDataList();
								this.shDialog()
							}
						});
					} else {
						this.$message.error(data.msg);
					}
				});
			});
		},
		contentStyleChange() {
			this.contentPageStyleChange()
		},
		// 分页
		contentPageStyleChange() {
			let arr = []

			// if(this.contents.pageTotal) arr.push('total')
			// if(this.contents.pageSizes) arr.push('sizes')
			// if(this.contents.pagePrevNext){
			//   arr.push('prev')
			//   if(this.contents.pagePager) arr.push('pager')
			//   arr.push('next')
			// }
			// if(this.contents.pageJumper) arr.push('jumper')
			// this.layouts = arr.join()
			// this.contents.pageEachNum = 10
		},

		payHandler(row) {
			this.$storage.set('paytable', 'restaurantreserve');
			this.$storage.set('payObject', row);
			this.$router.push('pay');
		},

		init() {
		},
		search() {
			this.pageIndex = 1;
			this.getDataList();
		},

		// 获取数据列表
		getDataList() {
			this.dataListLoading = true;
			let params = {
				page: this.pageIndex,
				limit: this.pageSize,
				sort: 'id',
				order: 'desc',
			}
			if (this.searchForm.restaurantname != '' && this.searchForm.restaurantname != undefined) {
				params['restaurantname'] = '%' + this.searchForm.restaurantname + '%'
			}
			if (this.searchForm.address != '' && this.searchForm.address != undefined) {
				params['address'] = '%' + this.searchForm.address + '%'
			}
			this.$http({
				url: "restaurantreserve/page",
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
		// 每页数
		sizeChangeHandle(val) {
			this.pageSize = val;
			this.pageIndex = 1;
			this.getDataList();
		},
		// 当前页
		currentChangeHandle(val) {
			this.pageIndex = val;
			this.getDataList();
		},
		// 多选
		selectionChangeHandler(val) {
			this.dataListSelections = val;
		},
		// 添加/修改
		addOrUpdateHandler(id, type) {
			this.showFlag = false;
			this.addOrUpdateFlag = true;
			this.crossAddOrUpdateFlag = false;
			if (type != 'info') {
				type = 'else';
			}
			this.$nextTick(() => {
				this.$refs.addOrUpdate.init(id, type);
			});
		},
		// 下载
		download(file) {
			window.open(`${file}`)
		},
		// 删除
		deleteHandler(id) {
			var ids = id
				? [Number(id)]
				: this.dataListSelections.map(item => {
					return Number(item.id);
				});
			this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
				type: "warning"
			}).then(() => {
				this.$http({
					url: "restaurantreserve/delete",
					method: "post",
					data: ids
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.$message({
							message: "操作成功",
							type: "success",
							duration: 1500,
							onClose: () => {
								this.search();
							}
						});
					} else {
						this.$message.error(data.msg);
					}
				});
			});
		},
	}
};
</script>
<style lang="scss" scoped>
	// 新增布局样式：控制筛选栏与表格的左右排列
	.list-layout-container {
	  display: flex;
	  align-items: flex-start; // 顶部对齐，避免筛选栏过高导致表格偏移
	  gap: 24px; // 表格与筛选栏之间的间距
	  width: 100%;
	  padding: 10px 0;
	}

	// 表格区域：左侧主体，左右留空余
	.table-area {
	  flex: 1; // 占满剩余宽度
	  margin: 0 40px; // 表格左右空余
	}

	// 筛选栏区域：右侧固定宽度
	.filter-area {
	  width: 240px; // 筛选栏固定宽度
	}

.center-form-pv {
	.el-date-editor.el-input {
		width: auto;
	}
}

.el-input {
	width: auto;
}

// form
.center-form-pv .el-input ::V-deep .el-input__inner {
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

.center-form-pv .el-select ::V-deep .el-input__inner {
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

.center-form-pv .el-date-editor ::V-deep .el-input__inner {
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
.el-table ::V-deep .el-table__header-wrapper thead {
	color: #999;
	font-weight: 500;
	width: 100%;
}

.el-table ::V-deep .el-table__header-wrapper thead tr {
	background: #fff;
}

.el-table ::V-deep .el-table__header-wrapper thead tr th {
	padding: 12px 0;
	background: #bad399;
	border-color: #fff;
	border-width: 0 1px 1px 0;
	border-style: solid;
	text-align: center;
}

.el-table ::V-deep .el-table__header-wrapper thead tr th .cell {
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


.el-table ::V-deep .el-table__body-wrapper tbody {
	width: 100%;
}

.el-table ::V-deep .el-table__body-wrapper tbody tr {
	background: #fff;
}

.el-table ::V-deep .el-table__body-wrapper tbody tr td {
	padding: 12px 0;
	color: #000;
	background: #fff;
	border-color: #6F6F97;
	border-width: 0 1px 1px 0;
	border-style: solid;
	text-align: center;
}


.el-table ::V-deep .el-table__body-wrapper tbody tr:hover td {
	padding: 12px 0;
	color: #000;
	background: rgba(103, 212, 178, .3);
	border-color: #6F6F97;
	border-width: 0 1px 1px 0;
	border-style: solid;
	text-align: center;
}

.el-table ::V-deep .el-table__body-wrapper tbody tr td {
	padding: 12px 0;
	color: #000;
	background: #fff;
	border-color: #6F6F97;
	border-width: 0 1px 1px 0;
	border-style: solid;
	text-align: center;
}

.el-table ::V-deep .el-table__body-wrapper tbody tr td .cell {
	padding: 0 10px;
	overflow: hidden;
	word-break: break-all;
	white-space: normal;
	line-height: 24px;
	text-overflow: ellipsis;
}

// pagination
.main-content .el-pagination ::V-deep .el-pagination__total {
	margin: 0 10px 0 0;
	color: #666;
	font-weight: 400;
	display: inline-block;
	vertical-align: top;
	font-size: 13px;
	line-height: 28px;
	height: 28px;
}

.main-content .el-pagination ::V-deep .btn-prev {
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

.main-content .el-pagination ::V-deep .btn-next {
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

.main-content .el-pagination ::V-deep .btn-prev:disabled {
	border: none;
	cursor: not-allowed;
	border-radius: 2px;
	padding: 0;
	margin: 0 5px;
	color: #C0C4CC;
	background: #f4f4f5;
	display: inline-block;
	vertical-align: top;
	font-size: 13px;
	line-height: 28px;
	height: 28px;
}

.main-content .el-pagination ::V-deep .btn-next:disabled {
	border: none;
	cursor: not-allowed;
	border-radius: 2px;
	padding: 0;
	margin: 0 5px;
	color: #C0C4CC;
	background: #f4f4f5;
	display: inline-block;
	vertical-align: top;
	font-size: 13px;
	line-height: 28px;
	height: 28px;
}

.main-content .el-pagination ::V-deep .el-pager {
	padding: 0;
	margin: 0;
	display: inline-block;
	vertical-align: top;
}

.main-content .el-pagination ::V-deep .el-pager .number {
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

.main-content .el-pagination ::V-deep .el-pager .number:hover {
	cursor: pointer;
	padding: 0 4px;
	margin: 0 5px;
	color: #409EFF;
	display: inline-block;
	vertical-align: top;
	font-size: 13px;
	line-height: 28px;
	border-radius: 2px;
	background: #bad399;
	text-align: center;
	min-width: 30px;
	height: 28px;
}

.main-content .el-pagination ::V-deep .el-pager .number.active {
	cursor: default;
	padding: 0 4px;
	margin: 0 5px;
	color: #FFF;
	display: inline-block;
	vertical-align: top;
	font-size: 13px;
	line-height: 28px;
	border-radius: 2px;
	background: #bad399;
	text-align: center;
	min-width: 30px;
	height: 28px;
}

.main-content .el-pagination ::V-deep .el-pagination__sizes {
	display: inline-block;
	vertical-align: top;
	font-size: 13px;
	line-height: 28px;
	height: 28px;
}

.main-content .el-pagination ::V-deep .el-pagination__sizes .el-input {
	margin: 0 5px;
	width: 100px;
	position: relative;
}

.main-content .el-pagination ::V-deep .el-pagination__sizes .el-input .el-input__inner {
	border: 1px solid #DCDFE6;
	cursor: pointer;
	padding: 0 25px 0 8px;
	color: #606266;
	display: inline-block;
	font-size: 13px;
	line-height: 28px;
	border-radius: 3px;
	outline: 0;
	background: #FFF;
	width: 100%;
	text-align: center;
	height: 28px;
}

.main-content .el-pagination ::V-deep .el-pagination__sizes .el-input span.el-input__suffix {
	top: 0;
	position: absolute;
	right: 0;
	height: 100%;
}

.main-content .el-pagination ::V-deep .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
	cursor: pointer;
	color: #C0C4CC;
	width: 25px;
	font-size: 14px;
	line-height: 28px;
	text-align: center;
}

.main-content .el-pagination ::V-deep .el-pagination__jump {
	margin: 0 0 0 24px;
	color: #606266;
	display: inline-block;
	vertical-align: top;
	font-size: 13px;
	line-height: 28px;
	height: 28px;
}

.main-content .el-pagination ::V-deep .el-pagination__jump .el-input {
	border-radius: 3px;
	padding: 0 2px;
	margin: 0 2px;
	display: inline-block;
	width: 50px;
	font-size: 14px;
	line-height: 18px;
	position: relative;
	text-align: center;
	height: 28px;
}

.main-content .el-pagination ::V-deep .el-pagination__jump .el-input .el-input__inner {
	border: 1px solid #DCDFE6;
	cursor: pointer;
	padding: 0 3px;
	color: #606266;
	display: inline-block;
	font-size: 14px;
	line-height: 28px;
	border-radius: 3px;
	outline: 0;
	background: #FFF;
	width: 100%;
	text-align: center;
	height: 28px;
}
</style>
