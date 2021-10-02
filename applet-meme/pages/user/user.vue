<!--页面名字:  我的 -->
<template>
	<view class="user-content">
		<view class="user-head">
			<view class="user-info">
				<image :src="userInfo.avatar?userInfo.avatar:'../../static/images/avatar.jpeg'"></image>
				<view class="user-name" v-if="userInfo.nickName">{{userInfo.nickName}}</view>
				<!-- <button @getuserinfo="login" :plain="true" open-type="getUserInfo" class="user-name" style="font-size: 35upx;border: none;" v-else>点击登录,记录点点滴滴..></button> -->
				<button @click="loginAction" :plain="true"  class="user-name" style="font-size: 35upx;border: none;" v-else>点击登录,记录点点滴滴..></button>
			</view>
		</view>
<!-- 		<view class="user-timu-info">
			<view class="answer-number" @click="navTo('/pages/personal/myCollect')"  style="border-right: 1px solid #E4E4E4;">
				<view class="card-title">收藏</view>
				<view class="card-value">{{userInfo.collect?userInfo.collect:0}}</view>
			</view>
			<view class="answer-numbers" @click="navTo('/pages/WrongTopic/WrongTopic')">
				<view class="card-title">错题本</view>
				<view class="card-value">{{userInfo.errNum?userInfo.errNum:0}}</view>
			</view>
		</view> -->
<!-- 		<view class="user-util">
			<list-cell icon="icon-quyu" title="考区" :tips="userInfo.area" navigateType="right" @eventClick="navTo('/pages/personal/setArea')"></list-cell>
			<list-cell icon="icon-zhinan" title="报考指南" navigateType="right" @eventClick="navTo('/pages/guide/guide')" ></list-cell>
			<button  class="user-item" open-type="contact">
				<image class="user-item-ic" src="../../static/wo.png" mode="widthFix" />
				<text>联系我们</text>
				<text class="cell-more yticon icon-you" style="text-align: right;"></text>
			</button>
			<list-cell icon="icon-ziliao" title="免费领取资料" @eventClick="switBar('/pages/freedata/freedata')" ></list-cell>
		</view> -->
	</view>
</template>

<script>
	import listCll from '../../components/list-cell/list-cell.vue'
	import { mapState } from 'vuex'
	import { mapMutations } from 'vuex'
	import MinxinShare from '../../mixins/share.js'
	export default {
		mixins:[
			MinxinShare
		],
		components:{listCll},
		data() {
			return {
			};
		},
		onLoad() {
		},
		onShow() {
		},
		computed:{
			...mapState(['userInfo'])
		},
		methods:{
			//微信用户登录
			loginAction(){
				var that = this;
				wx.getUserProfile({
				  desc: "获取你的昵称、头像、地区及性别",
				  success: res => {
				    console.log("新版userInfo",res)
					var userinfo = res.userInfo;
					uni.login({
					  provider: 'weixin',
					  success: function (loginRes) {
						  uni.showLoading({
						  	mask:true,
							title:"加载中..."
						  })
						  console.log("登录code",loginRes);
						// 获得登录凭证code,将其传到后台,进行最终的登录操作获取openId
						if(loginRes.code){
							userinfo['code'] = loginRes.code
							that.$http.post('/app/applet/login',userinfo).then(resp=>{
								console.log("登陆成功",resp);
								//将数据保存在状态管理器中
								that.$store.commit("setUserInfo",resp.data) //
								console.log("将数据保存在状态管理器中");
								//将数据保存在用户的手机内存中
								uni.setStorage({
								    key: 'userInfo',
								    data: resp.data,
								    success: function () {
										console.log("将数据保存在用户的手机内存中");
										uni.hideLoading()
								    }
								});
							}).catch(err=>{
								console.log("登录失败");
								uni.showModal({
									content:"登录失败",
									showCancel:false
								})
								uni.hideLoading()
							})
						}
						uni.hideLoading()
					  }
					});
				  },
				  fail: res => {
				  	 //拒绝授权
				    // that.showErrorModal('您拒绝了请求');
				    return;
				  }
				})
			},
			navTo(toUrl){
				uni.navigateTo({
					url:toUrl
				})
			},
			switBar(toUrl){
				uni.switchTab({
					url:toUrl
				})
			},


		}
	}
</script>

<style lang="scss" scoped>
	.user-content{
		height: 100vh;
		background-color: #F9F9F9;
		.user-head{
			width: 100%;
			min-height: 115upx;
			// text-align: center;
			background: $navBarColor;
			// background: rgba(240,210,100,0.2);
			border-bottom-left-radius: 40upx;
			border-bottom-right-radius: 40upx;
			.user-info{
				padding: 20upx;
				image{
					width: 150upx;
					height: 150upx;
					border-radius: 50%;
					vertical-align: top;
					margin: 20upx;
					margin-left: 50upx;
				}
				.user-name{
					display: inline-block;
					height: 150upx;
					vertical-align: top;
					line-height: 150upx;
					font-size: 60upx;
					margin: 20upx;
					// margin: 20upx 75upx 0upx 0upx;
				}
			}
		}
		.user-timu-info{
			width: 95%;
			margin: 0 auto;
			margin-top: 20upx;
			margin-bottom: 20upx;
			.answer-number,.answer-numbers{
				width: 50%;
				height: 150upx;
				background-color: #FFFFFF;
				display: inline-block;
				text-align: center;
				font-size: 30upx;
				.card-title{
					margin-top: 30upx;
					font-size: 35upx;
					// color: #E0E0E0;
				}
				.card-value{
					margin-top: 10upx;
					// color: #ffff00 ;
				}
			}
		}
		.user-util{
			width: 95%;
			background-color: #FFFFFF;
			margin: 0 auto;
		}
	
	}
	.user-item {
		display: flex;
		align-items: center;
		border: 0;
		border-bottom: 1px solid #f1f1f1;
		background-color: #fff;
		border-radius: 0;
		padding: 15upx 0;
		&:after {
			border: 0;
		}
		&:last-child {
			border-bottom: 0;
		}
		.user-item-ic {
			display: block;
			width: 50rpx;
			height: 50rpx;
			margin-right: 10rpx;
		}
		.user-item-arrow {
			display: block;
			width: 40rpx;
			height: 40rpx;
			margin-right: 40rpx;
		}
		text {
			display: block;
			flex: 1;
			font-size: 32rpx;
			text-align: left;
		}
	}
	
</style>
