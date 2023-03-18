import java.util.Scanner;

/** 
 * Mauricio Gomez Iglesias ID: 40244800
 * COMP 249 Section S
 * Assignment #1
 * Due Date: 06/02/2023
 */ 

/**
 * The PlaySnakeAndLadder class acts as the driver class for this program.
 * It is in charge of taking user input, more specifically, number of players
 * and the name for each player.
 * After the information has been entered, the game will start.
 * 
 * @author Mauricio Gomez Iglesias
 * @version 1.0
 */

//-------------------------------------------------
//Assignment 1 
//Question 1, Part 2
//Written by Mauricio Gomez Iglesias, ID: 40244800
//For COMP 249 Section S - Winter 2023
//-------------------------------------------------
public class PlaySnakeAndLadder {

	/**
	 * @param args an array of command-line arguments for the program
	 */
	public static void main(String[] args) {
		
		//Scanner object to accept inputs from the user
		Scanner input = new Scanner(System.in);
		
		//initializes the game board that will be used for the game
		int[][] board = new int[10][10];
		
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


		//creating player objects
		Player player1;
		Player player2;
		Player p1;
		Player p2;
		
		//variables for player names
		String name1;
		String name2;
		
		//dice rolls
		int p1Roll;
		int p2Roll;
		
		
		//welcome message
		System.out.println("********************************************************");
		System.out.println("Snakes and Ladders");
		System.out.println("********************************************************");
		System.out.println();
		System.out.println("Created by: Mauricio Gomez Iglesias");
		
		System.out.println();
		System.out.print("\nPlease enter the desired number of players: ");//prompts user to enter value
		int x = input.nextInt();
		
		LadderAndSnake game = new LadderAndSnake(x, board);//calls on the parameterized constructor of LadderAndSnake class
		
		
		System.out.print("\nPlease enter your first name each, separated by a space: ");
		name1 = input.next().trim();//.trim() ensures there is no unnecessary white space around a player's name
		name2 = input.next().trim();
		
		//calls on Player class parameterized constructor to create 2 player objects
		player1 = new Player(name1);
		player2 = new Player(name2);
		
		//displays rules of the game
		System.out.println("\nNice to meet you "+player1.getName()+" and "+player2.getName()+"!");
		System.out.println("\nBefore the game starts, please take a few moments to read the rules:"
				+ "\n-------------------------------------------------------------------------------"
				+ "\n1. Both players will take turns rolling a six-sided die, then advancing a "
				+ "\n   number of squares equal to the amount on the side of the die that is facing up."
				+ "\n\n2. The first player to reach EXACTLY square 100 wins the game."
				+ "\n\n3. If a player is close to square 100, and the dice value exceeds 100 when added"
				+ "\n   to their current position, they shall move to square 100, and the backwards by"
				+ "\n   the exceeded amount."
				+ "\n\n4. If a player reaches the bottom of a ladder, they shall move up to the square"
				+ "\n   containing the top of the ladder. Similarly, if a player reaches the head of"
				+ "\n   a snake, they shall move down to the square containing said snake's tail."
				+ "\n\n5. If a player lands on the same square where the other player is located,"
				+ "\n   the latter will be kicked out and their position will be reset to 0."
				+ "\n-------------------------------------------------------------------------------");
		
		System.out.println("\nWhen ready press <Enter> to continue>");//allows the players to read the rules before the game starts
		input.nextLine();
		input.nextLine();
		
		System.out.println("Time to decide who goes first!");
		
		do {//do while loop in case both players roll the same value
			System.out.println("\n"+player1.getName()+", press <Enter> to roll the die");
			input.nextLine();
			p1Roll = game.flipDice();
			System.out.println("You rolled a "+p1Roll);
			
			System.out.println("\nNow it is "+player2.getName()+"'s turn. Press <Enter> to roll the die");
			input.nextLine();
			p2Roll = game.flipDice();
			System.out.println("Your rolled a "+p2Roll);
		}while(p1Roll == p2Roll);
		
		
		//player with the highest dice roll will go first
		//copy constructors of the Player class allows the program to keep track of who is going first
		if(p1Roll > p2Roll) {
			p1 = new Player(player1);
			p2 = new Player(player2);
			
			//while loop to keep the game going while neither player has reached square 100
			while(p1.getpPosition() != 100 && p2.getpPosition() != 100) {
				game.play(p1, p2, board);
			}
			
			if(p1.getpPosition() > p2.getpPosition()) {
				System.out.println("Congratulations "+p1.getName()+"! You are the winner!");
			}
			else {
				System.out.println("Congratulations "+p2.getName()+"! You are the winner!");
			}
		}
		
		else {
			p1 = new Player(player2);
			p2 = new Player(player1);
			
			while(p1.getpPosition() != 100 && p2.getpPosition() != 100) {
				game.play(p1, p2, board);
			}
		}

		input.close();
	}
		



}
