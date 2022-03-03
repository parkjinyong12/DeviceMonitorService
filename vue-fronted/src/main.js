import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'

Vue.config.productionTip = false

var Foo = { template: '<div>foo</div>' }
var Bar = { template: '<div>bar</div>' }

var routes = [
{ path: '/foo', component: Foo },
{ path: '/bar', component: Bar }
]

var router = new VueRouter({
 routes
})

var app = new Vue({
  router
}).$mount('#app')
