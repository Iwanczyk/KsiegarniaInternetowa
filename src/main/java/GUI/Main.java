
package GUI;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
      
        Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/MainApp.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/style.css");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
        primaryStage.setTitle("Ksiêgarnia internetowa");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}