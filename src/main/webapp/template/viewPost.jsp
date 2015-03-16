<%@ page contentType = "text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
    <table>
        <tr>
            <td>글번호</td>
            <td>
            ${post.id}
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
                ${post.author}
            </td>
        </tr>
        <tr>
            <td>제  목</td>
            <td>
                ${post.author}
            </td>
        </tr>
        <tr>
            <td>본  문</td>
            <td>
                ${post.text}
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button id="postModify">수정</button>
                <button id="postDelete">삭제</button>
            </td>
        </tr>
    </table>

    <form id="postDeleteForm" action="/post/${postId}">
        <input type="hidden" value="{postId}">
    </form>


    <script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
    <script>
        //안티패턴이지만, 최대한 단순하게 처리하기 위함임.
        $("#postModify").on("click", function(){
            window.location.href = "/post/${postId}/modify";
        });

        //안티패턴이지만, 최대한 단순하게 처리하기 위함임.
        $("#postDelete").on("click", function(){
            $("#postDeleteForm").submit();
        });
    </script>

</body>


</html>