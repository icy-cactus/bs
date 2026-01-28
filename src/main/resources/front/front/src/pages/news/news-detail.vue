<template>
  <div
    :style='{ "border": "2px solid #bad399", "padding": "20px", "margin": "20px auto 0", "borderRadius": "8px", "flexWrap": "wrap", "background": "#fff", "width": "1200px", "position": "relative" }'>
    <div class="section-title"
      :style='{ "padding": "20px 0", "margin": "20px 0 20px 0", "borderColor": "#bad399", "color": "#333", "textAlign": "center", "overflow": "hidden", "borderRadius": "8px", "background": "url(none) #FAFFE6 no-repeat center 10px", "borderWidth": "1px 1px 4px 1px", "width": "100%", "fontSize": "28px", "lineHeight": "76px", "borderStyle": "solid", "height": "120px" }'>
      公告信息</div>
    <div class="section-content">
      <div class="content-title">{{ detail.title }}</div>
      <div class="content-sub-title">发布时间：{{ detail.addtime }}</div>
      <el-divider></el-divider>
      <img :name="detail.id" style='{border:0;width: 100%;height: auto;object-fit: contain; display: block;}'
        v-if="preHttp(detail.picture)" :src="item.picture.split(',')[0]" alt="" />
      <img :name="detail.id" :style="{}" v-else :src="baseUrl + (detail.picture ? detail.picture.split(',')[0] : '')
        " style='{border:0;width: 100%;height: auto;object-fit: contain; display: block;}' alt="" />
      <img :src="detail.picture" alt="">
      <div class="content-detail" v-html="detail.content"></div>
    </div>
  </div>
</template>

<script>
export default {
  //数据集合
  data() {
    return {
      baseUrl: this.$config.baseUrl,
      detail: {},
    }
  },
  created() {
    this.detail = Object.assign({}, JSON.parse(this.$route.query.detailObj));
  },
  methods: {
    preHttp(str) {
      return str && str.substr(0, 4) == "http";
    },
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.section {
  width: 900px;
  margin: 0 auto;
}

.section-content {
  margin-top: 30px;
}

.content-title {
  text-align: center;
  font-size: 22px;
  font-weight: bold;
}

.content-sub-title {
  text-align: center;
  margin-top: 20px;
  color: #888888;
  font-size: 14px;
}
</style>