package cc.openhome;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login.view")
public class Form extends HttpServlet {
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String username = "";
        String password = "";
        String checked = "";
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                if ("username".equals(name)) {
                    username = value;
                } else if ("password".equals(cookie.getName())) {
                    password = value;
                }
                if ((username.length() != 0) && (password.length() != 0)) {
                    checked = "checked";
                    break;
                }
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet LoginForm</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action='login.do' method='post'>");
        out.println("名稱：<input type='text' name='username' value='"
                + username + "'><br>");
        out.println("密碼：<input type='password' name='password' value='"
                + password + "'><br>");
        out.println("記住名稱密碼 <input type='checkbox' name='remember' "
                + checked + "><br><br>");
        out.println("<input type='submit' value='登入'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");

        out.close();

    }
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
