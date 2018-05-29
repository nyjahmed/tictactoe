import java.util.Scanner;

public class TicTacToe {

	private Scanner reader; //for user input
	private boolean winning = false; //set to true when a player wins
	private char player = 'X'; //starting player is X
	private char[][] gameBoard = new char[3][3]; // 9 possible sections for symbols in game
	private String line = "-------------------"; //partition of game board
	
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
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				gameBoard[i][j] = '_';
			}
		}
	}
	
	public void startGame()
	{
		reader = new Scanner(System.in);
		
		while(winning == false)
		{
			System.out.print("Player " + player + " type position you want a " + player + " on: ");
			//String userInput = reader.next(); 
			//userInput = userInput.replaceAll("\\s+", ""); //removes all whitespace in user input
			//int row = userInput.charAt(0) - 0;
			//int col = userInput.charAt(1) - 0;
			
			int row = reader.nextInt();
			int col = reader.nextInt();
			gameBoard[row - 1][col - 1] = player;
			drawBoard(gameBoard);
		}
		
	}
	
	public void drawBoard(char[][] gameBoard)
	{
		System.out.print(line + "\n|  "); //initial line
		
		for(int i = 0; i < 3; i++) 
		{
			for(int j = 0; j < 3; j++) //goes through each row and prints out symbols
			{
			    System.out.print( gameBoard[i][j] + "  |  ");
				
				if(j == 2) //prints partition if at end of row 
				{
					System.out.print("\n" + line + "\n|  ");
				}

			}
		}
	}
	
}
