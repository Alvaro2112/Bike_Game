package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.RopeConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Pendule extends GameEntity implements Actor{

	public Pendule(ActorGame game, boolean fixed) {
		super(game, fixed);	
	}
	
		void attachPendulum(Entity boite, Entity boule, float width1, float height1,Vector rayon) {
		RopeConstraintBuilder ropeConstraintBuilder =  getActorGame().getRope() ;
				ropeConstraintBuilder.setFirstEntity(boite) ;
				ropeConstraintBuilder.setFirstAnchor(new Vector(width1,height1));
				ropeConstraintBuilder.setSecondEntity(boule) ;
				ropeConstraintBuilder.setSecondAnchor(rayon) ;
				ropeConstraintBuilder.setMaxLength(3.0f) ;
				ropeConstraintBuilder.setInternalCollision(false) ;
				ropeConstraintBuilder.build() ;  
	}

	@Override
	public void draw(Canvas canvas) {
		
		
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
