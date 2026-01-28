<template>
  <div>
    <el-form
      :style='{"padding":"30px","boxShadow":"0 0px 0px rgba(64, 158, 255, .3)","margin":"-30px 0 0","borderRadius":"0 0 30px 30px","flexWrap":"wrap","background":"#F8F6F7","display":"flex","width":"100%"}'
      class="add-update-preview"
      ref="ruleForm"
      :model="ruleForm"
      label-width="120px"
    >  
     <el-row>
        <!-- 用户相关表单项 -->
        <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='users'" label="账号" prop="username">
          <el-input v-model="ruleForm.username" readonly placeholder="账号" clearable></el-input>
        </el-form-item>
        <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='users'" label="姓名" prop="name">
          <el-input v-model="ruleForm.name" placeholder="姓名" clearable></el-input>
        </el-form-item>
        <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='users'" label="性别" prop="gender">
          <el-select v-model="ruleForm.gender" placeholder="请选择性别">
            <el-option
                v-for="(item,index) in usersgenderOptions"
                v-bind:key="index"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='users'" label="年龄" prop="age">
          <el-input v-model="ruleForm.age" placeholder="年龄" clearable></el-input>
        </el-form-item>
        <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='users'" label="手机" prop="telephone">
          <el-input v-model="ruleForm.telephone" placeholder="手机" clearable></el-input>
        </el-form-item>
        <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='users'" label="头像" prop="avatar">
          <file-upload
            tip="点击上传头像"
            action="file/upload"
            :limit="3"
            :multiple="true"
            :fileUrls="ruleForm.avatar?ruleForm.avatar:''"
            @change="usersavatarUploadChange"
          ></file-upload>
        </el-form-item>

        <!-- 管理员相关表单项 -->
        <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='admin'" label="用户名" prop="username">
          <el-input v-model="ruleForm.username" placeholder="用户名"></el-input>
        </el-form-item>

        <!-- 商家相关表单项（新增） -->
        <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='merchant'" label="商家账号" prop="merchantusername">
          <el-input v-model="ruleForm.merchantusername" readonly placeholder="商家账号" clearable></el-input>
        </el-form-item>
        <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='merchant'" label="密码" prop="password">
          <el-input v-model="ruleForm.password" type="password" placeholder="密码" clearable></el-input>
        </el-form-item>
        <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='merchant'" label="商家名称" prop="merchantname">
          <el-input v-model="ruleForm.merchantname" placeholder="商家名称" clearable></el-input>
        </el-form-item>
        <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='merchant'" label="商家地址" prop="merchantaddress">
          <el-input v-model="ruleForm.merchantaddress" placeholder="商家地址" clearable></el-input>
        </el-form-item>
        <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='merchant'" label="邮箱" prop="email">
          <el-input v-model="ruleForm.email" placeholder="邮箱" clearable></el-input>
        </el-form-item>
        <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='merchant'" label="联系手机" prop="phonenumber">
          <el-input v-model="ruleForm.phonenumber" placeholder="联系手机" clearable></el-input>
        </el-form-item>
        <el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='merchant'" label="营业执照" prop="merchantlicense">
          <file-upload
            tip="点击上传营业执照"
            action="file/upload"
            :limit="1"  
            :multiple="false"
            :fileUrls="ruleForm.merchantlicense?ruleForm.merchantlicense:''"
            @change="merchantlicenseUploadChange"
          ></file-upload>
        </el-form-item>

        <!-- 提交按钮 -->
        <el-form-item :style='{"width":"100%","padding":"0","margin":"0"}'>
          <el-button :style='{"border":"0","cursor":"pointer","padding":"0","margin":"0 20px 0 0","outline":"none","color":"rgba(255, 255, 255, 1)","borderRadius":"0","background":"#BAD399","width":"128px","lineHeight":"40px","fontSize":"14px","height":"40px"}' type="primary" @click="onUpdateHandler">修 改</el-button>
        </el-form-item>
      </el-row>
    </el-form>
  </div>
</template>

<script>
// 数字，邮件，手机，url，身份证校验
import { isNumber,isIntNumer,isEmail,isMobile,isPhone,isURL,checkIdCard } from "@/utils/validate";

