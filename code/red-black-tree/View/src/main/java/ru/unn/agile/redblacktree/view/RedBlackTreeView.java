package ru.unn.agile.redblacktree.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import ru.unn.agile.redblacktree.viewmodel.ViewModel;
import ru.unn.agile.redblacktree.infrastructure.TxtLogger;

public class RedBlackTreeView {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtAddField;
    @FXML
    private TextField txtFindField;
    @FXML
    private TextField txtRemoveField;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnFind;
    @FXML
    private Button btnRemove;

    @FXML
    private Label txtResultFind;
    @FXML
    private Label txtResultRemove;

    @FXML
    void initialize() {
        viewModel = new ViewModel(new TxtLogger("./red-black-tree.log"));
        txtAddField.textProperty().bindBidirectional(viewModel.addFieldProperty());
        txtFindField.textProperty().bindBidirectional(viewModel.findInsertFieldProperty());
        txtRemoveField.textProperty().bindBidirectional(viewModel.removeInsertFieldProperty());

        txtResultFind.textProperty().bindBidirectional(viewModel.resultFindProperty());
        txtResultRemove.textProperty().bindBidirectional(viewModel.resultRemoveProperty());

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.addElementToTree();
            }
        });

        btnFind.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.findElementInTree();
            }
        });

        btnRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.removeElementFromTree();
            }
        });
    }
}
