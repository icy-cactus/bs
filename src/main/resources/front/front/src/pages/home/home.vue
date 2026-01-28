<template>
  <div
    class="home-preview"
    :style="{ width: '100%', margin: '0 auto', background: 'none' }"
  >
    <div
      class="banner-preview"
      style="width: 100%; height: 41.67vw; min-width: 1280px; min-height: 533px"
    >
      <el-carousel
        :style="{ width: '100%', margin: '0 auto', height: '100%' }"
        trigger="click"
        indicator-position="inside"
        arrow="always"
        type="default"
        direction="horizontal"
        height="100%"
        :autoplay="true"
        :interval="3000"
        :loop="true"
      >
        <el-carousel-item
          :style="{ width: '100%', height: '100%' }"
          v-for="item in carouselList"
          :key="item.id"
        >
          <el-image
            :style="{ objectFit: 'cover', width: '100%', height: '100%' }"
            :src="baseUrl + item.value"
            fit="cover"
          ></el-image>
        </el-carousel-item>
      </el-carousel>
    </div>

    <div
      class="home-preview"
      :style="{ width: '1280px', margin: '0 auto', background: 'none' }"
    >
      <!-- 景点 -->
      <div
        class="recommend"
        :style="{
          padding: '20px',
          margin: '20px 0 0 0',
          overflow: 'hidden',
          borderRadius: '8px',
          flexWrap: 'wrap',
          background: 'rgba(255,255,255,1)',
          display: 'flex',
          justifyContent: 'space-between',
        }"
      >
        <div
          class="title"
          :style="{
            width: 'auto',
            padding: '0px',
            margin: '0px ',
            lineHeight: '54px',
            textAlign: 'left',
            order: '0',
          }"
        >
          <span
            :style="{
              padding: '10px',
              borderColor: '#bad399',
              fontSize: '32px',
              color: '#000',
              borderStyle: 'solid',
              borderWidth: ' 0 0 5px 0',
            }"
            >景点信息推荐</span
          >
        </div>

        <!-- 样式一 -->
        <div class="recommend-list-container">
          <div
            class="recommend-item"
            v-for="(item, index) in scenicinfoRecommend.slice(0, 3)"
            :key="index"
            @click="toDetail('scenicinfoDetail', item)"
          >
            <div class="recommend-overlay">
              {{ item.content || "暂无简介信息" }}
            </div>
            <img
              class="recommend-image"
              v-if="preHttp(item.scenicphoto)"
              :src="item.scenicphoto.split(',')[0]"
              :alt="item.scenicname"
            />
            <img
              class="recommend-image"
              v-else
              :src="
                baseUrl +
                (item.scenicphoto ? item.scenicphoto.split(',')[0] : '')
              "
              :alt="item.scenicname"
            />
             <div class="rating-score">★{{ item.rating }}/10.0</div>
            <div class="recommend-name">{{ item.scenicname }}</div>
            <div class="recommend-address">{{ item.address }}</div>
            <div class="recommend-level">景点等级: {{ item.sceniclevel }}</div>
          </div>
        </div>

        <div
          @click="moreBtn('scenicinfo')"
          :style="{
            border: '0',
            cursor: 'pointer',
            padding: '0 24px',
            margin: '0',
            textAlign: 'center',
            display: 'block',
            float: 'right',
            borderRadius: '30px',
            background: '#bad399',
            width: 'auto',
            lineHeight: '40px',
            height: '40px',
            order: '0',
          }"
        >
          <span :style="{ color: '#f5f5f5', fontSize: '14px' }">查看更多</span>
          <i
            v-if="true"
            :style="{ color: '#f5f5f5', fontSize: '16px' }"
            class="el-icon-d-arrow-right"
          ></i>
        </div>
      </div>
      <!-- 景点轮播推荐	 -->
      <div class="list list3 index-pv1">
        <div
          class="swiper-container"
          id="scenicSwiper"
          :style="{
            width: '100%',
            padding: '10px',
            margin: '20px 0 0 0',
            background: '#fff',
            height: 'auto',
            order: '4',
          }"
        >
          <div class="swiper-wrapper">
            <div
              class="swiper-slide animation-box"
              v-for="(item, index) in scenicinfoRecommend"
              :key="index"
              @click="toDetail('scenicinfoDetail', item)"
            >
              <div class="recommend-overlay">
                {{ item.content || "暂无简介信息" }}
              </div>
              <img
                class="item-image"
                v-if="preHttp(item.scenicphoto)"
                :src="item.scenicphoto.split(',')[0]"
                :alt="item.scenicname"
              />
              <img
                class="item-image"
                v-else
                :src="
                  baseUrl +
                  (item.scenicphoto ? item.scenicphoto.split(',')[0] : '')
                "
                :alt="item.scenicname"
              />
               <div class="rating-score">★{{ item.rating }}/10.0</div>
              <div class="item-name">{{ item.scenicname }}</div>
              <div class="item-address">{{ item.address }}</div>
              <div class="item-level">景点等级: {{ item.sceniclevel }}</div>
            </div>
          </div>

          <!-- 轮播控制按钮 -->
          <div class="swiper-button-prev"></div>
          <div class="swiper-button-next"></div>
        </div>
      </div>

