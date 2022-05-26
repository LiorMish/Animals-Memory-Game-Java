public class Denis extends Fish {

	public Denis() {//Constructor
		super ('D');

	}
	protected boolean IsMatch(Animals other){//Check the Denis criteria to match and mark it if yes
		if (other instanceof Denis ) {
			this.found = true;
			(other).found = true;
			return true;
		}
		if (super.IsMatch(other)) {
			this.found = true;
			return true;
		}
		return false;
	}
}