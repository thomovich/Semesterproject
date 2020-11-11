package ViewModel;

import Model.MathModel;
import GameViewModel.AdditionGameViewModel;
import GameViewModel.MayorGameViewModel;

public class ViewModelFactory {
	 private FrontPageViewModel frontPageViewModel;
	 private Chapter1ViewModel chapter1viewModel;
	 private Chapter2ViewModel chapter2viewModel;
	 private ChaptersViewModel chaptersviewModel;
	 private MayorGameViewModel MayorGameViewModel;
	 private AdditionGameViewModel AdditionGameViewModel;



	 public ViewModelFactory (MathModel model) {
		 frontPageViewModel = new FrontPageViewModel(model);
		 chaptersviewModel = new ChaptersViewModel(model);
		 chapter1viewModel = new Chapter1ViewModel(model);
		 chapter2viewModel = new Chapter2ViewModel(model);
		 MayorGameViewModel = new MayorGameViewModel(model);

		}
	 
	 public FrontPageViewModel getFrontPageViewModel() {
			
			return frontPageViewModel;
		}
	 public ChaptersViewModel getChaptersViewModel() {
			
			return chaptersviewModel;
		}
	 
	 public Chapter1ViewModel getChapter1ViewModel() {
			
			return chapter1viewModel;
		}
	 public Chapter2ViewModel getChapter2ViewModel() {
			
			return chapter2viewModel;
		}
	 public MayorGameViewModel getMayorGameViewModel() {
		 return MayorGameViewModel;
	 }

	public AdditionGameViewModel getAdditionGameViewModel() {
		return AdditionGameViewModel;
	}
}
