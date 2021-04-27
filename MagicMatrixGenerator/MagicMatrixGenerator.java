// -------------------------------------------------------
// Assignment 3
// Written by: Nolan Bastien [40179166]
// For COMP 248 Section UA � Winter 2021
// --------------------------------------------------------
//
// This program randomly generates 3x3 matrices. 
// When a magic matrix is generated, the program outputs it.
//

public class Question_1 {
	
	public static void main(String[] args) {
		
		// Welcome message
		
		System.out.println("***********************************************");
		System.out.println("  Welcome to Magic Matrix Generator Program!");
		System.out.println("***********************************************");
		System.out.println();
		
		// Variable declaration

		final int MATRIX_SIZE = 3;
		int[][] matrix = new int[MATRIX_SIZE][MATRIX_SIZE];
		int[] countNumbers = new int[11];
		
		int[] sumRows = new int[3];
		int[] sumColumns = new int[3];
		int[] sumDiagonals = new int[3];
		
		boolean isMagic = false;
		boolean eachNumberOccurs = false;
		
		int countEqualities = 0;
		
		// Do the following until the matrix is magic
		
		do{	
			
		// Randomly generate matrix
			
		for(int i = 0; i < MATRIX_SIZE; i++){
			for(int j = 0; j < MATRIX_SIZE; j++){
				
				matrix[i][j] = (int)(Math.random() * 10);
				
			}
		}
		
		// Check if the matrix is magic
		
		// (Condition 1) Does each number 1, 2, 3, ... 9 occur in the matrix?
		
		// Set countNumbers to 0
		
		for(int i = 1; i <= 9; i++)
			countNumbers[i] = 0;
		
		// Loop through the matrix
		
		for(int i = 0; i < MATRIX_SIZE; i++){
			for(int j = 0; j < MATRIX_SIZE; j++){
				
				// For each number 1 to 9, when the number occurs,
				// add one to the corresponding index of countNumbers array
				
				countNumbers[ matrix[i][j] ]++;
				
			}
		}
		
		// Set eachNumberOccurs to true, then...
		
		eachNumberOccurs = true;
		
		for(int i = 1; i <= 9; i++){
			
			// .. set to false if a number does not occur
			
			if(countNumbers[i] == 0) {
				eachNumberOccurs = false;
			}
				
				
		}

		
		// (Condition 2) Are the sums of the rows, columns, and diagonals equal to each other?
		
		// Check the following only if the the first condition (eachNumberOccurs) is met
		
		if(eachNumberOccurs){
		
		// Set sums to 0 through each do loop
		
		for(int i = 0; i < 3; i++){
		
			sumRows[i] = 0;
			sumColumns[i] = 0;
			sumDiagonals[i] = 0;
		}
		
		for(int i = 0; i < MATRIX_SIZE; i++){
			
			for(int j = 0; j < MATRIX_SIZE; j++){
				
				// Sum the values at position (i, j) in sumRows at index i (current row) 
				// and in sumColumns at the index j (current column)
				
				sumRows[i] += matrix[i][j];
				sumColumns[j] += matrix[i][j];
				
				// Add values of the diagonal where i == j to sumDiagonals at index 0
				
				if(i == j)
					sumDiagonals[0] += matrix[i][j];
				
				// Add values of the diagonal where i + 2 == j to sumDiagonals at index 1. 
				// (i + j == 2 only on for the second diagonal).
				
				if(i + j == 2)
					sumDiagonals[1] += matrix[i][j];
				
				
			}
		}
		
		// Check if sum of each rows are equal together.
		
		// Set the count of equalities to 0.
		
		countEqualities = 0;
		
		
		for(int i = 0; i < 3; i++){
			
			if(sumRows[0] == sumRows[i]) countEqualities++; // If the matrix is magic, the count will be at 3.
				
		}
		
		for(int i = 0; i < 3; i++){
		
			if(sumColumns[0] == sumColumns[i]) countEqualities++; // If the matrix is magic, the count will be at 6.
			
		}
		
		if(sumDiagonals[0] == sumDiagonals[1])
			countEqualities++; // If the matrix is magic, the count will be at 7.
		
		if(sumRows[0] == sumColumns[0] && sumRows[0] == sumDiagonals[0])
			countEqualities++; // If the matrix is magic, the count will be at 8.
				
		// If count is at 8, all the sums are equal to each other.
		
		// If the two conditions are met, the matrix is magic.
		
		if(countEqualities == 8 && eachNumberOccurs)
			isMagic = true;
		
		} // End of second condition
		
		// If matrix is magic, then print.
		
		if(isMagic){
		
		System.out.println("The randomly generated matrix is:");
		
		for(int i = 0; i < MATRIX_SIZE; i++) {
			for(int j = 0; j < MATRIX_SIZE; j++) {
				
				System.out.print("\t" + matrix[i][j]);
			}
			
			System.out.println();
		}
		
		}
		
		
		} while(!isMagic);
		
		// Closing message
		
		System.out.println("\nThank you for using Magic Matrix Generator Program!");
	}

}
