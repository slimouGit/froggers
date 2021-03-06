package views;

import application.Configuration;
import application.Observer;
import application.SubscriberDaten;
import controller.SceneController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


/** 
 * StartSzene / StartSzene des Programms.
 * 
 * @author Die UMeLs
 *
 */
public class StartScene {

	private Scene scene;
	private SceneController sceneController;
	
	// Hauptpanel
	private BorderPane rootStart = new  BorderPane();
	private GridPane contentStart = new GridPane();
	private TextField spielerName = new TextField("Spielername");
		
	/**
	 * Konstruktor.
	 *
	 * @param sceneController / für die Szenen zustaendiger Controller
	 */
	public StartScene(SceneController sceneController) {
		this.sceneController = sceneController;
		this.contentStart.getStyleClass().add("content");
		scene = new Scene(rootStart,Configuration.xFields * 50,Configuration.yFields * 50);
		//Szene Formatierungs CSS  zuweisen
		scene.getStylesheets().add(getClass().getResource("/views/startScene.css").toExternalForm());
		scene.setUserData("StartScene");
		this.buildScene();
	}

	/**
	 * Funktion zum erstellen der Szene mit ihren Elementen.
	 * 
	 */
	private void buildScene() {

		//Äußere VertikalBox zur Aufnahme aller weiteren Elemente
		VBox verboAeussereBox = new VBox();
		
		verboAeussereBox.getStyleClass().add("verboAeussereBox");		
		spielerName.getStyleClass().add("spielerName");		
		Button starteSpiel = new Button();
		starteSpiel.getStyleClass().add("starteSpiel");
		starteSpiel.setPrefHeight(78);
		starteSpiel.setPrefWidth(260);
		starteSpiel.setOnAction(actionEvent -> {
				this.submitStart();
				this.sceneController.startGame();
			});
		verboAeussereBox.getChildren().add(spielerName);
		verboAeussereBox.getChildren().add(starteSpiel);	
		this.contentStart.getChildren().add(verboAeussereBox);
		this.rootStart.setTop(this.buildMenu());
		this.rootStart.setBottom(this.contentStart);
	}

	/** 
	 * Menueleiste bauen.
	 *
	 * @return HBox / HorizontalBox mit Menue 
	 */
	private VBox buildMenu() {
		
		MenuBar menuBar = new MenuBar();
		VBox menuBox = new VBox();
		menuBox.setPrefHeight(20);		
		Menu froggerMenu = new Menu("Frogger");
		MenuItem neuMenuItem = new MenuItem("Neues Spiel");
		MenuItem exitMenuItem = new MenuItem("Exit");    
		exitMenuItem.setOnAction(actionEvent -> Platform.exit());    
		froggerMenu.getItems().addAll(neuMenuItem,new SeparatorMenuItem(), exitMenuItem);    
		Menu infoMenu = new Menu("Info");
		MenuItem highMenuItem = new MenuItem("Highscore"); 
		highMenuItem.setOnAction(actionEvent -> this.sceneController.showHighscore());		
		MenuItem overMenuItem = new MenuItem("Über..");		
		overMenuItem.setOnAction(actionEvent -> this.sceneController.showOver());	    
		infoMenu.getItems().addAll(highMenuItem, overMenuItem);    
		
		MenuItem music = new MenuItem("Musik an/aus");
		SubscriberDaten subDat = new SubscriberDaten();
		subDat.typ = "toggle";
		music.setOnAction( actionEvent -> Observer.trigger("sound", subDat) );
		infoMenu.getItems().add(music);

		menuBar.getMenus().addAll(froggerMenu, infoMenu);		
		menuBox.getChildren().add(menuBar);		
		return menuBox;
	}
	
	/** 
	 * Hilfsfunktion zum ausloesen eines StartTriggers an den Observer.
	 *
	 */
	private void submitStart() {
		SubscriberDaten data = new SubscriberDaten();
		if(this.spielerName.getText().equals("")) {
			this.spielerName.setText("Spielername");
		}
		data.name = this.spielerName.getText();
		Observer.trigger("player", data);
	}

	/**
	 * Funktion zur Rueckgabe der Szene.
	 * @return komplette Szene mit allen Elementen
	 */
	public Scene getScene() {
		return this.scene;
	}

	
}
