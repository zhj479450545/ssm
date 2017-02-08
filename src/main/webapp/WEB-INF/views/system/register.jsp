<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/8
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>用户注册</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/web/register.html">
        <div class="form-group">
            <table>
                <tr><td>用户名：</td><td><input placeholder="请输入6-16位的字母、数字或下划线"/></td></tr>
                <tr><td>密码：</td><td><input placeholder="请输入6-16位的字符"/></td></tr>
                <tr><td>确认密码：</td><td><input placeholder="请再次输入密码"/></td></tr>
                <tr><td>昵称：</td><td><input placeholder="最多16个字符"/></td></tr>
                <tr><td>手机号码：</td><td><input placeholder="请输入手机号码"/></td></tr>
                <tr><td>联系地址：</td><td><input placeholder="请输入联系地址"/></td></tr>
                <tr><td>验证码：</td><td><input placeholder="请输入验证码"/></td></tr>
            </table>
        </div>

    </form>
</body>
</html>
