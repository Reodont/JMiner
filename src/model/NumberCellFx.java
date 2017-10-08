package model;

import javafx.scene.input.MouseButton;

public class NumberCellFx extends NumberCell {
    private final Integer i;

    public NumberCellFx(Integer i) {
        this.i = i;
        button.setOnMouseClicked(event -> {
            MouseButton button = event.getButton();

            if (button == MouseButton.PRIMARY) {
                if (!isFlagged) {
                    this.button.setText(i.toString());
                    this.button.setStyle("-fx-background-color: #7140ed; ");
                    isActivated = true;
                }
            } else if (button == MouseButton.SECONDARY && !isActivated) {
                if (!isFlagged) {
                    this.button.setText("Flagged");
                    this.isFlagged = !this.isFlagged;
                } else {
                    this.button.setText("");
                    this.isFlagged = !this.isFlagged;
                }
            }
        });
    }

    public void show() {
        this.button.setText(i.toString());
        button.setStyle("-fx-background-color: #7140ed; ");
        this.isActivated = true;
    }
}