<!--      &lt;!&ndash; 酒店 &ndash;&gt;
      <div
        class="recommend"
        :style="{
          padding: '20px',
          margin: '20px 0 0 0',
          overflow: 'hidden',
          borderRadius: '8px',
          flexWrap: 'wrap',
          background: 'rgba(255,255,255,1)',
          display: 'flex',
          justifyContent: 'space-between',
        }"
      >
        <div
          class="title"
          :style="{
            width: 'auto',
            padding: '0px',
            margin: '0px ',
            lineHeight: '54px',
            textAlign: 'left',
            order: '0',
          }"
        >
          <span
            :style="{
              padding: '10px',
              borderColor: '#bad399',
              fontSize: '32px',
              color: '#000',
              borderStyle: 'solid',
              borderWidth: ' 0 0 5px 0',
            }"
            >酒店信息推荐</span
          >
        </div>

        &lt;!&ndash; 样式一 &ndash;&gt;
        <div class="recommend-list-container">
          <div
            class="recommend-item"
            v-for="(item, index) in hotelinfoRecommend.slice(0, 3)"
            :key="index"
            @click="toDetail('hotelinfoDetail', item)"
          >
            <div class="recommend-overlay">
              {{ item.content || "暂无简介信息" }}
            </div>
            <img
              class="recommend-image"
              v-if="preHttp(item.hotelphoto)"
              :src="item.hotelphoto.split(',')[0]"
              :alt="item.hotelname"
            />
            <img
              class="recommend-image"
              v-else
              :src="
                baseUrl + (item.hotelphoto ? item.hotelphoto.split(',')[0] : '')
              "
              :alt="item.hotelname"
            />
             <div class="rating-score">★{{ item.rating }}/10.0</div>
            <div class="recommend-name">{{ item.hotelname }}</div>
            <div class="recommend-address">
              {{ item.hoteltype }}-{{ item.roomtype }}
            </div>
            <div class="recommend-level">{{ item.address }}</div>
          </div>
        </div>

        <div
          @click="moreBtn('hotelinfo')"
          :style="{
            border: '0',
            cursor: 'pointer',
            padding: '0 24px',
            margin: '0',
            textAlign: 'center',
            display: 'block',
            float: 'right',
            borderRadius: '30px',
            background: '#bad399',
            width: 'auto',
            lineHeight: '40px',
            height: '40px',
            order: '0',
          }"
        >
          <span :style="{ color: '#f5f5f5', fontSize: '14px' }">查看更多</span>
          <i
            v-if="true"
            :style="{ color: '#f5f5f5', fontSize: '16px' }"
            class="el-icon-d-arrow-right"
          ></i>
        </div>
      </div>
      &lt;!&ndash; 酒店轮播推荐	 &ndash;&gt;
      <div class="list list3 index-pv1">
        <div
          class="swiper-container"
          id="hotelSwiper"
          :style="{
            width: '100%',
            padding: '10px',
            margin: '20px 0 0 0',
            background: '#fff',
            height: 'auto',
            order: '4',
          }"
        >
          <div class="swiper-wrapper">
            <div
              class="swiper-slide animation-box"
              v-for="(item, index) in hotelinfoRecommend"
              :key="index"
              @click="toDetail('hotelinfoDetail', item)"
            >
              <div class="recommend-overlay">
                {{ item.content || "暂无简介信息" }}
              </div>
              <img
                class="item-image"
                v-if="preHttp(item.hotelphoto)"
                :src="item.hotelphoto.split(',')[0]"
                :alt="item.hotelname"
              />
              <img
                class="item-image"
                v-else
                :src="
                  baseUrl +
                  (item.hotelphoto ? item.hotelphoto.split(',')[0] : '')
                "
                :alt="item.hotelname"
              />
               <div class="rating-score">★{{ item.rating }}/10.0</div>
              <div class="recommend-name">{{ item.hotelname }}</div>
              <div class="recommend-address">
                {{ item.hoteltype }}-{{ item.roomtype }}
              </div>
              <div class="recommend-level">{{ item.address }}</div>
            </div>
          </div>

          &lt;!&ndash; 轮播控制按钮 &ndash;&gt;
          <div class="swiper-button-prev"></div>
          <div class="swiper-button-next"></div>
        </div>
      </div>

      &lt;!&ndash; 餐厅 &ndash;&gt;
      <div
        class="recommend"
        :style="{
          padding: '20px',
          margin: '20px 0 0 0',
          overflow: 'hidden',
          borderRadius: '8px',
          flexWrap: 'wrap',
          background: 'rgba(255,255,255,1)',
          display: 'flex',
          justifyContent: 'space-between',
        }"
      >
        <div
          class="title"
          :style="{
            width: 'auto',
            padding: '0px',
            margin: '0px ',
            lineHeight: '54px',
            textAlign: 'left',
            order: '0',
          }"
        >
          <span
            :style="{
              padding: '10px',
              borderColor: '#bad399',
              fontSize: '32px',
              color: '#000',
              borderStyle: 'solid',
              borderWidth: ' 0 0 5px 0',
            }"
            >餐厅信息推荐</span
          >
        </div>

        &lt;!&ndash; 样式一 &ndash;&gt;
        <div class="recommend-list-container">
          <div
            class="recommend-item"
            v-for="(item, index) in restaurantinfoRecommend.slice(0, 3)"
            :key="index"
            @click="toDetail('restaurantinfoDetail', item)"
          >
            <div class="recommend-overlay">
              {{ item.content || "暂无简介信息" }}
            </div>
            <img
              class="recommend-image"
              v-if="preHttp(item.restaurantphoto)"
              :src="item.restaurantphoto.split(',')[0]"
              :alt="item.restaurantname"
            />
            <img
              class="recommend-image"
              v-else
              :src="
                baseUrl +
                (item.restaurantphoto ? item.restaurantphoto.split(',')[0] : '')
              "
              :alt="item.restaurantname"
            />
             <div class="rating-score">★{{ item.rating }}/10.0</div>
            <div class="recommend-name">{{ item.restaurantname }}</div>
            <div class="recommend-address">{{ item.address }}</div>
          </div>
        </div>

        <div
          @click="moreBtn('restaurantinfo')"
          :style="{
            border: '0',
            cursor: 'pointer',
            padding: '0 24px',
            margin: '0',
            textAlign: 'center',
            display: 'block',
            float: 'right',
            borderRadius: '30px',
            background: '#bad399',
            width: 'auto',
            lineHeight: '40px',
            height: '40px',
            order: '0',
          }"
        >
          <span :style="{ color: '#f5f5f5', fontSize: '14px' }">查看更多</span>
          <i
            v-if="true"
            :style="{ color: '#f5f5f5', fontSize: '16px' }"
            class="el-icon-d-arrow-right"
          ></i>
        </div>
      </div>
      &lt;!&ndash; 餐厅轮播推荐	 &ndash;&gt;
      <div class="list list3 index-pv1">
        <div
          class="swiper-container"
          id="restaurantSwiper"
          :style="{
            width: '100%',
            padding: '10px',
            margin: '20px 0 0 0',
            background: '#fff',
            height: 'auto',
            order: '4',
          }"
        >
          <div class="swiper-wrapper">
            <div
              class="swiper-slide animation-box"
              v-for="(item, index) in restaurantinfoRecommend"
              :key="index"
              @click="toDetail('restaurantinfoDetail', item)"
            >
              <div class="recommend-overlay">
                {{ item.content || "暂无简介信息" }}
              </div>
              <img
                class="item-image"
                v-if="preHttp(item.restaurantphoto)"
                :src="item.restaurantphoto.split(',')[0]"
                :alt="item.restaurantname"
              />
              <img
                class="item-image"
                v-else
                :src="
                  baseUrl +
                  (item.restaurantphoto
                    ? item.restaurantphoto.split(',')[0]
                    : '')
                "
                :alt="item.restaurantname"
              />
               <div class="rating-score">★{{ item.rating }}/10.0</div>
              <div class="recommend-name">{{ item.restaurantname }}</div>
              <div class="recommend-address">{{ item.address }}</div>
            </div>
          </div>

          &lt;!&ndash; 轮播控制按钮 &ndash;&gt;
          <div class="swiper-button-prev"></div>
          <div class="swiper-button-next"></div>
        </div>
      </div>-->

      <!-- 新闻 -->
      <div
        class="news"
        :style="{
          padding: '20px',
          margin: '20px 0 0 0',
          borderRadius: '8px',
          flexWrap: 'wrap',
          background: 'rgba(255,255,255,1)',
          display: 'flex',
          justifyContent: 'space-between',
        }"
      >
        <div
          class="title"
          :style="{
            width: 'auto',
            padding: '0px',
            margin: '0px  ',
            lineHeight: '54px',
            textAlign: 'left',
            order: '0',
          }"
        >
          <span
            :style="{
              padding: '10px',
              borderColor: '#bad399',
              fontSize: '32px',
              color: '#000',
              borderStyle: 'solid',
              borderWidth: ' 0 0 5px 0',
            }"
            >公告信息</span
          >
        </div>

        <!-- 样式九 -->
        <div
          v-if="newsList.length"
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
            v-for="(item, index) in newsList"
            v-if="index < 6"
            :key="index"
            @click="toDetail('newsDetail', item)"
            class="new-list-item animation-box"
          >
            <div class="new-list-item-img">
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
                :src="
                  baseUrl + (item.picture ? item.picture.split(',')[0] : '')
                "
                alt=""
              />
            </div>
            <div class="new-list-item-content">
              <div class="new-list-item-title line1">
                {{ item.title }}
              </div>
              <div class="new-list-item-time">
                {{ item.addtime.split(" ")[0] }}
              </div>
              <div class="new-list-item-descript line2">
                {{ item.introduction }}
              </div>
            </div>
          </div>
        </div>

        <div
          @click="moreBtn('news')"
          :style="{
            border: '0',
            padding: '0 24px',
            margin: '0',
            borderRadius: '30px',
            textAlign: 'center',
            background: '#bad399',
            display: 'block',
            width: 'auto',
            lineHeight: '40px',
            height: '40px',
            order: '0',
          }"
        >
          <span :style="{ color: '#f5f5f5', fontSize: '14px' }">查看更多</span>
          <i
            v-if="true"
            :style="{ color: '#f5f5f5', fontSize: '14px' }"
            class="el-icon-d-arrow-right"
          ></i>
        </div>

      </div>

      <div
        class="lists"
        :style="{
          padding: '20px',
          margin: '20px 0 0 0',
          borderRadius: '8px',
          flexWrap: 'wrap',
          background: 'rgba(255,255,255,1)',
          display: 'block',
          position: 'relative',
          justifyContent: 'space-between',
        }"
      >
        <div
          v-if="false"
          class="idea"
          :style="{
            padding: '0',
            flexWrap: 'wrap-reverse',
            background: '#fff',
            justifyContent: 'space-between',
            display: 'flex',
          }"
        >
          <div
            class="box1"
            :style="{
              width: '140%',
              background: 'rgba(227, 232, 222,1)',
              height: '10px',
            }"
          ></div>
          <div
            class="box2"
            :style="{ width: '20%', background: '#8ac998', height: '120px' }"
          ></div>
          <div
            class="box3"
            :style="{ width: '15%', background: '#cce199', height: '60px' }"
          ></div>
          <div
            class="box4"
            :style="{ width: '20%', background: '#8ac998', height: '120px' }"
          ></div>
          <div
            class="box5"
            :style="{
              width: '20%',
              background: '#fff',
              display: 'none',
              height: '80px',
            }"
          ></div>
          <div
            class="box6"
            :style="{
              width: '20%',
              background: '#fff',
              display: 'none',
              height: '80px',
            }"
          ></div>
          <div
            class="box7"
            :style="{
              width: '20%',
              background: '#fff',
              display: 'none',
              height: '80px',
            }"
          ></div>
          <div
            class="box8"
            :style="{
              width: '20%',
              background: '#fff',
              display: 'none',
              height: '80px',
            }"
          ></div>
          <div
            class="box9"
            :style="{
              width: '20%',
              background: '#fff',
              display: 'none',
              height: '80px',
            }"
          ></div>
          <div
            class="box10"
            :style="{
              width: '20%',
              background: '#fff',
              display: 'none',
              height: '80px',
            }"
          ></div>
        </div>

        <div
          class="title"
          :style="{
            padding: '0px',
            margin: '0',
            textAlign: 'left',
            background: '0',
            width: 'auto',
            lineHeight: '54px',
            order: '1',
          }"
        >
          <span
            :style="{
              padding: '10px',
              borderColor: '#bad399',
              fontSize: '32px',
              color: '#000',
              borderStyle: 'solid',
              borderWidth: ' 0 0 5px 0',
            }"
            >天气预报展示</span
          >
        </div>

