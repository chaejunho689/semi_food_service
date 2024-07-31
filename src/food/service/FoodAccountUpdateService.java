package food.service;

import java.util.Scanner;

import food.dao.FoodDAO;
import food.main.IndexMain;

public class FoodAccountUpdateService implements Food {
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		// DB - 싱글톤 
		FoodDAO foodDAO = FoodDAO.getInstance();
		IndexMain indexMain = new IndexMain();
				
		while(true) {
					
			if(foodDAO.common_logincheck() == false) {
				System.out.println("먼저 로그인 해주세요..");
				return;
			}
			
			System.out.println("인증을 위해 아이디를 입력하세요.");
			String id = scan.next();
			
			if(!foodDAO.session_id.equals(id)) {
				System.out.println("아이디가 틀렸습니다.");
				continue;
			}
			
			System.out.println("인증을 위해 비밀번호를 입력하세요.");
			String pwd = scan.next();
			
			if(!foodDAO.session_pwd.equals(pwd)) {
				System.out.println("비밀번호가 틀렸습니다.");
				continue;
			}
			
			System.out.println("수정할 비밀번호를 입력하세요.");
			String new_pwd = scan.next();

			System.out.println("수정할 이름을 입력하세요.");
			String new_name = scan.next();
			
			foodDAO.login_update(id, new_pwd, new_name);
			break;
		}
			
	}

}
