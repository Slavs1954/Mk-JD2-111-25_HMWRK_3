<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>Тег FORM</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/vote" method="POST">
			<p><b>Ваш любимый исполнитель?</b></p>
			<c:forEach items="${authors}" var="item">
				<p><input type="radio" name="author" value="${item}">${item}</p>
			</c:forEach>
			<p><b>Ваши любимые жанры?</b></p>
			<c:forEach items="${genres}" var="item">
				<p><input type="checkbox" name="genres" value="${item}">${item}</p>
			</c:forEach>
			<p><b>Расскажите о себе?</b></p>
			<textarea name="about"></textarea>
			<p><input type="submit"></p>
		</form>
	</body>
</html>