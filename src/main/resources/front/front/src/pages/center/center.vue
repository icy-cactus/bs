<template>
  <div class="center-preview" :style="{
    width: '1200px',
    margin: '10px auto',
    position: 'relative',
    background: 'none',
    height: 'auto',
  }">
    <div class="title" :style="{
      padding: '20px 0',
      margin: '20px 0 20px 0',
      borderColor: '#FAFFE6',
      color: '#333',
      textAlign: 'center',
      overflow: 'hidden',
      borderRadius: '8px',
      background: 'url(none) #FAFFE6 no-repeat center 10px',
      borderWidth: '1px 1px 4px 1px',
      width: '100%',
      fontSize: '28px',
      lineHeight: '76px',
      borderStyle: 'solid',
      height: '120px',
    }">
      {{ title }}
    </div>

    <el-tabs tab-position="left" :style="{
      border: '2px solid #bad399',
      padding: '20px',
      margin: '20px 0 0 0',
      borderRadius: '8px',
      background: 'rgba(255,255,255,1)',
    }" @tab-click="handleClick">
      <el-tab-pane label="个人中心">
        <el-form class="center-preview-pv" ref="sessionForm" :model="sessionForm" :rules="rules" label-width="80px">
          <el-form-item :style="{ padding: '10px', margin: '0 0 4px', background: 'none' }"
            v-if="userTableName == 'users'" label="账号" prop="username">
            <el-input v-model="sessionForm.username" placeholder="账号" readonly></el-input>
          </el-form-item>
          <el-form-item :style="{ padding: '10px', margin: '0 0 4px', background: 'none' }"
            v-if="userTableName == 'users'" label="密码" prop="password">
            <el-input type="password" v-model="sessionForm.password" placeholder="密码"></el-input>
          </el-form-item>
          <el-form-item :style="{ padding: '10px', margin: '0 0 4px', background: 'none' }"
            v-if="userTableName == 'users'" label="姓名" prop="name">
            <el-input v-model="sessionForm.name" placeholder="姓名"></el-input>
          </el-form-item>
          <el-form-item :style="{ padding: '10px', margin: '0 0 4px', background: 'none' }"
            v-if="userTableName == 'users'" label="性别">
            <el-select v-model="sessionForm.gender" placeholder="请选择性别">
              <el-option v-for="(item, index) in dynamicProp.gender" :key="index" :label="item"
                :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style="{ padding: '10px', margin: '0 0 4px', background: 'none' }"
            v-if="userTableName == 'users'" label="年龄" prop="age">
            <el-input v-model="sessionForm.age" placeholder="年龄"></el-input>
          </el-form-item>
          <el-form-item :style="{ padding: '10px', margin: '0 0 4px', background: 'none' }"
            v-if="userTableName == 'users'" label="手机" prop="telephone">
            <el-input v-model="sessionForm.telephone" placeholder="手机"></el-input>
          </el-form-item>
          <el-form-item :style="{ padding: '10px', margin: '0 0 4px', background: 'none' }"
            v-if="userTableName == 'users'" label="头像">
            <el-upload class="avatar-uploader" :action="uploadUrl" :headers="headers" :show-file-list="false"
              :on-success="usersavatarHandleAvatarSuccess">
              <img :style="{
                border: '1px solid #bad399',
                cursor: 'pointer',
                minHeight: '100px',
                boxShadow: 'inset 0px 0px 48px 0px #FAFFE6',
                color: '#bad399',
                borderRadius: '6px',
                textAlign: 'center',
                width: '150px',
                fontSize: '32px',
                lineHeight: '100px',
                height: 'auto',
              }" v-if="sessionForm.avatar" :src="baseUrl + sessionForm.avatar" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item :style="{ padding: '0', margin: '16px 0 0 0' }">
            <el-button :style="{
              border: '0',
              cursor: 'pointer',
              padding: '0',
              margin: '0 20px 0 0',
              outline: 'none',
              color: '#fff',
              borderRadius: '40px',
              background: '#bad399',
              width: '128px',
              lineHeight: '40px',
              fontSize: '14px',
              height: '40px',
            }" type="primary" @click="onSubmit('sessionForm')">更新信息</el-button>
            <el-button :style="{
              border: '1px solid #bad399',
              cursor: 'pointer',
              padding: '0',
              margin: '0',
              outline: 'none',
              color: '#bad399',
              borderRadius: '40px',
              background: 'rgba(255, 255, 255, 1)',
              width: '128px',
              lineHeight: '40px',
              fontSize: '14px',
              height: '40px',
            }" type="danger" @click="logout">退出登录</el-button>
          </el-form-item>
        </el-form>

        <el-dialog title="用户充值" :visible.sync="dialogFormVisibleMoney" width="726px" center>
          <el-form :model="chongzhiForm">
            <el-form-item label="充值金额" label-width="120px">
              <el-input type="number" v-model="chongzhiForm.money" autocomplete="off" placeholder="充值金额"></el-input>
            </el-form-item>
            <el-form-item label-width="120px">
              <el-radio-group v-model="chongzhiForm.radio">
                <el-radio style="margin-bottom: 30px" label="微信支付">
                  <el-image style="width: 60px; height: 60px; vertical-align: middle"
                    :src="require('@/assets/weixin.png')" fit="fill"></el-image>
                  <span style="display: inline-block; margin-left: 10px">微信支付</span>
                </el-radio>
                <el-radio label="支付宝支付">
                  <el-image style="width: 60px; height: 60px; vertical-align: middle"
                    :src="require('@/assets/zhifubao.png')" fit="fill"></el-image>
                  <span style="display: inline-block; margin-left: 10px">支付宝支付</span>
                </el-radio>
                <el-radio label="中国建设银行支付">
                  <el-image style="width: 120px; height: 60px; vertical-align: middle"
                    :src="require('@/assets/jianshe.png')" fit="fill"></el-image>
                </el-radio>
                <el-radio label="中国农业银行支付">
                  <el-image style="width: 126px; height: 60px; vertical-align: middle"
                    :src="require('@/assets/nongye.png')" fit="fill"></el-image>
                </el-radio>
                <el-radio label="中国银行支付">
                  <el-image style="width: 140px; height: 60px; vertical-align: middle"
                    :src="require('@/assets/zhongguo.png')" fit="fill"></el-image>
                </el-radio>
                <el-radio label="旅游银行支付">
                  <el-image style="width: 120px; height: 60px; vertical-align: middle"
                    :src="require('@/assets/jiaotong.png')" fit="fill"></el-image>
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisibleMoney = false">取 消</el-button>
            <el-button type="primary" @click="chongzhi">确认充值</el-button>
          </div>
        </el-dialog>
        <el-dialog title="会员购买" :visible.sync="dialogFormVisibleVip" width="726px" center>
          <el-form :model="chongzhiForm">
            <el-form-item label="会员卡" label-width="120px">
              <el-input readonly autocomplete="off" value="￥199/年"></el-input>
            </el-form-item>
            <el-form-item label-width="120px">
              <el-radio-group v-model="chongzhiForm.radio">
                <el-radio style="margin-bottom: 30px" label="微信支付">
                  <el-image style="width: 60px; height: 60px; vertical-align: middle"
                    :src="require('@/assets/weixin.png')" fit="fill"></el-image>
                  <span style="display: inline-block; margin-left: 10px">微信支付</span>
                </el-radio>
                <el-radio label="支付宝支付">
                  <el-image style="width: 60px; height: 60px; vertical-align: middle"
                    :src="require('@/assets/zhifubao.png')" fit="fill"></el-image>
                  <span style="display: inline-block; margin-left: 10px">支付宝支付</span>
                </el-radio>
                <el-radio label="中国建设银行支付">
                  <el-image style="width: 120px; height: 60px; vertical-align: middle"
                    :src="require('@/assets/jianshe.png')" fit="fill"></el-image>
                </el-radio>
                <el-radio label="中国农业银行支付">
                  <el-image style="width: 126px; height: 60px; vertical-align: middle"
                    :src="require('@/assets/nongye.png')" fit="fill"></el-image>
                </el-radio>
                <el-radio label="中国银行支付">
                  <el-image style="width: 140px; height: 60px; vertical-align: middle"
                    :src="require('@/assets/zhongguo.png')" fit="fill"></el-image>
                </el-radio>
                <el-radio label="旅游银行支付">
                  <el-image style="width: 120px; height: 60px; vertical-align: middle"
                    :src="require('@/assets/jiaotong.png')" fit="fill"></el-image>
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisibleVip = false">取 消</el-button>
            <el-button type="primary" @click="chongzhivip">确认支付</el-button>
          </div>
        </el-dialog>
      </el-tab-pane>
      <el-tab-pane label="我的收藏"></el-tab-pane>
      <el-tab-pane label="我的门票"></el-tab-pane>
      <el-tab-pane label="我的酒店"></el-tab-pane>
      <el-tab-pane label="我的餐厅"></el-tab-pane>
      <el-tab-pane label="反馈建议"></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import config from "@/config/config";
