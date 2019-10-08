package ch.epfl.cs107.play.game.tutorial;
import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;


public class ContactGame implements Game {


private Window window;
private World world;

private Entity ball;
private ShapeGraphics ballGraphics;

private Entity block;
private ImageGraphics block1;

//creation du listener
private BasicContactListener contactListener ;


public boolean begin(Window window, FileSystem fileSsytem) {
this.window = window;
world = new World();
world.setGravity(new Vector(0.0f, -9.81f));

//creation de la balle
EntityBuilder BallBuilder = world.createEntityBuilder();

BallBuilder.setFixed(false);
Circle circle = new Circle(0.5f , new Vector(0.0f,2.0f)) ;
ballGraphics = new ShapeGraphics(circle , Color.BLUE, Color.RED,0.1f, 1.f, 0f) ;
  ball = BallBuilder.build();
  
  PartBuilder ballbuilder = ball.createPartBuilder();
ballbuilder.setShape(circle);
ballbuilder.build();  
  
  ballGraphics.setParent(ball);
// mettre la balle en listener
  contactListener = new BasicContactListener() ;
  ball.addContactListener(contactListener) ;
 
  
 // creation du bloc 
  EntityBuilder entityBuilder1 = world.createEntityBuilder();
  entityBuilder1.setPosition(new Vector(-5.0f, -1.0f));
  entityBuilder1.setFixed(true);
  block = entityBuilder1.build();
  PartBuilder partBuilder = block.createPartBuilder() ;
  Polygon polygon = new Polygon(
  		new Vector(0.0f, 0.0f),
  		new Vector(10.0f, 0.0f),
  		new Vector(10.0f, 1.0f),
  		new Vector(.0f, 1.0f)
  		) ;
  		partBuilder.setShape(polygon) ;
  		// Finally , do not forget the following line.
  		
  		partBuilder.build() ;
	   
  		 block1 =  new ImageGraphics("glass.2.png",10,1);
  		  block1.setParent(block);
  				
	   return true;  }


public void update(float deltaTime) {
	world.update(deltaTime) ;
 	block1.draw(window) ;
    ballGraphics.draw(window);
    

	window.setRelativeTransform(Transform.I.scaled(10.0f)) ;

	// détecte les collisions
	int numberOfCollisions = contactListener.getEntities().size() ;
	if (numberOfCollisions > 0){
	ballGraphics.setFillColor(Color.RED) ;
	}
	


    // The actual rendering will be done now, by the program loop
}


// This event is raised after game ends, to release additional resources
@Override
public void end() {
    // Empty on purpose, no cleanup required yet
}

}

