package food.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import food.dao.FoodDAO;
import food.main.IndexMain;

public class SearchMain implements Food {
	
	
	public void execute() {
		
		FoodDAO foodDAO = FoodDAO.getInstance();
		
		foodDAO.ShowRes();
		foodDAO.SearchRes();
	}
	
}