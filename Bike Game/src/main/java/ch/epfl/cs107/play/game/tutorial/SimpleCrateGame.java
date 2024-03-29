package ch.epfl.cs107.play.game.tutorial;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Polygon;

public class SimpleCrateGame implements Game {
	 // Store context
    private Window window;
    
    // We need our physics engine
    private World world;

    // And we need to keep references on our game objects
    private Entity block;
    private Entity crate;
    private ImageGraphics block1;
    private ImageGraphics crate1;
    
    
    public boolean begin(Window window, FileSystem fileSsytem) {
   this.window = window;
   world = new World();
   world.setGravity(new Vector(0.0f, -9.81f));
   
    EntityBuilder entityBuilder1 = world.createEntityBuilder();
    entityBuilder1.setPosition(new Vector(0.8f, 0.5f));
    entityBuilder1.setFixed(true);
    block = entityBuilder1.build();
    PartBuilder partBuilder = block.createPartBuilder() ;
    
    Polygon polygon = new Polygon(
    		new Vector(0.0f, 0.0f),
    		new Vector(1.0f, .0f),
    		new Vector(1.0f, 1.0f),
    		new Vector(0.0f, 1.0f)
    		) ;
    		partBuilder.setShape(polygon) ;
    		partBuilder.setFriction(0.5f) ;
    		partBuilder.build() ;
    		
    		
    EntityBuilder entityBuilder2 = world.createEntityBuilder();
    entityBuilder2.setPosition(new Vector(0.2f,4.0f));
    crate = entityBuilder2.build();
    	PartBuilder partBuilder2 = crate.createPartBuilder() ;
    		partBuilder2.setShape(polygon) ;
    		partBuilder2.build() ;
    
   
    block1 =  new ImageGraphics("stone.broken.4.png",1,1);
    block1.setParent(block);
    block1.setAlpha(1.0f);
    
    
    crate1 = new ImageGraphics("box.4.png",1,1);
    crate1.setParent(crate);
    crate1.setAlpha(1.0f);
    
    
return true;
    }
    
    public void update(float deltaTime) {
    	world.update(deltaTime) ;
    	// We can render our scene now,
    	block1.draw(window) ;
   crate1.draw(window);
   
    	window.setRelativeTransform(Transform.I.scaled(5.0f)) ;
   
   
    	

    
        // The actual rendering will be done now, by the program loop
    }
    public void end() {}
}
