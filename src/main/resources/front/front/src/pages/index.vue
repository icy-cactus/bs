<template>
	<div class="main-containers">
		<div class="top-container"
			:style='{ "margin": "0 auto", "overflow": "hidden", "alignItems": "center", "background": "none", "display": "flex", "width": "1200px", "position": "relative", "justifyContent": "space-between", "height": "64px", "zIndex": "1002" }'>
			<img v-if='false'
				:style='{ "width": "15%", "boxShadow": "10px  0  rgba(245, 245, 245,.5)", "objectFit": "cover", "borderRadius": "0", "display": "block", "height": "100%" }'
				src='http://codegen.caihongy.cn/20221021/5f27920058a1472eb34bcc63283b3a97.png'>
			<div v-if="true" :style='{ "color": "#4E8531", "margin": "0", "fontSize": "30px" }'>智能旅游推荐系统</div>
			<div>
				<div v-if="false"
					:style='{ "fontSize": "14px", "color": "#666", "marginRight": "20px", "display": "inline-block" }'>
					0753-1234567
				</div>
				<div v-if="Token"
					:style='{ "fontSize": "18px", "color": "#666", "marginRight": "20px", "display": "inline-block" }'>
					{{
						role }}
				</div>
				<div v-if="Token"
					:style='{ "fontSize": "18px", "color": "#666", "marginRight": "20px", "display": "inline-block" }'>
					{{
						username }}
				</div>
				<el-button v-if="!Token" @click="toLogin()"
					:style='{ "border": "none", "padding": "0 20px", "margin": "0 20px", "color": "#f4f4f5", "borderRadius": "20px", "background": "#bad399", "display": "inline-block", "fontSize": "18px", "lineHeight": "36px", "height": "36px" }'>登录/注册</el-button>
				<el-button v-if="Token" @click="logout"
					:style='{ "padding": "0 20px", "margin": "0 0 0 0", "color": "#666", "borderRadius": "20px", "background": "#f4f4f5", "display": "inline-block", "fontSize": "18px", "lineHeight": "36px", "height": "36px" }'>退出</el-button>
			</div>
		</div>


		<div class="body-containers"
			:style='"horizontal" == "vertical" ? { "minHeight": "100vh", "padding": "64px 0 0", "margin": "0 0 0 210px", "position": "relative", "background": "#fff", "display": "block" } : { "minHeight": "100vh", "padding": "0", "position": "relative", "backgroundImage": "none)" }'>
			<div class="menu-preview" :style='{ "width": "100%", "background": "none", "height": "auto" }'>
				<el-menu class="el-menu-horizontal-demo"
					:style='{ "border": "0", "listStyle": "none", "margin": "0 auto", "background": "#4E8531", "display": "flex", "width": "100%", "position": "relative", "justifyContent": "center" }'
					:default-active="activeIndex" :unique-opened="true" mode="horizontal" :router="true"
					@select="handleSelect">
					<el-image v-if="false"
						:style='{ "width": "44px", "margin": "8px 10px 8px 0", "objectFit": "cover", "borderRadius": "100%", "float": "left", "height": "44px" }'
						src="http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg"
						fit="cover"></el-image>
					<el-menu-item v-for="(menu, index) in menuList" :index="index + ''" :key="index" :route="menu.url">
						<i v-if="true"
							:style='{ "padding": "0 10px", "margin": "0", "color": "inherit", "width": "14px", "lineHeight": "60px", "fontSize": "14px", "height": "60px" }'
							:class="iconArr[index]"></i>
						<span
							:style='{ "padding": "0 10px", "lineHeight": "60px", "fontSize": "14px", "color": "inherit", "height": "60px" }'>{{
								menu.name }}</span>
					</el-menu-item>
					<el-menu-item @click="goSmartRoute">
						<i v-if="true"
							:style='{ "padding": "0 10px", "margin": "0", "color": "inherit", "width": "14px", "lineHeight": "60px", "fontSize": "14px", "height": "60px" }'
							class="el-icon-map-location"></i>
						<span
							:style='{ "padding": "0 10px", "lineHeight": "60px", "fontSize": "14px", "color": "inherit", "height": "60px" }'>智能路线规划</span>
					</el-menu-item>
					<el-menu-item @click="goBackend">
						<i v-if="true"
							:style='{ "padding": "0 10px", "margin": "0", "color": "inherit", "width": "14px", "lineHeight": "60px", "fontSize": "14px", "height": "60px" }'
							class="el-icon-box"></i>
						<span
							:style='{ "padding": "0 10px", "lineHeight": "60px", "fontSize": "14px", "color": "inherit", "height": "60px" }'>后台管理</span>
					</el-menu-item>
					<el-menu-item :index="menuList.length + 1 + ''" v-if="Token && notAdmin"
						@click="goMenu('/index/center')">
						<i v-if="true"
							:style='{ "padding": "0 10px", "margin": "0", "color": "inherit", "width": "14px", "lineHeight": "60px", "fontSize": "14px", "height": "60px" }'
							class="el-icon-user"></i>
						<span
							:style='{ "padding": "0 10px", "lineHeight": "60px", "fontSize": "14px", "color": "inherit", "height": "60px" }'>个人中心</span>
					</el-menu-item>
				</el-menu>
			</div>



			<router-view></router-view>

			<!-- 智能客服AI悬浮按钮 -->
			<div class="ai-chat-float-btn" @click="showChatDialog = true" v-if="!showChatDialog">
				<i class="el-icon-chat-dot-round"></i>
				<span>智能客服</span>
			</div>

			<!-- 智能客服AI对话框 -->
			<el-dialog
				title="智能客服AI助手"
				:visible.sync="showChatDialog"
				width="450px"
				:close-on-click-modal="false"
				:modal="true"
				class="ai-chat-dialog">
				<div class="chat-container">
					<div class="chat-messages" ref="chatMessages">
						<div
							v-for="(msg, index) in chatMessages"
							:key="index"
							:class="['chat-message', msg.type === 'user' ? 'user-message' : 'ai-message']">
							<div class="message-avatar">
								<i :class="msg.type === 'user' ? 'el-icon-user' : 'el-icon-cpu'"></i>
							</div>
							<div class="message-content">
								<div class="message-text">{{ msg.text }}</div>
								<div class="message-time">{{ msg.time }}</div>
							</div>
						</div>
						<div v-if="isAiTyping" class="chat-message ai-message">
							<div class="message-avatar">
								<i class="el-icon-cpu"></i>
							</div>
							<div class="message-content typing-indicator">
								<span></span>
								<span></span>
								<span></span>
							</div>
						</div>
					</div>
					<div class="chat-input-area">
						<el-input
							v-model="userInput"
							placeholder="请输入您的问题..."
							@keyup.enter.native="sendMessage"
							:disabled="isAiTyping">
							<el-button slot="append" icon="el-icon-s-promotion" @click="sendMessage" :disabled="isAiTyping">发送</el-button>
						</el-input>
					</div>
					<div class="quick-questions">
						<span class="quick-label">快捷问题：</span>
						<el-tag
							v-for="(question, index) in quickQuestions"
							:key="index"
							size="small"
							@click="sendQuickQuestion(question)"
							class="quick-tag">
							{{ question }}
						</el-tag>
					</div>
				</div>
			</el-dialog>

			<div class="bottom-preview"
				:style='{ "padding": "40px 0", "margin": "20px 0 0 0", "alignItems": "center", "background": "#282828", "flexDirection": "column", "display": "flex", "width": "100%", "justifyContent": "center" }'>
				<img :style='{ "objectFit": "cover", "borderRadius": "100%", "display": "none" }'
					src="http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg">
				<div :style='{ "margin": "10px 0 0", "fontSize": "14px", "lineHeight": "28px", "color": "#fff" }'></div>
				<div :style='{ "margin": "10px 0 0", "fontSize": "14px", "lineHeight": "28px", "color": "#fff" }'></div>
				<div :style='{ "margin": "10px 0 0", "fontSize": "14px", "lineHeight": "28px", "color": "#fff" }'></div>
			</div>
		</div>

	</div>
