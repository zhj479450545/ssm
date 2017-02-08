<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page isELIgnored="false" %> --%>

<jsp:include page="/WEB-INF/views/common/main.jsp" />
<div class="main-page">
	<fieldset>
		<div id="loginLogsDiv" class="listPage-container">
			<jsp:include page="/WEB-INF/views/system/list.jsp" />
		</div>
	</fieldset>
</div>
<script>
	$(function() {
		$('#loginLogsDiv').ajaxPage('#pageDiv');
	});
</script>