package Prosjektoppgave;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Controller {
    
    @FXML
    private GridPane TESTONE;

    @FXML
    private Circle blueCircle;

    @FXML
    private Circle pinkCircle;

    @FXML
    private TextField textOutput;


    @FXML
    private TextField textOutput2;

    @FXML
    private Button loadButton;

    
    private GameToFile gtf = new GameToFile();

    public Player player1 = new Player(8,16, true, 0);

    public Player player2 = new Player(8,0, false, 0);

  

    private int indexNumber = 0;
    public void addWall(){ //Legger frem vegger i FXML, logikken skjer i Walls.java
        if(indexNumber < Walls.wallsArray.size()){
            GridPane wall = new GridPane();
            wall = Walls.wallsArray.get(indexNumber);
            TESTONE.add(wall, GridPane.getColumnIndex(wall), GridPane.getRowIndex(wall), GridPane.getColumnSpan(wall), GridPane.getRowSpan(wall)); 
            indexNumber++; 
        }
    }

    @FXML
    private void addWall(ActionEvent e){
        addWall();
    }

    @FXML
    private void deleteWall(){
        TESTONE.getChildren().removeAll(Walls.wallsArray); 
    }

    @FXML
    private void restart(ActionEvent e){
        deleteWall();
        Walls.wallsArray.clear();
        indexNumber = 0;
        textOutput.setText("Velkommen til Quoridor");
        textOutput2.setText("Lykke til!");
        player1.restart(1);
        player2.restart(2);
        GridPane.setColumnIndex(blueCircle, player1.getXPosition());
        GridPane.setRowIndex(blueCircle, player1.getYPosition());
        GridPane.setColumnIndex(pinkCircle, player2.getXPosition());
        GridPane.setRowIndex(pinkCircle, player2.getYPosition());
    }

    @FXML
    private void saveGame(ActionEvent e){
        gtf.save("quoridor.txt", player1, player2);
        
    }

    @FXML
    private void loadGame(ActionEvent e){
        deleteWall(); 
        Walls.wallsArray.clear();
        

        gtf.load("quoridor.txt", player1, player2);
        
        GridPane.setColumnIndex(blueCircle, player1.getXPosition());
        GridPane.setRowIndex(blueCircle, player1.getYPosition());
        GridPane.setColumnIndex(pinkCircle, player2.getXPosition());
        GridPane.setRowIndex(pinkCircle, player2.getYPosition());

        if(player1.getMyTurn()== true){
            textOutput.setText("Spiller 1 sin tur");
            textOutput2.setText("Walls used by player 1: " + player1.getWalls() + "/10") ;
        }else{
            textOutput.setText("Spiller 2 sin tur");
            textOutput2.setText("Walls used by player 2: " + player2.getWalls() + "/10") ;
        }
        indexNumber = 0;
        for(int i = 0; i <Walls.wallsArray.size(); i++){ 
            addWall();  
        }
    }
    



    //Flytter brikke 1 opp.
    @FXML
    private void moveUp(ActionEvent e){
        if (player1.gameOver1()){
            textOutput.setText("Gratulerer, spiller 1 har vunnet!");
            textOutput2.setText("Trykk på restart for å starte nytt spill");
            GridPane.setRowIndex(blueCircle, player1.up());
            player1.changeTurn();
        }else {
            GridPane.setRowIndex(blueCircle, player1.up());
            player1.changeTurn();
            player2.changeTurn();            
            textOutput.setText("Spiller 2 sin tur");
            textOutput2.setText("Vegger brukt av spiller 2: " + player2.getWalls() + "/10") ;
            }
    }

    //Flytter brikken ned.
    @FXML
    private void moveDown(ActionEvent e){
        GridPane.setRowIndex(blueCircle, player1.down());
        player1.changeTurn();
        player2.changeTurn();
        textOutput.setText("Spiller 2 sin tur");
        textOutput2.setText("Vegger brukt av spiller 2: " + player2.getWalls() + "/10");
    }


    //Flytter brikken til venstre
    @FXML
    private void moveLeft(ActionEvent e){
        GridPane.setColumnIndex(blueCircle, player1.left());
        player1.changeTurn();
        player2.changeTurn();
        textOutput.setText("Spiller 2 sin tur");
        textOutput2.setText("Vegger brukt av spiller 2: " + player2.getWalls() + "/10");

    }

    //Flytter brikken til høyre
    @FXML
    private void moveRight(ActionEvent e){
        GridPane.setColumnIndex(blueCircle, player1.right());
        player1.changeTurn();
        player2.changeTurn();
        textOutput.setText("Spiller 2 sin tur");
        textOutput2.setText("Vegger brukt av spiller 2: " + player2.getWalls() + "/10");

    }

   
    //Forflytning spiller 2
    //Flytter brikken opp
    @FXML
    private void moveUpP2(ActionEvent e){
        GridPane.setRowIndex(pinkCircle, player2.up());
        player1.changeTurn();
        player2.changeTurn();
        textOutput.setText("Spiller 1 sin tur");
        textOutput2.setText("Vegger brukt av spiller 1: " + player1.getWalls() + "/10");
    }


    //Flytter brikken ned.
    @FXML
    private void moveDownP2(ActionEvent e){
        if (player2.gameOver2()){
            textOutput.setText("Gratulerer, spiller 2 har vunnet!");
            textOutput.setText("Trykk på restart for å starte nytt spill");
            GridPane.setRowIndex(pinkCircle, player2.down());
            player2.changeTurn();

        }else{
            GridPane.setRowIndex(pinkCircle, player2.down());
            player1.changeTurn();
            player2.changeTurn();
            textOutput.setText("Spiller 1 sin tur");
            textOutput2.setText("Vegger brukt av spiller 1: " + player1.getWalls() + "/10");
        }
    }

    // //Flytter brikken til venstre
    @FXML
    private void moveLeftP2(ActionEvent e){
        GridPane.setColumnIndex(pinkCircle, player2.left());
        player1.changeTurn();
        player2.changeTurn();
        textOutput.setText("Spiller 1 sin tur");
        textOutput2.setText("Vegger brukt av spiller 1: " + player1.getWalls() + "/10");
    }
    
    //Flytter brikken til høyre
    @FXML
    private void moveRightP2(ActionEvent e){
        GridPane.setColumnIndex(pinkCircle, player2.right());
        player1.changeTurn();
        player2.changeTurn();
        textOutput.setText("Spiller 1 sin tur");
        textOutput2.setText("Vegger brukt av spiller 1: " + player1.getWalls() + "/10");
    }

    
    @FXML
    private void wallPlacement(ActionEvent e){
    
        if(player1.myTurn() && player1.getWalls()<10){
            player1.changeTurn();
            player2.changeTurn();
            textOutput.setText("Spiller 2 sin tur");
            textOutput2.setText("Vegger brukt av spiller 2: " + player2.getWalls() + "/10");
            player1.incrementWalls();
            try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WallPlacement.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                        //Metoden er hentet herifra, tid 4.19. https://www.youtube.com/watch?v=IpqKiytEPGY
                } catch(Exception event){
                    event.printStackTrace();
                }
        }
    }

    @FXML
    private void wallPlacement2(ActionEvent e){
 
        if (player2.myTurn() && player2.getWalls()<10){
            player1.changeTurn();
            player2.changeTurn();
            textOutput.setText("Spiller 1 sin tur ");
            textOutput2.setText("Vegger brukt av spiller 1: " + player1.getWalls() + "/10");

            player2.incrementWalls();  

            try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WallPlacement.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                        //Metoden er hentet herifra, tid 4.19. https://www.youtube.com/watch?v=IpqKiytEPGY
                } catch(Exception event){
                    event.printStackTrace();
                }
        }
    }
}