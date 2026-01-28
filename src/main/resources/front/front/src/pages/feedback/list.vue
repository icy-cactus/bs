<template>
  <div>
    <div
      :style="{
        width: '80%',
        padding: '10px',
        margin: '12px auto 0',
        borderRadius: '16px',
        background: '#BAD399',
      }"
      class="breadcrumb-preview"
    >
      <el-breadcrumb
        :separator="'///'"
        :style="{ fontSize: '14px', lineHeight: '1' }"
      >
        <el-breadcrumb-item>首页</el-breadcrumb-item>
        <el-breadcrumb-item
          v-for="(item, index) in breadcrumbItem"
          :key="index"
          >{{ item.name }}</el-breadcrumb-item
        >
      </el-breadcrumb>
    </div>
    <div
    
      class="list-preview"
      :style="{
        width: '1280px',
        margin: '10px auto',
        position: 'relative',
        flexWrap: 'wrap',
        background: 'none',
        display: 'flex',
      }"
    ><el-button :style='{"border":"0","cursor":"pointer","padding":"0 10px","margin":"0px 5px 5px 0","outline":"none","color":"#383838","borderRadius":"4px","background":"#bad399","width":"auto","lineHeight":"40px","fontSize":"14px","height":"40px"}' type="warning" size="mini" @click="backClick" class="el-icon-back">返回</el-button>
    
      <el-form
        :inline="true"
        :model="formSearch"
        class="list-form-pv"
        :style="{
          border: '2px solid #bad399',
          padding: '10px 0',
          margin: '10px 0 0 0',
          borderRadius: '8px',
          flexWrap: 'wrap',
          background: '#FAFFE6',
          display: 'flex',
          width: '100%',
          height: 'auto',
        }"
      >
        <el-form-item :style="{ margin: '10px' }">
          <div
            class="lable"
            v-if="true"
            :style="{
              width: 'auto',
              padding: '0 10px',
              lineHeight: '42px',
              color: '#29292a',
              display: 'inline-block',
            }"
          >
            标题
          </div>
          <el-input v-model="title" placeholder="标题" clearable></el-input>
        </el-form-item>

        <el-button
          v-if="true"
          :style="{
            cursor: 'pointer',
            border: '0',
            padding: '0px 15px',
            margin: '10px',
            outline: 'none',
            color: '#fff',
            borderRadius: '10px',
            background: '#bad399',
            width: 'auto',
            fontSize: '14px',
            lineHeight: '42px',
            height: '42px',
          }"
          type="primary"
          @click="getfeedbackList(1)"
          ><i
            v-if="true"
            :style="{ color: '#fff', margin: '0 10px 0 0', fontSize: '14px' }"
            class="el-icon-search"
          ></i
          >查询</el-button
        >
		<el-button
          v-if="true"
          :style="{
            cursor: 'pointer',
            border: '0',
            padding: '0px 15px',
            margin: '10px',
            outline: 'none',
            color: '#fff',
            borderRadius: '10px',
            background: '#bad399',
            width: 'auto',
            fontSize: '14px',
            lineHeight: '42px',
            height: '42px',
          }"
          type="primary"
          @click="add('/index/feedbackAdd')"
          ><i
            v-if="true"
            :style="{ color: '#fff', margin: '0 10px 0 0', fontSize: '14px' }"
            class="el-icon-search"
          ></i
          >新增</el-button
        >
      </el-form>
    </div>

    <div
      class="feedback-preview"
      :style="{
        // border: '2px solid #bad399',
        padding: '20px',
        margin: '20px auto 0 auto',
        borderRadius: '8px',
        background: 'rgba(255,255,255,1)',
        width: '1280px',
        position: 'relative',
        height: 'auto',
      }"
    >
      <!-- 样式三 -->
      <div
        v-if="feedbackList.length"
        class="list list9 index-pv1"
        :style="{
          padding: '0px',
          margin: '20px auto',
          background: 'none',
          flexDirection: 'column',
          display: 'flex',
          width: '100%',
          height: 'auto',
          order: '4',
        }"
      >
        <div
          v-for="(item, index) in feedbackList"
          :key="index"
          @click="tofeedbackDetail(item)"
          class="feedback-list-item animation-box"
        >
          <div class="feedback-list-item-img">
            <img
              :name="item.id"
              :style="{ border: '0' }"
              v-if="preHttp(item.picture)"
              :src="item.picture.split(',')[0]"
              alt=""
            />
            <img
              :name="item.id"
              :style="{ border: '0' }"
              v-else
              :src="baseUrl + (item.picture ? item.picture.split(',')[0] : '')"
              alt=""
            />
          </div>
          <div class="feedback-list-item-content">
            <div class="feedback-list-item-title line1">
              {{ item.title }}
            </div>
            <div class="feedback-list-item-time">
              {{ item.addtime.split(" ")[0] }}
            </div>
            <!-- <div class="feedback-list-item-descript line2">
				{{ item.content }}
			  </div> -->
          </div>
        </div>
      </div>

      <el-pagination
        background
        class="pagination"
        :pager-count="7"
        :page-size="pageSize"
        :page-sizes="pageSizes"
        prev-text="上一页"
        next-text="下一页"
        :hide-on-single-page="false"
        :layout="['total', 'prev', 'pager', 'next', 'sizes', 'jumper'].join()"
        :total="total"
        :style="{
          width: '1280px',
          margin: '20px auto',
          whiteSpace: 'nowrap',
          color: '#333',
        }"
        @current-change="curChange"
        @prev-click="prevClick"
        @next-click="nextClick"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  //数据集合
  data() {
    return {
      baseUrl: this.$config.baseUrl,
      breadcrumbItem: [
        {
          name: "公告信息",
        },
      ],
      feedbackList: [],
      total: 1,
      pageSize: 10,
      pageSizes: [10, 20, 30, 50],
      totalPage: 1,
      layouts: "",
      title: "",
    };
  },
  created() {
    this.getfeedbackList(1);
  },
  //方法集合
  methods: {
    backClick() {
          this.$router.push('/index/center')
      },
	add(path) {
        this.$router.push({path: path});
      },
    getfeedbackList(page) {
      let params = { page, limit: this.pageSize ,userid:localStorage.getItem("userid")};
      let searchWhere = {};
      if (this.title != "") searchWhere.title = "%" + this.title + "%";
      this.$http
        .get("feedback/list", { params: Object.assign(params, searchWhere) })
        .then((res) => {
          if (res.data.code == 0) {
            this.feedbackList = res.data.data.list;
            this.total = res.data.data.total;
            this.pageSize = res.data.data.pageSize;
            this.pageSizes = [
              this.pageSize,
              this.pageSize * 2,
              this.pageSize * 3,
              this.pageSize * 5,
            ];
            this.totalPage = res.data.data.totalPage;
          }
        });
    },
    preHttp(str) {
      return str && str.substr(0, 4) == "http";
    },
    curChange(page) {
      this.getfeedbackList(page);
    },
    prevClick(page) {
      this.getfeedbackList(page);
    },
    nextClick(page) {
      this.getfeedbackList(page);
    },
    tofeedbackDetail(item) {
      this.$router.push({
        path: "/index/feedbackDetail",
        query: { detailObj: JSON.stringify(item) },
      });
    },
  },
};
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.list-preview .list-form-pv .el-input {
  width: auto;
}

