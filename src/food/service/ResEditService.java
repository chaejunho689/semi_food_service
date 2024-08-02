package food.service;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import food.bean.FoodDTO;
import food.dao.FoodDAO;
import food.main.IndexMain;


public class ResEditService implements Food {

	@Override
	public void execute() {
		FoodDAO foodDAO = FoodDAO.getInstance();
	    List<FoodDTO> resList = foodDAO.res_list();
	       
	       System.out.println();
	       System.out.println("------------------------------------------------------------------");
	       System.out.printf("%-10s %-20s %-20s %-10s%n", "NAME", "PNUMBER", "ADDRESS", "KIND");
	       System.out.println("------------------------------------------------------------------");
	       
	       for (FoodDTO foodDTO : resList) {
	           System.out.printf("%-10s %-20s %-20s %-10d%n",
	                   foodDTO.getRes_name(),
	                   foodDTO.getRes_pnumber(),
	                   foodDTO.getRes_address(),
	                   foodDTO.getRes_kind());
	       }
	       
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("수정 원하는 식당 이름 입력 : ");
		String res_name = scan.nextLine();
		
		IndexMain indexMain = new IndexMain();
		FoodDTO foodDTO = foodDAO.getResName(res_name);
		String oldRes_Name = res_name;
		
		if(foodDTO == null) {
			System.out.println();
			System.out.println("검색하신 식당이 존재하지 않습니다");
			return;
		}
		
		System.out.println();
		System.out.print("수정할 이름 입력 : ");
		String newRes_Name = scan.nextLine();
		System.out.print("수정할 주소 입력 : ");
		String res_address = scan.nextLine();
		System.out.print("수정할 휴대폰번호 입력 (예시:010-1234-5678) : ");
		String res_pnumber = scan.nextLine();
		int res_kind = 0;
		boolean validInput = false;
		while (!validInput) {
			System.out.print("식당 종류 입력 (예시: 한식(1), 양식(2), 중식(3), 일식(4)) : ");
			try {
				res_kind = Integer.parseInt(scan.nextLine());
				if (res_kind >= 1 && res_kind <= 4) {
					validInput = true;
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
		map.put("NAME", newRes_Name);
		map.put("ADDRESS", res_address);
		map.put("PNUMBER", res_pnumber);
		map.put("KIND", String.valueOf(res_kind));
		map.put("NAME2", oldRes_Name);

		int su = foodDAO.updateRes(map);
		
		System.out.println();
		System.out.println("식당 정보가 수정되었습니다");
		indexMain.menu_admin();
	}

}
