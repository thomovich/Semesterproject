    package Model;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ModelManager implements MathModel {

    private int score;
    private int Value;
    private Connection connect;
    private String studentName;
    private ArrayList<String> students;
    private int tries;

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
        students= new ArrayList<String> ();
        Driver driver = new org.postgresql.Driver(); 
                try {
                    DriverManager.registerDriver(driver);
                    
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
    }
    
    public ObservableList<String> getstudents() {
    	
    	 ObservableList<String> data = FXCollections.observableArrayList();
         
    	 String sql = "SELECT \"NameId\" FROM public.\"Students\"";
             try {
                 connect();
                 Statement statement = connect.createStatement();
                 ResultSet rs = statement.executeQuery(sql);
                 
                 while (rs.next()){
                	
                     data.add(rs.getString(1));
                             
                 }
                 statement.close();
             }
             catch (SQLException e) {

                 System.out.println("Error trying to getItems");

                 e.printStackTrace();
             }
             
         return data;
    }
    
    public int countStudents() {
    	
       	String sql = "SELECT COUNT(\"NameId\") FROM public.\"Students\"";
    	int students=0;
        try {
            connect();
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()){
               students= rs.getInt(1);         
            }
            statement.close();
        }
        catch (SQLException e) {

            System.out.println("Error trying to getItems");

            e.printStackTrace();
        }
        return students;
    	
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
    
    public boolean enterStudent(String input) {
        
        String sql = "INSERT INTO public.\"Students\"(\"NameId\") VALUES('"+input+"');";
        boolean check=false;
        
       
        if(!studentPresent(input)) {
        	check=true;
        try {
            connect();
            Statement statement = connect.createStatement();
            statement.executeQuery(sql);
            System.out.println("success in adding user to database");
            connect.close();
            
        }
        catch(SQLException e) {
            System.out.println("user is not in the database");
            e.printStackTrace();
            
        }
        
    
    }else {
        check=false;
    }
        return check;
    }
    
    
    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score,String extraInfo,String chapter) {
        String sql=null;
        if(studentPresent(studentName)) {
        	int tries=setTries(studentName,chapter);
        	if(chapter.equals("Chapter1")) {
        		 sql = "INSERT INTO public.\""+chapter+"\"(Tries,StudentId,Score,Game) VALUES('"+tries+"','"+studentName+"','"+score+"','"+extraInfo+"');";
        	}
        	else if(!(chapter.equals("Chapter1"))&& !(chapter.equals("Chapter5"))) {
        		sql = "INSERT INTO public.\""+chapter+"\"(Tries,StudentId,Score) VALUES('"+tries+"','"+studentName+"','"+score+"');";
        	}
        	else {
        		System.out.println("printing chapter5");
        		sql = "INSERT INTO public.\""+chapter+"\"(Tries,StudentId,Score,Question) VALUES('"+tries+"','"+studentName+"','"+score+"','"+extraInfo+"');";
        	}
        
        }
        try {
            connect();
            Statement statement = connect.createStatement();
            statement.executeUpdate(sql);
            System.out.println("success in adding user to database");
            connect.close();
        }
        catch(SQLException e) {
            System.out.println("Error trying to add user");
            e.printStackTrace();
        }
    }

    @Override
    public int getValue() {
        return Value;

    }

    
    public int setTries(String student,String chapter) {
        
        	int tries = getTries(student,chapter);
        	System.out.println(tries);
        	tries++;
        	System.out.println(tries);
            return tries;
        
        
    }
    
    public int getTries(String student,String chapter) {
    	//SELECT Tries FROM public."Chapter5" WHERE "studentId"='Erik' GROUP BY Tries
    	String sql = "SELECT Tries FROM public.\""+chapter+"\" WHERE studentId='"+student+"' GROUP BY Tries";
    	System.out.println(sql);
    	int tries=0;
        try {
            connect();
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()){
                if(rs.isLast()){
                	 tries=rs.getInt(1);
                }           
            }
            statement.close();
        }
        catch (SQLException e) {

            System.out.println("Error trying to getItems");

            e.printStackTrace();
        }
        return tries;
    }
    

    @Override
    public ObservableList<Content> getTable(String student, String chapter) {
        ObservableList<Content> data = FXCollections.observableArrayList();
        if(studentPresent(student)) {
            String sql = "SELECT * FROM public.\""+chapter+"\" WHERE StudentId='"+student+"'";
            System.out.println(sql);
            try {
                connect();
                Statement statement = connect.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                if(chapter.equals("Chapter5") || chapter.equals("Chapter1")) {
                while (rs.next()){
                    data.add(new Content(rs.getInt(1), rs.getString(2),rs.getDouble(3), rs.getString(4)));          
                }
                }
                else {
                	while (rs.next()){
                	data.add(new Content(rs.getInt(1), rs.getString(2),rs.getDouble(3))); 
                	}
                }
                statement.close();
            }
            catch (SQLException e) {

                System.out.println("Error trying to getItems");

                e.printStackTrace();
            }
        }
            
        return data;
    }
    
    public boolean studentPresent(String student){
    	
    	boolean check=false;
        String sql ="SELECT \"NameId\" FROM public.\"Students\" WHERE \"NameId\"='"+student+"'";
        try {
        	
            connect();
            Statement statement = connect.createStatement();
            ResultSet rest = statement.executeQuery(sql);
            
            int i=0;
            while(rest.next()) {
                i++;
                if(rest.getString(i).equals(student)) {
                    System.out.println("username already existing");
                    connect.close();
                    check = true;
                }
                else {
                	 check = false;
                }
                
            }
            connect.close();
        }
        catch(SQLException exception) {
            System.out.println("unable to read if the user is in the database");
            exception.printStackTrace();
        }
        
        return check;
    }
    

    @Override
    public void setCurrentStudent(String student) {
        this.studentName=student;
        
        
    }

    @Override
    public boolean correctStudent(String student) {
        if(students.contains(student)) {
            return true;
        }
        return false;
    }

    @Override
    public ObservableList<Content> getAverageScoreGame(String student,String chapter) {
        ObservableList<Content> data = FXCollections.observableArrayList();
        if(studentPresent(student)) {
        	//SELECT StudentId AS Name, AVG(Score) AS Average FROM public."Chapter5" WHERE StudentId='peter' GROUP BY StudentId
        	
            String sql ="SELECT StudentId AS Name, AVG(Score) AS Average FROM public.\""+chapter+"\" WHERE StudentId='"+student+"' GROUP BY StudentId";
            System.out.println(sql);
            try {
                connect();
                Statement statement = connect.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                
                while (rs.next()){
                    System.out.println(rs.getString(1)+":"+rs.getDouble(2));
                    data.add(new Content(rs.getString(1), rs.getDouble(2)));                            
                }
                statement.close();
            }
            catch (SQLException e) {

                System.out.println("Error trying to getItems");

                e.printStackTrace();
            }
        }
            
        return data;
    }

}
    
