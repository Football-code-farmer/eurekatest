import Vue from 'vue'
import Router from 'vue-router'
import upload from '@/components/upload'

import Home from '@/components/Home'
import EmpBasic from '@/components/emp/EmpBasic'
import login from '@/components/views/login';
import logout from '@/components/views/logout';
import register from '@/components/views/register';
Vue.use(Router)

export default new Router({
  routes: [

    //登录
    {
      path: '/',
      name: 'login',
      component: login
    },
    //注册
    {
      path:'/register',
      name: 'register',
      component: register
    },
    //主界面
    {
      path: '/home',
      name: 'home',
      meta: {
        requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录的
      },
      component: Home,
      children: [
        {
          path: '/emp/basic',
          name: 'EmpBasic',
          component: EmpBasic
        }
      ]
    },
    //退出
    {
      path: '/logout',
      name: 'logout',
      component: logout
    }
  ]
})
