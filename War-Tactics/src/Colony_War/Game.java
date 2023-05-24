package Colony_War;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Colony> colonyList;
    
    private int roundNum;

    public Game() {
        this.colonyList = new ArrayList<>();
        this.roundNum = 0;
    }

    public void addColonies(Colony colony) {
        this.colonyList.add(colony);
    }
    
    public void roundFight(Colony one, Colony two) 
    {
        one.chooseTactic();
        two.chooseTactic();

        int colony1Strength = one.getArmyStrength();
        int colony2Strength = two.getArmyStrength();

        Colony winner;
        Colony loser;

        if (colony1Strength == colony2Strength) { // If equal strength
            winner = (one.getPopulation() > two.getPopulation()) ? one : two; // Winner is the one with higher population
            loser = (winner == one) ? two : one;
        } else { // Otherwise, winner is the one with higher strength
            winner = (colony1Strength > colony2Strength) ? one : two;
            loser = (winner == one) ? two : one;
        }

        winner.incrementWin();
        loser.incrementLoss();

        // Calculate the strength difference
        int strengthDiff = Math.abs(colony1Strength - colony2Strength);

        // Calculate the percentage difference
        float percentDiff = (float) strengthDiff / 1000.f;

        // Update the population of the loser
        int populationLoss = (int) (loser.getPopulation() * percentDiff);
        loser.setPopulation(loser.getPopulation() - populationLoss);

        // Update the food stock of the loser
        int foodLoss = (int) (loser.getFoodStock() * percentDiff);
        loser.setFoodStock(loser.getFoodStock() - foodLoss);

        // Transfer the lost food to the winner
        winner.setFoodStock(winner.getFoodStock() + foodLoss);

        // Choose Production type
        one.chooseProductionRate();
        two.chooseProductionRate();

        // Get Production Rate from the selected type
        int colony1PRate = one.getProductionRate();
        int colony2PRate = two.getProductionRate();

        // Update the food stock with production
        one.setFoodStock(one.getFoodStock() + colony1PRate);
        two.setFoodStock(two.getFoodStock() + colony2PRate);

        // Increase the population by 20%
        one.setPopulation((int) (one.getPopulation() * 1.2f));
        two.setPopulation((int) (two.getPopulation() * 1.2f));

        // Decrease the food stock as rate of (current population * 2)
        one.setFoodStock(one.getFoodStock() - one.getPopulation() * 2);
        two.setFoodStock(two.getFoodStock() - two.getPopulation() * 2);

        // Ensure foodStock doesn't go below 0
        if (one.getFoodStock() < 0) one.setFoodStock(0);
        if (two.getFoodStock() < 0) two.setFoodStock(0);
        
        // Ensure population doesn't go below 0
        if (one.getPopulation() < 0) one.setPopulation(0);;
        if (two.getPopulation() < 0) two.setPopulation(0);;
        
        printStatus();
    }

    void declareWar() {
        int activeColonies;

        do {
            activeColonies = 0;

            // loop over all colonies
            for (int i = 0; i < this.colonyList.size(); i++) {
                // only consider colonies that are still active
                if (this.colonyList.get(i).isActive()) {
                    activeColonies++;
                    this.colonyList.get(i).setStatus("Active");

                    // loop over all other colonies
                    for (int j = 0; j < this.colonyList.size(); j++) {
                        // don't fight with self or inactive colonies
                        if (i != j && this.colonyList.get(j).isActive()) {
                            this.roundFight(this.colonyList.get(i), this.colonyList.get(j));
                            this.roundNum++;
                            // check if either colony has been defeated
                            if (this.colonyList.get(i).getPopulation() < 2 || this.colonyList.get(i).getFoodStock() < 1) {
                                this.colonyList.get(i).setActive(false);
                                this.colonyList.get(i).setStatus("---");
                            }
                            if (this.colonyList.get(j).getPopulation() < 2 || this.colonyList.get(j).getFoodStock() < 1) {
                                this.colonyList.get(j).setActive(false);
                                this.colonyList.get(j).setStatus("---");
                            }
                        }
                    }
                }
            }

            // continue the game as long as there is more than one active colony
        } while (activeColonies > 1);

        // After the loop, there is only one colony left. We set it as the winner.
        for (Colony colony : this.colonyList) {
            if (colony.isActive()) {
                colony.setStatus("Winner");
                break;
            }
        }
    }

    public void printStatus() {
        clearScreen();
        System.out.println("\nRound : " + this.roundNum +"\n");

        System.out.println("------------------------------------------------------------------------------------------");

        // print the table header
        System.out.printf("%-13s %-15s %-16s %-15s %-15s %-15s%n", "|Colony|", "|Population|", "|FoodStock|", "|Wins|", "|Losses|", "|Status|");
        System.out.printf("%-13s %-15s %-16s %-15s %-15s %-15s\n", "--------", "------------", "-----------", "------", "--------", "--------");

        // iterate through all the colonies and print their status
        for (Colony colony : colonyList) {
            if (colony.isActive() || colony.getStatus().equals("Winner")) {
                System.out.printf("%-15c %-15d %-15d %-15d %-15d %-15s\n",
                        colony.getSymbol(),
                        colony.getPopulation(),
                        colony.getFoodStock(),
                        colony.getWins(),
                        colony.getLosses(), 
                        colony.getStatus());
            } else {
                System.out.printf("%-15c %-15s %-15s %-15s %-15s %-15s\n",
                        colony.getSymbol(),
                        "---",
                        "---",
                        "---",
                        "---", 
                        colony.getStatus());
            }
        }
        System.out.println("------------------------------------------------------------------------------------------");
    }


    
    public static void clearScreen() {  
    	try {
    	    if (System.getProperty("os.name").contains("Windows")) {
    	        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    	    } else {
    	        Runtime.getRuntime().exec("clear");
    	    }
    	} catch (IOException | InterruptedException ex) {
    	    ex.printStackTrace();
    	}

    }
}
