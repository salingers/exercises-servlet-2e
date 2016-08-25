<%@page pageEncoding="UTF-8" %>
<%!
    private String LOGIN_VIEW;

    @Override
    public void init() throws ServletException {
        LOGIN_VIEW = getServletConfig().getInitParameter("LOGIN_VIEW");
    }
%>
<% 
    session.invalidate();
    response.sendRedirect(LOGIN_VIEW);
%>