import { createRouter, createWebHashHistory } from 'vue-router'

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            redirect: '/explosion_identify'  // 重定向
        },
        {
            path: '/explosion_identify',//直接设置为玻璃内爆识别页面
            name: 'explosion_identify',  
            component: () => import('@/views/layout/index.vue'),
        },
    ]
})

export default router
