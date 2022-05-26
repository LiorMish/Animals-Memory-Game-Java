import java.util.*;

public class TheGame {

	public static int  counterPairs=0, saver1=0,saver2=0, counterMatrix;  //Helpers variables
	public static int row1, column1;        //   Helpers variables for saving coordinates.
	public static Animals  card1, card2,place1,place2;  //Helper variables for operation in the animals matrix.
	public static char column2, row2;                 //Saving the coordinates that the user want to flip
	public static String place, newGame,Start;             // Receives user input.
	public static char[][] board = new char[5][4];   //Game board to print for the user.
	public static int [][] HelperInteger = new int [5][4];   ////Helper board  saving the numbers 0-9 .
	public static AnimalsMatrix GameBoard = new AnimalsMatrix();  // Matrix with animals types.
	static Scanner sc =new Scanner (System.in);

	public static void main(String[] args) {
		do {

			System.out.print("Welcome to Fatma Memory Game");
			StartGame();                                        //The game is starting, printing the table for the user.
			while (!(EndGame())) {

				System.out.println("Please choose first card to flip");
				card1 = ChooseCardToFlip();                       //User choose first card.
				PrintBoard();                                    //Printing the table with the card that as chosen.

				System.out.println("Please choose second card to flip");
				card2 = ChooseCardToFlip();                      //User choose second card.
				PrintBoard();                                    //Printing the table with the card that as chosen.

				if (MatchCards())                            //If cards are matches they are removed else they are flip back.
					RemoveCards();
				else FlipBack();
			}

		}	while(NewGame());	//Game is continuing until it finish , when finish the user will decide if to start new one. 
	}

	public static Animals ChooseCardToFlip() { //Receiving the user choice for which card to flip.
		do {
			Scanner sc = new Scanner (System.in);
			place = sc.nextLine(); //User input as a string.
			if(place.length()>=2) { //If the user input is not one char save him as two coordinates.
				row2 = place.charAt(0);
				column2 = place.charAt(1);
			}
		}while (WrongInput(place) ) ;//Check if the input is valid, if its true ,keep ask for valid input.

		board [ row2-48][column2-48] = ((GameBoard.AnimalsKingdom [row2-48][column2-48].name));//Replace the '#' at the board game with the number at the same place in the helper matrix.
		return GameBoard.AnimalsKingdom [row2-48][column2-48]; //Return the value of the card.

	}

	public static boolean WrongInput(String UserInput) {//Check if the user entered correct coordinates.

		boolean check=true, wrong = false; //Helpers booleans

		if (UserInput.length()!=2) { //If user input is not two chars the input is invalid.
			wrong = true;     //Return true , the input is wrong.
			check=false;      //Check is false, do not keep check other conditions.
		}
		if(check) {
			if (UserInput.charAt(0)<'0' || UserInput.charAt(0)>'4'||UserInput.charAt(1)<'0' || UserInput.charAt(1)>'3') //If the input is not two numbers that are at the correct range , return true,input invalid. 
				wrong = true;
			else if( board [ UserInput.charAt(0)-48][ UserInput.charAt(1)-48] != '#')//If the coordinates were already choose,return true,input invalid. 
				wrong=true;
		}
		if (wrong == true) { //If wrong is true , one of the conditions for invalid input is true.
			System.out.println("Sorry, wrong input. Please try again.");
			PrintBoard(); 
			return wrong;//return true.
		}else return wrong;//return false.
	}


