<%@ page contentType = "text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
    <form action="/post" method="post">
        <table>
            <tr>
                <td>글쓴이</td>
                <td>
                    <input type="text" name="author">
                </td>
            </tr>
            <tr>
                <td>제  목</td>
                <td>
                    <input type="text" name="title">
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
                    <input type="submit" value="확인">
                </td>
            </tr>
        </table>
    </form>
</body>

<script>
</script>

</html>