package View;

import GameViewModel.TeacherViewModel;
import Model.Content;
import Model.MathModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
	private ObservableList<Content> data ;

	@FXML 
	private TextField student;
	@FXML 
	private TextField chapter;
	@FXML 
	private TableView<Content> contentTable;


    @FXML
    public void submit() {	
    	String studentName = student.getText();
    	String chapterId = chapter.getText();
    	System.out.println(chapterId);
    	if(chapterId.equals("")) {
    		studentsSetup();
    		 data= model.getTableStudent(studentName);
         	contentTable.setItems(data);
    	}
    	else {
    		chapterSetup(chapterId);
    		 data= model.getTable(studentName, chapterId);
         	contentTable.setItems(data);
    	}
    }
    
    
    public void init(ViewHandler viewHandler, TeacherViewModel teacher, Region root) {
    	model=viewHandler.getModel();
    	this.viewhandler = viewHandler;
    	this.root = root;
    	this.teacher = teacher;
    }
    
    public void chapterSetup(String chapter) {

    	contentTable.getColumns().clear();
    		
    	TableColumn<Content, Integer> tries= new TableColumn<Content, Integer>("Tries"); 	//creates a new table column with the name temperature in
		tries.setCellValueFactory(new PropertyValueFactory<>("Tries")); 			//sets the cell values, so that all rows will be displayed
		contentTable.getColumns().add(tries); 	
		
		TableColumn<Content, String> student = new TableColumn<Content, String>("Student"); 	//creates a new table column with the name temperature in
		student.setCellValueFactory(new PropertyValueFactory<>("Student")); 			//sets the cell values, so that all rows will be displayed
		contentTable.getColumns().add(student); 
			
		
		TableColumn<Content, Integer> score= new TableColumn<Content, Integer>("Score"); 	//creates a new table column with the name temperature in
		score.setCellValueFactory(new PropertyValueFactory<>("Score")); 			//sets the cell values, so that all rows will be displayed
		contentTable.getColumns().add(score); 	
		
		
		TableColumn<Content, String> question= new TableColumn<Content, String>("Question"); 	//creates a new table column with the name temperature in
		question.setCellValueFactory(new PropertyValueFactory<>("Question")); 			//sets the cell values, so that all rows will be displayed
		contentTable.getColumns().add(question); 
    	
    }
    
    public void studentsSetup() {
    	
    	
    	contentTable.getColumns().clear();
    	
    	TableColumn<Content, String> name= new TableColumn<Content, String>("Name"); 	//creates a new table column with the name temperature in
		name.setCellValueFactory(new PropertyValueFactory<>("Name")); 			//sets the cell values, so that all rows will be displayed
		contentTable.getColumns().add(name); 	
			
		
		TableColumn<Content, Integer> totalScore= new TableColumn<Content, Integer>("total Score"); 	//creates a new table column with the name temperature in
		totalScore.setCellValueFactory(new PropertyValueFactory<>("Total Score")); 			//sets the cell values, so that all rows will be displayed
		contentTable.getColumns().add(totalScore); 	
		
		TableColumn<Content, String> avgScore = new TableColumn<Content, String>("avg Score"); 	//creates a new table column with the name temperature in
		avgScore.setCellValueFactory(new PropertyValueFactory<>("Avg Score")); 			//sets the cell values, so that all rows will be displayed
		contentTable.getColumns().add(avgScore); 
		
    }

	public void reset() {
		
	}

	public Region getRoot() {
		return root;
	}
}
