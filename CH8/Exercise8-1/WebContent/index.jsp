<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="g" uri="http://openhome.cc/graphics"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自訂 eachImage 標籤</title>
</head>
<body>
	<g:eachImage var="image" dir="/avatars">
		<img src="${image}">
		<br>
	</g:eachImage>
</body>
</html>