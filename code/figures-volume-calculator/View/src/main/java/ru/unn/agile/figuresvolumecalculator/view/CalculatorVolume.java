package ru.unn.agile.figuresvolumecalculator.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.unn.agile.figuresvolumecalculator.model.FiguresVolumeCalculator.*;
import ru.unn.agile.figuresvolumecalculator.viewmodel.ViewModel;

public class CalculatorVolume {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField length1;
    @FXML
    private TextField length2;

    @FXML
    private Label length1Lab;
    @FXML
    private Label length2Lab;

    @FXML
    private ComboBox<TypeOfFunction> cbTypeOfGeomBody;
    @FXML
    private Button btnCalc;

    @FXML
    void initialize() {
        length1.textProperty().bindBidirectional(viewModel.squareProperty());
        length1.textProperty().bindBidirectional(viewModel.heightProperty());

        cbTypeOfGeomBody.valueProperty().bindBidirectional(viewModel.operationProperty());

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
