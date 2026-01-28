<template>
<div :style='{"border":"2px solid #bad399","padding":"20px","margin":"20px auto 0","borderRadius":"8px","flexWrap":"wrap","background":"#fff","width":"1200px","position":"relative"}'>
    <el-form
      class="add-update-preview"
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="200px"
    >
          <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 0px","display":"inline-block"}' label="城市" prop="city">
            <el-input v-model="ruleForm.city"
                placeholder="城市" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 0px","display":"inline-block"}' label="封面" v-if="type!='cross' || (type=='cross' && !ro.picture)" prop="picture">
            <file-upload
            tip="点击上传封面"
            action="file/upload"
            :limit="3"
            :multiple="true"
            :fileUrls="ruleForm.picture?ruleForm.picture:''"
            @change="pictureUploadChange"
            ></file-upload>
          </el-form-item>
            <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 0px","display":"inline-block"}' class="upload" v-else label="封面" prop="picture">
                <img v-if="ruleForm.picture.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.picture.split(',')[0]" width="100" height="100">
                <img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.picture.split(',')" :src="baseUrl+item" width="100" height="100">
            </el-form-item>
          <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 0px","display":"inline-block"}'  label="天气" prop="weather">
            <el-select v-model="ruleForm.weather" placeholder="请选择天气"  >
              <el-option
                  v-for="(item,index) in weatherOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 0px","display":"inline-block"}' label="气温" prop="temp">
            <el-input v-model="ruleForm.temp"
                placeholder="气温" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 0px","display":"inline-block"}' label="湿度" prop="wet">
            <el-input v-model="ruleForm.wet"
                placeholder="湿度" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 0px","display":"inline-block"}'  label="风向" prop="wind">
            <el-select v-model="ruleForm.wind" placeholder="请选择风向"  >
              <el-option
                  v-for="(item,index) in windOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 0px","display":"inline-block"}'  label="风速" prop="windspeed">
            <el-select v-model="ruleForm.windspeed" placeholder="请选择风速"  >
              <el-option
                  v-for="(item,index) in windspeedOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 0px","display":"inline-block"}'  label="适宜出行" prop="outdoor">
            <el-select v-model="ruleForm.outdoor" placeholder="请选择适宜出行"  >
              <el-option
                  v-for="(item,index) in outdoorOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 0px","display":"inline-block"}' label="穿衣推荐" prop="clothing">
            <el-input v-model="ruleForm.clothing"
                placeholder="穿衣推荐" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 0px","display":"inline-block"}' label="更新时间" prop="updatetime" >
              <el-date-picker
                  value-format="yyyy-MM-dd HH:mm:ss"
                  v-model="ruleForm.updatetime"
                  type="datetime"
                  placeholder="更新时间">
              </el-date-picker>
          </el-form-item>

      <el-form-item :style='{"padding":"0","margin":"8px 0 0 0"}'>
        <el-button :style='{"border":"0","cursor":"pointer","padding":"0","margin":"20px 40px 20px 260px","outline":"none","color":"rgba(255, 255, 255, 1)","borderRadius":"20px","background":"#47b144","width":"128px","lineHeight":"40px","fontSize":"14px","height":"40px"}'  type="primary" @click="onSubmit">提交</el-button>
        <el-button :style='{"border":"2px solid #bad399","cursor":"pointer","padding":"0","margin":"0","outline":"none","color":"#bad399","borderRadius":"20px","background":"rgba(255, 255, 255, 1)","width":"128px","lineHeight":"40px","fontSize":"14px","height":"40px"}' @click="back()">返回</el-button>
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
        ro:{
            city : false,
            picture : false,
            weather : false,
            temp : false,
            wet : false,
            wind : false,
            windspeed : false,
            outdoor : false,
            clothing : false,
            updatetime : false,
        },
        type: '',
        userTableName: localStorage.getItem('UserTableName'),
        ruleForm: {
          city: '',
          picture: '',
          weather: '',
          temp: '',
          wet: '',
          wind: '',
          windspeed: '',
          outdoor: '',
          clothing: '',
          updatetime: '',
        },
        weatherOptions: [],
        windOptions: [],
        windspeedOptions: [],
        outdoorOptions: [],
        rules: {
          city: [
          ],
          picture: [
          ],
          weather: [
          ],
          temp: [
          ],
          wet: [
          ],
          wind: [
          ],
          windspeed: [
          ],
          outdoor: [
          ],
          clothing: [
          ],
          updatetime: [
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
    },
    methods: {
      getMakeZero(s) {
          return s < 10 ? '0' + s : s;
      },
      // 下载
      download(file){
        window.open(`${file}`)
      },
      // 初始化
      init(type) {
        this.type = type;
        if(type=='cross'){
          var obj = JSON.parse(localStorage.getItem('crossObj'));
          for (var o in obj){
            if(o=='city'){
              this.ruleForm.city = obj[o];
              this.ro.city = true;
              continue;
            }
            if(o=='picture'){
              this.ruleForm.picture = obj[o].split(",")[0];
              this.ro.picture = true;
              continue;
            }
            if(o=='weather'){
              this.ruleForm.weather = obj[o];
              this.ro.weather = true;
              continue;
            }
            if(o=='temp'){
              this.ruleForm.temp = obj[o];
              this.ro.temp = true;
              continue;
            }
            if(o=='wet'){
              this.ruleForm.wet = obj[o];
              this.ro.wet = true;
              continue;
            }
            if(o=='wind'){
              this.ruleForm.wind = obj[o];
              this.ro.wind = true;
              continue;
            }
            if(o=='windspeed'){
              this.ruleForm.windspeed = obj[o];
              this.ro.windspeed = true;
              continue;
            }
            if(o=='outdoor'){
              this.ruleForm.outdoor = obj[o];
              this.ro.outdoor = true;
              continue;
            }
            if(o=='clothing'){
              this.ruleForm.clothing = obj[o];
              this.ro.clothing = true;
              continue;
            }
            if(o=='updatetime'){
              this.ruleForm.updatetime = obj[o];
              this.ro.updatetime = true;
              continue;
            }
          }
        }
        // 获取用户信息
        this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            var json = res.data.data;
          }
        });
        this.weatherOptions = "晴天,阴天,小雨,中雨,大雨,暴雨,阵雨,降雪,冰雹".split(',')
        this.windOptions = "北,东北,东,东南,南,西南,西,西北".split(',')
        this.windspeedOptions = "无风,软风,轻风,微风,和风,劲风,强风,疾风,大风,烈风,狂风,暴风,台风".split(',')
        this.outdoorOptions = "是,否".split(',')
      },

    // 多级联动参数
      // 多级联动参数
      info(id) {
        this.$http.get('weather/detail/${id}', {emulateJSON: true}).then(res => {
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
          if(valid) {
            if(this.type=='cross'){
                 var statusColumnName = localStorage.getItem('statusColumnName');
                 var statusColumnValue = localStorage.getItem('statusColumnValue');
                 if(statusColumnName && statusColumnName!='') {
                     var obj = JSON.parse(localStorage.getItem('crossObj'));
                     if(!statusColumnName.startsWith("[")) {
                         for (var o in obj){
                             if(o==statusColumnName){
                                 obj[o] = statusColumnValue;
                             }
                         }
                         var table = localStorage.getItem('crossTable');
                         this.$http.post(table+'/update', obj).then(res => {});
                     } else {
                            crossuserid=Number(localStorage.getItem('userid'));
                            crossrefid=obj['id'];
                            crossoptnum=localStorage.getItem('statusColumnName');
                            crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
                     }
                 }
            }
            if(crossrefid && crossuserid) {
                 this.ruleForm.crossuserid=crossuserid;
                 this.ruleForm.crossrefid=crossrefid;
                 var params = {
                     page: 1,
                     limit: 10,
                     crossuserid:crossuserid,
                     crossrefid:crossrefid,
                 }
                 this.$http.get('weather/list', {
                  params: params
                 }).then(res => {
                     if(res.data.data.total>=crossoptnum) {
                         this.$message({
                          message: localStorage.getItem('tips'),
                          type: 'success',
                          duration: 1500,
                         });
                          return false;
                     } else {
                         // 跨表计算


                          this.$http.post('weather/add', this.ruleForm).then(res => {
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


                  this.$http.post('weather/add', this.ruleForm).then(res => {
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
      getUUID () {
        return new Date().getTime();
      },
      // 返回
      back() {
        this.$router.go(-1);
      },
      pictureUploadChange(fileUrls) {
          this.ruleForm.picture = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");;
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
