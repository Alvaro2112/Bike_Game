package ch.epfl.cs107.play.game.tutorial;
import java.awt.event.KeyEvent;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.RevoluteConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;


public class ScaleGame implements Game{
	private Window window;
	private World world;
	
	private Entity block;
	private Entity plank;
	private Entity ball;
	private Entity baller;
	
	private ImageGraphics ballGraphics;
	private ImageGraphics block1;
	private ImageGraphics plank1;
	private ImageGraphics ball1;
	
	
public boolean begin(Window window, FileSystem filesystem) {

	this.window = window;
world = new World();
world.setGravity(new Vector(0.0f, -9.81f));

//creation du bloc

EntityBuilder blockbuilder = world.createEntityBuilder();
blockbuilder.setFixed(true);
blockbuilder.setPosition(new Vector(-5.0f,-1.0f));
block = blockbuilder.build();
PartBuilder Partblock = block.createPartBuilder();
Polygon polygonblock = new Polygon(
		new Vector(0.0f, 0.0f),
		new Vector(10.0f, .0f),
		new Vector(10.0f, 1.0f),
		new Vector(0.0f, 1.0f)
		) ;
Partblock.setShape(polygonblock);
Partblock.setFriction(1f);
Partblock.build();

block1 = new ImageGraphics("stone.broken.4.png",10,1);
block1.setParent(block);

// creation de la planche

EntityBuilder plankbuilder = world.createEntityBuilder();
plankbuilder.setFixed(false);
plankbuilder.setPosition(new Vector(-2.5f, 0.8f));

plank = plankbuilder.build();

PartBuilder Partplank = plank.createPartBuilder();
Polygon polygonplank = new Polygon(
		new Vector(0.0f, 0.0f),
		new Vector(5f, 0.0f),
		new Vector(5f, .2f),
		new Vector(0.0f, .2f)
	         ) ;
Partplank.setShape(polygonplank);
Partplank.setFriction(0.8f);
Partplank.build();

plank1 = new ImageGraphics("wood.3.png",5f,0.2f);
plank1.setParent(plank);

//creation de la balle
Circle circle1 = new Circle(0.6f);


EntityBuilder ballbuilder = world.createEntityBuilder(); 
ballbuilder.setFixed(false);
ballbuilder.setPosition(new Vector(0.5f,2.5f) );
ball = ballbuilder.build();
PartBuilder Ballbuilder = ball.createPartBuilder();
Ballbuilder.setShape(circle1);
Ballbuilder.build();  
 ballGraphics = new ImageGraphics("explosive.11.png", 2.0f*circle1.getRadius() , 2.0f *circle1.getRadius(),new Vector(0.5f,0.5f) ) ;

ballGraphics.setParent(ball);  



  RevoluteConstraintBuilder revoluteConstraintBuilder = 	world.createRevoluteConstraintBuilder() ;
	
	 revoluteConstraintBuilder.setFirstEntity(block) ;
	revoluteConstraintBuilder.setFirstAnchor(new Vector(block1.getWidth()/2, block1.getHeight()*7/4)) ;
	revoluteConstraintBuilder.setSecondEntity(plank) ;
	revoluteConstraintBuilder.setSecondAnchor(new Vector(plank1.getWidth()/2, plank1.getHeight()/2)) ;
	revoluteConstraintBuilder.setInternalCollision(true) ;
	revoluteConstraintBuilder.build() ; 

	
	
	
	
return true;
}
public void update(float deltaTime) {
	world.update(deltaTime) ;
	
	
	   ballGraphics.draw(window);
	   block1.draw(window) ;
       plank1.draw(window);
 

	window.setRelativeTransform(Transform.I.scaled(10.0f)) ;
	
	if (window.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) {
		ball.applyAngularForce(10.0f) ;}
	else if (window.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) {
		ball.applyAngularForce(-10.0f) ;
		}
    // The actual rendering will be done now, by the program loop
}
public void end() {}

}
