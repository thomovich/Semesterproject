package Model;

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
    private String studentName="Erik";
    private ArrayList<String> students;
    private int triesErikChapter5;

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
    
    public void enterStudent(String input) {
        
        String sql = "INSERT INTO public.\"Students\"(\"Name\") VALUES('"+studentName+"');";
        String quotation = "\"Rom\"";
        System.out.println(quotation);
        System.out.println(sql);
        if(studentPresent(input)) {
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
    }
    
    
    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score,String extraInfo,String game) {
        String sql=null;
        if(studentPresent("Erik")) {
        sql = "INSERT INTO public."+game+"(Tries,Student,Score,Question) VALUES('"+triesErikChapter5+"','"+studentName+"','"+score+"','"+extraInfo+"');";
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

    @Override
    public void setTriesChapter5(String student) {
        if(student=="Erik") {
            triesErikChapter5++;
        }
        
    }
    

    @Override
    public ObservableList<Content> getTable(String student, String chapter) {
        ObservableList<Content> data = FXCollections.observableArrayList();
        if(studentPresent(student)) {
            String sql = "SELECT * FROM public.\""+chapter+"\";";
            try {
                connect();
                Statement statement = connect.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                
                while (rs.next()){
                    data.add(new Content(rs.getInt(1), rs.getString(2),rs.getDouble(3), rs.getString(4)));
                    
                            
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
                    return true;
                }
                
            }
            connect.close();
        }
        catch(SQLException exception) {
            System.out.println("unable to read if the user is in the database");
            exception.printStackTrace();
        }
        
        return false;
    }
    

    @Override
    public void setCurrentStudent(String student) {
        this.studentName=student;
        students.add(student);
        
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
            String sql ="SELECT \"NameId\", AVG(Score) AS Score FROM public.\"Students\" NATURAL JOIN public.\""+chapter+"\" WHERE \"NameId\"='"+student+"' GROUP BY \"NameId\"";
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