	public static void PrintBoard() {//Printing the board.

		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board [i].length; j++) { 

				System.out.print(board [i][j] + "      ");//Printing the chars matrix.
			}
			System.out.println("");
		}
	}

	public static boolean MatchCards() { //Checks if the card are matches

		if (card1.IsMatch(card2)&&(!GameBoard.NearbyDenis(card1, card2))){ //Check by the specific card criteria if he match the other card.
			counterPairs++;
			return true;
		}
		return false;
	}

	public static void StartGame() {//Initializing game board  and animals board.
		Start=sc.nextLine();
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board [i].length; j++) { //Initializing game board at first with '#'.
				board [i][j] = '#';
				System.out.print(board [i][j] + "      ");
			}
			System.out.println("");
		}

		InitializeIntArray();//Initialize the  helper matrix with numbers .
		GameBoard.CreateAnimalsMatrix(HelperInteger); //Initialize the Animals Board.
	}

	private static void InitializeIntArray() {//Initialize the  helper matrix with numbers from 0-9 there are no two same numbers next to each other.
		counterMatrix=0;
		for (int i=0; i<HelperInteger.length; i++) {  //Initializing helper board at first with '-1'.
			for (int j=0; j<HelperInteger[i].length; j++) {
				HelperInteger [i][j] = -1;
			}
		}
		for (int i=0; i<2;i++) { //Twice Initializing numbers from 0-9 randomly in the helper matrix.
			for (int j=0; j<=9; j++) {//Loop that runs from 0-9 to initializing them in the board.
				row1 = (int) (5 * (Math.random()));  //Choose randomly row from 0-4.
				column1 = (int) (4 * (Math.random()));//Choose randomly column from 0-3.

				if (HelperInteger [row1][column1] != -1 )  //If there is number at the coordinates keep search new random coordinates to the same number.
					j--;                          //Keeps the number the same.
				else if(counterMatrix>=16&&(GameBoard.Startover(HelperInteger,j)))// Checks if the specific random initialize impossible and will start a new one,(only from the second 6 could be problem).
					InitializeIntArray();

				else if (GameBoard.Adjacent(HelperInteger,row1,column1,j)) //Check if next to free place the same number is not exist.
					j--;

				else {
					HelperInteger [row1][column1]=j; //There is no number ,so put the current number in the matrix.
					counterMatrix++;
				}
			}
		}
	}

	public static void FlipBack() {//Flip the cards back.

		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board [i].length; j++) {
				if (board [i][j] <= 'Z' && board [i][j]>='A')//Checks the place that there are letters and put back "#" instead.
					board [i][j] = '#';
			}
		}	
		System.out.println("Cards do not match!");
		PrintBoard();         //Print the board with the cards flip.
	}



	public static void RemoveCards() {//Removes cards from table.

		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board [i].length; j++) {
				if (board [i][j] <= 'Z' && board [i][j]>='A')//Checks the place that there are letters and put "*" instead.
					board [i][j] = '*';
			}
		}
		System.out.println("Cards match!");
		PrintBoard();           //Print the board with * at the places where the cards were.
	}

	public static boolean NewGame() {//Checks if the user want new game or not.
		counterPairs=0;	            //Reset the counter.
		boolean ask=false;
		do{
			System.out.println("Would you like to start a new game?");
			Scanner sc = new Scanner (System.in);
			newGame = sc.nextLine();
			if(newGame.equals("Y")||newGame.equals("y"))        //If the user enter y or Y return true,new game will start.
				return true;
			else if(newGame.equals("N")||newGame.equals("n"))   //If the user enter n or N return false, game will finish.
				return false;
			else {
				System.out.println("Sorry, wrong input. Please try again."); 
				ask=true;                      //Ask is true ,keep ask for correct input.
			}

		}	while(ask);  // //Ask is true ,keep ask for correct input.
		return false;
	}

	public static boolean EndGame() {//Checks if game need to be finished.

		if(counterPairs==9)  {                     //If nine pairs were found we check if the last pair can be matched.
			saveLastPair();
			if(!IsLastPairMatched()) {
				System.out.println("Game is over! No more possible matches.");
				PrintBoard();
				return true;
			}
			return false;	
		}
		return IsCounterPairs10(); 
	}

	private static void saveLastPair() { //Save last pair to check if match and save their places.
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board [i].length; j++) {
				if(board[i][j]=='#') {
					if(place1==null) {
						place1=GameBoard.AnimalsKingdom [i][j];                  //Saves first number that was remained.
						board[i][j]= place1.name; //Put the animal name instead '#' to display.
						saver1=i*10+j;                               //Saves the coordinates of the card.
					}
					else {
						place2=GameBoard.AnimalsKingdom [i][j];                 //Saves second number that was remained.
						board[i][j]= place2.name; //Put the animal name instead '#' to display.
						saver2=i*10+j;                               //Saves the coordinates of the card.
					}
				}
			}
		}
	}

	public  static boolean IsCounterPairs10() {//Check if the  ten pairs were found and game is over.
		if(counterPairs==10) { //All pairs were found.
			System.out.println("Game is over! All cards are matched."); 
			PrintBoard();  //Print the game board with all '*'.
			return true;   //Return true , finished the game.
		}
		return false;
	}

	public static boolean IsLastPairMatched() {//Checks if the last pair that remained can be matched.
		if(!(place1.IsMatch(place2))){  //The cards are not equals,and there are no joker,there are no more matches.
			return false;     //Return true , finished the game.
		}
		else {                             //The last pair can be matched.
			board[saver1/10][saver1%10]='#';  //Put back '#' at the place of the first card in the chars matrix.
			board[saver2/10][saver2%10]='#';  //Put back '#' at the place of the second card in the chars matrix.
			return true;
		}
	}	
}