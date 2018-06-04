import java.util.Scanner;

//TicTacToe game
//@Jonayed Ahmed

public class TicTacToe {

	private static final String line = "-------------------"; //partition of game board
	private static final int COL = 3; //size of column is 3
	private static final int ROW = 3; //size of row is 3
	
	private Scanner reader = new Scanner(System.in); //for user input
	
	private char[][] gameBoard = new char[COL][ROW]; // 9 possible sections for symbols in game
	
	public void startingDialogue()
	{
		System.out.println("Welcome to Jonayed's TicTacToe!");
		System.out.println("X will start first \n");
		
		System.out.println("In order to choose a position for your symbol, type in row and column.");
		System.out.println("For example, an input of 3 2 will give: ");
		System.out.println("-------------------");
		System.out.println("|  _  |  _  |  _  |");
		System.out.println("-------------------");
		System.out.println("|  _  |  _  |  _  |");
		System.out.println("-------------------");
		System.out.println("|  _  |  X  |  _  |");
		System.out.println("-------------------");
		
		initializeBoard();
		
		boolean winning = false; //initially, neither player is winning
		int turns = 0; //determines when while loop should stop if game is draw
		char player = 'X'; //starting player is X
		startGame(winning, turns, player);
	}
	
	public void initializeBoard()
	{
		for(int i = 0; i < ROW; i++)
		{
			for(int j = 0; j < COL; j++)
			{
				gameBoard[i][j] = '_';
			}
		}
	}
	
	public void startGame(boolean winning, int turns, char player)
	{
		int r = 0;
		int c = 0;
		
		while(winning == false && turns < ROW * COL) //keep playing the game while there is not a winner
		{
			System.out.print("Player " + player + " type position you want a " + player + " on: ");
			
			r = firstIntCheck(r); //first input error checking
			c = secondIntCheck(c); //second input error checking
			
			if((r > ROW || r < 1) || (c > COL || c < 1)) //user input not on board error check
			{
				System.out.println("Input was not greater than 0 and less than 3! Valid input example: 3 2 \n");
				startGame(winning, turns, player);
			}
			
			if(gameBoard[r-1][c-1] == 'X' || gameBoard[r-1][c-1] == 'O') //user input already taken on board error check
			{
				System.out.println("That position is taken! Please type in a position that is not taken! \n");
				startGame(winning, turns, player);
			}
			
			gameBoard[r - 1][c - 1] = player; //array starts at 0, so -1
			
			turns++;
			drawBoard();
			
			if(turns < 5) //only can have a winner if there have been at least 5 turns
				player = nextPlayer(player);
			else
			{
				determineWinner(player);
				player = nextPlayer(player);
			}	
		}
		
		//only get to this portion of code if game is a draw
		System.out.println("The game is a draw!!! Nobody wins! \n");
		System.out.print("Would you like to play again? (Y or N)");
		replayGame();
	}
	
	public int firstIntCheck(int r) //checks if row value is an int
	{
		try //for first input
		{
			return r = Integer.parseInt(reader.next());
		}
		catch(NumberFormatException ex)
		{
			System.out.println("First input was not a valid number!");
			System.out.print("Please input a valid value: ");
			firstIntCheck(r);
		}
		return 0;
	}
	
	public int secondIntCheck(int c) //checks if column value is an int
	{
		try
		{
			return c = Integer.parseInt(reader.next());
		}
		catch(NumberFormatException ex)
		{
			System.out.println("Second input was not a valid number!");
			System.out.print("Please input a valid value: ");
			firstIntCheck(c);
		}
		return 0;
	}
	
	public char nextPlayer(char player)
	{
		if(player == 'X')
			return 'O';
		else
			return 'X';
	}
	
	
	public void drawBoard()
	{
		System.out.print(line + "\n|  "); //initial line and left partition
		
		for(int i = 0; i < ROW; i++) 
		{
			for(int j = 0; j < COL; j++) //goes through each row and prints out symbols
			{
			    System.out.print( gameBoard[i][j] + "  |  ");
				
				if(j == COL - 1 && i == ROW - 1) //prints last dividing line
				{
					System.out.println("\n" + line + "\n");
				}
				else if(j == COL - 1)//prints dividing line and left partition on next line
				{
					System.out.print("\n" + line + "\n|  ");
				}

			}
		}
	}
	
	public void determineWinner(char player)
	{
		if(horizontalWinner() || verticalWinner() || diagonalWinner()) //if a player got a tic tac toe
		{
			System.out.println("Player " + player + "! Congratulations you won!!! \n");
			System.out.print("Would you like to play again? (Y or N)");
			replayGame();
		}	
	}
	
	public boolean horizontalWinner()
	{
		for(int i = 0; i < ROW; i++)
		{
			if ((gameBoard[i][0] != '_') && //if there is a _ symbol in the row, then there can't be a horizontal winner
				(gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2] && gameBoard[i][0] == gameBoard[i][2]))
				return true;
		}
		return false;
	}
	
	public boolean verticalWinner()
	{
		for(int i = 0; i < ROW; i++)
		{
			if ((gameBoard[0][i] != '_') &&	 //if there is a _ symbol in the column, then there can't be a vertical winner
				(gameBoard[0][i] == gameBoard[1][i] && gameBoard[1][i] == gameBoard[2][i] && gameBoard[0][i] == gameBoard[2][i]))
				return true;
		}
		return false;
		
	}
	
	public boolean diagonalWinner()
	{
		return ((gameBoard[1][1] != '_') &&  //if there is a _ symbol in the middle space then there can't be a diagonal winner
				((gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]) ||
				(gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0])));
	}
	
	public void replayGame()
	{
		char choice = reader.next().trim().charAt(0);
		
		if(choice == 'Y' || choice == 'y')
		{
			System.out.println("Awesome! Have fun \n");
			initializeBoard();
			startGame(false, 0, 'X');
		}
		else if(choice  == 'N' || choice == 'n') //N
		{
			System.out.println("Thank you for playing!!!");
			System.out.println("Have a great day!");
			System.out.print("You can x out of the game now");
			System.exit(0);
		}
		else //user typed neither Y or N error check
		{
			System.out.print("Please type in either Y or N: ");
			replayGame();
		}
	}
}