import Vue from "vue";
export default {
  //数据集合
  data() {
    return {
      title: "个人中心",
      baseUrl: config.baseUrl,
      sessionForm: {},
      rules: {},
      chongzhiForm: {
        money: "",
        radio: "",
      },
      disabled: false,
      dialogFormVisibleMoney: false,
      dialogFormVisibleVip: false,
      uploadUrl: config.baseUrl + "file/upload",
      imageUrl: "",
      headers: { Token: localStorage.getItem("Token") },
      userTableName: localStorage.getItem("UserTableName"),
      dynamicProp: {},
    };
  },
  created() {
    if ("users" == this.userTableName) {
      this.$set(this.sessionForm, "username", null);
    }
    if ("users" == this.userTableName) {
      this.$set(this.sessionForm, "password", null);
    }
    if ("users" == this.userTableName) {
      this.$set(this.sessionForm, "name", null);
    }
    if ("users" == this.userTableName) {
      this.$set(this.sessionForm, "gender", null);
    }
    if ("users" == this.userTableName) {
      this.$set(this.sessionForm, "age", null);
    }
    if ("users" == this.userTableName) {
      this.$set(this.sessionForm, "telephone", null);
    }
    if ("users" == this.userTableName) {
      this.$set(this.sessionForm, "avatar", null);
    }

    if ("users" == this.userTableName) {
      this.$set(this.rules, "username", [
        { required: true, message: "请输入账号", trigger: "blur" },
      ]);
    }
    if ("users" == this.userTableName) {
      this.$set(this.rules, "password", [
        { required: true, message: "请输入密码", trigger: "blur" },
      ]);
    }
    if ("users" == this.userTableName) {
      this.$set(this.rules, "name", [
        { required: true, message: "请输入姓名", trigger: "blur" },
      ]);
    }
    if ("users" == this.userTableName) {
      this.$set(this.rules, "age", [
        {
          required: false,
          validator: this.$validate.isIntNumer,
          trigger: "blur",
        },
      ]);
    }
    if ("users" == this.userTableName) {
      this.$set(this.rules, "telephone", [
        {
          required: false,
          validator: this.$validate.isMobile,
          trigger: "blur",
        },
      ]);
    }

    this.init();
    this.getSession();
  },
  //方法集合
  methods: {
    init() {
      if ("users" == this.userTableName) {
        this.dynamicProp.gender = "男,女".split(",");
      }
    },
    getSession() {
      this.$http
        .get(this.userTableName + "/session", { emulateJSON: true })
        .then((res) => {
          if (res.data.code == 0) {
            this.sessionForm = res.data.data;
            localStorage.setItem("userid", res.data.data.id);
            if (res.data.data.vip) {
              localStorage.setItem("vip", res.data.data.vip);
            }
            if (res.data.data.name) {
              localStorage.setItem("name", res.data.data.name);
            }
            if (res.data.data.avatar) {
              localStorage.setItem("headportrait", res.data.data.avatar);
            } else if (res.data.data.headportrait) {
              localStorage.setItem("headportrait", res.data.data.headportrait);
            }
          }
        });
    },
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http
            .post(this.userTableName + "/update", this.sessionForm)
            .then((res) => {
              if (res.data.code == 0) {
                this.$message({
                  message: "更新成功",
                  type: "success",
                  duration: 1500,
                });
              }
            });
        } else {
          return false;
        }
      });
    },
    usersavatarHandleAvatarSuccess(res, file) {
      if (res.code == 0) {
        if ("users" == this.userTableName) {
          this.sessionForm.avatar = "upload/" + res.file;
        }
      }
    },
    chongzhi() {
      if (this.chongzhiForm.money == "") {
        this.$message({
          message: "请输入充值金额",
          type: "error",
          duration: 1500,
        });
        return;
      }
      if (this.chongzhiForm.money <= 0) {
        this.$message({
          message: "请输入正确的充值金额",
          type: "error",
          duration: 1500,
        });
        return;
      }
      if (this.chongzhiForm.radio == "") {
        this.$message({
          message: "请选择充值方式",
          type: "error",
          duration: 1500,
        });
        return;
      }
      this.sessionForm.money =
        parseInt(this.sessionForm.money) + parseInt(this.chongzhiForm.money);
      this.$http
        .post(this.userTableName + "/update", this.sessionForm)
        .then((res) => {
          if (res.data.code == 0) {
            this.$message({
              message: "充值成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.dialogFormVisibleMoney = false;
              },
            });
          }
        });
    },
    chongzhivip() {
      this.chongzhiForm.money == 199;
      if (this.chongzhiForm.radio == "") {
        this.$message({
          message: "请选择支付方式",
          type: "error",
          duration: 1500,
        });
        return;
      }
      if (this.sessionForm.vip == "是") {
        this.$message({
          message: "您已是我们的尊贵会员。",
          type: "success",
          duration: 1500,
        });
        return;
      }

      this.sessionForm.vip = "是";
      this.$http
        .post(this.userTableName + "/update", this.sessionForm)
        .then((res) => {
          if (res.data.code == 0) {
            this.$message({
              message: "会员购买成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                localStorage.setItem("vip", this.sessionForm.vip);
                this.dialogFormVisibleVip = false;
              },
            });
          }
        });
    },
    handleClick(tab, event) {
      switch (event.target.outerText) {
        case "个人中心":
          tab.$router.push("/index/center");
          break;
        case "我的收藏":
          localStorage.setItem("storeupType", 1);
          tab.$router.push("/index/storeup");
          break;
        case "我的门票":
          // localStorage.setItem('storeupType', 1);
          tab.$router.push("/index/myticket");
          break;
        case "我的酒店":
          // localStorage.setItem('storeupType', 1);
          tab.$router.push("/index/myhotel");
          break;
        case "我的餐厅":
          // localStorage.setItem('storeupType', 1);
          tab.$router.push("/index/myrestaurant");
          break;
        case "反馈建议":
          // localStorage.setItem('storeupType', 1);
          tab.$router.push("/index/feedback");
          break;
      }

      this.title = event.target.outerText;
    },
    logout() {
      localStorage.clear();
      Vue.http.headers.common["Token"] = "";
      this.$router.push("/index/home");
      this.activeIndex = "0";
      localStorage.setItem("keyPath", this.activeIndex);
      this.$forceUpdate();
      this.$message({
        message: "登出成功",
        type: "success",
        duration: 1500,
      });
    },
  },
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.center-preview {
  .el-tabs {
    & ::v-deep .el-tabs__header {
      .el-tabs__nav-wrap {
        margin: 0;

        &::after {
          content: none;
        }
      }

      .el-tabs__active-bar {
        display: none !important;
      }
    }

    .center-preview-pv {
      .el-date-editor.el-input {
        width: auto;
      }

      .balance {
        .el-input {
          width: auto;
        }
      }
    }
  }
}

