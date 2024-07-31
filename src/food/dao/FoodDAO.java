package food.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import food.bean.FoodDTO;

public class FoodDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url ="jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "1234";

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public String session_id = null;
	public String session_pwd = null;
	public String session_name = null;
	public int session_auth;
	private boolean login_yn;
	
	private static FoodDAO instance = new FoodDAO();
	
	public FoodDAO() {
		try {
			Class.forName(driver);
//			System.out.println("driver loading...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static FoodDAO getInstance() {
		return instance;
	}
	
	public void getConnection() {
		try {
			con = DriverManager.getConnection(url, username, password);
//			System.out.println("Oracle Connect...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 로그인 여부 체크 
	// if(boardDAO.login_check() == false)	return; < 로그인 체크 필요시 이것 서비스에 넣어서 사용
	public boolean common_logincheck() {
		if(login_yn == false) {
			System.out.println("로그인이 필요한 서비스입니다.");
			return false;
		}
//		System.out.println(session_name + "님 환영합니다.");
		return true;
	}
	
	public boolean common_isExistId(String id) {
		boolean exist = false;
		
		getConnection();
		try {
			pstmt = con.prepareStatement("SELECT ID FROM food_account WHERE ID = ?");
			pstmt.setString(1,id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				exist = true; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally : 에러와 상관없이 끝나면 실행 됨.
			// 거꾸로 close 해줘야 함.
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exist;
	}
	
	public boolean common_isLoginSuccess(String id, String pwd) {
		boolean exist = false;
		
		getConnection();
		try {
			pstmt = con.prepareStatement("SELECT ID FROM food_account WHERE ID = ? AND PWD = ?");
			pstmt.setString(1,id);
			pstmt.setString(2,pwd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				exist = true; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally : 에러와 상관없이 끝나면 실행 됨.
			// 거꾸로 close 해줘야 함.
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exist;
	}
	
	public void login_register(FoodDTO foodDTO) {
		getConnection();
		String sql = "INSERT INTO food_account(NAME, ID, PWD, code) VALUES(?, ?, ?, 0)";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,foodDTO.getName());
			pstmt.setString(2,foodDTO.getId());
			pstmt.setString(3,foodDTO.getPwd());
			
			pstmt.executeUpdate();
			System.out.println("회원가입이 완료되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void login_logout() {
		login_yn = false;
		session_id = null;
		session_pwd = null;
		
		System.out.println("로그아웃 되었습니다.");
		
	}
	
	public void login_login(String id, String pwd) {

		getConnection();
		
		try {
			pstmt = con.prepareStatement("SELECT ID, PWD, NAME, CODE FROM food_account WHERE ID = ? and PWD = ?");
			pstmt.setString(1,id);
			pstmt.setString(2,pwd);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				session_id = rs.getString("ID");
				session_pwd = rs.getString("PWD");
				session_name = rs.getString("NAME");
				session_auth = rs.getInt("CODE");
				login_yn = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally : 에러와 상관없이 끝나면 실행 됨.
			// 거꾸로 close 해줘야 함.
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void admin_authUpdate(String id) {

		getConnection();
		
		try {
			pstmt = con.prepareStatement("UPDATE food_account SET CODE = 1 WHERE ID = ?");
			pstmt.setString(1,id);

			pstmt.executeUpdate();
			System.out.println("사용자 <" + id + ">에게 관리자 권한이 부여되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally : 에러와 상관없이 끝나면 실행 됨.
			// 거꾸로 close 해줘야 함.
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void login_update(String id, String pwd, String name) {

		getConnection();
		
		try {
			pstmt = con.prepareStatement("UPDATE food_account SET PWD = ?, NAME = ? WHERE ID = ?");
			pstmt.setString(1,pwd);
			pstmt.setString(2,name);
			pstmt.setString(3,id);

			pstmt.executeUpdate();
			System.out.println("사용자 <" + id + ">의 정보가 수정되었습니다.");
			login_logout();
			System.out.println("다시 로그인 해주세요.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// 유저 or 관리자 여부 체크 
	public boolean common_adminYn() {
		boolean adminYn = false;
		if(login_yn == true && session_auth == 1) {
			adminYn = true;
		} else {
			adminYn = false;
		}
		return adminYn;
	}
	
	
	
}
