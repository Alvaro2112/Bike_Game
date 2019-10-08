package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;






public class BikeGame extends ActorGame implements Game {
	
private bike bike;
private Puit puit2;
private Terrain terrain1;
 private Bascule bascule1;
 private Bascule bascule2;
 private Coin coin;
 private Coin coin1;
 private Coin coin2;
 private Coin coin3;
 private Coin coin4 ;
 private Coin coin5;
 private Coin coin6;
 private Coin coin7;
 private Coin coin8;
 private Coin coin9;
 private Coin coin10;
 private Coin coin11;
 private Coin coin12;
 private Coin coin13;

 
 
 private Flag flag1;
 private Pendule pendule1;
 private Boite boite1;
 private Boite boite2;
 private Boite boite3;
 private Boule boule1;
 private Boule boule2;
 private Boule boule3;
 private Tremplin tremplin;
 private Accelerateur accelerateur;
 
 public boolean begin(Window window, FileSystem filesystem) {

		super.begin(window, filesystem);

		 bike = new bike(this, false, 0.5f, new Vector(0, 5f), java.awt.Color.BLUE);

		super.addActor(bike);

		 terrain1 = new Terrain(this, true, Vector.ZERO);
		super.addActor(terrain1);


 accelerateur = new Accelerateur(this, true , new Vector(297,3f));
 super.addActor(accelerateur);

		
 tremplin = new Tremplin(this, true ,new Vector(3f,0f));
 super.addActor(tremplin);
 
  puit2 = new Puit( this, true, new Vector(277f, 3f) );
	super.addActor(puit2);
		 

	   bascule1 = new Bascule(this, new Vector(131f, -.2f), 3f, true);
super.addActor(bascule1);
		
	 bascule2 = new Bascule(this, new Vector(210.5f, 4.8f), 4f, true);
	super.addActor(bascule2);

		 coin = new Coin(this, true, new Vector(6f, 6f));
super.addActor(coin);

		 coin1 = new Coin(this, true, new Vector(138.0f, 1f));
	super.addActor(coin1);
	
	 coin2 = new Coin(this, true, new Vector(10f, 3f));
	super.addActor(coin2);
	 coin3 = new Coin(this, true, new Vector(19f, 3f));
	super.addActor(coin3);
	 coin4 = new Coin(this, true, new Vector(36f, -2f));
	super.addActor(coin4);
	 coin5 = new Coin(this, true, new Vector(48f, -2f));
	super.addActor(coin5);
	 coin6 = new Coin(this, true, new Vector(60f, 0f));
	super.addActor(coin6);
	 coin7 = new Coin(this, true, new Vector(85, 3f));
	super.addActor(coin7);
	 coin8 = new Coin(this, true, new Vector(75f, 3f));
	super.addActor(coin8);
	 coin9 = new Coin(this, true, new Vector(150f, 2f));
	super.addActor(coin9);
	 coin10 = new Coin(this, true, new Vector(190f, 4f));
	super.addActor(coin10);
 coin11 = new Coin(this, true, new Vector(221f, 5f));
	super.addActor(coin11);
	 coin12 = new Coin(this, true, new Vector(260f, 5f));
	super.addActor(coin12);
	 coin13 = new Coin(this, true, new Vector(310f, 9f));
	super.addActor(coin13);


		

		 pendule1 = new Pendule(this, false);
	boite1 = new Boite(this, true, new Vector(6,9));
	super.addActor(boite1);
	boule1 = new Boule(this,false, new Vector(8,9));
	super.addActor(boule1);
	pendule1.attachPendulum(boite1.boiteEntity(),  boule1.bouleEntity(), boite1.boitegetWidth()/2, boite1.boitegetHeight()/2,boule1.bouleEntity().getPosition());
	
	

	boite3 = new Boite(this, false, new Vector(237,9));

	super.addActor(boite3);

		 flag1 = new Flag(this, java.awt.Color.WHITE, 1f, true, new Vector(321f, 11f));

	super.addActor(flag1);

		setViewCandidate(bike);

		return true;

	}

	public void update(float DeltaTime) {

		super.update(DeltaTime);

	}

	public void end() {

		for (int a = 0; a < actorlist.size(); ++a) {

			super.removeActor(a);

		}
		// TODO Auto-generated method stub
		super.end();
	}

}