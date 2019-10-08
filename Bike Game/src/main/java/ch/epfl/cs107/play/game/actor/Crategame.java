package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.RopeConstraintBuilder;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class Crategame extends ActorGame implements Game{

	public boolean begin(Window window, FileSystem fs) {
		super.begin(window, fs);
		Crate box1 = new Crate(this, false, new Vector(0.0f, 5f));
		Crate box2 = new Crate(this, false, new Vector(0.2f, 7f));
		Crate box3 = new Crate(this, false, new Vector(2.0f, 6f));

  		
  	
		super.addActor(box1);
		super.addActor(box2);
		super.addActor(box3);
		return true;
	}

	public void updtae(float DeltaTime) {

		super.update(DeltaTime);
	}

	public void end() {
		// TODO Auto-generated method stub
		super.end();
	}
}
