import VueRouter from 'vue-router'

//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import usersList from '../pages/users/list'
import usersDetail from '../pages/users/detail'
import usersAdd from '../pages/users/add'
import scenicinfoList from '../pages/scenicinfo/list'
import scenicinfoDetail from '../pages/scenicinfo/detail'
import scenicinfoAdd from '../pages/scenicinfo/add'
import ticketreserveList from '../pages/ticketreserve/list'
import ticketreserveDetail from '../pages/ticketreserve/detail'
import ticketreserveAdd from '../pages/ticketreserve/add'
import trafficrouteList from '../pages/trafficroute/list'
import trafficrouteDetail from '../pages/trafficroute/detail'
import trafficrouteAdd from '../pages/trafficroute/add'
import travelguideList from '../pages/travelguide/list'
import travelguideDetail from '../pages/travelguide/detail'
import travelguideAdd from '../pages/travelguide/add'
import tourplanList from '../pages/tourplan/list'
import tourplanDetail from '../pages/tourplan/detail'
import tourplanAdd from '../pages/tourplan/add'
import feedbackList from '../pages/feedback/list'
import feedbackDetail from '../pages/feedback/detail'
import feedbackAdd from '../pages/feedback/add'
import myticketList from '../pages/myticket/list'
import pay from '../pages/pay'
import smartRouteList from '../pages/smartRoute/list'



const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes: [
		{
			path: '/',
			redirect: '/index/home'
		},
		{
			path: '/index',
			component: Index,
			children: [
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'users',
					component: usersList
				},
				{
					path: 'usersDetail',
					component: usersDetail
				},
				{
					path: 'usersAdd',
					component: usersAdd
				},
				{
					path: 'scenicinfo',
					component: scenicinfoList
				},
				{
					path: 'scenicinfoDetail',
					component: scenicinfoDetail
				},
				{
					path: 'scenicinfoAdd',
					component: scenicinfoAdd
				},
				{
					path: 'ticketreserve',
					component: ticketreserveList
				},
				{
					path: 'ticketreserveDetail',
					component: ticketreserveDetail
				},
				{
					path: 'ticketreserveAdd',
					component: ticketreserveAdd
				},
				{
					path: 'trafficroute',
					component: trafficrouteList
				},
				{
					path: 'trafficrouteDetail',
					component: trafficrouteDetail
				},
				{
					path: 'trafficrouteAdd',
					component: trafficrouteAdd
				},
				{
					path: 'travelguide',
					component: travelguideList
				},
				{
					path: 'travelguideDetail',
					component: travelguideDetail
				},
				{
					path: 'travelguideAdd',
					component: travelguideAdd
				},
				{
					path: 'tourplan',
					component: tourplanList
				},
				{
					path: 'tourplanDetail',
					component: tourplanDetail
				},
				{
					path: 'tourplanAdd',
					component: tourplanAdd
				},
				{
					path: 'feedback',
					component: feedbackList
				},
				{
					path: 'feedbackDetail',
					component: feedbackDetail
				},
				{
					path: 'feedbackAdd',
					component: feedbackAdd
				},
				{
					path: 'myticket',
					component: myticketList
				},
				{
					path: 'pay',
					component: pay
				},
				{
					path: 'smartRoute',
					component: smartRouteList
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},

	]
})
