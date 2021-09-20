
package budget;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Budget extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("CSS/Style.css");
        stage.setTitle("Buget");
        stage.getIcons().add(new Image("/Image/icon.png"));
        stage.setWidth(650);
        stage.setHeight(600);
        stage.setScene(scene);
        
        stage.show();
    }

  
    public static void main(String[] args) {
        launch(args);
    }
    
}
