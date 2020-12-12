package ViewModel;

import Model.MathModel;

public class LoginViewModel {
	private MathModel model;
	
	public LoginViewModel(MathModel model) {
		this.model = model;
	}
	
	public void setUser(String student) {
		model.setCurrentStudent(student);
	}

}
