<template>
	<div>

	<div class="container" :style='{"minHeight":"100%","backgroundAttachment": "fixed","alignItems":"center","backgroundImage":"none","backgroundColor":"#fff8dc","display":"flex","width":"100%","backgroundSize":"cover","backgroundPosition":"center center","backgroundRepeat":"no-repeat","justifyContent":"center"}'>
		<el-form class='rgs-form' v-if="pageFlag=='register'" :style='{"padding":"20px","borderRadius":"10px","background":"#fff","width":"750px","height":"auto"}' ref="registerForm" :model="registerForm" :rules="rules">
			<div v-if="true" :style='{"width":"100%","margin":"30px 0 20px 0","lineHeight":"44px","fontSize":"36px","color":"#bad399","textAlign":"center"}'>智能旅游推荐系统注册</div>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='users'" prop="username">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"#bad399"}'>账号：</div>
				<el-input v-model="registerForm.username"  placeholder="请输入账号" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='users'" prop="password">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"#bad399"}'>密码：</div>
				<el-input v-model="registerForm.password" type="password" placeholder="请输入密码" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='users'" prop="password">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"#bad399"}'>确认密码：</div>
				<el-input v-model="registerForm.password2" type="password" placeholder="请再次输入密码" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='users'" prop="name">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"#bad399"}'>姓名：</div>
				<el-input v-model="registerForm.name"  placeholder="请输入姓名" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='users'" prop="gender">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"#bad399"}'>性别：</div>
                <el-select v-model="registerForm.gender" placeholder="请选择性别" >
                  <el-option
                      v-for="(item,index) in usersgenderOptions"
                      :key="index"
                      :label="item"
                      :value="item">
                  </el-option>
                </el-select>
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='users'" prop="age">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"#bad399"}'>年龄：</div>
				<el-input v-model="registerForm.age"  placeholder="请输入年龄" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='users'" prop="telephone">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"#bad399"}'>手机：</div>
				<el-input v-model="registerForm.telephone"  placeholder="请输入手机" />
			</el-form-item>
			<el-form-item :style='{"width":"80%","margin":"0 auto 24px"}' v-if="tableName=='users'" prop="avatar">
				<div v-if="false" :style='{"width":"64px","lineHeight":"44px","fontSize":"14px","color":"#bad399"}'>头像：</div>
                <file-upload
					tip="点击上传头像"
					action="file/upload"
					:limit="1"
					:multiple="true"
					:fileUrls="registerForm.avatar?registerForm.avatar:''"
					@change="usersavatarUploadChange"
				></file-upload>
			</el-form-item>
			<el-button :style='{"border":"none","cursor":"pointer","padding":"0 10px","margin":"20px auto 5px","outline":"none","color":"#fff","borderRadius":"8px","background":"#bad399","display":"block","width":"80%","fontSize":"16px","height":"40px"}' type="primary" @click="submitForm('registerForm')">注册</el-button>
			<el-button :style='{"cursor":"pointer","border":"1px solid rgba(204, 204, 204, 1)","padding":"0 10px","margin":"20px auto 5px","outline":"none","color":"#000","borderRadius":"8px","background":"none","display":"block","width":"80%","fontSize":"16px","height":"40px"}' @click="resetForm('registerForm')">重置</el-button>
			<router-link :style='{"cursor":"pointer","padding":"0 10%","color":"rgba(40, 40, 40, 1)","display":"inline-block","lineHeight":"1.5","fontSize":"12px","textDecoration":"none"}' to="/login">已有账户登录</router-link>
		</el-form>
    </div>

</div>
</template>

<script>

