package model;

import model.general.BombCell;

public class BombCellFx extends BombCell {
    public void show() {
        this.button.setStyle("-fx-background-color: rgba(237,45,6,0.85); ");
        this.isActivated = true;
    }
}
