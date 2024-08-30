
<%@ page import ="dao.BoardDAO" %>
<%@ page import ="entity.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	response.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String title = request.getParameter("title");
	String building = request.getParameter("building");
	String room = request.getParameter("room");
	String reason = request.getParameter("reason");
	
	BoardDTO dto = new BoardDTO();
	dto.setId(Long.parseLong(id.replaceAll("\\s+", "")));
	dto.setTitle(title);
	dto.setBuilding(building);
	dto.setRoom(room);
	dto.setReason(reason);
    BoardDAO dao = BoardDAO.getInstance();
    int cnt  = dao.changeBoard(dto);

	if(cnt != 1){
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	else{
        response.setStatus(HttpServletResponse.SC_OK);
	}

%>