export default {
  data() {
    return {
      ruleForm: {},
      flag: '',
      adminFlag: false,
      usersgenderOptions: [],
    };
  },
  mounted() {
    var table = this.$storage.get("sessionTable");
    this.flag = table;
    // 请求当前会话数据（兼容merchant）
    this.$http({
      url: `${this.$storage.get("sessionTable")}/session`,
      method: "get"
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.ruleForm = data.data;
      } else {
        this.$message.error(data.msg);
      }
    });
    this.usersgenderOptions = "男,女".split(',')
  },
  methods: {
    // 用户头像上传处理
    usersavatarUploadChange(fileUrls) {
      this.ruleForm.avatar = fileUrls;
    },
    // 商家营业执照上传处理（新增）
    merchantlicenseUploadChange(fileUrls) {
      this.ruleForm.merchantlicense = fileUrls;
    },
    // 修改提交处理
    onUpdateHandler() {
      // ========== 用户(users) 校验 ==========
      if (this.flag === 'users') {
        if (!this.ruleForm.username) {
          this.$message.error('账号不能为空');
          return;
        }
        if (!this.ruleForm.password) {
          this.$message.error('密码不能为空');
          return;
        }
        if (!this.ruleForm.name) {
          this.$message.error('姓名不能为空');
          return;
        }
        if (this.ruleForm.age && !isIntNumer(this.ruleForm.age)) {
          this.$message.error('年龄应输入整数');
          return;
        }
        if (this.ruleForm.telephone && !isMobile(this.ruleForm.telephone)) {
          this.$message.error('手机格式不正确');
          return;
        }
        // 头像URL处理
        if (this.ruleForm.avatar != null) {
          this.ruleForm.avatar = this.ruleForm.avatar.replace(new RegExp(this.$base.url, "g"), "");
        }
      }

      // ========== 管理员(admin) 校验 ==========
      if (this.flag === 'admin') {
        if (!this.ruleForm.username || this.ruleForm.username.trim().length < 1) {
          this.$message.error('用户名不能为空');
          return;
        }
      }

      // ========== 商家(merchant) 校验（新增） ==========
      if (this.flag === 'merchant') {
        // 必输项校验
        if (!this.ruleForm.merchantusername) {
          this.$message.error('商家账号不能为空');
          return;
        }
        if (!this.ruleForm.password) {
          this.$message.error('密码不能为空');
          return;
        }
        if (!this.ruleForm.merchantname) {
          this.$message.error('商家名称不能为空');
          return;
        }
        // 格式校验（选填项有值时校验）
        if (this.ruleForm.email && !isEmail(this.ruleForm.email)) {
          this.$message.error('邮箱格式不正确');
          return;
        }
        if (this.ruleForm.phonenumber && !isMobile(this.ruleForm.phonenumber)) {
          this.$message.error('联系手机格式不正确');
          return;
        }
        // 营业执照URL处理
        if (this.ruleForm.merchantlicense != null) {
          this.ruleForm.merchantlicense = this.ruleForm.merchantlicense.replace(new RegExp(this.$base.url, "g"), "");
        }
      }

      // ========== 通用提交逻辑 ==========
      this.$http({
        url: `${this.$storage.get("sessionTable")}/update`,
        method: "post",
        data: this.ruleForm
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "修改信息成功",
            type: "success",
            duration: 1500,
            onClose: () => {
              // 可添加修改成功后的回调，比如刷新页面/关闭弹窗等
            }
          });
        } else {
          this.$message.error(data.msg);
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.el-date-editor.el-input {
  width: auto;
}

.add-update-preview .el-form-item ::v-deep .el-form-item__label {
  padding: 0 10px 0 0;
  color: #666;
  font-weight: 500;
  width: 120px;
  font-size: 14px;
  line-height: 40px;
  text-align: center;
}

.add-update-preview .el-form-item ::v-deep .el-form-item__content {
  margin-left: 120px;
}

.add-update-preview .el-input ::v-deep .el-input__inner {
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

.add-update-preview .el-select ::v-deep .el-input__inner {
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

.add-update-preview .el-date-editor ::v-deep .el-input__inner {
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

.add-update-preview ::v-deep .el-upload--picture-card {
  background: transparent;
  border: 0;
  border-radius: 0;
  width: auto;
  height: auto;
  line-height: initial;
  vertical-align: middle;
}

.add-update-preview ::v-deep .el-upload-list .el-upload-list__item {
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

.add-update-preview ::v-deep .el-upload .el-icon-plus {
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

.add-update-preview .el-textarea ::v-deep .el-textarea__inner {
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