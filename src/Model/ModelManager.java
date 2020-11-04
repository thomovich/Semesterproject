package Model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ModelManager implements MathModel {

	private int score;
	private int Value;
	private Connection connect;

	/*
	private final String url ="jdbc:postgresql://dumbo.db.elephantsql.com:5432/mqygqwnx";
	private final String user = "mqygqwnx";
	private final String password = "yx2Id-PIXB6_u1BE3VUE8HJ7SnDUyHtS";*/
	
	
	public Connection connect() {
		
		try {
			
			connect = DriverManager.getConnection("jdbc:postgresql://dumbo.db.elephantsql.com:5432/mqygqwnx", "mqygqwnx", "yx2Id-PIXB6_u1BE3VUE8HJ7SnDUyHtS");
			
		}
		catch(Exception e) {
			System.out.println("connection failed");
			e.printStackTrace();
		}
		return connect;
	}
	
	public ModelManager() {
		Driver driver = new org.postgresql.Driver(); 
				try {
					DriverManager.registerDriver(driver);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	
	public void enterValue(String input) {
		String sql = "INSERT INTO public.testing VALUES('"+input+"');";
		
		try {
			connect();
			Statement statement = connect.createStatement();
			statement.executeQuery(sql);
			System.out.println("success in adding user to database");
			connect.close();
		}
		catch(SQLException e) {
			System.out.println("Error trying to add user");
			e.printStackTrace();
		}
	}
	
	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int getValue() {
		return Value;

	}

}
