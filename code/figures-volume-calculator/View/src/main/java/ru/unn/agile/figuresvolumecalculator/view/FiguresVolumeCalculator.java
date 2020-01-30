package ru.unn.agile.figuresvolumecalculator.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.figuresvolumecalculator.model.FiguresVolumeCalculator.Operation;
import ru.unn.agile.figuresvolumecalculator.viewmodel.ViewModel;

public class FiguresVolumeCalculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtFirstParameter;
    @FXML
    private TextField txtSecondParameter;
    @FXML
    private ComboBox<Operation> comboBoxOperation;
    @FXML
    private Button buttonCalc;

    @FXML
    void initialize() {

    }
}