.breadcrumb-preview .el-breadcrumb ::v-deep .el-breadcrumb__separator {
  margin: 0 9px;
  color: #eee;
  font-weight: 500;
}

.breadcrumb-preview .el-breadcrumb ::v-deep .el-breadcrumb__inner a {
  color: #eee;
  display: inline-block;
}

.breadcrumb-preview .el-breadcrumb ::v-deep .el-breadcrumb__inner {
  color: #fff;
  display: inline-block;
}

.category-1 .item {
  cursor: pointer;
  border-radius: 4px;
  margin: 0 10px 0 0;
  color: #999;
  background: #efefef;
  width: 72px;
  font-size: 14px;
  line-height: 36px;
  text-align: center;
}

.category-1 .item:hover {
  cursor: pointer;
  border-radius: 4px;
  margin: 0 10px 0 0;
  color: #999;
  background: #000;
  width: 72px;
  font-size: 14px;
  line-height: 36px;
  text-align: center;
}

.category-1 .item.active {
  cursor: pointer;
  border-radius: 4px;
  margin: 0 10px 0 0;
  color: #999;
  background: #000;
  width: 72px;
  font-size: 14px;
  line-height: 36px;
  text-align: center;
}

.category-2 .item {
  cursor: pointer;
  padding: 0px 0;
  margin: 0 0 10px 0;
  color: #bad399;
  font-size: 12px;
  border-color: #bad399;
  line-height: 40px;
  border-radius: 30px;
  background: #ecfcf2;
  width: 100%;
  border-width: 2px;
  border-style: solid;
  text-align: center;
  height: 40px;
}

