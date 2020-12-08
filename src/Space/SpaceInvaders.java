package Space;

import java.awt.EventQueue;
import javax.swing.JFrame;

import Model.MathModel;

public class SpaceInvaders extends JFrame  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SpaceInvaders(String speed,MathModel model) {

        initUI(speed,model);
    }

    private void initUI(String speed,MathModel model) {

        add(new Board(speed,model));

        setTitle("Space Invaders");
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

    }
}
