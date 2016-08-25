<%@page pageEncoding="UTF-8" import="cc.openhome.model.*" %>
<%!
    private String SUCCESS_VIEW;
    private String ERROR_VIEW;
    
    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
        ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
    }
%>    
<%
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String forwordPage;
        
        UserService userService = (UserService) application.getAttribute("userService");
        if(userService.checkLogin(username, password)) {
            session.setAttribute("login", username);
            forwordPage = SUCCESS_VIEW;
        }
        else {
            request.setAttribute("error", "名稱或密碼錯誤");
            forwordPage = ERROR_VIEW;
        }
        request.getRequestDispatcher(forwordPage).forward(request, response);
%>