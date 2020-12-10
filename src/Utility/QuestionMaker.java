package Utility;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class QuestionMaker {
	int firstNumber;
	int secondNumber;
	int answer;

	public QuestionMaker() {

	}

	public String createQuestion() {
		String question;
		firstNumber = (int) (Math.random() * 15) + 1;
		secondNumber = (int) (Math.random() * 15) + 1;

		answer = firstNumber * secondNumber;
		question = "What is: " + answer + " / " + secondNumber;
		return question;
	}

	public String getAnswer() {
		String questionanswer;
		questionanswer = ""+ (answer / secondNumber);
		System.out.println(questionanswer);

		return questionanswer;

	}

}
