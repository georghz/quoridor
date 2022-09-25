package Prosjektoppgave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.layout.GridPane;

public class Walls {
    
    private int columnIndex;
    private int rowIndex;
    private int rowSpan;
    private int columnSpan;

    public Walls(int columnIndex, int rowIndex, int rowSpan, int columnSpan){
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        this.rowSpan = rowSpan;
        this.columnSpan = columnSpan;
    }

    public static ArrayList<List> wallsList = new ArrayList<List>();            //Listen brukes kun for load og save filen
   
    public static ArrayList<GridPane> wallsArray = new ArrayList<GridPane>();   //Listen brukes for å visualisere vegger i GridPane og logikk for å flytte brikkene

    
    public void addWall(){
        setXC();
        setYC();
        
        wallsList.add(Arrays.asList(columnIndex, rowIndex, rowSpan, columnSpan));
        createGridPane(columnIndex, rowIndex, rowSpan, columnSpan);
    }

    public void addWallFromFile(){
        wallsList.add(Arrays.asList(columnIndex, rowIndex, rowSpan, columnSpan));
        createGridPane(columnIndex, rowIndex, rowSpan, columnSpan);
    }

    //Opprette gridpane her
    public GridPane createGridPane(int columnIndex, int rowIndex, int rowSpan, int columnSpan){
        GridPane name = new GridPane();                        
        GridPane.setColumnIndex(name, columnIndex);
        GridPane.setRowIndex(name, rowIndex);
        GridPane.setRowSpan(name, rowSpan);     
        GridPane.setColumnSpan(name, columnSpan); 
        name.setStyle("-fx-background-color: brown;");
        wallsArray.add(name);
        return name; 
    }


    //Oversetter fra det egentlige koordinatsystemer som er 0 <=  >= 16 (med koordinatene brikkene står) 
    //til koordinatsystemet som spillet visualiserer (der veggene står)
    public int setXC(){
        if(columnIndex == 0){
            return columnIndex = 0;
        }else if(columnIndex == 1){
            return columnIndex = 1;
       } else if(columnIndex == 2){
            return columnIndex = 3;
       } else if(columnIndex == 3){
            return columnIndex = 5;
       } else if(columnIndex == 4){
            return columnIndex = 7;
       } else if(columnIndex == 5){
            return columnIndex = 9;
       } else if(columnIndex == 6){
            return  columnIndex = 11;
       } else if(columnIndex == 7){
           return columnIndex = 13;
       } else if(columnIndex == 8){
           return columnIndex = 15;
       } else{
           throw new IllegalArgumentException("Ikke lovlig plassering av vegg");
       }
    }


    public int setYC(){ 
        if (rowIndex ==0){
        return rowIndex = 0;
        }else if(rowIndex == 1){
            return rowIndex = 1;
       } else if(rowIndex == 2){
            return rowIndex = 3;
       } else if(rowIndex == 3){
            return rowIndex = 5;
       } else if(rowIndex == 4){
            return rowIndex = 7;
       } else if(rowIndex == 5){
            return rowIndex = 9;
       } else if(rowIndex == 6){
            return rowIndex = 11;
       } else if(rowIndex == 7){
           return rowIndex = 13;
       } else if(rowIndex == 8){
           return rowIndex = 15;
       } else{
           throw new IllegalArgumentException("Ikke lovlig plassering av vegg");
       }
    }

    public void validWallH(){
        if(rowIndex== 0 || columnIndex ==8 || columnIndex > 13){
            throw new IllegalArgumentException("Ikke lovlig plassering av vegg");
        } 
    }

    public void validWallV(){
        if(columnIndex== 0 ||rowIndex == 8 || rowIndex > 13){
            throw new IllegalArgumentException("Ikke lovlig plassering av vegg");
        }
    }
}

