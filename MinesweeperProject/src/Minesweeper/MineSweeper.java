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
		
				
		public static void main(String[] args) {
		
			
			
			// Set up hidden minefield
			createMinefield();
			// Put neighbour function into minefield
			totalMinefield();
			
			// If uncommented, will reveal the minefield for testing
			printIntGrid(fieldHidden);
			
			//This method sets the visual array to be filled with 'X'
			visualArray(fieldDisplayed);

			
			// Prints 'MineSweeper' and the minefield for the user to see
			newGame();
			
			// A count that, once the amount of turns user has made is 90 ()so all tiles revealed except for 10 mines of the 100 tile grid),
			//prints that player has won
			int turnCount = 0;
			
			// A boolean that checks if player has hit a mine(returns false, and game is over) or true (which prompts the player to go again)
			boolean checkPlayer = false;
	
			while(checkPlayer) {
				
				// Checks if player has set off mine
				checkPlayer = playerInput();
				
				turnCount++;
			} 
			
			System.out.println("Game Over");
			
			if(turnCount ==90) {
				System.out.println("Congratulations, you won!");
			}
			
			
			
		}
		// 90 guesses = win
		
		public static void newGame() {
			System.out.println("\t  Minesweeper\n");
	boolean checkPlayer = true;
			
			while(checkPlayer) {
				
				checkPlayer = playerInput();
			}

		}
		
		
		
		// Grid for Minesweeper
		public static void printGrid(char[][] grid) {
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
			int MineNumber = 1;
			
			// To add 10 mines
			while(setMineCount!=10) {
				// An instance of Java Random class is used to generate
				// random numbers, which will be random coordinates for the mines
				Random random = new Random();
				// nextInt is used to get the next random integer value
				// from this random number generators sequence (0-9)
				int r = random.nextInt(10);
				int c = random.nextInt(10);
//				System.out.println("Mine Number: " + MineNumber +  " row: " + r + " column: " + c);
				// Assign value of 9 to each Mine
				fieldHidden[r][c] = 9;
				// Increase count by 1 each time, so no infinite loop
				setMineCount++;
				MineNumber++;
				
			}
			
		}
		
		// Build the rest of the field, and setting numbers for tiles alongside mines
		public static void totalMinefield() {
	        for(int r=0; r<10; r++)
	        {
	            for(int c=0; c<10; c++)
	            {
	            	// Counter for each tile
	                int count=0;
	                
	                // If the coordinate is not equal to 9, it is therefore not a mine.
	                // We will check its neighbours. So if it does not equal
	                // 9, we will check the surrounding tiles and increase count by 1
	                // for each time a mine occupies a nearby tile
	                if(fieldHidden[r][c]!=9)
	                {
	                	
	                    if(r!=0)
	                    {
	                        if(fieldHidden[r-1][c]==9) count++; // left
	                        if(c!=0)
	                        {
	                            if(fieldHidden[r-1][c-1]==9) count++; // top left corner
	                        }

	                    }
	                  
	                    if(r!=9)
	                    {
	                        if(fieldHidden[r+1][c]==9) count++; // right
	                        
	                        if(c!=9)
	                        {
	                            if(fieldHidden[r+1][c+1]==9) count++; // bottom right
	                        }
	                    }
	                    
	                    if(c!=0)
	                    {
	                        if(fieldHidden[r][c-1]==9) count++; // top
	                        if(r!=9)
	                        {
	                            if(fieldHidden[r+1][c-1]==9) count++; // top right
	                        }
	                    }
	                    if(c!=9)
	                    {
	                        if(fieldHidden[r][c+1]==9) count++; //bottom
	                        if(r!=0)
	                        {
	                            if(fieldHidden[r-1][c+1]==9) count++; // bottom left
	                        }
	                    }

	                    fieldHidden[r][c] = count;
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
	        Scanner player= new Scanner(System.in);
	        // Ask user for row number, store in 'row'
	        System.out.print("\nRow Number (0-9): ");
	        int row = player.nextInt();
	        // Ask user for column number, store in 'column'
	        System.out.print("Column Number (0-9): ");
	        int column = player.nextInt();

	        // Check user puts in numbers between 0-9, if not tell them they need to
	        if(row<0 || row>9 || column<0 || column>9)
	        {
	            System.out.println("\nINVALID INPUT. Must be between a number 0 and 9\n");
	            playerInput();
	        }
	        // If the row and column coordinate equals 9, they have chosen a mine. 
	        // Print boom!
	        if(fieldHidden[row][column]==9)
	        {
	            System.out.println("boom!");
	            
//	            if(fieldHidden[row][column]==9) {
//	            	fieldDisplayed[row][column] = 'O';
//	            }
	             return false;
	            
	        }
	        else if(fieldHidden[row][column]==0) {
//	        	fieldDisplayed[row][column] = ""; Need to convert to a String array?
	        	fieldDisplayed[row][column] = '0';
	        }
	        
	        // Otherwise, the displayed array should change to the hidden array value at that coordinate. 
	        
	        else {
	        	// char array  =  (cast to char) int array
	        	fieldDisplayed[row][column] =  (char)(fieldHidden[row][column] + '0');
	        }

	        return true;
	        
	    }
		

}
