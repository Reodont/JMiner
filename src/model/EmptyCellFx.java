package model;

public class EmptyCellFx extends EmptyCell {
    EmptyCellFx(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void show() {
        button.setStyle("-fx-background-color: #7140ed; ");
        this.isActivated = true;
    }
}
