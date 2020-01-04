<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
</head>
<body>
<h3>登录页面</h3>
	<center>
		<form:form modelAttribute="user" methood="post" action="login">
			<table>
				<tr>
					<td>用户名:</td>
					<td><form:input path="name" /></td>
				</tr>			
				<tr>
					<td>性别:</td>
					<td><form:input path="sex" /></td>
				</tr>
				<tr>
					<td>年龄:</td>
					<td><form:input path="age" /></td>
				</tr>
				<tr> 
            		<td colspan="2"><input type="submit" value="提交"/></td> 
        		</tr> 
			</table>
		</form:form>
	</center>
</body>
</html>