<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="table table-hover table-striped table-bordered text-center">
	<thead>
		<tr>
			<th><input id="choose_all" type="checkbox" onclick="this.checked==true?$('input[name=ids]').prop('checked',true): $('input[name=ids]').prop('checked',false);"/><label for="choose_all">全选</label></th>
			<th>序号</th>
			<th>用户名</th>
			<th>登录时间</th>
			<th>登录Ip</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean.datas }" var="log" varStatus="status">
			<tr>
				<td><input type="checkbox" name="ids" value="${log.id }" /></td>
				<td>${status.index+1 }</td>
				<td>${log.username }</td>
				<td><fmt:formatDate value="${log.loginTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${log.loginIp }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="pageDiv" class="row" totalSize="${pageBean.totalSize }" rel-url="<%=request.getContextPath() %>/system/list.html" pageNum="${pageBean.pageNum }" pageSize=${pageBean.pageSize }>
	<jsp:include page="/WEB-INF/views/common/page.jsp" />
</div>

<script>
</script>