package Minesweeper;

import java.util.Random;
import java.util.Scanner;


// Random coordinates for mines  - done
// Basic grid format - done
// Put mines into fieldHidden, then print to test - done
// Take user input:
// If [r][c] == 10, end game and print 'boom' - prints boom, game isn't repeating
// If [r][c] is close to mine, increase its count - done
// else print blank " " ?
// Need boolean method that tests if game is finished, if false game continues,
// if true print 'You won' and end game
// Need to link hidden and visible array, so that user input at x coordinate 
// is pulled from hidden array and shown on visible array - done?






public class MineSweeper {
	
	// Created two 2D arrays, each with 10 rows and 10 columns
	private static int[][] fieldDisplayed = new int[10][10];
	private static int[][]  fieldHidden = new int[10][10];
	

	public static void main(String[] args) {
	
		createMinefield();
		totalMinefield();
		printGrid(fieldHidden);
		newGame();
		
		
		boolean checkPlayer = true;
		
		while(checkPlayer) {
			
			checkPlayer = playerInput();
		}
		
		
		
		
	}
	
	public static void newGame() {
		System.out.println("\t  Minesweeper\n");
		
//		if(playerInput() == false) {
//			
//		} 
	}
	
	
	
	// Grid for Minesweeper
	public static void printGrid(int[][] grid) {
		// r = row, c = column
		for(int r = 0; r <grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
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
		
		// To add 10 bombs
		while(setMineCount!=10) {
			// An instance of Java Random class is used to generate
			// random numbers, which will be random coordinates for the mines
			Random random = new Random();
			// nextInt is used to get the next random integer value
			// from this random number generators sequence (0-9)
			int r = random.nextInt(10);
			int c = random.nextInt(10);
//			System.out.println("Bomb Number: " + MineNumber +  " r: " + r + " c: " + c);
			// Assign value of 10 to each bomb
			fieldHidden[r][c] = 10;
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
                
                // If the coordinate is not equal to 10, it is therefore not a mine.
                // We will check its neighbours. So if it does not equal
                // 10, we will check the surrounding tiles and increase count by 1
                // for each time a mine occupies a nearby tile
                if(fieldHidden[r][c]!=10)
                {
                	// If the row isn't row 0, it's safe to change left and upper left
                    if(r!=0)
                    {
                        if(fieldHidden[r-1][c]==10) count++; // left
                        if(c!=0)
                        {
                            if(fieldHidden[r-1][c-1]==10) count++; // top left corner
                        }

                    }
                  
                    if(r!=9)
                    {
                        if(fieldHidden[r+1][c]==10) count++; // right
                        
                        if(c!=9)
                        {
                            if(fieldHidden[r+1][c+1]==10) count++; // bottom right
                        }
                    }
                    // If column isn't column 
                    if(c!=0)
                    {
                        if(fieldHidden[r][c-1]==10) count++; // top
                        if(r!=9)
                        {
                            if(fieldHidden[r+1][c-1]==10) count++; // top right
                        }
                    }
                    if(c!=9)
                    {
                        if(fieldHidden[r][c+1]==10) count++; //bottom
                        if(r!=0)
                        {
                            if(fieldHidden[r-1][c+1]==10) count++; // bottom left
                        }
                    }

                    fieldHidden[r][c] = count;
                }
            }
        }

    }
	
	// Build the rest of the field, and setting numbers for tiles alongside mines
	public static void totalMinefiel() {
		// Build our hidden 2D array, which has the bombs and numbers 
		//and is concealed from the user
		for(int r=0; r<10; r++) {
            for(int c=0; c<10; c++) {
            	// Set initial count to 0
                int count = 0;
                
                // If the coordinate is not equal to 10, it is therefore not a mine.
                // We will check its neighbours. So if it does not equal
                // 10, we will check the surrounding tiles and increase count by 1
                // for each time a mine occupies a nearby tile
                
                 
                if(fieldHidden[r][c]!= 10 ) {
                	// And the neighbours
                	// If tile is not at point 0 or 9 (corners, or sides) so in the middle, check every tile around it
                	if((r != 0 && r!= 9) && (c !=0  && c !=9))
                		if(fieldHidden[r-1][c-1] == 10) count++; // top left corner
                		if(fieldHidden[r][c-1] == 10) count++; // top
                		if(fieldHidden[r+1][c-1] == 10) count ++; // top right
                		if(fieldHidden[r-1][c] ==10) count++; // left
                		if(fieldHidden[r+1][c] ==10) count++; // right
                		if(fieldHidden[r-1][c+1] ==10) count++; // bottom left
                		if(fieldHidden[r][c+1] ==10) count++;	//bottom
                		if(fieldHidden[r+1][c+1] ==10) count++; // bottom right
                		// If tile is top left corner
                } else if((r == 0) && (c == 0)) {
                	if(fieldHidden[r+1][c] ==10) count++; // right
                	if(fieldHidden[r][c+1] ==10) count++;	//bottom
            		if(fieldHidden[r+1][c+1] ==10) count++; // bottom right
            		// If tile is top right corner
                } else if((r==0) && (c==9)) {
                	if(fieldHidden[r-1][c] ==10) count++; // left
                	if(fieldHidden[r-1][c+1] ==10) count++; // bottom left
            		if(fieldHidden[r][c+1] ==10) count++;	//bottom
            		// If tile is bottom right corner
                } else if((r ==9) && (c==9)) {
                	if(fieldHidden[r-1][c-1] == 10) count++; // top left corner
            		if(fieldHidden[r][c-1] == 10) count++; // top
            		if(fieldHidden[r-1][c] ==10) count++; // left
            		// If tile is bottom left corner
                } else if ((r==9) && (c==0)) {
                	if(fieldHidden[r][c-1] == 10) count++; // top
            		if(fieldHidden[r+1][c-1] == 10) count ++; // top right
            		if(fieldHidden[r+1][c] ==10) count++; // right
                } else if(r == 0) {
                	if(fieldHidden[r-1][c] ==10) count++; // left
            		if(fieldHidden[r+1][c] ==10) count++; // right
            		if(fieldHidden[r-1][c+1] ==10) count++; // bottom left
            		if(fieldHidden[r][c+1] ==10) count++;	//bottom
            		if(fieldHidden[r+1][c+1] ==10) count++; // bottom right
                } else if(r==9) {
                	if(fieldHidden[r-1][c-1] == 10) count++; // top left corner
            		if(fieldHidden[r][c-1] == 10) count++; // top
            		if(fieldHidden[r+1][c-1] == 10) count ++; // top right
            		if(fieldHidden[r-1][c] ==10) count++; // left
            		if(fieldHidden[r+1][c] ==10) count++; // right
                } 
                
                
                fieldHidden[r][c] = count;
            
            }
        }
	}
	
	
	

	
	

	// Player input
	// Take player coordinate row (r) and column (c)
	// Compare these coordinates with the hidden array minefield. 
	// If the coordinate matches up with a value 10 (mine) print 'boom' and end game
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
            System.out.println("\nInvalid input. Must be between a number 0 and 9");
        
        }
        // If the row and column coordinate equals 10, they have chosen a mine. 
        // Print boom!
        if(fieldHidden[row][column]==10)
        {
            System.out.println("boom!");
             return false;
            
        }
        else if(fieldHidden[row][column]==0) {
//        	fieldDisplayed[row][column] = ""; Need to convert to a String array?
        }
        
        // Otherwise, the displayed array should change to the hidden array value at that coordinate. 
        else fieldDisplayed[row][column] = fieldHidden[row][column];
        printGrid(fieldDisplayed);
        return true;
        
    }
	
	

}
