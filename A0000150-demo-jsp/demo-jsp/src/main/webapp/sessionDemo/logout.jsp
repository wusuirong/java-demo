<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jsp中指示服务器编译jsp时采用的编码 -->
<%
session = request.getSession();
String name = (String)session.getAttribute("usernameInSession");
if (null == name) {
	response.sendRedirect("login.jsp");
} else {
	session.invalidate();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注销页面</title>
</head>
<body>
你退出了系统<p>
<a href="login.jsp">重新登录</a>
</body>
</html>
<%
}
%>