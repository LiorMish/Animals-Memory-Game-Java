abstract class Reptiles extends Animals {

	protected Reptiles(char name) {//Constructor
		super(name);
	}

	protected boolean IsMatch(Animals other){//Check the Reptiles criteria to match
		if (other instanceof Reptiles && !(other instanceof Viper) ||  super.IsMatch(other)) 
			return true;
		return false;
	}
}


