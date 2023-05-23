package Colony_War;

import java.util.Random;

public class Colony {

	char Symbol;
	public int Population;
	public int FoodStock;
	public int Win;
	public int Lost;
	public int ArmyStrength;
	public boolean active;
	private Tactic tactic;
	private Production production;
	
	public Colony(int population , char symbol) 
	{
		this.Population = population;
		this.Symbol = symbol;
		this.FoodStock = this.Population * this.Population;
		this.Win = 0;
		this.Lost = 0;
		this.active = true;
		
		//THIS CHOOSE WEATHER A-B TACTIC ADN PRODUCTION
		Random random = new Random();
		if(random.nextBoolean())
		{
			this.tactic = new ATactic();
			this.production = new AProduction();
		}else
		{
			this.tactic = new BTactic();
			this.production = new BProduction();
		}
		////////////////////////////////////////////////
		
		this.ArmyStrength = this.tactic.Strength();
		
	
	}
	
}
