import Vue from 'vue'
// element
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// reset
import './assets/css/reset.css'
// theme
import './assets/css/theme.css'

// iconfont
import './assets/iconfont/iconfont.css'
// iconSVG
import './assets/iconfont/iconfont.js'

// type animation
import VueTypedJs from 'vue-typed-js';

// axios
import axios from 'axios';
Vue.prototype.$http = axios

import router from './router'
import App from './App.vue'

Vue.config.productionTip = false
Vue.use(VueTypedJs)
Vue.use(ElementUI)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')