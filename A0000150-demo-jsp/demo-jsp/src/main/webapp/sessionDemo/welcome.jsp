<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jsp中指示服务器编译jsp时采用的编码 -->
<%
session = request.getSession();
String name = (String)session.getAttribute("usernameInSession");
if (null == name) {
	response.sendRedirect("login.jsp");
} else {
	//显示欢迎页
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎页面</title>
</head>
<body>
session信息<p>
session id: ${pageContext.session.id}<p>
session 创建时间：${pageContext.session.creationTime} 这里如何在EL表达式里转换时间？<p>
session 上次访问时间：${pageContext.session.lastAccessedTime}<p>
session 最大不活动时间间隔：${pageContext.session.maxInactiveInterval}<p>
session 是否新会话：<%=session.isNew() %> 这里如何用EL表达式显示？\${pageContext.session.isNew}<p>
欢迎，${sessionScope.usernameInSession}<p>
<a href="login.jsp">重新登录</a>
<a href="logout.jsp">注销</a>
</body>
</html>
<%
}
%>