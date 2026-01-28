<template>
	<div class="addEdit-block" style="width: 100%;">
		<el-form
			:style='{ "padding": "30px", "boxShadow": "0 0px 0px rgba(64, 158, 255, .3)", "margin": "-30px 0 0", "borderRadius": "0 0 30px 30px", "flexWrap": "wrap", "background": "#F8F6F7", "display": "flex", "width": "100%" }'
			class="add-update-preview" ref="ruleForm" :model="ruleForm" :rules="rules" label-width="120px">
			<template>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' class="input" v-if="type != 'info'" label="标题"
					prop="address">
					<el-input v-model="ruleForm.address" placeholder="标题" clearable :readonly="ro.address"></el-input>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' v-else class="input" label="标题"
					prop="address">
					<el-input v-model="ruleForm.address" placeholder="标题" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' class="select" v-if="type != 'info'"
					label="攻略类型" prop="traveltype">
						<el-select v-model="ruleForm.traveltype" placeholder="攻略类型" clearable>
							<el-option v-for="item in traveltypeList" :key="item.indexName" :label="item.indexName"
								:value="item.indexName"></el-option>
						</el-select>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' v-else class="input" label="攻略类型"
					prop="traveltype">
					<el-input v-model="ruleForm.traveltype" placeholder="攻略类型" readonly></el-input>
				</el-form-item>
				<!-- <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' class="input" v-if="type!='info'"  label="旅行天数" prop="duration">
					<el-input v-model="ruleForm.duration" placeholder="旅行天数" clearable  :readonly="ro.duration"></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-else class="input" label="旅行天数" prop="duration">
					<el-input v-model="ruleForm.duration" placeholder="旅行天数" readonly></el-input>
				</el-form-item> -->
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' class="upload"
					v-if="type != 'info' && !ro.travelphoto" label="旅行图片" prop="travelphoto">
					<file-upload tip="点击上传图片" action="file/upload" :limit="3" :multiple="true"
						:fileUrls="ruleForm.travelphoto ? ruleForm.travelphoto : ''"
						@change="travelphotoUploadChange"></file-upload>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' class="upload"
					v-else-if="ruleForm.travelphoto" label="图片" prop="travelphoto">
					<img v-if="ruleForm.travelphoto.substring(0, 4) == 'http'" class="upload-img" style="margin-right:20px;"
						v-bind:key="index" :src="ruleForm.travelphoto.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="item"
						v-for="(item, index) in ruleForm.travelphoto.split(',')" :src="$base.url + item" width="100"
						height="100">
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' class="input" v-if="type != 'info'" label="账号"
					prop="username">
					<el-input v-model="ruleForm.username" placeholder="账号" clearable :readonly="ro.username"></el-input>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' v-else class="input" label="账号"
					prop="username">
					<el-input v-model="ruleForm.username" placeholder="账号" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' class="input" v-if="type != 'info'" label="姓名"
					prop="name">
					<el-input v-model="ruleForm.name" placeholder="姓名" clearable :readonly="ro.name"></el-input>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' v-else class="input" label="姓名" prop="name">
					<el-input v-model="ruleForm.name" placeholder="姓名" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' class="textarea" v-if="type != 'info'"
				label="简介" prop="content">
				<el-input style="min-width: 200px; max-width: 600px;" type="textarea" :rows="8" placeholder="简介"
					v-model="ruleForm.content">
				</el-input>
			</el-form-item>
			<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' v-else-if="ruleForm.content" label="简介"
				prop="content">
				<span
					:style='{ "fontSize": "14px", "lineHeight": "40px", "color": "#000", "fontWeight": "500", "display": "inline-block" }'>{{
						ruleForm.content }}</span>
			</el-form-item>
			<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' v-if="type != 'info'" label="内容"
				prop="introduction">
				<editor style="min-width: 200px; max-width: 600px;" v-model="ruleForm.introduction" class="editor"
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' v-else-if="ruleForm.introduction" label="内容"
				prop="introduction">
				<span
					:style='{ "fontSize": "14px", "lineHeight": "40px", "color": "#000", "fontWeight": "500", "display": "inline-block" }'
					v-html="ruleForm.introduction"></span>
			</el-form-item>
			<el-form-item :style='{ "width": "100%", "padding": "0", "margin": "0" }' class="btn">
				<el-button
					:style='{ "border": "0", "cursor": "pointer", "padding": "0", "margin": "0 20px 0 0", "outline": "none", "color": "rgba(255, 255, 255, 1)", "borderRadius": "0", "background": "#BAD399", "width": "128px", "lineHeight": "40px", "fontSize": "14px", "height": "40px" }'
					v-if="type != 'info'" type="primary" class="btn-success" @click="onSubmit">提交</el-button>
				<el-button
					:style='{ "border": "0px solid rgba(64, 158, 255, 1)", "cursor": "pointer", "padding": "0", "margin": "0", "outline": "none", "color": "#fff", "borderRadius": "0", "background": "#BAD399", "width": "128px", "lineHeight": "40px", "fontSize": "14px", "height": "40px" }'
					v-if="type != 'info'" class="btn-close" @click="back()">取消</el-button>
				<el-button
					:style='{ "border": "0px solid rgba(64, 158, 255, 1)", "cursor": "pointer", "padding": "0", "margin": "0", "outline": "none", "color": "#fff", "borderRadius": "0", "background": "#BAD399", "width": "128px", "lineHeight": "40px", "fontSize": "14px", "height": "40px" }'
					v-if="type == 'info'" class="btn-close" @click="back()">返回</el-button>
			</el-form-item>
		</el-form>


	</div>
