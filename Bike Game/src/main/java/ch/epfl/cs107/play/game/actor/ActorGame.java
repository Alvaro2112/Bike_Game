package ch.epfl.cs107.play.game.actor;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.bike.Pics;
import ch.epfl.cs107.play.game.actor.bike.bike;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RevoluteConstraintBuilder;
import ch.epfl.cs107.play.math.RopeConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public abstract class ActorGame implements Game  {

	protected ArrayList<Actor> actorlist = new ArrayList<Actor>();
	private World world;
	private Canvas canvas;
	private Window window;
	private Vector viewCenter;
	private Vector viewTarget;
	private Positionable viewCandidate;
	private static final float VIEW_TARGET_VELOCITY_COMPENSATION = 0.2f;
	private static final float VIEW_INTERPOLATION_RATION_PER_SECOND = 0.05f;
	private Vector shiftedViewCenter = Vector.ZERO;
	private float VIEW_SCALE = 12f;
	private boolean pause;
	private FileSystem filesystem; 

	public boolean begin(Window window, FileSystem fileSystem) {

		this.window = window;
		world = new World();
		world.setGravity(new Vector(0f, -9.81f));
		viewCenter = new Vector(0f, 0f);
		viewTarget = new Vector(0f, 0f);

		return true;
	}

	public void addActor(Actor a) {
		actorlist.add(a);
	}
	
	// erase the actor of index a
	public void removeActor(int a) {
		actorlist.remove(a);
	}

	public void setViewCandidate(Positionable candidat) {
		viewCandidate = candidat;
	}
	

	public Keyboard getKeyboard() {
		return window.getKeyboard();
	}

	public Canvas getCanvas() {
		return window;
	}
	

	public void update(float deltaTime) {
		if (getKeyboard().get(KeyEvent.VK_R).isDown()) {

			actorlist.clear();
			begin(window, filesystem);
		}

		world.update(deltaTime);

		for (Actor a : actorlist) {
			a.update(deltaTime);
			a.draw(this.window);
			

		}

		// drawing the actors

		// camera

		if (viewCandidate != null) {
			viewTarget = viewCandidate.getPosition()
					.add(viewCandidate.getVelocity().mul(VIEW_TARGET_VELOCITY_COMPENSATION));
		}

		// Interpolate with previous location pas sur pour le bail d'interpolation
		// peut etre à changer qui sait voir le ratio sur le doc
		float ratio = (float) Math.pow(VIEW_INTERPOLATION_RATION_PER_SECOND, deltaTime);
		viewCenter = viewCenter.mixed(viewTarget, 1.0f - ratio);
		// Compute new viewport
		Transform viewTransform = Transform.I.scaled(VIEW_SCALE).translated(viewCenter);
		this.window.setRelativeTransform(viewTransform);
	}

	public void end() {

	}
	

	public EntityBuilder createEB() {
		return world.createEntityBuilder();
	}
	
	public WheelConstraintBuilder Getconstraint() {
		
		return world.createWheelConstraintBuilder();
	}
public RopeConstraintBuilder getRope() {
	return world.createRopeConstraintBuilder();
}

public GameEntity getPayload() {
	// TODO Auto-generated method stub
	return (GameEntity)actorlist.get(0);
}
public GameEntity getPics() {
	return (GameEntity)actorlist.get(2);
}
public RevoluteConstraintBuilder getRevoluteConstraint() {

	return world.createRevoluteConstraintBuilder();
}

}