<!--        <div class="list list3 index-pv1">
          <div
            class="swiper-container"
            id="listsweather"
            :style="{
              width: '100%',
              padding: '10px',
              margin: '20px 0 0 0',
              background: '#fff',
              height: 'auto',
              order: '4',
            }"
          >
            <div class="swiper-wrapper">
              <div
                class="swiper-slide animation-box"
                :style="{
                  width: '20%',
                  border: '1px solid #bad399',
                  cursor: 'pointer',
                  padding: '4px',
                  borderRadius: '4px',
                  background: '#fff',
                  fontSize: '0',
                  position: 'relative',
                }"
                v-for="(item, index) in weatherList"
                :key="index"
                @click="toDetail('weatherDetail', item)"
              >
                <img
                  :name="item.id"
                  :style="{ border: '0', width: '100%', height: '220px' }"
                  v-if="preHttp(item.picture)"
                  :src="item.picture.split(',')[0]"
                  alt=""
                />
                <img
                  :name="item.id"
                  :style="{ border: '0', width: '100%', height: '220px' }"
                  v-else
                  :src="
                    baseUrl + (item.picture ? item.picture.split(',')[0] : '')
                  "
                  alt=""
                />
                <div
                  class="name line1"
                  :style="{
                    padding: '0 10px',
                    borderColor: '#e1f6e9',
                    color: '#333',
                    background: '#FAFFE6',
                    borderWidth: '0 0 1px 0',
                    fontSize: '14px',
                    lineHeight: '32px',
                    borderStyle: 'solid',
                  }"
                >
                  {{ item.city }}
                </div>
              </div>
            </div>

            <div class="swiper-button-prev"></div>
            <div class="swiper-button-next"></div>
          </div>
        </div>

        <div
          @click="moreBtn('weather')"
          :style="{
            border: '0',
            padding: '0 24px',
            margin: '0',
            textAlign: 'center',
            display: 'block',
            right: '20px',
            borderRadius: '30px',
            top: '20px',
            background: '#bad399',
            width: 'auto',
            lineHeight: '40px',
            position: 'absolute',
            height: '40px',
            order: '2',
          }"
        >
          <span :style="{ color: '#f5f5f5', fontSize: '14px' }">查看更多</span>
          <i
            v-if="true"
            :style="{ color: '#f5f5f5', fontSize: '12px' }"
            class="el-icon-d-arrow-right"
          ></i>
        </div>-->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  //数据集合
  data() {
    return {
      carouselList: [],
      baseUrl: "",
      newsList: [],
      scenicinfoRecommend: [],
      hotelinfoRecommend: [],
      restaurantinfoRecommend: [],

      weatherList: [],
    };
  },
  created() {
    this.baseUrl = this.$config.baseUrl;
    this.getNewsList();
    this.getList();
    this.getCarousel();
  },
  //方法集合
  methods: {
    preHttp(str) {
      return str && str.substr(0, 4) == "http";
    },
    getNewsList() {
      this.$http
        .get("news/list", {
          params: {
            page: 1,
            limit: 5,
            order: "desc",
          },
        })
        .then((res) => {
          if (res.data.code == 0) {
            this.newsList = res.data.data.list;
          }
        });
    },
    calculateRating(item) {
      // 确保数据是数字类型，默认值为0
      const thumbsUp = Number(item.thumbsupnum) || 0;
      const thumbsDown = Number(item.crazilynum) || 0;

      // 处理异常情况
      if (thumbsUp < 0 || thumbsDown < 0) {
        return "数据异常";
      }

      // 没有任何评价时显示N/A
      if (thumbsUp === 0 && thumbsDown === 0) {
        return "N/A";
      }

      // 计算好评率并转换为10分制
      const total = thumbsUp + thumbsDown;
      const positiveRate = thumbsUp / total;
      const rating = positiveRate * 10;

      // 保留一位小数
      return rating.toFixed(1);
    },
    getList() {
      let autoSortUrl = "";
/*      autoSortUrl = "scenicinfo/autoSort";*/
      autoSortUrl = "scenicinfo/recommend";
      this.$http
        .get(autoSortUrl, {
          params: {
            page: 1,
            limit: 8,
          },
        })
        .then((res) => {
          if (res.data.code == 0) {
            this.scenicinfoRecommend = res.data.data.list;
            let options = {
              observer: true,
              navigation: {
                nextEl: ".swiper-button-next",
                prevEl: ".swiper-button-prev",
              },
              observeParents: true,
              loop: true,
              slidesPerView: 5,
              speed: 500,
              spaceBetween: 20,
              autoplay: { delay: 3000, disableOnInteraction: false },
            };
            options.pagination = { el: "null" };
            if (options.slidesPerView) {
              options.slidesPerView = Number(options.slidesPerView);
            }
            if (options.spaceBetween) {
              options.spaceBetween = Number(options.spaceBetween);
            }
            this.$nextTick(() => {
              new Swiper("#scenicSwiper", options);
            });

            // 商品列表样式五
          }
        });
      autoSortUrl = "hotelinfo/autoSort";
      this.$http
        .get(autoSortUrl, {
          params: {
            page: 1,
            limit: 6,
          },
        })
        .then((res) => {
          if (res.data.code == 0) {
            this.hotelinfoRecommend = res.data.data.list;
            let options = {
              observer: true,
              navigation: {
                nextEl: ".swiper-button-next",
                prevEl: ".swiper-button-prev",
              },
              observeParents: true,
              loop: true,
              slidesPerView: 5,
              speed: 500,
              spaceBetween: 20,
              autoplay: { delay: 3000, disableOnInteraction: false },
            };
            options.pagination = { el: "null" };
            if (options.slidesPerView) {
              options.slidesPerView = Number(options.slidesPerView);
            }
            if (options.spaceBetween) {
              options.spaceBetween = Number(options.spaceBetween);
            }
            this.$nextTick(() => {
              new Swiper("#hotelSwiper", options);
            });
            // 商品列表样式五
          }
        });
      autoSortUrl = "restaurantinfo/autoSort";
      this.$http
        .get(autoSortUrl, {
          params: {
            page: 1,
            limit: 6,
          },
        })
        .then((res) => {
          if (res.data.code == 0) {
            this.restaurantinfoRecommend = res.data.data.list;
            let options = {
              observer: true,
              navigation: {
                nextEl: ".swiper-button-next",
                prevEl: ".swiper-button-prev",
              },
              observeParents: true,
              loop: true,
              slidesPerView: 5,
              speed: 500,
              spaceBetween: 20,
              autoplay: { delay: 3000, disableOnInteraction: false },
            };
            options.pagination = { el: "null" };
            if (options.slidesPerView) {
              options.slidesPerView = Number(options.slidesPerView);
            }
            if (options.spaceBetween) {
              options.spaceBetween = Number(options.spaceBetween);
            }
            this.$nextTick(() => {
              new Swiper("#restaurantSwiper", options);
            });
          }
        });

      this.$http
        .get("weather/list", {
          params: {
            sort: "updatetime",
            order: "desc",
            page: 1,
            limit: 8,
          },
        })
        .then((res) => {
          if (res.data.code == 0) {
            this.weatherList = res.data.data.list;
            let options = {
              observer: true,
              navigation: {
                nextEl: ".swiper-button-next",
                prevEl: ".swiper-button-prev",
              },
              observeParents: true,
              loop: true,
              slidesPerView: 5,
              speed: 500,
              spaceBetween: 20,
              autoplay: { delay: 3000, disableOnInteraction: false },
            };
            options.pagination = { el: "null" };
            if (options.slidesPerView) {
              options.slidesPerView = Number(options.slidesPerView);
            }
            if (options.spaceBetween) {
              options.spaceBetween = Number(options.spaceBetween);
            }
            this.$nextTick(() => {
              new Swiper("#listsweather", options);
            });

            // 商品列表样式五
          }
        });
    },
    toDetail(path, item) {
      this.$router.push({
        path: "/index/" + path,
        query: { detailObj: JSON.stringify(item) },
      });
    },
    getCarousel() {
      this.$http
        .get("config/list", { params: { page: 1, limit: 3 } })
        .then((res) => {
          if (res.data.code == 0) {
            this.carouselList = res.data.data.list;
          }
        });
    },
    moreBtn(path) {
      this.$router.push({ path: "/index/" + path });
    },
  },
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.home-preview {
  .recommend {
    .index-pv1 .animation-box {
      transform: rotate(0deg) scale(1) skew(0deg, 0deg)
        translate3d(0px, 0px, 0px);
      z-index: initial;
    }

    .index-pv1 .animation-box:hover {
      transform: rotate(0) scale(0.98) skew(0deg, 0deg)
        translate3d(0px, 0px, 0px);
      -webkit-perspective: 1000px;
      perspective: 1000px;
      transition: 0.3s;
      z-index: 1;
    }

    .index-pv1 .animation-box img {
      transform: rotate(0deg) scale(1) skew(0deg, 0deg)
        translate3d(0px, 0px, 0px);
    }

    .index-pv1 .animation-box img:hover {
      -webkit-perspective: 1000px;
      perspective: 1000px;
      transition: 0.3s;
    }
  }

  .news {
    .index-pv1 .animation-box {
      transform: rotate(0deg) scale(1) skew(0deg, 0deg)
        translate3d(0px, 0px, 0px);
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
      transform: rotate(0deg) scale(1) skew(0deg, 0deg)
        translate3d(0px, 0px, 0px);
    }

    .index-pv1 .animation-box img:hover {
      transform: 0;
      -webkit-perspective: 1000px;
      perspective: 1000px;
      transition: 0.3s;
    }
  }
}
</style>
<style>
.recommend-list-container {
  padding: 0;
  margin: 20px 0 0 0;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  width: 100%;
  height: auto;
  order: 2;
  background: none;
}

