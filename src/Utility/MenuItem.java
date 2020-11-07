package Utility;
import View.FrontPage;
import javafx.beans.binding.Bindings;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class MenuItem extends Pane {
    private Text text;

    private Effect shadow = new DropShadow(5, Color.BLACK);
    private Effect blur = new BoxBlur(1, 1, 3);

    public MenuItem(String name) {
        Ellipse bg = new Ellipse(80, 20, 100, 10
        );
        bg.setStroke(Color.color(1, 1, 1, 0.75));
        bg.setEffect(new Glow());

        bg.fillProperty().bind(
                Bindings.when(pressedProperty())
                        .then(Color.color(0, 1, 0, 0.75))
                        .otherwise(Color.color(0.5, 0, 0, 0.25))
        );

        text = new Text(name);
        text.setTranslateX(45);
        text.setTranslateY(25);
        text.setFont(Font.loadFont(FrontPage.class.getResource("../Images/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 14));
        text.setFill(Color.WHITE);

        text.effectProperty().bind(
                Bindings.when(hoverProperty())
                        .then(shadow)
                        .otherwise(blur)
        );

        getChildren().addAll(bg, text);
    }

    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }
}