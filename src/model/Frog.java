package model;

public class Frog {

	
	private int oldPositionX;
	private int oldPositionY;
	private int positionX;
	private int positionY; 
	private String spielername;
	private Image frogPic;
	
	public Frog (int positionY, int positionX, String spielername, Image frogPic) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.frogPic = frogPic;
		this.spielername = spielername;
	}
	
	public int getPositionX() {
		return this.positionX;
	}
	
	public int getPositionY() {
		return this.positionY;
	}
	
	public int getOldPositionX() {
		return this.oldPositionX;
	}
	
	public int getOldPositionY() {
		return this.oldPositionY;
	}


	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	public void setOldPositionX(int oldPositionX) {
		this.oldPositionX = oldPositionX;
	}
	
	public void setOldPositionY(int oldPositionY) {
		this.oldPositionY = oldPositionY;
	}
	
	public void getImage() {
		return this.frogPic;
	}
	
	public void setSpielername(String Spielername) {
		this.spielername = Spielername;
	}
	
	public String getSpielername() {
		return this.spielername;
	}
	
	
}
