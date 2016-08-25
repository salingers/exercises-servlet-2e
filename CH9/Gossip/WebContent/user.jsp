<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="openhome" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>
<html>
    <head>
        <meta content='text/html;charset=UTF-8' http-equiv='content-type'>
        <title>Gossip 微網誌</title>
        <link rel='stylesheet' href='../css/member.css' type='text/css'>
    </head>
    <body>
<c:choose>    
    <c:when test="${requestScope.blahs != null }">
        <div class='leftPanel'>
            <img src='../images/caterpillar.jpg' alt='Gossip 微網誌' />
            <br><br>${ requestScope.username }的微網誌
        </div>
        <openhome:Blahs/>
        <hr style='width: 100%; height: 1px;'>
    </c:when>        
    <c:otherwise>
        <h1 style='color: rgb(255, 0, 0);'>${ requestScope.username } 使用者不存在</h1>
    </c:otherwise>
</c:choose>
    </body>
</html>
