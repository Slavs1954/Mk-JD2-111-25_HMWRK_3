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
		<h1>Результат голосования</h1>
		<h2>Исполнители</h2>
		<ul>
			<c:forEach items="${authors}" var="author">
				<li value="${author.key}">${author.key} ${author.value}</li>
			</c:forEach>
		</ul>
		<h2>Жанры</h2>
		<ul>
			<c:forEach items="${genres}" var="genre">
				<li value="${genre.key}">${genre.key} ${genre.value}</li>
			</c:forEach>
		</ul>
		<h2>Последние комментарии</h2>
		<c:forEach items="${abouts}" var="about">
			<li>${about}</li>
		</c:forEach>
	</body>
</html>