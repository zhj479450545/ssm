$.extend({
	/**
	 * 方法描述：弹出异步返回回来的信息
	 * @param data
	 */
	showResultMsg: function(data) {
		var res = eval('(' + data + ')');
		var code = res.code;
		var msg = res.msg;
		if(msg) {
			layer.alert(msg);
		}
	}
});