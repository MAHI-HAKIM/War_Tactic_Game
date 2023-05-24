package Colony_War;

import java.util.Scanner;
import java.util.Random;


public class main {
	
	public class SymbolGenerator {
		
		private static char[] symbols = 
			{
			    '!', '#', '$', '%', '&', '*', '+', '-', '/', 
			    ':', ';', '<', '=', '>', '?', '@', '^', 
			    '{', '|', '}', '~'
			};
	    private static int symbolIndex = 0;

	    // Shuffle the array of symbols at the start
	    static {
	        Random rand = new Random();
	        for (int i = symbols.length - 1; i > 0; i--) {
	            int j = rand.nextInt(i + 1);
	            // Swap symbols[i] with symbols[j]
	            char temp = symbols[i];
	            symbols[i] = symbols[j];
	            symbols[j] = temp;
	        }
	    }

	    public static char getNextSymbol() {
	    	 if (symbolIndex >= symbols.length) {
	    	        // Reset symbolIndex to 0 if we've used up all symbols
	    	        symbolIndex = 0;
	    	    }
	    	    return symbols[symbolIndex++];
	    }
	}
	public static void main(String[] args) 
	{   
		
        Game game = new Game();
       

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the populations of colonies separated by space:");

        String[] populationsInput = scanner.nextLine().split(" ");


        for (String populationStr : populationsInput) {
           
        	int population = Integer.parseInt(populationStr.trim());

            Colony colony = new Colony(population,SymbolGenerator.getNextSymbol() );

            game.addColonies(colony);
        }

        scanner.close();

        game.declareWar();

        game.printStatus();
    }
		
}


