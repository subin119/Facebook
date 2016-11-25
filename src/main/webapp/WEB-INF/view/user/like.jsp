<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LIKE</title>
</head>
<body>
	<form method="post" action="<c:url value="/user/like/movie"/>">
		좋아하는 영화를 선택하세요.<br/>
		<c:forEach items="${movies}" var="movie">
			<c:if test="${movie.movieName ne ''}">
				<input type="checkbox" id="${movie.id}" name="movies" value="${movie.movieName}" />
				<label for="${movie.id}">${movie.movieName}</label><br/>
			</c:if>
		</c:forEach>
		<input type="text" name="movies" placeholder="기타"/><br/><br/>
		<input type="submit" value="저장" />
	</form>
	
	<form method="post" action="<c:url value="/user/like/book"/>">
		좋아하는 책을 선택하세요.<br/>
		<c:forEach items="${books}" var="book">
			<c:if test="${book.bookName ne ''}">
				<input type="checkbox" id="${book.id}" name="books" value="${book.bookName}" />
				<label for="${book.id}">${book.bookName}</label><br/>
			</c:if>
		</c:forEach>
		<input type="text" name="books" placeholder="기타"/><br/><br/>
		<input type="submit" value="저장" />
	</form>
</body>
</html>