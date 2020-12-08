package Model;

import java.sql.Connection;

public interface MathModel {

	public int getScore();

	public void setScore(int score,String extraInfo,String game);

	public int getValue();
	
	public Connection connect();
	
	public void enterStudent(String input);
	
	public void setTries(String student);
}
