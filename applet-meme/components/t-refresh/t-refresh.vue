<template>
	<view :style="{height:height,paddingTop:tPadding+'rpx'}" class="refresh-body" @touchmove.stop.prevent>
	  <scroll-view scroll-y  @touchstart="touchstart" @scrolltolower="loadMore" @touchmove="touchmove" @touchend="touchend" class="refresh-scroll">
		  <!-- logo -->
		  <view :class="icon_class" class="refresh-icon" :style="{top:top+'px',transform: `translateX(-50%) rotate(${rotate_number}deg)`}">
		  	<!-- <text class="iconfont">&#xe609;</text> -->
		  	<image src="/static/tRefresh/refresh.png" mode="widthFix"/>
		  </view>
		   <view class="content">
			    <!-- 内容 -->
				<slot name="content"></slot>
				<!-- 上拉加载 -->
				<view class="t-loading-box" v-if="loadingType!=3&&openLoadMore" @click="loadMore">
					<view class="t-line"></view>
					<view class="t-loading-text">
						<view class="loading" v-if="loadingType==2"></view>
						<text>{{loadingText[loadingType]}}</text>
					</view>
					<view class="t-line"></view>
				</view>
		   </view> 
	  </scroll-view> 
	</view> 
</template> 

<script> 
export default {
	name:'t-refresh',
	props:{
		//高度
		height: {
			type: String,
			default: '100vh'
		},
		//padding高度  配合tabs
		tPadding: {
			type: [String||Number],
			default: '0'
		},
		//是否开启上拉加载
		openLoadMore:{
			type: Boolean,
			default: true
		},
		//是否开启下拉刷新
		openRefresh:{
			type: Boolean,
			default: true
		},
		//上拉状态  0 上拉加载更多 1 加载全部 2 上拉加载中 3 不展示组件
		loadingType: {
			type : Number,
			default: 0
		},
		//上拉状态文字
		loadingText : {
			type  : Array,
			default :["上拉或点击加载更多","已加载全部数据",'加载中','']
		},
	},
  data() {
    return {
	  icon_class:'',
	  top:-90,
      touchstart_num:0,  //点击位置
	  touchmove_num:0,   //移动距离
	  move_number:0, //拖动距离
	  rotate_number:0,
	  timer:null,
	  is_refresh:false, //是否在刷新
	  move_timer:null,
    };
  },
  methods: {
	  //触底加载更多
	  loadMore(e) {
	  	if(this.openLoadMore){
			this.$emit("loadMore")
		}
	  },
	  //点击位置
	  touchstart(e){
		  this.touchstart_num=e.touches[0].clientY
	  },
	  //移动距离
	  touchmove(e){
		  if(!this.is_refresh&&e.touches.length<=1&&this.openRefresh){
			  this.move_number=e.touches[0].clientY-this.touchstart_num
			  this.rotate_number=this.move_number*2
			  if(this.move_number<120){
					this.top=(this.move_number)/1-90
			  }else if(120<=this.move_number&&this.move_number<=170){
					this.top=(this.move_number)/4
			  }else{
					this.top=(this.move_number)/60+40
			  }
			  clearTimeout(this.timer)
			  this.timer = setTimeout(()=>{
				this.touchmove_num=e.touches[0].clientY
			  },500) 
		  }
	  },
	  //结束 
	  touchend(e){
		  if(this.is_refresh||e.touches.length>=1||!this.openRefresh){return}
		  if(this.move_number>170){
			  this.icon_class='refresh-icon-active'
			  this.top+=Number(this.tPadding)/2
			  this.is_refresh=true
			  this.$emit('refresh')
			 this.move_timer=setInterval(()=>{
				this.rotate_number+=8
			 },10) 
		  }else{
			this.move_timer=setInterval(()=>{
				this.top-=8
				if(this.top<=-90){
					clearInterval(this.move_timer)
				}
			},10)
		  }
	  },
	  //加载完成
	  endRefresh(){
		 this.top=-90
		 this.icon_class=''
		 this.move_number=this.rotate_number=0
		 this.is_refresh=false
		 clearInterval(this.move_timer)
	  },
  }
};
</script>
<style lang="scss" >
.refresh-body{
	box-sizing: border-box;
	.refresh-scroll{
		position: relative;
		height: 100%;
	}
	.refresh-icon{
		width: 80upx;
		height: 80upx;
		text-align: center;
		line-height: 80upx;
		border-radius: 50%;
		background: #FFFFFF;
		box-shadow: 0px 0px 10rpx 0px rgba(0, 0, 0, 0.2);
		position: absolute;
		left: 50%;
		color: #00aa7f;
		z-index: 9999999999;
		display: flex;
		align-items: center;
		justify-content: center;
		.iconfont{
			font-size: 36upx;
		}
		image{
			width: 36upx;
		}
	}
	.refresh-icon-active{
		position: fixed;
	}
	.content{
		height: 100%;
	}
	.t-loading-box{
		display: flex;
		align-items: center;
		justify-content: center;
		flex-direction: row;
		line-height:80upx;
		font-size: 26upx;
		width: 100%;
		padding-bottom:20upx;
		color: #888888;
		.t-loading-text{
			padding: 0 10upx;
			display: flex;
			align-items: center;
			flex-direction: row;
		}
		.loading {
			width:25upx;
			height:25upx;
			border:2.5upx solid #888888;
			border-bottom:#ffffff 2.5upx solid;
			border-radius:50%;
			margin-right: 15upx;
			animation:grace-rotate360 1200ms infinite linear;
		}
		.t-line{
			margin: 0px;
			border-bottom: 1px solid rgb(212, 212, 212);
			width: 20px;
			transform: scaleY(0.5);
			border-top-color: rgb(212, 212, 212);
			border-right-color: rgb(212, 212, 212);
			border-left-color: rgb(212, 212, 212);
		}
	}
	@keyframes grace-rotate360{0%{transform:rotate(0deg);} 50%{transform:rotate(180deg);} 100%{transform:rotate(360deg);}}
}
</style>