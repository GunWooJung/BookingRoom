
<%@ page import ="org.json.JSONObject" %>
<%@ page import ="dao.UserDAO" %>
<%@ page import ="entity.UserDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	response.setCharacterEncoding("UTF-8");
	try{
	String id = request.getParameter("id");
	String password = request.getParameter("user_password");
    UserDAO dao = UserDAO.getInstance();
    int dto = dao.deleteUser(id, password);
	String result2 = String.valueOf(dto);
    System.out.println(dto);
    if(dto != 1){
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
    else{
        response.setStatus(HttpServletResponse.SC_OK);
    }
	}catch(Exception e){
		e.printStackTrace();
	}
%>