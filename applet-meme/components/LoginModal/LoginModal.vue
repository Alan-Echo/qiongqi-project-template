<template>
	<view class="box-container">
		<view class="box-mask">
			<view class="box-body">
				<view class="box-modal">
					<view class="box-title">
						{{title}}
					</view>
					<view class="box-contents">
						{{content}}
					</view>
					<view class="box-btns">
						<view class="l-btn">
							<button type="primary" @click="canlc">取消</button>
						</view>
						<view class="r-btn">
							<button @click="loginAction" type="primary" >登录</button>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import { mapState } from 'vuex'
	export default {
		props:{
			title:{
				rtpe:String,
				default:"温馨提示",
			},
			content:{
				rtpe:String,
				default:"请先登录",
			}
		},
		data() {
			return {
				
			};
		},
		computed:{
			...mapState(['userInfo'])
		},
		// onLoad() {
		// 	var that = this;
		// 	uni.showModal({
		// 		confirmText:'登录',
		// 		content:'请先登录',
		// 		success() {
		// 			that.loginAction()
		// 		}
		// 	})
		// },
		methods:{
			canlc(){
				console.log("取消");
				this.$emit("close")
			},
			//微信用户登录
			loginAction(){
				uni.showLoading({
					mask:true,
					title:'加载中...'
				})
				var that = this;
				wx.getUserProfile({
				  desc: "获取你的昵称、头像、地区及性别",
				  success: res => {
				    console.log("新版userInfo",res)
					var userinfo = res.userInfo;
					uni.login({
					  provider: 'weixin',
					  success: function (loginRes) {
						  console.log("登录code",loginRes);
						// 获得登录凭证code,将其传到后台,进行最终的登录操作获取openId
						if(loginRes.code){
							userinfo['code'] = loginRes.code
							that.$http.post('/app/applet/login',userinfo).then(resp=>{
								console.log("登陆成功",resp);
								// that.userInfo = resp.data
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
								that.$emit("close")
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
		},
	}
</script>

<style lang="scss" scoped>
	.box-container{
		position: fixed;
		top: 0;
		bottom: 0;
		right: 0;
		left: 0;
		background-color: rgba($color: #000000, $alpha: 0.5);
		z-index: 19;
		// .box-mask{
		// 	top: 0;
		// 	bottom: 0;
		// 	background-color: #CDCDCD;
		// 	z-index: 19;
		// }
		.box-mask{
			display: table;
			width: 100%;
			height: 100%;
			.box-body{
				z-index: 20;
				width: 70%;
				display: table-cell;
				vertical-align: middle;
				.box-modal{
					width: 70%;
					background-color: $modelColor;
					margin: 0 auto;
					.box-title{
						width: 100%;
						text-align: center;
					}
					.box-contents{
						padding: 20upx;
						text-align: center;
					}
					.box-btns{
					width: 100%;
					.l-btn,.r-btn{
						width: 50%;
						button{
							background-color: $modelColor;
							color: #000000;
							border-radius: 0;
						}
						button:after{
							border-radius: 0;
						}
					}
					.l-btn{
						float: left;
					}
					.r-btn{
						float: right;
					}
				}
			}
		}
	}
	
	}
</style>
