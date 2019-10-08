package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Flag extends GameEntity implements Actor {
	private ImageGraphics Flag;
	
	BasicContactListener contact;
	
	
    
	public Flag(ActorGame game, Color colour, Float Checkpoint, boolean fixed, Vector position) {
		super(game, fixed, position);

		Polyline flag = new Polyline(0f, 0f, 0.5f, 0f, 0.5f, 1.5f, 2.0f, 1.5f, 0.0f, 2.5f);

		Flag = new ImageGraphics("flag.red.png",2f,2f);
		
		Flag.setParent(getEntity());
		
		getEntity().setPosition(position);

		PartBuilder PartFlag = getEntity().createPartBuilder();
		
		PartFlag.setShape(flag);
		PartFlag.setFriction(0.100f);
		PartFlag.setGhost(true);
		PartFlag.build();
		
		
	

	}


	@Override
	public void draw(Canvas canvas) {
		Flag.draw(canvas);

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