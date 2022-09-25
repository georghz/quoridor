package Prosjektoppgave;
import javafx.scene.layout.GridPane;

public class Player {

    private int x_pos;
    private int y_pos;
    private boolean myTurn;
    private int numWalls;

    public Player(int x_pos, int y_pos, boolean myTurn, int numWalls){
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.myTurn = myTurn;
        this.numWalls = numWalls;
    }
    

    public void incrementWalls(){
        this.numWalls++;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }
    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }
    public void setMyTurn(boolean myTurn){
        this.myTurn = myTurn;
    }
    public void setWalls(int numWalls){
        this.numWalls = numWalls;
    }

    
    public boolean gameOver1(){
        if (validMoveUp() && myTurn() && y_pos==2){
            return true;
        }
        return false;
    }
    public boolean gameOver2(){
        if (validMoveDown() && myTurn() && y_pos==14){
            return true;
        }
        return false;
    }


    public boolean getMyTurn(){
        return this.myTurn;
    }
    public int getWalls(){
        return this.numWalls;
    }
    public int getXPosition(){
        return this.x_pos;
    }
    public int getYPosition(){
        return this.y_pos;
    }
   
    public boolean myTurn(){
        if(myTurn){
            return true;
        } else{
            throw new IllegalArgumentException("Det er ikke din tur");
        }
    }
    public boolean changeTurn(){
        if(myTurn){
            return myTurn = false;
        }else{
            return myTurn = true;
        }
    }
    
    public void restart(int player){
        setX_pos(8);
        if(player ==1){
            setWalls(0);
            setY_pos(16);
            setMyTurn(true);
        }else{
            setY_pos(0);
            setMyTurn(false);
            setWalls(0);
        }
    }
    
    //Logikk for spiller å flytte seg oppover
    private boolean validMoveUp(){
        if(y_pos ==0){
            return false;
        }
        for(GridPane num: Walls.wallsArray){
            int x = GridPane.getColumnIndex(num);
            int y = GridPane.getRowIndex(num);
            int z = GridPane.getRowSpan(num);
            if((x == 0) && (x_pos == x || x_pos == x + 2) && (y_pos == y+1) && (z ==1)){
                return false;
            }else if((x_pos == x +1 || x_pos == x + 3) && (y_pos == y+1) && (z ==1)){
                return false;
            }else if(y_pos == 0){
                return false;
            }
        }return true;
    }
    public Integer up(){
        if(validMoveUp() && myTurn()){
            y_pos -=2;
            return y_pos;
        }else{
            throw new IllegalStateException("Du står på feil plass for dette trekket");        }
    }
    
    //Logikk for å flytte seg nedover
    private boolean validMoveDown(){
        if(y_pos ==16){
            return false;
        }
        for(GridPane num: Walls.wallsArray){
            int x = GridPane.getColumnIndex(num);
            int y = GridPane.getRowIndex(num);
            int z = GridPane.getRowSpan(num);
            if((x == 0) && (x_pos == x || x_pos == x + 2) && (y_pos == y-1) && (z == 1)){
                return false;
            }else if((x_pos == x +1 || x_pos == x + 3) && (y_pos == y-1) && (z ==1)){
                return false;
            }else if(y_pos == 16){
                return false;
            }
        }return true;
    }
    public Integer down(){
        if(validMoveDown() && myTurn()){
            y_pos +=2;
            return y_pos;
        }else{
            throw new IllegalStateException("Du står på feil plass for dette trekket");
        }
    }
    //Logikk for å flytte seg til venstre
    private boolean validMoveLeft(){
        if(x_pos ==0){
            return false;
        }
        for(GridPane num: Walls.wallsArray){
            int x = GridPane.getColumnIndex(num);
            int y = GridPane.getRowIndex(num);
            int z = GridPane.getRowSpan(num);
            if((y==0 || y==16) && (x_pos == x+1 ) 
                && (y_pos == y || y_pos == y +2) && (z == 4)){
                return false;
            }else if((x_pos == x+1 ) 
                && (y_pos == y +1 || y_pos == y +3) && (z == 4)){
                return false;
            } else if(x_pos == 0){
                return false;
            }
        }return true;
    }
    public Integer left(){
        if(validMoveLeft() && myTurn()){
            x_pos-=2;
            return x_pos;
        }else{
            throw new IllegalStateException("Du står på feil plass for dette trekket");        }
    }
    //Logikk for å flytte til høyre
    private boolean validMoveRight(){
        if(x_pos ==16){
            return false;
        }
        for(GridPane num: Walls.wallsArray){
            int x = GridPane.getColumnIndex(num);
            int y = GridPane.getRowIndex(num);
            int z = GridPane.getRowSpan(num);
            if((y==0 ) && (x_pos == x-1 ) 
            && (y_pos == y || y_pos == y +2) && (z == 4)){
                return false;
            }else if((x_pos == x-1 ) 
            && (y_pos == y +1 || y_pos == y +3) && (z == 4)){
                return false;
            }
        }return true;
    }
    public Integer right(){
        if(validMoveRight() && myTurn()){
            x_pos +=2;
            return x_pos;
        }else{
            throw new IllegalStateException("Du står på feil plass for dette trekket");        
        }
    }
    
}