package model;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.Random;

public class Field {
    private  final int N = 10;

    public  int[][] field = new int[N][N];
    public BaseCell[][] buttonField = new BaseCell[N][N];

    private boolean isCorrectIndex(int x, int y) {
        return (x >= 0 && y >= 0 && y < N && x < N);
    }

    private void recursiveShow(int x, int y) {
        if (isCorrectIndex(x-1, y-1) && ( buttonField[x-1][y-1] instanceof EmptyCell) && !buttonField[x-1][y-1].isActivated) {
            buttonField[x-1][y-1].show();
            recursiveShow(x-1, y-1);
        }
        if (isCorrectIndex(x, y-1) && ( buttonField[x][y-1] instanceof EmptyCell) && !buttonField[x][y-1].isActivated) {
            buttonField[x][y-1].show();
            recursiveShow(x, y-1);
        }
        if (isCorrectIndex(x+1, y-1) && ( buttonField[x+1][y-1] instanceof EmptyCell) && !buttonField[x+1][y-1].isActivated) {
            buttonField[x+1][y-1].show();
            recursiveShow(x+1, y-1);
        }
        if (isCorrectIndex(x-1, y) && ( buttonField[x-1][y] instanceof EmptyCell) && !buttonField[x-1][y].isActivated) {
            buttonField[x-1][y].show();
            recursiveShow(x-1, y);
        }
        if (isCorrectIndex(x+1, y) && ( buttonField[x+1][y] instanceof EmptyCell) && !buttonField[x+1][y].isActivated) {
            buttonField[x+1][y].show();
            recursiveShow(x+1, y);
        }
        if (isCorrectIndex(x-1, y+1) && ( buttonField[x-1][y+1] instanceof EmptyCell) && !buttonField[x-1][y+1].isActivated) {
            buttonField[x-1][y+1].show();
            recursiveShow(x-1, y+1);
        }
        if (isCorrectIndex(x, y+1) && ( buttonField[x][y+1] instanceof EmptyCell) && !buttonField[x][y+1].isActivated) {
            buttonField[x][y+1].show();
            recursiveShow(x, y+1);
        }
        if (isCorrectIndex(x+1, y+1) && ( buttonField[x+1][y+1] instanceof EmptyCell) && !buttonField[x+1][y+1].isActivated) {
            buttonField[x+1][y+1].show();
            recursiveShow(x+1, y+1);
        }

        if (isCorrectIndex(x-1, y-1) &&  !buttonField[x-1][y-1].isActivated)
            buttonField[x-1][y-1].show();
        if (isCorrectIndex(x, y-1) &&  !buttonField[x][y-1].isActivated)
            buttonField[x][y-1].show();
        if (isCorrectIndex(x+1, y-1) && !buttonField[x+1][y-1].isActivated)
            buttonField[x+1][y-1].show();
        if (isCorrectIndex(x-1, y) &&  !buttonField[x-1][y].isActivated)
            buttonField[x-1][y].show();
        if (isCorrectIndex(x+1, y) &&  !buttonField[x+1][y].isActivated)
            buttonField[x+1][y].show();
        if (isCorrectIndex(x-1, y+1) &&  !buttonField[x-1][y+1].isActivated)
            buttonField[x-1][y+1].show();
        if (isCorrectIndex(x, y+1)  && !buttonField[x][y+1].isActivated)
            buttonField[x][y+1].show();
        if (isCorrectIndex(x+1, y+1) && !buttonField[x+1][y+1].isActivated)
            buttonField[x+1][y+1].show();

    }

    private void bombShow() {
        for (int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                buttonField[i][j].show();
    }

    public Field() {
        Random random = new Random();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                int temp = random.nextInt(N);
                if (temp < 2) {
                    field[i][j] = -1;
                    buttonField[i][j] = new BombCell();
                }
            }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (field[i][j] != -1) {
                    field[i][j] += ((isCorrectIndex(i - 1, j - 1) && (field[i - 1][j - 1] == -1)) ? 1 : 0)
                            + ((isCorrectIndex(i - 1, j) && (field[i - 1][j] == -1)) ? 1 : 0)
                            + ((isCorrectIndex(i - 1, j + 1) && (field[i - 1][j + 1] == -1)) ? 1 : 0)
                            + ((isCorrectIndex(i, j - 1) && (field[i][j - 1] == -1)) ? 1 : 0)
                            + ((isCorrectIndex(i, j + 1) && (field[i][j + 1] == -1)) ? 1 : 0)
                            + ((isCorrectIndex(i + 1, j - 1) && (field[i + 1][j - 1] == -1)) ? 1 : 0)
                            + ((isCorrectIndex(i + 1, j) && (field[i + 1][j] == -1)) ? 1 : 0)
                            + ((isCorrectIndex(i + 1, j + 1) && (field[i + 1][j + 1] == -1)) ? 1 : 0);
                    buttonField[i][j] = (field[i][j] > 0) ? new NumberCell(field[i][j]) : new EmptyCell(i, j);
                    if (buttonField[i][j] instanceof EmptyCell) {
                        EmptyCell temp = (EmptyCell) buttonField[i][j];
                        temp.button.setOnMouseClicked((MouseEvent event) -> {
                            MouseButton button = event.getButton();

                            if (button == MouseButton.PRIMARY) {
                                temp.button.setText("Empty");
                                temp.isActivated = true;
                                recursiveShow(temp.x, temp.y);
                            } else if (button == MouseButton.SECONDARY && !temp.isActivated) {
                                if (!temp.isFlagged) {
                                    temp.button.setText("Flagged");
                                    temp.isFlagged = !temp.isFlagged;
                                } else {
                                    temp.button.setText("");
                                    temp.isFlagged = !temp.isFlagged;
                                }
                            }
                        });
                    }
                }
                    if (buttonField[i][j] instanceof  BombCell) {
                        BombCell temp = (BombCell) buttonField[i][j];
                        temp.button.setOnMouseClicked((MouseEvent event) -> {
                            MouseButton button = event.getButton();

                            if (button == MouseButton.PRIMARY) {
                                temp.button.setText("Bomb");
                                temp.isActivated = true;
                                bombShow();
                            }   else if (button == MouseButton.SECONDARY && !temp.isActivated){
                                if (!temp.isFlagged) {
                                    temp.button.setText("Flagged");
                                    temp.isFlagged = !temp.isFlagged;
                                }
                                else {
                                    temp.button.setText("");
                                    temp.isFlagged = !temp.isFlagged;
                                }
                            }
                        });
                    }

            }
    }


}
