package food.service;

import food.dao.FoodDAO;

public class FoodLogoutService implements Food {
	@Override
	public void execute() {
			FoodDAO foodDAO = FoodDAO.getInstance();
			
			if(foodDAO.common_logincheck() == false) {
				return;
			}
			
			foodDAO.login_logout();
	}
}
