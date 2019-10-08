package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Part;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Pics extends GameEntity implements Actor {
private ImageGraphics cactus; 
private  boolean touche;
private BasicContactListener contact;

public Pics(ActorGame game, boolean fixed, Vector position) {
		super(game, fixed, position);
		Polygon polycactus = new Polygon(
				 0f,0f,
				3f,0f,
				3f,3f,
				0f,3f
				);
		
		cactus = new ImageGraphics("cactus.png", 1,1);
		cactus.setParent(getEntity());
		PartBuilder partcactus = getEntity().createPartBuilder();
		partcactus.setShape(polycactus);
		partcactus.setGhost(true);
		partcactus.build();
		
	}
	

	@Override
	public void draw(Canvas canvas) {
		cactus.draw(canvas);
		
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