.category-2 .item:hover {
  cursor: pointer;
  border-radius: 30px;
  padding: 0px 0;
  margin: 0 0 10px 0;
  color: #fff;
  background: #bad399;
  width: 100%;
  font-size: 12px;
  line-height: 40px;
  text-align: center;
  height: 40px;
}

.category-2 .item.active {
  cursor: pointer;
  border-radius: 30px;
  padding: 0px 0;
  margin: 0 0 10px 0;
  color: #fff;
  background: #bad399;
  width: 100%;
  font-size: 14px;
  line-height: 40px;
  text-align: center;
  height: 40px;
}

.list-form-pv .el-input ::v-deep .el-input__inner {
  border: 2px solid #bad399;
  border-radius: 8px;
  padding: 0 10px;
  margin: 0;
  outline: none;
  color: #333;
  width: 140px;
  font-size: 14px;
  line-height: 42px;
  height: 42px;
}

.list-form-pv .el-select ::v-deep .el-input__inner {
  border: 2px solid #bad399;
  border-radius: 8px;
  padding: 0 10px;
  margin: 0;
  outline: none;
  color: #333;
  width: 140px;
  font-size: 14px;
  line-height: 42px;
  height: 42px;
}

.list-form-pv .el-date-editor ::v-deep .el-input__inner {
  border: 2px solid #bad399;
  border-radius: 8px;
  padding: 0 30px;
  margin: 0;
  outline: none;
  color: #333;
  width: 140px;
  font-size: 14px;
  line-height: 42px;
  height: 42px;
}

.list .index-pv1 .animation-box {
  transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
  z-index: initial;
}

.list .index-pv1 .animation-box:hover {
  transform: 0;
  -webkit-perspective: 1000px;
  perspective: 1000px;
  transition: 0.3s;
  z-index: 1;
}

.list .index-pv1 .animation-box img {
  transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
}

.list .index-pv1 .animation-box img:hover {
  transform: 0;
  -webkit-perspective: 1000px;
  perspective: 1000px;
  transition: 0.3s;
}

