<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录拦截测试-登陆后界面</title>
</head>
<body>
<h3>欢迎[${sessionScope.user.name }]访问</h3>
<br>
<a href="http://localhost:8080/Mybatis_test/userInfo">进入用户详情页</a>
<br>
<table border="1">
	<tr>
		<th>封面</th><th>用户名</th><th>性别</th><th>年龄</th>
	</tr>
	<c:forEach items="${requestScope.user_list}" var="user">
		<tr>
			<td><img src="${user.temp}" height="60"></td>
			<td>${user.name}</td>
			<td>${user.sex}</td>
			<td>${user.age}</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>