import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
    import ticketreserve from '@/views/modules/ticketreserve/list'
    import tourplan from '@/views/modules/tourplan/list'
    import news from '@/views/modules/news/list'
    import discusstravelguide from '@/views/modules/discusstravelguide/list'
    import scenicinfo from '@/views/modules/scenicinfo/list'
    import discusstrafficroute from '@/views/modules/discusstrafficroute/list'
    import travelguide from '@/views/modules/travelguide/list'
    import users from '@/views/modules/users/list'
    import discussscenicinfo from '@/views/modules/discussscenicinfo/list'
    import config from '@/views/modules/config/list'
    import trafficroute from '@/views/modules/trafficroute/list'
    import merchant from '@/views/modules/merchant/list'
    import dictionary from '@/views/modules/dictionary/list'


//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '系统首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '系统首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }
      ,{
	path: '/ticketreserve',
        name: '门票预订',
        component: ticketreserve
      }
      ,{
	path: '/tourplan',
        name: '旅游规划',
        component: tourplan
      }
      ,{
	path: '/news',
        name: '公告信息',
        component: news
      }
      ,{
	path: '/discusstravelguide',
        name: '旅游攻略评论',
        component: discusstravelguide
      }
      ,{
	path: '/scenicinfo',
        name: '景点信息',
        component: scenicinfo
      }
      ,{
	path: '/discusstrafficroute',
        name: '路线评论',
        component: discusstrafficroute
      }
      ,{
	path: '/travelguide',
        name: '旅游攻略',
        component: travelguide
      }
      ,{
	path: '/users',
        name: '用户',
        component: users
      }
      ,{
	path: '/discussscenicinfo',
        name: '景点信息评论',
        component: discussscenicinfo
      }
      ,{
	path: '/config',
        name: '轮播图管理',
        component: config
      }
      ,{
	path: '/trafficroute',
        name: '路线',
        component: trafficroute
      }
      ,{
	path: '/merchant',
        name: '商家',
        component: merchant
      }
      ,{
	path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '系统首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}
export default router;
