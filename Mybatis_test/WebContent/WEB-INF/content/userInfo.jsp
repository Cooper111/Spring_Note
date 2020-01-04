<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文件下载</title>
</head>
<body>
<body>

<h3>用户详情</h3>

<%-- 	生日：<fmt:formatDate value="${requestScope.user.birthday }" --%>
<!-- 	pattern="yyyy年MM月dd日" /><br> -->
	性别：${requestScope.user.sex}<br>
	年龄：${requestScope.user.age}<br>
<%-- 	电话：${requestScope.user.phone}<br> --%>
	<a href="download?filename=${requestScope.user.name}">
	${requestScope.user.name }头像下载
	</a>
</body>
</html>