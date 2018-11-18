package main;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainmenuScreen extends Scene {
	static Pane root = new Pane();
	int state; // 0=menu 1=play 2=pause 3=load
	private Font narutoFont = Font.loadFont(ClassLoader.getSystemResource("fonts/njnaruto.ttf").toExternalForm(), 50);
	VBox MenuBox = new VBox(5);
	int Oldchoice = 0 ;
	int NewChoice = 0 ;
	public MainmenuScreen(Main main) {
		super(root);
		root.setPrefSize(1280, 720);
		root.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
		
		//-----------<Menu Box>---------------------------------------------------------------
		MenuBox.setTranslateX(450);
		MenuBox.setTranslateY(250);
		//----------<Menu List>---------------------------------------------------------------
		ListMenu vsComp = new ListMenu("VS Comp."); 
		ListMenu vsHuman = new ListMenu("VS Human");
		ListMenu option = new ListMenu("Option");
		ListMenu exit = new ListMenu("Exit");
		//----------<\Menu List>---------------------------------------------------------------
		MenuBox.getChildren().addAll(vsComp,vsHuman,option,exit);
		((ListMenu) MenuBox.getChildren().get(Oldchoice)).setActive(true);
		MenuBox.setAlignment(Pos.CENTER);
		//-----------<\Menu Box>---------------------------------------------------------------
		
		root.getChildren().addAll(MenuBox);
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				System.out.println("Mainmenu:Pressed " + key.toString());
				if (key == KeyCode.SPACE || key == KeyCode.ENTER) {
					if(Oldchoice==1) {main.ChangeScene(main.loadscreen);}
					else if(Oldchoice==0) {main.ChangeScene(main.loadscreen);}
					else if(Oldchoice==MenuBox.getChildren().size()-1) {}
				}
				else if (key == KeyCode.UP || key == KeyCode.W || key == KeyCode.KP_UP) {
						if (Oldchoice == 0) {NewChoice = 3;} 
						else {NewChoice = Oldchoice - 1;}
				} 
				else if (key == KeyCode.DOWN || key == KeyCode.S || key == KeyCode.KP_DOWN) {
						if (Oldchoice == MenuBox.getChildren().size()-1) {NewChoice = 0;} 
						else {NewChoice = Oldchoice + 1;}
				}
				((ListMenu) MenuBox.getChildren().get(Oldchoice)).setActive(false);
				((ListMenu) MenuBox.getChildren().get(NewChoice)).setActive(true);
				Oldchoice = NewChoice;
				}
		});
	
	}
	public void ChooseMenu() {
		root.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				System.out.println("Pressed1 "+key.toString());
				if(key==KeyCode.UP || key==KeyCode.W) {
					if(Oldchoice == 0) {NewChoice = 3;}
					else {NewChoice=Oldchoice-1;}
					((ListMenu)MenuBox.getChildren().get(Oldchoice)).setActive(false);
					((ListMenu)MenuBox.getChildren().get(NewChoice)).setActive(true);
					Oldchoice = NewChoice;
					}
				}
			});
	}

	public class ListMenu extends HBox {
		private Text name;
		private ImageView kunai = new ImageView(new Image(ClassLoader.getSystemResource("icon/kunai.png").toString(),150,40,true,true));
//		private Runnable script;

		ListMenu(String text) {
//			this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			this.setSpacing(10);
			this.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
			this.setAlignment(Pos.CENTER);
			name = new Text(text);
			name.setFont(MainmenuScreen.this.narutoFont);
			getChildren().addAll(kunai, name);
			setActive(false);
		}

		void setActive(boolean check) {
			kunai.setVisible(check);
			name.setFill(check ? Color.BLACK : Color.GRAY);
		}
		//dont know why use Thread here
//		public void setOnActivate(Runnable r) {
//            script = r;
//        }
//		public void activate() {
//	            if (script != null)
//	                script.run();
//	     }
		
	}
}
