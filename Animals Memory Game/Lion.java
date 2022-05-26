public class Lion extends Mammals {

	public Lion() {//Constructor
		super('L');
	}

	protected boolean IsMatch (Animals other) {//Check the Lion criteria to match
		if (other instanceof Lion || other instanceof Kangaroo)
			return true;
		return false;
	}

}
