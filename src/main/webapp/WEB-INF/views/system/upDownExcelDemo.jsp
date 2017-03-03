<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/3
  Time: 下午 09:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/main.jsp" />
<div class="main-page">
    <form action="<%=request.getContextPath()%>/excelDemo/upLoadUsers.html" method="post" enctype="multipart/form-data">
        <div class="row">
            <div class="col-md-1">
                <sapn>导入用户：</sapn>
            </div>
            <div class="col-md-3">
                <input name="usersExcel" type="file"/>
            </div>
            <div class="col-md-3">
                <button type="submit" >上传</button></div>
            </div>
    </form>
    <div>
        <a href="<%=request.getContextPath()%>/excelDemo/downLoadUsers.html">下载用户列表</a>
    </div>
</div>