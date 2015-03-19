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
            ${article.id}
            </td>
        </tr>
        <tr>
            <td>시  간</td>
            <td>
                ${article.writeDate}
            </td>
        </tr>
        <tr>
            <td>글쓴이</td>
            <td>
                ${article.author}
            </td>
        </tr>
        <tr>
            <td>제  목</td>
            <td>
                ${article.title}
            </td>
        </tr>
        <tr>
            <td>본  문</td>
            <td>
                ${article.text}
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button id="articleModify">수정</button>
                <button id="articleDelete">삭제</button>
            </td>
        </tr>
    </table>

    <form id="articleDeleteForm" action="/article/${articleId}" method="article">
        <input type="hidden" name="_method" value="DELETE">
        <input type="hidden" value="{articleId}">
    </form>


    <script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
    <script>
        //안티패턴이지만, 최대한 단순하게 처리하기 위함임.
        $("#articleModify").on("click", function(){
            window.location.href = "/article/${articleId}/modify";
        });

        //안티패턴이지만, 최대한 단순하게 처리하기 위함임.
        $("#articleDelete").on("click", function(){
            $("#articleDeleteForm").submit();
        });
    </script>

</body>


</html>