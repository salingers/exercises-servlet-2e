<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="openhome" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gossip 微網誌</title>
        <link rel="stylesheet" href="css/index.css" type="text/css">
    </head>
    <body>
        <div class="leftPanel">
            <img src='images/caterpillar.jpg' alt='Gossip 微網誌'/>
            <div>
                <a href='register.jsp'>還不是會員？</a><p>
                <div style='color: rgb(255, 0, 0);'>${ requestScope.error }</div>
                <form method='post' action='login.do'>
                    <table bgcolor='#cccccc'>
                         <tr>
                            <td colspan='2'>會員登入</td>
                        </tr>
                        <tr>
                            <td>名稱：</td>
                            <td><input type='text' name='username' value="${ param.username }"></td>
                        </tr>
                        <tr>
                            <td>密碼：</td>
                            <td><input type='password' name='password'></td>
                        </tr>
                        <tr>
                            <td colspan='2' align='center'><input type='submit' value='登入'></td>
                        </tr>
                        <tr>
                            <td colspan='2'><a href='forgot.html'>忘記密碼？</a></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>  
        <div>
            <h1>Gossip ... XD</h1>
               <ul>
                    <li>談天說地不奇怪
                    <li>分享訊息也可以
                    <li>隨意寫寫表心情
               </ul>
               <openhome:Blahs/>
        </div>
    </body>
</html>