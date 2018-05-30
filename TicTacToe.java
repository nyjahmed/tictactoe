import java.util.Scanner;

public class TicTacToe {

	private Scanner reader; //for user input
	
	private static char player = ' ';
	private char[][] gameBoard = new char[3][3]; // 9 possible sections for symbols in game
	
	private static final String line = "-------------------"; //partition of game board
	private static final int COL = 3; //size of column is 3
	private static final int ROW = 3; //size of row is 3
	
	public TicTacToe()
	{
		
	}
	
	public void startingDialogue()
	{
		System.out.println("Welcome to TicTacToe!");
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
		startGame();
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
	
	public void startGame()
	{
		reader = new Scanner(System.in);
		boolean winning = false; //initially, neither player is winning
		int turns = 0; //determines when while loop should stop if game is draw
		player = 'X'; //starting player is X
		
		while(winning == false && turns < ROW * COL) //keep playing the game while there is not a winner
		{
			System.out.print("Player " + player + " type position you want a " + player + " on: ");
			
			int r = reader.nextInt(); //the row the user wants to put the symbol on
			int c = reader.nextInt(); //the column the user wants to put the symbol on
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
		System.out.println("The game is a draw!!! Nobody wins!");
			
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
				
				if(j == 2) //prints dividing line and left partition if at end of row 
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
			System.out.println("Player " + player + "! Congratulations you won!!!");
		}	
	}
	
	public boolean horizontalWinner()
	{
		if((gameBoard[0][0] == gameBoard[0][1] && gameBoard[0][1] == gameBoard[0][2]) ||
		   (gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[1][2]) ||
		   (gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][1] == gameBoard[2][2]))
		{
			return true;
		}
		else
			return false;
	}
	
	public boolean verticalWinner()
	{
		if((gameBoard[0][0] == gameBoard[1][0] && gameBoard[1][0] == gameBoard[2][0]) ||
		   (gameBoard[0][1] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][1]) ||
		   (gameBoard[0][2] == gameBoard[1][2] && gameBoard[1][2] == gameBoard[2][2]))
		{
			return true;
		}
		else
			return false;
	}
	
	public boolean diagonalWinner()
	{
		if((gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]) ||
		   (gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0]))
		{
			return true;
		}
		else 
			return false;
	}
}
