package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Boule extends GameEntity implements Actor {
private ShapeGraphics boulegraphic;
	public Boule(ActorGame game, boolean fixed, Vector position) {
		super(game, fixed, position);
			Circle boule = new Circle(0.5f, position);
		boulegraphic = new ShapeGraphics(boule, Color.BLUE,Color.BLUE,1f, 1.f, 0f);
		boulegraphic.setParent(getEntity());
	
		PartBuilder partbuildball = getEntity().createPartBuilder();
		partbuildball.setShape(boule);  
		partbuildball.build();
		
	}

public Entity bouleEntity() {
	return getEntity();
}


	@Override
	public void draw(Canvas canvas) {
		boulegraphic.draw(canvas);
		
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
