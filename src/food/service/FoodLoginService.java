package food.service;

import java.util.Scanner;

import food.dao.FoodDAO;
import food.main.IndexMain;


public class FoodLoginService implements Food {
	@Override
	public void execute() {

		Scanner scan = new Scanner(System.in);
		// DB - 싱글톤 
		FoodDAO foodDAO = FoodDAO.getInstance();
		IndexMain indexMain = new IndexMain();
		
		while(true) {
			System.out.println("아이디를 입력하세요.");
			String id = scan.next();
			
			System.out.println("비밀번호를 입력하세요.");
			String pwd = scan.next();

			boolean id_check = foodDAO.common_isExistId(id);
			if(id_check == false) {
				System.out.println("아이디 또는 비밀번호가 잘못되었습니다..");
			} else {
				foodDAO.login_login(id, pwd);
				foodDAO.common_logincheck();
				String sessionName = foodDAO.session_name;
				System.out.println(sessionName+ "님이 로그인하였습니다.");
				indexMain.menu_user();
				break;
			}
		}
		
		
		
	}
}
