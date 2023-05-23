package Colony_War;

public class main {

	public static void main(String[] args) 
	{
		Colony cl = new Colony(100,'a');
		
		cl.chooseTactic();
		int army = cl.getArmyStrength();
		System.out.println("Army strenght ");
	}

}
