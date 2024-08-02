package food.service;

import java.util.Scanner;

import food.bean.FoodDTO;
import food.dao.FoodDAO;
import food.main.IndexMain;

public class ResAddService implements Food {

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		
		System.out.print("추가할 식당 이름 입력 : ");
		String res_name = scan.nextLine();
		System.out.print("식당 휴대폰번호 입력 (예시:010-1234-5678) : ");
		String res_pnumber = scan.nextLine();
		System.out.print("식당 주소 입력 : ");
		String res_address = scan.nextLine();
		System.out.println("식당 종류 입력 (예시: 한식(1), 양식(2), 중식(3), 일식(4)) : ");
		int res_kind = scan.nextInt();
		
		FoodDTO foodDTO = new FoodDTO();
		FoodDAO foodDAO = FoodDAO.getInstance();
		IndexMain indexMain = new IndexMain();
		
		int res_code = foodDAO.getRes_code(foodDTO);
		foodDTO.setRes_code(res_code);
		foodDTO.setRes_name(res_name);
		foodDTO.setRes_pnumber(res_pnumber);
		foodDTO.setRes_address(res_address);
		foodDTO.setRes_kind(res_kind);
		
	
	    int su= foodDAO.addRes(foodDTO);
	    System.out.println();
	    System.out.println("신규 식당이 추가되었습니다");
	    indexMain.menu_admin();
		
	}

}
