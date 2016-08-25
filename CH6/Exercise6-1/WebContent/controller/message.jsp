<%@page pageEncoding="UTF-8" import="java.util.*,cc.openhome.model.*" %>
<%!
    private String MEMBER_VIEW;

    @Override
    public void init() throws ServletException {
        MEMBER_VIEW = getServletConfig().getInitParameter("MEMBER_VIEW");
    }
%>    
<%
  
        String username = (String) session.getAttribute("login");
        
        UserService userService = (UserService) application.getAttribute("userService");

        Blah blah = new Blah();
        blah.setUsername(username);
        
        String blabla = request.getParameter("blabla");
        if (blabla != null && blabla.length() != 0) {
            if (blabla.length() < 140) {
                blah.setDate(new Date());
                blah.setTxt(blabla);
                userService.addBlah(blah);
            }
            else {
                request.setAttribute("blabla", blabla);
            }
        }
        
        List<Blah> blahs = userService.getBlahs(blah);
        request.setAttribute("blahs", blahs);
        request.getRequestDispatcher(MEMBER_VIEW).forward(request, response);
%>