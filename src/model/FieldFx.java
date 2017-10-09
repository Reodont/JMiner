package model;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.general.BaseCell;
import model.general.BombCell;
import model.general.EmptyCell;

import java.util.Random;

public class FieldFx {

    private static final int shift[] = {-1, 0, 1};
    static int activated;
    private final int N;
    public int[][] field;
    public BaseCell[][] buttonField;


    public FieldFx(int N) {
        this.N = N;
        field = new int[N][N];
        buttonField = new BaseCell[N][N];
        activated = N * N;
        Random random = new Random();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                int temp = random.nextInt(N);
                if (temp <= N / 5) {
                    field[i][j] = -1;
                    buttonField[i][j] = new BombCellFx();
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
                    buttonField[i][j] = (field[i][j] > 0) ? new NumberCellFx(field[i][j]) : new EmptyCellFx(i, j);
                    if (buttonField[i][j] instanceof EmptyCell) {
                        EmptyCell temp = (EmptyCell) buttonField[i][j];
                        temp.button.setOnMouseClicked((MouseEvent event) -> {
                            MouseButton button = event.getButton();

                            if (button == MouseButton.PRIMARY) {
                                if (!temp.isFlagged) {
                                    temp.isActivated = true;
                                    temp.button.setStyle("-fx-background-color: #7140ED; ");
                                    recursiveShow(temp.x, temp.y);
                                    if (activated == 0) showWin();
                                }
                            } else if (button == MouseButton.SECONDARY && !temp.isActivated) {
                                if (!temp.isFlagged) {
                                    temp.button.setText("F");
                                    temp.isFlagged = !temp.isFlagged;
                                } else {
                                    temp.button.setText("");
                                    temp.isFlagged = !temp.isFlagged;
                                }
                            }
                        });
                    }
                }
                if (buttonField[i][j] instanceof BombCell) {
                    BombCell temp = (BombCell) buttonField[i][j];

                    temp.button.setOnMouseClicked((MouseEvent event) -> {
                        MouseButton button = event.getButton();

                        if (button == MouseButton.PRIMARY) {
                            if (!temp.isFlagged) {
                                temp.button.setText("B");
                                temp.button.setStyle("-fx-background-color: #7140ED; ");
                                temp.isActivated = true;
                                bombShow();
                                showLose();
                            }
                        } else if (button == MouseButton.SECONDARY && !temp.isActivated) {
                            if (!temp.isFlagged) {
                                temp.button.setText("F");
                                activated--;
                                temp.isFlagged = !temp.isFlagged;
                            } else {
                                temp.button.setText("");
                                activated++;
                                temp.isFlagged = !temp.isFlagged;
                            }
                        }
                    });
                }
            }
    }

    public static void showWin() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End of game");
        alert.setHeaderText(null);
        alert.setContentText("You won, congratulations.");
        alert.showAndWait();
        System.exit(0);
    }

    private static void showLose() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End of game");
        alert.setHeaderText(null);
        alert.setContentText("You lost, sorry.");
        alert.showAndWait();
        System.exit(0);
    }

    private void bombShow() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                buttonField[i][j].show();
    }

    private boolean isCorrectIndex(int x, int y) {
        return (x >= 0 && y >= 0 && y < N && x < N);
    }

    private void recursiveShow(int x, int y) {
        for (int xAcc : shift)
            for (int yAcc : shift)
                if (this.isCorrectIndex(x + xAcc, y + yAcc) && this.buttonField[x + xAcc][y + yAcc] instanceof EmptyCell
                        && !this.buttonField[x + xAcc][y + yAcc].isActivated) {
                    this.buttonField[x + xAcc][y + yAcc].show();
                    this.recursiveShow(x + xAcc, y + yAcc);
                }
        for (int xAcc : shift)
            for (int yAcc : shift)
                if (this.isCorrectIndex(x + xAcc, y + yAcc)
                        && !this.buttonField[x + xAcc][y + yAcc].isActivated)
                    this.buttonField[x + xAcc][y + yAcc].show();
    }
}
