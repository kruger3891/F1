package se.lexicon.Formel_1;

import java.awt.Color;
import java.awt.Font;
//2 shag
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener, Runnable {

	Timer mainTimer = new Timer(20, this);

//	Image img = new ImageIcon(getClass()
//			.getClassLoader().getResource("res/bg_road.png")).getImage();

	Image img = new ImageIcon("res/bg_road.png").getImage();

	Player p = new Player();

	Thread enemiesFactory = new Thread(this);
	
	Thread audioThread = new Thread(new AudioThread());

	List<Enemy> enemies = new ArrayList<Enemy>();

	
//	zapuskaet potoki
	public Road() {
		mainTimer.start();
		enemiesFactory.start();
		audioThread.start();
		addKeyListener(new MyKeyAdapter());
		setFocusable(true);

	}

	private class MyKeyAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			p.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			p.keyReleassed(e);
		}

	}

//	resuet dorogu i mashiny
	public void paint(Graphics g) {
		g = (Graphics2D) g;
//		 2 sloya dorogi
		g.drawImage(img, p.road1, 0, null);
		g.drawImage(img, p.road2, 0, null);
//		sloy mashiny
		g.drawImage(p.img, p.x, p.y, null);

//		pokazyvaet skorost
		double v = (200 / Player.MAX_SPEED) * p.speed;
		g.setColor(Color.WHITE);
		Font font = new Font("Arial", Font.ITALIC, 20);
		g.setFont(font);
		g.drawString("Speed " + v + " km/ch ", 100, 80);

//		ubiraet vragov esli oni za granicey ekrana
		Iterator<Enemy> i = enemies.iterator();
		while (i.hasNext()) {
			Enemy e = i.next();
			if (e.x >= 2400 || e.x <= -2400) {
				i.remove();
			} else {
				e.move();
				g.drawImage(e.img, e.x, e.y, null);

			}

		}

	}

//	kajdye Timer(20, this) pereresovyvaet kartinku i proveryaet stolknovenie s sopernikom 
	public void actionPerformed(ActionEvent e) {
		p.move();
		repaint();
		testCollisionWithEnemies();
		testWin();
		System.out.println(enemies.size());

	}

//	Proverka vygrali vy
	private void testWin() {
		if (p.s > 25000) {
			JOptionPane.showMessageDialog(null, "You Win");
			System.exit(0);
		}

	}

	// proveryaet stolknovenie
	private void testCollisionWithEnemies() {
		Iterator<Enemy> i = enemies.iterator();
		while (i.hasNext()) {
			Enemy e = i.next();
//			proverka ne пересекается li igrok s vragom (avariya)
			if (p.getRect().intersects(e.getRect())) {
				JOptionPane.showMessageDialog(null, "Game over");
				System.exit(1);
			}
		}
	}

//	delaet vragov
	public void run() {
		while (true) {
			Random rand = new Random();
			try {
				Thread.sleep(rand.nextInt(2000));
				enemies.add(new Enemy(1200, rand.nextInt(600), rand.nextInt(60), this));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
