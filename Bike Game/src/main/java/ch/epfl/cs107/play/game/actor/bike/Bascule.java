package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.math.RevoluteConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Bascule extends GameEntity implements Actor {
	Pivot pivot;
	Planche planche;

	public Bascule(ActorGame game, Vector position, float longueur, boolean fixed) {
		super(game, fixed, position);
if ((longueur <0)) {
			
			throw new IllegalArgumentException();
		
		}
		
		planche = new Planche(game, position, longueur, fixed);
		
		pivot = new Pivot(game, position.add(new Vector(longueur/2-0.05f,-0.15f)), fixed);

		
	
		RevoluteConstraintBuilder revoluteConstraintBuilder = getActorGame().getRevoluteConstraint();
		revoluteConstraintBuilder.setFirstEntity(planche.planchegetEntity());
		revoluteConstraintBuilder.setFirstAnchor(new Vector(longueur / 2,0f));
		revoluteConstraintBuilder.setSecondEntity(pivot.getPivotEntity());
		revoluteConstraintBuilder.setSecondAnchor(new Vector(0.05f, 0.1f));
		revoluteConstraintBuilder.setInternalCollision(false);
		revoluteConstraintBuilder.build();
	}

	@Override
	public void draw(Canvas canvas) {
		pivot.draw(canvas);
		planche.draw(canvas);

	}

	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

}