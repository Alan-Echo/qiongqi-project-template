import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'
/**
 * 系统路由配置
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },
  {
    path: '/user',
    component: Layout,
    name: 'user',
    meta: { title: '用户管理', icon: 'el-icon-user' },
    children: [
      {
        path: 'user',
        name: 'user',
        component: () => import('@/views/user/index'),
        meta: { title: '用户管理', icon: 'el-icon-user' }
      }
    ]
  },
  {
    path: '/order',
    component: Layout,
    name: 'order',
    meta: { title: '订单管理', icon: 'icon-dingdanguanli' },
    children: [
      {
        path: 'order',
        name: 'order',
        component: () => import('@/views/order/vOrder'),
        meta: { title: '订单管理', icon: 'icon-dingdanguanli' }
      }
    ]
  },
  {
    path: '/vip',
    component: Layout,
    name: 'vip',
    meta: { title: 'vip管理', icon: 'icon-VIP' },
    children: [
      {
        path: 'vip',
        name: 'vip',
        component: () => import('@/views/vip/vVip'),
        meta: { title: 'vip管理', icon: 'icon-VIP' }
      }
    ]
  },
  {
    path: '/sys',
    component: Layout,
    redirect: '/sys',
    meta: { title: '系统设置', icon: 'el-icon-setting' },
    children: [
      {
        path: 'admin',
        name: 'admin',
        component: () => import('@/views/sys/admin/index'),
        meta: { title: '管理员', icon: 'icon-guanliyuan' }
      },
      {
        path: 'qiniu',
        name: 'qiniu',
        component: () => import('@/views/sys/qiniu/vQiniu'),
        meta: { title: '七牛云管理', icon: 'icon-yuncunchu' }
      },
      {
        path: 'applet',
        name: 'applet',
        component: () => import('@/views/sys/applet/index'),
        meta: { title: '小程序配置', icon: 'icon-xiaochengxu' }
      },
      {
        path: 'pay-config',
        name: 'pay-config',
        component: () => import('@/views/sys/payconfig/vPayconfig'),
        meta: { title: '支付配置', icon: 'icon-tubiao_zhifu-shanghuzhifu' }
      }
  ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
