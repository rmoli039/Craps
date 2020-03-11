/*=============================================================================  
|   Source code:  Analyzer.java
|        Author:  Richard Molina 
|    Student ID:  6140544  
|    Assignment:  Program #3 - Craps
|  
|            Course:  COP 3337 (Intermediate Programming)  
|           Section:  U08  
|        Instructor:  William Feild  
|          Due Date:  9 October 2018, at the beginning of class
|
|	I hereby certify that this collective work is my own
|	and none of it is the work of any other person or entity.
|	______________________________________ [Signature]
|  
|     Language:  Java 
|  Compile/Run:   
| 		javac Analyzer.java Die.java Craps.java
|		java Analyzer
| 
|  +-----------------------------------------------------------------------------  
|  
|  Description:  This program asks the user to input a number of Craps games to
|				 analyze, between 1 and 1000000. The program then uses a loop to
|				 play that many Craps games from the Craps class, recording the
|				 outcome and total rolls made of each game played. The program
|				 tracks statistics for the games played, and provides static
|				 methods to calculate more statistics. These statistics are then
|				 displayed to the user in neat boxes for easy understanding.
|  
|        Input:  The user is required to enter an integer between 1 and 1000000.
|				 If the user fails to enter an integer, an error will display
|				 reminding the user to input an integer. If the user fails to
|				 enter an integer within the bounds, an error will display
|			 	 reminding the user to input a number within the bounds.
|  
|       Output:  The program will display a variety of gathered, calculated, and
|				 expected statistics about the number of games of Craps played
|				 based on the user's input. 17 stats are displayed in organized
|				 and easy-to-read boxes, allowing for comparing and validation.
|                All output is displayed in the console.  
|  
|      Process:  The program's steps are as follows:  
|  
|                1.  The program displays its purpose  
|                2.  The user is prompted to enter the number of Craps games to play
|                3.  The user's input is validated, and a for loop is run to play
|					 that many Craps games from the Craps class.
|                4.  Win and roll statistics are tracked in the loop for each game.
|				 5.  The longest roll is also tracked, and every game's length is
|					 tallied in an array.
|				 6.  Static output methods are called, utilizing the gathered stats
|					 as well as other static computation methods to display
|					 17 statistics about all the games played in separate, neat boxes.
|  
|   Required Features Not Included:  All required features are included.  
|  
|   Known Bugs:  None; the program operates correctly. 
|
|  *===========================================================================*/

import java.util.Scanner;	//Needed to receive user input

public class Analyzer
{
	public static void main(String[] args)
	{
		int totalGames = 0;
		int totalWins = 0;
		int totalComingOutWins = 0;
		int totalRolls = 0;
		int longestGame = 0;
		int[][] gameLengthTally = new int[21][2];
		//Initialized with 21 rows, where the last row represents values 21+, to prevent extensive tallying 
		
		Craps game = new Craps();
		
		totalGames = getInput();
		for (int counter = 0; counter < totalGames; counter++)
		{
			game.playGame();
			if (game.getGameResult())
			{
				totalWins++;
				if (game.getTotalRolls() == 1)
				{
					//A 'coming out' roll is the first roll of the game, when rolls = 1
					totalComingOutWins++;
				}
			}
			
			totalRolls += game.getTotalRolls();
			
			if (game.getTotalRolls() > longestGame)
			{
				longestGame = game.getTotalRolls();
			}
			
			if (game.getTotalRolls() >= 21)
			{
				//The 21st row represents all game lengths that had 21+ rolls
				gameLengthTally[20][1]++;
			}
			else
			{
				gameLengthTally[game.getTotalRolls() - 1][1]++;
			}
		}
		
		printSummaryOfGame(totalGames, totalRolls, longestGame);
		printSummaryOfWin(totalGames, totalWins, totalComingOutWins, gameLengthTally);
		printSummaryOfEnding(totalGames, gameLengthTally);
		printGameLengthTally(totalGames, gameLengthTally);
	}

