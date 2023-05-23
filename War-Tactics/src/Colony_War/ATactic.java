package Colony_War;
import java.util.Random;

public class ATactic extends Tactic {

	public ATactic() {	
	}
	
	@Override
    public int War() {
		Random random = new Random();
		int random1 = random.nextInt(500)+1; // Generate random number between 1 to 500
		int random2 = random.nextInt(500)+501; // Generate random number between 500 to 501
		int strength = (random.nextInt(2)==0) ? random1 : random2; // Choose one random from either 1 or 2
        return strength;
    }

}
