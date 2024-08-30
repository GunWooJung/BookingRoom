
<%@ page import ="dao.BoardDAO" %>
<%@ page import ="entity.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	response.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");	
	BoardDTO dto = new BoardDTO();

    BoardDAO dao = BoardDAO.getInstance();
    int cnt  = dao.deleteBoard(id);

	if(cnt != 1){
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	else{
        response.setStatus(HttpServletResponse.SC_OK);
	}

%>