package panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ButtonPane extends StackPane {
    private Button imageLoad = new Button("Load");
    private Button drawLine = new Button("Draw");
    private Button clear = new Button("Clear");
    private Button calculate = new Button("Calculate");
    private Button angle = new Button("Angle");

    Button getImageLoad() {
        return imageLoad;
    }

    Button getDrawLine() {
        return drawLine;
    }

    Button getCalculate() {
        return calculate;
    }

    Button getAngle() {
        return angle;
    }

    Button getClear() {return clear;}

    ButtonPane(){
        HBox hBoxImageLoad = new HBox();
        HBox hBoxButtons = new HBox();

        VBox vBoxButtons = new VBox();

        hBoxImageLoad.getChildren().add(imageLoad);
        hBoxButtons.getChildren().addAll(drawLine, clear,calculate, angle);

        hBoxImageLoad.setAlignment(Pos.CENTER);
        hBoxButtons.setAlignment(Pos.CENTER);

        hBoxImageLoad.setPadding(new Insets(10,0,0,0));
        hBoxButtons.setPadding(new Insets(0,0,10,0));

        vBoxButtons.getChildren().addAll(hBoxImageLoad, hBoxButtons);

        imageLoad.setPrefSize(230, 50);

        getChildren().add(vBoxButtons);
    }
}