package food.service;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import food.dao.FoodDAO;
import food.main.IndexMain;

public class OrderMain implements Food {
	
	@Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
		FoodDAO foodDAO = FoodDAO.getInstance();
        
        try {
            foodDAO.showFoodMenu();

            Map<Integer, Integer> orderDetails = new HashMap<>();
            double totalPrice = 0;

            boolean cont = true;
            while (cont) {
                System.out.println("주문하실 음식의 코드를 입력해주세요!");
                int foodCode = scan.nextInt();

                try {
                    double price = foodDAO.getFoodPrice(foodCode);

                    System.out.println("몇개   주문하시겠어요?");
                    int quantity = scan.nextInt();

                    double itemTotalPrice = price * quantity;
                    totalPrice += itemTotalPrice;

                    orderDetails.put(foodCode, quantity);

                    System.out.println("최종금액은 " + (int) totalPrice + "원입니다");

                    System.out.println("추가 주문 하시겠어요? (y/n)");
                    scan.next(); 
                    String extra = scan.nextLine();

                    cont = extra.equalsIgnoreCase("y"); //y 이외면 false while 문 탈출

                } catch (SQLException e) {
                	e.printStackTrace();
                    cont = false;
                }
            }
           
            
            String userId = foodDAO.session_name;
            
        	//System.out.println(userId+ "님이 주문하였습니다.");
            
        	System.out.println(userId+ "님이 주문하였습니다.");

        	foodDAO.saveOrder(userId, orderDetails, totalPrice);

            System.out.println("배달의민족 주문!");
            System.out.println("결제하실금액은: " + (int) totalPrice + "원입니다");

            //-------------------------------------------------------------------------

            System.out.println();
            System.out.println("추가 주문을 원하시면 1번을 눌러주세요");
            System.out.println();
            System.out.println("다른식당을 이용하시려면 2번을 눌러주세요");
            System.out.println();
            System.out.println("처음 화면으로 돌아가시려면 3번을 눌러주세요");

            int order = scan.nextInt();

            switch (order) {
                case 1:
                    new OrderMain().execute(); 
                    break;
                case 2:
                    new SearchMain().execute(); 
                    break;
                case 3:
                    new IndexMain().menu_user(); 
                    break;
            }
        } finally {
            scan.close();
        }
    }
}