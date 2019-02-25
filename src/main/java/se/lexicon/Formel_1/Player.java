package se.lexicon.Formel_1;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {

	public static final int MAX_SPEED = 50;
	public static final int MAX_TOP = 110;
	public static final int MAX_BOTTOM = 400;

//	Image img_c = new ImageIcon(getClass()
//	.getClassLoader().getResource("res/player.png")).getImage();
//	Image img_l = new ImageIcon(getClass()
//	.getClassLoader().getResource("res/player_left.png")).getImage();
//	Image img_r = new ImageIcon(getClass()
//	.getClassLoader().getResource("res/player_right.png")).getImage();

	Image img_c = new ImageIcon("res/player.png").getImage();
	Image img_l = new ImageIcon("res/player_left.png").getImage();
	Image img_r = new ImageIcon("res/player_right.png").getImage();

	Image img = img_c;

//	beret razmer mashiny igroka
	public Rectangle getRect() {
		return new Rectangle(x, y, 130, 80);
	}

	int speed = 0;// skorost
	int turboSpeed = 0;// uskorenie
	int s = 0;// polnyy put

	int x = 30;// 1-ya poziciya mashiny igroka
	int y = 100;// 1-ya poziciya mashiny igroka
	int dy = 0;

	int road1 = 0;// sloy dorogi
	int road2 = 1100;

//	Dvijenie i skorost
	public void move() {
		s += speed;
		speed += turboSpeed;
		if (speed <= 0)
			speed = 0;
		if (speed >= MAX_SPEED)
			speed = MAX_SPEED;

//		 dvijenie verh i vniz
		y -= dy;
		if (y <= MAX_TOP)
			y = MAX_TOP;
		if (y >= MAX_BOTTOM)
			y = MAX_BOTTOM;

//		 dvijenie mashiny
		if (road2 - speed <= 0) {
			road1 = 0;
			road2 = 1100;
		} else {
			road1 -= speed;
			road2 -= speed;
		}

	}

//	Esli knopka najata
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT) {
			turboSpeed = 1;
		}
		if (key == KeyEvent.VK_LEFT) {
			turboSpeed = -5;
		}
		if (key == KeyEvent.VK_UP) {
			dy = 10;
			img = img_l;
		}
		if (key == KeyEvent.VK_DOWN) {
			dy = -10;
			img = img_r;
		}

	}

//	Esli knopka otpushena
	public void keyReleassed(KeyEvent e) {

		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
			turboSpeed = 0;

		}

		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			dy = 0;
			img = img_c;
		}

	}

}