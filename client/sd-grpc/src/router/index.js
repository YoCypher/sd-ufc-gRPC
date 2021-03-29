import Vue from 'vue'
import VueRouter from 'vue-router'
import CarsView from '../views/CarsView.vue'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/cars',
      component: CarsView
    },
    {
      path: '*',
      redirect: '/cars'
    }
  ]
})


export default router
