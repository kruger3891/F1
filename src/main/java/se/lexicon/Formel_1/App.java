package se.lexicon.Formel_1;

import javax.swing.JFrame;

public class App {
	public static void main(String[] args) {

		JFrame win = new JFrame();
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setSize(1100, 600);
		win.add(new Road());
		win.setVisible(true);
		win.setLocationRelativeTo(null);
		win.setTitle("F1");

	}
}
