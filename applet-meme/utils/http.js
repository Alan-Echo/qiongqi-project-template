//引入状态管理器
import store from '../store/index.js'

// uni-app请求封装

//后台接口
const API ="http://localhost:9596/prj";
// const API ="https://api.applet.guaikavideo.cn/graduation";

//封装post请求
function post (router,data={}) {
        // 返回promise
        return new Promise((resolve,reject) => {
            // 请求
            uni.request({
                url: API + router,
                data: data,
                method:'POST',
                header: {
                    'content-type': 'application/json' ,
					'X-Token': store.state.userInfo.token
                },
                success: (res) => {
					var resp = res.data
					if(resp.status == 200){
						// 将结果抛出
						resolve(resp)
					}else{
						uni.showToast({
						    title: res.msg,
							icon:'none',
						    duration: 2000
						});
						reject(resp);
					}
                },
				fail(err) {
					uni.showToast({
					    title: err.msg,
						icon:'none',
					    duration: 2000
					});
					reject(err);
				}
            })
        })    
}

//封装get请求
function get (router,dataForm) {
        // 返回promise
        return new Promise((resolve,reject) => {
            // 请求
            uni.request({
                url: API + router,
                method:'GET',
				data:dataForm,
                header: {
                    'content-type': 'application/json' ,
					'X-Token': store.state.userInfo.token
                },
                success: (res) => {
					// console.log("请求完成",res);
					var resp = res.data
					if(resp.status == 200){
						// 将结果抛出
						resolve(resp)
					}else{
						uni.showToast({
						    title: resp.msg,
						    duration: 2000
						});
					}
                },
				fail(err) {
					// console.log("发生错误",err);
					uni.showToast({
					    title: err.msg,
					    duration: 2000
					});
				},
				complete(e) {
					if(e.errMsg.replace(" ","")=="request:fail"){
						uni.hideLoading()
						uni.showModal({
							showCancel:false,
							content:"网络异常"
						})
					}
				}
            })
        })    
}

function getAPI(){
	return API;
}

export default{
	post,
	get,
	getAPI
}