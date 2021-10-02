<template>
	<view class="list-content">
		
		<view class="mix-list-cell"   :class="[border,hasborder]" @click="eventClick" hover-class="cell-hover"  :hover-stay-time="50">
			<!-- <text
				v-if="icon"
				class="cell-icon yticon"
				:style="[{
					color: iconColor,
				}]"
				:class="icon"
			></text> -->
			<image v-if="img" :src="img" class="cell-icon yticon item-icon " :style="[{color: iconColor}]"></image>
			<text v-el-if="icon" :class="['iconfont',icon]" :style="[{color: iconColor,'font-size':'40rpx','margin-right':'10rpx','margin-left':'10rpx'}]"></text>
			<text class="cell-tit clamp">{{title}}</text>
			<text v-if="tips" class="cell-tip">{{tips}}</text>
			<text class="cell-more yticon"
				:class="typeList[navigateType]"
			></text>
		</view>

	</view>
</template>
 
<script>
	/**
	 *  简单封装了下， 应用范围比较狭窄，可以在此基础上进行扩展使用
	 *  比如加入image， iconSize可控等
	 */
	export default {
		data() {
			return {
				typeList: {
					left: 'icon-zuo',
					right: 'icon-you',
					up: 'icon-shang',
					down: 'icon-xia'
				},
			}
		},
		props: {
			img: {
				type: String,
				default: ''
			},
			icon: {
				type: String,
				default: ''
			},
			title: {
				type: String,
				default: '标题'
			},
			tips: {
				type: String,
				default: ''
			},
			navigateType: {
				type: String,
				default: 'right'
			},
			border: {
				type: String,
				default: 'b-b'
			},
			hasborder:{
				type:String,
				default:'hasborder'
			},
			hoverClass: {
				type: String,
				default: 'cell-hover'
			},
			iconColor: {
				type: String,
				default: '#333'
			}
		},
		methods: {
			eventClick(){
				this.$emit('eventClick');
			}
		},
	}
</script>

<style lang='scss'>

	.icon .mix-list-cell.b-b:after{
		left: 90upx;
	}
	.hasborder{
		border-bottom: 1upx #E9E9E9 solid;
	}
	.mix-list-cell{
		display:flex;
		align-items:baseline;
		/* padding: 20upx $page-row-spacing; */
		padding: 30upx 0;
		/* line-height:60upx; */
		position:relative;
		
		&.cell-hover{
			background:#fafafa;
		}
		&.b-b:after{
			left: 30upx;
		}

		.cell-icon{
			align-self:center;
			width:70upx;
			height: 70upx;
			/* max-height:60upx; */
			font-size:38upx;
		}
		.cell-more{
			align-self: center;
			font-size:30upx;
			color:$font-color-base;
			margin-left:$uni-spacing-row-sm;
		}
		.cell-tit{
			flex: 1;
			font-size: $font-base;
			color: $font-color-dark;
			margin-right:10upx;
		}
		.cell-tip{
			font-size: $font-sm+2upx;
			color: $font-color-light;
		}
	}
	.item-icon{
		width: 50upx;
		height: 50upx;
		display: inline;
	}
</style>
