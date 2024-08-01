package food.service;

import java.util.Scanner;

import food.dao.FoodDAO;

public class ResDeleteService implements Food {

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("삭제할 식당 이름 입력 : ");
		String res_name = scan.nextLine();
		
		FoodDAO foodDAO = FoodDAO.getInstance();
		
		boolean exist = foodDAO.isExistResName(res_name);
		if (!exist) {
			System.out.println();
			System.out.println("찾으시는 식당이 없습니다");
			System.out.println();
			return;
		}
	
	    int su= foodDAO.deleteRes(res_name);
	    System.out.println();
	    System.out.println("식당이 삭제되었습니다");
	}

}
