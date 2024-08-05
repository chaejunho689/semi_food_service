package food.service;

import java.sql.SQLException;
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
        
        int foodcode = 0;
        
        try {
            Map<String, Integer> orderDetails = new HashMap<>();
            int totalPrice = 0;

            boolean cont = true;
            while (cont) {
                System.out.println("주문하실 음식의 이름을 입력해주세요!");
                String foodname = scan.nextLine(); 
                
                foodcode = foodDAO.getFoodCodeFromInput(foodname);
                if(foodcode == 0) {
                	System.out.println("일치하는 이름의 음식메뉴가 없습니다.");
                	continue;
                }
                
                
                try {
                    int price = foodDAO.getFoodPrice(foodname);

                    System.out.println("몇개 주문하시겠어요?");
                    int quantity = scan.nextInt();
                    scan.nextLine(); 

                    int itemTotalPrice = price * quantity; 
                    totalPrice += itemTotalPrice;

                    orderDetails.put(foodname, quantity);
                    System.out.printf("현재까지의 총 금액은 %s원입니다%n", foodDAO.PriceConvert(totalPrice));

                    cont = false; 

                } catch (SQLException e) {
                    System.out.println("주문 처리 중 오류가 발생했습니다. 다시 시도해주세요.");
                    e.printStackTrace();
                    
                }
            }
           
            String userId = foodDAO.session_id;
            System.out.println(userId + "님이 주문하였습니다.");

            foodDAO.saveOrder(userId, orderDetails, totalPrice, foodcode);

            System.out.printf("비트바이트 주문! 결제하실 금액은 %s원입니다%n", foodDAO.PriceConvert(totalPrice));

            System.out.println();
          
            System.out.println("처음 화면으로 돌아갑니다!");
            
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            
            int order = 1;

            switch (order) {
                case 1:
                    new IndexMain().menu_user(); 
                    break;
       
            }
        } finally {
          
        }
    }
}