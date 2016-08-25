<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="fileService" 
             class="cc.openhome.FileService"
	         scope="application" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" 
              content="text/html; charset=UTF-8">
        <title>檔案管理</title>
    </head>
    <body>
        <a href="logout.do">登出</a>
        <form method="post" enctype="multipart/form-data"
                            action="upload.do"><br>
                              選取檔案：<input type="file" name="file"><br><br>
           <input type="submit" value="上傳">
        </form>
        <hr>
        <table style="text-align: left;" border="1"
		       cellpadding="2" cellspacing="2">
		    <tbody>
		        <tr>
                    <td>檔案名稱</td>
                    <td>上傳日期</td>
                    <td>操作</td>
                </tr>
		    <c:forEach var="file" items="${fileService.fileList}">
		        <tr>
		            <td>${file.filename}</td>
		            <td>${file.savedTime}</td>
		            <td><a href="download.do?id=${file.id}">下載</a> ／
		                <a href="delete.do?id=${file.id}">刪除</a>
		            </td>
		        </tr>
		    </c:forEach>
		    </tbody>
	    </table>
    </body>
</html>