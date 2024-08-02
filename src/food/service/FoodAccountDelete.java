package food.service;

import java.util.Scanner;

import food.bean.FoodDTO;
import food.dao.FoodDAO;
import food.main.IndexMain;

public class FoodAccountDelete implements Food {
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
			
	        if(foodDAO.common_adminYn() == true) {
	            System.out.println("관리자 계정은 탈퇴 할 수 없습니다.");
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
			
			System.out.println("정말 탈퇴하시겠습니까? (Y/N)");
			String yn = scan.next();
			
			if(yn.equals("Y") || yn.equals("y") || yn.equals("Yes") || yn.equals("yes") || yn.equals("네") || yn.equals("탈퇴"))
			{
				foodDAO.login_delete(id, pwd);
			} else {
				System.out.println("취소되었습니다.");
				return;
			}
			break;
		}
			
	}

}
