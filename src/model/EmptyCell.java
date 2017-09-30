package model;

import javafx.scene.input.MouseButton;

public class EmptyCell extends BaseCell {

    EmptyCell(int x, int y) {
        this.x = x;
        this.y = y;
    }
   /* public EmptyCell() {
        button.setOnMouseClicked(event -> {
            MouseButton button = event.getButton();

            if (button == MouseButton.PRIMARY) {
                this.button.setText("Empty");
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
    }*/

   public void show() {
       this.button.setText("Empty");
       this.isActivated = true;
   }
}
