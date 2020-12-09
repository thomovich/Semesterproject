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
    	System.out.println(model);
    	ObservableList<Content> data= model.getTable(studentName, chapterId);
    	System.out.println(data.get(0).getTries()+data.get(0).getStudent()+data.get(0).getScore()+data.get(0).getQuestion());
     	contentTable.setItems(data);
    }
    
    
    public void init(ViewHandler viewHandler, TeacherViewModel teacher, Region root) {
    	model=viewHandler.getModel();
    	this.viewhandler = viewHandler;
    	this.root = root;
    	this.teacher = teacher;
    	
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

	public void reset() {
		
	}

	public Region getRoot() {
		return root;
	}
}
