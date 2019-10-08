package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Boite extends GameEntity implements Actor {
private ImageGraphics boitegraphic;
private ShapeGraphics boulegraphic;


	public Boite(ActorGame game, boolean fixed, Vector position) {
		super(game, fixed, position);
		boitegraphic = new ImageGraphics("box.4.png",1,1);
		boitegraphic.setParent(getEntity());
		getEntity().setPosition(position);
		Polygon polyboite = new Polygon(
				 0f,0f,
				1f,0f,
				1f,1f,
				0f,1f
				);
	
		
		PartBuilder partbuilderboite = getEntity().createPartBuilder();
		partbuilderboite.setShape(polyboite);
		partbuilderboite.build();
	
		
	
	}
public float boitegetWidth() {
	return boitegraphic.getWidth();
}
public float boitegetHeight() {
	return boitegraphic.getHeight();
}
public Entity boiteEntity() {
	return getEntity();
}
	
	

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		boitegraphic.draw(canvas);
		
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
