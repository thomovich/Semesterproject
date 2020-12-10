package Model;

import java.sql.Connection;

import javafx.collections.ObservableList;

public interface MathModel {

	public int getScore();

	public void setScore(int score,String extraInfo,String game);

	public int getValue();
	
	public Connection connect();
	
	public void enterStudent(String input);
	
	public void setTriesChapter5(String student);
	
	public ObservableList<Content> getTable(String student, String chapter);
	
	public ObservableList<Content> getAverageScoreGame(String student,String chapter);
	
	public void setCurrentStudent(String student);
	
	public boolean correctStudent(String student);
		
}
