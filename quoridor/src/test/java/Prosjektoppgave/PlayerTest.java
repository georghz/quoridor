package Prosjektoppgave;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class PlayerTest {
   
    private Player player1;
    private Player player2;


    private void checkPos(Player player, int x, int y) {
		Assertions.assertEquals(x, player.getXPosition(), "Wrong x coordinate");
		Assertions.assertEquals(y, player.getYPosition(), "Wrong y coordinate");
	}

    @BeforeEach
	public void setup() {
		player1 = new Player(8,16,true,0);
		player2 = new Player(8,0,true,0); //denne er true for at testene skal kunne kjøre,
                                        // uten at jeg må kalle på changeturn() hver gang. 
	}
    
    @Test
	@DisplayName("Start position") //Denne er vel unødvendig siden jeg har satt dem opp selv?
	public void testStartPosition() {
		checkPos(player1, 8, 16);
        checkPos(player2, 8, 0);
	}

    @Test
	@DisplayName("Move up")
	public void testUp() {
		player1.up();
		checkPos(player1, 8, 14);
        player1.up();
		checkPos(player1, 8, 12);
         
        Assertions.assertThrows(IllegalStateException.class, () -> {
			player2.up();
		}, "Sjekker at up() kaster illegalState når den skal");
  
    }

    @Test
	@DisplayName("Move down")
	public void testDown() {
		player2.down();
		checkPos(player2, 8, 2);
        player2.down();
		checkPos(player2, 8, 4);
        
        Assertions.assertThrows(IllegalStateException.class, () -> {
			player1.down();
		}, "Sjekker at down() kaster illegalState når den skal");
    }

    @Test
	@DisplayName("Move left")
	public void testLeft() {
		player1.left();
		checkPos(player1, 6, 16);
        player1.left();
		checkPos(player1, 4, 16);
        player1.left(); //x_pos == 2
        player1.left(); //x_pos == 0
        Assertions.assertThrows(IllegalStateException.class, () -> {
			player1.left();
		}, "Sjekker at left() kaster illegalState når den skal");
        checkPos(player1, 0, 16);

        player2.left();
		checkPos(player2, 6, 0);
        player2.left();
		checkPos(player2, 4, 0);
        player2.left(); //x_pos == 2
        player2.left(); //x_pos == 0
        Assertions.assertThrows(IllegalStateException.class, () -> {
			player2.left();
		}, "Sjekker at left() kaster illegalState når den skal");
        checkPos(player2, 0, 0);
	}

    @Test
	@DisplayName("Move right")
	public void testRight() {
		player1.right();
		checkPos(player1, 10, 16);
        player1.right();
		checkPos(player1, 12, 16);
        player1.right();//x_pos == 14
        player1.right();//x_pos == 16;
        Assertions.assertThrows(IllegalStateException.class, () -> {
			player1.right();
		}, "Sjekker at right() kaster illegalState når den skal");
        checkPos(player1, 16, 16);

        player2.right();
		checkPos(player2, 10, 0);
        player2.right();
		checkPos(player2, 12, 0);
        player2.right();//x_pos == 14
        player2.right();//x_pos == 16;
        Assertions.assertThrows(IllegalStateException.class, () -> {
			player2.right();
		}, "Sjekker at right() kaster illegalState når den skal");
        checkPos(player2, 16, 0);
	}


    @Test
    @DisplayName("Movement with walls")
    public void testWithWall() {
        Walls wall1 = new Walls(4,4,1,4); 
        Walls wall2 = new Walls(3,3,4,1);
        wall1.addWall(); //legger en vegg horisontal
        wall2.addWall(); //legger vegg vertikalt. 
        
        player1.up();
        player1.up();
        player1.up();
        player1.up(); //For å flytte brikken under veggen, 
                      //nå skal det ikke gå ann å gå oppover
        Assertions.assertThrows(IllegalStateException.class, () -> {
            player1.up();
        });
        player1.left();
        player1.up();
        player1.right();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            player1.down();
        });

        player1.left();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            player1.left();
        });



        player2.down();
        player2.down();
        player2.down();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            player2.down();
        });
        player2.left();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            player2.left();
        });
    }

    @Test
    @DisplayName("Tester increment funksjon")
    public void increment(){
        player1.setWalls(3);
        player1.incrementWalls();

        Assertions.assertEquals(4, player1.getWalls());
    }

    @Test
    @DisplayName("Change turn function")
    public void changeTurn(){
        player1.changeTurn();
        Assertions.assertFalse(player1.getMyTurn());
    }

    @Test
    @DisplayName("Game over player1 wins")
    public void gameover1(){
        player1.setY_pos(2);
        Assertions.assertTrue(player1.gameOver1());
    }

    @Test
    @DisplayName("Game over player2 wins")
    public void gameover2(){
        player2.setY_pos(14);
        Assertions.assertTrue(player2.gameOver2());
    }


}
