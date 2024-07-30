package food.service;

import java.util.Scanner;

import food.bean.FoodDTO;
import food.dao.FoodDAO;

public class FoodRegistService implements Food {
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		FoodDAO foodDAO = FoodDAO.getInstance();
		
		while(true) {
			System.out.println("아이디를 입력하세요.");
			String id = scan.next();
			
			//DB
			boolean exist = foodDAO.common_isExistId(id);
			
			if(exist == true) {
				System.out.println("중복된 ID입니다.");
				return;
			} else {
				System.out.println("사용 가능한 아이디입니다.");
				

				System.out.println("비밀번호를 입력하세요.");
				String pwd = scan.next();
		
				System.out.println("이름을 입력하세요.");
				String name = scan.next();
				
				FoodDTO foodDTO = new FoodDTO();
				foodDTO.setName(name);
				foodDTO.setId(id);
				foodDTO.setPwd(pwd);
				//입력
				foodDAO.login_register(foodDTO);
				break;
			}
		}

	}
}
