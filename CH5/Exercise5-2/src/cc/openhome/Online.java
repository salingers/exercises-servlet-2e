package cc.openhome;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/online.view")
public class Online extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Online</title>");
        out.println("</head>");
        out.println("<body>");

        Map<String, HttpSession> sessions = OnlineUser.getSessions();
        out.println("目前線上人數 " + sessions.size() + " 人<br><br>");
        for(HttpSession session : sessions.values()) {
            User user = (User) session.getAttribute("user");
            out.println(new Date(session.getLastAccessedTime()) + "<br>");
            out.println(user.toString() + "<br><br>");
        }

        out.println("</body>");
        out.println("</html>");

        out.close();

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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
