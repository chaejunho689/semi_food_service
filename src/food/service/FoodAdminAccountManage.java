package food.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import food.bean.FoodDTO;
import food.dao.FoodDAO;

public class FoodAdminAccountManage implements Food {

	@Override
	public void execute() {	
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("삭제할 회원 ID 입력 : ");
		String id = scan.nextLine();
		
		FoodDAO foodDAO = FoodDAO.getInstance();
		
		boolean exist = foodDAO.common_isExistId(id);
		if (!exist) {
			System.out.println();
			System.out.println("입력하신 회원이 존재하지 않습니다");
			System.out.println();
			return;
		}
	
	    int su= foodDAO.deleteId(id);
	    System.out.println();
	    System.out.println("해당 회원 정보가 삭제되었습니다");
	}
}
		
