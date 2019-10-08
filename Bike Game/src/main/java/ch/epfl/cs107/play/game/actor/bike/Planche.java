package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Planche extends GameEntity implements Actor {

	private ImageGraphics Graphics3;

	public Planche(ActorGame game, Vector position, float longueur, boolean fixed) {
		super(game, false, position);
		
if ((longueur <0)) {
			
			throw new IllegalArgumentException();
		
		}

		Polygon planche = new Polygon(new Vector(longueur, 0.0f), new Vector(longueur, 0.2f),
				new Vector(0.0f, 0.2f), new Vector(0.0f, 0.0f));

		Graphics3 = new ImageGraphics("box.4.png", longueur, 0.2f);

		Graphics3.setParent(getEntity());

		getEntity().setPosition(position);

		PartBuilder PartTerrain = getEntity().createPartBuilder();

		PartTerrain.setShape(planche);

		PartTerrain.build();
		

	

		

	/*	
*/
	}
public Entity planchegetEntity() {
	return getEntity();
}
	@Override
	public void draw(Canvas canvas) {
		Graphics3.draw(canvas);

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