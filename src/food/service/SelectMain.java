package food.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import food.main.IndexMain;

public class SelectMain implements Food{
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "1234";
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public SelectMain() {
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
//--------------------------------------------------------------------------
	public void execute() {
		getConnection();//접속
		System.out.println("************************");
		System.out.println("식당 데이터 베이스에 접속되었습니다");
		System.out.println("************************");
		System.out.println("코드"+"\t"+"식당명"+"\t"+"전화번호"+"\t"+"\t"+"위치"+"\t"+"\t"+"분류코드(1. 한식, 2. 중식, 3. 양식, 4. 양식)");
		
		String sql = "select * from RESTAURANT";
		try {
			pstmt = con.prepareStatement(sql); //생성
			rs = pstmt.executeQuery(); //실행
			
			
			while(rs.next()) {
				System.out.println(rs.getString("NAME") + "\t"
						 + rs.getString("NAME") + "\t" 
						 + rs.getString("PNUMBER") + "\t"
						 + rs.getString("ADDRESS") + "\t"
						 + rs.getString("KIND"));
				
			}//while
			
			System.out.println();
			System.out.println("다시 검색하시려면 1번을 눌러주세요");
			System.out.println();
			System.out.println("처음 화면으로 돌아가시려면 2번을 눌러주세요");
			
			Scanner scan =new Scanner(System.in);
			
			int order = scan.nextInt();
			
			switch(order){
			
				case 1: new SelectMain().execute();
					
					
				case 2: new IndexMain().menu_user();
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

