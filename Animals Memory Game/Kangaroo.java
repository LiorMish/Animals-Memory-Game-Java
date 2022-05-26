public class Kangaroo extends Mammals {

	public Kangaroo() {//Constructor
		super('K');
	}
	protected boolean IsMatch(Animals other){//Check the Kangaroo criteria to match
		other.found = true;
		return true;
	}
}
