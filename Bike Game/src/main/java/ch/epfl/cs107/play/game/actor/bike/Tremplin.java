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

public class Tremplin extends GameEntity implements Actor {
private ImageGraphics puit;
private static BasicContactListener contact;
	public Tremplin(ActorGame game, boolean fixed, Vector position)  {
	
		super(game, fixed, position);
		puit = new ImageGraphics("slime.green.right.1.png", 3, 3);
		puit.setParent(getEntity());
		puit.setAlpha(0.5f);
		getEntity().setPosition(position);
		getEntity().applyForce(new Vector(0f,19f), getEntity().getPosition().add(-1.5f,-4f));
		
		PartBuilder partpuit = getEntity().createPartBuilder();		
		Polygon polypuit  = new Polygon (
				 0f,0f,
				3f,0f,
				3f,3f,
				0f,3f
				  );	
		
		partpuit.setShape(polypuit);
        partpuit.setGhost(true);
	    partpuit.build();
	   
	    contact = new BasicContactListener();
		getEntity().addContactListener(contact);	
	}

	public void update(float deltatime) {
		if(contact.getEntities().contains(getPayloadEntity())){
			super.saut(0.5f);
		}
	}
	public static boolean contatcterrain(Object a) {
		return contact.getEntities().contains(a);
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
