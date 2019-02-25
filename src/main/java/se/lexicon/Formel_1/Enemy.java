package se.lexicon.Formel_1;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {

	public static final int MAX_TOP = 110;
	public static final int MAX_BOTTOM = 400;

	int x;
	int y;
	int v;

//	Image img = new ImageIcon(getClass()
//	.getClassLoader().getResource("res/enemy.png")).getImage();

	Image img = new ImageIcon("res/enemy.png").getImage();

	Road road;

	public Rectangle getRect() {
		return new Rectangle(x, y, 150, 80);
	}

	public Enemy(int x, int y, int v, Road road) {
		super();
		this.x = x;
		this.y = y;
		this.v = v;
		this.road = road;
	}

	public void move() {
		x = x - road.p.speed + v;

		if (y <= MAX_TOP)
			y = MAX_TOP;
		if (y >= MAX_BOTTOM)
			y = MAX_BOTTOM;
	}

}
