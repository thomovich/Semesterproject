package miniTennis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.glass.ui.Window;

import javax.swing.JOptionPane;

public class tennis extends JPanel {
	Racquet racquet = new Racquet(this);
	Ball ball = new Ball(this);
	int number = 0;
	int speed = 1;

	private int getScore() {
		return speed - 1;
	}

	public tennis() {
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {

			}

			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}

			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}
		});
		setFocusable(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);

		g2d.setColor(Color.RED);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore()), 10, 30);
	}

	public void move() {
		ball.move();
		racquet.move();
	}

	public void gameOver() {

		JFrame window = new JFrame("Math Quiz");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new MathQuizGUI());
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		for(int i= 0;i < 5; i++) {
			
		}
		if (window.isEnabled()) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			int n = JOptionPane.showConfirmDialog(this, "Would you like to play again?", "Game Over",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				ball.yspd = -1;
			} else {
				System.exit(ABORT);
			}
		}
	}

}