<template>
  <div>
    <div :style="{
      width: '80%',
      padding: '10px',
      margin: '12px auto 0',
      borderRadius: '16px',
      background: '#BAD399',
    }" class="breadcrumb-preview">
      <el-breadcrumb :separator="'///'" :style="{ fontSize: '14px', lineHeight: '1' }">
        <el-breadcrumb-item>首页</el-breadcrumb-item>
        <el-breadcrumb-item v-for="(item, index) in breadcrumbItem" :key="index">{{ item.name }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="list-preview" :style="{
      width: '1280px',
      margin: '10px auto',
      position: 'relative',
      flexWrap: 'wrap',
      background: 'none',
      display: 'flex',
    }">
      <el-form :inline="true" :model="formSearch" class="list-form-pv" :style="{
        border: '2px solid #bad399',
        padding: '10px 0',
        margin: '10px 0 0 0',
        borderRadius: '8px',
        flexWrap: 'wrap',
        background: '#FAFFE6',
        display: 'flex',
        width: '100%',
        height: 'auto',
      }">
        <el-form-item :style="{ margin: '10px' }">
          <div class="lable" v-if="true" :style="{
            width: 'auto',
            padding: '0 10px',
            lineHeight: '42px',
            color: '#29292a',
            display: 'inline-block',
          }">
            景点名称
          </div>
          <el-input v-model="formSearch.scenicname" placeholder="景点名称" clearable></el-input>
        </el-form-item>
        <el-form-item :style="{ margin: '10px' }">
          <div class="lable" v-if="true" :style="{
            width: 'auto',
            padding: '0 10px',
            lineHeight: '42px',
            color: '#29292a',
            display: 'inline-block',
          }">
            景点地址
          </div>
          <el-input v-model="formSearch.address" placeholder="景点地址" clearable></el-input>
        </el-form-item>
        <el-button v-if="true" :style="{
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
        }" type="primary" @click="getList(1, curFenlei)"><i v-if="true"
            :style="{ color: '#fff', margin: '0 10px 0 0', fontSize: '14px' }" class="el-icon-search"></i>查询</el-button>
        <el-button v-if="isAuth('scenicinfo', '新增')" :style="{
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
        }" type="primary" @click="add('/index/scenicinfoAdd')"><i v-if="true"
            :style="{ color: '#fff', margin: '0 10px 0 0', fontSize: '14px' }"
            class="el-icon-circle-plus-outline"></i>添加</el-button>
      </el-form>

      <div class="list" :style="{ margin: '20px 0 0 0', flex: '1' }">
        <!-- 样式一 -->

        <!-- 样式二 -->
        <div v-for="(item, index) in dataList" :key="index" @click="toDetail(item)" class="list-card">
          <!-- 简介覆盖层 -->
          <div class="list-overlay">
            {{ item.content || "暂无简介信息" }}
          </div>

          <!-- 景点图片区域 -->
          <img v-if="item.scenicphoto && item.scenicphoto.substr(0, 4) === 'http'" :src="item.scenicphoto"
            class="list-image" />
          <img v-else :src="baseUrl + (item.scenicphoto ? item.scenicphoto.split(',')[0] : '')
            " class="list-image" />

          <!-- 信息区域 -->
          <div class="item-info">
            <div class="list-name">
              {{ item.scenicname }}
            </div>
            <!-- 评分显示区域 -->
             <div class="rating-score">★{{ item.rating }}/10.0</div>
            <div class="list-type">
              {{ item.scenictype }}
            </div>
            <div class="list-address">{{ item.address }}</div>

            <div v-if="item.price" class="list-price">
              <span class="price-symbol">￥</span>{{ item.price }}
              <span class="price-suffix">起</span>
            </div>
          </div>
        </div>
      </div>

      <el-pagination background class="pagination" :pager-count="7" :page-size="pageSize" :page-sizes="pageSizes"
        prev-text="上一页" next-text="下一页" :hide-on-single-page="false"
        :layout="['total', 'prev', 'pager', 'next', 'sizes', 'jumper'].join()" :total="total" :style="{
          width: '1280px',
          margin: '20px auto',
          whiteSpace: 'nowrap',
          color: '#333',
        }" @current-change="curChange" @prev-click="prevClick" @next-click="nextClick"></el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  //数据集合
  data() {
    return {
      layouts: "",
      swiperIndex: -1,
      baseUrl: "",
      breadcrumbItem: [
        {
          name: "景点信息",
        },
      ],
      formSearch: {
        scenicname: "",
        address: "",
      },
      fenlei: [],
      dataList: [],
      total: 1,
      pageSize: 10,
      pageSizes: [10, 20, 30, 50],
      totalPage: 1,
      curFenlei: "全部",
      isPlain: false,
      indexQueryCondition: "",
      timeRange: [],
    };
  },
  created() {
    this.indexQueryCondition = this.$route.query.indexQueryCondition
      ? this.$route.query.indexQueryCondition
      : "";
    this.baseUrl = this.$config.baseUrl;
    this.getFenlei();
    this.getList(1, "全部");
  },
  //方法集合
  methods: {
    add(path) {
      this.$router.push({ path: path });
    },
    calculateRating(item) {
      // 确保数据是数字类型，默认值为0
      const thumbsUp = Number(item.thumbsupnum) || 0;
      const thumbsDown = Number(item.crazilynum) || 0;

      // 处理异常情况
      if (thumbsUp < 0 || thumbsDown < 0) {
        return "数据异常";
      }

      // 没有任何评价时显示0
      if (thumbsUp === 0 && thumbsDown === 0) {
        return "0";
      }

      // 计算好评率并转换为10分制
      const total = thumbsUp + thumbsDown;
      const positiveRate = thumbsUp / total;
      const rating = positiveRate * 10;

      // 保留一位小数
      return rating.toFixed(1);
    },
    getFenlei() { },
    getList(page, fenlei, ref = "") {
      let params = { page, limit: this.pageSize };
      let searchWhere = {};
      if (this.formSearch.scenicname != "")
        searchWhere.scenicname = "%" + this.formSearch.scenicname + "%";
      if (this.formSearch.address != "")
        searchWhere.address = "%" + this.formSearch.address + "%";
      this.$http
        .get("scenicinfo/list", { params: Object.assign(params, searchWhere) })
        .then((res) => {
          if (res.data.code == 0) {
            this.dataList = res.data.data.list;
            this.total = res.data.data.total;
            this.pageSize = res.data.data.pageSize;
            this.totalPage = res.data.data.totalPage;

            this.pageSizes = [
              this.pageSize,
              this.pageSize * 2,
              this.pageSize * 3,
              this.pageSize * 5,
            ];
          }
        });
    },
    curChange(page) {
      this.getList(page);
    },
    prevClick(page) {
      this.getList(page);
    },
    nextClick(page) {
      this.getList(page);
    },
    toDetail(item) {
      this.$router.push({
        path: "/index/scenicinfoDetail",
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

.el-pagination ::v-deep .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
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
<style>
/*卡片样式及动画*/

/* 卡片容器 */
.list-card {
  cursor: pointer;
  margin: 0 1% 20px;
  border-radius: 12px;
  background: #faffe6;
  display: flex;
  width: 48%;
  height: 220px;
  transition: all 0.3s ease;
  box-shadow: 0 3px 12px rgba(186, 211, 153, 0.5);
  overflow: hidden;
  float: left;
  position: relative;
  /* 为覆盖层提供定位上下文 */
}

/* 悬停效果 */
.list-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 16px rgba(186, 211, 153, 0.3);
}

/* 简介覆盖层 */
.list-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 380px;
  height: 100%;
  background-color: #00000099;
  border-radius: 12px;
  color: white;
  padding: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0;
  transition: opacity 0.4s ease;
  z-index: 10;
  overflow: hidden;
  font-size: 14px;
  line-height: 1.5;
}

.list-card:hover .list-overlay {
  opacity: 1;
}

/* 图片样式 */
.list-image {
  width: 380px;
  height: 100%;
  object-fit: cover;
  border-right: 10px solid #e7efc0;
  border-radius: 12px 0 0 12px;
  box-shadow: 5px 0 10px -3px rgba(250, 255, 230, 0.8);
  transition: box-shadow 0.3s ease;
  position: relative;
  z-index: 1;
}

.list-card:hover .list-image {
  box-shadow: 8px 0 15px -3px rgba(250, 255, 230, 0.9);
}

/* 信息区域容器 */
.item-info {
  flex: 1;
  padding: 20px 25px;
  position: relative;
  z-index: 1;
  border-left: 2px dashed #bad399;
  margin-left: 15px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

/* 名称 */
.list-name {
  font-size: 18px;
  font-weight: 600;
  color: #333;
  margin-bottom: 8px;
  line-height: 1.3;
  white-space: normal;
  overflow: visible;
  /* 替换 overflow: hidden */
  max-height: 40px;
}

/* 评分容器 */
.rating-container {
  display: flex;
  align-items: center;
  margin-bottom: 6px;
  font-size: 14px;
}

/* 评分分数 */
.rating-score {
  color: #ff9500;
  font-weight: bold;
  margin-right: 8px;
  font-size: 16px;
}

/* 评分统计 */
.rating-stats {
  color: #666;
  display: flex;
  align-items: center;
  gap: 8px;
}

/* 景点类型 */
.list-type {
  font-size: 15px;
  color: #555;
  margin-bottom: 6px;
  line-height: 1.3;
}

/* 景点地址 */
.list-address {
  font-size: 14px;
  color: #666;
  line-height: 1.3;
  text-overflow: ellipsis;
  color: #333;
  margin-bottom: 8px;
  white-space: normal;
  overflow: visible;
  max-height: 40px;
}

/* 价格标签 */
.list-price {
  position: absolute;
  right: 20px;
  bottom: 20px;
  /* background-color: rgba(229, 62, 62, 0.1); */
  padding: 5px 10px;
  border-radius: 4px;
  color: #e53e3e;
  font-size: 18px;
  font-weight: bold;
}

/* 价格符号 */
.price-symbol {
  font-size: 14px;
}

/* 价格后缀 */
.price-suffix {
  font-size: 12px;
  font-weight: normal;
  margin-left: 2px;
}
</style>
