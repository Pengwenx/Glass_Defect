import Request from "@/utils/Request.js";  // 在每个 api 文件里都要引入这两个文件
import Message from "@/utils/Message.js"  // 在每个 api 文件里都要引入这两个文件
import router from "@/router/index.js"
import store from "@/store/index.js"

// 图像上传
//！！接口与后端对应，如需使用请对接口地址等信息做更改（同时更改utils下的Request.js）
export function UploadImg(FormData) { 
    return Request({  // 发送请求
        method: 'POST',
        headers: {
            'Content-Type': 'application/form-data', // 设置请求头
            //'Content-Type': 'application/x-www-form-urlencoded',
        },
        url: '/upload',  // 与后端接口对应！！！
        data: FormData, // 使用FormData作为请求体
    }).then(function (response) {  // then 表示成功接收到响应后的操作
        if (response.data === "successfully") {
            console.log("respons数据");
            console.log(response);
            Beginyolo();
            Message.success("上传成功");  // 正确响应，返回数据
        } else {
            Message.success("上传失败");
        }
    }).catch(function (error) {  // catch 表示接收到错误响应后的操作
        console.log(error);
    });
}

export function Beginyolo(){
    return Request({  // 发送请求
        method: 'GET',
        url: '/yolo',  // 与后端接口对应！！！
    }).then(function (response) {  // then 表示成功接收到响应后的操作
        if (response.data === "runsuccessfully") {
            Message.success("识别成功");
            store.state.process_status = !store.state.process_status;
            console.log(response.data); // 检查返回的数据
            //return response;  
        } else {
            Message.success("识别失败");
        }
    }).catch(function (error) {  // catch 表示接收到错误响应后的操作
        console.log(error);
    });
}