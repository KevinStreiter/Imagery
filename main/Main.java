package main;

import panes.AdjustmentPane;
import panes.ButtonPane;
import panes.ImagePane;
import panes.ResultPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage stage;

    public static Stage getMainStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ImagePane imagePane = new ImagePane();
        AdjustmentPane adjustmentPane;
        ResultPane resultPane;
        ButtonPane buttonPane;
        final double MIN_WINDOW_SIZE = 680;


        adjustmentPane = imagePane.getAdjustmentPane();
        resultPane = imagePane.getResultPane();
        buttonPane = imagePane.getButtonPane();

        BorderPane paneWhole = new BorderPane();

        paneWhole.setCenter(imagePane);
        paneWhole.setTop(buttonPane);
        paneWhole.setBottom(resultPane);
        paneWhole.setLeft(adjustmentPane);
        paneWhole.setStyle("-fx-background-color: rgba(116,117,128,0.33);");

        imagePane.getImageView().fitWidthProperty().bind(imagePane.widthProperty());
        imagePane.getImageView().fitHeightProperty().bind(imagePane.heightProperty());

        Scene scene = new Scene(paneWhole, MIN_WINDOW_SIZE, MIN_WINDOW_SIZE);
        stage.setScene(scene);

        stage.setMinWidth(MIN_WINDOW_SIZE);
        stage.setMinHeight(MIN_WINDOW_SIZE);
        stage.setWidth(MIN_WINDOW_SIZE);
        stage.setHeight(MIN_WINDOW_SIZE);
        stage.setTitle("Imagery");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}