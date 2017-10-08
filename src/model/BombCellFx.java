package model;

import javafx.scene.input.MouseButton;

public class BombCellFx extends BombCell {
    public BombCellFx() {
        button.setOnMouseClicked(event -> {
            MouseButton button = event.getButton();

            if (button == MouseButton.PRIMARY) {
                if (!isActivated) {
                    this.button.setText("Bomb");
                    this.button.setStyle("-fx-background-color: #7140ed; ");
                    //TO-DO GAME OVER DIALOG WINDOW
                }
            } else if (button == MouseButton.SECONDARY && !isActivated)
                if (!isFlagged) {
                    this.button.setText("Flagged");
                    this.isFlagged = !this.isFlagged;
                } else {
                    this.button.setText("");
                    this.isFlagged = !this.isFlagged;
                }
        });
    }

    public void show() {
        this.button.setText("Bomb");
        button.setStyle("-fx-background-color: #7140ed; ");
        this.isActivated = true;
    }
}
