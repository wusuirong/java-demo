<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib uri="http://sherwin.org/tagLearning/my-tag-lib" prefix="my" %>

<html>
<head>
</head>
<body>
	<my:helloTag/><p>
	<my:helloGuyTag name="sherwin" /><p>
	<my:helloGuyTag name="<%= \"wu\" + \"suirong\" %>" /><p>
	
	<%! int size=2; %>
	<my:forTag count="5">
		<font size=<%= size++ %> color="red" >
			count the times
		</font><p>
	</my:forTag>
</body>
</html>