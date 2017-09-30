package model;

import javafx.scene.input.MouseButton;

public class NumberCell extends BaseCell {
    final Integer i;
    public NumberCell(Integer i) {
        this.i = i;
        button.setOnMouseClicked(event -> {
            MouseButton button = event.getButton();

            if (button == MouseButton.PRIMARY) {
                this.button.setText(i.toString());
                isActivated = true;
            }   else if (button == MouseButton.SECONDARY && !isActivated){
                    if (!isFlagged) {
                        this.button.setText("Flagged");
                        this.isFlagged = !this.isFlagged;
                    }
                    else {
                        this.button.setText("");
                        this.isFlagged = !this.isFlagged;
                    }
            }
        });
    }

    public void show() {
        this.button.setText(i.toString());
        this.isActivated = true;
    }
}
