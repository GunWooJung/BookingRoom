
<%@ page import ="org.json.JSONObject" %>
<%@ page import ="dao.UserDAO" %>
<%@ page import ="entity.UserDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	response.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String password = request.getParameter("user_password");
	String user_name = request.getParameter("user_name");
	String user_phone = request.getParameter("user_phone");
	String user_major = request.getParameter("user_major");
	
    UserDAO dao = UserDAO.getInstance();
    int dto = dao.changeInfo(id, password, user_name, user_phone, user_major);
    if(dto != 1){
        response.setStatus(HttpServletResponse.SC_CONFLICT);
    }
    else{
        response.setStatus(HttpServletResponse.SC_OK);
    }
%>