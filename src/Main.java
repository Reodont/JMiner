import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Field;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Field field = new Field();
        primaryStage.setTitle("JMiner!");
        VBox root = new VBox();
        for (int i = 0 ; i < 10; i++) {
            HBox hBox = new HBox(5);
            for (int j = 0; j < 10; j++) {
                field.buttonField[i][j].button.setMinSize(60.0, 60.0);
                hBox.getChildren().add(field.buttonField[i][j].button);
            }
            root.getChildren().add(hBox);
        }
        primaryStage.setScene(new Scene(root, 650, 610));
        primaryStage.show();
    }
}