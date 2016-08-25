<%@page pageEncoding="UTF-8" import="java.util.*,cc.openhome.model.*" %>
<%!
    private String SUCCESS_VIEW;
    private String ERROR_VIEW;

    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
        ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
    }

    private boolean isInvalidEmail(String email) {
        return email == null
                || !email.matches("^[_a-z0-9-]+([.]"
                        + "[_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
    }

    private boolean isInvalidPassword(String password, String confirmedPasswd) {
        return password == null || password.length() < 6
                || password.length() > 16 || !password.equals(confirmedPasswd);
    }
    
%>
<%
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmedPasswd = request.getParameter("confirmedPasswd");

        UserService userService = (UserService) application.getAttribute("userService");
        
        List<String> errors = new ArrayList<String>();
        if (isInvalidEmail(email)) {
            errors.add("未填寫郵件或郵件格式不正確");
        }
        if (userService.isInvalidUsername(username)) {
            errors.add("使用者名稱為空或已存在");
        }
        if (isInvalidPassword(password, confirmedPasswd)) {
            errors.add("請確認密碼符合格式並再度確認密碼");
        }
        String resultPage = ERROR_VIEW;
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
        } else {
            resultPage = SUCCESS_VIEW;
            userService.createUserData(email, username, password);
        }

        request.getRequestDispatcher(resultPage).forward(request, response);
%>