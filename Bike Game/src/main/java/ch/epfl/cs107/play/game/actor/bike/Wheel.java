package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraint;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.window.Canvas;

public class Wheel extends GameEntity implements Actor {
	
private ShapeGraphics Wheelsgraphics;
private WheelConstraint wheelconstraint;
private boolean touche;
private WheelConstraintBuilder constraintBuilder1;
private static BasicContactListener contact;
	

	public Wheel(ActorGame game, boolean fixed, float radius, Vector position,Color colour) {
		
		super(game, fixed, position);
if (!(colour instanceof Color)|| radius < 0) {
			
			throw new IllegalArgumentException();
		
		}
		
		getEntity().setPosition(position);

		Circle shape = new Circle(radius);

		PartBuilder partBuilder1 = getEntity().createPartBuilder();

		partBuilder1.setShape(shape);
		partBuilder1.setFriction(0.5f);

		partBuilder1.build();

		Wheelsgraphics = new ShapeGraphics(shape, java.awt.Color.BLUE, java.awt.Color.YELLOW, radius);

		Wheelsgraphics.setParent(getEntity());
		
		 contact = new BasicContactListener();
			getEntity().addContactListener(contact);	

	}
	
	public void update(float deltatime) {
	if(contact.getEntities().contains(getPicsEntity())) {
		touche =true;
	}
		}
	
	
	public void power(float speed) {
		getActorGame().Getconstraint().setMotorSpeed(speed);
		
	}
	
	// disables the motor
	public void relax() {
		getActorGame().Getconstraint().setMotorEnabled(false);	
	}
	
	
	// destroys the constraint
	public  void detach() { 
	  wheelconstraint.destroy();
	  
	}
	
	protected Entity wheelEntity(Wheel w) {
		return w.getEntity();
	}
	
public void attach(Entity Bike,Vector anchor,Vector axis) {
	
	
	 constraintBuilder1 = getActorGame().Getconstraint();

	constraintBuilder1.setFirstEntity(Bike);
	constraintBuilder1.setFirstAnchor(anchor);
	constraintBuilder1.setSecondEntity(getEntity());
	constraintBuilder1.setSecondAnchor(Vector.ZERO);
	constraintBuilder1.setAxis(axis);
	constraintBuilder1.setFrequency(3.0f);
	constraintBuilder1.setDamping(0.5f);
	constraintBuilder1.setMotorMaxTorque(10.0f);
	constraintBuilder1.setInternalCollision(true);
	constraintBuilder1.build();
	wheelconstraint = constraintBuilder1.build();
	
}

	@Override
	public void draw(Canvas canvas) {
		Wheelsgraphics.draw(canvas);
		
	}
	public Vector getPosition() {
		return getEntity().getPosition();	
	}
	
	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return getEntity().getTransform();
	}

	@Override
	public Vector getVelocity() {
		
		return getEntity().getVelocity();
	}
	
	public boolean getTouche() {
		return touche;
	}

		


	

	
	

}
