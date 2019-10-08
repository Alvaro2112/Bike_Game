package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;
import java.awt.event.KeyEvent;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class bike extends GameEntity implements Actor {
	private Wheel LeftWheel;
	private Wheel RightWheel;
	
	//dessin du stickman
	private ShapeGraphics Head;
	private ShapeGraphics Body;
	private ShapeGraphics LeftLeggraphics;
	private ShapeGraphics RightLeggraphics;
	private ShapeGraphics LeftKneegraphics;
	private ShapeGraphics RightKneegraphics;
	private ShapeGraphics RightArm;
	private ShapeGraphics LeftArm;
	private boolean Switch = false;
	private boolean hit;
	private TextGraphics message;
	private ImageGraphics fond;
	private final float MAX_WHEEL_SPEED = 20;
	private boolean win;
	private TextGraphics message1;
	
	//initialisation du stickman
	private Vector BotRightLeg = new Vector(0f, 0.7f);
	private Vector BotLeftLeg = new Vector(0f, 0.7f);
	private Vector BotRightKnee = new Vector(0.2f, 0f);
	private Vector BotLeftKnee = new Vector(-0.2f, 0f);
	private Vector RightTopLeg = new Vector(-0.5f, 1f);
	private Vector LeftTopLeg = new Vector(-0.5f, 1f);
	
	private Vector RightBotArmLocation = new Vector(0.4f, 1.1f);
	private Vector LeftBotArmLocation = new Vector(0.4f, 1.1f);
	private Vector RightShoulderLocation = new Vector(-0.1f, 1.55f);
	private Vector LeftShoulderLocation = new Vector(-0.1f, 1.55f);
	private double angle = 0;
	
	private Vector DebutBotRightLeg = new Vector(0f, 0.7f);
	private Vector DebutBotLeftLeg = new Vector(0f, 0.7f);
	private Vector DebutBotRightKnee = new Vector(0.2f, 0f);
	private Vector DebutBotLeftKnee = new Vector(-0.2f, 0f);
	
	
	
	private int Score = 0;
	final short CATEGORY_PLAYER = 0x0001;
	private boolean groundcontact = false;
	
	private Polyline RightLeg1;
	private Polyline LeftLeg1;
	private Polyline LeftKnee1;
	private Polyline RightKnee1;
	
	

	public bike(ActorGame game, boolean fixed, float radius, Vector position, Color colour) {

		super(game, fixed, position);

		fond = new ImageGraphics("/Projet 2/src/main/resources/layer-1.png", 1, 1, new Vector(0.5f, 0.5f));
		fond.setParent(getActorGame().getCanvas());
		getEntity().setPosition(position);

		Polygon polygon = new Polygon(0.0f, 0.5f, 0.5f, 1.0f, 0.0f, 2.0f, -0.5f, 1.0f);
		PartBuilder partBuilder1 = getEntity().createPartBuilder();
		partBuilder1.setShape(polygon);
		partBuilder1.build();

	
		RightLeg1 = new Polyline(getRightTopLegLocation(), getRightBotLegLocation());
		RightLeggraphics = new ShapeGraphics(RightLeg1, java.awt.Color.BLUE, java.awt.Color.BLUE, 0.1f);
		RightLeggraphics.setParent(getEntity());
		
		LeftLeg1 =  new Polyline(getLeftTopLegLocation(), getLeftBotLegLocation());
		LeftLeggraphics = new ShapeGraphics(LeftLeg1, java.awt.Color.BLUE, java.awt.Color.BLUE, 0.1f);
		LeftLeggraphics.setParent(getEntity());
	
		LeftKnee1 = new Polyline(getLeftBotLegLocation(), getBotLeftKneeLocation());
		LeftKneegraphics = new ShapeGraphics(LeftKnee1, java.awt.Color.BLUE, java.awt.Color.BLUE, 0.1f);
		LeftKneegraphics.setParent(getEntity());

		RightKnee1 = new Polyline(getRightBotLegLocation(), getBotRightKneeLocation());
		RightKneegraphics = new ShapeGraphics(RightKnee1, java.awt.Color.BLUE, java.awt.Color.BLUE, 0.1f);
		RightKneegraphics.setParent(getEntity());

		LeftWheel = new Wheel(game, fixed, radius, position.add(new Vector(-1f, 0f)), java.awt.Color.BLUE);
		LeftWheel.attach(getEntity(), new Vector(-1f, 0f), new Vector(-0.5f, -1f));
	
		RightWheel = new Wheel(game, fixed, radius, position.add(new Vector(1f, 0f)), java.awt.Color.RED);
		RightWheel.attach(getEntity(), new Vector(1f, 0f), new Vector(0.5f, -1f));

		Circle head = new Circle(0.2f, getHead());
		partBuilder1.setShape(head);
		Head = new ShapeGraphics(head, colour, colour, 0f);
		Head.setParent(getEntity());

		
		Polyline body = new Polyline(getBodyBotLocation(), getBodyTopLocation());
	    partBuilder1.setShape(body);
       	Body = new ShapeGraphics(body, colour, colour, 0.1f);
	    Body.setParent(getEntity());

	    
		Polyline Rightarm = new Polyline(getRightShoulderLocation(), getRightBotArmLocation());
		RightArm = new ShapeGraphics(Rightarm, java.awt.Color.BLUE, java.awt.Color.BLUE, 0.1f);
		RightArm.setParent(getEntity());

		
		Polyline Leftarm = new Polyline(getLeftShoulderLocation(), getLeftBotArmLocation());
		LeftArm = new ShapeGraphics(Leftarm, java.awt.Color.BLUE, java.awt.Color.BLUE, 0.1f);
		LeftArm.setParent(getEntity());

		ContactListener listener = new ContactListener() {
			@Override
			public void beginContact(Contact contact) {

				if (contact.getOther().isGhost()
						|| contact.getOther() == LeftWheel.wheelEntity(LeftWheel).getParts().get(0)
						|| contact.getOther() == RightWheel.wheelEntity(RightWheel).getParts().get(0)

				) {
					return;
				}

				// si contact avec les roues :
				else {
					hit = true;
				}
			}

			@Override
			public void endContact(Contact contact) {
			}
		};

		
		getEntity().addContactListener(listener);

		ContactListener listener3 = new ContactListener() {
			@Override
			public void beginContact(Contact contact) {

				if ((contact.getOwner().getFriction() == 0.5F)

				) {
					groundcontact = true;
				}

				// si contact avec les roues :
				else  {

					groundcontact = false;				}
			}

			@Override
			public void endContact(Contact contact) {
			}
		};

		RightWheel.wheelEntity(RightWheel).addContactListener(listener3);
		LeftWheel.wheelEntity(LeftWheel).addContactListener(listener3);

		ContactListener listener1 = new ContactListener() {
			@Override
			public void beginContact(Contact contact) {

				if (contact.getOther().isGhost() && contact.getOther().getFriction() == 0.100f

				) {
					win = true;
				}

				// si contact avec les roues :
				else {

					return;
				}
			}

			@Override
			public void endContact(Contact contact) {
			}
		};

		getEntity().addContactListener(listener1);
		ContactListener listener2 = new ContactListener() {
			@Override
			public void beginContact(Contact contact) {
	if (contact.getOther().isGhost() && contact.getOther().getFriction() == 0.111f)
	{	Score++;
		contact.getOther().setFriction(0f);
				}

				// si contact avec les roues :
				else {
					return;
				}
	                                 }

			@Override
			public void endContact(Contact contact) {
			}
		};
		getEntity().addContactListener(listener2);

	}

	public void update(float deltatime) {

		if (!hit) {
			if(!win)
			message1 = new TextGraphics("Score:" + Score, 0.1f, java.awt.Color.RED, java.awt.Color.RED, 0.02f, true,
					false, new Vector(0.5f, 0.5f), 1.0f, 50.0f);
			message1.setParent(getActorGame().getCanvas());

			message1.setRelativeTransform(Transform.I.translated(-.7f, -0.5f));

			message1.draw(getActorGame().getCanvas());
			
			message1 = new TextGraphics("Speed:"+Math.abs(Math.round((getEntity().getVelocity().getX()))), 0.1f, java.awt.Color.RED, java.awt.Color.RED, 0.02f, true,
					false, new Vector(0.5f, 0.5f), 1.0f, 50.0f);
			message1.setParent(getActorGame().getCanvas());

			message1.setRelativeTransform(Transform.I.translated(-.7f, -0.7f));

			message1.draw(getActorGame().getCanvas());
			}
			
			if(!Switch) {angle = LeftWheel.wheelEntity(LeftWheel).getAngularPosition();}
			if(Switch) {angle = RightWheel.wheelEntity(RightWheel).getAngularPosition();}
			 
			setBotLeftKnee(new Vector((float)Math.cos(angle)*(0.15f) + DebutBotLeftKnee.getX(), 
					(float)Math.sin(angle)*0.15f + DebutBotLeftKnee.getY()));
			setBotRightKnee(new Vector((float)Math.cos(angle)*(0.15f) + DebutBotRightKnee.getX(), 
					(float)Math.sin(angle)*0.15f + DebutBotRightKnee.getY()));
			setRightBotLeg(new Vector((float)Math.cos(angle )*(0.15f) + DebutBotRightLeg.getX(), 
					(float)Math.sin(angle )*0.15f + DebutBotRightLeg.getY()));
			setLeftBotLeg(new Vector((float)Math.cos(angle)*(0.15f) + DebutBotLeftLeg.getX(), 
					(float)Math.sin(angle)*0.15f + DebutBotLeftLeg.getY()));	
		
			// polylin des leg qui bougent
RightLeg1 = new Polyline(getRightTopLegLocation(), getRightBotLegLocation());
LeftLeg1 =  new Polyline(getLeftTopLegLocation(), getLeftBotLegLocation());
RightKnee1 = new Polyline(getRightBotLegLocation(), getBotRightKneeLocation());
LeftKnee1 = new Polyline(getLeftBotLegLocation(), getBotLeftKneeLocation());

RightLeggraphics.setShape(RightLeg1);
LeftLeggraphics.setShape(LeftLeg1);
RightKneegraphics.setShape(RightKnee1);
LeftKneegraphics.setShape(LeftKnee1);

LeftWheel.relax();
RightWheel.relax();

			if (win) {



PartBuilder partBuilder1 = getEntity().createPartBuilder();

				Polyline Rightarm = new Polyline(getRightShoulderLocation(), getRightBotArmLocation());

				partBuilder1.setShape(Rightarm);

				RightArm = new ShapeGraphics(Rightarm, java.awt.Color.BLUE, java.awt.Color.BLUE, 0.1f);

				RightArm.setParent(getEntity());

				Polyline Leftarm = new Polyline(getLeftShoulderLocation(), getLeftBotArmLocation());

				partBuilder1.setShape(Leftarm);

				LeftArm = new ShapeGraphics(Leftarm, java.awt.Color.BLUE, java.awt.Color.BLUE, 0.1f);

				LeftArm.setParent(getEntity());

				if (Switch) {
					setRightBotArmLocation(new Vector(0.6f, 2.3f));
					setLeftBotArmLocation(new Vector(.8f, 1.8f));
					LeftArm.setRelativeTransform(Transform.I.scaled(-1f, 1f));
					RightArm.setRelativeTransform(Transform.I.scaled(-1f, 1f));

				}

				else {
					setRightBotArmLocation(new Vector(0.6f, 2.3f));
					setLeftBotArmLocation(new Vector(.8f, 1.8f));
				}

			

				message1 = new TextGraphics("YOU WIN!" + "Score" + Score, 0.3f, java.awt.Color.RED, java.awt.Color.RED, 0.02f,
						true, false, new Vector(0.5f, 0.5f), 1.0f, 50.0f);
				message1.setParent(getActorGame().getCanvas());
				message1.setRelativeTransform(Transform.I.translated(0.0f, -1.0f));
				message1.draw(getActorGame().getCanvas());

				do {
					LeftWheel.wheelEntity(LeftWheel).applyAngularForce(0 - LeftWheel.wheelEntity(LeftWheel).getAngularVelocity());
					RightWheel.wheelEntity(RightWheel).applyAngularForce(0 - RightWheel.wheelEntity(RightWheel).getAngularVelocity());
				} while (LeftWheel.wheelEntity(LeftWheel).getAngularVelocity() > 5
						&& RightWheel.wheelEntity(RightWheel).getAngularVelocity() > 5);
				
			}

			if (getActorGame().getKeyboard().get(KeyEvent.VK_SPACE).isPressed()) {

				SwitchSense(Switch);

				Switch = !Switch;

			}

			if (getActorGame().getKeyboard().get(KeyEvent.VK_DOWN).isDown()) {

				LeftWheel.wheelEntity(LeftWheel).applyAngularForce(0 - LeftWheel.wheelEntity(LeftWheel).getAngularVelocity());
				RightWheel.wheelEntity(RightWheel).applyAngularForce(0 - RightWheel.wheelEntity(RightWheel).getAngularVelocity());

			}

			if (getActorGame().getKeyboard().get(KeyEvent.VK_UP).isDown() && getSpeed() < MAX_WHEEL_SPEED) {

				if (!Switch) {
					LeftWheel.wheelEntity(LeftWheel).applyAngularForce(-10f);
				}

				if (Switch) {
					RightWheel.wheelEntity(RightWheel).applyAngularForce(10f);
				}
			}
			
			if (getActorGame().getKeyboard().get(KeyEvent.VK_UP).isUp()) {

				LeftWheel.wheelEntity(LeftWheel).applyAngularForce(0 - LeftWheel.wheelEntity(LeftWheel).getAngularVelocity()/5);
				RightWheel.wheelEntity(RightWheel).applyAngularForce(0 - RightWheel.wheelEntity(RightWheel).getAngularVelocity()/5);
			}
			
			if (getActorGame().getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) {
				if (Switch)
					getEntity().applyAngularForce(20f);
				else
					getEntity().applyAngularForce(-20f);
			}
			if (getActorGame().getKeyboard().get(KeyEvent.VK_A).isDown()) {

				getEntity().setPosition(new Vector(getPosition().x,getPosition().y+0.05f));
				}

			if (getActorGame().getKeyboard().get(KeyEvent.VK_LEFT).isDown()) {
				if (Switch)
					getEntity().applyAngularForce(-20f);
				else
					getEntity().applyAngularForce(20f);
			}

		


		else if (hit)

		{

			Destroy();
			message = new TextGraphics("GAME OVER", 0.3f, java.awt.Color.RED, java.awt.Color.RED, 0.02f, true, false,
					new Vector(0.5f, 0.5f), 1.0f, 70.0f);
			message.setParent(getActorGame().getCanvas());
			message.setRelativeTransform(Transform.I.translated(0.0f, -1.0f));
			message.draw(getActorGame().getCanvas());
			
			message1 = new TextGraphics("Press r to restart", 0.3f, java.awt.Color.RED, java.awt.Color.RED, 0.02f, true, false,
					new Vector(0.5f, 0.0f), 1.0f, 70.0f);
			message1.setParent(getActorGame().getCanvas());
			message1.setRelativeTransform(Transform.I.translated(0.0f, -1.0f));
			message1.draw(getActorGame().getCanvas());

		}

	}

	private void SwitchSense(boolean Switch) {

		if (Switch) {

			Head.setRelativeTransform(Transform.I);
			Body.setRelativeTransform(Transform.I);
			RightArm.setRelativeTransform(Transform.I);
			LeftArm.setRelativeTransform(Transform.I);
			LeftLeggraphics.setRelativeTransform(Transform.I);
			RightLeggraphics.setRelativeTransform(Transform.I);
			LeftKneegraphics.setRelativeTransform(Transform.I);
			RightKneegraphics.setRelativeTransform(Transform.I);

		}

		else {

			Head.setRelativeTransform(Transform.I.scaled(-1f, 1f));
			Body.setRelativeTransform(Transform.I.scaled(-1f, 1f));
			RightArm.setRelativeTransform(Transform.I.scaled(-1f, 1f));
			LeftArm.setRelativeTransform(Transform.I.scaled(-1f, 1f));
			LeftLeggraphics.setRelativeTransform(Transform.I.scaled(-1f, 1f));
			RightLeggraphics.setRelativeTransform(Transform.I.scaled(-1f, 1f));
			LeftKneegraphics.setRelativeTransform(Transform.I.scaled(-1f, 1f));
			RightKneegraphics.setRelativeTransform(Transform.I.scaled(-1f, 1f));

		}

	}

	public float getSpeed() {
		return getEntity().getAngularVelocity() - LeftWheel.wheelEntity(LeftWheel).getAngularVelocity();
	}

	private Vector getHead() {
		return new Vector(0.0f, 1.75f);
	}

	private Vector getBodyTopLocation() {

		return new Vector(0f, 1.75f);

	}
	private void setBotLeftKnee(Vector v) {
		BotLeftKnee = v;
	}
	private void setBotRightKnee(Vector v) {
		BotRightKnee =v;
	}
	private void setRightBotLeg(Vector v) {
		BotRightLeg = v;
	}
	private void setLeftBotLeg(Vector v) {
		BotLeftLeg = v;
	}
	
	public void relax() {
		getActorGame().Getconstraint().setMotorEnabled(false);
	}

	private Vector getBodyBotLocation() {
		return new Vector(-0.5f, 1f);
	}

	private Vector getRightShoulderLocation() {

		return new Vector(-0.1f, 1.55f);

	}

	private Vector getLeftShoulderLocation() {

		return new Vector(-0.1f, 1.55f);

	}

	private Vector getRightBotArmLocation() {

		return RightBotArmLocation;

	}

	private Vector getLeftBotArmLocation() {

		return LeftBotArmLocation;

	}

	private Vector getRightTopLegLocation() {
		return RightTopLeg;
	}

	private Vector getLeftTopLegLocation() {
		return LeftTopLeg;
	}

	private Vector getRightBotLegLocation() {
		return BotRightLeg;
	}

	private Vector getLeftBotLegLocation() {
		return BotLeftLeg;
	}

	

	

	private Vector getBotLeftKneeLocation() {
		return BotLeftKnee;
	}

	private Vector getBotRightKneeLocation() {
		return BotRightKnee;
	}

	private Vector setRightBotArmLocation(Vector NewPosition) {
		return RightBotArmLocation = NewPosition;
	}
	private Vector setLeftBotArmLocation(Vector NewPosition) {
		return LeftBotArmLocation = NewPosition;
	}



	@Override
	public void draw(Canvas canvas) {

		LeftWheel.draw(canvas);
		RightWheel.draw(canvas);
		Head.draw(canvas);
		Body.draw(canvas);
		RightArm.draw(canvas);
		LeftArm.draw(canvas);
		RightLeggraphics.draw(canvas);
		LeftLeggraphics.draw(canvas);
		RightKneegraphics.draw(canvas);
		LeftKneegraphics.draw(canvas);
		fond.draw(canvas);
		

	}

	@Override
	public Transform getTransform() {
		return getEntity().getTransform();
	}

	@Override
	public Vector getVelocity() {
		return getEntity().getVelocity();
	}

	public void Destroy() {

		getEntity().destroy();
	/*	LeftWheel.WheelDestroy();
		RightWheel.WheelDestroy();
		RightWheel.getGraphics().setAlpha(0);
		LeftWheel.getGraphics().setAlpha(0); */
		Body.setShape(null);
		RightArm.setShape(null);
		LeftArm.setShape(null);
		LeftLeggraphics.setShape(null);
		RightLeggraphics.setShape(null);
		LeftKneegraphics.setShape(null);
		RightKneegraphics.setShape(null);
		Head.setShape(null);

	}

}