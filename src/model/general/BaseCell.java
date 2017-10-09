package model.general;

import javafx.scene.control.Button;



public abstract class BaseCell {
    public Button button = new Button();
    public boolean isFlagged = false;
    public boolean isActivated = false;
    public int x;
    public int y;

    public abstract void show();
}
