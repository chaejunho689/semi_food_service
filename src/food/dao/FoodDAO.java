package food.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

import food.bean.FoodDTO;
import food.main.IndexMain;
import food.service.OrderMain;
import food.service.SearchMain;
import food.service.SelectMain;

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


	public void showFoodMenu() {

		getConnection();

		String query = "SELECT CODE, NAME, PRICE FROM FOODMENU";
		try (PreparedStatement pstmt = con.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {
			System.out.println("***********************");
			System.out.println("음식 메뉴를 출력합니다:");
			while (rs.next()) {
				int code = rs.getInt("CODE");
				String name = rs.getString("NAME");
				double price = rs.getDouble("PRICE");
				System.out.printf("코드: %d 메뉴: %s 가격: %.0f%n", code, name, price);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public double getFoodPrice(int foodCode) throws SQLException {
		String sqlprice = "SELECT PRICE FROM FOODMENU WHERE CODE = ?";  
		double price = 0;   // int로 형변환 하면 오류남
		try (PreparedStatement pstmt = con.prepareStatement(sqlprice)) {

			pstmt.setInt(1, foodCode);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					price = rs.getDouble("PRICE");
				} else {
					throw new SQLException("음식을 찾을수없습니다.");
				}
			}
		}
		return price;
	}

	public FoodDTO getResName(String res_name) {
		FoodDTO foodDTO = null;
		getConnection();
		String selectQuery = "select * from RESTAURANT where NAME=?";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, res_name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				foodDTO = new FoodDTO();
				foodDTO.setRes_name(rs.getString("NAME"));
				foodDTO.setRes_address(rs.getString("ADDRESS"));
				foodDTO.setRes_pnumber(rs.getString("PNUMBER"));
				foodDTO.setRes_kind(rs.getInt("KIND"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return foodDTO;
	}




	public int getRes_code(FoodDTO foodDTO) {
		int codeNum = 0;
		getConnection();
		try {
			String seqQuery = "SELECT FOOD_SEQUENCE.NEXTVAL FROM dual";
			pstmt = con.prepareStatement(seqQuery);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				codeNum = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return codeNum;
	}

	public int addRes(FoodDTO foodDTO) {
		int su = 0;
		getConnection();
		try {
			String insertQuery = "INSERT into RESTAURANT (CODE, "
					+ "NAME, PNUMBER, ADDRESS, KIND)"
					+ " VALUES (?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setInt(1, foodDTO.getRes_code());
			pstmt.setString(2, foodDTO.getRes_name());
			pstmt.setString(3, foodDTO.getRes_pnumber());
			pstmt.setString(4, foodDTO.getRes_address());
			pstmt.setInt(5, foodDTO.getRes_kind());
			su = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return su;
	}

	public boolean isExistResName(String res_name) {
		boolean exist = false;
		getConnection();
		String selectQuery = "select * from RESTAURANT where NAME=?";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, res_name);
			rs = pstmt.executeQuery();
			if (rs.next())
				exist = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exist;
	}

	public int deleteRes(String res_name) {
		int su = 0;
		getConnection();
		String deleteQuery = "delete from RESTAURANT where NAME=?";
		try {
			pstmt = con.prepareStatement(deleteQuery);
			pstmt.setString(1, res_name);
			su = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return su;
	}

	public int updateRes(Map<String, String> map) {
		int su = 0;
		getConnection();
		String updateQuery = "update RESTAURANT set NAME=?, ADDRESS=?, PNUMBER=?, KIND=? where NAME=?";
		try {
			pstmt = con.prepareStatement(updateQuery);
			pstmt.setString(1, map.get("NAME"));
			pstmt.setString(2, map.get("ADDRESS"));
			pstmt.setString(3, map.get("PNUMBER"));
			pstmt.setInt(4, Integer.parseInt(map.get("KIND")));
			pstmt.setString(5, map.get("NAME2"));

			su = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return su;
	}

	public int getFood_code(FoodDTO foodDTO) {
		int codeNum = 0;
		getConnection();
		try {
			String seqQuery = "SELECT FOOD_SEQUENCE.NEXTVAL FROM dual";
			pstmt = con.prepareStatement(seqQuery);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				codeNum = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return codeNum;
	}

	public int addFood(FoodDTO foodDTO) {
		int su = 0;
		getConnection();
		try {
			String insertQuery = "INSERT into FOODMENU (CODE, "
					+ "NAME, PRICE, KIND)"
					+ " VALUES (?, ?, ?, ?)";
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setInt(1, foodDTO.getFood_code());
			pstmt.setString(2, foodDTO.getFood_name());
			pstmt.setString(3, foodDTO.getFood_price());
			pstmt.setString(4, foodDTO.getFood_kind());
			su = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return su;
	}

	public boolean isExistFoodName(String food_name) {
		boolean exist = false;
		getConnection();
		String selectQuery = "select * from FOODMENU where NAME=?";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, food_name);
			rs = pstmt.executeQuery();
			if (rs.next())
				exist = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exist;
	}

	public int deleteFood(String food_name) {
		int su = 0;
		getConnection();
		String deleteQuery = "delete from FOODMENU where NAME=?";
		try {
			pstmt = con.prepareStatement(deleteQuery);
			pstmt.setString(1, food_name);
			su = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return su;
	}


	public FoodDTO getFoodName(String food_name) {
		FoodDTO foodDTO = null;
		getConnection();
		String selectQuery = "select * from FOODMENU where NAME=?";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, food_name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				foodDTO = new FoodDTO();
				foodDTO.setFood_name(rs.getString("NAME"));
				foodDTO.setFood_price(rs.getString("PRICE"));
				foodDTO.setFood_kind(rs.getString("KIND"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return foodDTO;
	}

	public int updateFood(Map<String, String> map) {
		int su = 0;
		getConnection();
		String updateQuery = "update FOODMENU set NAME=?, PRICE=?, KIND=? where NAME=?";
		try {
			pstmt = con.prepareStatement(updateQuery);
			pstmt.setString(1, map.get("NAME"));
			pstmt.setString(2, map.get("PRICE"));
			pstmt.setString(3, map.get("KIND"));
			pstmt.setString(4, map.get("NAME2"));
			su = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return su;
	}

	public void login_delete(String id, String pwd) {

		getConnection();

		try {
			pstmt = con.prepareStatement("DELETE food_account WHERE ID = ?");
			pstmt.setString(1,id);

			pstmt.executeUpdate();
			System.out.println("사용자 <" + id + ">의 탈퇴처리가 완료 되었습니다.");
			System.out.println("서비스를 이용해주셔서 감사합니다.");
			login_logout();
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


	public int getFoodPrice(String foodName) throws SQLException {
		String sqlPrice = "SELECT PRICE FROM FOODMENU WHERE NAME LIKE ? AND ROWNUM=1";
		int price = 0;
		try (PreparedStatement pstmt = con.prepareStatement(sqlPrice)) {
			pstmt.setString(1, '%' + foodName + '%');
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					price = rs.getInt("PRICE");
				} else {
					throw new SQLException("음식을 찾을 수 없습니다.");
				}
			}
		}
		return price;
	}

	public void saveOrder(String userId, Map<String, Integer> orderDetails, double totalPrice, int foodcode) {
		String query = "INSERT INTO ORDERS (ID, CODE, QUANTITY, TOTALPRICE, ORDERDATE) "
				+ "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)"; 

		try (PreparedStatement pstmt = con.prepareStatement(query)) {
			for (Map.Entry<String, Integer> entry : orderDetails.entrySet()) {
				String foodName = entry.getKey(); 
				int quantity = entry.getValue();
				double itemPrice = getFoodPrice(foodName);
				double itemTotalPrice = itemPrice * quantity;

				pstmt.setString(1, userId);
				pstmt.setInt(2, foodcode); 
				pstmt.setInt(3, quantity);
				pstmt.setDouble(4, itemTotalPrice);
				pstmt.addBatch(); 
			}

			pstmt.executeBatch(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void ShowRes() {

		getConnection();
		System.out.println("************************");
		System.out.println("식당 데이터 베이스에 접속되었습니다");
		System.out.println("************************");
		System.out.println("식당코드"+"\t"+"식당명"+"\t"+"전화번호"+"\t"+"\t"+"위치"+"\t"+"\t"+"분류코드(1. 한식, 2. 중식, 3. 양식, 4. 양식)");

		String sql = "select * from RESTAURANT";
		try {
			pstmt = con.prepareStatement(sql); //생성
			rs = pstmt.executeQuery(); //실행


			while(rs.next()) {
				System.out.println(rs.getString("CODE") + "\t"
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

	public void SearchRes() {
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
				System.out.println(rs.getString("NAME"));
			}//while


			System.out.println("식당이름을 입력하시면 메뉴와 가격을 보여드립니다 ");	

			String code = scan.next();  // 조인문으로 변경

			String sql2 = "SELECT F.NAME, F.PRICE, F.CODE FROM FOODMENU F JOIN RESTAURANT R ON F.KIND = R.KIND WHERE R.NAME LIKE ? AND ROWNUM = 1 ";
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, '%' + code + '%');

			rs = pstmt.executeQuery(); //실행

			while(rs.next()) {
				System.out.println(rs.getString("NAME") + "\t" +
						PriceConvert(rs.getInt("PRICE"))+"원");

			}//while

			System.out.println();
			System.out.println("주문을 원하시면 1번을 눌러주세요");
			System.out.println();
			System.out.println("다른식당을 이용하시려면 2번을 눌러주세요");
			System.out.println();
			System.out.println("처음 화면으로 돌아가시려면 3번을 눌러주세요");

			int order = scan.nextInt();

			switch(order){

			case 1:


				String sql3 = "SELECT F.NAME, F.PRICE FROM FOODMENU F JOIN RESTAURANT R ON F.KIND = R.KIND WHERE R.NAME = ?";
				pstmt = con.prepareStatement(sql3);
				pstmt.setString(1, code);

				rs = pstmt.executeQuery(); //실행


				while(rs.next()) {
					System.out.println(rs.getString("NAME")+'\t'+
							PriceConvert(rs.getInt("PRICE"))+"원");

				}

				new OrderMain().execute();


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
	
	public String PriceConvert(int args_price) {
		String string_price = null;
		DecimalFormat df = new DecimalFormat("###,###");
		
		string_price = df.format(args_price);
			
		return string_price;
	}



	public void OrderInfoDB() {

		getConnection();//접속

		String query = "SELECT O.ID, F.NAME, O.QUANTITY, O.TOTALPRICE, O.ORDERDATE FROM ORDERS O JOIN FOODMENU F ON  O.CODE = F.CODE WHERE  O.ID = ?";

		try {

			String userId = session_id;

			
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, userId);

			ResultSet rs;

			rs = pstmt.executeQuery();

			String username = session_name;
			
			System.out.println(username + "님의 주문 내역입니다.");
			while (rs.next()) {
				String foodname = rs.getString("NAME");
				int quantity = rs.getInt("QUANTITY");
				String totalPrice = PriceConvert(rs.getInt("TOTALPRICE"));
				java.sql.Date orderDate;

				orderDate = rs.getDate("ORDERDATE");

				System.out.printf("이름: %s 메뉴: %s, 수량: %d, 총금액: %s, 주문일: %s%n",
						username, foodname, quantity, totalPrice, orderDate);
			} // s=문자열,d=int열, f=double열,%s%n 날짜 문자열
		}
		catch (SQLException e)  {
			// TODO Auto-generated catch block
			e.printStackTrace();            
		}
	}
	
	public int getFoodCodeFromInput(String foodname) {
		int foodcode = 0;
		
		getConnection();//접속

		String query = "SELECT F.CODE FROM FOODMENU F JOIN RESTAURANT R ON F.KIND = R.KIND WHERE F.NAME LIKE ?";

		try {
			 PreparedStatement pstmt = con.prepareStatement(query);
	            pstmt.setString(1, '%' + foodname + '%');

	            ResultSet rs = pstmt.executeQuery();

	            // Check if any result exists
	            if (rs.next()) {
	                foodcode = rs.getInt("CODE");
	            } else {
//	                System.out.println("일치하는 이름의 음식메뉴가 없습니다.");
	            }
		}
		catch (SQLException e)  {
			// TODO Auto-generated catch block
			e.printStackTrace();            
		} 
		return foodcode;
	}



}
