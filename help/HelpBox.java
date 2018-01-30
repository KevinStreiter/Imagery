package help;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelpBox {
    public static void display() {
        Stage window = new Stage();
        final int WINDOW_SIZE = 450;
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Help");
        window.setMinWidth(WINDOW_SIZE);
        window.setMinHeight(WINDOW_SIZE);
        Label text = new Label();
        text.setText("FUNCTIONS OF IMAGERY\n" +
                "-------------------------------------------------------------------------\n" +
                "\n" +
                "LOAD:\n" +
                "Press the Load button und choose either a .txt or a .xml file\n" +
                "\n" +
                "DRAW:\n" +
                "\t1.\tPress the Draw button\n" +
                "\t2.\tSet a new point by click once\n" +
                "\t3.\tEnd drawing by double click\n" +
                "\n" +
                "CLEAR:\n" +
                "Press the Clear button to erase previous lines\n" +
                "\n" +
                "CALCULATE:\n" +
                "Press the Calculate button to calculate length of the line\n" +
                "\n" +
                "ANGLE:\n" +
                "Draw two lines, end second line by double click\n" +
                "\n" +
                "THICKNESS:\n" +
                "Choose the thickness of the line by using the slider\n" +
                "\n" +
                "COLOR:\n" +
                "Choose the color by clicking on the color icon\n");

        Button close = new Button("close");
        close.setOnAction(e -> window.close());

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        HBox hBoxText = new HBox();
        HBox hBoxButton = new HBox();

        hBoxText.getChildren().add(text);
        hBoxButton.getChildren().add(close);

        hBoxText.setAlignment(Pos.CENTER);
        hBoxButton.setAlignment(Pos.CENTER);

        hBoxText.setPadding(new Insets(10, 10, 10, 10));
        hBoxButton.setPadding(new Insets(0, 0, 10, 0));

        vBox.getChildren().addAll(hBoxText, hBoxButton);

        StackPane stackPane = new StackPane();

        stackPane.getChildren().add(vBox);
        stackPane.setStyle("-fx-background-color: rgba(116,117,128,0.33);");

        Scene scene = new Scene(stackPane);

        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
    }
}