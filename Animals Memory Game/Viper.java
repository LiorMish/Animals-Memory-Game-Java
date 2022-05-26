public class Viper extends Reptiles{

	public Viper() {//Constructor
		super('V');
	}

	protected boolean IsMatch (Animals other) {//Check the Viper criteria to match
		if (other instanceof Viper || other instanceof Kangaroo)
			return true;
		return false;
	}
}
