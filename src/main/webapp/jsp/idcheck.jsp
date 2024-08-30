
<%@ page import="dao.UserDAO" %>
<%@ page import="entity.UserDTO" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // 응답 인코딩 설정
    response.setCharacterEncoding("UTF-8");
    String user_id = request.getParameter("user_id");
    String message = "";

    try {
        UserDAO dao = UserDAO.getInstance();

        // ID 체크
        if(dao.IdCheck(user_id)) {
            message = "already";
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        } else {
            message = "ok";
            response.setStatus(HttpServletResponse.SC_OK);
        }
		System.out.println(message);
    } catch (Exception e) {
        // 예외 처리
        e.printStackTrace();
        message = "error: " + e.getMessage();
    } 
%>
<%=message%>