<template>
  <div
    :style="{
      border: '2px solid #bad399',
      padding: '20px',
      margin: '20px auto 0',
      borderRadius: '8px',
      flexWrap: 'wrap',
      background: '#fff',
      width: '1200px',
      position: 'relative',
    }"
  >
    <el-button
      :style="{
        border: '0',
        cursor: 'pointer',
        padding: '0 10px',
        margin: '0px 5px 5px 0',
        outline: 'none',
        color: '#383838',
        borderRadius: '4px',
        background: '#bad399',
        width: 'auto',
        lineHeight: '40px',
        fontSize: '14px',
        height: '40px',
      }"
      type="warning"
      size="mini"
      @click="backClick"
      class="el-icon-back"
      >返回</el-button
    >

    <el-form
      class="add-update-preview"
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="80px"
    >
      <el-form-item
        :style="{
          width: '100%',
          padding: '10px',
          margin: '0 0 0px',
          display: 'inline-block',
        }"
        label="标题"
        prop="title"
      >
        <el-input
          v-model="ruleForm.title"
          placeholder="标题"
          clearable
        ></el-input>
      </el-form-item>

      <el-form-item
        :style="{ padding: '10px', margin: '0 0 4px', background: 'none' }"
        label="封面"
      >
        <el-upload
          class="picture-uploader"
          :action="uploadUrl"
          :headers="headers"
          :show-file-list="false"
          :on-success="imgUpdateSuccess"
        >
          <img
            :style="{
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
            }"
            v-if="ruleForm.picture"
            :src="baseUrl + ruleForm.picture"
            class="picture"
          />
          <i v-else class="el-icon-plus picture-uploader-icon"></i>
        </el-upload>
      </el-form-item>

      <el-form-item
        :style="{
          width: '100%',
          padding: '10px',
          margin: '0 0 0px',
          display: 'inline-block',
        }"
        label="反馈时间"
        prop="feedbacktime"
      >
        <el-date-picker
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd"
          v-model="ruleForm.feedbacktime"
          type="date"
          placeholder="反馈时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item
        :style="{
          width: '100%',
          padding: '10px',
          margin: '0 0 0px',
          display: 'inline-block',
        }"
        label="用户id"
        prop="userid"
      >
        <el-input
          v-model="ruleForm.userid"
          placeholder="用户id"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item
        :style="{
          width: '100%',
          padding: '10px',
          margin: '0 0 0px',
          display: 'inline-block',
        }"
        label="用户姓名"
        prop="username"
      >
        <el-input
          v-model="ruleForm.username"
          placeholder="用户姓名"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item
        :style="{
          width: '100%',
          padding: '10px',
          margin: '0 0 0px',
          display: 'inline-block',
        }"
        label="备注"
        prop="tips"
      >
        <el-input
          v-model="ruleForm.tips"
          placeholder="备注"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item
        :style="{
          width: '100%',
          padding: '10px',
          margin: '0 0 0px',
          display: 'inline-block',
        }"
        label="反馈内容"
        prop="content"
      >
        <editor
          :style="{
            minHeight: '300px',
            padding: '0',
            margin: '0',
            borderColor: '#ccc',
            backgroundColor: '#f9f9f9',
            borderRadius: '0',
            borderWidth: '0',
            width: '100%',
            borderStyle: 'solid',
            height: 'auto',
          }"
          v-model="ruleForm.content"
          class="editor"
          action="file/upload"
        >
        </editor>
      </el-form-item>

      <el-form-item :style="{ padding: '0', margin: '20px 0 20px 0' }">
        <el-button
          :style="{
            border: '0',
            cursor: 'pointer',
            padding: '0',
            margin: '20px 40px 20px 260px',
            outline: 'none',
            color: 'rgba(255, 255, 255, 1)',
            borderRadius: '20px',
            background: '#47b144',
            width: '128px',
            lineHeight: '40px',
            fontSize: '14px',
            height: '40px',
          }"
          type="primary"
          @click="onSubmit"
          >提交</el-button
        >
        <el-button
          :style="{
            border: '2px solid #bad399',
            cursor: 'pointer',
            padding: '0',
            margin: '0',
            outline: 'none',
            color: '#bad399',
            borderRadius: '20px',
            background: 'rgba(255, 255, 255, 1)',
            width: '128px',
            lineHeight: '40px',
            fontSize: '14px',
            height: '40px',
          }"
          @click="back()"
          >返回</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import config from "@/config/config";
