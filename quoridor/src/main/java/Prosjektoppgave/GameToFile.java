package Prosjektoppgave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;


public class GameToFile implements Filehandler{

	private final static String SAVE_FOLDER = "/Users/georghovezimmer/Desktop/Prosjektoppgave/quoridor/src/main/java/Prosjektoppgave";
	
	public String getFilePath(String filename) {
		return SAVE_FOLDER + filename;
	}
	

	public void save(String filename, Player player1, Player player2){
		if (filename ==""){
			throw new IllegalArgumentException("Filnavnet kan ikke være tomt");
		}
		
		try {
			PrintWriter writer = new PrintWriter(getFilePath(filename));
			writer.println(filename);
			
			writer.println("Hvem sin tur det er (1 er spiller 1 sin tur, 2 spiller 2 sin tur): ");
			if (player1.getMyTurn() == true){
				writer.println(1);
			}
			else{
				writer.println(2);
			}
			
			writer.println("Posisjonen til spiller 1: ");
			writer.println(player1.getXPosition());
			writer.println(player1.getYPosition());


			writer.println("Posisjonen til spiller 2: ");
			writer.println(player2.getXPosition());
			writer.println(player2.getYPosition());

			
			writer.println("Antall vegger: p1" );
			writer.println(player1.getWalls());
			

			writer.println("Antall vegger: p2 ");
			writer.println(player2.getWalls());
	

			writer.println("Antall vegger totalt: ");
			writer.println(Walls.wallsList.size());

			writer.println("Posisjonen til alle veggene : " );

	
		
			for (int i=0; i<Walls.wallsList.size(); i++){
				List paneHorisontal = Walls.wallsList.get(i);
				int a = (int) paneHorisontal.get(0);
				int b = (int) paneHorisontal.get(1);
				int c = (int) paneHorisontal.get(2);
				int d = (int) paneHorisontal.get(3);
				writer.println(a +" "+ b +" "+ c+" "+ d);
			}
			
			writer.flush();
			writer.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void load(String filename, Player player1, Player player2){
		
		try{ 
			Scanner scanner = new Scanner(new File(getFilePath(filename)));
			scanner.nextLine(); // quoridor
			scanner.nextLine(); //hvem sin tur
			
			if (scanner.nextInt()==1){
				player1.setMyTurn(true);
				player2.setMyTurn(false);
			}else {
				player1.setMyTurn(false);
				player2.setMyTurn(true);
		}
		scanner.nextLine();
		scanner.nextLine(); //posisjon 1
			
		player1.setX_pos(scanner.nextInt()); 
		player1.setY_pos(scanner.nextInt());

		scanner.nextLine();
		scanner.nextLine(); //posisjon 2

		player2.setX_pos(scanner.nextInt()); 
		player2.setY_pos(scanner.nextInt());

		scanner.nextLine();
			
		scanner.nextLine(); // antall vegger p1
		player1.setWalls(scanner.nextInt());

		scanner.nextLine();

		scanner.nextLine(); // antall vegger p2
		player2.setWalls(scanner.nextInt());

		scanner.nextLine();

		scanner.nextLine(); // antall vegger totalt
		int number = scanner.nextInt();

		scanner.nextLine(); // posisjonene til alle veggene
		scanner.nextLine(); // posisjonene til alle veggene

			
		for(int i = 0 ; i <number; i++){
			Walls vegg = new Walls(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
			vegg.addWallFromFile();
			scanner.nextLine();
		}
			
		}catch(FileNotFoundException e) {
	}
}
}