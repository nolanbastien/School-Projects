// -------------------------------------------------------
// Assignment 4
// Written by: Nolan Bastien [40179166]
// For COMP 248 Section UA � Winter 2021
// --------------------------------------------------------
//
// This is the class in which the game is played.
//

import java.util.Scanner;
public class BattleGame {

	public static void main(String[] args) {
		
		// 1. Display a welcome message
		
		System.out.println("[------------------------------------------------]");
		System.out.println("[ Welcolme to the Battle Game ]");
		System.out.println("[------------------------------------------------]");
		
		
		// 2. Create the characters
		
		// Prompt the user for the number of creatures 
		// which will play the game (between 2 and 8 inclusive)
		
		Scanner input = new Scanner(System.in);
		
		int nbOfCreatures;
		
		do{
		System.out.print("How many creatures would you like to have (minimum 2, maximum 8)? ");
		
		nbOfCreatures = input.nextInt();
		input.nextLine();
		
		if(nbOfCreatures < 2 || nbOfCreatures > 8)
			System.out.println("*** Illegal number of creatures requested ***");
		
		} while (nbOfCreatures < 2 || nbOfCreatures > 8);
		
		// Create an array containing the requested number of 
		// objects of type Creature
		
		Creature[] creature = new Creature[8];
		
		
		for(int i = 0; i < nbOfCreatures; i++){
			
			System.out.println();
			
			System.out.print("What is the name of creature " + (i + 1) + "? ");
						
			creature[i] = new Creature(input.nextLine());
			
			System.out.println("\n" + creature[i].toString());
					
			
		}
		
		// 3. Play the game
		
		// Randomly decide which creature starts
		
		int turn = (int) (Math.random()*nbOfCreatures)+1;
		
		// Play as long as there are more than one creature alive
		
		while(creature[0].getNumStillAlive() > 1) {
				
			// Repeat until an action that ends the current player's turn is chosen
				
			boolean continueTurn = true;
				
				while(continueTurn){
					
				// Call the choose method to display and get the user's choice
				
				int choice = choose(turn, creature);
				
				// Options
				
					switch(choice){
					
					case 1:
						
						// Display the number of creatures alive
						
						System.out.println("Number of creatures alive " + creature[0].getNumStillAlive());
						continueTurn = true;
						break;
						
					case 2:
						
						// Display the creature's status
						
						System.out.println(creature[turn-1].toString());
						continueTurn = true;
						break;
					
					case 3:
						
						// Display the status of all creatures
						
						for(int i = 0; i < nbOfCreatures; i++){
							
							System.out.println(creature[i].toString());
						}
						continueTurn = true;
						break;
					
					case 4:
						
						// Change the name of the creature
						
						System.out.println("Your name is currently " + creature[turn-1].getName());
						System.out.print("What is the new name: ");
						creature[turn-1].setName(input.nextLine());
						continueTurn = true;
						break;
					
					case 5:
						
						// Try to earn food
						
						System.out.println("\nYour status before working for food: " + creature[turn-1].showStatus()
								+ " ... You earned " + creature[turn-1].earnFood() + " food units. ");
						
						System.out.println("\nYour status after working for food: " + creature[turn-1].showStatus());
						
						continueTurn = false;
						break;
						
					case 6:
						
						// Attack another creature
						
						// Ask user for the creature they want to attack
						
						int attackIndex;
						
						do{
						System.out.print("Who do you want to attack? (enter a number "
								+ "from 1 to " + nbOfCreatures + " other than yourself(" + turn + ")): ");
							
						attackIndex = input.nextInt();
						
						if(attackIndex == turn)
							System.out.println("Can't attack yourself silly! Try again ...");
						
						if(attackIndex < 1 || attackIndex > nbOfCreatures)
							System.out.println("That creature does not exist. Try again ...");
						
						else if(!creature[attackIndex-1].isAlive())
							System.out.println("That creature is dead. Try again ...");
						
						} while(attackIndex == turn || attackIndex < 1 || attackIndex > nbOfCreatures || !creature[attackIndex-1].isAlive());
						
						
						// Check the odds of success of the attack
						
						int oddsOfAttack = (int) (Math.random()*2.0);
						
						// If odds are 0, you are being attacked
						
						if(oddsOfAttack == 0) {
							
							// You are being attacked, but the creature attacked does not have enough FPU
								
								if(creature[attackIndex-1].getFirePowerUnits() < 2)
								{
									System.out.println("Lucky you, the odds were that the other player attacks you, "
											+ "but " + creature[attackIndex-1].getName() + " doesn't have enough fire power to attack you! "
											+ "So is status quo!!");
								}
							
							// You are being attacked
								
								else {
									
									System.out.println("....... Oh No!!! You are being attacked by "
									+ creature[attackIndex-1].getName() + "!");
							
									System.out.println("Your status before being attacked: " + creature[turn-1].showStatus());
									
									creature[attackIndex-1].attacking(creature[turn-1]);
									
									System.out.println("Your status after being attacked: " + creature[turn-1].showStatus());
									
									// Display if you died
								
									if(!creature[turn-1].isAlive())
										System.out.println("\n----> " + creature[turn-1].getName() + " is dead");
									
								}
						}
					
						// if odds are 1 or 2, you can attack...
						
						if(oddsOfAttack >= 1){
							
							// ... only if you have enough FPU
							
							if(creature[turn-1].getFirePowerUnits() < 2) {
								
								// If the creature you tried to attack does not have enough FPU, it does not attack
								
								if(creature[attackIndex-1].getFirePowerUnits() < 2) {
									
									System.out.println("You don't have enough fire power units. The odds were that the other player attacks you, "
											+ "but " + creature[attackIndex-1].getName() + " doesn't have enough fire power to attack you! "
											+ "So is status quo!!");
									
								}
								
								// If the creature has enough FPU, it attacks you
								
								else{
									
										System.out.println("That was not a good idea ... you only had "
												+ creature[turn-1].getFirePowerUnits() + " Fire Power units!!!");
										
										System.out.println("....... Oh No!!! You are being attacked by "
												+ creature[attackIndex-1].getName() + "!");
										
										System.out.println("Your status before being attacked: " + creature[turn-1].showStatus());
										
										creature[attackIndex-1].attacking(creature[turn-1]);
										
										System.out.println("Your status after being attacked: " + creature[turn-1].showStatus());
										
										// Display if you died
								
										if(!creature[turn-1].isAlive())
											System.out.println("\n----> " + creature[turn-1].getName() + " is dead");
								}
							}
							
							// If you have enough FPU, you attack
							
							else {
								System.out.println("\n..... You are attacking " + creature[attackIndex-1].getName() + "!");
								
								System.out.println("Your status before attacking: " + creature[turn-1].showStatus());
										
								creature[turn-1].attacking(creature[attackIndex-1]);
										
								System.out.println("Your status after attacking: " + creature[turn-1].showStatus());
								
								// Display if the creature you attacked died
								
								if(!creature[attackIndex-1].isAlive())
									System.out.println("\n----> " + creature[attackIndex-1].getName() + " is dead");

							}
						}
						
						continueTurn = false;
						break;
					}
				}
			
			// Next turn
				
			turn = (turn % nbOfCreatures) + 1;
			
			// If next creature is dead, skip turn
			
			if(!creature[turn-1].isAlive())
				turn++;

		}

		// Only one creatures remaining. Game is over.
		
		System.out.println("\nGAME OVER!!!!!\n");
		
		for(int i = 0; i < nbOfCreatures; i++){
			
			System.out.println(creature[i].toString());
		}
		
		// As always, close scanner

		input.close();
	}
	
	public static int choose(int turn, Creature[] creature){
		
		Scanner input = new Scanner(System.in);
		
		int choice;
		
		do{
			
		// Prompt the user for a choice
		
		System.out.println();
		
		System.out.println("Creature #" + turn + ": " + creature[turn -1].getName() + ", what do you want to do?");
		
		System.out.println("\t1. How many creatures are alive?"
						+ "\n\t2. See my status"
						+ "\n\t3. See status of all players"
						+ "\n\t4. Change my name"
						+ "\n\t5. Work for some food"
						+ "\n\t6. Attack another creature (Warning! may turn against you)");
		System.out.print("Your Choice please > ");
		
		// Register choice
		
		choice = input.nextInt();
		
		// Verify choice
		
		} while(choice < 1 || choice > 6 );
		
		return choice;
		
	}


}
