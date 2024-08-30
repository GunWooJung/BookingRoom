package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.UserDTO;

public class UserDAO {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "1234";

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// private static MemberDAO instance;
	private static UserDAO instance = new UserDAO();

	public UserDAO() {
		try {
			Class.forName(driver);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // 생성

	}

	public static UserDAO getInstance() {
		
		if (instance == null) {
			synchronized (UserDAO.class) {
				instance = new UserDAO();
			}
		}
		return instance;
	}

	public void getCnnection() {

		try {
			con = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean IdCheck(String id) {
		boolean idchek = false;

		getCnnection();
		String sql = "select * from users where userId = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				idchek = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return idchek;
	}
	
	public int Login(String id, String password) {
		int user_seq = -1;

		getCnnection();
		String sql = "select * from users where userId = ? and password=  ?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next())
				user_seq = rs.getInt("id");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user_seq;
	}
	
	public UserDTO getInfo(String id) {
		UserDTO dto = null;
		getCnnection();
		String sql = "select * from users where id = ?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				dto = new UserDTO();
				dto.setUserId(rs.getString("userId"));
				dto.setName(rs.getString("name"));
				dto.setMajor(rs.getString("major"));
				dto.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	public int deleteUser(String id , String password) {
		int line = 0;
		getCnnection();
		String sql = "delete from users where id = ? and password = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			line = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return line;
	}
	
	public int Join(UserDTO dto) {
		int line = 0;
		getCnnection();
		String sql = "insert into users values(user_seq.NEXTVAL,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getUserId());
			pstmt.setString(3, dto.getPassword());
			pstmt.setString(4, dto.getMajor());
			pstmt.setString(5, dto.getPhone());
			line = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return line;
	}
  
	public int changeInfo(String id, String password, String user_name, String user_phone, String user_major) {
		int line = 0;
		getCnnection();
		String sql = "update users set name = ? , phone =? , major = ? where id = ? and password = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_phone);
			pstmt.setString(3, user_major);
			pstmt.setString(4, id);
			pstmt.setString(5, password);
			line = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return line;
	}
}
