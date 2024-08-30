
<%@ page import ="dao.UserDAO" %>
<%@ page import ="entity.UserDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	response.setCharacterEncoding("UTF-8");

	String user_id = request.getParameter("user_id");
	String user_password = request.getParameter("user_password");
	String user_name = request.getParameter("user_name");
	String user_phone = request.getParameter("user_phone");
	String user_major = request.getParameter("user_major");
	
    String message = "";
	try{
    UserDAO dao = UserDAO.getInstance();

        // ID 체크
    if(dao.IdCheck(user_id)) {
        message = "already";
        response.setStatus(HttpServletResponse.SC_CONFLICT);
   } else {
	   	UserDTO dto = new UserDTO();
		dto.setUserId(user_id);
		dto.setPassword(user_password);
		dto.setName(user_name);
		dto.setPhone(user_phone);
		dto.setMajor(user_major);
		
		int cnt = dao.Join(dto);
		if(cnt != 1){
			message = "fail";
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		else{
			message = "success";
            response.setStatus(HttpServletResponse.SC_OK);
		}
		System.out.println(message);
   }

	}catch(Exception e){
		message = e.toString();
	}
%>
<%=message%>