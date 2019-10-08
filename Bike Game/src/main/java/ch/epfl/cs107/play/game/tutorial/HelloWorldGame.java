package ch.epfl.cs107.play.game.tutorial;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

/**
 * Simple game, to show basic the basic architecture
 */
public class HelloWorldGame implements Game {

    // Store context
    private Window window;
    
    // We need our physics engine
    private World world;
    private ImageGraphics graphics;
    private ImageGraphics graph;
    // And we need to keep references on our game objects
    private Entity body;
    
    

    // This event is raised when game has just started
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        world = new World();
        world.setGravity(new Vector(0.0f, -9.81f));
        
        EntityBuilder entityBuilder = world.createEntityBuilder();
        entityBuilder.setFixed(true);
        entityBuilder.setPosition(new Vector(1.f, 1.5f));
        body = entityBuilder.build();
        // Store context
        this.window = window;
       
        graphics = new ImageGraphics("stone.broken.4.png", 1, 1) ;
             
          	  graphics.setAlpha(0.4f) ;
        	// Additionally , you can choose a depth when drawing
        	// Therefore , you could draw behind what you have already done
        	graphics.setDepth(10.0f) ;
        	graphics.setParent(body) ;
        	
               graph = new ImageGraphics("bow.png",1,1);
               graph.setParent(body);
               graph.setDepth(0.0f);
               graph.setAlpha(0.6f);
           	
          	
          	graphics.draw(window) ;
            graph.draw(window);
       // TO BE COMPLETED
        // Successfully initiated
        return true;
    }

    // This event is called at each frame
    @Override
    public void update(float deltaTime) {
    	world.update(deltaTime) ;
    	// We can render our scene now,
    	graphics.draw(window) ;
   graph.draw(window);
   
    	window.setRelativeTransform(Transform.I.scaled(5.0f)) ;
   
   
    	

    
        // The actual rendering will be done now, by the program loop
    }
   

    // This event is raised after game ends, to release additional resources
    @Override
    public void end() {
        // Empty on purpose, no cleanup required yet
    }
    
}

