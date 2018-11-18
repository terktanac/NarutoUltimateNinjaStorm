package main;

import javafx.application.Application;
<<<<<<< HEAD
import javafx.scene.Scene;
import javafx.stage.Stage;
=======
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import main.MainMenuScreen.ListMenu;
>>>>>>> refs/remotes/origin/master

public class Main extends Application {
	private Stage stage ;
	IntroScreen intro = new IntroScreen(this);
	MainmenuScreen mainmenu = new MainmenuScreen(this);
	LoadScreen loadscreen = new LoadScreen(this);
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;

<<<<<<< HEAD
		stage.setTitle("Naruto Ultimate Ninja by C&T");
		stage.setScene(intro);
		stage.setResizable(false);
		stage.show();

		primaryStage.setTitle("Naruto Ultimate Ninja by C&T");
=======
		IntroScreen intro = new IntroScreen();
		MainMenuScreen mainmenu = new MainMenuScreen();
		LoadScreen loadscreen = new LoadScreen();

		primaryStage.setTitle("Naruto Ultimate Ninja by C&T");
		primaryStage.setScene(intro);
		primaryStage.setResizable(false);
		primaryStage.show();

		// Change Scene
		intro.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				primaryStage.setScene(mainmenu);
				System.out.println("PRESSED");
				intro.player.stop();
				mainmenu.state = 0;
			}
		});
		//Don't like this----it's ugly want to change---
		mainmenu.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				MediaPlayer player = new MediaPlayer(new Media(ClassLoader.getSystemResource("lighter.wav").toString()));
				player.play();
				KeyCode key = event.getCode();
				System.out.println("Pressed " + key.toString());
				if (key == KeyCode.SPACE || key == KeyCode.ENTER) {
					if(mainmenu.Oldchoice==1) {primaryStage.setScene(loadscreen);}
					else if(mainmenu.Oldchoice==3){System.exit(1);}
				}
				else if (key == KeyCode.UP || key == KeyCode.W || key == KeyCode.KP_UP) {
						if (mainmenu.Oldchoice == 0) {mainmenu.NewChoice = 3;} 
						else {mainmenu.NewChoice = mainmenu.Oldchoice - 1;}
				} 
				else if (key == KeyCode.DOWN || key == KeyCode.S || key == KeyCode.KP_DOWN) {
						if (mainmenu.Oldchoice == mainmenu.MenuBox.getChildren().size()-1) {mainmenu.NewChoice = 0;} 
						else {mainmenu.NewChoice = mainmenu.Oldchoice + 1;}
				}
				((ListMenu) mainmenu.MenuBox.getChildren().get(mainmenu.Oldchoice)).setActive(false);
				((ListMenu) mainmenu.MenuBox.getChildren().get(mainmenu.NewChoice)).setActive(true);
				mainmenu.Oldchoice = mainmenu.NewChoice;
				}
		});
		primaryStage.setTitle("Naruto Ultimate Ninja Java Edition by C&T");
>>>>>>> refs/remotes/origin/master
		primaryStage.setScene(intro);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	public void ChangeScene(Scene nextScene) {
		stage.setScene(nextScene);
	}

	public static void main(String[] args) {
		launch(args);

	}

}