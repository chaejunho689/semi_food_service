package food.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import food.bean.FoodDTO;
import food.dao.FoodDAO;

public class MenuEditService implements Food {

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("메뉴 이름 검색 : ");
		String food_name = scan.nextLine();
		
		FoodDAO foodDAO = FoodDAO.getInstance();
		FoodDTO foodDTO = foodDAO.getFoodName(food_name);
		String oldFood_Name = food_name;
		
		if(foodDTO == null) {
			System.out.println();
			System.out.println("검색하신 메뉴가 존재하지 않습니다");
			return;
		}
		
		System.out.println(foodDTO);
		System.out.println();
		System.out.print("수정 메뉴 이름 입력 : ");
		String newFood_Name = scan.nextLine();
		System.out.print("수정할 메뉴 가격 입력 : ");
		String food_price = scan.nextLine();
		System.out.print("수정할 메뉴 종류 입력 : ");
		String food_kind = scan.nextLine();
		
		Map<String, String> map = new HashMap<>();
		map.put("NAME", newFood_Name);
		map.put("PRICE", food_price);
		map.put("KIND", food_kind);
		map.put("NAME2", oldFood_Name);
	
		int su = foodDAO.updateFood(map);
		
		System.out.println();
		System.out.println("메뉴 정보가 수정되었습니다");
		
	}

}
