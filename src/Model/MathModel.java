package Model;

import java.sql.Connection;

public interface MathModel {

	public int getScore();

	public void setScore(int score);

	public int getValue();
	
	public Connection connect();
}
