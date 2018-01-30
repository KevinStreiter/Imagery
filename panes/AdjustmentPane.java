package panes;

import help.HelpBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AdjustmentPane extends StackPane {
    private Slider slider = new Slider(1, 10, 1);
    private ColorPicker colorPicker = new ColorPicker(Color.web("#000000"));

    Slider getSlider() {
        return slider;
    }

    ColorPicker getColorPicker() {
        return colorPicker;
    }

    AdjustmentPane() {
        Button help = new Button("Help");
        Label sliderLabel = new Label("Set Line Thickness");
        Label colorLabel = new Label("Pick a Color");

        HBox hBoxHelp = new HBox();
        HBox hBoxSlider = new HBox();
        HBox hBoxSliderLabel = new HBox();
        HBox hBoxColorPicker = new HBox();
        HBox hBoxColorLabel = new HBox();

        VBox vBoxAdjustments = new VBox();

        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMinorTickCount(1);
        slider.setMajorTickUnit(3);
        slider.setSnapToTicks(true);
        colorPicker.setStyle("-fx-color-label-visible: false ;");
        colorPicker.setScaleX(2);
        colorPicker.setScaleY(2);

        hBoxHelp.getChildren().add(help);
        hBoxSlider.getChildren().add(slider);

        hBoxSliderLabel.getChildren().add(sliderLabel);
        hBoxColorPicker.getChildren().add(colorPicker);
        hBoxColorLabel.getChildren().add(colorLabel);

        hBoxHelp.setAlignment(Pos.TOP_CENTER);
        hBoxSlider.setAlignment(Pos.CENTER);
        hBoxSliderLabel.setAlignment(Pos.CENTER);
        hBoxColorPicker.setAlignment(Pos.CENTER);
        hBoxColorLabel.setAlignment(Pos.CENTER);

        hBoxColorPicker.setPadding(new Insets(50, 0, 25, 0));
        hBoxHelp.setPadding(new Insets(0, 0, 50, 0));

        vBoxAdjustments.getChildren().addAll(hBoxHelp, hBoxSlider, hBoxSliderLabel, hBoxColorPicker, hBoxColorLabel);
        vBoxAdjustments.setAlignment(Pos.CENTER);
        vBoxAdjustments.setPadding(new Insets(0, 10, 0, 10));

        help.setOnAction((ActionEvent) -> {
            HelpBox.display();
        });
        getChildren().add(vBoxAdjustments);
    }
}