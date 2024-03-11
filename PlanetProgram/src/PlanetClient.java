import java.util.Scanner;
import java.io.*;

/** This program will evaluate extrasolar planets based on two attributes
 * using a file of data and an Earth Similarity Index formula.
 * @author Gisell
 * 12/18/2023
 */

public class PlanetClient {
	
	/**
    Read the planetary data file into an array of PlanetData objects and return.
    @return the data array
*/
	
	
	public  PlanetData[] readFile(String fileName) throws IOException {
        PlanetData[] planetArray = new PlanetData[10];
        int numPlanets = 0;
       
        // Open the file
        File file = new File(fileName);
        if (file.exists()) {
            Scanner inputFile = new Scanner(file);
            // Check if there are contents and fill the array
            while (inputFile.hasNext() && numPlanets < 10) {
                String name = inputFile.nextLine();
                double radius = inputFile.nextDouble();
                double flux = inputFile.nextDouble();
                double distance = inputFile.nextDouble();
                if (inputFile.hasNext()) inputFile.nextLine();
                PlanetData planet = new PlanetData(name, radius, flux, distance);
                planetArray[numPlanets] = planet;
                numPlanets ++;
            }
            inputFile.close(); // Close the file
        }
        return planetArray; // Return the array
    }
	
	/**
    Show all the planets in the planet array.
    @param planetArray The array of PlanetData objects.
	 */
	
	public  void showAll(PlanetData[] planets) {
		for (int i = 0; i < planets.length; i ++) {
            System.out.printf("%s%28s\n", "Name: ", planets[i].getName());
            System.out.printf("%-30s%.2f\n", "Planetary Radius: ", planets[i].getRadius());
            System.out.printf("%-30s%.2f\n", "Stellar Flux: ", planets[i].getFlux());
            System.out.printf("%-30s%.2f\n", "Earth Similarity Index: ", planets[i].getEsi());
            System.out.printf("%-30s%.0f\n\n", "Distance from Earth: ", planets[i].getDistance());
        }
		
	}
	
	/** 
	 * Find and display the planet with the greatest Earth similarity index.
	 * @param planetArray The array of PlanetData objects.
	 * */
	
	public void showMostSimilar(PlanetData[] planetArray) {
		double largest = 0;
		int largestIndex = -1;
		for (int i = 0; i < planetArray.length; i ++ ) {
            double currentPercent = planetArray[i].getEsi();
            if (currentPercent > largest) {
                largestIndex = i;
                largest = currentPercent;
            }
        }
		if (largestIndex != -1) {
            System.out.println("\nThe planet with the greatest Earth similarity index is: " + planetArray[largestIndex].getName() + "\n");            
        }
	}
	
	/** 
	 * Find and display the planet with the lowest Earth similarity index.	 * 
	 * @param planetArray The array of PlanetData objects.
	 */
	
	public void showLeastSimilar(PlanetData[] planetArray) {
		double lowest = 2000000000;
		int lowestIndex = -1;
		for (int i = 0; i < planetArray.length; i ++ ) {
            double currentPercent = planetArray[i].getEsi();
            if (currentPercent < lowest) {
                lowestIndex = i;
                lowest = currentPercent;
            }
        }
		if (lowestIndex != -1) {
            System.out.println("\nThe planet with the lowest Earth similarity index is: " + planetArray[lowestIndex].getName() + "\n");
        }
	}
	
	/** 
	 * Find and display the average Earth similarity index.	 * 
	 * @param planetArray The array of PlanetData objects.
	 */
	
	public void showAverageSimilarity(PlanetData[] planetArray) {
		double sumPercent = 0;
		for (int i = 0; i < planetArray.length; i ++ ) {
            sumPercent += planetArray[i].getEsi();
        }
		System.out.printf("\nThe average Earth similarity index of planets in the data file is: %.2f\n\n", sumPercent / 10);
	}
	
	/** Display a list of planets for the user to pick from and return their choice.
	 * 
	 * @param planetArray The array of PlanetData objects.
	 * @return pick The user's choice of planet.
	 */
		
