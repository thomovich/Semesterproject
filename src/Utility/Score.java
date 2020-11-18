package Utility;


import com.sun.java.swing.plaf.windows.resources.windows;

import Model.MathModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Score extends Stage{
private Text Score = new Text();

public Score(int score, String game) {
	Button btnOK = new Button("OK");
	Score.setText("Your score is: "+score);
	btnOK.setOnAction(event->{
		Score.setText("score submitted");
	});
	VBox vbox = new VBox(10,Score,btnOK);
	vbox.setAlignment(Pos.CENTER);
	Scene scene = new Scene(vbox);
	setScene(scene);
	show();
}


}
