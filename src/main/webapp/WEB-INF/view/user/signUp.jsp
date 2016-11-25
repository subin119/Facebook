<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="post" action="<c:url value="/user/doSignUp" />">
		ID : <input type="text" name="userId" id="userId" /><br/>
		Name : <input type="text" name="userName" id="userName" /><br/>
		Password: <input type="password" name="userPassword" id="userPassword" /><br/>
		<input type="submit" value="가입" /><br/>
	</form>
</body>
</html>