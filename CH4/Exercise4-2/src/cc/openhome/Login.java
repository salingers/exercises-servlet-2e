package cc.openhome;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login.do")
public class Login extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = "login.view";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if ("caterpillar".equals(username) && "123456".equals(password)) {
            String remember = request.getParameter("remember");
            if (remember != null) {
                Cookie usernameCookie = new Cookie("username", "caterpillar");
                usernameCookie.setMaxAge(7 * 24 * 60 * 60);
                Cookie passwordCookie = new Cookie("password", "123456");
                passwordCookie.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }
            page = "success.html";
            
        } 
        response.sendRedirect(page);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
