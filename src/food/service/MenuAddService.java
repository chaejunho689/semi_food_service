package food.service;

import java.util.Scanner;

import food.bean.FoodDTO;
import food.dao.FoodDAO;
import food.main.IndexMain;

public class MenuAddService implements Food {

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		
		System.out.print("추가할 메뉴 이름 입력 : ");
		String food_name = scan.nextLine();
				
		int food_price = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("메뉴 가격 입력 (최대 100만원) : ");
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
		
		FoodDTO foodDTO = new FoodDTO();
		FoodDAO foodDAO = FoodDAO.getInstance();
		IndexMain indexMain = new IndexMain();
		
		int food_code = foodDAO.getFood_code(foodDTO);
		foodDTO.setFood_code(food_code);
		foodDTO.setFood_name(food_name);
		foodDTO.setFood_price(food_price);
		foodDTO.setFood_kind(food_kind);
		
	    int su= foodDAO.addFood(foodDTO);
	    System.out.println();
	    System.out.println("신규 메뉴 추가되었습니다");
	    indexMain.menu_admin();
		
	}

}
