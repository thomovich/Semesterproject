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
	@FXML private TextField student;
	@FXML private TextField chapter;
	@FXML private TableView<Content> contentTable;
	private ViewHandler viewhandler;
	private Region root;
	private TeacherViewModel teacher;
	private MathModel model;
	private ObservableList<Content> data2 = FXCollections.observableArrayList();





    @FXML
    void submit() {
    	/*TableColumn<Content, String> tries= new TableColumn<Content, String>("Tries"); 	//creates a new table column with the name temperature in
		tries.setCellValueFactory(new PropertyValueFactory<>("Tries")); 			//sets the cell values, so that all rows will be displayed
		contentTable.getColumns().add(tries); 	
		
		TableColumn<Content, String> student = new TableColumn<Content, String>("Student"); 	//creates a new table column with the name temperature in
		student.setCellValueFactory(new PropertyValueFactory<>("Student")); 			//sets the cell values, so that all rows will be displayed
		contentTable.getColumns().add(student); 	
		
		TableColumn<Content, String> score= new TableColumn<Content, String>("Score"); 	//creates a new table column with the name temperature in
		student.setCellValueFactory(new PropertyValueFactory<>("Score")); 			//sets the cell values, so that all rows will be displayed
		contentTable.getColumns().add(score); 	
		
		TableColumn<Content, String> question= new TableColumn<Content, String>("Question"); 	//creates a new table column with the name temperature in
		student.setCellValueFactory(new PropertyValueFactory<>("Question")); 			//sets the cell values, so that all rows will be displayed
		contentTable.getColumns().add(question); 	
    	
    	//contentTable = model.getScore();*/
    }
    
    
    public void init(ViewHandler viewHandler, TeacherViewModel teacher, Region root) {
    	model=viewHandler.getModel();
    	this.viewhandler = viewHandler;
    	this.root = root;
    	this.teacher = teacher;
    }

	public void reset() {
		
	}

	public Region getRoot() {
		return root;
	}
}
