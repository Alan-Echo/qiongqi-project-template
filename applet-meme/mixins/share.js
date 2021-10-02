
export default {
	onShareAppMessage(r) {
		return {
			title: "快来一起玩吧~",
			path: '/pages/index/index',
			imageUrl: 'http://cdn.envelope.guaikavideo.cn/b8389b504fc2d56228463ddde61190ef76c66cb0.png',
		};
	},
	onShareTimeline: function() {
	        return {
	            title: "快来一起玩吧~",
	            path: '/pages/index/index',
				imageUrl: 'http://cdn.envelope.guaikavideo.cn/b8389b504fc2d56228463ddde61190ef76c66cb0.png',
	        };
	    }
}