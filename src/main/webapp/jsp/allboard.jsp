
<%@ page import ="dao.BoardDAO" %>
<%@ page import ="entity.BoardDTO" %>
<%@ page import = "org.json.JSONArray" %>
<%@ page import = "java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	response.setCharacterEncoding("UTF-8");

    BoardDAO dao = BoardDAO.getInstance();
    List<BoardDTO> list  = dao.allBoard();
    JSONArray jsonArray = new JSONArray(list);
	String result = jsonArray.toString();
	
%>
<%=result%>