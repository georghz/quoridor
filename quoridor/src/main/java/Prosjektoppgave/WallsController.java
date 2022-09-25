package Prosjektoppgave;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class WallsController {

    @FXML
    private TextField xcoordinate;

    @FXML
    private TextField ycoordinate;

    @FXML 
    private TextField ValidCoordinatesText;
    
    
    //Knapp for horisontalvegg
    @FXML
    public void xPlacement(ActionEvent e){
        try {
            Walls wall = new Walls(Integer.parseInt(xcoordinate.getText()), Integer.parseInt(ycoordinate.getText()),1,4);
            wall.validWallH();
            wall.addWall();
            ValidCoordinatesText.setText("Vellykket! Trykk på AddWall for å spille.");

        } catch (Exception ex) {
            ValidCoordinatesText.setText("Ikke gyldig input, prøv på nytt!");

        }
    }

    //knapp for vertikalvegg
    @FXML
    public void yPlacement(ActionEvent e){
        try {
            Walls wall = new Walls(Integer.parseInt(xcoordinate.getText()), Integer.parseInt(ycoordinate.getText()),4,1);
            wall.validWallV();
            wall.addWall();
            ValidCoordinatesText.setText("Velykket! Trykk på AddWall for å spille.");

        } catch (Exception ex) {
            ValidCoordinatesText.setText("Ikke gyldig input, prøv på nytt!");
        }
    }
}