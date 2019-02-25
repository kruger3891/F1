package se.lexicon.Formel_1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioThread implements Runnable {

	public void run() {
		
		try {
			
//			Player p = new Player(new FileInputStream("res/cake.mp3"));
//			p.play();
			
			Player p = new Player(new FileInputStream("res/myFavouriteGame.mp3"));
			p.play();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}