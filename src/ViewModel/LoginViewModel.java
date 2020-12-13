package ViewModel;

import Model.MathModel;
import javafx.collections.ObservableList;

public class LoginViewModel {
	private MathModel model;
	
	public LoginViewModel(MathModel model) {
		this.model = model;
	}
	
	public void setUser(String student) {
		model.setCurrentStudent(student);
	}
	
	public ObservableList<String> getStudents() {
		return model.getstudents();
	}

}
