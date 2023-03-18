/**
 * Mauricio Gomez Iglesias ID: 40244800
 * COMP 249 Section S
 * Assignment #1
 * Due Date: 06/02/2023
 */

/**
 * This class's purpose is to initialize the game, as well as the number of players and the board.
 * It also contains the play() method that will initiate the game.
 * 
 * @author Mauricio Gomez Iglesias
 * @version 1.0
 *
 */

//-------------------------------------------------
//Assignment 1 
//Question 1, Part 1
//Written by Mauricio Gomez Iglesias, ID: 40244800
//For COMP 249 Section S - Winter 2023
//-------------------------------------------------

public class LadderAndSnake {
	
	//attributes of LadderAndSnake class
	private int numOfPlayers; //number of players
	private int[][] board = new int[10][10];//10x10 board
	
	
	
	
	//accessor method for game board
	/**
	 * Gets an element from a 2D array based on the row and column parameters passed
	 * @param i row value
	 * @param j column value
	 * @return board[i][j]
	 */
	public int getBoard(int i, int j) {
		return board[i][j];
	}

	
	//accessor and mutator methods for numOfPlayers
	/**
	 * Gets the number of players from the LadderAndSnake object
	 * @return numOfPlayers
	 */
	public int getNumOfPlayers() {
		return numOfPlayers;
	}

	/**
	 * Sets the numOfPlayers for the LadderAndSnake object based on the value of the parameter passed
	 * @param numOfPlayers
	 */
	public void setNumOfPlayers(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}

	//default constructor
	/**
	 * Default constructor for the class.
	 * Will initialize numOfPlayers to 2 and the board to a 10x10 array
	 */
	public LadderAndSnake() {
		numOfPlayers = 2;
		int[][] board = new int[10][10];
		
		//board elements with non-zero values represent the final destination of a ladder/snake
		//ladders
		board[0][0] = 38;
		board[0][3] = 14;
		board[0][8] = 31;
		board[2][0] = 42;
		board[2][7] = 84;
		board[3][5] = 44;
		board[6][6] = 67;
		board[9][0] = 91;
		board[9][9] = 100;
		
		//snakes
		board[1][5] = 6;
		board[4][7] = 30;
		board[6][3] = 60;
		board[7][8] = 19;
		board[9][2] = 68;
		board[9][4] = 24;
		board[9][6] = 76;
		board[9][7] = 78;
	}

	//parameterized constructor
	/**
	 * Parameterized constructor used when asking the user to input the number of players.
	 * Will display warning messages accordingly if the value entered does not satisfy the requirements
	 * @param numOfPlayers
	 * @param board
	 */
	public LadderAndSnake(int numOfPlayers, int[][] board) {
		if(numOfPlayers > 2) {//value entered is greater than 2: prompts a warning and sets the numOfPlayers value to 2
			System.out.println("\nInitialization was attempted for "+numOfPlayers+" players; however, this is only expected for an extended version of the game."
					+ "\nValue will be set to 2");
			this.setNumOfPlayers(2);
		}
		if(numOfPlayers < 2) {//if the value entered is less than 2, the game will terminate
			System.out.println("\nError: Cannot execute the game with less than 2 players! Will exit the program now.");
			System.exit(0);
		}
		else if(numOfPlayers == 2 ){//displays a validation message
			this.setNumOfPlayers(numOfPlayers);
			System.out.println("\nPerfect! The game will be initialized for 2 players.");
		}
		this.board = board;
		

	}
	
	/**
	 * This method's purpose is to roll the die.
	 * It will generate a random number ranging from 1 to 6 every time it is called.
	 * @return diceVal randomly generated value
	 */
	public int flipDice() {
		//multiplication by 6 extends the range from [0,1) to [0,6). Adding 1 shifts the bounds by 1 to [1,7)
		int diceVal = (int)(Math.random()*6)+1;
		return diceVal;
	}
	
	/**
	 * This method is in charge of running all operations executed during one turn from each player.
	 * Takes on 2 player objects as parameters, as well as the game board.
	 * Calls on various methods from the Player class.
	 * @see Player
	 * @param p1 First player
	 * @param p2 Second players
	 * @param board Game board
	 */
	public void play(Player p1, Player p2, int[][] board) {
		
		System.out.println("\nIt is "+p1.getName()+"'s turn. ");//displays message indicating whose turn it is
		int diceVal1 = flipDice();//rolls dice
		System.out.println("You rolled a "+diceVal1);
		p1.setpPosition(p1.getpPosition()+diceVal1);//updates position
		//checks if position >  100; only checked at this point because landing on a square containing a snake or a ladder will never take the player further than square 100
		p1.over100();
		p1.calcRowCol();//calculates row and column based on position (x,y)
		p1.sameSquare(p2);//checks if p1 landed on the same square as p2
		p1.snakeOrLadder(board);//checks if p1 landed on a snake or a ladder
		p1.sameSquare(p2);//checks again if position is the same as p2
		p1.calcRowCol();//recalculates player's row and column values in case they changed
		System.out.println(p1);//prints player's name and their current position
		if(p1.getpPosition() == 100) {//displays congratulations message and terminates program if p1 reaches square 100
			System.out.println("Congratulations "+p1.getName()+"! You are the winner!");
			System.out.println();
			System.out.println("Thanks for playing!");
			System.exit(0);
		}
		//same procedure for p2
		System.out.println("\nIt is "+p2.getName()+"'s turn. ");
		int diceVal2 = flipDice();
		System.out.println("You rolled a "+diceVal2);
		p2.setpPosition(p2.getpPosition()+diceVal2);
		p2.over100();
		p2.calcRowCol();
		p2.sameSquare(p1);
		p2.snakeOrLadder(board);
		p2.sameSquare(p1);
		p2.calcRowCol();
		System.out.println(p2);
		if(p2.getpPosition() == 100) {
			System.out.println("Congratulations "+p2.getName()+"! You are the winner!");
			System.out.println();
			System.out.println("Thanks for playing!");
			System.exit(0);
		}
	}
	

}
