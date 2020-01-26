package ru.unn.agile.distancebetweenvector.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CalculateMetrics.fxml"));
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
