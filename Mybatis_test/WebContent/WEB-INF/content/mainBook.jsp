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
		<th>封面</th><th>书名</th><th>作者</th><th>封面</th>
	</tr>
	<c:forEach items="${requestScope.book_list}" var="book">
		<tr>
			<td><img src="images/${book.image}" height="60"></td>
			<td>${book.name}</td>
			<td>${book.author}</td>
			<td>${book.price}</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>