<!-- 排版页面 -->
<template>
    <!-- 侧边栏区域begin -->
    <div class = "menu">
        <SideBar />
    </div>
    <!-- 侧边栏区域end -->
    <!-- 右侧功能区域begin -->
    <div class = "func-zone">
        <!-- <dashboard v-if="choice == 'dashboard'"/>
        <segmentation v-if="choice == 'segmentation'"/> -->
        <explosion_identify/>
        <!-- <vibration v-if="choice == 'vibration'"/> -->
    </div>
</template>

<script setup>
import { ref, reactive, onMounted, onUpdated, computed} from 'vue'
import SideBar from '@/components/SideBar.vue'
// import dashboard from '@/views/layout/dashboard/index.vue'
// import segmentation from '@/views/layout/segmentation/index.vue'
import explosion_identify from '@/views/layout/explosion_identify/index.vue'
// import vibration from '@/views/layout/vibration/index.vue'
import { useRoute } from 'vue-router'

const route = useRoute();
const choice = ref();//根据路由传值对应功能区的显示内容

// // 监听路由变化并执行不同逻辑
// onMounted(() => {
//   updateLogic();
// });
// onUpdated(() => {
//   updateLogic();
// });

</script>

<style scoped>
.menu{
    position: fixed;
    left:0%;
    top:0%;
    width:15%;
    height:100%;
    background-color: rgb(0, 23, 52);
    color:white;
}
.func-zone{
    position: absolute;
    left:15%;
    top:0%;
    width:85%;
    height:100%;
}
</style>