.recommend-item {
  width: 32%;
  margin: 8px 0;
  padding: 4px;
  border: 1px solid #d0e6b8;
  border-radius: 8px;
  background: #ffffff;
  display: flex;
  flex-wrap: wrap;
  position: relative;
  height: auto;
  box-shadow: 0 3px 10px rgba(186, 211, 153, 0.2);
  transition: all 0.3s ease;
  cursor: pointer;
}

.recommend-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 16px rgba(186, 211, 153, 0.3);
  border-color: #a4c77d;
}

/* 简介覆盖层 */
.recommend-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #00000099;
  border-radius: 8px;
  color: white;
  padding: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0;
  transition: opacity 0.4s ease;
  z-index: 10;
  white-space: normal;
  /* overflow: hidden; */
  font-size: 14px;
  line-height: 1.5;
}

.recommend-item:hover .recommend-overlay {
  opacity: 1;
}

.swiper-slide:hover .recommend-overlay {
  opacity: 1;
}

.recommend-image {
  width: 100%;
  height: 220px;
  object-fit: cover;
  border-radius: 4px;
  display: block;
  transition: transform 0.5s ease;
}

.recommend-item:hover .recommend-image {
  transform: scale(1.02);
}

.recommend-name {
  width: 100%;
  padding: 0 10px;
  margin: 0 auto;
  line-height: 36px;
  font-size: 16px;
  font-weight: 600;
  color: #2d5e15;
  background: #f8fff0;
  border-style: solid;
  border-width: 0 0 1px 0;
  border-color: #e1f6e9;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.recommend-address {
  width: 100%;
  padding: 0 10px;
  margin: 0 auto;
  line-height: 30px;
  font-size: 14px;
  color: #555;
  background: #f8fff0;
  border-style: solid;
  border-width: 0 0 1px 0;
  border-color: #e1f6e9;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.recommend-level {
  width: 100%;
  padding: 0 10px;
  margin: 0 auto;
  line-height: 30px;
  font-size: 14px;
  color: #666;
  background: #f8fff0;
  border-style: solid;
  border-width: 0 0 1px 0;
  border-color: #e1f6e9;
}

