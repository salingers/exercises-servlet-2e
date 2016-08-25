<%@page pageEncoding="UTF-8" import="java.util.*, cc.openhome.model.*" %>
<%!
    private String SUCCESS_VIEW;
    
    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
    }
%>
<%
        String username = (String) session.getAttribute("login");
        String message = request.getParameter("message"); 
        UserService userService = (UserService) application.getAttribute("userService");
        Blah blah = new Blah();
        blah.setUsername(username);
        blah.setDate(new Date(Long.parseLong(message)));
        userService.deleteBlah(blah);
        response.sendRedirect(SUCCESS_VIEW);
%>