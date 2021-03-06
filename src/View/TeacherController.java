    package View;

import GameViewModel.TeacherViewModel;
import Model.Content;
import Model.MathModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class TeacherController {
    
    private ViewHandler viewhandler;
    private Region root;
    private TeacherViewModel teacher;
    private MathModel model;
    private ObservableList<Content> data2 = FXCollections.observableArrayList();
    private ObservableList<Content> data;

    @FXML 
    private TextField student;
    @FXML 
    private TextField chapter;
    @FXML 
    private CheckBox averageOption;
    @FXML 
    private TableView<Content> contentTable;
    @FXML
    private TextField studentAdd;
    @FXML
    private Label noStudent;


    @FXML
    public void backToMenu() {
    	viewhandler.startFrontpage();
    }
    @FXML
    public void submit() {    
        String studentName = student.getText();
        String chapterId = chapter.getText();
        boolean average = averageOption.isSelected();
        System.out.println(chapterId);
        
        if(average) {
            studentsSetup();
             data = model.getAverageScoreGame(studentName,chapterId);
            contentTable.setItems(data);
        }
        
        else {
        	
        	if(chapterId.equals("Chapter5")) {
            chapterSetup5();
        	}
        	
        	else if(chapterId.equals("Chapter1")) {
        		chapterSetup1();
        	}
        	else if(chapterId.equals("Chapter2") ||chapterId.equals("Chapter3")||chapterId.equals("Chapter4")) {
        		chapterSetupRest();
        		
        	}
             data= model.getTable(studentName, chapterId);
             contentTable.setItems(data);
        }
        
    }
    @FXML 
    public void submitStudent() {
    	
    	
    		String student = studentAdd.getText();
    		noStudent.setText("");
    		boolean check=model.enterStudent(student);
    		if(check) {
    			noStudent.setText("the student ID has been added");
    		}
    		else {
    			noStudent.setText(("the student ID is present and therefore not added"));	
    	}	
    }
    
 
    
    
    public void init(ViewHandler viewHandler, TeacherViewModel teacher, Region root) {
        model=viewHandler.getModel();
        this.viewhandler = viewHandler;
        this.root = root;
        this.teacher = teacher;
    }
    
    public void chapterSetup5() {

        contentTable.getColumns().clear();
            
        TableColumn<Content, Integer> tries= new TableColumn<Content, Integer>("Tries");     //creates a new table column with the name temperature in
        tries.setCellValueFactory(new PropertyValueFactory<>("Tries"));             //sets the cell values, so that all rows will be displayed
        contentTable.getColumns().add(tries);     
        
        TableColumn<Content, String> student = new TableColumn<Content, String>("Student");     //creates a new table column with the name temperature in
        student.setCellValueFactory(new PropertyValueFactory<>("Student"));             //sets the cell values, so that all rows will be displayed
        contentTable.getColumns().add(student); 
            
        
        TableColumn<Content, Integer> score= new TableColumn<Content, Integer>("Score");     //creates a new table column with the name temperature in
        score.setCellValueFactory(new PropertyValueFactory<>("Score"));             //sets the cell values, so that all rows will be displayed
        contentTable.getColumns().add(score);     
        
        
        TableColumn<Content, String> question= new TableColumn<Content, String>("Question");     //creates a new table column with the name temperature in
        question.setCellValueFactory(new PropertyValueFactory<>("Extra"));             //sets the cell values, so that all rows will be displayed
        contentTable.getColumns().add(question); 
        
    }
    
    public void chapterSetup1() {

        contentTable.getColumns().clear();
            
        TableColumn<Content, Integer> tries= new TableColumn<Content, Integer>("Tries");     //creates a new table column with the name temperature in
        tries.setCellValueFactory(new PropertyValueFactory<>("Tries"));             //sets the cell values, so that all rows will be displayed
        contentTable.getColumns().add(tries);     
        
        TableColumn<Content, String> student = new TableColumn<Content, String>("Student");     //creates a new table column with the name temperature in
        student.setCellValueFactory(new PropertyValueFactory<>("Student"));             //sets the cell values, so that all rows will be displayed
        contentTable.getColumns().add(student); 
            
        
        TableColumn<Content, Integer> score= new TableColumn<Content, Integer>("Score");     //creates a new table column with the name temperature in
        score.setCellValueFactory(new PropertyValueFactory<>("Score"));             //sets the cell values, so that all rows will be displayed
        contentTable.getColumns().add(score);     
        
        
        TableColumn<Content, String> game= new TableColumn<Content, String>("Game");     //creates a new table column with the name temperature in
        game.setCellValueFactory(new PropertyValueFactory<>("Extra"));             //sets the cell values, so that all rows will be displayed
        contentTable.getColumns().add(game); 
        
    }
    
   
		public void chapterSetupRest() {
			 contentTable.getColumns().clear();
	            
		        TableColumn<Content, Integer> tries= new TableColumn<Content, Integer>("Tries");     //creates a new table column with the name temperature in
		        tries.setCellValueFactory(new PropertyValueFactory<>("Tries"));             //sets the cell values, so that all rows will be displayed
		        contentTable.getColumns().add(tries);     
		        
		        TableColumn<Content, String> student = new TableColumn<Content, String>("Student");     //creates a new table column with the name temperature in
		        student.setCellValueFactory(new PropertyValueFactory<>("Student"));             //sets the cell values, so that all rows will be displayed
		        contentTable.getColumns().add(student); 
		            
		        
		        TableColumn<Content, Integer> score= new TableColumn<Content, Integer>("Score");     //creates a new table column with the name temperature in
		        score.setCellValueFactory(new PropertyValueFactory<>("Score"));             //sets the cell values, so that all rows will be displayed
		        contentTable.getColumns().add(score); 
		}
	
    
    public void studentsSetup() {
        
        contentTable.getColumns().clear();
        
        TableColumn<Content, String> name= new TableColumn<Content, String>("Name");     //creates a new table column with the name temperature in
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));             //sets the cell values, so that all rows will be displayed
        contentTable.getColumns().add(name);     
        
        TableColumn<Content, Integer> avgScore = new TableColumn<Content, Integer>("Average");     //creates a new table column with the name temperature in
        avgScore.setCellValueFactory(new PropertyValueFactory<>("Average"));             //sets the cell values, so that all rows will be displayed
        contentTable.getColumns().add(avgScore); 
        
    }

    public void reset() {
        
    }

    public Region getRoot() {
        return root;
    }
}
    