.center-preview .el-tabs ::v-deep .el-tabs__header {
  border: 2px solid #bad399;
  padding: 0;
  margin: 0 0px;
  background: none;
  width: 180px;
  position: relative;
  float: left;
}

.center-preview .el-tabs ::v-deep .el-tabs__header .el-tabs__item {
  padding: 0 10px;
  color: #333;
  background: 0;
  font-weight: 500;
  font-size: 14px;
  line-height: 40px;
  position: relative;
  text-align: center;
  height: 40px;
}

.center-preview .el-tabs ::v-deep .el-tabs__header .el-tabs__item:hover {
  padding: 0 10px;
  color: #666;
  background: #d4eddd;
  font-weight: 500;
  font-size: 14px;
  line-height: 40px;
  position: relative;
  text-align: center;
  height: 40px;
}

.center-preview .el-tabs ::v-deep .el-tabs__header .el-tabs__item.is-active {
  padding: 0 10px;
  color: #fff;
  background: #bad399;
  font-weight: 500;
  font-size: 14px;
  line-height: 40px;
  position: relative;
  text-align: center;
  height: 40px;
}

.center-preview .el-tabs ::v-deep .el-tabs__content .el-tab-pane {
  padding: 10px;
}

.center-preview-pv .el-form-item ::v-deep .el-form-item__label {
  padding: 0 10px 0 0;
  color: #666;
  font-weight: 500;
  width: 80px;
  font-size: 14px;
  line-height: 40px;
  text-align: right;
}

