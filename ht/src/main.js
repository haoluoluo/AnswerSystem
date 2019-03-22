// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/router'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Qs from 'qs'
import VueAxios from 'vue-axios'
import {getCookie, setCookie, removeCookie} from './util/cookie'
import webrequest from './util/webrequest'
import axios from 'axios'
import $ from 'jquery'
window.jquery = window.$ = $

Vue.use(VueRouter)
Vue.use(VueAxios, axios)
Vue.use(ElementUI)
Vue.config.productionTip = false

axios.defaults.withCredentials = true
Vue.config.productionTip = false
Vue.prototype.$qs = Qs
Vue.use(webrequest)
Vue.prototype.$cookieHandle = {getCookie, setCookie, removeCookie}
// 接口地址
Vue.prototype.$apiUrl = '/as-api' // api
// Vue.prototype.$dkUrl = '/api'; // dk
// Vue.prototype.$stockUrl = '/stockapi'; // stock
// Vue.prototype.$sharekUrl = 'https://test.iwill.beigebigdata.com'; // shareUrl: https://test.iwill.beigebigdata.com   https://xnt.beigebigdata.com
// 路由判断登录 根据路由配置文件的参数
// 路由拦截
router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    if (getCookie('adminId')) {
      next()
    } else {
      next({
        path: '/admin/login'
      })
    }
  } else {
    next()
  }
})
// const url = '/as-api'
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