	/*---------------------------- getInput ----------------------------
    |  Method getInput ()
    |
    |  Purpose:  To receive user input for a number of Craps games to play
    |			 and to validate before accepting it.
    |
    |  @param	
    |
    |  @return  the number of games the user entered
    *-------------------------------------------------------------------*/

	public static int getInput()
	{
		Scanner userInput = new Scanner(System.in);
		
		boolean valid = false;
		int numGames = 0;
		
		do
		{
			System.out.println("Analyze up to a million Craps games.");
			System.out.print("Enter the # of games as an integer between 1 and 1000000,  inclusive: ");
			valid = userInput.hasNextInt();
			
			if(valid)
			{
				numGames = userInput.nextInt();
				if(numGames < 1 || numGames > 1000000)
				{
					valid = false;
					System.out.println("Error. Input must be between 1 and 1000000,  inclusive.");
				}	//close if
			}	//close if
			else
			{
				userInput.next();
				System.out.println("Error. Input must be an integer.");
			}	//close else
		} while (!valid);	//close do while
		
		System.out.println("");
		return numGames;
	}	//close getInput method

	/*---------------------------- averageRolls ----------------------------
    |  Method averageRolls (rolls, games)
    |
    |  Purpose:  Calculate and return the average number of rolls thrown per
    |			 game of Craps played. Calculated by dividing the total rolls
    |			 thrown in all games by the total number of games played.
    |
    |  @param	rolls the total rolls thrown in all games
    |			
    |			games the total number of games played
    |
    |  @return  the average number of rolls thrown per game of Craps played
    *-------------------------------------------------------------------*/
	public static double averageRolls(int rolls, int games)
	{
		return (double)rolls / games;
	}	//close averageRolls method

	/*---------------------------- winningOutcome ----------------------------
    |  Method winningOutcome (wins, games)
    |
    |  Purpose:  Calculate and return the experimental probability of winning
    |			 a game of Craps. Calculated by dividing the player's total wins
    |			 by the total number of games played.
    |
    |  @param	wins the total number of games won
    |			
    |			games the total number of games played
    |
    |  @return  the experimental probability of winning a game of Craps
    *-------------------------------------------------------------------*/
	public static double winningOutcome(int wins, int games)
	{
		return (double)wins / games;
	}	//close winningOutcome method

	/*---------------------------- winningComingOutOutcome ----------------------------
    |  Method winningComingOutOutcome (comingOutWins, comingOutGames)
    |
    |  Purpose:  Calculate and return the experimental probability of winning
    |			 a game of Craps on the first roll ('coming out' roll). Calculated 
    |			 by dividing the player's total 'coming out' wins by the total
    |			 number of games that ended on the 'coming out' roll. The method
    |			 checks that the number of 'coming out games' != 0 to prevent
    |		     division by 0, returning a probability of 0.0 if it is.
    |
    |  @param	comingOutWins the total number of wins on the 'coming out' roll
    |			
    |			comingOutGames the total number of games that ended on the
    |						   'coming out' roll
    |
    |  @return  the experimental probability of winning a game of Craps on the
    |			'coming out' roll
    *-------------------------------------------------------------------*/
	public static double winningComingOutOutcome(int comingOutWins, int comingOutGames)
	{
		if (comingOutGames != 0)   //Prevents division by 0
		{
			return (double)comingOutWins / comingOutGames;
		}	//close if
		else
		{
			return 0.0;
		}	//close else
	}	//close winningComingOutOutcome method
	
