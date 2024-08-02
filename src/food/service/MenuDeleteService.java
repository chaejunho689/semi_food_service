package food.service;

import java.util.Scanner;

import food.dao.FoodDAO;
import food.main.IndexMain;

public class MenuDeleteService implements Food {

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("삭제할 음식 이름 입력 : ");
		String food_name = scan.nextLine();
		
		FoodDAO foodDAO = FoodDAO.getInstance();
		IndexMain indexMain = new IndexMain();
		
		boolean exist = foodDAO.isExistFoodName(food_name);
		if (!exist) {
			System.out.println();
			System.out.println("해당 메뉴가 존재하지 않습니다");
			System.out.println();
			return;
		}
	
	    int su= foodDAO.deleteFood(food_name);
	    System.out.println();
	    System.out.println("메뉴가 삭제되었습니다");
	    indexMain.menu_admin();
		
	}

}
