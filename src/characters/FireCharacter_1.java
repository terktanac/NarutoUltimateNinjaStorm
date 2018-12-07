package characters;

import gameObject.Amaterasu;
import gameObject.Chidori;
import gameObject.Fireball;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import main.GameScreen;

public class FireCharacter_1 extends Character{

	private static Image image = new Image(ClassLoader.getSystemResource("characters/sasuke_aka/sasuke_aka.png").toString(),1110, 2220, false, false);
	public FireCharacter_1() {
		super("Sasuke", 1, 100, 10, 4,1.2, new ImageView(image));
		setCount(4);
		setOffSetY(111);
		setAnimation(new CharacterAnimation(this.getImageview(), Duration.millis(300), getCount(), getCol(), getOffSetX(), getOffSetY(), get_Width(), get_Height()));
	}

	@Override
	public int walk_right() {
		this.setMove(true);
		if(!isAttacked() && !isDead() && !isCrouch() && !isBlock()) {	
			if(this.isRight() != true) {
				this.getImageview().setRotationAxis(Rotate.Y_AXIS);
				this.getImageview().setRotate(0);
				this.setRight(true);
			}
			this.getAnimation().setOffSetX(0);
			this.getAnimation().setOffSetY(222);
			this.setCount(6);
			this.getAnimation().play();
			this.moveX(characters.Character.getX_speed());
			
			return 1;
		}
		else {
			this.setMove(false);
			return 0;
		}
	}
	@Override
	public int walk_left() {
		this.setMove(true);
		if(!isAttacked() && !isDead() && !isCrouch() && !isBlock()) {	
			if(this.isRight() == true) {
				this.getImageview().setRotationAxis(Rotate.Y_AXIS);
				this.getImageview().setRotate(180);
				this.setRight(false);
			}
			this.getAnimation().setOffSetX(0);
			this.getAnimation().setOffSetY(222);
			this.setCount(6);
			this.getAnimation().play();
			this.moveX(-characters.Character.getX_speed());
			return 1;
		}
		else {
			this.setMove(false);
			return 0;
		}
	}
	@Override
	public int crouch() {
		if(!isAir() && !isAttacked() && !isJump() && !isDead() && !isMove()) {
			setCrouch(true);
			this.getImageview().setViewport(new Rectangle2D(333, 333, get_Width() - 15, get_Height() - 1.3));
			return 1;
		}
		else 
			return 0;
	}
	@Override
	public int jump() {
		if(!isAir() && !isAttacked() && !isJump() && !isDead() && !isCrouch() && !isBlock()) {
			
			setJump(true);
			setAir(true);
			this.getAnimation().stop();
			return 1;
		}
		else
			return 0;
	}
	
@Override
	public int doJump() {
		if(isJump()) {
			if(getTranslateY() > 100) {
				setTranslateY(getTranslateY()-3);
				getImageview().setViewport(new Rectangle2D(444, 333, get_Width() - 15, get_Height() - 1.3));
			}
			else
				setJump(false);
		}
		else if(isAir()) {
			if(getTranslateY() < 300) {
				setTranslateY(getTranslateY()+3);
				getImageview().setViewport(new Rectangle2D(555, 333, get_Width() - 15, get_Height() - 1.3));
			}
			else {
				setAir(false);
				setMove(false);
				stand();
			}
		}
		return 1;
	}
	
	@Override
	public int melee() {
		if(!isAttacked() && !isDead() && !isCrouch() && !isRange() && !isBlock()) {
			setMelee(true);
			setMove(true);
			this.getAnimation().stop();
			return 1;
		}
		return 0;
	}
	
