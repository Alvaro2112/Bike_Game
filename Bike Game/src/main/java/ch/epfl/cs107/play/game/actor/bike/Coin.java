package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Coin extends GameEntity implements Actor {
private ImageGraphics coin;
BasicContactListener contact;
private int score;
private boolean touche;
private TextGraphics message1;
	public Coin(ActorGame game, boolean fixed, Vector position) {
		super(game, fixed, position);
		coin = new ImageGraphics("star.gold.png",0.3f,0.3f);
		coin.setParent(getEntity());
		getEntity().setPosition(position);
		PartBuilder partcoin = getEntity().createPartBuilder();
		Polygon polycoin  = new Polygon(
				 0f,0f,
				0.3f,0f,
				0.3f,0.3f,
				0f,0.3f
				);
		partcoin.setGhost(true);
		partcoin.setFriction(0.111f);
		partcoin.setShape(polycoin);
		partcoin.build();
		
		contact = new BasicContactListener();
		getEntity().addContactListener(contact);

	}
	public boolean getTouche() {
		return touche;
	}
	public void update(float deltatime) {
		if(contact.getEntities().contains(getPayloadEntity())){
			touche = true;
		}
		else {touche = false;}
		if(touche) {
		score =1;
		}
	}
public TextGraphics getMessageCoin() {
	TextGraphics message1;
	message1 = new TextGraphics("Score :" + score, 0.3f, java.awt.Color.RED, java.awt.Color.RED, 0.02f, true,
			false, new Vector(0.5f, 0.5f), 1.0f, 10.0f);
	message1.setParent(getActorGame().getCanvas());

	message1.setRelativeTransform(Transform.I.translated(0.0f, -1.0f));

	message1.draw(getActorGame().getCanvas());
	
	return message1;
	
}

	@Override
	public void draw(Canvas canvas) {
		coin.draw(canvas);
		
	}

	@Override
	public Transform getTransform() {
		return getEntity().getTransform();
	}

	@Override
	public Vector getVelocity() {
		return getEntity().getVelocity();
	}

}
