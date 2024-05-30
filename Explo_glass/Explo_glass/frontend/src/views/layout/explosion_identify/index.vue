<!-- 玻璃内爆识别页面 -->
<template>
    <!-- <el-button @click="GoToDash">进入仪表盘</el-button> -->
    <!-- 上传图片begin -->
    <div class="upload-container">
        <el-divider  content-position="center">本地上传</el-divider>
        <ImgUploader ref="ImgUploadRef" @uploadPicture="upload" />
        <el-divider style="margin-top:60px" content-position="center">历史图片</el-divider>
        <div class="el-upload__tip">
            点击查看用户图片
        </div>
        <el-image :src="images[0]" :preview-src-list="images" style="width: 200px; height: 150px; margin-top:10px; object-fit:contain">
        </el-image>

        <!-- <img v-if="images[0]" :src="images[0]" /> -->
    </div>
    <!-- 上传图片end -->
    <div>
        <el-divider style="margin-top:60px;width: 68%; left: 28% " content-position="center">识别结果</el-divider>
        <el-scrollbar class="scrollbar-container">
            <img v-if="imageUrl" :src="imageUrl" alt="Annotated Image"
                :style="{ 'width': '100%', 'height': '100%' }" />
            <el-button v-if="imageUrl" type="primary" @click="saveImage">保存图片</el-button>
        </el-scrollbar>
    </div>
</template>

<script setup>
import { ref, watch } from 'vue'
import axios from 'axios'
import router from "@/router/index.js"
import Message from "@/utils/Message.js"
import ImgUploader from '@/components/ImgUploader.vue'
import { UploadImg } from '@/api/public.js'
import { useStore } from 'vuex'
import screen from '@/assets/images/1.jpg'


// const fileList = ref();
const ImgUploadRef = ref(null); //上传的图片
const imageUrl = ref(null); //分割后获得的图片
const store = useStore()

const fetchImage = async () => {
    try {
        const response = await axios.get('http://localhost:8021/images', {
            responseType: 'arraybuffer',
        });
        const imageBlob = new Blob([response.data], { type: 'image/jpeg' });
        imageUrl.value = URL.createObjectURL(imageBlob);
        console.log("display数据");
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
};

const saveImage = async () => {
    try {
        const response = await axios.get('http://localhost:8021/save')
        // 处理后端返回的数据
        console.log(response.data);
        Message.success("保存成功");
    } catch (error) {
        console.error(error);
        Message.success("保存失败");
    }
};

const images = ref([]); // 创建一个空数组来存储图片
fetch('http://localhost:8021/api/images/display5')
   .then(response => response.json())
    .then(imageBase64List => {
        images.value = imageBase64List.map(base64Image => 'data:image/jpeg;base64,' + base64Image);
    })
    .catch(error => console.error('Error fetching images:', error));

const upload = (val) => {
    console.log(val.fileList[0].raw) //图片raw文件

    let formData = new FormData();
    formData.append('photo', val.fileList[0].raw);
    //formData.append('func', 'B');

    UploadImg(formData)
    // .then(function (result) {  // result 是 api /user/login 的返回值，在后端 api 定义
    //     // 接收返回值，放在 person_info 变量中
    //     console.log(result)
    //     after_upload(result);
    // })
    // .catch(function (error) {
    //     console.log(error);
    // });
    watch(() => store.state.process_status, (newStatus, oldStatus) => {
        fetchImage();
    });

}

// const beforeUpload = (file) => {
//   // 在上传前进行一些处理，如判断文件类型、大小等
//   // 如果不希望上传这个文件，返回 false；如果希望上传这个文件，返回 true
//     return true;
// }
// const uploadHttpRequest = (param) => {
//   //自定义上传方法
//     console.log(param.file);//查看是否选取到文件

// }
// const handleRemove = (file, fileList) => {
//     //  文件列表移除文件时的钩子
//     Message.warning("文件已移除");
// }

const after_upload = (result) => {
    ImgResult.value = result;//后期需要修改，先这么写
}
</script>

<style scoped>
.upload-container {
    position: absolute;
    top: 15%;
    left: 5%;
}

.scrollbar-container {
    position: absolute;
    top: 13%;
    left: 28%;
    max-height: 80%;
    width: 68%;
    text-align: left;
    border: 1px solid black;
    border-radius: 1%;
}
</style>