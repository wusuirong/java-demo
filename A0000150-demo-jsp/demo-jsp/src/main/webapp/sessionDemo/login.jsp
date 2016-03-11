<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jsp中指示服务器编译jsp时采用的编码 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>session演示——登录页面</title>
</head>
<body>
<form method="post" action="sessionLoginServlet">
	<table>
		<tr>
			<td>用户名：</td>
<%
String name = (String)session.getAttribute("usernameInSession");
if (null == name) {%>
			<td><input type="text" name="username" id="username"></td>
<%
} else {%>
			<td><input type="text" name="username" value="${sessionScope.usernameInSession}"></td>
<%
}
%>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td><input type="reset" value="重填"></input></td>
			<td><input type="submit" value="提交"></input></td>
		</tr>
	</table>
</form>
</body>
</html>