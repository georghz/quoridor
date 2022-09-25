package Prosjektoppgave;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


//Her skal du teste lister, det må du finne eksempler på fra Undassene. 

public class WallsTest {
    //I spillet er UI opprettet for å  gjøre opplevelsen MYE lettere. Derfor når jeg oppretter vegger
    // i denne testfilen, så vil koordinatene kanskje virke litt rart, men de blir "oversatt" til rett
    // koordinat. 

    private Walls wall1;
    private Walls wall2;
    private Walls wall3;
    private Walls wall4;
    private Walls wall5;
    private Walls wall6;

    

    //Tror ikke dette er noe særlig å ha her egt, kan heller opprettte vegg i player testen også se om
    // brikken kan gå over veggen. Her kan jeg heller teste om vi får opprettet nye vegger og om plasseringene er
    //lovlige
    // private void checkPlacement(Walls walls, int x, int y) {
	// 	Assertions.assertEquals(x, walls.columnIndex, "Wrong x coordinate");
	// 	Assertions.assertEquals(y, walls.rowIndex, "Wrong y coordinate");
	// }
    

    @BeforeEach
	public void setup() {
		wall1 = new Walls(4,5,1,4); //Horisontal, x=7, y=9.
        wall2 = new Walls(9,13,4,1); //Ulovlig vegg horisontal

        wall3 = new Walls(6,6,4,1); //Vertikal, x = 11, y=11; 
        wall4 = new Walls(12,1,4,1); //Ulovlig vegg Vertikal 
        wall5 = new Walls(-1,1,4,1); //Ulovlig vegg Vertikal 
        wall6 = new Walls(-3,-5,4,1); //Ulovlig vegg Vertikal 

	}

    @Test
    @DisplayName("Legal placement")
    public void placeWall(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			wall2.addWall();
		} );

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			wall4.addWall();
		} );

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			wall5.addWall();
		} );
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
			wall6.addWall();
		} );
        
        

        wall1.addWall();
        wall3.addWall();
    }

    


}
