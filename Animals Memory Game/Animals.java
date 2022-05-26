abstract class  Animals {

	public int age ;// the age of the animal
	public char gender;// the gender of the animal
	private int random=(int)(2*Math.random());//helper variable to the gender
	public char name;// the name of the animal
	public int row , column;//the place of the animal in the matrix
	public boolean found;// shows if this animal is already matched 
	
	protected Animals(char name) {//The animals constructor
		age = (int)(120*Math.random()+1);
		gender = RandomGender();
		this.name=name;
		row = 0;
		column = 0;
		this.found=false;
	}

	private char RandomGender() {// give a random gender to the animals
		if (random == 0)
			return 'M';
		return 'F';
	}

	protected boolean IsMatch(Animals other){//check if one of the animals that chose is a kangaroo (Joker Card)
		if(other instanceof Kangaroo)
			return true;
		return false;
	}

}