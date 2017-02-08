<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<script src="<%=request.getContextPath() %>/js/jquery-1.7.2.js" type="text/javascript" language="utf-8"></script>
	<script type="text/javascript" language="utf-8" src="<%=request.getContextPath() %>/js/main.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/layer/layer.js"></script>
</head>
<body>
<form role="form" action="<%=request.getContextPath()%>/web/login.html" enctype="application/x-www-form-urlencoded">
<div></div>
<div class="form-group" style="margin-top:50vh;" align="center">
	<table>
		<tr><td>用户名：</td><td><input name="username" placeholder="请输入用户名" class="form-control input"/></td></tr>
		<tr><td>密&emsp;码：</td><td><input type="password" name="password" placeholder="请输入密码" class="form-control input"/></td></tr>
		<tr><td>验证码：</td><td><input name="vertifyCode" placeholder="请输入验证码"/><img id="vertifyImg" src=""/></td></tr>
		<tr><td colspan="2" align="right"><label><input type="checkbox" name="rememberUser" class="form-control input"/>记住用户</label></td></tr>
		<tr><td colspan="2" align="center">
			<input type="button" value="登录" onclick="login()" class="btn btn-primary" />
			<input type="button" value="注册" onclick="retister()" />
		</td></tr>
		<tr>
			<td colspan="2" align="center"><span id="errorMsg" style="color: red;"></span></td>
		</tr>
	</table>
</div>
</form>
<script>
	
	/*
	 *登录
	*/
	function login(){
		debugger;
		var form = $('form');
		$.post('<%=request.getContextPath()%>/web/login.html', $(form).serialize(), function(data){
			var res = eval('(' + data + ')');
			var code = +res.code;
			var msg = res.msg;
			if(code && msg) {
				$('#errorMsg').text(msg);
			} else {
				location.href='<%=request.getContextPath()%>/system/index.html';
			}
		});
	}
	
	/*
	 *注册
	*/
	function retister() {
		var form = $('form');
		$.post('<%=request.getContextPath()%>/web/doRegister.html', $(form).serialize(), function(data){
			$.showResultMsg(data);
		});
        <%--window.location.href = "<%=request.getContextPath()%>/web/register.html";--%>
	}
	
</script>
</body>
</html>
