package controller;

import application.Observer;
import application.SubscriberDaten;
import application.SubscriberInterface;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundController implements SubscriberInterface {
	
	Media music;
	MediaPlayer musicPlayer;
	Boolean musicOn;
	
	public SoundController() {
		this.music = new Media( 
			ClassLoader.getSystemResource("views/music.mp3").toString()
		);		
		this.musicPlayer = new MediaPlayer(this.music);
		this.musicOn = true;
		
		playMusic();
		Observer.add("sound", this);
	}
	
	public void playMusic() {
		this.musicPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				musicPlayer.seek(Duration.ZERO);
			}
		});
		musicPlayer.play();		
	}
	
	public void calling(String trigger, SubscriberDaten data) {
		if(trigger == "sound") {
			switch (data.typ) {
				case "musicOff": { 
					this.musicOn = false;
					this.musicPlayer.stop();
					break;
				}
				case "musicOn": { 
					this.musicOn = true;
					this.musicPlayer.play();
					break;
				}
				case "toggle": { 
					if(this.musicOn) {
						this.musicOn = false;
						this.musicPlayer.stop();
					} else {
						this.musicOn = true;
						this.musicPlayer.play();
					}
					break;
				}
			}
		}
	}
}
