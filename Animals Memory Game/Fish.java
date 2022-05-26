abstract class Fish extends Animals {

	protected Fish(char name) { //Constructor
		super(name);
	}
	
	protected boolean IsMatch(Animals other){//Check the Fish criteria to match
		return super.IsMatch(other);
	}

}
