import dao.UserDAO;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		UserDAO dao = UserDAO.getInstance();
		
		String message = "";
		if(dao.IdCheck("johndoe") == true){
			message = "already";
		}
		else {
			message = "ok";
		}
		System.out.println(message);
	}

}
