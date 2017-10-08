import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ConcreteFxFactory;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ConcreteFxFactory fxFactory = new ConcreteFxFactory();
        fxFactory.createField();
        primaryStage.setTitle("JMiner!");
        VBox root = new VBox(5);
        for (int i = 0; i < 10; i++) {
            HBox hBox = new HBox(5);
            for (int j = 0; j < 10; j++) {
                fxFactory.getFieldFx().buttonField[i][j].button.setMinSize(60.0, 60.0);
                hBox.getChildren().add(fxFactory.getFieldFx().buttonField[i][j].button);
            }
            root.getChildren().add(hBox);
        }
        Scene scene = new Scene(root, 650, 650);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}