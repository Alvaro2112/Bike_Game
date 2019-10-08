package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Canvas;

public class Terrain extends GameEntity implements Actor {

	Color Brown = new Color(139, 69, 19);
	Color Green = new Color(124, 252, 0);

	private ShapeGraphics Graphicsterrain;
	private ShapeGraphics Graphicsterrain1;
	private ShapeGraphics Graphicsterrain2;
	private ShapeGraphics Graphicsterrain3;
	private ShapeGraphics Graphicsterrain4;

	public Terrain(ActorGame game, boolean fixed, Vector position) {
		super(game, fixed, position);

		Polyline Terrainshape = new Polyline(-1000.0f, -1000.0f, -1000.0f, 0.0f, 0.0f, 0.0f, 3.0f, 1.0f, 8.0f, 1.0f,
				15.0f, 3.0f, 16.0f, 3.0f, 25.0f, 0.0f, 35.0f, -5.0f, 50.0f, -5.0f, 55.0f, -4.0f, 65.0f, 0.0f, 68f, 1f,
				73f, 1f, 80f, 3f, 81f, 3f, 90f, 0f, 100f, -5f, 115f, -5f, 120f, -4f, 130f, 0f);

		Graphicsterrain = new ShapeGraphics(Terrainshape, Brown, Green, 0.5f);
		Graphicsterrain.setParent(getEntity());
		getEntity().setPosition(position);

		PartBuilder PartTerrain = getEntity().createPartBuilder();
		PartTerrain.setShape(Terrainshape);
		PartTerrain.setFriction(0.7f);
		PartTerrain.build();

		Polyline Terrainshape1 = new Polyline(135f, -1000f, 135f, 0f, 180f, 0f, 210f, 5f, 210f, -1000f);

		Graphicsterrain1 = new ShapeGraphics(Terrainshape1, Brown, Green, 0.5f);
		Graphicsterrain1.setParent(getEntity());
		getEntity().setPosition(position);

		PartBuilder PartTerrain1 = getEntity().createPartBuilder();
		PartTerrain1.setShape(Terrainshape1);
		PartTerrain1.setFriction(0.7f);
		PartTerrain1.build();
		
		
		Polyline Terrainshape2 = new Polyline(215f, -1000f,215f, 5f, 220f,3F,222F,4F,224F,3F,226F,4F,228F,3F,230F,4F,232F,3F,232F,3F);

		Graphicsterrain2 = new ShapeGraphics(Terrainshape2, Brown, Green, 0.5f);
		Graphicsterrain2.setParent(getEntity());
		getEntity().setPosition(position);

		PartBuilder PartTerrain2 = getEntity().createPartBuilder();
		PartTerrain2.setShape(Terrainshape2);
		PartTerrain2.setFriction(0.7f);
		PartTerrain2.build();
		
		Polyline Terrainshape3 = new Polyline(232F,3F,232F,3F,280F,3F,280F,-1000F);

		Graphicsterrain3 = new ShapeGraphics(Terrainshape3, Brown, Green, 0.5f);
		Graphicsterrain3.setParent(getEntity());
		getEntity().setPosition(position);

		PartBuilder PartTerrain3 = getEntity().createPartBuilder();
		PartTerrain3.setShape(Terrainshape3);
		PartTerrain3.setFriction(0);
		PartTerrain3.build();
		
		Polyline Terrainshape4 = new Polyline(285F,-1000F,285F,3F,300F,3F,320F,11,340F,11f,340F,-1000);

		Graphicsterrain4 = new ShapeGraphics(Terrainshape4, Brown, Green, 0.5f);
		Graphicsterrain4.setParent(getEntity());
		getEntity().setPosition(position);

		PartBuilder PartTerrain4 = getEntity().createPartBuilder();
		PartTerrain4.setShape(Terrainshape4);
		PartTerrain4.setFriction(0.7f);
		PartTerrain4.build();
		
		
	}

	@Override
	public void draw(Canvas canvas) {
		Graphicsterrain.draw(canvas);
		Graphicsterrain1.draw(canvas);
		Graphicsterrain2.draw(canvas);
		Graphicsterrain3.draw(canvas);
		Graphicsterrain4.draw(canvas);

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
}