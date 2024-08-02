package food.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import food.dao.FoodDAO;

public class OrderInfo implements Food {

    @Override
    public void execute() {
    	
    	FoodDAO foodDAO = FoodDAO.getInstance();
    	
    	foodDAO.OrderInfoDB();
    	
    
}
}