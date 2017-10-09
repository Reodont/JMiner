package model;

import javafx.scene.input.MouseButton;
import model.general.NumberCell;

import static model.FieldFx.activated;
import static model.FieldFx.showWin;

public class NumberCellFx extends NumberCell {
    private final Integer i;

    public NumberCellFx(Integer i) {
        this.i = i;
        button.setOnMouseClicked(event -> {
            MouseButton button = event.getButton();

            if (button == MouseButton.PRIMARY) {
                if (!isFlagged) {
                    this.button.setText(i.toString());
                    pickColor();
                    isActivated = true;
                    activated--;
                    if (activated == 0) showWin();
                }
            } else if (button == MouseButton.SECONDARY && !isActivated) {
                if (!isFlagged) {
                    this.button.setText("F");
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
        pickColor();
        this.isActivated = true;
        activated--;
    }

    private void pickColor() {
        switch (i) {
            case 1:
                this.button.setStyle("-fx-background-color: #6aed47; ");
                break;
            case 2:
                this.button.setStyle("-fx-background-color: #d8ed50; ");
                break;
            case 3:
                this.button.setStyle("-fx-background-color: #ed974c; ");
                break;
            case 4:
                this.button.setStyle("-fx-background-color: #ed75c1; ");
                break;
            case 5:
                this.button.setStyle("-fx-background-color: #64edeb; ");
                break;
            case 6:
                this.button.setStyle("-fx-background-color: #ed01a2; ");
                break;
            case 7:
                this.button.setStyle("-fx-background-color: #ed0006; ");
                break;
            case 8:
                this.button.setStyle("-fx-background-color: #37ed00; ");
                break;
        }
    }
}
