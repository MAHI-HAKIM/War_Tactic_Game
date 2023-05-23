package Colony_War;
import java.util.Random;
import java.time.Instant;

public  class BTactic extends Tactic{

	@Override
    public int Strength() 
	{
		System.out.println("B tactic has been used");
		
		Random random = new Random();
		long CurrentTime = Instant.now().toEpochMilli();
       
		if(CurrentTime % 2 == 0)
		{
			//when current time is even
			int rand_num = random.nextInt(250);
			return rand_num * 2 +2; // returns an even random number between 2 and 500
		}
		else
		{
			//when current time is odd
			int rand_num = random.nextInt(250);
			return rand_num *2 + 501; // returns an even random number between 501 and 999

		}
    }
}
