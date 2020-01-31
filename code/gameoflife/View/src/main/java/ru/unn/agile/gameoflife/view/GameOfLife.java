package ru.unn.agile.gameoflife.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.unn.agile.gameoflife.viewmodel.ViewModel;

public class GameOfLife {
    private static final float GAME_FIELD_SIZE = 400;
    private float cellWidth;
    private float cellHeight;

    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtHeight;
    @FXML
    private TextField txtwidth;
    @FXML
    private Label txtStatus;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnNext;
    @FXML
    private GridPane gridGame;

    private EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(final MouseEvent e) {
            Rectangle clickedNode = (Rectangle) e.getPickResult().getIntersectedNode();
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            viewModel.changeCellStatus(colIndex, rowIndex);
            if (clickedNode.getFill().equals(Color.WHITE)) {
                gridGame.add(new Rectangle(cellWidth, cellHeight, Color.BLACK), colIndex, rowIndex);
            } else {
                gridGame.add(new Rectangle(cellWidth, cellHeight, Color.WHITE), colIndex, rowIndex);
            }
        }
    };

    private void gridGameUpdate() {
        char[][] gridArray = viewModel.gridArrayProperty().clone();

        int width = Integer.parseInt(viewModel.widthFieldProperty().get());
        int height = Integer.parseInt(viewModel.heightFieldProperty().get());
        cellWidth = GAME_FIELD_SIZE / width;
        cellHeight = GAME_FIELD_SIZE / width;

        gridGame.getChildren().clear();
        gridGame.setOnMouseClicked(mouseHandler);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (gridArray[y][x] == '*') {
                    gridGame.add(new Rectangle(cellWidth, cellHeight, Color.BLACK), y, x);
                } else {
                    gridGame.add(new Rectangle(cellWidth, cellHeight, Color.WHITE), y, x);
                }
            }
        }
    }

    @FXML
    void initialize() {
        txtHeight.textProperty().bindBidirectional(viewModel.heightFieldProperty());
        txtwidth.textProperty().bindBidirectional(viewModel.widthFieldProperty());
        txtStatus.textProperty().bindBidirectional(viewModel.statusTextProperty());
        btnCreate.disableProperty().bindBidirectional(viewModel.couldNotCreateProperty());
        btnNext.disableProperty().bindBidirectional(viewModel.couldNotGetNextStepProperty());

        btnCreate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.createGrid();
                gridGameUpdate();
            }
        });

        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getNextStep();
                gridGameUpdate();
            }
        });
    }
}