.el-pagination ::v-deep .el-pagination__total {
  margin: 0 10px 0 0;
  color: #666;
  font-weight: 400;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.el-pagination ::v-deep .btn-prev {
  border: none;
  border-radius: 2px;
  padding: 0 4px;
  margin: 0 5px;
  color: #bad399;
  background: #f4f4f5;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  min-width: 35px;
  height: 28px;
}

.el-pagination ::v-deep .btn-next {
  border: none;
  border-radius: 2px;
  padding: 0 4px;
  margin: 0 5px;
  color: #bad399;
  background: #f4f4f5;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  min-width: 35px;
  height: 28px;
}

.el-pagination ::v-deep .btn-prev:disabled {
  border: none;
  cursor: not-allowed;
  border-radius: 2px;
  padding: 0 4px;
  margin: 0 5px;
  color: #c0c4cc;
  background: #f4f4f5;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.el-pagination ::v-deep .btn-next:disabled {
  border: none;
  cursor: not-allowed;
  border-radius: 2px;
  padding: 0 4px;
  margin: 0 5px;
  color: #c0c4cc;
  background: #f4f4f5;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.el-pagination ::v-deep .el-pager {
  padding: 0;
  margin: 0;
  display: inline-block;
  vertical-align: top;
}

.el-pagination ::v-deep .el-pager .number {
  cursor: pointer;
  border-radius: 2px;
  margin: 0 5px;
  color: #666;
  background: #f4f4f5;
  display: inline-block;
  vertical-align: top;
  line-height: 28px;
  text-align: center;
  min-width: 30px;
  height: 28px;
}

.el-pagination ::v-deep .el-pager .number:hover {
  cursor: pointer;
  padding: 0 4px;
  margin: 0 5px;
  color: #bad399;
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

.el-pagination ::v-deep .el-pager .number.active {
  cursor: default;
  padding: 0 4px;
  margin: 0 5px;
  color: #bad399;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  border-radius: 2px;
  background: #fff;
  text-align: center;
  min-width: 30px;
  height: 28px;
}

.el-pagination ::v-deep .el-pagination__sizes {
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.el-pagination ::v-deep .el-pagination__sizes .el-input {
  margin: 0 5px;
  width: 100px;
  position: relative;
}

.el-pagination ::v-deep .el-pagination__sizes .el-input .el-input__inner {
  border: 1px solid #dcdfe6;
  cursor: pointer;
  padding: 0 25px 0 8px;
  color: #bad399;
  display: inline-block;
  font-size: 13px;
  line-height: 28px;
  border-radius: 3px;
  outline: 0;
  background: #fff;
  width: 100%;
  text-align: center;
  height: 28px;
}

.el-pagination ::v-deep .el-pagination__sizes .el-input span.el-input__suffix {
  top: 0;
  position: absolute;
  right: 0;
  height: 100%;
}

.el-pagination
  ::v-deep
  .el-pagination__sizes
  .el-input
  .el-input__suffix
  .el-select__caret {
  cursor: pointer;
  color: #c0c4cc;
  width: 25px;
  font-size: 14px;
  line-height: 28px;
  text-align: center;
}

.el-pagination ::v-deep .el-pagination__jump {
  margin: 0 0 0 24px;
  color: #606266;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.el-pagination ::v-deep .el-pagination__jump .el-input {
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

.el-pagination ::v-deep .el-pagination__jump .el-input .el-input__inner {
  border: 1px solid #dcdfe6;
  cursor: pointer;
  border-radius: 3px;
  padding: 0 3px;
  color: #bad399;
  background: #fff;
  display: inline-block;
  width: 100%;
  font-size: 14px;
  line-height: 28px;
  text-align: center;
  height: 28px;
}
</style>
<style rel="stylesheet/scss" lang="scss" scoped>
.feedback-wrap {
  width: 1000px;
  margin: 0 auto;
}
.feedback-item {
  height: 200px;
  width: auto;
  margin-bottom: 30px;
  cursor: pointer;
}
.feedback-item-text {
  width: calc(100% - 215px);
  display: inline-block;
  vertical-align: top;
  margin-left: 15px;
}
.feedback-item-text-title {
  font-size: 16px;
  margin-bottom: 15px;
}
.feedback-item-text-intro {
  font-size: 14px;
  color: #666;
}

.hide1 {
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}
.hide2 {
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}
.hide3 {
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}

.index-pv1 .animation-box {
  transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
  z-index: initial;
}

.index-pv1 .animation-box:hover {
  transform: translate3d(-2px, -2px, 20px);
  -webkit-perspective: 1000px;
  perspective: 1000px;
  transition: 0.3s;
  z-index: 1;
}

.index-pv1 .animation-box img {
  transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
}

.index-pv1 .animation-box img:hover {
  transform: 0;
  -webkit-perspective: 1000px;
  perspective: 1000px;
  transition: 0.3s;
}

.el-pagination ::v-deep .el-pagination__total {
  margin: 0 10px 0 0;
  color: #666;
  font-weight: 400;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.el-pagination ::v-deep .btn-prev {
  border: none;
  border-radius: 2px;
  padding: 0 4px;
  margin: 0 5px;
  color: #bad399;
  background: #f4f4f5;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  min-width: 35px;
  height: 28px;
}

.el-pagination ::v-deep .btn-next {
  border: none;
  border-radius: 2px;
  padding: 0 4px;
  margin: 0 5px;
  color: #bad399;
  background: #f4f4f5;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  min-width: 35px;
  height: 28px;
}

.el-pagination ::v-deep .btn-prev:disabled {
  border: none;
  cursor: not-allowed;
  border-radius: 2px;
  padding: 0 4px;
  margin: 0 5px;
  color: #c0c4cc;
  background: #f4f4f5;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.el-pagination ::v-deep .btn-next:disabled {
  border: none;
  cursor: not-allowed;
  border-radius: 2px;
  padding: 0 4px;
  margin: 0 5px;
  color: #c0c4cc;
  background: #f4f4f5;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.el-pagination ::v-deep .el-pager {
  padding: 0;
  margin: 0;
  display: inline-block;
  vertical-align: top;
}

.el-pagination ::v-deep .el-pager .number {
  cursor: pointer;
  border-radius: 2px;
  margin: 0 5px;
  color: #666;
  background: #f4f4f5;
  display: inline-block;
  vertical-align: top;
  line-height: 28px;
  text-align: center;
  min-width: 30px;
  height: 28px;
}

.el-pagination ::v-deep .el-pager .number:hover {
  cursor: pointer;
  padding: 0 4px;
  margin: 0 5px;
  color: #bad399;
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

.el-pagination ::v-deep .el-pager .number.active {
  cursor: default;
  padding: 0 4px;
  margin: 0 5px;
  color: #bad399;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  border-radius: 2px;
  background: #fff;
  text-align: center;
  min-width: 30px;
  height: 28px;
}

.el-pagination ::v-deep .el-pagination__sizes {
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.el-pagination ::v-deep .el-pagination__sizes .el-input {
  margin: 0 5px;
  width: 100px;
  position: relative;
}

.el-pagination ::v-deep .el-pagination__sizes .el-input .el-input__inner {
  border: 1px solid #dcdfe6;
  cursor: pointer;
  padding: 0 25px 0 8px;
  color: #bad399;
  display: inline-block;
  font-size: 13px;
  line-height: 28px;
  border-radius: 3px;
  outline: 0;
  background: #fff;
  width: 100%;
  text-align: center;
  height: 28px;
}

.el-pagination ::v-deep .el-pagination__sizes .el-input span.el-input__suffix {
  top: 0;
  position: absolute;
  right: 0;
  height: 100%;
}

.el-pagination
  ::v-deep
  .el-pagination__sizes
  .el-input
  .el-input__suffix
  .el-select__caret {
  cursor: pointer;
  color: #c0c4cc;
  width: 25px;
  font-size: 14px;
  line-height: 28px;
  text-align: center;
}

.el-pagination ::v-deep .el-pagination__jump {
  margin: 0 0 0 24px;
  color: #606266;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.el-pagination ::v-deep .el-pagination__jump .el-input {
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

.el-pagination ::v-deep .el-pagination__jump .el-input .el-input__inner {
  border: 1px solid #dcdfe6;
  cursor: pointer;
  border-radius: 3px;
  padding: 0 3px;
  color: #bad399;
  background: #fff;
  display: inline-block;
  width: 100%;
  font-size: 14px;
  line-height: 28px;
  text-align: center;
  height: 28px;
}
</style>
<!-- 新闻动态 -->
<style>
.feedback-list-item {
  cursor: pointer;
  padding: 20px;
  margin: 0 0 15px 0;
  border-bottom: 1px solid #bad399;
  width: 100%;
  position: relative;
  display: flex;
  align-items: center;
  box-sizing: border-box;
}

.feedback-list-item-img {
  width: 120px;
  height: 100px;
  margin-right: 20px;
  flex-shrink: 0;
}

.feedback-list-item-img img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
}

.feedback-list-item-content {
  flex-grow: 1;
  margin-right: 60px;
}

.feedback-list-item-title {
  color: #333;
  text-align: left;
  background: none;
  font-size: 16px;
  line-height: 28px;
  whitespace: nowrap;
  overflow: hidden;
  textoverflow: ellipsis;
  margin-bottom: 10px;
}

.feedback-list-item-time {
  font-size: 12px;
  line-height: 24px;
  color: #999;
  position: absolute;
  right: 20px;
  top: 20px;
}

.feedback-list-item-descript {
  color: #666;
  text-align: left;
  background: none;
  font-size: 14px;
  line-height: 24px;
  whitespace: nowrap;
  overflow: hidden;
  textoverflow: ellipsis;
  height: 24px;
}

.feedback-list-item-identification {
  padding: 12px;
  color: #fff;
  border-radius: 12px;
  text-align: center;
  background: radial-gradient(
    circle,
    rgba(95, 218, 140, 1) 0%,
    rgba(186, 211, 153, 1) 100%
  );
  display: inline-block;
  width: 40px;
  font-size: 14px;
  line-height: 1.1;
  position: absolute;
  height: 80px;
  right: 20px;
  top: 50%;
  transform: translateY(-50%);
}
</style>
