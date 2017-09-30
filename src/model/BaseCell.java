package model;

import javafx.scene.control.Button;



public abstract class BaseCell {
    public Button button = new Button();
    protected boolean isFlagged = false;
    protected boolean isActivated = false;
    public int x;
    public int y;

    public abstract void show();
}
