package Colony_War;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private List<Colony>colonyList;
	
	public int Round_Num;
	
	public Game()
	{
		this.colonyList = new ArrayList<>();
		this.Round_Num = 0;
	}
	
	public void Round_Fight(Colony one,Colony two) {
	    one.ChooseTactic();
	    two.ChooseTactic();
	     
	    int Colony1_Strength = one.GetArmyStrength();
	    int Colony2_Strength = two.GetArmyStrength();
	    
	    Colony Winner;
	    Colony Loser;

	    if (Colony1_Strength == Colony2_Strength) { // If equal strength
	        Winner = (one.getPopulation() > two.getPopulation()) ? one : two; // Winner is the one with higher population
	        Loser = (Winner == one) ? two : one;
	    }
	    else { // Otherwise, winner is the one with higher strength
	        Winner = (Colony1_Strength > Colony2_Strength) ? one : two;
	        Loser = (Winner == one) ? two : one;
	    }
	    
	    Winner.incrementWin();
	    Loser.incrementLoss();
	    
	    // Calculate the strength difference
	    int Strength_Diff = Math.abs(Colony1_Strength - Colony2_Strength);
	    
	    // Calculate the percentage difference
	    float Percent_Diff = (float)Strength_Diff / 1000.f;
	    
	    // Update the population of the loser
	    int Population_Loss = (int)(Loser.getPopulation() * Percent_Diff);
	    Loser.setPopulation(Loser.getPopulation() - Population_Loss);
	    
	    // Update the food stock of the loser
	    int Food_Loss = (int)(Loser.getFoodStock() * Percent_Diff);
	    Loser.setFoodStock(Loser.getFoodStock() - Food_Loss);
	    
	    // Transfer the lost food to the winner
	    Winner.setFoodStock(Winner.getFoodStock() + Food_Loss);
	    
	    //Choose Production type
	    one.ChooseProductionRate();
	    two.ChooseProductionRate();
	    //Get Production Rate from the selected type
	    int Colony1_PRate = one.GetProductionRate();
	    int Colony2_PRate = one.GetProductionRate();
	    
	    // Update the food stock with production
	    one.setFoodStock(one.getFoodStock() + Colony1_PRate);
	    two.setFoodStock(two.getFoodStock() + Colony2_PRate);
	    
	    // Increase the population by 20%
	    one.setPopulation((int)(one.getPopulation() * 1.2f));
	    two.setPopulation((int)(two.getPopulation() * 1.2f));

	    // Decrease the food stock as rate of (current population * 2)
	    one.setFoodStock(one.getFoodStock() - one.getPopulation() * 2);
	    two.setFoodStock(two.getFoodStock() - two.getPopulation() * 2);
	    
	    // Ensure foodStock doesn't go below 0
	    if (one.getFoodStock() < 0) one.setFoodStock(0);
	    if (two.getFoodStock() < 0) two.setFoodStock(0);
	}

	
	void Declare_War(Game game)
	{
		int activeColonies;

	    do {
	        activeColonies = 0;

	        // loop over all colonies
	        for (int i = 0; i < this.colonyList.size(); i++) {
	            // only consider colonies that are still active
	            if (this.colonyList.get(i).isActive()) {
	                activeColonies++;

	                // loop over all other colonies
	                for (int j = 0; j < this.colonyList.size(); j++) {
	                    // don't fight with self or inactive colonies
	                    if (i != j && this.colonyList.get(j).isActive()) {
	                        this.Round_Fight(this.colonyList.get(i), this.colonyList.get(j));
	                        this.Round_Num++;

	                        // check if either colony has been defeated
	                        if (this.colonyList.get(i).getPopulation() < 2 || this.colonyList.get(i).getFoodStock() < 2) {
	                            this.colonyList.get(i).setActive(false); 
	                        }
	                        if (this.colonyList.get(j).getPopulation() < 2 || this.colonyList.get(j).getFoodStock() < 2) {
	                            this.colonyList.get(j).setActive(false);
	                        }
	                    }
	                }
	            }
	        }

	        // continue the game as long as there is more than one active colony
	    } while (activeColonies > 1);
		
	}
	
	public void Print_Status(List<Colony> colonies) {
	    // print the table header
	    System.out.println("Colony\tPopulation\tFoodStock\tWin\tLoss\tStatus");

	    // iterate through all the colonies and print their status
	    for (Colony colony : colonies) {
	        String status = colony.isActive() ? "Won" : "Defeated";
	        System.out.println(colony.getSymbol() + "\t" + colony.getPopulation() + "\t" + colony.getFoodStock() + "\t" + colony.getWin() + "\t" + colony.getLoss() + "\t" + status);
	    }
	}

	

}
