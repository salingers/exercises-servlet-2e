<%@page pageEncoding="UTF-8" import="java.util.*, cc.openhome.model.*" %>
<%!
    private String USER_VIEW;

    @Override
    public void init() throws ServletException {
        USER_VIEW = getServletConfig().getInitParameter("USER_VIEW");
    }
%>
<%
        UserService userService = 
            (UserService) application.getAttribute("userService");
        
        String username = request.getPathInfo().substring(1);
        
        if(userService.isUserExisted(username)) {
            Blah blah = new Blah();
            blah.setUsername(username);
            List<Blah> blahs = userService.getBlahs(blah);
            request.setAttribute("blahs", blahs);
        }
        request.setAttribute("username", username);
        request.getRequestDispatcher(USER_VIEW).forward(request, response);
    
%>    