	/*---------------------------- comingOutOutcome ----------------------------
    |  Method comingOutOutcome (comingOutGames, games)
    |
    |  Purpose:  Calculate and return the experimental probability of a game of
    |			 Craps ending on the 'coming out' roll. Calculated by dividing the
    |			 total number of games that ended on the 'coming out' roll by the
    |			 total number of games played.
    |
    |  @param	comingOutGames the total number of games that ended on the
    |						   'coming out' roll
    |			
    |			games the total number of games played
    |
    |  @return  the experimental probability of a game ending on the
    |			'coming out' roll
    *-------------------------------------------------------------------*/
	public static double comingOutOutcome(int comingOutGames, int games)
	{
		return (double)comingOutGames / games;
	}	//close comingOutOutcome method
	
	/*---------------------------- continuingOnOutcome ----------------------------
    |  Method continuingOnOutcome (comingOutGames, games)
    |
    |  Purpose:  Calculate and return the experimental probability of a game of
    |			 Craps not ending on the 'coming out' roll. Calculated by
    |			 subtracting the total number of games that ended on the 'coming out'
    |			 roll from the total number of games played, and then dividing by
    |			 the total number of games played.
    |
    |  @param	comingOutGames the total number of games that ended on the
    |						   'coming out' roll
    |			
    |			games the total number of games played
    |
    |  @return  the experimental probability of a game not ending on the
    |			'coming out' roll
    *-------------------------------------------------------------------*/
	public static double continuingOnOutcome(int comingOutGames, int games)
	{
		return (double)(games - comingOutGames) / games;
	}	//close continuingOnOutcome method
	
	/*---------------------------- printDashBreak ----------------------------
    |  Method printDashBreak (dashes)
    |
    |  Purpose:  Prints a row of dashes to the console at the inputted
    |			 length. Used to break lines and separate sections.
    |
    |  @param	dashes the number of dashes to print in a row
    |
    |  @return  void
    *-------------------------------------------------------------------*/
	public static void printDashBreak(int dashes)
	{
		for (int counter = 0; counter < dashes; counter++)
		{
			System.out.print("-");
		}	//close for
		System.out.println("");
	}	//close printDashBreak method
	
	/*---------------------------- printSummaryOfGame ----------------------------
    |  Method printSummaryOfGame (games, rolls, longest)
    |
    |  Purpose:  Prints a summary of game statistics for all of the games of
    |			 Craps played. Includes the total games played, the total rolls
    |			 thrown, the average number of rolls thrown per game, and the
    |			 length of the longest game.
    |
    |  @param	games the total number of games played
    |
    |			rolls the total rolls thrown in all games
    |
    |			longest the length of the longest game in rolls
    |
    |  @return  void
    *-------------------------------------------------------------------*/
	public static void printSummaryOfGame(int games, int rolls, int longest)
	{
		System.out.println("Summary of Game Statistics:");
		printDashBreak(30);
		System.out.printf("Total Games%17d%n", games);
		System.out.printf("Total Rolls%17d%n", rolls);
		System.out.printf("Average Rolls%15.4f%n", averageRolls(rolls, games));
		System.out.printf("Longest Game%16d%n", longest);
		printDashBreak(30);
		System.out.println("");
	}	//close printSummaryOfGame method
	
