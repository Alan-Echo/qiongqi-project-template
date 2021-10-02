import Vue from 'vue'
import App from './App'
import store from './store'
import http from './utils/http.js'

Vue.prototype.$store = store
Vue.prototype.$http = http

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
