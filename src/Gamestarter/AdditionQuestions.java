package Gamestarter;

public class AdditionQuestions {

	int firstNumber;
	int secondNumber;
	int answer;

	public AdditionQuestions() {

	}

	public String createproblem() {
		String question;
		firstNumber = (int) (Math.random() + 15) + 1;
		secondNumber = (int) (Math.random() + 15) + 1;

		answer = firstNumber * secondNumber;
		question = "What is: " + answer + " + " + secondNumber;
		return question;
	}

	public String getAnswer() {
		String questionanswer;
		questionanswer = "" + (answer + secondNumber);
		System.out.println(questionanswer);

		return questionanswer;

	}

	public boolean CheckAnswer(Object anObject) {
		if (firstNumber+secondNumber==answer) {
			System.out.println("Your answer is correct");
			return true;
			
		}
		return false;

	}
}
