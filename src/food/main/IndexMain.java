package food.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import food.service.Food;
import food.service.FoodAccountUpdateService;
import food.service.FoodLoginService;
import food.service.FoodLogoutService;
import food.service.FoodRegistService;

public class IndexMain {

	public void menu() {
		Scanner scan = new Scanner(System.in);
		int num;
		Food food = null;
		
		while(true) {
			System.out.println();
			System.out.println("***********************");
			System.out.println("1. 회원가입 ");
			System.out.println("2. 로그인 ");
			System.out.println("3. 로그아웃 ");
			System.out.println("4. 회원정보수정 ");
			System.out.println("5. 프로그램 종료 ");
			System.out.println("***********************");
			System.out.print("메뉴입력 :");
			
			try {
				num = scan.nextInt();
				if(num <1 || num > 5) {
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요.");
                scan.next(); // 잘못된 입력을 읽어들이고 다음 입력을 기다리게 함
                continue; // 잘못된 입력인 경우 루프를 계속
			}
			
            if (num == 5) break; // 프로그램 종료

			switch(num) {
			case 1 :
				food = new FoodRegistService();
				break;
			case 2 :
				food = new FoodLoginService();
				break;
			case 3 :
				food = new FoodLogoutService();
				break;
			case 4 :
				food = new FoodAccountUpdateService();
				break;
			}
			

			
			food.execute();;
		}
	}
	
	
	public void menu_user() {
		Scanner scan = new Scanner(System.in);
		int num;
		Food food = null;
		
		while(true) {
			System.out.println();
			System.out.println("***********************");
			System.out.println("1. 식당 검색");
			System.out.println("2. 식당 목록 ");
			System.out.println("3. 뒤로가기");
			System.out.println("***********************");
			System.out.print("메뉴입력 :");
			num = scan.nextInt();
			
			if(num == 1) {
				menu();
			} else if(num == 2) {
				menu();
			} else if(num == 3) {
				menu();
			}
			food.execute();;
		}
	}
	
	
	public void menu_admin() {
		Scanner scan = new Scanner(System.in);
		int num;
		Food food = null;
		
		while(true) {
			System.out.println();
			System.out.println("***********************");
			System.out.println("1. 관리자 ");
			System.out.println("2. 로그인 ");
			System.out.println("3. 로그아웃 ");
			System.out.println("4. 프로그램 종료 ");
			System.out.println("***********************");
			System.out.print("메뉴입력 :");
			num = scan.nextInt();
			
			if(num == 4) break;
			if(num == 1) {
				food = new FoodRegistService();
			} else if(num == 2) {
				food = new FoodLoginService();
			} else if(num == 3) {
				food = new FoodLogoutService();
			}
			
			food.execute();;
		}
	}
	
	public static void main(String[] args) {
		IndexMain indexMain = new IndexMain();
		indexMain.menu();
		System.out.println("프로그램을 종료합니다.");
	}
}
