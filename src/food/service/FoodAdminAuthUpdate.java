package food.service;

import java.util.List;
import java.util.Scanner;

import food.bean.FoodDTO;
import food.dao.FoodDAO;
import food.main.IndexMain;

public class FoodAdminAuthUpdate implements Food{
	@Override
	public void execute() {
		FoodDAO foodDAO = FoodDAO.getInstance();
		IndexMain indexMain = new IndexMain();
	       List<FoodDTO> foodList = foodDAO.list();
	       
	       System.out.println();
	       System.out.println("-------------------------------------");
	       System.out.printf("%-10s %-10s %-10s %-10s%n", "ID", "PWD", "NAME", "CODE");
	       System.out.println("-------------------------------------");
	       
	       for (FoodDTO foodDTO : foodList) {
	           System.out.printf("%-10s %-10s %-10s %-10d%n",
	                   foodDTO.getId(),
	                   foodDTO.getPwd(),
	                   foodDTO.getName(),
	                   foodDTO.getCode());
	       }
	     
	    System.out.println();
		Scanner scan = new Scanner(System.in);
		// DB - 싱글톤 
		foodDAO = FoodDAO.getInstance();
		indexMain = new IndexMain();
		
		while(true) {
			System.out.println("관리자 권한을 부여할 아이디를 입력하세요 : ");
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
