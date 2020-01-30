package ru.unn.agile.numberstowords.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.unn.agile.numberstowords.infrastructure.TxtLogger;
import ru.unn.agile.numberstowords.viewmodel.ViewModel;

public class NumbersToWords {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtText;
    @FXML
    private Label labelErrorMsg;
    @FXML
    private Button btnConvert;

    @FXML
    void initialize() {
        viewModel.setLogger(new TxtLogger("./TxtLogger.log"));

        txtNumber.textProperty().bindBidirectional(viewModel.numberInputProperty());
        txtText.textProperty().bindBidirectional(viewModel.textOutputProperty());
        labelErrorMsg.textProperty().bindBidirectional(viewModel.statusProperty());

        txtText.setEditable(false);
        txtText.setDisable(false);

        btnConvert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.convert();
            }
        });
    }
}
