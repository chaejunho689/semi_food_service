package food.service;

import java.util.Scanner;

import food.bean.FoodDTO;
import food.dao.FoodDAO;

public class MenuAddService implements Food {

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		
		System.out.print("추가할 메뉴 이름 입력 : ");
		String food_name = scan.nextLine();
		System.out.print("메뉴 가격 입력 : ");
		String food_price = scan.nextLine();
		System.out.print("음식 종류 입력 : ");
		String food_kind = scan.nextLine();
		
		FoodDTO foodDTO = new FoodDTO();
		FoodDAO foodDAO = FoodDAO.getInstance();
		
		int food_code = foodDAO.getFood_code(foodDTO);
		foodDTO.setFood_code(food_code);
		foodDTO.setFood_name(food_name);
		foodDTO.setFood_price(food_price);
		foodDTO.setFood_kind(food_kind);
		
	    int su= foodDAO.addFood(foodDTO);
	    System.out.println();
	    System.out.println("신규 메뉴 추가되었습니다");
		
	}

}
