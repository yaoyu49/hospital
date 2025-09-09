import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  { path: '/login', name: 'Login', component: () => import('../pages/Login.vue') },
  { path: '/', name: 'Home', component: () => import('../pages/Home.vue') }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;