</template>
<script>
// 数字，邮件，手机，url，身份证校验
import { isNumber, isIntNumer, isEmail, isPhone, isMobile, isURL, checkIdCard } from "@/utils/validate";
export default {
	data() {
		let self = this
		var validateIdCard = (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (!checkIdCard(value)) {
				callback(new Error("请输入正确的身份证号码"));
			} else {
				callback();
			}
		};
		var validateUrl = (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (!isURL(value)) {
				callback(new Error("请输入正确的URL地址"));
			} else {
				callback();
			}
		};
		var validateMobile = (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (!isMobile(value)) {
				callback(new Error("请输入正确的手机号码"));
			} else {
				callback();
			}
		};
		var validatePhone = (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (!isPhone(value)) {
				callback(new Error("请输入正确的电话号码"));
			} else {
				callback();
			}
		};
		var validateEmail = (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (!isEmail(value)) {
				callback(new Error("请输入正确的邮箱地址"));
			} else {
				callback();
			}
		};
		var validateNumber = (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (!isNumber(value)) {
				callback(new Error("请输入数字"));
			} else {
				callback();
			}
		};
		var validateIntNumber = (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (!isIntNumer(value)) {
				callback(new Error("请输入整数"));
			} else {
				callback();
			}
		};
		return {
			id: '',
			type: '',


			ro: {
				address: false,
				traveltype: false,
				duration: false,
				introduction: false,
				travelphoto: false,
				username: false,
				name: false,
				thumbsupnum: false,
				crazilynum: false,
				content: false,
			},


			ruleForm: {
				address: '',
				traveltype: '',
				duration: '',
				introduction: '',
				travelphoto: '',
				username: '',
				name: '',
				content: '',
			},

			traveltypeOptions: [],

			rules: {
				address: [
				],
				traveltype: [
				],
				duration: [

				],
				introduction: [
				],
				travelphoto: [
				],
				username: [
				],
				name: [
				],
				thumbsupnum: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				crazilynum: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				content: [
				],
			}
		};
	},
	props: ["parent"],
	computed: {



	},
	created() {
	},
	methods: {

		// 下载
		download(file) {
			window.open(`${file}`)
		},
		// 初始化
		init(id, type) {
			if (id) {
				this.id = id;
				this.type = type;
			}
			if (this.type == 'info' || this.type == 'else') {
				this.info(id);
			} else if (this.type == 'logistics') {
				this.logistics = false;
				this.info(id);
			} else if (this.type == 'cross') {
				var obj = this.$storage.getObj('crossObj');
				for (var o in obj) {
					if (o == 'address') {
						this.ruleForm.address = obj[o];
						this.ro.address = true;
						continue;
					}
					if (o == 'traveltype') {
						this.ruleForm.traveltype = obj[o];
						this.ro.traveltype = true;
						continue;
					}
					if (o == 'duration') {
						this.ruleForm.duration = obj[o];
						this.ro.duration = true;
						continue;
					}
					if (o == 'introduction') {
						this.ruleForm.introduction = obj[o];
						this.ro.introduction = true;
						continue;
					}
					if (o == 'travelphoto') {
						this.ruleForm.travelphoto = obj[o];
						this.ro.travelphoto = true;
						continue;
					}
					if (o == 'username') {
						this.ruleForm.username = obj[o];
						this.ro.username = true;
						continue;
					}
					if (o == 'name') {
						this.ruleForm.name = obj[o];
						this.ro.name = true;
						continue;
					}
					if (o == 'thumbsupnum') {
						this.ruleForm.thumbsupnum = obj[o];
						this.ro.thumbsupnum = true;
						continue;
					}
					if (o == 'crazilynum') {
						this.ruleForm.crazilynum = obj[o];
						this.ro.crazilynum = true;
						continue;
					}
					if (o == 'content') {
						this.ruleForm.content = obj[o];
						this.ro.content = true;
						continue;
					}
				}










			}


			this.$http({
				url: "dictionary/page",
				method: "get",
				params: {
					dicCode: 'traveltype',
					page: 1,
					limit: 1000  // 适当设置较大的数量，确保获取所有商家
				}
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.traveltypeList = data.data.list || [];
				} else {
					this.$message.error(data.msg || '获取攻略类型列表失败');
				}
			}).catch(err => {
				console.error('获取攻略类型列表出错:', err);
				this.$message.error('获取攻略类型列表失败');
			});
			// 获取用户信息
			this.$http({
				url: `${this.$storage.get('sessionTable')}/session`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {

					var json = data.data;
					if (((json.username != '' && json.username) || json.username == 0) && this.$storage.get("role") != "管理员") {
						this.ruleForm.username = json.username
						this.ro.username = true;
					}
					if (((json.name != '' && json.name) || json.name == 0) && this.$storage.get("role") != "管理员") {
						this.ruleForm.name = json.name
						this.ro.name = true;
					}
				} else {
					this.$message.error(data.msg);
				}
			});

			this.traveltypeOptions = '旅游攻略,游玩攻略,景点分享,美食推荐,地区特色'.split(',');

		},
		// 多级联动参数

		info(id) {
			this.$http({
				url: `travelguide/info/${id}`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.ruleForm = data.data;
					//解决前台上传图片后台不显示的问题
					let reg = new RegExp('../../../upload', 'g')//g代表全部
					this.ruleForm.introduction = this.ruleForm.introduction.replace(reg, '../../../inttourismsys/upload');
				} else {
					this.$message.error(data.msg);
				}
			});
		},


		// 提交
		onSubmit() {










			if (this.ruleForm.travelphoto != null) {
				this.ruleForm.travelphoto = this.ruleForm.travelphoto.replace(new RegExp(this.$base.url, "g"), "");
			}









			var objcross = this.$storage.getObj('crossObj');

			//更新跨表属性
			var crossuserid;
			var crossrefid;
			var crossoptnum;
			if (this.type == 'cross') {
				var statusColumnName = this.$storage.get('statusColumnName');
				var statusColumnValue = this.$storage.get('statusColumnValue');
				if (statusColumnName != '') {
					var obj = this.$storage.getObj('crossObj');
					if (statusColumnName && !statusColumnName.startsWith("[")) {
						for (var o in obj) {
							if (o == statusColumnName) {
								obj[o] = statusColumnValue;
							}
						}
						var table = this.$storage.get('crossTable');
						this.$http({
							url: `${table}/update`,
							method: "post",
							data: obj
						}).then(({ data }) => { });
					} else {
						crossuserid = this.$storage.get('userid');
						crossrefid = obj['id'];
						crossoptnum = this.$storage.get('statusColumnName');
						crossoptnum = crossoptnum.replace(/\[/, "").replace(/\]/, "");
					}
				}
			}
			this.$refs["ruleForm"].validate(valid => {
				if (valid) {
					if (crossrefid && crossuserid) {
						this.ruleForm.crossuserid = crossuserid;
						this.ruleForm.crossrefid = crossrefid;
						let params = {
							page: 1,
							limit: 10,
							crossuserid: this.ruleForm.crossuserid,
							crossrefid: this.ruleForm.crossrefid,
						}
						this.$http({
							url: "travelguide/page",
							method: "get",
							params: params
						}).then(({
							data
						}) => {
							if (data && data.code === 0) {
								if (data.data.total >= crossoptnum) {
									this.$message.error(this.$storage.get('tips'));
									return false;
								} else {
									this.$http({
										url: `travelguide/${!this.ruleForm.id ? "save" : "update"}`,
										method: "post",
										data: this.ruleForm
									}).then(({ data }) => {
										if (data && data.code === 0) {
											this.$message({
												message: "操作成功",
												type: "success",
												duration: 1500,
												onClose: () => {
													this.parent.showFlag = true;
													this.parent.addOrUpdateFlag = false;
													this.parent.travelguideCrossAddOrUpdateFlag = false;
													this.parent.search();
													this.parent.contentStyleChange();
												}
											});
										} else {
											this.$message.error(data.msg);
										}
									});

								}
							} else {
							}
						});
					} else {
						this.$http({
							url: `travelguide/${!this.ruleForm.id ? "save" : "update"}`,
							method: "post",
							data: this.ruleForm
						}).then(({ data }) => {
							if (data && data.code === 0) {
								this.$message({
									message: "操作成功",
									type: "success",
									duration: 1500,
									onClose: () => {
										this.parent.showFlag = true;
										this.parent.addOrUpdateFlag = false;
										this.parent.travelguideCrossAddOrUpdateFlag = false;
										this.parent.search();
										this.parent.contentStyleChange();
									}
								});
							} else {
								this.$message.error(data.msg);
							}
						});
					}
				}
			});
		},
		// 获取uuid
		getUUID() {
			return new Date().getTime();
		},
		// 返回
		back() {
			this.parent.showFlag = true;
			this.parent.addOrUpdateFlag = false;
			this.parent.travelguideCrossAddOrUpdateFlag = false;
			this.parent.contentStyleChange();
		},
		travelphotoUploadChange(fileUrls) {
			this.ruleForm.travelphoto = fileUrls;
		},
	}
};
</script>
<style lang="scss" scoped>
.amap-wrapper {
	width: 100%;
	height: 500px;
}