</template>

<script>
import Vue from 'vue'
export default {
	data() {
		return {
			activeIndex: '0',
			roleMenus: [{ "backMenu": [{ "child": [{ "appFrontIcon": "cuIcon-attentionfavor", "buttons": ["新增", "查看", "修改", "删除"], "menu": "用户", "menuJump": "列表", "tableName": "users" }], "menu": "用户管理" }, { "child": [{ "appFrontIcon": "cuIcon-goodsnew", "buttons": ["新增", "查看", "修改", "删除"], "menu": "天气预报", "menuJump": "列表", "tableName": "weather" }], "menu": "天气预报管理" }, { "child": [{ "appFrontIcon": "cuIcon-time", "buttons": ["新增", "查看", "修改", "删除", "查看评论"], "menu": "景点信息", "menuJump": "列表", "tableName": "scenicinfo" }], "menu": "景点信息管理" }, { "child": [{ "appFrontIcon": "cuIcon-newshot", "buttons": ["查看", "删除"], "menu": "门票预订", "menuJump": "列表", "tableName": "ticketreserve" }], "menu": "门票预订管理" }, { "child": [{ "appFrontIcon": "cuIcon-attentionfavor", "buttons": ["新增", "查看", "修改", "删除", "查看评论"], "menu": "酒店信息", "menuJump": "列表", "tableName": "hotelinfo" }], "menu": "酒店信息管理" }, { "child": [{ "appFrontIcon": "cuIcon-keyboard", "buttons": ["查看", "删除"], "menu": "酒店预订", "menuJump": "列表", "tableName": "hotelreserve" }], "menu": "酒店预订管理" }, { "child": [{ "appFrontIcon": "cuIcon-wenzi", "buttons": ["新增", "查看", "修改", "删除", "查看评论"], "menu": "餐厅信息", "menuJump": "列表", "tableName": "restaurantinfo" }], "menu": "餐厅信息管理" }, { "child": [{ "appFrontIcon": "cuIcon-taxi", "buttons": ["查看", "删除"], "menu": "餐厅预约", "menuJump": "列表", "tableName": "restaurantreserve" }], "menu": "餐厅预约管理" }, { "child": [{ "appFrontIcon": "cuIcon-camera", "buttons": ["新增", "查看", "修改", "删除", "查看评论"], "menu": "旅游路线", "menuJump": "列表", "tableName": "trafficroute" }], "menu": "旅游路线管理" }, { "child": [{ "appFrontIcon": "cuIcon-phone", "buttons": ["查看", "删除", "查看评论"], "menu": "旅游攻略", "menuJump": "列表", "tableName": "travelguide" }], "menu": "旅游攻略管理" }, { "child": [{ "appFrontIcon": "cuIcon-addressbook", "buttons": ["查看", "删除"], "menu": "旅游规划", "menuJump": "列表", "tableName": "tourplan" }], "menu": "旅游规划管理" }, { "child": [{ "appFrontIcon": "cuIcon-time", "buttons": ["查看", "修改", "删除"], "menu": "轮播图管理", "tableName": "config" }, { "appFrontIcon": "cuIcon-news", "buttons": ["新增", "查看", "修改", "删除"], "menu": "公告信息", "tableName": "news" }], "menu": "系统管理" }], "frontMenu": [{ "child": [{ "appFrontIcon": "cuIcon-shop", "buttons": ["查看", "订门票"], "menu": "景点信息列表", "menuJump": "列表", "tableName": "scenicinfo" }], "menu": "景点信息模块" }, { "child": [{ "appFrontIcon": "cuIcon-phone", "buttons": ["查看", "订酒店"], "menu": "酒店信息列表", "menuJump": "列表", "tableName": "hotelinfo" }], "menu": "酒店信息模块" }, { "child": [{ "appFrontIcon": "cuIcon-full", "buttons": ["查看", "订餐厅"], "menu": "餐厅信息列表", "menuJump": "列表", "tableName": "restaurantinfo" }], "menu": "餐厅信息模块" }, { "child": [{ "appFrontIcon": "cuIcon-addressbook", "buttons": ["查看"], "menu": "旅游路线列表", "menuJump": "列表", "tableName": "trafficroute" }], "menu": "旅游路线模块" }, { "child": [{ "appFrontIcon": "cuIcon-newshot", "buttons": ["查看"], "menu": "旅游攻略列表", "menuJump": "列表", "tableName": "travelguide" }], "menu": "旅游攻略模块" }], "hasBackLogin": "是", "hasBackRegister": "否", "hasFrontLogin": "否", "hasFrontRegister": "否", "roleName": "管理员", "tableName": "admin" }, { "backMenu": [{ "child": [{ "appFrontIcon": "cuIcon-newshot", "buttons": ["查看", "支付"], "menu": "门票预订", "menuJump": "列表", "tableName": "ticketreserve" }], "menu": "门票预订管理" }, { "child": [{ "appFrontIcon": "cuIcon-keyboard", "buttons": ["查看", "支付"], "menu": "酒店预订", "menuJump": "列表", "tableName": "hotelreserve" }], "menu": "酒店预订管理" }, { "child": [{ "appFrontIcon": "cuIcon-taxi", "buttons": ["查看", "支付"], "menu": "餐厅预约", "menuJump": "列表", "tableName": "restaurantreserve" }], "menu": "餐厅预约管理" }, { "child": [{ "appFrontIcon": "cuIcon-phone", "buttons": ["新增", "查看", "修改", "删除", "查看评论"], "menu": "旅游攻略", "menuJump": "列表", "tableName": "travelguide" }], "menu": "旅游攻略管理" }, { "child": [{ "appFrontIcon": "cuIcon-addressbook", "buttons": ["新增", "查看", "修改", "删除"], "menu": "旅游规划", "menuJump": "列表", "tableName": "tourplan" }], "menu": "旅游规划管理" }], "frontMenu": [{ "child": [{ "appFrontIcon": "cuIcon-shop", "buttons": ["查看", "订门票"], "menu": "景点信息列表", "menuJump": "列表", "tableName": "scenicinfo" }], "menu": "景点信息模块" }, { "child": [{ "appFrontIcon": "cuIcon-phone", "buttons": ["查看", "订酒店"], "menu": "酒店信息列表", "menuJump": "列表", "tableName": "hotelinfo" }], "menu": "酒店信息模块" }, { "child": [{ "appFrontIcon": "cuIcon-full", "buttons": ["查看", "订餐厅"], "menu": "餐厅信息列表", "menuJump": "列表", "tableName": "restaurantinfo" }], "menu": "餐厅信息模块" }, { "child": [{ "appFrontIcon": "cuIcon-addressbook", "buttons": ["查看"], "menu": "旅游路线列表", "menuJump": "列表", "tableName": "trafficroute" }], "menu": "旅游路线模块" }, { "child": [{ "appFrontIcon": "cuIcon-newshot", "buttons": ["查看"], "menu": "旅游攻略列表", "menuJump": "列表", "tableName": "travelguide" }], "menu": "旅游攻略模块" }], "hasBackLogin": "是", "hasBackRegister": "否", "hasFrontLogin": "是", "hasFrontRegister": "是", "roleName": "用户", "tableName": "users" }],
			baseUrl: '',
			carouselList: [],
			menuList: [],
			form: {
				ask: '',
				userid: localStorage.getItem('userid')
			},
			Token: localStorage.getItem('Token'),
			role: localStorage.getItem('role'),
			username: localStorage.getItem('username'),
			notAdmin: localStorage.getItem('sessionTable') != '"admin"',
			timer: '',
			iconArr: [
				'el-icon-star-off',
				'el-icon-goods',
				'el-icon-warning',
				'el-icon-question',
				'el-icon-info',
				'el-icon-help',
				'el-icon-picture-outline-round',
				'el-icon-camera-solid',
				'el-icon-video-camera-solid',
				'el-icon-video-camera',
				'el-icon-bell',
				'el-icon-s-cooperation',
				'el-icon-s-order',
				'el-icon-s-platform',
				'el-icon-s-operation',
				'el-icon-s-promotion',
				'el-icon-s-release',
				'el-icon-s-ticket',
				'el-icon-s-management',
				'el-icon-s-open',
				'el-icon-s-shop',
				'el-icon-s-marketing',
				'el-icon-s-flag',
				'el-icon-s-comment',
				'el-icon-s-finance',
				'el-icon-s-claim',
				'el-icon-s-opportunity',
				'el-icon-s-data',
				'el-icon-s-check'
			],
			// 智能客服AI相关数据
			showChatDialog: false,
			chatMessages: [
				{
					type: 'ai',
					text: '您好！我是智能旅游客服AI助手，很高兴为您服务！我可以帮您：\n1. 推荐热门景点\n2. 查询酒店和餐厅信息\n3. 提供旅游路线建议\n4. 解答旅游相关问题',
					time: this.getCurrentTime()
				}
			],
			userInput: '',
			isAiTyping: false,
			quickQuestions: [
				'推荐热门景点',
				'如何预订门票？',
				'附近的酒店有哪些？',
				'旅游路线建议'
			]
		}
	},
	created() {
		this.baseUrl = this.$config.baseUrl;
		this.menuList = this.$config.indexNav;
		this.getCarousel();
	},
	mounted() {
		this.activeIndex = localStorage.getItem('keyPath') || '0';
	},
	watch: {
		$route(newValue) {
			let that = this
			let url = window.location.href
			let arr = url.split('#')
			for (let x in this.menuList) {
				if (newValue.path == this.menuList[x].url) {
					this.activeIndex = x
				}
			}
			this.Token = localStorage.getItem('Token')
		},
	},
	methods: {
		handleSelect(keyPath) {
			if (keyPath) {
				localStorage.setItem('keyPath', keyPath)
			}
		},
		toLogin() {
			this.$router.push('/login');
		},
		logout() {
			localStorage.clear();
			Vue.http.headers.common['Token'] = "";
			this.$router.push('/index/home');
			this.activeIndex = '0'
			localStorage.setItem('keyPath', this.activeIndex)
			this.Token = ''
			this.$forceUpdate()
			this.$message({
				message: '登出成功',
				type: 'success',
				duration: 1000,
			});
		},
		getCarousel() {
			this.$http.get('config/list', { params: { page: 1, limit: 3 } }).then(res => {
				if (res.data.code == 0) {
					this.carouselList = res.data.data.list;
				}
			});
		},
		goBackend() {
			window.open(`${this.$config.baseUrl}admin/dist/index.html`, "_blank");
		},
		goMenu(path) {
			if (!localStorage.getItem('Token')) {
				this.toLogin();
			} else {
				this.$router.push(path);
			}
		},
		// 智能客服AI相关方法
		goSmartRoute() {
			this.$router.push('/index/smartRoute');
		},
		getCurrentTime() {
			const now = new Date();
			const hours = String(now.getHours()).padStart(2, '0');
			const minutes = String(now.getMinutes()).padStart(2, '0');
			return `${hours}:${minutes}`;
		},
		sendMessage() {
			if (!this.userInput.trim()) return;

			// 添加用户消息
			this.chatMessages.push({
				type: 'user',
				text: this.userInput,
				time: this.getCurrentTime()
			});

			const userQuestion = this.userInput;
			this.userInput = '';

			// 滚动到底部
			this.$nextTick(() => {
				this.scrollToBottom();
			});

			// 显示AI正在输入
			this.isAiTyping = true;

			// 模拟AI回复
			setTimeout(() => {
				this.getAiResponse(userQuestion);
			}, 1500);
		},
		sendQuickQuestion(question) {
			this.userInput = question;
			this.sendMessage();
		},
		async getAiResponse(question) {
			// 简单的关键词匹配AI回复逻辑
			let response = '';

			if (question.includes('景点') || question.includes('推荐')) {
				response = '根据您的需求，我为您推荐以下热门景点：\n\n1. 故宫博物院 - 中国最大的古代文化艺术博物馆\n2. 长城 - 世界文化遗产，中华民族的象征\n3. 颐和园 - 中国古典园林之首\n\n您可以在"景点信息"页面查看更多详情，并预订门票。如需更个性化的推荐，请告诉我您的具体偏好！';
			} else if (question.includes('门票') || question.includes('预订')) {
				response = '预订门票很简单：\n\n1. 在首页浏览推荐景点\n2. 点击"景点信息"查看所有景点\n3. 选择您想参观的景点\n4. 在详情页面点击"订门票"按钮\n5. 填写预订信息并提交\n\n如需帮助，请随时告诉我！';
			} else if (question.includes('酒店')) {
				response = '我们为您精选了多家优质酒店：\n\n1. 豪华型酒店 - 提供高端服务和完善设施\n2. 经济型酒店 - 性价比高，适合预算有限的旅客\n3. 特色民宿 - 体验当地文化风情\n\n您可以在"酒店信息"页面查看详情并预订。请问您有特定的地区或预算需求吗？';
			} else if (question.includes('路线') || question.includes('攻略')) {
				response = '关于旅游路线规划，您可以尝试我们的"智能路线规划"功能：\n\n1. 点击导航栏的"智能路线规划"\n2. 输入起始地和目的地\n3. 选择旅游天数和偏好（人文/自然景观）\n4. 系统将为您生成个性化路线\n\n也可以在"旅游攻略"页面查看其他游客分享的游记哦！';
			} else if (question.includes('餐厅') || question.includes('美食')) {
				response = '当地的美食不容错过！我们为您推荐：\n\n1. 地方特色餐厅 - 品尝正宗本地菜\n2. 知名连锁餐厅 - 品质有保障\n3. 街头小吃 - 体验当地风土人情\n\n在"餐厅信息"页面可以查看详情并预约。有什么特别的口味偏好吗？';
			} else if (question.includes('天气')) {
				response = '在首页的"天气预报展示"区域可以查看各城市的天气情况。建议您出行前查看目的地的天气，合理安排行程！';
			} else {
				response = '感谢您的提问！作为智能旅游客服，我可以帮您：\n\n🏞️ 推荐景点和景点信息查询\n🏨 查询酒店预订信息\n🍕 推荐餐厅和美食\n🗺️ 提供旅游路线规划建议\n🎫 门票预订指导\n🌤️ 天气查询\n\n请告诉我您需要哪方面的帮助？';
			}

			// 隐藏输入状态并添加AI回复
			this.isAiTyping = false;
			this.chatMessages.push({
				type: 'ai',
				text: response,
				time: this.getCurrentTime()
			});

			// 滚动到底部
			this.$nextTick(() => {
				this.scrollToBottom();
			});
		},
		scrollToBottom() {
			const container = this.$refs.chatMessages;
			if (container) {
				container.scrollTop = container.scrollHeight;
			}
		}
	}
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.menu-preview {
	.el-scrollbar {
		height: 100%;

		& ::v-deep .scrollbar-wrapper {
			overflow-x: hidden;
		}
	}
}


.menu-preview .el-menu-horizontal-demo .el-menu-item {
	cursor: pointer;
	border: 0;
	padding: 0 20px;
	margin: 0px 2px 0 2px;
	color: #fff;
	white-space: nowrap;
	display: flex;
	font-size: 14px;
	line-height: 60px;
	box-shadow: none;
	background: none;
	align-items: center;
	position: relative;
	list-style: none;
	height: 60px;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item:hover {
	cursor: pointer;
	padding: 0 20px;
	margin: 5px 2px 0 2px;
	color: #fff;
	white-space: nowrap;
	font-size: 14px;
	border-color: #6bd591;
	line-height: 50px;
	border-radius: 8px;
	box-shadow: inset 0px 0px 2px 0px #b0e8c4;
	background: #bad399;
	border-width: 0;
	position: relative;
	border-style: solid;
	list-style: none;
	height: 50px;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.is-active {
	cursor: pointer;
	padding: 0 20px;
	margin: 5px 2px 0 2px;
	color: #fff;
	white-space: nowrap;
	font-size: 14px;
	border-color: #6bd591;
	line-height: 50px;
	border-radius: 8px;
	box-shadow: inset 0px 0px 2px 0px #b0e8c4;
	background: #bad399;
	border-width: 0;
	position: relative;
	border-style: solid;
	list-style: none;
	height: 50px;
}

.banner-preview {
	.el-carousel ::v-deep .el-carousel__indicator button {
		width: 0;
		height: 0;
		display: none;
	}
}

.banner-preview .el-carousel ::v-deep .el-carousel__container .el-carousel__arrow--left {
	width: 36px;
	font-size: 12px;
	height: 36px;
}

.banner-preview .el-carousel ::v-deep .el-carousel__container .el-carousel__arrow--left:hover {
	background: rgba(168, 168, 168, .4);
}

.banner-preview .el-carousel ::v-deep .el-carousel__container .el-carousel__arrow--right {
	width: 36px;
	font-size: 12px;
	height: 36px;
}

.banner-preview .el-carousel ::v-deep .el-carousel__container .el-carousel__arrow--right:hover {
	background: rgba(168, 168, 168, .4);
}

.banner-preview .el-carousel ::v-deep .el-carousel__indicators {
	margin: 0 0 8px;
	z-index: 2;
	position: absolute;
	list-style: none;
}

.banner-preview .el-carousel ::v-deep .el-carousel__indicators li {
	border-radius: 50%;
	padding: 0;
	margin: 0 5px;
	background: #fff;
	display: inline-block;
	width: 12px;
	opacity: 0.4;
	transition: 0.3s;
	height: 12px;
}

.banner-preview .el-carousel ::v-deep .el-carousel__indicators li:hover {
	border-radius: 50%;
	margin: 0 4px;
	background: #fff;
	display: inline-block;
	width: 12px;
	opacity: 0.7;
	height: 12px;
}

.banner-preview .el-carousel ::v-deep .el-carousel__indicators li.is-active {
	border-radius: 50%;
	margin: 0 4px;
	background: #fff;
	display: inline-block;
	width: 12px;
	opacity: 1;
	height: 12px;
}

.chat-content {
	.left-content {
		width: 100%;
		text-align: left;
	}

	.right-content {
		width: 100%;
		text-align: right;
	}
}

/* 智能客服AI悬浮按钮样式 */
.ai-chat-float-btn {
	position: fixed;
	bottom: 30px;
	right: 30px;
	width: 60px;
	height: 60px;
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	border-radius: 50%;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	cursor: pointer;
	box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
	transition: all 0.3s ease;
	z-index: 9999;
}

.ai-chat-float-btn:hover {
	transform: scale(1.1);
	box-shadow: 0 6px 20px rgba(102, 126, 234, 0.6);
}

.ai-chat-float-btn i {
	font-size: 24px;
	color: #fff;
}

.ai-chat-float-btn span {
	font-size: 10px;
	color: #fff;
	margin-top: 2px;
}

/* 智能客服对话框样式 */
.ai-chat-dialog .el-dialog__header {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	padding: 15px 20px;
}

.ai-chat-dialog .el-dialog__title {
	color: #fff;
	font-size: 16px;
	font-weight: 600;
}

.ai-chat-dialog .el-dialog__headerbtn .el-dialog__close {
	color: #fff;
	font-size: 18px;
}

.chat-container {
	display: flex;
	flex-direction: column;
	height: 500px;
}

.chat-messages {
	flex: 1;
	overflow-y: auto;
	padding: 15px;
	background: #f5f7fa;
	border-radius: 8px;
	margin-bottom: 15px;
}

.chat-message {
	display: flex;
	margin-bottom: 15px;
	align-items: flex-start;
}

.user-message {
	flex-direction: row-reverse;
}

.message-avatar {
	width: 36px;
	height: 36px;
	border-radius: 50%;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-shrink: 0;
}

.ai-message .message-avatar {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	margin-right: 10px;
}

.user-message .message-avatar {
	background: #4E8531;
	margin-left: 10px;
}

.message-avatar i {
	font-size: 18px;
	color: #fff;
}

.message-content {
	max-width: 70%;
}

.ai-message .message-content {
	background: #fff;
	border-radius: 12px;
	border-top-left-radius: 4px;
	padding: 12px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.user-message .message-content {
	background: #4E8531;
	border-radius: 12px;
	border-top-right-radius: 4px;
	padding: 12px;
	box-shadow: 0 2px 8px rgba(78, 133, 49, 0.2);
}

.message-text {
	font-size: 14px;
	line-height: 1.6;
	color: #333;
	white-space: pre-wrap;
	word-break: break-word;
}

.user-message .message-text {
	color: #fff;
}

.message-time {
	font-size: 11px;
	color: #999;
	margin-top: 5px;
}

.user-message .message-time {
	color: rgba(255, 255, 255, 0.8);
	text-align: right;
}

/* 打字指示器动画 */
.typing-indicator {
	display: flex;
	gap: 4px;
	padding: 8px 12px;
}

.typing-indicator span {
	width: 8px;
	height: 8px;
	border-radius: 50%;
	background: #ccc;
	animation: typing 1.4s infinite;
}

.typing-indicator span:nth-child(2) {
	animation-delay: 0.2s;
}

.typing-indicator span:nth-child(3) {
	animation-delay: 0.4s;
}

@keyframes typing {
	0%, 60%, 100% {
		transform: translateY(0);
		opacity: 0.7;
	}
	30% {
		transform: translateY(-10px);
		opacity: 1;
	}
}

/* 快捷问题区域 */
.quick-questions {
	display: flex;
	flex-wrap: wrap;
	gap: 8px;
	align-items: center;
	padding: 10px;
	background: #f5f7fa;
	border-radius: 8px;
}

.quick-label {
	font-size: 13px;
	color: #666;
	font-weight: 500;
}

.quick-tag {
	cursor: pointer;
	transition: all 0.2s ease;
	background: #fff;
	border: 1px solid #667eea;
	color: #667eea;
}

.quick-tag:hover {
	background: #667eea;
	color: #fff;
	transform: translateY(-2px);
}
</style>