	private int pickPlanet(PlanetData[] planetArray){
		int pick = -1;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("\nSelect a planet:");
		for (int i = 0; i < planetArray.length; i ++ ) {
            System.out.println((i + 1) + ". " + planetArray[i].getName());
        }		
		System.out.print("Which planet? (1-10): ");
		pick += keyboard.nextInt();
		return pick;
		
	}
	
	/** Allow the user to pick a planet to change it's radius and display the new results
	 * 
	 * @param planetArray The array of PlanetData objects.
	 */
	
	
	public void  changeRadius(PlanetData[] planetArray) {
		int planet;
		double newRadius;
		// Call to method to pick the planet
		planet = pickPlanet(planetArray);
		System.out.println("\nCurrent planetary radius: " + planetArray[planet].getRadius());
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Please enter new radius: ");
		newRadius = keyboard.nextDouble();		
		planetArray[planet].setRadius(newRadius);
		System.out.printf("\n%s%28s\n", "Name: ", planetArray[planet].getName());
		System.out.printf("%-30s%.2f\n", "Planetary Radius: ", planetArray[planet].getRadius());
		System.out.printf("%-30s%.2f\n", "Stellar Flux: ", planetArray[planet].getFlux());
		System.out.printf("%-30s%.2f\n", "Earth Similarity Index: ", planetArray[planet].getEsi());
		System.out.printf("%-32s%.0f\n\n", "Distance from Earth: ", planetArray[planet].getDistance());		
	}
	
	/** Allow the user to pick a planet to change it's flux and display the new results.
	 * 
	 * @param planetArray The array of PlanetData objects.
	 */
	
	public void changeFlux(PlanetData[] planetArray) {
		int planet;
		double newFlux;
		// Call to method to pick the planet
		planet = pickPlanet(planetArray);
		System.out.println("\nCurrent planetary flux: " + planetArray[planet].getFlux());
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Please enter new flux: ");
		newFlux = keyboard.nextDouble();
		planetArray[planet].setFlux(newFlux);
		System.out.printf("\n%s%28s\n", "Name: ", planetArray[planet].getName());
		System.out.printf("%-30s%.2f\n", "Planetary Radius: ", planetArray[planet].getRadius());
		System.out.printf("%-30s%.2f\n", "Stellar Flux: ", planetArray[planet].getFlux());
		System.out.printf("%-30s%.2f\n", "Earth Similarity Index: ", planetArray[planet].getEsi());
		System.out.printf("%-32s%.0f\n\n", "Distance from Earth: ", planetArray[planet].getDistance());
	}
	
	/**
	   Read the planet data into an array and display menu
	 */
	
	public void displayMenu() throws IOException{
		PlanetData[] planetArray;
		
		// Fill the planet array
		planetArray = readFile("PlanetData.txt");
		// Declare variable to hold the user's input	
		int choice;		
		
		Scanner keyboard = new Scanner(System.in);
		
		//Display full menu
		System.out.println("Planetary Conditions Data Menu");
		System.out.println("1. List all planetary data");
		System.out.println("2. Change radius");
		System.out.println("3. Change flux");
		System.out.println("4. Show planet with highest ESI");
		System.out.println("5. Show planet with lowest ESI");
		System.out.println("6. Show average ESI of planets in the data file");
		System.out.println("7. Quit");
		
		do {						
			// Prompt user to choose
			System.out.print("Your choice: ");	
			choice = keyboard.nextInt();
			if (choice > 0 && choice < 8) {
				switch (choice) {
				case 1:
					// Display planet data
					showAll(planetArray);
					break;
				case 2:
					// Call method to change radius
					changeRadius(planetArray);					
					break;
				case 3:
					// Call method to change flux
					changeFlux(planetArray);					
					break;
				case 4:
					// Show the planet with the highest ESI
					showMostSimilar(planetArray);
					break;
				case 5:
					// Show the planet with the lowest ESI
					showLeastSimilar(planetArray);
					break;
				case 6:
					// Show the average ESI
					showAverageSimilarity(planetArray);
					break;
				case 7:
					// Quit program					
					break;
				}
			}
		}while (choice != 7);
		
	}
	
	public static void main(String[] args) throws IOException{
		
		PlanetClient planetClient = new PlanetClient();
		planetClient.displayMenu();
	}

}
