package ViewModel;

import Model.MathModel;
import GameViewModel.AdditionGameViewModel;
import GameViewModel.DuckGameViewModel;
import GameViewModel.MayorGameViewModel;
import GameViewModel.OldladyViewModel;
import GameViewModel.TeacherViewModel;

public class ViewModelFactory {
	 private FrontPageViewModel frontPageViewModel;
	 private Chapter1ViewModel chapter1viewModel;
	 private Chapter2ViewModel chapter2viewModel;
	 private Chapter3ViewModel chapter3viewModel;
	 private MayorGameViewModel MayorGameViewModel;
	 private Chapter4ViewModel chapter4viewModel;
	 private DuckGameViewModel duckgameviewModel;
	 private AdditionGameViewModel AdditionGameViewModel;
	 private OldladyViewModel oldladyviewmodel;
	 private Chapter5ViewModel spaceGameViewModel;
	 private TeacherViewModel teacherViewModel;
	 private LoginViewModel LoginViewModel;
	 private MathModel model;



	 public ViewModelFactory (MathModel model) {
		 frontPageViewModel = new FrontPageViewModel(model);
		 chapter1viewModel = new Chapter1ViewModel(model);
		 chapter2viewModel = new Chapter2ViewModel(model);
		 chapter3viewModel = new Chapter3ViewModel(model);
		 MayorGameViewModel = new MayorGameViewModel(model);
		 chapter4viewModel= new Chapter4ViewModel(model);
		 AdditionGameViewModel = new AdditionGameViewModel(model);
		 duckgameviewModel = new DuckGameViewModel(model);
		 oldladyviewmodel = new OldladyViewModel(model);
		 spaceGameViewModel = new Chapter5ViewModel(model);
		 teacherViewModel = new TeacherViewModel(model);
		 LoginViewModel = new LoginViewModel(model);
		 this.model=model;
		 
		 

		}
	 
	 public MathModel getModel() {
		 return model;
	 }
	 
	 public FrontPageViewModel getFrontPageViewModel() {
			
			return frontPageViewModel;
		}
	 
	 public Chapter1ViewModel getChapter1ViewModel() {
			
			return chapter1viewModel;
		}
	 public Chapter2ViewModel getChapter2ViewModel() {
			
			return chapter2viewModel;
		}
	 public Chapter3ViewModel getChapter3ViewModel() {
			
			return chapter3viewModel;
		}
	 public MayorGameViewModel getMayorGameViewModel() {
		 return MayorGameViewModel;
	 }

	public Chapter4ViewModel getAdditionGameViewModel() {
		return chapter4viewModel;
	}
	
	public DuckGameViewModel getDuckGameViewModel() {
		return duckgameviewModel;
	}
	
	public OldladyViewModel getOldLadyViewModel() {
		return oldladyviewmodel;
	}
	
	public Chapter5ViewModel getChapter5ViewModel() {
		return spaceGameViewModel;
	}
	public TeacherViewModel getTeacherViewModel() {
		return teacherViewModel;
	}
	
	public LoginViewModel getLoginViewModel() {
		return LoginViewModel;
	}
}
