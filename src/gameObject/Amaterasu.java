package gameObject;

import characters.Character;
import characters.CharacterAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Amaterasu extends GameObject{

	Character target;
	public Amaterasu(double posx, double posy, boolean direction, Character target) {
		super(posx, posy, direction);
		this.target = target;
		setHasEffect(true);
		setOffSetX(0);
		setOffSetY(171);
		setWidth(111);
		setHeight(171);
		setCount(5);
		setSpeed(0);
		setDamage(100);
		setImageview(new ImageView("sys/amaterasu.png"));
		getImageview().setFitHeight(171.0*350.0/171.0);
		getImageview().setFitWidth(111.0*350.0/171.0);
		setAnimation((new CharacterAnimation(getImageview(), Duration.millis(100), getCount(), 0, getOffSetX(), getOffSetY(), get_Width(), get_Height())));
		getChildren().addAll(getImageview());
	}

	@Override
	public void doEffect() {
		setTranslateX(target.getTranslateX());
		setTranslateY(target.getTranslateY());
		if(getDelay() >= 280) {
			getImageview().setViewport(new Rectangle2D(0, 0, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 260) {
			getImageview().setViewport(new Rectangle2D(111, 0, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 230) {
			getImageview().setViewport(new Rectangle2D(222, 0, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 200) {
			getImageview().setViewport(new Rectangle2D(333, 0, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 170) {
			getImageview().setViewport(new Rectangle2D(444, 0, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 100) {
			getAnimation().play();
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 80) {
			getAnimation().stop();
			getImageview().setViewport(new Rectangle2D(0, 342, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 60) {
			getImageview().setViewport(new Rectangle2D(111, 342, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 40) {
			getImageview().setViewport(new Rectangle2D(222, 342, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 20) {
			getImageview().setViewport(new Rectangle2D(333, 342, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else if(getDelay() >= 0) {
			getImageview().setViewport(new Rectangle2D(444, 342, get_Width(), get_Height()));
			setDelay(getDelay()-1);
		}
		else {
			setDoing(false);
			setDone(true);
			setDelay(300);
		}
			
		
	}

}