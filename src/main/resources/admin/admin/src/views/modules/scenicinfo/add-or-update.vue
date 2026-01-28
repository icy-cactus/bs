<template>
	<div class="addEdit-block" style="width: 100%;">
		<el-form
			:style='{ "padding": "30px", "boxShadow": "0 0px 0px rgba(64, 158, 255, .3)", "margin": "-30px 0 0", "borderRadius": "0 0 30px 30px", "flexWrap": "wrap", "background": "#F8F6F7", "display": "flex", "width": "100%" }'
			class="add-update-preview" ref="ruleForm" :model="ruleForm" :rules="rules" label-width="120px">
			<template>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' class="input" v-if="type != 'info'"
					label="景点名称" prop="scenicname">
					<el-input v-model="ruleForm.scenicname" placeholder="景点名称" clearable
						:readonly="ro.scenicname"></el-input>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' v-else class="input" label="景点名称"
					prop="scenicname">
					<el-input v-model="ruleForm.scenicname" placeholder="景点名称" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' class="input" v-if="type != 'info'"
					label="景点地址" prop="address">
					<el-input v-model="ruleForm.address" placeholder="景点地址" clearable :readonly="ro.address"></el-input>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' v-else class="input" label="景点地址"
					prop="address">
					<el-input v-model="ruleForm.address" placeholder="景点地址" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' class="select" v-if="type != 'info'"
					label="景点等级" prop="sceniclevel">
					<el-select :disabled="ro.sceniclevel" v-model="ruleForm.sceniclevel" placeholder="请选择景点等级">
						<el-option v-for="(item, index) in sceniclevelOptions" v-bind:key="index" :label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' v-else class="input" label="景点等级"
					prop="sceniclevel">
					<el-input v-model="ruleForm.sceniclevel" placeholder="景点等级" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' class="input" v-if="type != 'info'"
					label="门票价格" prop="price">
					<el-input v-model="ruleForm.price" placeholder="门票价格" clearable :readonly="ro.price"></el-input>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' v-else class="input" label="门票价格"
					prop="price">
					<el-input v-model="ruleForm.price" placeholder="门票价格" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' class="input" v-if="type != 'info'"
					label="景点电话" prop="telephone">
					<el-input v-model="ruleForm.telephone" placeholder="景点电话" clearable
						:readonly="ro.telephone"></el-input>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' v-else class="input" label="景点电话"
					prop="telephone">
					<el-input v-model="ruleForm.telephone" placeholder="景点电话" readonly></el-input>
				</el-form-item>

				<el-form-item :style='{ "width": "45%", "margin": "0 2% 20px 0", "display": "inline-block" }'
					class="input" v-if="type != 'info'" label="商家账号" prop="merchantusername">
					<el-input v-model="ruleForm.merchantusername" placeholder="商家账号" clearable
						:readonly="ro.merchantusername || isFromSelect"></el-input>
				</el-form-item>
				<el-form-item :style='{ "width": "45%", "margin": "0 2% 20px 0", "display": "inline-block" }' v-else
					class="input" label="商家账号" prop="merchantusername">
					<el-input v-model="ruleForm.merchantusername" placeholder="商家账号" readonly></el-input>
				</el-form-item>

				<!-- 商家名称改为下拉框 -->
				<el-form-item :style='{ "width": "45%", "margin": "0 2% 20px 0", "display": "inline-block" }'
					class="input" v-if="type != 'info'" label="商家名称" prop="merchantname">
					<el-select v-model="ruleForm.merchantname" placeholder="请选择商家" clearable :disabled="ro.merchantname"
						@change="handleMerchantChange" filterable>
						<el-option v-for="merchant in merchantList" :key="merchant.id" :label="merchant.merchantname"
							:value="merchant.merchantname"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{ "width": "45%", "margin": "0 2% 20px 0", "display": "inline-block" }' v-else
					class="input" label="商家名称" prop="merchantname">
					<el-input v-model="ruleForm.merchantname" placeholder="商家名称" readonly></el-input>
				</el-form-item>

				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' class="upload"
					v-if="type != 'info' && !ro.scenicphoto" label="景点图片" prop="scenicphoto">
					<file-upload tip="点击上传景点图片" action="file/upload" :limit="3" :multiple="true"
						:fileUrls="ruleForm.scenicphoto ? ruleForm.scenicphoto : ''"
						@change="scenicphotoUploadChange"></file-upload>
				</el-form-item>
				<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' class="upload"
					v-else-if="ruleForm.scenicphoto" label="景点图片" prop="scenicphoto">
					<img v-if="ruleForm.scenicphoto.substring(0, 4) == 'http'" class="upload-img"
						style="margin-right:20px;" v-bind:key="index" :src="ruleForm.scenicphoto.split(',')[0]"
						width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index"
						v-for="(item, index) in ruleForm.scenicphoto.split(',')" :src="$base.url + item" width="100"
						height="100">
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
			<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' v-if="type != 'info'" label="景点介绍"
				prop="introduce">
				<editor style="min-width: 200px; max-width: 600px;" v-model="ruleForm.introduce" class="editor"
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item :style='{ "width": "100%", "margin": "0 0 20px 0" }' v-else-if="ruleForm.introduce"
				label="景点介绍" prop="introduce">
				<span
					:style='{ "fontSize": "14px", "lineHeight": "40px", "color": "#000", "fontWeight": "500", "display": "inline-block" }'
					v-html="ruleForm.introduce"></span>
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
				scenicname: false,
				address: false,
				sceniclevel: false,
				price: false,
				telephone: false,
				introduce: false,
				scenicphoto: false,
				thumbsupnum: false,
				crazilynum: false,
				clicktime: false,
				clicknum: false,
				content: false,
				merchantname: false,
				merchantusername: false,
			},


			ruleForm: {
				scenicname: '',
				address: '',
				sceniclevel: '',
				price: '',
				telephone: '',
				introduce: '',
				scenicphoto: '',
				clicktime: '',
				clicknum: '',
				content: '',
				merchantname: '',
				merchantusername: '',
			},

			sceniclevelOptions: [],

			rules: {
				scenicname: [
				],
				address: [
				],
				sceniclevel: [
				],
				price: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				telephone: [
				],
				introduce: [
				],
				scenicphoto: [
				],
				thumbsupnum: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				crazilynum: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				clicktime: [
				],
				clicknum: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				content: [
				],
				merchantname: [
				],
				merchantusername: [
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
		handleMerchantChange(merchantname) {
			if (!merchantname) {
				// 如果清空选择，重置相关字段和标记
				this.isFromSelect = false;
				return;
			}

			// 查找选中的商家信息
			const selectedMerchant = this.merchantList.find(
				(item) => item.merchantname === merchantname
			);

			if (selectedMerchant) {
				// 同步更新其他字段
				this.ruleForm.merchantusername = selectedMerchant.merchantusername;
				this.ruleForm.merchantaddress = selectedMerchant.merchantaddress;
				// this.ruleForm.phonenumber = selectedMerchant.phonenumber;

				// 标记为从下拉框选择，设置相关字段为只读
				this.isFromSelect = true;
			}
		},
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
					if (o == 'scenicname') {
						this.ruleForm.scenicname = obj[o];
						this.ro.scenicname = true;
						continue;
					}
					if (o == 'address') {
						this.ruleForm.address = obj[o];
						this.ro.address = true;
						continue;
					}
					if (o == 'sceniclevel') {
						this.ruleForm.sceniclevel = obj[o];
						this.ro.sceniclevel = true;
						continue;
					}
					if (o == 'price') {
						this.ruleForm.price = obj[o];
						this.ro.price = true;
						continue;
					}
					if (o == 'telephone') {
						this.ruleForm.telephone = obj[o];
						this.ro.telephone = true;
						continue;
					}
					if (o == 'introduce') {
						this.ruleForm.introduce = obj[o];
						this.ro.introduce = true;
						continue;
					}
					if (o == 'scenicphoto') {
						this.ruleForm.scenicphoto = obj[o];
						this.ro.scenicphoto = true;
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
					if (o == 'clicktime') {
						this.ruleForm.clicktime = obj[o];
						this.ro.clicktime = true;
						continue;
					}
					if (o == 'clicknum') {
						this.ruleForm.clicknum = obj[o];
						this.ro.clicknum = true;
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
				url: "merchant/page",
				method: "get",
				params: {
					page: 1,
					limit: 1000, // 适当设置较大的数量，确保获取所有商家
				},
			}).then(({ data }) => {
					if (data && data.code === 0) {
						this.merchantList = data.data.list || [];
					} else {
						this.$message.error(data.msg || "获取商家列表失败");
					}
				})
				.catch((err) => {
					console.error("获取商家列表出错:", err);
					this.$message.error("获取商家列表失败");
				});
			// 获取用户信息
			this.$http({
				url: `${this.$storage.get('sessionTable')}/session`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {

					var json = data.data;
				} else {
					this.$message.error(data.msg);
				}
			});

			this.sceniclevelOptions = "A,AA,AAA,AAAA,AAAAA".split(',')

		},
		// 多级联动参数

		info(id) {
			this.$http({
				url: `scenicinfo/info/${id}`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.ruleForm = data.data;
					//解决前台上传图片后台不显示的问题
					let reg = new RegExp('../../../upload', 'g')//g代表全部
					this.ruleForm.introduce = this.ruleForm.introduce.replace(reg, '../../../inttourismsys/upload');
				} else {
					this.$message.error(data.msg);
				}
			});
		},


		// 提交
		onSubmit() {














			if (this.ruleForm.scenicphoto != null) {
				this.ruleForm.scenicphoto = this.ruleForm.scenicphoto.replace(new RegExp(this.$base.url, "g"), "");
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
							url: "scenicinfo/page",
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
										url: `scenicinfo/${!this.ruleForm.id ? "save" : "update"}`,
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
													this.parent.scenicinfoCrossAddOrUpdateFlag = false;
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
							url: `scenicinfo/${!this.ruleForm.id ? "save" : "update"}`,
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
										this.parent.scenicinfoCrossAddOrUpdateFlag = false;
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
			this.parent.scenicinfoCrossAddOrUpdateFlag = false;
			this.parent.contentStyleChange();
		},
		scenicphotoUploadChange(fileUrls) {
			this.ruleForm.scenicphoto = fileUrls;
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
}
</style>