export default {
    //数据集合
    data() {
		return {
            pageFlag : '',
			tableName: '',
			registerForm: {
                gender: '',
            },
			rules: {},
            usersgenderOptions: [],
		}
    },
	mounted() {
	},
    created() {
      this.pageFlag = this.$route.query.pageFlag;
      this.tableName = this.$route.query.role;
      if ('users' == this.tableName) {
        this.rules.username = [{ required: true, message: '请输入账号', trigger: 'blur' }];
      }
      if ('users' == this.tableName) {
        this.rules.password = [{ required: true, message: '请输入密码', trigger: 'blur' }];
      }
      if ('users' == this.tableName) {
        this.rules.name = [{ required: true, message: '请输入姓名', trigger: 'blur' }];
      }
        this.usersgenderOptions = "男,女".split(',');
      if ('users' == this.tableName) {
        this.rules.age = [{ required: true, validator: this.$validate.isIntNumer, trigger: 'blur' }];
      }
      if ('users' == this.tableName) {
        this.rules.telephone = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
      }
    },
    //方法集合
    methods: {
      // 获取uuid
      getUUID () {
        return new Date().getTime();
      },
        // 下二随
      usersavatarUploadChange(fileUrls) {
          this.registerForm.avatar = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
      },

        // 多级联动参数


      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            var url=this.tableName+"/register";
               if(`users` == this.tableName && this.registerForm.password!=this.registerForm.password2) {
                this.$message.error(`两次密码输入不一致`);
                return
               }
            this.$http.post(url, this.registerForm).then(res => {
              if (res.data.code === 0) {
                this.$message({
                  message: '注册成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.$router.push('/login');
                  }
                });
              } else {
                this.$message.error(res.data.msg);
              }
            });
          } else {
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.container {
		position: relative;

		.el-date-editor.el-input {
			width: 100%;
		}
		
		.rgs-form .el-input ::v-deep .el-input__inner {
						border: 1px solid rgba(204, 204, 204, 1);
						border-radius: 8px;
						padding: 0 10px;
						outline: none;
						color: rgba(153, 153, 153, 1);
						width: 100%;
						font-size: 14px;
						height: 40px;
					}
		
		.rgs-form .el-select ::v-deep .el-input__inner {
						border: 1px solid rgba(204, 204, 204, 1);
						border-radius: 8px;
						padding: 0 10px;
						outline: none;
						color:  rgba(153, 153, 153, 1);
						width: 288px;
						font-size: 14px;
						height: 40px;
					}
		
		.rgs-form .el-date-editor ::v-deep .el-input__inner {
						border: 1px solid rgba(204, 204, 204, 1);
						border-radius: 8px;
						padding: 0 10px 0 30px;
						outline: none;
						color: rgba(153, 153, 153, 1);
						width: 288px;
						font-size: 14px;
						height: 40px;
					}
		
		.rgs-form .el-date-editor ::v-deep .el-input__inner {
						border: 1px solid rgba(204, 204, 204, 1);
						border-radius: 8px;
						padding: 0 10px 0 30px;
						outline: none;
						color: rgba(153, 153, 153, 1);
						width: 288px;
						font-size: 14px;
						height: 40px;
					}
		
		.rgs-form ::v-deep .el-upload--picture-card {
			background: transparent;
			border: 0;
			border-radius: 0;
			width: auto;
			height: auto;
			line-height: initial;
			vertical-align: middle;
		}
		
		.rgs-form ::v-deep .upload .upload-img {
		  		  border: 1px solid rgba(204, 204, 204, 1);
		  		  cursor: pointer;
		  		  border-radius: 8px;
		  		  color: rgba(153, 153, 153, 1);
		  		  width: 160px;
		  		  font-size: 32px;
		  		  line-height: 100px;
		  		  text-align: center;
		  		  height: 100px;
		  		}
		
		.rgs-form ::v-deep .el-upload-list .el-upload-list__item {
		  		  border: 1px solid rgba(204, 204, 204, 1);
		  		  cursor: pointer;
		  		  border-radius: 8px;
		  		  color: rgba(153, 153, 153, 1);
		  		  width: 160px;
		  		  font-size: 32px;
		  		  line-height: 100px;
		  		  text-align: center;
		  		  height: 100px;
		  		}
		
		.rgs-form ::v-deep .el-upload .el-icon-plus {
		  		  border: 1px solid rgba(204, 204, 204, 1);
		  		  cursor: pointer;
		  		  border-radius: 8px;
		  		  color: rgba(153, 153, 153, 1);
		  		  width: 160px;
		  		  font-size: 32px;
		  		  line-height: 100px;
		  		  text-align: center;
		  		  height: 100px;
		  		}
	}
</style>
