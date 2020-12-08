package Space;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class SpaceInvaders extends JFrame  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SpaceInvaders(String speed) {

        initUI(speed);
    }

    private void initUI(String speed) {

        add(new Board(speed));

        setTitle("Space Invaders");
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

    }
}
