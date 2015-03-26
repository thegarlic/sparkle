<%@ page contentType = "text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
    <table>
    	<tr>
    		<th>글번호</th>
    		<th>제목</th>
    		<th>글쓴이</th>
    		<th>시간</th>
    	</tr>
    	<c:forEach items="${articles}" var="article">
    	<tr>
    		<td>${article.id}</td>
    		<td><a href="/article/${article.id}">${article.title}</a></td>
    		<td>${article.author}</td>
    		<td>${article.writeDateString}</td>
    	</tr>
    	</c:forEach>
    </table>

    <script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
</body>
</html>