/* 添加标签效果 */
.recommend-item::after {
  content: "";
  position: absolute;
  top: 10px;
  left: 10px;
  width: 3px;
  height: 18px;
  background: #7cb342;
  border-radius: 3px;
}
</style>
<style>

/* 评分分数 */
.rating-score {
  color: #ff9500;
  font-weight: bold;
  margin-right: 8px;
  font-size: 16px;
}
/* 轮播容器样式 */
#scenicSwiper {
  width: 100%;
  padding: 10px;
  margin: 40px 0 0 0;
  background: #fff;
  height: auto;
  order: 4;
}

/* 轮播 slide 样式 */
.swiper-slide {
  width: 20%;
  border: 1px solid #d0e6b8;
  cursor: pointer;
  padding: 4px;
  border-radius: 8px;
  background: #ffffff;
  font-size: 0;
  position: relative;
  box-shadow: 0 3px 10px rgba(186, 211, 153, 0.2);
  transition: all 0.3s ease;
}

.swiper-slide:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 16px rgba(186, 211, 153, 0.3);
  border-color: #a4c77d;
}

/* 景点图片样式 */
.item-image {
  border: 0;
  width: 100%;
  height: 220px;
  object-fit: cover;
  border-radius: 4px;
  display: block;
  transition: transform 0.5s ease;
}

