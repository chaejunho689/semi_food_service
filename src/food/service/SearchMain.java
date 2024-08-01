package food.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import food.main.IndexMain;

public class SearchMain implements Food {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "1234";
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public SearchMain() {
		try {
			Class.forName(driver); //생성
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection() {
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//-------------------------------------------------------------------
	public void execute() {
		getConnection();//접속
		
		System.out.println("********************************");
		
		System.out.println("식당 데이터 베이스에 접속되었습니다");
		
		try {
			
		Scanner scan = new Scanner(System.in);
		
		System.out.println("********************************");
		
		System.out.println("찾고싶은 식당이름에 포함된 단어를 입력해주세요");
		
		String Res = scan.next();
		
		System.out.println("코드"+"\t"+"식당명");
		
		String sql = "SELECT * FROM Restaurant WHERE name like  ?";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%" + Res + "%");
		rs = pstmt.executeQuery(); //실행
			
			
			while(rs.next()) {
				System.out.println(rs.getString("KIND")+"\t"
								  + rs.getString("NAME"));
			}//while
			
			
		System.out.println("식당코드를 입력하시면 메뉴를 보여드립니다");	
			
			String code = scan.next();
			
			String sql2 = "SELECT * FROM FOODMENU WHERE KIND = ?";
            pstmt = con.prepareStatement(sql2);
            pstmt.setString(1, code);

			rs = pstmt.executeQuery(); //실행
			
			
			while(rs.next()) {
				System.out.println(rs.getString("NAME"));
				
				}//while
			
			System.out.println();
			System.out.println("주문을 원하시면 1번을 눌러주세요");
			System.out.println();
			System.out.println("다른식당을 이용하시려면 2번을 눌러주세요");
			System.out.println();
			System.out.println("처음 화면으로 돌아가시려면 3번을 눌러주세요");
			
			int order = scan.nextInt();
			
			switch(order){
			
				case 1: new OrderMain().execute();
					
					
				case 2:  new SearchMain().execute();
				
				case 3: new IndexMain().menu_user();
			}
			
			
		
		}
			
			catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}

	
	
}
