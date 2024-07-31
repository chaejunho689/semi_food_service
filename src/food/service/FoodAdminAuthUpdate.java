package food.service;

import java.util.Scanner;

import food.dao.FoodDAO;
import food.main.IndexMain;

public class FoodAdminAuthUpdate implements Food{
	@Override
	public void execute() {

		Scanner scan = new Scanner(System.in);
		// DB - 싱글톤 
		FoodDAO foodDAO = FoodDAO.getInstance();
		IndexMain indexMain = new IndexMain();
		
		while(true) {
			System.out.println("관리자 권한을 부여할 아이디를 입력하세요.");
			String id = scan.next();
			
			boolean id_check = foodDAO.common_isExistId(id);

			if(id_check == false) {
				System.out.println("아이디 또는 비밀번호가 잘못되었습니다..");
			} else {
				foodDAO.admin_authUpdate(id);
				indexMain.menu_admin();
				break;
			}
		}
		
	}
}
