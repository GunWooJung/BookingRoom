
<%@ page import="dao.UserDAO" %>
<%@ page import="entity.UserDTO" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // 응답 인코딩 설정
    response.setCharacterEncoding("UTF-8");
    String user_id = request.getParameter("user_id");
	String user_password = request.getParameter("user_password");
    String message = "";

    try {
        UserDAO dao = UserDAO.getInstance();

        // ID 체크
        int id = dao.Login(user_id, user_password);
        if(id != -1) {
            message = String.valueOf(id);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            message = "fail";
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    } catch (Exception e) {
        // 예외 처리
        e.printStackTrace();
        message = "error: " + e.getMessage();
    } 
%>
<%=message%>