.search-box {
	position: absolute;
}

.el-date-editor.el-input {
	width: auto;
}

.add-update-preview .el-form-item ::V-deep .el-form-item__label {
	padding: 0 10px 0 0;
	color: #666;
	font-weight: 500;
	width: 120px;
	font-size: 14px;
	line-height: 40px;
	text-align: center;
}

.add-update-preview .el-form-item ::V-deep .el-form-item__content {
	margin-left: 120px;
}

.add-update-preview .el-input ::V-deep .el-input__inner {
	border: 2px solid #797979;
	border-radius: 0;
	padding: 0 12px;
	box-shadow: 0 0 0px rgba(64, 158, 255, .5);
	outline: none;
	color: #000;
	width: 400px;
	font-size: 14px;
	height: 40px;
}

.add-update-preview .el-select ::V-deep .el-input__inner {
	border: 2px solid #797979;
	border-radius: 0;
	padding: 0 10px;
	box-shadow: 0 0 0px rgba(64, 158, 255, .5);
	outline: none;
	color: #000;
	width: 300px;
	font-size: 14px;
	height: 40px;
}

.add-update-preview .el-date-editor ::V-deep .el-input__inner {
	border: 2px solid #797979;
	border-radius: 0;
	padding: 0 10px 0 30px;
	box-shadow: 0 0 0px rgba(64, 158, 255, .5);
	outline: none;
	color: #000;
	width: 300px;
	font-size: 14px;
	height: 40px;
}

