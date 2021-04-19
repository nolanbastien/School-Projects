// -------------------------------------------------------
// Assignment 4
// Written by: Nolan Bastien [40179166]
// For COMP 248 Section UA � Winter 2021
// --------------------------------------------------------
//
// This class is used to create creature objects.
//

import java.util.Date;
public class Creature {
	
	private static final int FOOD2HEALTH = 6;
	private static final int HEALTH2POWER = 4;
	private static int numStillAlive = 0;
	
	private String name;
	private int foodUnits;
	private int healthUnits;
	private int firePowerUnits;
	private Date dateCreated = new Date();
	private Date dateDied = new Date();
	
	
	// This is the constructor
	public Creature(String name){
		 
		// Note: 0 <= Math.random < 1
		
		numStillAlive = numStillAlive + 1;
		
		this.name = name;
		
		// Between 1 and 12 inclusive
		
		foodUnits = (int)(12*Math.random()+1);
		
		// Between 1 and 7 inclusive
		
		healthUnits = (int)(7*Math.random()+1);
		
		// Between 0 and 10 inclusive
		
		firePowerUnits = (int)(11*Math.random());
		
		dateCreated = new Date();
		
		dateDied = null;
		
		// Call normalize
		
		normalizeUnits();
		
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	public void setHealthUnit(int n){
		healthUnits = n;
	}
	
	public void setFoodUnit(int n){
		foodUnits = n;
	}
	
	public void reduceFirePowerUnits(int n){
		firePowerUnits = firePowerUnits - n;
	}
	
	public String getName(){
		return name;
	}
	
	public int getFoodUnits(){
		return foodUnits;
	}
	
	public int getHealthUnits(){
		return healthUnits;
	}
	
	public int getFirePowerUnits(){
		return firePowerUnits;
	}
	
	public int getDateCreated(){
		return dateCreated.getDate();
	}
	
	public int getDateDied(){ 
		return dateDied.getDate();
	}

	
	public static int getNumStillAlive(){
		return numStillAlive;
	}
	
	public boolean isAlive(){
		
		if(dateDied == null)
			return true;
		
		else return false;
	}
	
	public int earnFood(){
		
		// Note: 0 <= Math.random < 1
		
		// Between 0 and 15 inclusive
		
		int foodEarned = (int) (Math.random()*16);
		
		foodUnits = foodUnits + foodEarned;
		
		normalizeUnits();
		
		return foodEarned;
	}
	
	public void attacking(Creature player){
		
		// Gains to Creature class from attacking player
		// and penalty to player
		
		int foodUnitsGained = (int) Math.ceil(player.foodUnits/2.0);
		
		foodUnits += foodUnitsGained;
		
		player.foodUnits -= foodUnitsGained;
		
		
		int healthUnitsGained = (int) Math.ceil(player.healthUnits/2.0);
		
		healthUnits += healthUnitsGained;
		
		player.healthUnits -= healthUnitsGained;
		
		
		// Cost of attacking is 2 power units
		
		firePowerUnits -= 2;
		
		normalizeUnits();
		
		// This method also calls the player's healthFood Units Zero 
		// to determine if player is dead after the attack
		
		player.healthFoodUnitsZero();
		
	}
	
	public boolean healthFoodUnitsZero(){
		
		// If the creature has no more health and food units left, call died and return true
		
		if(healthUnits <= 0 && foodUnits <= 0){
			
			died();
			return true;
		}
		
		// Otherwise, return false
		
		else return false;
	}
	
	private void died(){
		
		// When a creature dies, set the dateDied to current date and 
		// reduce by one the counter of creature alive
		
		dateDied = new Date();
		
		numStillAlive--;
	}
	
	public String toString(){
		
		if(isAlive())
			return "Food units \tHealth units \tFire power units \tName"
					+ "\n---------- \t------------ \t---------------- \t----"
					+ "\n" + foodUnits + "\t\t" + healthUnits + "\t\t" + firePowerUnits + "\t\t\t" + name
					+ "\nDate Created: " + dateCreated
					+ "\nDate Died: is still alive";
		
		else return "Food units \tHealth units \tFire power units \tName"
					+ "\n---------- \t------------ \t---------------- \t----"
					+ "\n" + foodUnits + "\t\t" + healthUnits + "\t\t" + firePowerUnits + "\t\t\t" + name
					+ "\nDate Created: " + dateCreated
					+ "\nDate Died: " + dateDied;
		
	}
	
	public String showStatus(){
		
		return foodUnits + " food units, " + healthUnits + " health units, " 
		+ firePowerUnits + " fire power units";
	}
	
	public void normalizeUnits(){
				
		// For each 6 food units, 1 is converted to health
		// (FOOD2HEALTH = 6)
		
		int healthConverted = foodUnits / FOOD2HEALTH;
		
		// For each health converted, we remove 6 foodUnits
		
		foodUnits -= healthConverted*FOOD2HEALTH;
		
		// Add the number of health converted to healthUnits
		
		healthUnits += healthConverted;
		
		
		// Same for fire power conversion, for each 4 health units
		
		if(healthUnits > 4){ // Do not convert if health is 4 or less
		
		int firePowerConverted = healthUnits / HEALTH2POWER;
		
		healthUnits = healthUnits - firePowerConverted*HEALTH2POWER;
		
		firePowerUnits += firePowerConverted;
		
		}	
	}

}
