package Prosjektoppgave;
    
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class SaveHandlerTest {
	
	private GameToFile savehandler = new GameToFile();
	
	Player player1 = new Player(8,16,true,0);
	Player player2 = new Player(8,0,true,0);

	Walls vegg1 = new Walls(2,2,1,4);
	Walls vegg2 = new Walls(4,2,4,1);
	
	
	private void checkPos(Player player, int x, int y) {
		Assertions.assertEquals(x, player.getXPosition(), "Wrong x coordinate");
		Assertions.assertEquals(y, player.getYPosition(), "Wrong y coordinate");
	}

	
	@Test
	@DisplayName("Teste save")
	public void testSave() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			savehandler.save("", player1, player2);
		}, "Sjekker at det ikke kan opprettes en tom fil");
		
	}


	@Test
	@DisplayName("Teste save og load med funksjonaliteter")
	public void testGameToFileFunction(){
		//flytter brikker, lagrer posisisjon på brikkene. 
		//Loader og sjekker om posisjonen stemmer
		//getmyturn, setmyturn
		//get x og set x
		//get y og set y
		//setwall, getwall
		//
		vegg1.addWall();
		player1.incrementWalls();
		player1.up(); //(8,14)
		player1.up(); //(8,12)
		player1.right(); //(10,12)
		player2.right(); //(0,10)
		player2.right(); //(0,12)


		savehandler.save("testfil.txt", player1, player2); // lagrer spiller

		//gjør endringer etter lagring
		player1.right(); // 
		player1.right(); // 
		player2.right(); // 
		vegg2.addWall();
		player1.incrementWalls();
		

		//oppretter spill fra lagringspunkt. Nye endringer er da ikke med! 
		savehandler.load("testfil.txt", player1, player2);
		checkPos(player1, 10, 12);
		Assertions.assertEquals(1, player1.getWalls());
	
		
	}




	
}