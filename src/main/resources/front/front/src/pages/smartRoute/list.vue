<template>
  <div class="smart-route-container">
    <!-- 页面标题 -->
    <div class="page-header">
      <h1>智能路线规划</h1>
      <p>根据您的需求，为您量身定制专属旅游路线</p>
    </div>

    <!-- 规划表单 -->
    <el-card class="plan-form-card" shadow="hover">
      <el-form :model="planForm" :rules="rules" ref="planForm" label-width="120px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="起始地址" prop="startAddress">
              <el-input v-model="planForm.startAddress" placeholder="请输入出发城市（可选）"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="目的地址" prop="endAddress">
              <el-input v-model="planForm.endAddress" placeholder="请输入目的地城市（可选）"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="旅游天数" prop="days">
              <el-input-number
                v-model="planForm.days"
                :min="1"
                :max="15"
                label="天数">
              </el-input-number>
              <span class="form-tip">建议1-7天</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="旅游方式" prop="travelType">
              <el-radio-group v-model="planForm.travelType">
                <el-radio label="人文景观">人文景观</el-radio>
                <el-radio label="自然景观">自然景观</el-radio>
                <el-radio label="综合体验">综合体验</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item>
          <el-button type="primary" @click="submitPlan" :loading="loading" icon="el-icon-map-location">
            生成路线规划
          </el-button>
          <el-button @click="resetForm" icon="el-icon-refresh-left">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 规划结果 -->
    <div v-if="planResult" class="plan-result">
      <!-- 路线总览 -->
      <el-card class="route-summary-card" shadow="hover">
        <div slot="header" class="card-header">
          <span class="card-title">路线总览</span>
          <el-tag type="success">{{ planResult.routeSummary.travelType }}</el-tag>
        </div>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="summary-item">
              <i class="el-icon-location-outline"></i>
              <span class="label">出发地</span>
              <span class="value">{{ planResult.routeSummary.startAddress }}</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="summary-item">
              <i class="el-icon-place"></i>
              <span class="label">目的地</span>
              <span class="value">{{ planResult.routeSummary.endAddress }}</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="summary-item">
              <i class="el-icon-date"></i>
              <span class="label">行程天数</span>
              <span class="value">{{ planResult.routeSummary.days }}天</span>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 15px;">
          <el-col :span="12">
            <div class="summary-item">
              <i class="el-icon-ticket"></i>
              <span class="label">景点数量</span>
              <span class="value">{{ planResult.routeSummary.totalScenics }}个</span>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="summary-item total-budget">
              <i class="el-icon-wallet"></i>
              <span class="label">预估总费用</span>
              <span class="value">¥{{ planResult.routeSummary.estimatedBudget.totalCost }}</span>
            </div>
          </el-col>
        </el-row>

        <!-- 预估费用 -->
        <el-divider></el-divider>
        <div class="budget-info">
          <h3>预估费用明细</h3>
          <el-row :gutter="20">
            <el-col :span="8">
              <div class="budget-item">
                <span class="budget-label">门票费用</span>
                <span class="budget-value">¥{{ planResult.routeSummary.estimatedBudget.ticketCost }}</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="budget-item">
                <span class="budget-label">住宿费用</span>
                <span class="budget-value">¥{{ planResult.routeSummary.estimatedBudget.hotelCost }}</span>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="budget-item">
                <span class="budget-label">餐饮费用</span>
                <span class="budget-value">¥{{ planResult.routeSummary.estimatedBudget.mealCost }}</span>
              </div>
            </el-col>
          </el-row>
          <p class="budget-desc">{{ planResult.routeSummary.estimatedBudget.description }}</p>
        </div>
      </el-card>

      <!-- 每日行程 -->
      <div class="daily-plans">
        <h2 class="section-title">每日行程安排</h2>
        <el-timeline>
          <el-timeline-item
            v-for="(day, index) in planResult.dailyPlan"
            :key="index"
            :timestamp="day.title"
            placement="top"
            :color="index === 0 ? '#4E8531' : '#bad399'">
            <el-card class="day-plan-card" shadow="hover">
              <div class="day-content">
                <!-- 景点列表 -->
                <div class="scenic-list">
                  <h4>景点安排</h4>
                  <div v-if="day.scenics && day.scenics.length > 0">
                    <div
                      v-for="(scenic, sIndex) in day.scenics"
                      :key="sIndex"
                      class="scenic-item"
                      @click="toDetail('scenicinfoDetail', scenic)">
                      <img
                        v-if="scenic.scenicphoto"
                        :src="baseUrl + scenic.scenicphoto.split(',')[0]"
                        :alt="scenic.scenicname">
                      <div class="scenic-info">
                        <h5>{{ scenic.scenicname }}</h5>
                        <p><i class="el-icon-location"></i> {{ scenic.address }}</p>
                        <p><i class="el-icon-star-on"></i> 评分: {{ scenic.rating || 'N/A' }}/10</p>
                        <p class="price" v-if="scenic.price">门票: ¥{{ scenic.price }}</p>
                        <p class="introduce">{{ scenic.introduce ? scenic.introduce.substring(0, 50) + '...' : '暂无介绍' }}</p>
                      </div>
                    </div>
                  </div>
                  <el-empty v-else description="暂无景点安排" :image-size="60"></el-empty>
                </div>
              </div>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </div>

      <!-- 所有推荐景点 -->
      <el-card class="recommend-card" shadow="hover" v-if="planResult.allScenics && planResult.allScenics.length > 0">
        <div slot="header" class="card-header">
          <span class="card-title">推荐景点一览</span>
        </div>
        <div class="recommend-list">
          <div
            v-for="(scenic, index) in planResult.allScenics"
            :key="index"
            class="recommend-item"
            @click="toDetail('scenicinfoDetail', scenic)">
            <img v-if="scenic.scenicphoto" :src="baseUrl + scenic.scenicphoto.split(',')[0]" :alt="scenic.scenicname">
            <div class="recommend-info">
              <h5>{{ scenic.scenicname }}</h5>
              <p><i class="el-icon-location"></i> {{ scenic.address }}</p>
              <p><i class="el-icon-star-on"></i> {{ scenic.rating || 'N/A' }}/10</p>
            </div>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      baseUrl: '',
      loading: false,
      planForm: {
        startAddress: '',
        endAddress: '',
        days: 3,
        travelType: '综合体验'
      },
      rules: {
        days: [
          { required: true, message: '请选择旅游天数', trigger: 'change' }
        ],
        travelType: [
          { required: true, message: '请选择旅游方式', trigger: 'change' }
        ]
      },
      planResult: null
    };
  },
  created() {
    this.baseUrl = this.$config.baseUrl;
  },
  methods: {
    submitPlan() {
      this.$refs.planForm.validate(valid => {
        if (valid) {
          this.loading = true;
          const params = {
            startAddress: this.planForm.startAddress,
            endAddress: this.planForm.endAddress,
            days: this.planForm.days,
            travelType: this.planForm.travelType
          };

          this.$http.get('smartroute/plan', { params })
            .then(res => {
              if (res.data.code === 0) {
                this.planResult = res.data.data;
                this.$message.success('路线规划生成成功！');

                // 滚动到结果区域
                this.$nextTick(() => {
                  const resultElement = document.querySelector('.plan-result');
                  if (resultElement) {
                    resultElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
                  }
                });
              } else {
                this.$message.error(res.data.msg || '生成失败，请稍后重试');
              }
            })
            .catch(err => {
              console.error(err);
              this.$message.error('网络错误，请稍后重试');
            })
            .finally(() => {
              this.loading = false;
            });
        }
      });
    },
    resetForm() {
      this.$refs.planForm.resetFields();
      this.planResult = null;
    },
    toDetail(path, item) {
      this.$router.push({
        path: '/index/' + path,
        query: { detailObj: JSON.stringify(item) }
      });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.smart-route-container {
  max-width: 1280px;
  margin: 20px auto;
  padding: 0 20px;
}

.page-header {
  text-align: center;
  margin-bottom: 30px;
  padding: 40px 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 12px;
  color: #fff;

  h1 {
    font-size: 36px;
    margin-bottom: 10px;
  }

  p {
    font-size: 16px;
    opacity: 0.9;
  }
}

.plan-form-card {
  margin-bottom: 30px;
  border-radius: 12px;
}

.form-tip {
  margin-left: 10px;
  color: #999;
  font-size: 12px;
}

.plan-result {
  animation: fadeIn 0.5s ease;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;

  .card-title {
    font-size: 18px;
    font-weight: 600;
    color: #333;
  }
}

.route-summary-card {
  margin-bottom: 30px;
  border-radius: 12px;
}

.summary-item {
  display: flex;
  align-items: center;
  padding: 15px;
  background: #f5f7fa;
  border-radius: 8px;

  &.total-budget {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    color: #fff;

    .label,
    .value {
      color: #fff;
    }
  }

  i {
    font-size: 24px;
    color: #4E8531;
    margin-right: 10px;
  }

  .total-budget i {
    color: #fff;
  }

  .label {
    color: #666;
    margin-right: 5px;
  }

  .value {
    font-weight: 600;
    color: #333;
  }
}

.budget-info {
  h3 {
    font-size: 16px;
    color: #333;
    margin-bottom: 15px;
  }

  .budget-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 12px;
    background: #f5f7fa;
    border-radius: 8px;
    margin-bottom: 10px;

    .budget-label {
      font-size: 14px;
      color: #666;
    }

    .budget-value {
      font-size: 18px;
      font-weight: 600;
      color: #333;
    }
  }

  .budget-desc {
    font-size: 12px;
    color: #999;
    margin-top: 10px;
    padding-left: 10px;
  }
}

.daily-plans {
  margin-bottom: 30px;

  .section-title {
    font-size: 24px;
    color: #333;
    margin-bottom: 20px;
    padding-left: 10px;
    border-left: 4px solid #4E8531;
  }
}

.day-plan-card {
  border-radius: 12px;
  margin-bottom: 20px;
}

.day-content {
  display: block;
}

.scenic-list {
  h4 {
    font-size: 16px;
    color: #333;
    margin-bottom: 15px;
    padding-bottom: 10px;
    border-bottom: 2px solid #bad399;
  }
}

.scenic-item {
  display: flex;
  padding: 15px;
  background: #f5f7fa;
  border-radius: 8px;
  margin-bottom: 15px;
  cursor: pointer;
  transition: all 0.3s ease;

  &:hover {
    background: #e8f5e9;
    transform: translateX(5px);
    box-shadow: 0 4px 12px rgba(78, 133, 49, 0.15);
  }

  img {
    width: 120px;
    height: 120px;
    object-fit: cover;
    border-radius: 8px;
    margin-right: 15px;
    flex-shrink: 0;
  }

  .scenic-info {
    flex: 1;

    h5 {
      font-size: 18px;
      color: #333;
      margin-bottom: 10px;
      font-weight: 600;
    }

    p {
      font-size: 14px;
      color: #666;
      margin: 5px 0;
      line-height: 1.6;

      i {
        margin-right: 5px;
        color: #4E8531;
      }
    }

    .price {
      color: #ff6b6b;
      font-weight: 600;
      font-size: 16px;
    }

    .introduce {
      color: #999;
      font-size: 13px;
      margin-top: 8px;
    }
  }
}

.recommend-card {
  margin-bottom: 30px;
  border-radius: 12px;
}

.recommend-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 20px;
}

.recommend-item {
  display: flex;
  align-items: center;
  padding: 12px;
  background: #f5f7fa;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease;

  &:hover {
    background: #e8f5e9;
    transform: translateY(-5px);
    box-shadow: 0 6px 16px rgba(78, 133, 49, 0.2);
  }

  img {
    width: 80px;
    height: 80px;
    object-fit: cover;
    border-radius: 6px;
    margin-right: 12px;
    flex-shrink: 0;
  }

  .recommend-info {
    flex: 1;

    h5 {
      font-size: 15px;
      color: #333;
      margin-bottom: 8px;
      font-weight: 600;
    }

    p {
      font-size: 13px;
      color: #666;
      margin: 4px 0;

      i {
        margin-right: 4px;
        color: #4E8531;
      }
    }
  }
}

.el-divider {
  margin: 20px 0;
}

@media (max-width: 768px) {
  .recommend-list {
    grid-template-columns: 1fr;
  }

  .scenic-item {
    flex-direction: column;

    img {
      width: 100%;
      height: 200px;
      margin-right: 0;
      margin-bottom: 10px;
    }
  }
}
</style>
