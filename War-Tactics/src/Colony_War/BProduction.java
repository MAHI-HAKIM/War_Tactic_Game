package Colony_War;

import java.util.Random;

public class BProduction extends Production {

	
	@Override
	public int Production_Rate()
	{
		Random random = new Random();
		
		int rand_num = random.nextInt(10)+1;
		
		if(rand_num > 5)
		{
			return (rand_num -2) > 0? (rand_num -2):1;
		}
		else
		{
			return rand_num;
		}
		
	}

}
