package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.BoardDTO;
import entity.BoardUserDTO;

public class BoardDAO {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "1234";

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// private static MemberDAO instance;
	private static BoardDAO instance = new BoardDAO();

	public BoardDAO() {
		try {
			Class.forName(driver);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // 생성

	}

	public static BoardDAO getInstance() {
		
		if (instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
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

	public int insertBoard(BoardDTO dto) {
		int line = 0;
		getCnnection();
		String sql = "insert into boards values(board_seq.NEXTVAL,?,?,?,?,?,sysdate)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getBuilding());
			pstmt.setString(3, dto.getRoom());
			pstmt.setString(4, dto.getReason());
			pstmt.setLong(5, dto.getUserId());
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
	public int deleteBoard(String id) {
		int line = 0;
		getCnnection();
		String sql = "delete from boards where id = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
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
	public int changeBoard(BoardDTO dto) {
		int line = 0;
		getCnnection();
		String sql = "update boards set title = ?, building = ?, room = ?, reason = ? where id = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getBuilding());
			pstmt.setString(3, dto.getRoom());
			pstmt.setString(4, dto.getReason());
			pstmt.setLong(5, dto.getId());
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
	public List<BoardDTO> allBoard() {
		List<BoardDTO> list = new ArrayList<>();
		getCnnection();
		String sql = "select * from boards order by id desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				dto.setId(rs.getLong("id"));
				dto.setTitle(rs.getString("title"));
				dto.setBuilding(rs.getString("building"));
				dto.setRoom(rs.getString("room"));
				list.add(dto);
				
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
		return list;
	}
	
	public BoardUserDTO detailBoard(String id) {
		
		
		BoardUserDTO dto = new BoardUserDTO();
		
		getCnnection();
		String sql = "select boards.id as bid, user_id , logtime, title,room,building,name,reason from boards, users where boards.id = ? and boards.user_Id = users.id";
		
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				
				dto.setId(rs.getLong("bid"));
				dto.setTitle(rs.getString("title"));
				dto.setBuilding(rs.getString("building"));
				dto.setRoom(rs.getString("room"));
				dto.setReason(rs.getString("reason"));
				dto.setName(rs.getString("name"));
				dto.setLogtime(rs.getString("logtime"));
				dto.setUserId(rs.getLong("user_id"));
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
}
