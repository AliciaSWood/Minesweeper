package Minesweeper;

import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
	// Created two 2D arrays, each with 10 rows and 10 columns
		private static char[][] fieldDisplayed = new char[10][10];
		private static int [][]  fieldHidden = new int[10][10];
		
	//Key:
		// 9 = mine
		// 0 = blank space
		//1-8 = number of mines around tile
		// r = row, c = column
		
				
		public static void main(String[] args) {

			// Set up hidden minefield
			createMinefield();
			// Put neighbour function into minefield
			totalMinefield();
			
			// If uncommented, will reveal the minefield for testing
//			printIntGrid(fieldHidden);
			
			//This method sets the visual array to be filled with 'X'
			visualArray(fieldDisplayed);

			
			// Prints 'MineSweeper' and the minefield for the user to see
			newGame();			
			
			// A boolean that checks if player has hit a mine(returns false, and game is over) or true (which prompts the player to go again)
			boolean checkPlayer = false;
	
			while(checkPlayer) {	
				// Checks if player has set off mine
				checkPlayer = playerInput();
			} 
			
			System.out.println("Game Over");

		}
		
		
		public static void newGame() {
			System.out.println("\t  Minesweeper\n");
			boolean checkPlayer = true;
			
			while(checkPlayer) {	
				checkPlayer = playerInput();
			}

		}
		

		// Grid for Minesweeper
		public static void printGrid(char[][] grid) {
			for(char r = 0; r <grid.length; r++) {
				for(char c = 0; c < grid[r].length; c++) {
					
					// Add a space after each item
					System.out.print( " " + grid[r][c] + " ");
					
				}
				// Print a new line at the end of each row
				System.out.println();
			}
			
		}
		
		// Method to fill char array with 'X' to begin with.
		public static void visualArray(char[][] grid) {
			for(char r = 0; r <grid.length; r++) {
				for(char c = 0; c < grid[r].length; c++) {
					grid[r][c] = 'X';
				
				}
			}
		}
		
		// Prints out the hidden int array. Used only to check int array is filled with mines and correct numbers.
		public static void printIntGrid(int[][] grid) {
			// r = row, c = column
			for(char r = 0; r <grid.length; r++) {
				for(char c = 0; c < grid[r].length; c++) {
					// Add a space after each item					
					System.out.print( " " + grid[r][c] + " ");
				}
				// Print a new line at the end of each row
				System.out.println();
			}	
		}
		
		
		// Randomly generate coordinates for mines
		public static void createMinefield() {
			int setMineCount = 0;
			
			// To add 10 mines
			while(setMineCount!=10) {
				// An instance of Java Random class is used to generate
				// random numbers, which will be random coordinates for the mines
				Random random = new Random();
				
				// nextInt is used to get the next random integer value
				// from this random number generators sequence (0-9)
				int r = random.nextInt(10);
				int c = random.nextInt(10);

				// Assign value of 9 to each Mine
				fieldHidden[r][c] = 9;
				// Increase count by 1 each time, so no infinite loop
				setMineCount++;				
			}
			
		}
		
		// Build the rest of the field, and setting numbers for tiles alongside mines
					public static void totalMinefield()
				    {
						// Consider row as y axis, column as x axis (logically should be other way around, but easier to visualise this way)
				        for(int row = 0; row <10; row++)
				        {
				            for(int column = 0; column <10; column++)
				            {
				                int count = 0;
				                // If the coordinate is not equal to 9, it is therefore not a mine.
				                // We will check its neighbours. So if it does not equal
				                // 9, we will check the surrounding tiles and increase count by 1
				                // for each time a mine occupies a nearby tile
				                
				                if(fieldHidden[row][column]!= 9)
				                {
				                	// If row isn't 0, there will be a tile above that we can check for mine
				                    if(row != 0)
				                    {
				                        if(fieldHidden[row-1][column] == 9) count++;   //Top
				                        // If column isn't 0, we can go top left
				                        if(column != 0)
				                        {
				                            if(fieldHidden[row-1][column-1]==9) count++;  // Top left
				                        }

				                    }
				                    // If row isn't 9, we can check bottom tile
				                    if(row!=9)
				                    {
				                        if(fieldHidden[row+1][column] == 9) count++;  // Bottom
				                        // If column also isn't 9, we can check bottom right
				                        if(column != 9)
				                        {
				                            if(fieldHidden[row+1][column+1]==9) count++; // Bottom right
				                        }
				                    }
				                    //If column isn't 0, we can check left tile
				                    if(column != 0)
				                    {
				                        if(fieldHidden[row][column-1] == 9) count++; // Left
				                        // If row also isn't 9, we can check bottom left
				                        if(row != 9)
				                        {
				                            if(fieldHidden[row+1][column-1] == 9) count++;  // Bottom Left
				                        }
				                    }
				                    // If column isn't 9
				                    if(column != 9)
				                    {
				                    	// We can access right tile
				                        if(fieldHidden[row][column+1] == 9) count++;
				                        // If row also isn't 0, we can can check top right tile
				                        if(row != 0)
				                        {
				                            if(fieldHidden[row-1][column+1] == 9) count++; // Top Right
				                        }
				                    }

				                    fieldHidden[row][column] = count;
				                }
				            }
				        }

				    }


		// Player input
		// Take player coordinate row and column
		// Compare these coordinates with the hidden array minefield. 
		// If the coordinate matches up with a value 9 (mine) print 'boom' and end game
		// Else expose hidden array tile (will be a number between 0-9)
		// Ensure player enters a number, ensure number is between 0 and 9
		
		public static boolean  playerInput(){
			printGrid(fieldDisplayed);
			int turnCount = 1;
	        Scanner player= new Scanner(System.in);
	        // Ask user for row number, store in 'row'
	        System.out.print("\nEnter Row Number (0-9): ");
	        int row = player.nextInt();
	        // Ask user for column number, store in 'column'
	        System.out.print("Enter Column Number (0-9): ");
	        int column = player.nextInt();
	        
	        try {
	        	 if(turnCount == 90) {
	 				System.out.println("Congratulations, you won!");
	 				return false;
	 	        
	 		        // If the row and column coordinate equals 9, they have chosen a mine. 
	 		        // Print boom!
	 	        }
	 	        if(fieldHidden[row][column] == 9)
	 	        {
	 	            System.out.println("boom!");
	 	             return false; 
	 	        }
	 	        else if(fieldHidden[row][column]==0) {
	 	        	fieldDisplayed[row][column] = '0';
	 	        }
	 	       
	 	        // Otherwise, the displayed array should change to the hidden array value at that coordinate.
	 	        else {
	 	        	// char array  =  (cast to char) int array
	 	        	fieldDisplayed[row][column] =  (char)(fieldHidden[row][column] + '0');
	 	  	     // A count that, once the amount of turns user has made is 90 (all tiles revealed except for 10 mines of the 100 tile grid),
	 	  	     //prints that player has won
	 	  	     // 90 guesses = win
	 	        	turnCount++;
	 	        }
	 	        return true;
	        } catch(Exception e) {
	        	// If user uses wrong input type or number isn't between 0-9
	        	System.out.println("Invalid input - please try again!\n");
	        	return true;
	        }
	        	       
	    }

}
