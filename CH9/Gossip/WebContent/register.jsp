<%@page pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gossip 微網誌</title>
    </head>
    <body>
    <c:if test="${requestScope.errors != null}">
        <h1>新增會員失敗</h1>
        <ul style='color: rgb(255, 0, 0);'>
            <c:forEach var="error" items="${requestScope.errors}">
                <li>${error}</li>
            </c:forEach>
        </ul><br>
    </c:if>
        <h1>會員註冊</h1>
        <form method='post' action='register.do'>
            <table bgcolor=#cccccc>
                <tr>
                    <td>郵件位址：</td>
                    <td><input type='text' name='email' value='${param.email}' size='25' maxlength='100'>
                    </td>
                </tr>
                <tr>
                    <td>名稱（最大16字元）：</td>
                    <td><input type='text' name='username' value='${param.username}' size='25' maxlength='16'></td>
                </tr>
                <tr>
                    <td>密碼（6到16字元）：</td>
                    <td><input type='password' name='password' size='25' maxlength='16'>
                    </td>
                </tr>
                <tr>
                    <td>確認密碼：</td>
                    <td><input type='password' name='confirmedPasswd' size='25' maxlength='16'></td>
                </tr>
                <tr>
                    <td colspan='2' align='center'><input type='submit' value='註冊'></td>
                </tr>
            </table>
        </form>
    </body>
</html>