package Colony_War;

import java.util.Random;

public class Colony {

	private char Symbol;
	private int Population;
	private int FoodStock;
	private String status;
	public int Win;
	public int Loss;
	private boolean isActive;
	private Tactic tactic;
	private Production production;
	
	public Colony(int population , char symbol) 
	{
		this.Population = population;
		this.Symbol = symbol;
		this.FoodStock = this.Population * this.Population;
		this.Win = 0;
		this.Loss = 0;
		this.isActive = true;
		this.status = "Active";
	}
	
	 public void setStatus(String status) {
	        this.status = status;
	    }

	    public String getStatus() {
	        return this.status;
	    }
    
	// getter for name
    public char getSymbol() {
        return this.Symbol;
    }

    // setter for name
    public void getSymbol(char symbol) {
        this.Symbol = symbol;
    }
    
	  public int getPopulation() {
	        return this.Population;
	    }

	    public void setPopulation(int population) {
	        this.Population = population;
	    }

	    public int getFoodStock() {
	        return this.FoodStock;
	    }

	    public void setFoodStock(int foodStock) {
	        this.FoodStock = foodStock;
	    }

	    public boolean isActive() {
	        return this.isActive;
	    }

	    public void setActive(boolean isActive) {
	        this.isActive = isActive;
	    }
	    

	    // Getters
	    public int getWins() {
	        return this.Win;
	    }

	    public int getLosses() {
	        return this.Loss;
	    }

	    // Setters
	    public void setWins(int wins) {
	        this.Win = wins;
	    }

	    public void setLosses(int losses) {
	        this.Loss = losses;
	    }

	    // Increment the wins and losses
	    public void incrementWin() {
	        this.Win++;
	    }

	    public void incrementLoss() {
	        this.Loss++;
	    }
	
	public void chooseTactic() { //Chooses the Tactic on weather its A or B
		
	    Random random = new Random();
	    if (random.nextBoolean())
	    {
	       this.tactic = new ATactic();
	       
	    } else {
	      this.tactic = new BTactic();
	    }
	    
	 }
	
	public int getArmyStrength() {
        // Ensure that a tactic has been chosen before calling this method
        if (this.tactic == null) {
            throw new IllegalStateException("Tactic must be chosen before getting army strength.");
        }
        // Call the Strength() method of the chosen Tactic
        return this.tactic.Strength();
    }
	
	public void chooseProductionRate() { //Chooses the Production Type on weather its A or B
		
	    Random random = new Random();
	    if (random.nextBoolean())
	    {
	       this.production = new AProduction();
	       
	    } else {
	      this.production = new BProduction();
	    }
	    
	 }
	
	public int getProductionRate() {
        // Ensure that a Production has been chosen before calling this method
        if (this.production == null) 
        {
            throw new IllegalStateException("Tactic must be chosen before getting army strength.");
        }
        // Call the Production_Rate() method of the chosen Production
        return this.production.Production_Rate();
    }
	
}
