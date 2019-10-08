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

public class Pivot extends GameEntity implements Actor {
	private ImageGraphics Graphics4;

	public Pivot(ActorGame game, Vector position, boolean fixed) {
		super(game, true, position);
		// TODO Auto-generated constructor stub

		Polygon polygon4 = new Polygon(new Vector(0.1f, 0f), new Vector(0.1f, 0.1f),
				new Vector(0f,  0.1f), new Vector(0f, 0f));

		Graphics4 = new ImageGraphics(null, 0.1f,  0.1f);

		Graphics4.setParent(getEntity());

		getEntity().setPosition(position);

		PartBuilder partBuilder4 = getEntity().createPartBuilder();

		partBuilder4.setShape(polygon4);
		partBuilder4.build();
	}

	public Entity getPivotEntity() {
		return getEntity();
	}
	@Override
	public void draw(Canvas canvas) {
		Graphics4.draw(canvas);

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