	/*---------------------------- printSummaryOfWin ----------------------------
    |  Method printSummaryOfWin (games, wins, comingOutWins, tally)
    |
    |  Purpose:  Prints a summary of win statistics for all of the games of
    |			 Craps played. Includes the numerical total, the experimental
    |			 probability, and the expected probability of winning, winning on
    |			 the 'coming out' throw, and playing 'coming out' games.
    |
    |			 The expected probability of winning a game of Craps was found
    |			 online.
    |
    |			 Source: http://mathworld.wolfram.com/Craps.html
    |
    |   		 The expected probability of winning a game of Craps on the
    |			 'coming out' roll was calculated by counting the number of dice
    |			 combinations that form 7 and 11 (8) and dividing by the total number
    |			 of combinations (36). 8 / 36 = 0.2222.
    |
    |   		 The expected probability of ending a game of Craps on the
    |			 'coming out' roll was calculated by counting the number of dice
    |			 combinations that form 2, 3, 7, 11, & 12 (12) and dividing by the total number
    |			 of combinations (36). 12 / 36 = 0.3333.
    |
    |
    |  @param	games the total number of games played
    |
    |			wins the total number of games won
    |
    |			comingOutWins the total number of wins on the 'coming out' roll
    |
    |		 	tally the array of tallied game lengths
    |
    |  @return  void
    *-------------------------------------------------------------------*/
	public static void printSummaryOfWin(int games, int wins, int comingOutWins, int[][] tally)
	{
		System.out.println("Summary of Win Statistics:");
		printDashBreak(75);
		System.out.printf("%4s%25s%22s%21s%n","STAT","GAMES","OUTCOME","EXPECTED");
		printDashBreak(75);
		System.out.printf("Total Wins%20d%20.4f%20s%n", wins, winningOutcome(wins, games),"0.4929");
		System.out.printf("Coming Out Wins%15d%20.4f%20s%n", comingOutWins, winningComingOutOutcome(comingOutWins, tally[0][1]),"0.2222");
		System.out.printf("Coming Out Games%14d%20.4f%20s%n", tally[0][1], comingOutOutcome(tally[0][1], games),"0.3333");
		printDashBreak(75);
		System.out.println("");
	}	//close printSummaryOfWin method
	
	/*---------------------------- printSummaryOfEnding ----------------------------
    |  Method printSummaryOfEnding (games, tally)
    |
    |  Purpose:  Prints a summary of end statistics for all of the games of
    |			 Craps played. Includes the numerical total, the experimental
    |			 probability, and the expected probability of playing a game that
    |			 lasts longer than 1 roll.
    |
    |   		 The expected probability of playing a game of Craps that lasts longer
    |			 than 1 roll was calculated by counting the number of dice
    |			 combinations that don't form 2, 3, 7, 11, & 12 (24) and dividing by
    |			 the total number of combinations (36). 24 / 36 = 0.6667.
    |
    |  @param	games the total number of games played
    |
    |		 	tally the array of tallied game lengths
    |
    |  @return  void
    *-------------------------------------------------------------------*/
	public static void printSummaryOfEnding(int games, int[][] tally)
	{
		System.out.println("Summary of Ending Statistics:");
		printDashBreak(74);
		System.out.printf("%4s%25s%22s%21s%n","STAT","GAMES","OUTCOME","EXPECTED");
		printDashBreak(74);
		System.out.printf("Continuing On Games%11d%20.4f%20s%n", (games - tally[0][1]), continuingOnOutcome(tally[0][1], games),"0.6667");
		printDashBreak(74);
		System.out.println("");
	}	//close printSummaryOfEnding method
	
	/*---------------------------- printGameLengthTally ----------------------------
    |  Method printGameLengthTally (games, tally)
    |
    |  Purpose:  Prints a vertically oriented table of game lengths by number of rolls
    |			 and the tally of games that ended at each game length. The 21st row 
    |			 represents all game lengths that had 21+ rolls.
    |
    |  @param	games the total number of games played
    |
    |		 	tally the array of tallied game lengths
    |
    |  @return  void
    *-------------------------------------------------------------------*/
	public static void printGameLengthTally(int games, int[][] tally)
	{
		System.out.println("Summary of Game Length in Rolls:");
		printDashBreak(32);
		System.out.printf("%5s%25s%n","ROLLS","# OF GAMES");
		printDashBreak(32);
		
		for (int row = 0; row < (tally.length - 1); row++) {
				System.out.printf("%2d%25d%n", (row+1), tally[row][1]);
		}	//close for
		
		System.out.printf("%2s%24d%n", (tally.length + "+"), tally[tally.length - 1][1]);
		printDashBreak(32);
		System.out.printf("%5s%22d%n","TOTAL", games);
		printDashBreak(32);
	}	//close printGameLengthTally method
}