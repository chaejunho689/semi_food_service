package food.main;

import java.util.Scanner;

import food.service.Food;
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
			System.out.println("4. 프로그램 종료 ");
			System.out.println("***********************");
			System.out.print(" 번호 :");
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
			System.out.print(" 번호 :");
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
			System.out.println("1. 회원가입 ");
			System.out.println("2. 로그인 ");
			System.out.println("3. 로그아웃 ");
			System.out.println("4. 프로그램 종료 ");
			System.out.println("***********************");
			System.out.print(" 번호 :");
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