.swiper-slide:hover .item-image {
  transform: scale(1.02);
}

/* 文字信息样式 */
.item-name,
.item-address,
.item-level {
  padding: 0 10px;
  border-color: #e1f6e9;
  background: #f8fff0;
  border-width: 0 0 1px 0;
  font-size: 14px;
  line-height: 32px;
  border-style: solid;
  width: 100%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  font-size: 0; /* 清除父元素继承的font-size */
}

.item-name {
  color: #2d5e15;
  font-weight: 600;
  font-size: 14px; /* 重新设置字体大小 */
}

.item-address {
  color: #555;
  font-size: 13px;
  line-height: 28px;
}

.item-level {
  color: #666;
  font-size: 13px;
  line-height: 28px;
  border-bottom: none;
}

/* 轮播导航按钮样式 */
.swiper-button-prev,
.swiper-button-next {
  width: 40px;
  height: 40px;
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 50%;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
  top: 50%;
  transform: translateY(-50%);
}

.swiper-button-prev::after,
.swiper-button-next::after {
  color: #2d5e15;
  font-size: 20px;
  font-weight: bold;
}

.swiper-button-prev {
  left: 0px;
}

.swiper-button-next {
  right: 0px;
}

/* 添加标签装饰 */
.swiper-slide::after {
  content: "";
  position: absolute;
  top: 14px;
  left: 14px;
  width: 3px;
  height: 18px;
  background: #7cb342;
  border-radius: 3px;
}
</style>
<!-- 新闻动态 -->
<style>
.new-list-item {
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

.new-list-item-img {
  width: 120px;
  height: 100px;
  margin-right: 20px;
  flex-shrink: 0;
}

.new-list-item-img img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
}

.new-list-item-content {
  flex-grow: 1;
  margin-right: 60px;
}

.new-list-item-title {
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

.new-list-item-time {
  font-size: 12px;
  line-height: 24px;
  color: #999;
  position: absolute;
  right: 20px;
  top: 20px;
}

.new-list-item-descript {
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

.new-list-item-identification {
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
