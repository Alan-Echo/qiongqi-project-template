<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		},
		created: function() {
			var that = this;
			//将保存在用户手机内存中的用户账号数据取出,
			uni.getStorage({
			    key: 'userInfo',
			    success: function (res) {
			        console.log("初始化",res.data);
					that.$store.commit("setUserInfo",res.data) //将取出的数据保存在状态管理器中
			    }
			});
		},
	}
</script>

<style>
	/*每个页面公共css */
</style>
