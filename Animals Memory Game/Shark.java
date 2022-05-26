public class Shark extends Fish {

	public Shark() {//Constructor
		super('S');	
	}
	
	protected boolean IsMatch(Animals other){//Check the Shark criteria to match
		return other instanceof Pyrenees  || super.IsMatch(other) || other instanceof Shark;
	}
}