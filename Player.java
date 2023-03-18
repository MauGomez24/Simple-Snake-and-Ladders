/**
 * Mauricio Gomez Iglesias ID: 40244800
 * COMP 249 Section S
 * Assignment #1
 * Due Date: 06/02/2023
 */

/**
 * The Player class' purpose is to create objects that will store each player's position.
 * It keeps track of each player's position (square on the board),
 * as well as the player coordinates in the array.
 * 
 * @author Mauricio Gomez Iglesias
 * @version 1.0
 */

//-------------------------------------------------
//Assignment 1 
//Question 1, Part 1
//Written by Mauricio Gomez Iglesias, ID: 40244800
//For COMP 249 Section S - Winter 2023
//-------------------------------------------------

public class Player {

	
	//Player class attributes
	private String name; //player name
	private int pRow; //player's current row
	private int pCol; //player's current column
	private int pPosition; //player's current position
	
	
	
	//accessor and mutator methods for name
	/**
	 * Gets this player's name value
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
    /**
     * Updates this player's name value
     * @param name
     */
	public void setName(String name) {
		this.name = name;
	}

	//accessor and mutator methods for pRow
	/**
	 * Gets this player's row value
	 * @return pRow
	 */
	public int getpRow() {
		return pRow;
	}
	
	/**
	 * Updates this player's row value
	 * @param pRow
	 */
	public void setpRow(int pRow) {
		this.pRow = pRow;
	}
	
	//accessor and mutator methods for pCol
	/**
	 * Gets this player's column value
	 * @return pCol
	 */
	public int getpCol() {
		return pCol;
	}
	
	/**
	 * Updates this player's column value
	 * @param pCol
	 */
	public void setpCol(int pCol) {
		this.pCol = pCol;
	}

	//accessor and mutator methods for pPosition
	/**
	 * Gets this player's position value
	 * @return pPosition
	 */
	public int getpPosition() {
		return pPosition;
	}
	
	/**
	 * Updates this player's position value
	 * @param pPosition
	 */
	public void setpPosition(int pPosition) {
		this.pPosition = pPosition;
	}
	
	//default constructor
	/**
	 * Default constructor of the Player class.
	 * Initializes name as an empty string, pRow to 0, pCol to -1, pPosition 0.
	 */
	public Player() {
		name = "";
		pRow = 0;
		pCol = -1;
		pPosition = 0;
	}
	
	//parameterized constructor
	/**
	 * Initializes name as the value passed through the parameter.
	 * pRow, pCol and pPosition are initialized to the same values as in the default constructor.
	 * @param name
	 */
	public Player(String name) {
		this.name = name; //name is the only variable that will take input from the user
		this.pRow = 0; //each player will start on the first row of the board
		this.pCol = -1; //each player will start on column -1 of the board (out of bounds for array --> player is outside of board)
		this.pPosition = 0; //square 0 on board doesn't exist, therefore, player is outside of board
	}
	
	//copy constructor
	/**
	 * Copy constructor of the Player class.
	 * Initializes each attribute to the same value as the attributes of the Player object passed as a parameter.
	 * @param player
	 */
	public Player(Player player) {
		this.name = player.name;
		this.pRow = player.pRow;
		this.pCol = player.pCol;
		this.pPosition = player.pPosition;
	}
	
	//method that will calculate player position from pRow and pCol
	/**
	 * Calculates the player's position based on the row and column.
	 * Method does not take any parameters. Also a void method so it will not return any values.
	 */
	public void calcPosition() {
		int position = (this.getpRow() * 10) + (this.getpCol() + 1);
		this.setpPosition(position); //using setter method to update player's position
	}
	
	//method to calculate pRow and pCol based on pPosition
	/**
	 * Similar to calcPosition(), however, calculates the player row and column based on the player's position.
	 * Void method, therefore, it won't return any values. Doesn't take any parameters.
	 * Contains special cases due to Java's indexing paradigm.
	 */
	public void calcRowCol(){
		//instantiating placeholder variables
		int row;
		int col;
		
		row = this.getpPosition()/10; //calculates the value of the row by dividing by 10
		if(this.getpPosition()%10 > 0) {
			this.setpRow(row);
		}
		else if(this.getpPosition()%10 == 0) {
			this.setpRow(row - 1); //special case: if the square is a multiple of 10, it will be located on a previous row i.e square 20 is on row 1 rather than row 2
		}
		
		col = (this.getpPosition()%10); //calculates the value of the column by computing mod 10
		if(col > 0) {
			this.setpCol(col-1);
		}
		else if(col == 0) {
			this.setpCol(9);//special case: due to Java's indexing paradigm squares that are multiples of 10 will be located on column 9
		}
		
	}
	
