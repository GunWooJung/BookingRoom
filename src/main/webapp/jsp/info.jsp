
<%@ page import ="org.json.JSONObject" %>
<%@ page import ="dao.UserDAO" %>
<%@ page import ="entity.UserDTO" %>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	response.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
    UserDAO dao = UserDAO.getInstance();
    UserDTO dto = dao.getInfo(id);
    if(dto == null){
        response.setStatus(HttpServletResponse.SC_CONFLICT);
    }
    else{
        response.setStatus(HttpServletResponse.SC_OK);
    }
    JSONObject jsonResponse = new JSONObject();
    System.out.println(dto.getUserId());
    jsonResponse.put("userId", dto.getUserId());
    jsonResponse.put("name", dto.getName());
    jsonResponse.put("major", dto.getMajor());
    jsonResponse.put("phone", dto.getPhone());

	String result = jsonResponse.toString();
%>
<%=result%>