import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);//vue的插件机制

//Vuex.Store 构造器选项
const store = new Vuex.Store({
    state:{//存放状态
        "userInfo":{
			"avatar":"", // 头像
			"nickName":"",
			"token":"",
			"id":0,
		}
    },
	mutations:{
		setUserInfo(state,data){
			state.userInfo = data
			console.log("数据更新");
		},
		setUserAreaId(state,id){
			state.userInfo.areaId = id
		}
	},
})
export default store