	@Override
	public int doMelee() {
		if(isMelee()) {
			if(getDelay() >= 80) {
				getImageview().setViewport(new Rectangle2D(555, 555, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 60) {
				getImageview().setViewport(new Rectangle2D(666, 555, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 40) {
				getImageview().setViewport(new Rectangle2D(777, 555, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 20) {
				getImageview().setViewport(new Rectangle2D(888, 555, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 0) {
				getImageview().setViewport(new Rectangle2D(999, 555, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else {
				setMelee(false);
				setDelay(100);
				stand();
			}
		}
		return 1;
	}

	@Override
	public int range() {
		if(!isAttacked() && !isDead() && !isCrouch() && !isMelee() && !isBlock()) {
			setRange(true);
			setMove(true);
			this.getAnimation().stop();
			return 1;
		}
		return 1;
	}

	@Override
	public int doRange() {
		if(isRange()) {
			if(getDelay() >= 80) {
				getImageview().setViewport(new Rectangle2D(777, 999, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 60) {
				getImageview().setViewport(new Rectangle2D(888, 999, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 40) {
				getImageview().setViewport(new Rectangle2D(999, 999, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 20) {
				getImageview().setViewport(new Rectangle2D(0, 1110, get_Width() - 15, get_Height() - 1.3));
				setDelay(getDelay()-1);
			}
			else {
				setRange(false);
				setDelay(100);
				stand();
			}
		}
		return 1;
	}
	
	@Override
	public int block() {
		if(!isAir() && !isAttacked() && !isJump() && !isDead() && !isMove()) {
			setBlock(true);
			this.getImageview().setViewport(new Rectangle2D(222, 333, get_Width() - 15, get_Height() - 1.3));
			return 1;
		}
		else 
			return 0;
	}

	@Override
	public int dodge() {
		if(!isAttacked() && !isDead() && !isCrouch() && !isMelee() && !isRange() && !isBlock() && !isJump() && !isDodge() && getLimitDodge() > 0) {
			setDodge(true);
			setMove(true);
			this.getAnimation().stop();
			getImageview().setImage(getSmoke().getImage());
			setWidth(440);
			setHeight(199);
			getImageview().setViewport(new Rectangle2D(440, 0, get_Width(), get_Height()));
			getImageview().setFitWidth(440.0*(350.0/199.0));
			getImageview().setFitHeight(350);
			setTranslateX(getTranslateX()-250);
			return 1;
		}
		return 0;
	}
	
	@Override
	public int doDodge() {
		if(isDodge()) {
			System.out.println("dodge");
			if(getDelay() >= 80) {
				getImageview().setViewport(new Rectangle2D(440, 0, get_Width(), get_Height()));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 60) {
				getImageview().setViewport(new Rectangle2D(880, 0, get_Width(), get_Height()));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 40) {
				getImageview().setViewport(new Rectangle2D(1320, 0, get_Width(), get_Height()));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 20) {
				getImageview().setViewport(new Rectangle2D(880, 0, get_Width(), get_Height()));
				setDelay(getDelay()-1);
			}
			else if(getDelay() >= 0) {
				getImageview().setViewport(new Rectangle2D(440, 0, get_Width(), get_Height()));
				setDelay(getDelay()-1);
			}
			else {
				setOffSetX(0);
				setOffSetY(0);
				setWidth(111);
				setHeight(111);
				getImageview().setImage(image);
				getImageview().setViewport(new Rectangle2D(getOffSetX(), getOffSetY(), get_Width() - 15, get_Height() - 1.3));
				getImageview().setFitHeight(350);
				getImageview().setFitWidth(350);	
				setDodge(false);
				setDelay(100);
				setTranslateX(getTranslateX()+250);
				setLimitDodge(getLimitDodge()-1);
				stand();
			}
			return 1;
		}
		return 0;
		
	}
	
	@Override
	public int stand() {
		this.setCount(4);
		this.setOffSetY(111);
		this.getAnimation().setOffSetY(111);
		this.getAnimation().play();
		return 1;
	}

	@Override
	public int basic_skill(Character target) {
		if(isSkill1()) {
			if(getSkillDelay() >= 230) {
				getImageview().setViewport(new Rectangle2D(0, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 160) {
				getImageview().setViewport(new Rectangle2D(111, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() > 140) {
				setTranslateX(target.getTranslateX());
				setTranslateY(100);
				getImageview().setViewport(new Rectangle2D(111, 1776, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() == 140) {
				setTranslateX(target.getTranslateX());
				setTranslateY(100);
				getImageview().setViewport(new Rectangle2D(111, 1776, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
				target.setStackFly(1);
				GameScreen.getgameObjects2().add(new Chidori(GameScreen.getPlayer1().getTranslateX(), GameScreen.getPlayer1().getTranslateY(),GameScreen.getPlayer2().isRight()));
				GameScreen.get_Root().getChildren().add(GameScreen.getgameObjects2().get(GameScreen.getgameObjects2().size()-1));
				GameScreen.getgameObjects2().get(GameScreen.getgameObjects2().size()-1).getAnimation().play();
			}
			else if(getSkillDelay() == 40) {
				getImageview().setViewport(new Rectangle2D(222, 1776, get_Width(), get_Height()));
				getAnimation().play();
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() > 40) {
				getImageview().setViewport(new Rectangle2D(222, 1776, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else {
				setSkillDelay(250);
				setSkill1(false);
				jump();
				stand();
			}
			return 1;
		}
		return 0;
	}

	@Override
	public int mid_skill(Character target) {
		if(isSkill2()) {
			if(getSkillDelay() >= 230) {
				getImageview().setViewport(new Rectangle2D(0, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 160) {
				getImageview().setViewport(new Rectangle2D(111, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 130) {
				getImageview().setViewport(new Rectangle2D(222, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 100) {
				getImageview().setViewport(new Rectangle2D(333, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 70) {
				getImageview().setViewport(new Rectangle2D(444, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
				GameScreen.getgameObjects2().add(new Fireball(GameScreen.getPlayer2().getTranslateX(), GameScreen.getPlayer2().getTranslateY(),GameScreen.getPlayer2().isRight()));
				GameScreen.get_Root().getChildren().add(GameScreen.getgameObjects2().get(GameScreen.getgameObjects2().size()-1));
				GameScreen.getgameObjects2().get(GameScreen.getgameObjects2().size()-1).getAnimation().play();
			}
			else if(getSkillDelay() >= 50) {
				getImageview().setViewport(new Rectangle2D(555, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 30) {
				getImageview().setViewport(new Rectangle2D(666, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 10) {
				getImageview().setViewport(new Rectangle2D(777, 1221, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else {
				setSkillDelay(250);
				setSkill2(false);
				stand();
			}
			return 1;
		}
		return 0;
	}

	@Override
	public int High_skill(Character target) {
		if(isSkill3()) {
			if(getSkillDelay() == 250) {
				if(!isRight())
					setTranslateX(getTranslateX()+30);
				else
					setTranslateX(getTranslateX()-30);
				getImageview().setViewport(new Rectangle2D(0, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 230) {
				getImageview().setViewport(new Rectangle2D(0, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() == 160) {
				getImageview().setViewport(new Rectangle2D(111, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
				GameScreen.getgameObjects2().add(new Amaterasu(GameScreen.getPlayer1().getTranslateX(), GameScreen.getPlayer1().getTranslateY(),GameScreen.getPlayer2().isRight(),target));
				GameScreen.get_Root().getChildren().add(GameScreen.getgameObjects2().get(GameScreen.getgameObjects2().size()-1));
			}
			else if(getSkillDelay() > 160) {
				getImageview().setViewport(new Rectangle2D(111, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 130) {
				getImageview().setViewport(new Rectangle2D(222, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 100) {
				getImageview().setViewport(new Rectangle2D(333, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 70) {
				getImageview().setViewport(new Rectangle2D(444, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 40) {
				getImageview().setViewport(new Rectangle2D(555, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= 10) {
				getImageview().setViewport(new Rectangle2D(666, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -20) {
				getImageview().setViewport(new Rectangle2D(777, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -50) {
				getImageview().setViewport(new Rectangle2D(888, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -80) {
				getImageview().setViewport(new Rectangle2D(999, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else if(getSkillDelay() >= -110) {
				getImageview().setViewport(new Rectangle2D(777, 1887, get_Width(), get_Height()));
				setSkillDelay(getSkillDelay()-1);
			}
			else {
				if(isRight())
					setTranslateX(getTranslateX()+30);
				else
					setTranslateX(getTranslateX()-30);
				setSkillDelay(250);
				setSkill3(false);
				stand();
			}
			return 1;
		}
		return 0;
	}



	@Override
	public int dotakeDamage() {
		if(isAttacked() && !isDead()) {
			if(getStackFly() == 3) {
				if(getDelay() >= 0) {
					this.getImageview().setViewport(new Rectangle2D(0, 444, get_Width() - 15, get_Height() - 1.3));
					setDelay(getDelay()-1);
				}
				else {
					setStackFly(2);
					setDelay(100);
					setAttacked(false);
					stand();
					getAnimation().play();
					
				}
			}
			else if(getStackFly() == 2) {
				if(getDelay() >= 0) {
					this.getImageview().setViewport(new Rectangle2D(111, 444, get_Width() - 15, get_Height() - 1.3));
					setDelay(getDelay()-1);
				}
				else {
					setStackFly(1);
					setAttacked(false);
					setDelay(100);
					stand();
					getAnimation().play();
					
				}
			}
			else {
				if(getLongDelay() >= 150) {
					getImageview().setViewport(new Rectangle2D(111, 444, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
					if(isRight())
						setTranslateX(getTranslateX()-3);
					else
						setTranslateX(getTranslateX()+3);
				}
				else if(getLongDelay() >= 130) {
					getImageview().setViewport(new Rectangle2D(222, 444, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
					if(isRight())
						setTranslateX(getTranslateX()-3);
					else
						setTranslateX(getTranslateX()+3);
				}
				else if(getLongDelay() >= 100) {
					getImageview().setViewport(new Rectangle2D(444, 444, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
					if(isRight())
						setTranslateX(getTranslateX()-3);
					else
						setTranslateX(getTranslateX()+3);
				}
				else if(getLongDelay() >= 80) {
					getImageview().setViewport(new Rectangle2D(888, 444, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
				}
				else if(getLongDelay() >= 60) {
					getImageview().setViewport(new Rectangle2D(777, 444, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
				}
				else if(getLongDelay() >= 30) {
					getImageview().setViewport(new Rectangle2D(999, 444, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
				}
				else if(getLongDelay() >= 20) {
					getImageview().setViewport(new Rectangle2D(0, 555, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
				}
				else if(getLongDelay() >= 10) {
					getImageview().setViewport(new Rectangle2D(111, 555, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
				}
				else if(getLongDelay() >= 0) {
					getImageview().setViewport(new Rectangle2D(222, 555, get_Width() - 15, get_Height() - 1.3));
					setLongDelay(getLongDelay()-1);
				}
				else {
					setStackFly(3);
					setAttacked(false);
					setLongDelay(170);
					stand();
					getAnimation().play();
				}
			}
			return 1;
		}
		return 0;
	}
	@Override
	public int dead() {
		if(!isDead())
			return 0;
		if(getLongDelay() >= 150) {
			getImageview().setViewport(new Rectangle2D(111, 444, get_Width() - 15, get_Height() - 1.3));
			setLongDelay(getLongDelay()-1);
			if(isRight())
				setTranslateX(getTranslateX()-3);
			else
				setTranslateX(getTranslateX()+3);
		}
		else if(getLongDelay() >= 130) {
			getImageview().setViewport(new Rectangle2D(222, 444, get_Width() - 15, get_Height() - 1.3));
			setLongDelay(getLongDelay()-1);
			if(isRight())
				setTranslateX(getTranslateX()-3);
			else
				setTranslateX(getTranslateX()+3);
		}
		else if(getLongDelay() >= 100) {
			getImageview().setViewport(new Rectangle2D(444, 444, get_Width() - 15, get_Height() - 1.3));
			setLongDelay(getLongDelay()-1);
			if(isRight())
				setTranslateX(getTranslateX()-3);
			else
				setTranslateX(getTranslateX()+3);
		}
		else if(getLongDelay() >= 50) {
			getImageview().setViewport(new Rectangle2D(888, 444, get_Width() - 15, get_Height() - 1.3));
			setLongDelay(getLongDelay()-1);
		}
		else if(getLongDelay() >= 25) {
			getImageview().setViewport(new Rectangle2D(777, 444, get_Width() - 15, get_Height() - 1.3));
			setLongDelay(getLongDelay()-1);
		}
		else if(getLongDelay() >= 0) {
			getImageview().setViewport(new Rectangle2D(999, 444, get_Width() - 15, get_Height() - 1.3));
			setLongDelay(getLongDelay()-1);
		}
		else {
			getAnimation().stop();
		}
		return 1;
	}




}