.add-update-preview ::V-deep .el-upload--picture-card {
	background: transparent;
	border: 0;
	border-radius: 0;
	width: auto;
	height: auto;
	line-height: initial;
	vertical-align: middle;
}

.add-update-preview ::V-deep .upload .upload-img {
	border: 2px solid #797979;
	cursor: pointer;
	border-radius: 6px;
	color: #797979;
	width: 90px;
	font-size: 32px;
	line-height: 90px;
	text-align: center;
	height: 90px;
}

.add-update-preview ::V-deep .el-upload-list .el-upload-list__item {
	border: 2px solid #797979;
	cursor: pointer;
	border-radius: 6px;
	color: #797979;
	width: 90px;
	font-size: 32px;
	line-height: 90px;
	text-align: center;
	height: 90px;
}

.add-update-preview ::V-deep .el-upload .el-icon-plus {
	border: 2px solid #797979;
	cursor: pointer;
	border-radius: 6px;
	color: #797979;
	width: 90px;
	font-size: 32px;
	line-height: 90px;
	text-align: center;
	height: 90px;
}

.add-update-preview .el-textarea ::V-deep .el-textarea__inner {
	border: 2px solid #797979;
	border-radius: 0;
	padding: 12px;
	box-shadow: 0 0 0px rgba(64, 158, 255, .5);
	outline: none;
	color: #000;
	width: 400px;
	font-size: 14px;
	height: 120px;
}</style>
