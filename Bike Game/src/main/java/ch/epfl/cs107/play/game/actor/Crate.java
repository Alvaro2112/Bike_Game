package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Canvas;

public class Crate extends GameEntity implements Actor {
	private ImageGraphics Graphics;

	Crate(ActorGame game, boolean fixed, Vector position) {

		super(game, fixed, position);

		Graphics = new ImageGraphics("box.4.png", 1, 1);

		Graphics.setParent(getEntity());

		getEntity().setPosition(position);

		PartBuilder partBuilder2 = getEntity().createPartBuilder();

		Polygon polygon2 = new Polygon(new Vector(1.0f, 0.0f), new Vector(1.0f, 1.0f), new Vector(0.0f, 1.0f),
				new Vector(0.0f, 0.0f));

		partBuilder2.setShape(polygon2);

		partBuilder2.build();

	}

	public void update(float DeltaTime) {

	}

	public void Destroy() {

	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		Graphics.draw(canvas);

	}

	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return getEntity().getTransform();
	}

	@Override
	public Vector getVelocity() {
		// TODO Auto-generated method stub
		return getEntity().getVelocity();
	}

}