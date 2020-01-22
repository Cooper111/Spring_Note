<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="page.title"></spring:message></title>
<style>
table{width:50%;border-spacing:0;}
td{padding:5px 10px;border:1px solid #ccc;}
</style>
<title>注册界面</title>
</head>
<body>
<h3>注册页面</h3>
<!-- 提示信息 -->
<font color="red">${requestScope.message}</font>
<!-- 用户可以选择切换语言环境 -->
<a href="registForm?request_locale=zh_CN">中文</a> | <a href="registForm?request_locale=en_US">英文</a>
<br/>
<!-- 使用message标签来输出国际化信息 -->
<spring:message code="title" />
	<center>
		<form:form modelAttribute="user" methood="post" enctype="multipart/form-data" action="regist">
			<table>
				<tr>
					<td>用户名：<spring:message code="loginname" /></td>
					<td><form:input path="name" /></td>
					<td><form:errors path="name" cssStyle="color:red"/></td>
				</tr>			
				<tr>
					<td>性别：<spring:message code="sex" /></td>
					<td><form:input path="sex" /></td>
					<td><form:errors path="sex" cssStyle="color:red"/></td>
				</tr>
				<tr>
					<td>年龄：<spring:message code="age" /></td>
					<td><form:input path="age" /></td>
					<td><form:errors path="age" cssStyle="color:red"/></td>
				</tr>
				<tr>
					<td>生日：<spring:message code="birthday" /></td>
					<td><form:input path="birthday" /></td>
					<td><form:errors path="birthday" cssStyle="color:red"/></td>
				</tr>
				<tr>
					<td>上传用户头像：</td>
					<td><input type="file" name="image"></td>
				</tr>
				<tr> 
            		<td colspan="2"><input type="submit" value="提交"/></td> 
        		</tr> 
			</table>
		</form:form>
	</center>
</body>
</html>