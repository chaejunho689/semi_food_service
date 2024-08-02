package food.service;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import food.bean.FoodDTO;
import food.dao.FoodDAO;
import food.main.IndexMain;

public class FoodAdminAccountView implements Food {

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		FoodDAO foodDAO = FoodDAO.getInstance();
		IndexMain indexMain = new IndexMain();
	       List<FoodDTO> foodList = foodDAO.list();
	       
	       System.out.println();
	       System.out.println("-------------------------------------");
	       System.out.printf("%-10s %-10s %-10s %-10s%n", "ID", "PWD", "NAME", "CODE");
	       System.out.println("-------------------------------------");
	       
	       for (FoodDTO foodDTO : foodList) {
	           System.out.printf("%-10s %-10s %-10s %-10d%n",
	                   foodDTO.getId(),
	                   foodDTO.getPwd(),
	                   foodDTO.getName(),
	                   foodDTO.getCode());
	       }
	        
	        System.out.println();
	        System.out.println("뒤로 가실려면 ENTER를 누르세요");
	        try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        indexMain.menu_admin();
	}

}
