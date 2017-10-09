package model;

import model.general.EmptyCell;

import static model.FieldFx.activated;

public class EmptyCellFx extends EmptyCell {
    EmptyCellFx(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void show() {
        button.setStyle("-fx-background-color: #7140ED; ");
        activated--;
        this.isActivated = true;
    }
}
