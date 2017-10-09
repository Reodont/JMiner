import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ConcreteFxFactory;

import java.util.Optional;

public class Main extends Application {

    static int N = 30;
    static double widthCell, heightCell, widthWindow, heightWindow;
    ConcreteFxFactory fxFactory = new ConcreteFxFactory();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        constructWelcomeMessage();
        drawScene(primaryStage);
    }

    void constructWelcomeMessage() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Choose your game level");
        alert.setHeaderText("Good morning Sir or Madam. That`s seems you wanna play..");
        alert.setContentText("Choose your level and have fun :p");

        ButtonType buttonTypeOne = new ButtonType("Easy(10x10)");
        ButtonType buttonTypeTwo = new ButtonType("Medium(20x20)");
        ButtonType buttonTypeThree = new ButtonType("Hardcore(25x25)");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            N = 10;
            widthCell = 60.0;
            heightCell = 60.0;
            widthWindow = 635;
            heightWindow = 680;
        } else if (result.get() == buttonTypeTwo) {
            N = 20;
            widthCell = 30.0;
            heightCell = 30.0;
            widthWindow = 650;
            heightWindow = 690;
        } else if (result.get() == buttonTypeThree) {
            N = 25;
            widthCell = 25.0;
            heightCell = 22.0;
            widthWindow = 650;
            heightWindow = 690;
        } else System.exit(0);
        fxFactory.createField(N);
    }

    MenuBar constructMenuBar(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        Menu menuLevel = new Menu("Select level");
        Menu menuScore = new Menu("Highscore");
        Menu menuAbout = new Menu("About");
        MenuItem exit = new MenuItem("Exit");
        MenuItem aboutProgram = new MenuItem("About Program");
        MenuItem easyLevel = new MenuItem("Easy (10x10)");
        MenuItem mediumLevel = new MenuItem("Medium (20x20)");
        MenuItem hardLevel = new MenuItem("Hard (25x25)");
        aboutProgram.setOnAction(t -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About program");
            alert.setHeaderText("JMiner 1.0");
            String s = "This program was created by Roman Kvasnytskyy in 2017. ";
            alert.setContentText(s);
            alert.show();
        });
        exit.setOnAction(t -> System.exit(0));
        easyLevel.setOnAction(t -> {
            N = 10;
            widthCell = 60.0;
            heightCell = 60.0;
            widthWindow = 635;
            heightWindow = 680;
            fxFactory.createField(N);
            primaryStage.close();
            drawScene(primaryStage);
        });
        mediumLevel.setOnAction(t -> {
            N = 20;
            widthCell = 30.0;
            heightCell = 30.0;
            widthWindow = 650;
            heightWindow = 690;
            fxFactory.createField(N);
            primaryStage.close();
            drawScene(primaryStage);
        });
        hardLevel.setOnAction(t -> {
            N = 25;
            widthCell = 25.0;
            heightCell = 22.0;
            widthWindow = 650;
            heightWindow = 690;
            fxFactory.createField(N);
            primaryStage.close();
            drawScene(primaryStage);
        });
        menuBar.getMenus().addAll(menuLevel, menuScore, menuAbout);
        menuLevel.getItems().addAll(easyLevel, mediumLevel, hardLevel, new SeparatorMenuItem(), exit);
        menuAbout.getItems().add(aboutProgram);
        return menuBar;
    }

    void drawScene(Stage primaryStage) {
        primaryStage.setTitle("JMiner!");
        VBox root = new VBox(5);
        root.getChildren().add(constructMenuBar(primaryStage));
        for (int i = 0; i < N; i++) {
            HBox hBox = new HBox(5);
            for (int j = 0; j < N; j++) {
                fxFactory.getFieldFx().buttonField[i][j].button.setMinSize(widthCell, heightCell);
                hBox.getChildren().add(fxFactory.getFieldFx().buttonField[i][j].button);
            }
            root.getChildren().add(hBox);
        }
        Scene scene = new Scene(root, widthWindow, heightWindow);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("DarkTheme.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

