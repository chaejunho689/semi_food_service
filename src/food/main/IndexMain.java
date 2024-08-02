package food.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import food.dao.FoodDAO;
import food.service.AdminFoodMenu;
import food.service.AdminMemberMenu;
import food.service.AdminResMenu;
import food.service.Food;
import food.service.FoodAccountDelete;
import food.service.FoodAccountUpdateService;
import food.service.FoodAdminAccountManage;
import food.service.FoodAdminAccountView;
import food.service.FoodAdminAuthUpdate;
import food.service.FoodLoginService;
import food.service.FoodLogoutService;
import food.service.FoodRegistService;
import food.service.MenuAddService;
import food.service.MenuDeleteService;
import food.service.MenuEditService;
import food.service.OrderInfo;
import food.service.ResAddService;
import food.service.ResDeleteService;
import food.service.ResEditService;
import food.service.SearchMain;
import food.service.SelectMain;

public class IndexMain {

	public void menu() {
		Scanner scan = new Scanner(System.in);
		int num;
		Food food = null;
		
		FoodDAO foodDAO = FoodDAO.getInstance();
		IndexMain indexMain = new IndexMain();
		
		while(true) {
			System.out.println();
			System.out.println("***********************");
			System.out.println("1. 회원가입 ");
			System.out.println("2. 로그인 ");
			System.out.println("3. 로그아웃 ");
			System.out.println("4. 회원정보수정 ");
			System.out.println("5. 회원탈퇴 ");
			if(foodDAO.session_id != null) {
				if(foodDAO.common_adminYn() == true) {
					System.out.println("6. 사용자 메뉴 접속");
					System.out.println("7. 관리자 메뉴 접속");
				} else {
					System.out.println("6. 사용자 메뉴 접속");
				}
			}
			System.out.println("0. 프로그램 종료 ");
			System.out.println("***********************");
			System.out.print("메뉴입력 : ");
			
			try {
				num = scan.nextInt();
				if(num < 0 || num > 7) {
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요.");
                scan.next(); // 잘못된 입력을 읽어들이고 다음 입력을 기다리게 함
                continue; // 잘못된 입력인 경우 루프를 계속
			}
			
            if (num == 0) break; // 프로그램 종료

            switch(num) {
			case 6 :
				indexMain.menu_user();
				break;
			case 7 :
				if(foodDAO.common_adminYn() == true) {
					indexMain.menu_admin();
					System.out.println("debugs");
				} else {
					System.out.println("권한이 없습니다.");
				}
				break;
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
			case 5 :
				food = new FoodAccountDelete();
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
			System.out.println("2. 주문내역 확인");
			System.out.println("3. 회원메뉴");
			System.out.println("***********************");
			System.out.print(" 번호 :");
			
			try {
				num = scan.nextInt();
				if(num < 1 || num > 3) {
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요.");
                scan.next(); // 잘못된 입력을 읽어들이고 다음 입력을 기다리게 함
                continue; // 잘못된 입력인 경우 루프를 계속
			}
			
			
			switch(num) {
			case 1 :
				food = new SearchMain();
				break;
			case 2 :
				food = new OrderInfo();
				break;
			case 3 :
				IndexMain indexMain = new IndexMain();
				indexMain.menu();
				break;
			}
			food.execute();
		}
	}
	
	
	public void menu_admin() {
		Scanner scan = new Scanner(System.in);
		int num;
		Food food = null;
		
		while(true) {
			System.out.println();
			System.out.println("***********************");
			System.out.println("1. 식당 추가 및 수정/삭제");
			System.out.println("2. 메뉴 추가 및 수정/삭제");
			System.out.println("3. 회원 관리");
			System.out.println("4. 뒤로가기 ");
			System.out.println("***********************");
			System.out.print("   번호 : ");
			try {
				num = scan.nextInt();
				if(num < 1 || num > 4) {
					System.out.println();
					System.out.println("1 ~ 4 사이의 숫자를 입력하세요");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("숫자만 입력하세요!");
                scan.next(); // 잘못된 입력을 읽어들이고 다음 입력을 기다리게 함
                continue; // 잘못된 입력인 경우 루프를 계속
			}
			while (true) {
				if(num == 4) {
					IndexMain indexMain = new IndexMain();
					indexMain.menu();
				} else if(num == 1) {
					food = new AdminResMenu();
					food.execute();
				} else if(num == 2) {
					food = new AdminFoodMenu();
					food.execute();
				} else if(num == 3) {
					food = new AdminMemberMenu();
					food.execute();
				} else {
					System.out.println();
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		IndexMain indexMain = new IndexMain();
		indexMain.menu();
		System.out.println("프로그램을 종료합니다.");
	}
}
