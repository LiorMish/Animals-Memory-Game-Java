public class AnimalsMatrix {

	public Animals [][] AnimalsKingdom = new Animals [5][4];// Animals board matrix

	public void CreateAnimalsMatrix(int [][] BoardMatrix) {// initialize the Animals board from the Integer board
		for (int i=0; i<AnimalsKingdom.length;i++)  
			for (int j=0; j<AnimalsKingdom[i].length; j++) {
				switch (BoardMatrix[i][j]) {// In each cell in the matrix put the animal according to the numbers
				case 0:
					AnimalsKingdom[i][j] = new Monkey();
					break;
				case 1:
					AnimalsKingdom[i][j] = new Kangaroo();
					break;
				case 2:
					AnimalsKingdom[i][j] = new Zebra();
					break;
				case 3:
					AnimalsKingdom[i][j] = new Lion();
					break;
				case 4:
					AnimalsKingdom[i][j] = new Shark();
					break;
				case 5:
					AnimalsKingdom[i][j] = new Denis();
					break;
				case 6:
					AnimalsKingdom[i][j] = new Pyrenees();
					break;
				case 7:
					AnimalsKingdom[i][j] = new Viper();
					break;
				case 8:
					AnimalsKingdom[i][j] = new Turtle();
					break;
				case 9:
					AnimalsKingdom[i][j] = new Iguana();
					break;
				}
				AnimalsKingdom[i][j].row=i;//Keep for each animal the row and the column in the board
				AnimalsKingdom[i][j].column=j;
			}
	}

	public boolean Adjacent(int [][]HelperInteger,int row1,int column1, int number) {//Check if there is the same number near the current coordinate
		if (row1>0 ) 
			if (number==(HelperInteger [row1-1][column1]))
				return true;
		if (row1<4 )
			if (number==(HelperInteger [row1+1][column1]))
				return true;
		if (column1>0 )
			if (number==(HelperInteger [row1][column1-1]))
				return true;
		if (column1<3 )
			if (number==(HelperInteger[row1][column1+1]))
				return true;

		return false;
	}
	
	public boolean Startover (int [][]HelperInteger,int number) {//checks if there is possible number to put in the given place.
		for(int r=0;r<HelperInteger.length;r++) {
			for(int c=0;c<HelperInteger[r].length;c++) { 	
				if(HelperInteger[r][c]==-1) {
					if(!Adjacent(HelperInteger,r,c,number))//there is one  free place possible to put the number.
						return false;	
				}
			}
		}
		return true;
	}

	
	public boolean NearbyDenis(Animals place1 ,  Animals place2) {//Check if there is a nearby Denis for each card
		if (NearbyDenis2(place1, place2) || NearbyDenis2(place2,place1))
			return true;
		return false;
	}

	private boolean NearbyDenis2(Animals place1 , Animals place2) {//Check if one of the cards near the Pyrenees or the Shark is a Denis
		if(place1 instanceof Pyrenees && place2 instanceof Shark || place1 instanceof Shark && place2 instanceof Pyrenees )
		{	
			if (place1.row>0) 
				if (AnimalsKingdom [place1.row-1][place1.column]instanceof Denis && (AnimalsKingdom[place1.row-1][place1.column]).found==false)
					return true;
			if (place1.row<4 )
				if (AnimalsKingdom [place1.row+1][place1.column]instanceof Denis && (AnimalsKingdom[place1.row+1][place1.column]).found==false)
					return true;
			if (place1.column>0 )
				if ( AnimalsKingdom [place1.row][place1.column-1]instanceof Denis && (AnimalsKingdom[place1.row][place1.column-1]).found==false)
					return true;
			if (place1.column<3 )
				if (AnimalsKingdom [place1.row][place1.column+1]instanceof Denis && (AnimalsKingdom[place1.row][place1.column+1]).found==false)
					return true;	
		}
		return false;
	}
} 