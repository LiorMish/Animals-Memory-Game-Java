public class Pyrenees extends Fish {

	public Pyrenees() {//Constructor
		super('P');
	}
	
	protected boolean IsMatch(Animals other){//Check the Pyrenees criteria to match
		return other instanceof Shark  || super.IsMatch(other) || other instanceof Pyrenees;
	}

}