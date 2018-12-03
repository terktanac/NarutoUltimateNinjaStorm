package main;



import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class MultiPlayerScreen extends myScene{
	private int player1 = 1,player2 = 0; //default character =0 : naruto
	private static Pane root = new Pane();
	protected static MediaPlayer player = new MediaPlayer(new Media(ClassLoader.getSystemResource("menu/Gekiha.mp3").toString()));
	
	public MultiPlayerScreen() {
		super(root);
		
		player.setVolume(0.3);
		ArrayList<Image> Characters = new ArrayList<Image>();
		ArrayList<Image> CharactersReady = new ArrayList<Image>();
		ArrayList<String> CharactersName = new ArrayList<String>();
		ArrayList<ListCharacter> listCharacterpy1 = new ArrayList<ListCharacter>();
		ArrayList<ListCharacter> listCharacterpy2 = new ArrayList<ListCharacter>();
		
		Characters.add(new Image(ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage_mugs_2.png").toString(),230,500,false,true));
		Characters.add(new Image(ClassLoader.getSystemResource("characters/sasuke_aka/sasuke_aka_mugs_2.png").toString(),230,500,false,true));
		
		CharactersReady.add(new Image(ClassLoader.getSystemResource("characters/naruto_sage/naruto_sage_mugs_3.png").toString(),310,480,false,true));
		CharactersReady.add(new Image(ClassLoader.getSystemResource("characters/sasuke_aka/sasuke_aka_mugs_3.png").toString(),230,500,false,true)); 
		
		CharactersName.add("Naruto");
		CharactersName.add("Sasuke");

		listCharacterpy1.add(new ListCharacter("characters/naruto_sage/face.jpg","characters/naruto_sage/face_active.jpg",100,130));
		listCharacterpy1.add(new ListCharacter("characters/sasuke_aka/face.jpg","characters/sasuke_aka/face_active.jpg",205,130));
		
		listCharacterpy2.add(new ListCharacter("characters/naruto_sage/face.jpg","characters/naruto_sage/face_active.jpg",980,130));
		listCharacterpy2.add(new ListCharacter("characters/sasuke_aka/face.jpg","characters/sasuke_aka/face_active.jpg",1085,130));
		
		Text pressKey = new Text("Press any key to Continue");
		pressKey.setFont(getNarutoFont());
		pressKey.setFill(Color.WHITE);
		pressKey.setStroke(Color.BLACK);
		pressKey.setTranslateX(340);
		pressKey.setTranslateY(600);
		pressKey.setVisible(false);
		
		root.setPrefSize(1280, 720);
		Image background = new Image(ClassLoader.getSystemResource("background/shinobi2.jpg").toString(),1300,740,false,false);
		root.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		
		ImageView lhschar,rhschar,vs,scrollpy1,scrollpy2;
		//vs. logo
		vs = new ImageView(new Image(ClassLoader.getSystemResource("icon/vs.png").toString(),170,170,false,false));
		vs.setTranslateX(560);
		vs.setTranslateY(280);
		
		lhschar = new ImageView(Characters.get(player1));
		lhschar.setTranslateX(360);
		lhschar.setTranslateY(110);
		
		rhschar = new ImageView(Characters.get(player2));
		rhschar.setTranslateX(690);
		rhschar.setTranslateY(110);
		rhschar.setRotationAxis(Rotate.Y_AXIS);
		rhschar.setRotate(180);
		
		scrollpy1 = new ImageView(new Image(ClassLoader.getSystemResource("icon/scroll.png").toString(),350,600,false,true));
		scrollpy1.setTranslateX(20);
		scrollpy1.setTranslateY(50);
		
		scrollpy2 = new ImageView(new Image(ClassLoader.getSystemResource("icon/scroll.png").toString(),350,600,false,true));
		scrollpy2.setTranslateX(900);
		scrollpy2.setTranslateY(50);
		
		Check chosen1 = new Check(false);
		Check chosen2 = new Check(false);

		root.getChildren().addAll(lhschar,rhschar,scrollpy1,scrollpy2,vs,pressKey);
		
		for(int i = 0; i < listCharacterpy1.size(); i++)
			root.getChildren().add(listCharacterpy1.get(i));
		
		for(int i = 0; i < listCharacterpy2.size(); i++)
			root.getChildren().add(listCharacterpy2.get(i));
		
		listCharacterpy1.get(player1).setActive(true);
		listCharacterpy2.get(player2).setActive(true);
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), evt -> pressKey.setVisible(true)),
				new KeyFrame(Duration.seconds(0.7), evt -> pressKey.setVisible(false)));
		timeline.setCycleCount(Animation.INDEFINITE);
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				MediaPlayer click = new MediaPlayer(new Media(ClassLoader.getSystemResource("lighter.wav").toString()));
				MediaPlayer choose = new MediaPlayer(new Media(ClassLoader.getSystemResource("accept5.wav").toString()));
				KeyCode key = event.getCode();
				System.out.println("Multiplayer:Pressed " + key.toString());
				
				if(chosen1.check == true && chosen2.check == true) {
					choose.play();
					Main.ChangeScene(Main.getMapscreen());
				}
				else if (key == KeyCode.BACK_SPACE) {
					player.stop();
					choose.play();
					Main.ChangeScene(Main.getMainmenu());
				}
				else if (key == KeyCode.SPACE) {
					lhschar.setImage(CharactersReady.get(player1));
					choose.play();
					chosen1.check = true;
					if(chosen1.check == true && chosen2.check == true) {
						timeline.play();
					}
				} 
				else if (key == KeyCode.ENTER) {
					rhschar.setImage(CharactersReady.get(player2));
					choose.play();
					chosen2.check = true;
					if(chosen1.check == true && chosen2.check == true) {
						timeline.play();
					}
				} 
				else if ((key == Main.getOptionscreen().getUp_1() || key == Main.getOptionscreen().getLeft_1()) && !chosen1.check) {
					listCharacterpy1.get(player1).setActive(false);
					click.play();
					player1 = (player1+1)%Characters.size();
					lhschar.setImage(Characters.get(player1));
					listCharacterpy1.get(player1).setActive(true);
				}
				else if ((key == Main.getOptionscreen().getUp_2() || key == Main.getOptionscreen().getLeft_2()) && !chosen2.check) {
					click.play();
					listCharacterpy2.get(player2).setActive(false);
					player2 = (player2+1+Characters.size())%Characters.size();
					rhschar.setImage(Characters.get(player2));
					listCharacterpy2.get(player2).setActive(true);
				}
				else if ((key == Main.getOptionscreen().getDown_1() || key == Main.getOptionscreen().getRight_1()) && !chosen1.check) {
					listCharacterpy1.get(player1).setActive(false);
					click.play();
					player1 = (player1-1+Characters.size())%Characters.size();
					lhschar.setImage(Characters.get(player1));
					listCharacterpy1.get(player1).setActive(true);
				}
				else if ((key == Main.getOptionscreen().getDown_2() ||key == Main.getOptionscreen().getRight_2()) && !chosen2.check) {
					click.play();
					listCharacterpy2.get(player2).setActive(false);
					player2 = (player2-1+Characters.size())%Characters.size();
					rhschar.setImage(Characters.get(player2));
					listCharacterpy2.get(player2).setActive(true);
				}
			}
		});
		

	}
	public class ListCharacter extends HBox{
		private ImageView img;
		private String normal,active;
		int px,py;
		public ListCharacter(String n,String a, int posx, int posy) {
			this.normal = n;
			this.active = a;
			this.px = posx;
			this.py = posy;		
			img = new ImageView(new Image(ClassLoader.getSystemResource(n).toString(),100,100,false,true));
			img.setTranslateX(posx);
			img.setTranslateY(posy);
			this.getChildren().addAll(img);
			setActive(false);
		}
		public void setActive(boolean check) {
			if(check == true) {
				img.setImage(new Image(ClassLoader.getSystemResource(this.active).toString(),100,100,false,true));
			}
			else {
				img.setImage(new Image(ClassLoader.getSystemResource(this.normal).toString(),100,100,false,true));
			}
		}
	}
	public class Check {
		private boolean check;
		
		public Check(boolean c) {
			check = c;
		}
		public boolean getCheck() {
			return check;
		}

		public void setCheck(boolean check) {
			this.check = check;
		}
	}
	@Override
	public void upPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void downPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void leftPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rightPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void meleePressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rangePressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dodgePressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void blockPressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void SpacePressed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void EnterPressed() {
		// TODO Auto-generated method stub
		
	}
}

	