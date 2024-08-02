package food.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import food.bean.FoodDTO;
import food.dao.FoodDAO;
import food.main.IndexMain;

public class MenuEditService implements Food {

	@Override
	public void execute() {
		FoodDAO foodDAO = FoodDAO.getInstance();
	    List<FoodDTO> menuList = foodDAO.menu_list();
	       
	       System.out.println();
	       System.out.println("--------------------------------");
	       System.out.printf("%-10s %-10s %-10s%n", "NAME", "PRICE", "KIND");
	       System.out.println("--------------------------------");
	       
	       for (FoodDTO foodDTO : menuList) {
	           System.out.printf("%-10s %-10s %-10d%n",
	                   foodDTO.getFood_name(),
	                   foodDTO.getFood_price(),
	                   foodDTO.getFood_kind());
	       }
	       
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("수정 원하는 메뉴 이름 검색 : ");
		String food_name = scan.nextLine();
		
		IndexMain indexMain = new IndexMain();
		FoodDTO foodDTO = foodDAO.getFoodName(food_name);
		String oldFood_Name = food_name;
		
		if(foodDTO == null) {
			System.out.println();
			System.out.println("검색하신 메뉴가 존재하지 않습니다");
			return;
		}
		
		System.out.println();
		System.out.print("수정할 이름 입력 : ");
		String newFood_Name = scan.nextLine();
	
		int food_price = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("수정할 가격 입력 (최대 100만원) : ");
            try {
                food_price = Integer.parseInt(scan.nextLine());
                if (food_price >= 0 && food_price <= 1000000) {
                    validInput = true;
                } else {
                    System.out.println();
                    System.out.println("100만원 미만으로 입력하세요!");
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("숫자만 입력하세요!");
            }
        }
		
        int food_kind = 0;
		boolean validInput2 = false;
		while (!validInput2) {
			System.out.print("음식 종류 입력 (예시: 한식(1), 양식(2), 중식(3), 일식(4)) : ");
			try {
				food_kind = Integer.parseInt(scan.nextLine());
				if (food_kind >= 1 && food_kind <= 4) {
					validInput2 = true;
				} else {
					System.out.println();
					System.out.println("1~4 사이의 숫자를 입력하세요!");
				}
			} catch (NumberFormatException e) {
				System.out.println();
				System.out.println("숫자만 입력하세요!");
			}
		}
		
		Map<String, String> map = new HashMap<>();
		map.put("NAME", newFood_Name);
		map.put("PRICE", String.valueOf(food_price));
		map.put("KIND", String.valueOf(food_kind));
		map.put("NAME2", oldFood_Name);
	
		int su = foodDAO.updateFood(map);
		
		System.out.println();
		System.out.println("메뉴 정보가 수정되었습니다");
		indexMain.menu_admin();
		
	}

}
