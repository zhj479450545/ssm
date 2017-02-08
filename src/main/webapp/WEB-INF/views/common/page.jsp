<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="page-num col-lg-2">
	<span>每页显示</span>
	<select class="select" name="pageSize" onchange="$.changePage(this);">
		<option value="10" <c:if test="${pageBean.pageSize == 10}">selected="selected"</c:if>>10</option>
		<option value="25" <c:if test="${pageBean.pageSize == 25}">selected="selected"</c:if>>25</option>
		<option value="50" <c:if test="${pageBean.pageSize == 50}">selected="selected"</c:if>>50</option>
		<option value="100" <c:if test="${pageBean.pageSize == 100}">selected="selected"</c:if>>100</option>
	</select>
	<span>条</span>
</div>
<div class="pull-right page-num">
	<span id="pageBar_total">共条</span>
</div>
<div class="pull-right">
	<ul class="pagination"></ul>
</div>