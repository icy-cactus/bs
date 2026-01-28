<template>
  <div
    :style='{ "border": "2px solid #bad399", "padding": "20px", "margin": "20px auto 0", "borderRadius": "8px", "flexWrap": "wrap", "background": "#fff", "width": "1200px", "position": "relative" }'>
    <el-form class="add-update-preview" ref="ruleForm" :model="ruleForm" :rules="rules" label-width="200px">
      <el-form-item :style='{ "width": "100%", "padding": "10px", "margin": "0 0 0px", "display": "inline-block" }' label="景点名称"
        prop="scenicname">
        <el-input v-model="ruleForm.scenicname" placeholder="景点名称" clearable></el-input>
      </el-form-item>
      <el-form-item :style='{ "width": "100%", "padding": "10px", "margin": "0 0 0px", "display": "inline-block" }' label="景点地址"
        prop="address">
        <el-input v-model="ruleForm.address" placeholder="景点地址" clearable></el-input>
      </el-form-item>
      <el-form-item :style='{ "width": "100%", "padding": "10px", "margin": "0 0 0px", "display": "inline-block" }' label="门票价格"
        prop="price">
        <el-input v-model="ruleForm.price" placeholder="门票价格" clearable></el-input>
      </el-form-item>
      <el-form-item :style='{ "width": "100%", "padding": "10px", "margin": "0 0 0px", "display": "inline-block" }' label="景点等级"
        prop="sceniclevel">
        <el-input v-model="ruleForm.sceniclevel" placeholder="景点等级" clearable></el-input>
      </el-form-item>
      <el-form-item :style='{ "width": "100%", "padding": "10px", "margin": "0 0 0px", "display": "inline-block" }' label="预订时间"
        prop="reservetime">
        <el-date-picker value-format="yyyy-MM-dd HH:mm:ss" v-model="ruleForm.reservetime" type="datetime"
          placeholder="预订时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item :style='{ "width": "100%", "padding": "10px", "margin": "0 0 0px", "display": "inline-block" }' label="备注"
        prop="remark">
        <el-input v-model="ruleForm.remark" placeholder="备注" clearable></el-input>
      </el-form-item>
      <el-form-item :style='{ "width": "100%", "padding": "10px", "margin": "0 0 0px", "display": "inline-block" }' label="账号"
        prop="username">
        <el-input v-model="ruleForm.username" placeholder="账号" clearable></el-input>
      </el-form-item>
      <el-form-item :style='{ "width": "100%", "padding": "10px", "margin": "0 0 0px", "display": "inline-block" }' label="姓名"
        prop="name">
        <el-input v-model="ruleForm.name" placeholder="姓名" clearable></el-input>
      </el-form-item>
      <el-form-item :style='{ "width": "100%", "padding": "10px", "margin": "0 0 0px", "display": "inline-block" }' label="手机"
        prop="telephone">
        <el-input v-model="ruleForm.telephone" placeholder="手机" clearable></el-input>
      </el-form-item>

      <el-form-item :style='{ "padding": "0", "margin": "8px 0 0 0" }'>
        <el-button
          :style='{ "border": "0", "cursor": "pointer", "padding": "0", "margin": "20px 40px 20px 260px", "outline": "none", "color": "rgba(255, 255, 255, 1)", "borderRadius": "20px", "background": "#47b144", "width": "128px", "lineHeight": "40px", "fontSize": "14px", "height": "40px" }'
          type="primary" @click="onSubmit">提交</el-button>
        <el-button
          :style='{ "border": "2px solid #bad399", "cursor": "pointer", "padding": "0", "margin": "0", "outline": "none", "color": "#bad399", "borderRadius": "20px", "background": "rgba(255, 255, 255, 1)", "width": "128px", "lineHeight": "40px", "fontSize": "14px", "height": "40px" }'
          @click="back()">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: '',
      baseUrl: '',
      ro: {
        scenicname: false,
        address: false,
        price: false,
        sceniclevel: false,
        reservetime: false,
        remark: false,
        username: false,
        name: false,
        telephone: false,
        ispay: false,
        refid: '',
      },
      type: '',
      userTableName: localStorage.getItem('UserTableName'),
      ruleForm: {
        scenicname: '',
        address: '',
        price: '',
        sceniclevel: '',
        reservetime: '',
        remark: '',
        username: '',
        name: '',
        telephone: '',
        ispay: '未支付',
        refid: '',
      },
      rules: {
        scenicname: [
        ],
        address: [
        ],
        price: [
          { validator: this.$validate.isNumber, trigger: 'blur' },
        ],
        sceniclevel: [
        ],
        reservetime: [
        ],
        remark: [
        ],
        username: [
        ],
        name: [
        ],
        telephone: [
          { validator: this.$validate.isMobile, trigger: 'blur' },
        ],
        ispay: [
        ],
        refid: [
        ],
      },
    };
  },
  computed: {



  },
  created() {
    //this.bg();
    let type = this.$route.query.type ? this.$route.query.type : '';
    this.init(type);
    this.baseUrl = this.$config.baseUrl;
    this.ruleForm.reservetime = this.getCurDateTime()
  },
  methods: {
    getMakeZero(s) {
      return s < 10 ? '0' + s : s;
    },
    // 下载
    download(file) {
      window.open(`${file}`)
    },
    // 初始化
    init(type) {
      this.type = type;
      if (type == 'cross') {
        var obj = JSON.parse(localStorage.getItem('crossObj'));
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
          if (o == 'price') {
            this.ruleForm.price = obj[o];
            this.ro.price = true;
            continue;
          }
          if (o == 'sceniclevel') {
            this.ruleForm.sceniclevel = obj[o];
            this.ro.sceniclevel = true;
            continue;
          }
          if (o == 'reservetime') {
            this.ruleForm.reservetime = obj[o];
            this.ro.reservetime = true;
            continue;
          }
          if (o == 'remark') {
            this.ruleForm.remark = obj[o];
            this.ro.remark = true;
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
          if (o == 'telephone') {
            this.ruleForm.telephone = obj[o];
            this.ro.telephone = true;
            continue;
          }
          if (o == 'id') {
            this.ruleForm.refid = obj[o];
            this.ro.refid = true;
            continue;
          }
           if(o=='merchantusername'){
              this.ruleForm.merchantusername = obj[o];
              this.ro.merchantusername = true;
              continue;
            }
             if(o=='merchantname'){
              this.ruleForm.merchantname = obj[o];
              this.ro.merchantname = true;
              continue;
            }
        }
      }
      // 获取用户信息
      this.$http.get(this.userTableName + '/session', { emulateJSON: true }).then(res => {
        if (res.data.code == 0) {
          var json = res.data.data;
          if ((json.username != '' && json.username) || json.username == 0) {
            this.ruleForm.username = json.username
          }
          if ((json.avatar != '' && json.avatar) || json.avatar == 0) {
            this.ruleForm.avatar = json.avatar
          }
          if ((json.name != '' && json.name) || json.name == 0) {
            this.ruleForm.name = json.name
          }
          if ((json.telephone != '' && json.telephone) || json.telephone == 0) {
            this.ruleForm.telephone = json.telephone
          }
        }
      });
    },

    // 多级联动参数
    // 多级联动参数
    info(id) {
      this.$http.get('ticketreserve/detail/${id}', { emulateJSON: true }).then(res => {
        if (res.data.code == 0) {
          this.ruleForm = res.data.data;
        }
      });
    },
    // 提交
    onSubmit() {

      //更新跨表属性
      var crossuserid;
      var crossrefid;
      var crossoptnum;
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          if (this.type == 'cross') {
            var statusColumnName = localStorage.getItem('statusColumnName');
            var statusColumnValue = localStorage.getItem('statusColumnValue');
            if (statusColumnName && statusColumnName != '') {
              var obj = JSON.parse(localStorage.getItem('crossObj'));
              if (!statusColumnName.startsWith("[")) {
                for (var o in obj) {
                  if (o == statusColumnName) {
                    obj[o] = statusColumnValue;
                  }
                }
                var table = localStorage.getItem('crossTable');
                this.$http.post(table + '/update', obj).then(res => { });
              } else {
                crossuserid = Number(localStorage.getItem('userid'));
                crossrefid = obj['id'];
                crossoptnum = localStorage.getItem('statusColumnName');
                crossoptnum = crossoptnum.replace(/\[/, "").replace(/\]/, "");
              }
            }
          }
          if (crossrefid && crossuserid) {
            this.ruleForm.crossuserid = crossuserid;
            this.ruleForm.crossrefid = crossrefid;
            var params = {
              page: 1,
              limit: 10,
              crossuserid: crossuserid,
              crossrefid: crossrefid,
            }
            this.$http.get('ticketreserve/list', {
              params: params
            }).then(res => {
              if (res.data.data.total >= crossoptnum) {
                this.$message({
                  message: localStorage.getItem('tips'),
                  type: 'success',
                  duration: 1500,
                });
                return false;
              } else {
                // 跨表计算


                this.$http.post('ticketreserve/add', this.ruleForm).then(res => {
                  if (res.data.code == 0) {
                    this.$message({
                      message: '操作成功',
                      type: 'success',
                      duration: 1500,
                      onClose: () => {
                        this.$router.go(-1);
                      }
                    });
                  } else {
                    this.$message({
                      message: res.data.msg,
                      type: 'error',
                      duration: 1500
                    });
                  }
                });
              }
            });
          } else {


            this.$http.post('ticketreserve/add', this.ruleForm).then(res => {
              if (res.data.code == 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.$router.go(-1);
                  }
                });
              } else {
                this.$message({
                  message: res.data.msg,
                  type: 'error',
                  duration: 1500
                });
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
      this.$router.go(-1);
    },
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.el-date-editor.el-input {
  width: auto;
}

.add-update-preview .el-form-item ::v-deep .el-form-item__label {
  padding: 0 10px 0 0;
  color: #666;
  font-weight: 500;
  width: 200px;
  font-size: 14px;
  line-height: 40px;
  text-align: right;
}

.add-update-preview .el-form-item ::v-deep .el-form-item__content {
  margin-left: 200px;
}

.add-update-preview .el-input ::v-deep .el-input__inner {
  border: 1px solid #bad399;
  border-radius: 4px;
  padding: 0 12px;
  box-shadow: inset 0px 0px 48px 0px #FAFFE6;
  outline: none;
  color: #666;
  width: 80%;
  font-size: 14px;
  height: 40px;
}

.add-update-preview .el-select ::v-deep .el-input__inner {
  border: 1px solid #bad399;
  border-radius: 4px;
  padding: 0 10px;
  box-shadow: inset 0px 0px 48px 0px #FAFFE6;
  outline: none;
  color: #666;
  width: 200px;
  font-size: 14px;
  height: 40px;
}

.add-update-preview .el-date-editor ::v-deep .el-input__inner {
  border: 1px solid #bad399;
  border-radius: 4px;
  padding: 0 10px 0 30px;
  box-shadow: inset 0px 0px 48px 0px #FAFFE6;
  outline: none;
  color: #666;
  width: 200px;
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

.add-update-preview ::v-deep .upload .upload-img {
  border: 1px solid #bad399;
  cursor: pointer;
  border-radius: 6px;
  box-shadow: inset 0px 0px 48px 0px #FAFFE6;
  color: #bad399;
  width: 200px;
  font-size: 32px;
  line-height: 120px;
  text-align: center;
  height: auto;
}

.add-update-preview ::v-deep .el-upload-list .el-upload-list__item {
  border: 1px solid #bad399;
  cursor: pointer;
  border-radius: 6px;
  box-shadow: inset 0px 0px 48px 0px #FAFFE6;
  color: #bad399;
  width: 200px;
  font-size: 32px;
  line-height: 120px;
  text-align: center;
  height: auto;
}

.add-update-preview ::v-deep .el-upload .el-icon-plus {
  border: 1px solid #bad399;
  cursor: pointer;
  border-radius: 6px;
  box-shadow: inset 0px 0px 48px 0px #FAFFE6;
  color: #bad399;
  width: 200px;
  font-size: 32px;
  line-height: 120px;
  text-align: center;
  height: auto;
}

.add-update-preview .el-textarea ::v-deep .el-textarea__inner {
  border: 1px solid #bad399;
  border-radius: 4px;
  padding: 12px;
  box-shadow: inset 0px 0px 48px 0px #FAFFE6;
  outline: none;
  color: #666;
  width: 80%;
  font-size: 14px;
  min-height: 180px;
}
</style>
