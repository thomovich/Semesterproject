package miniTennis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class MathQuizGUI extends JPanel {
	
	private JLabel display;          // this is used to display output to the user 
	private JTextField answerInput;  // the box where the user enters his answer
	private JButton button;          // a "Submit" button that the user clicks to submit answer

	private QuizQuestion question;   // the current math question that the user has to answer

	private int correctFirstTry;     // number of questions answered correctly by the user on the first try
	private int correctSecondTry;    // number of questions answered correctly by the user on the second try
	private int incorrect;           // number of questions not answered correctly after two tries
	
	private final static int STARTING = 0;   // represents the "state" before any questions have been asked
	private final static int FIRST_TRY = 1;  // the state while waiting for the first answer to a question
	private final static int SECOND_TRY = 2; // the state while waiting for the second answer to a question
	
	private int state = STARTING;  // keeps track of the current state; this is one of the values
	                               // STARTING, FIRST_TRY, or SECOND_TRY; the value of this variable 
	                               // is used in the buttonPressed() method to determine the program's
	                               // response when the user clicks the button
	
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Math Quiz");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new MathQuizGUI());
		window.pack();
		window.setLocation(80,40);
		window.setVisible(true);
	}
	

	public MathQuizGUI() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.LIGHT_GRAY));
		display = new JLabel("<html><center><big><b>Welcome to a Math Quiz!</b></big>" 
				+ "<br><br>Click \"begin\" to start.<br>"
				+ "Close the window when you feel like quitting.</center></html>", JLabel.CENTER);
		display.setPreferredSize(new Dimension(500,180));
		display.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		add(display,BorderLayout.CENTER);
		JPanel bottom = new JPanel();
		bottom.add(new JLabel("Enter Answer Here: "));
		answerInput = new JTextField(4);
		answerInput.setEnabled(false);
		bottom.add(answerInput);
		button = new JButton("Begin!");
		button.addActionListener( new ActionListener() { 
			public void actionPerformed(ActionEvent evt) {
				buttonPressed();
			}
		});
		bottom.add(button);
		bottom.setBackground(Color.WHITE);
		add(bottom, BorderLayout.SOUTH);
	}
	
	
	
	private void buttonPressed() {

		if (state == STARTING) {    // Create and display the first question; change state to FIRST_TRY.
			question = new QuizQuestion();
			display.setText("<html><center><i>First Question</i><br><br><big><b><font color=red>"
					+ question.getQuestion() + "</font></b></big></center></html>");
			answerInput.setEnabled(true);
			answerInput.requestFocus();
			button.setText("Submit");
			state = FIRST_TRY;
			return;               
		}
		
		String userInput = answerInput.getText().trim();  // Get user's input from the input box.
		
		if (userInput.length() == 0) { // Nothing was entered in the box; give an appropriate error message.
			errorMessage("Enter your answer in the input box,\nand then click the Submit button.");
			return;
		}
		
		int userAnswer;  // user's answer as an integer value
		try {
			userAnswer = Integer.parseInt(userInput);  // convert input string to integer
		}
		catch (NumberFormatException e) {  // Input was not valid; give an appropriate error message.
			errorMessage("\"" + userInput + "\" is not a legal integer.\nPlease enter your answer as an integer.");
			return;
		}
		
		// At this point, we have the user's answer stored in the int variable userAnswer, and the
		// state is FIRST_TRY or SECOND_TRY.  Check the answer and respond accordingly.
		
		String response;  // This will be the program's feedback to the user about the answer.
		
		if (state == FIRST_TRY) {
			if (userAnswer == question.getAnswer()) {
				response = "Good Job!  That's correct.  Next Question:";
				correctFirstTry++;
				question = new QuizQuestion();  // Generate the next question.
				// The state remains equal to FIRST_TRY for the new question.
			}
			else {   // Keep the same question, but change state to SECOND_TRY.
				response = "Sorry, that's not correct.  Try again:";
				state = SECOND_TRY;
			}
		}
		else {  // state is SECOND_TRY
			if (userAnswer == question.getAnswer()) {
				response = "Correct on the second try!  Next Question:";
				correctSecondTry++;
			}
			else {
				response = "Sorry, the correct answer is " + question.getAnswer() + ".  Next Question:";
				incorrect++;
			}
			question = new QuizQuestion();  // Generate a new question, and change state to FIRST_TRY.
			state = FIRST_TRY;
		}
		
		display.setText("<html><center><i>" + response + "</i><br><br><big><b><font color=red>"
				+ question.getQuestion() + "</font></b></big><br><br>"
				+ correctFirstTry + " questions correct on the first try<br>"
				+ correctSecondTry + " questions correct on the second try<br>"
				+ incorrect + " questions not answered correctly</center></html>");
		
		answerInput.selectAll();      // Highlights the contents of the input box.
		answerInput.requestFocus();   // Moves input focus to input box, so user doesn't have to click it.

	} // end buttonPressed()
	

	/**
	 * A small utility routine that puts up an error box on the screen containing.
	 * @param message the error message that is to be displayed in the error box
	 */
	private void errorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Error in Input", JOptionPane.ERROR_MESSAGE);
	}
	
}