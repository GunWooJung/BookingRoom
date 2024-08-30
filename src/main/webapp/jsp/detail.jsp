
<%@ page import ="dao.BoardDAO" %>
<%@ page import ="entity.BoardDTO" %>
<%@ page import ="entity.BoardUserDTO" %>
<%@ page import = "org.json.JSONObject" %>
<%@ page import = "java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	response.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
    BoardDAO dao = BoardDAO.getInstance();
    BoardUserDTO dto  = dao.detailBoard(id);
    JSONObject json = new JSONObject();
    json.put("id",dto.getId());
    json.put("name",dto.getName());
    json.put("title",dto.getTitle());
    json.put("building",dto.getBuilding());
    json.put("room",dto.getRoom());
    json.put("reason",dto.getReason());
    json.put("userId",dto.getUserId());
    json.put("logtime",dto.getLogtime());
	String result = json.toString();

%>
<%=result%>