package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Puit extends GameEntity implements Actor {
private ImageGraphics puit;
private BasicContactListener contact;
	public Puit(ActorGame game, boolean fixed, Vector position) {
		
		super(game, fixed, position);
		
		puit = new ImageGraphics("glass.8.png", 3, 8);
		puit.setParent(getEntity());
		puit.setAlpha(0.5f);
		getEntity().setPosition(position);
		
		
		PartBuilder partpuit = getEntity().createPartBuilder();		
		Polygon polypuit  = new Polygon (
				 0f,0f,
				3f,0f,
				3f,8f,
				0f,8f
				  );	
		
		partpuit.setShape(polypuit);
        partpuit.setGhost(true);
	    partpuit.build();
	   
	    contact = new BasicContactListener();
		getEntity().addContactListener(contact);
	}
	public void update(float deltatime) {
		if(contact.getEntities().contains(getPayloadEntity())){
 super.force(new Vector(0, 0.18f), getPayloadEntity());
		}
	}
	
	

	@Override
	public void draw(Canvas canvas) {
		puit.draw(canvas);
		
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
