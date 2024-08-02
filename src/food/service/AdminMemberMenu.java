package food.service;

import java.util.Scanner;

import food.main.IndexMain;

public class AdminMemberMenu implements Food {
	
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		int num;
		Food food = null;
		
		while(true) {
			System.out.println();
			System.out.println("***********************");
			System.out.println("1. 모든 회원 보기 ");
			System.out.println("2. 회원 계정 삭제 ");
			System.out.println("3. 회원 관리자 기능 부여 ");
			System.out.println("4. 뒤로가기 ");
			System.out.println("***********************");
			System.out.print("   번호 : ");
			num = scan.nextInt();
			if(num == 4) {
				IndexMain indexMain = new IndexMain();
				indexMain.menu_admin();
			} else if(num == 1) {
				food = new FoodAdminAccountView();
			} else if(num == 2) {
				food = new FoodAdminAccountManage();
			} else if(num == 3) {
				food = new FoodAdminAuthUpdate();;
			} else {
				System.out.println();
				System.out.println("1 ~ 4 사이의 숫자를 입력하세요");
			}
			
			food.execute();
			
		}
		
	}
}
