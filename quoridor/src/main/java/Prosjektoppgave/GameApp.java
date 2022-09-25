package Prosjektoppgave;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GameApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(javafx.stage.Stage theStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Board.fxml"));
            Scene scene = new Scene(root);
            theStage.setScene(scene);
            theStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}