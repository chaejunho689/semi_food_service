package food.service;

import java.util.Scanner;

import food.main.IndexMain;

public class AdminFoodMenu implements Food {
	
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		int num;
		Food food = null;
		
		while(true) {
			System.out.println();
			System.out.println("***********************");
			System.out.println("1. 신규 음식 추가 ");
			System.out.println("2. 음식 정보 보기/수정 ");
			System.out.println("3. 음식 정보 삭제 ");
			System.out.println("4. 뒤로가기 ");
			System.out.println("***********************");
			System.out.print("   번호 : ");
			num = scan.nextInt();
			if(num == 4) {
				IndexMain indexMain = new IndexMain();
				indexMain.menu_admin();
			} else if(num == 1) {
				food = new MenuAddService();
			} else if(num == 2) {
				food = new MenuEditService();
			} else if(num == 3) {
				food = new MenuDeleteService();
			} else {
				System.out.println();
				System.out.println("1 ~ 4 사이의 숫자를 입력하세요");
			}
	
			food.execute();
			
		}
		
	}
}