.center-preview-pv .el-form-item .el-form-item__content {
  margin-left: 80px;
}

.center-preview-pv .el-input ::v-deep .el-input__inner {
  border: 1px solid #bad399;
  border-radius: 4px;
  padding: 0 12px;
  box-shadow: inset 0px 0px 48px 0px #faffe6;
  outline: none;
  color: #666;
  background: rgba(255, 255, 255, 0.5);
  width: 400px;
  font-size: 14px;
  height: 40px;
}

.center-preview-pv .el-select ::v-deep .el-input__inner {
  border: 1px solid #bad399;
  border-radius: 4px;
  padding: 0 12px;
  box-shadow: inset 0px 0px 48px 0px #faffe6;
  outline: none;
  color: #666;
  background: rgba(255, 255, 255, 0.5);
  width: 400px;
  font-size: 14px;
  height: 40px;
}

.center-preview-pv .el-date-editor ::v-deep .el-input__inner {
  border: 1px solid #bad399;
  border-radius: 4px;
  padding: 0 10px 0 30px;
  box-shadow: inset 0px 0px 48px 0px #faffe6;
  outline: none;
  color: #666;
  background: rgba(255, 255, 255, 0.5);
  width: 200px;
  font-size: 14px;
  height: 40px;
}

.center-preview-pv ::v-deep .avatar-uploader-icon {
  border: 1px solid #bad399;
  cursor: pointer;
  border-radius: 6px;
  box-shadow: inset 0px 0px 48px 0px #faffe6;
  color: #bad399;
  width: 150px;
  font-size: 32px;
  min-height: 100px;
  line-height: 100px;
  text-align: center;
  height: auto;
}

.center-preview-pv .el-form-item.balance ::v-deep .el-input__inner {
  border: 1px solid #bad399;
  border-radius: 4px 0 0 4px;
  padding: 0 12px;
  box-shadow: inset 0px 0px 48px 0px #faffe6;
  outline: none;
  margin: 0 10px 0 0;
  color: #666;
  background: rgba(255, 255, 255, 0.5);
  display: inline-block;
  width: 200px;
  font-size: 14px;
  height: 40px;
}
</style>