	//method that checks if a player's position is over 100
	/**
	 * This method will check whether the player has gone past square 100 with an if statement.
	 * If true, the player is sent back an amount of squares equal to the amount by which they went over 100.
	 * Takes no parameters.
	 */
	public void over100() {
		if(this.getpPosition() > 100) {
			int temp = this.getpPosition() - 100; //calculating the amount by which the player exceeded 100
			this.setpPosition(100 - temp); //sets the players new position
			
			System.out.println("Looks like you went past square 100 by "+temp); //displaying a message to inform the player of what has happened
			System.out.println("Back to square "+this.getpPosition()+". Better luck next turn.");
		}
	}
	
	//method to check if players are in the same square
	/**
	 * This method checks whether a player has landed on the same position as a the other.
	 * If this is true, the player that was on that square first has their position reset back to 0.
	 * Therefore, they are placed outside of the board.
	 * @param p2 player 2
	 */
	public void sameSquare(Player p2) { //takes player 2 as a parameter to compare positions
		if(this.getpPosition() == p2.getpPosition()) {
			p2.setpPosition(0); //resets player 2's position
			p2.setpRow(0);
			p2.setpCol(-1); //places player 2 outside of the board
			System.out.println("Looks like you landed on the same square as "+p2.getName()+"."); //displays message to player
			System.out.println("Unfortunately there is not enough space for both of you.");
			System.out.println("Sorry "+p2.getName()+", but you have to go to position 0 :(");
		}
	}
	
	//method to check whether the player has landed on a snake or a ladder
	/**
	 * This method will check if the player has landed at the bottom of a ladder or has stepped on a snake.
	 * Then, it will adjust the player's position accordingly.
	 * Also calculates their corresponding row and column based on their position
	 * @param board
	 */
	public void snakeOrLadder(int[][] board) {//takes the board as a parameter
		int pos = this.getpPosition();
		int row = this.getpRow();
		int col = this.getpCol();
		int temp = board[row][col];
		
		if(temp != 0) {//only squares containing ladders/snakes have an assigned value that is not 0
			if(temp > pos) {//player lands at the bottom of a ladder because final position value is greater than initial one
				System.out.println("\nLucky! You landed at the bottom of a ladder"
						+ "\nYou can now move to square "+temp);
				this.setpPosition(temp);//updates position
				
				//same method to get player row and column based on position value
				row = this.getpPosition()/10;
				if(this.getpPosition()%10 > 0) {
					this.setpRow(row);
				}
				else if(this.getpPosition()%10 == 0) {
					this.setpRow(row - 1);
				}
				
				col = (this.getpPosition()%10);
				if(col > 0) {
					this.setpCol(col-1);
				}
				else if(col == 0) {
					this.setpCol(9);
				}
				
			}
			else if(temp < pos) {//player lands on a snake's head beacause final position value is lower than initial one
				System.out.println("\nOops! Looks like you stepped on a snake"
						+ "\nYou'll have to ssslide down to square "+temp);
				this.setpPosition(temp);//updates position
				
				//same method to get player row and column based on position value
				row = this.getpPosition()/10;
				if(this.getpPosition()%10 > 0) {
					this.setpRow(row);
				}
				else if(this.getpPosition()%10 == 0) {
					this.setpRow(row - 1);
				}
				
				col = (this.getpPosition()%10);
				if(col > 0) {
					this.setpCol(col-1);
				}
				else if(col == 0) {
					this.setpCol(9);
				}
			}
		}
		
		
	}
	
	@Override
	/**
	 * This method overrides the toString() method from the object class.
	 * Displays a player's name and their current position.
	 */
	public String toString() {
		return this.name+" is currently on square "+this.pPosition;
	}
	
	
}
