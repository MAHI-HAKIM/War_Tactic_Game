package Colony_War;
import java.util.Random;


public class AProduction extends Production {

	public AProduction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int Produce()
	{
		Random random = new Random();
		
		int rand_num = random.nextInt(10) +1 ; //Generate Random number from 1 to 10
		
		if(rand_num < 5)
		{
			return (rand_num + 2)%11; // to ensure that the number stays with in the range of 1-10
		}
		else
		{
			return rand_num;
		}
	}
}
