<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("base", path);
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	location.href = "${base}/loginController/loginNoAuth.do"
</script>
</head>
</html>