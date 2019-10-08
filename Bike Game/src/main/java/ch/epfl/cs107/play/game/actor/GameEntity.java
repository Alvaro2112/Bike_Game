package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public abstract class GameEntity {

	private ActorGame game;
	private Entity entity;
	
	public GameEntity(ActorGame game, boolean fixed, Vector position)  {
if (!(game instanceof ActorGame)||!(position instanceof Vector)) {
			
			throw new IllegalArgumentException();
		}

		this.game = game;
		if(game == null ) { throw new NullPointerException("the game can't be null");
		}
		EntityBuilder aBuilder = game.createEB();
		 
		 aBuilder.setFixed(fixed);
		 aBuilder.setPosition(position);
		 if(position == null) {throw new NullPointerException("the position can't be null");}
		 entity = aBuilder.build();
		 
		 
	}

	public GameEntity(ActorGame game, boolean fixed) throws NullPointerException{
 try { this.game =game;
		EntityBuilder aBuilder = game.createEB();
		 
		 aBuilder.setFixed(fixed);
		 entity = aBuilder.build();
		
 }catch(NullPointerException e) {
	 System.err.println("L'actor game est null");
	
 }
	}
	


	public void destroy() {
		entity.destroy();
	}

	protected Entity getEntity() {
		return entity;
	}

	protected ActorGame getActorGame() {
		return game;
	}
	public void applyAngularForce(float a) {
		entity.applyAngularForce(a);
		
	}
	
	public float getAngularVelocity() {
		return entity.getAngularVelocity();
	}
	
	public Entity getPayloadEntity() {
		return game.getPayload().entity;
	}
	
	
	
	public void saut(float hauteur) {
		getPayloadEntity().applyImpulse(new Vector(0, hauteur), getPayloadEntity().getPosition());
	}
	public Entity getPicsEntity() {
		return game.getPics().entity;
	}
	public void force(Vector force, Entity entity ) {
		getPayloadEntity().applyImpulse(force, entity.getPosition());
	}

}

