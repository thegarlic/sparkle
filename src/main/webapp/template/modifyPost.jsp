<%@ page contentType = "text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
    <form action="/post/${postId}/modify" method="post">
        <input type="hidden" name="_method" value="PUT">
        <table>
            <tr>
                <td>글번호</td>
                <td>
                ${post.id}
                    <input type="hidden" name="id" value="${post.id}">
                </td>
            </tr>
            <tr>
                <td>시  간</td>
                <td>
                    ${post.writeDate}
                </td>
            </tr>
            <tr>
                <td>글쓴이</td>
                <td>
                    <input type="text" name="author" value="${post.author}">
                </td>
            </tr>
            <tr>
                <td>제  목</td>
                <td>
                    <input type="text" name="title" value="${post.title}">
                </td>
            </tr>
            <tr>
                <td>본  문</td>
                <td>
                    <textarea rows="4" cols="50" name="text">${post.text}</textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button id="postModify">수정</button>
                </td>
            </tr>
        </table>
    </form>
</body>

</html>