export default {
  data() {
    return {
      id: "",
      baseUrl: config.baseUrl ,

      ro: {
        title: false,
        picture: false,
        feedbacktime: false,
        content: false,
        userid: false,
        username: false,
        tips: false,
        sfsh: false,
        shhf: false,
      },
      type: "",
      userTableName: localStorage.getItem("UserTableName"),
      uploadUrl: config.baseUrl + "file/upload",
      imageUrl: "",
      headers: { Token: localStorage.getItem("Token") },
      ruleForm: {
        title: "",
        picture: "",
        feedbacktime: "",
        content: "",
        userid: localStorage.getItem("userid"),
        username: localStorage.getItem("username"),
        tips: "",
      },
      rules: {
        title: [],
        picture: [],
        feedbacktime: [],
        content: [],
        userid: [],
        username: [],
        tips: [],
        sfsh: [],
        shhf: [],
      },
    };
  },
  computed: {},
  created() {
    //this.bg();
    let type = this.$route.query.type ? this.$route.query.type : "";
    this.init(type);
    this.baseUrl = this.$config.baseUrl;
    this.ruleForm.feedbacktime = this.getCurDate();
  },
  methods: {
    getMakeZero(s) {
      return s < 10 ? "0" + s : s;
    },
    // 下载
    download(file) {
      window.open(`${file}`);
    },
    // 初始化
    init(type) {
      this.type = type;
      if (type == "cross") {
        var obj = JSON.parse(localStorage.getItem("crossObj"));
        for (var o in obj) {
          if (o == "title") {
            this.ruleForm.title = obj[o];
            this.ro.title = true;
            continue;
          }
          if (o == "picture") {
            this.ruleForm.picture = obj[o];
            this.ro.picture = true;
            continue;
          }
          if (o == "feedbacktime") {
            this.ruleForm.feedbacktime = obj[o];
            this.ro.feedbacktime = true;
            continue;
          }
          if (o == "content") {
            this.ruleForm.content = obj[o];
            this.ro.content = true;
            continue;
          }
          if (o == "userid") {
            this.ruleForm.userid = obj[o];
            this.ro.userid = true;
            continue;
          }
          if (o == "username") {
            this.ruleForm.username = obj[o];
            this.ro.username = true;
            continue;
          }
          if (o == "tips") {
            this.ruleForm.tips = obj[o];
            this.ro.tips = true;
            continue;
          }
        }
      }
      // 获取用户信息
      this.$http
        .get(this.userTableName + "/session", { emulateJSON: true })
        .then((res) => {
          if (res.data.code == 0) {
            console.log(res.data.data);
            var json = res.data.data;
            if ((json.userid != "" && json.userid) || json.userid == 0) {
              this.ruleForm.userid = json.userid;
            }
            if ((json.username != "" && json.username) || json.username == 0) {
              this.ruleForm.username = json.username;
            }
            if ((json.tips != "" && json.tips) || json.tips == 0) {
              this.ruleForm.tips = json.tips;
            }
          }
        });
    },

    // 多级联动参数
    // 多级联动参数
    info(id) {
      this.$http
        .get("feedback/detail/${id}", { emulateJSON: true })
        .then((res) => {
          if (res.data.code == 0) {
            this.ruleForm = res.data.data;
          }
        });
    },
    imgUpdateSuccess(res, file) {
      if (res.code == 0) {
        console.log(res.file);

          this.ruleForm.picture = "upload/" + res.file;
      }
    },
    
    backClick() {
      this.$router.push("/index/feedback");
    },
    // 提交
    onSubmit() {
      //更新跨表属性
      var crossuserid;
      var crossrefid;
      var crossoptnum;
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          if (this.type == "cross") {
            var statusColumnName = localStorage.getItem("statusColumnName");
            var statusColumnValue = localStorage.getItem("statusColumnValue");
            if (statusColumnName && statusColumnName != "") {
              var obj = JSON.parse(localStorage.getItem("crossObj"));
              if (!statusColumnName.startsWith("[")) {
                for (var o in obj) {
                  if (o == statusColumnName) {
                    obj[o] = statusColumnValue;
                  }
                }
                var table = localStorage.getItem("crossTable");
                this.$http.post(table + "/update", obj).then((res) => {});
              } else {
                crossuserid = Number(localStorage.getItem("userid"));
                crossrefid = obj["id"];
                crossoptnum = localStorage.getItem("statusColumnName");
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
            };
            this.$http
              .get("feedback/list", {
                params: params,
              })
              .then((res) => {
                if (res.data.data.total >= crossoptnum) {
                  this.$message({
                    message: localStorage.getItem("tips"),
                    type: "success",
                    duration: 1500,
                  });
                  return false;
                } else {
                  // 跨表计算

                  this.$http.post("feedback/add", this.ruleForm).then((res) => {
                    if (res.data.code == 0) {
                      this.$message({
                        message: "操作成功",
                        type: "success",
                        duration: 1500,
                        onClose: () => {
                          this.$router.go(-1);
                        },
                      });
                    } else {
                      this.$message({
                        message: res.data.msg,
                        type: "error",
                        duration: 1500,
                      });
                    }
                  });
                }
              });
          } else {
            this.$http.post("feedback/add", this.ruleForm).then((res) => {
              if (res.data.code == 0) {
                this.$message({
                  message: "操作成功",
                  type: "success",
                  duration: 1500,
                  onClose: () => {
                    this.$router.go(-1);
                  },
                });
              } else {
                this.$message({
                  message: res.data.msg,
                  type: "error",
                  duration: 1500,
                });
              }
            });
          }
        }
      });
    },
    // 返回
    back() {
      this.$router.go(-1);
    },
  },
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
  box-shadow: inset 0px 0px 48px 0px #faffe6;
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
  box-shadow: inset 0px 0px 48px 0px #faffe6;
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
  box-shadow: inset 0px 0px 48px 0px #faffe6;
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
  box-shadow: inset 0px 0px 48px 0px #faffe6;
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
  box-shadow: inset 0px 0px 48px 0px #faffe6;
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
  box-shadow: inset 0px 0px 48px 0px #faffe6;
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
  box-shadow: inset 0px 0px 48px 0px #faffe6;
  outline: none;
  color: #666;
  width: 80%;
  font-size: 14px;
  min-height: 180px;
}
</style>
