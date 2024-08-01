package food.service;

import java.util.HashMap; 
import java.util.Map;
import java.util.Scanner;

import food.bean.FoodDTO;
import food.dao.FoodDAO;


public class ResEditService implements Food {

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("식당 이름 검색 : ");
		String res_name = scan.nextLine();
		
		FoodDAO foodDAO = FoodDAO.getInstance();
		FoodDTO foodDTO = foodDAO.getResName(res_name);
		String oldRes_Name = res_name;
		
		if(foodDTO == null) {
			System.out.println();
			System.out.println("검색하신 식당이 존재하지 않습니다");
			return;
		}
		
		System.out.println(foodDTO);
		System.out.println();
		System.out.print("수정할 식당 이름 입력 : ");
		String newRes_Name = scan.nextLine();
		System.out.print("수정할 주소 입력 : ");
		String res_address = scan.nextLine();
		System.out.print("수정할 휴대폰번호 입력 (예시:010-1234-5678) : ");
		String res_pnumber = scan.nextLine();
		System.out.println("수정할 종류 정보 입력 (예시: 한식(1), 양식(2), 중식(3), 일식(4)) : ");
		int res_kind = scan.nextInt();
		
		Map<String, String> map = new HashMap<>();
		map.put("NAME", newRes_Name);
		map.put("ADDRESS", res_address);
		map.put("PNUMBER", res_pnumber);
		map.put("KIND", String.valueOf(res_kind));
		map.put("NAME2", oldRes_Name);

		int su = foodDAO.updateRes(map);
		
		System.out.println();
		System.out.println("식당 정보가 수정되었습니다");
	}

}
