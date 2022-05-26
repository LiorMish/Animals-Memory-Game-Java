abstract class Mammals extends Animals {
	
	protected Mammals(char name) { //Constructor
		super(name);
	}

	protected boolean IsMatch(Animals other){ //Check the Mammals criteria to match 
		if (other instanceof Mammals && !(other instanceof Lion) ||  super.IsMatch(other)) 
			return true;
		return false;
	}
}

