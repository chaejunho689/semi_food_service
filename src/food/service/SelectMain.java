package food.service;

import food.dao.FoodDAO;

public class SelectMain implements Food{
	
	public void execute() {
		
		FoodDAO foodDAO = FoodDAO.getInstance();
		
		foodDAO.ShowRes();

}
}

