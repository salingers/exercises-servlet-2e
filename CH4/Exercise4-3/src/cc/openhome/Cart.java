package cc.openhome;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cart.view")
public class Cart extends HttpServlet {
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession() == null) {
            response.sendRedirect("shopping.view");
        }

        List<String> cart = (List<String>) request.getSession().getAttribute(
                "cart");
        Map<String, Integer> books = new HashMap<String, Integer>();
        for (String book : cart) {
            int number = 1;
            if (books.containsKey(book)) {
                number = (Integer) books.get(book);
                number++;

            }
            books.put(book, number);
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
        out.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>購物車</title></head><body>");
        out.println("已採購  " + cart.size() + " 本書籍 <br><br>");
        out.println("<table style='text-align: left; width: 193px; height: 74px;' border='1' cellpadding='2' cellspacing='2'><tbody>");
        for (String book : books.keySet()) {
            out.println("<tr>");
            out.println("<td style='width: 109px;'><img style='width: 89px; height: 120px;' alt='' src='images/"
                    + book + ".jpg'><br></td>");
            out.println("<td style='width: 232px;text-align: center;'>共 " + books.get(book)
                    + " 本<br></td></tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("<br>");
        out.println("</body></html>");
    }
}
