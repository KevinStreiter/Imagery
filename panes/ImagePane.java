package panes;

import main.Main;
import calculation.ImageRatio;
import calculation.PolylineCalculation;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.stage.FileChooser;

import java.io.File;

public class ImagePane extends Pane {
    private ImageView imageView = new ImageView();
    private Polyline polyline1 = new Polyline();
    private Polyline polyline2 = new Polyline();
    private ResultPane resultPane = new ResultPane();
    private ButtonPane buttonPane = new ButtonPane();
    private AdjustmentPane adjustmentPane = new AdjustmentPane();
    private ImageRatio imageRatio;
    private PolylineCalculation polylineCalculation;
    private boolean draw = false;
    private boolean clicked = false;
    private boolean imageLoaded = false;
    private boolean doubleClicked = false;
    private boolean calculated = false;

    public ResultPane getResultPane() {
        return resultPane;
    }

    public ButtonPane getButtonPane() {
        return buttonPane;
    }

    public AdjustmentPane getAdjustmentPane() {
        return adjustmentPane;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public ImagePane() throws Exception {
        Pane pane = new Pane();
        double IMAGEVIEW_SIZE = 450;
        imageView.prefHeight(IMAGEVIEW_SIZE);
        imageView.prefWidth(IMAGEVIEW_SIZE);
        imageView.setPreserveRatio(true);

        polyline1.strokeWidthProperty().bind(adjustmentPane.getSlider().valueProperty());
        polyline2.strokeWidthProperty().bind(adjustmentPane.getSlider().valueProperty());

        adjustmentPane.getColorPicker().setOnAction((ActionEvent) -> {
            polyline1.setStroke(adjustmentPane.getColorPicker().getValue());
            polyline2.setStroke(adjustmentPane.getColorPicker().getValue());
        });

        pane.getChildren().addAll(imageView, polyline1, polyline2);
        getChildren().add(pane);

        buttonPane.getImageLoad().setOnAction((ActionEvent event) -> {
            try {
                FileChooser fileChooser = new FileChooser();
                File selectedFile = fileChooser.showOpenDialog(Main.getMainStage());
                deletePolyline(polyline1, polyline2, resultPane.getResult());
                resultPane.setImageText(selectedFile);
                Image image = new Image("file:" + selectedFile.getParent() + File.separator +
                        resultPane.getFileMetadata()[1]);
                imageView.setImage(image);
                double trueImageWidth = imageView.getImage().getWidth();
                double trueImageHeight = imageView.getImage().getHeight();
                String resolutionValue = resultPane.getFileMetadata()[2];
                String resolutionUnit = resultPane.getFileMetadata()[3];
                if (resultPane.getFileMetadata()[0] != null && resultPane.getFileMetadata()[1] != null) {
                    if (resolutionUnit != null && resolutionValue != null) {
                        resultPane.getImageMeasurements().setText((Math.round(trueImageWidth *
                                Double.parseDouble(resolutionValue))) + " x " + (Math.round(trueImageHeight *
                                Double.parseDouble(resolutionValue))) + " " + resolutionUnit);
                    } else {
                        resultPane.getImageMeasurements().setText(trueImageWidth + " x " + trueImageHeight + " Pixels");
                    }
                }
                imageRatio = new ImageRatio(imageView.boundsInParentProperty().getValue(), trueImageWidth,
                        trueImageHeight);
                pane.setMaxSize(imageRatio.getFittedImageSize().getWidth(),
                        imageRatio.getFittedImageSize().getHeight());
                imageLoaded = true;
                draw = false;
            } catch (Exception e) {
                resultPane.getImageDescription().setText("Try again");
            }
        });

        buttonPane.getDrawLine().setOnAction((ActionEvent event) -> {
            draw = true;
            doubleClicked = false;
            if (calculated) {
                deletePolyline(polyline1, polyline2, resultPane.getResult());
                calculated = false;
            }

            pane.setOnMouseClicked(eventClicked -> {
                if (imageLoaded && !calculated && draw) {
                    clicked = true;
                    double mouseX = eventClicked.getX();
                    double mouseY = eventClicked.getY();
                    polylineCalculation = new PolylineCalculation(mouseX, mouseY, imageRatio.calculateImageRatio());
                    polylineCalculation.addCoordinates();
                    polyline1.getPoints().addAll(mouseX, mouseY);
                    if (2 == eventClicked.getClickCount()) {
                        polyline2.getPoints().clear();
                        polylineCalculation.removeLastCoordinate();
                        doubleClicked = true;
                        draw = false;
                    }
                }
            });

            pane.setOnMouseMoved(eventMoved -> {
                if (clicked && !doubleClicked && draw) {
                    int index = polylineCalculation.getAllCoordinates().size() - 1;
                    polyline2.getPoints().clear();
                    polyline2.getPoints().addAll(polylineCalculation.getAllCoordinates().get(index).getX(),
                            polylineCalculation.getAllCoordinates().get(index).getY(),
                            eventMoved.getX(), eventMoved.getY());
                }
            });
        });

        buttonPane.getClear().setOnAction((ActionEvent) -> {
            deletePolyline(polyline1, polyline2, resultPane.getResult());
            clicked = false;
            doubleClicked = false;
            calculated = false;
            draw = false;
        });

        buttonPane.getCalculate().setOnAction((ActionEvent) -> {
            if (imageLoaded && clicked) {
                double pixelValue = polylineCalculation.calculateLengthPixels();
                String resolutionValue = resultPane.getFileMetadata()[2];
                String resolutionUnit = resultPane.getFileMetadata()[3];
                if (resolutionUnit != null && resolutionValue != null) {
                    double calculatedValue = pixelValue * Double.parseDouble(resolutionValue);
                    resultPane.getResult().setText(String.valueOf(
                            Math.round(calculatedValue * 10.0) / 10.0) + " " + resolutionUnit);
                } else {
                    resultPane.getResult().setText(String.valueOf(Math.round(pixelValue * 10.0) / 10.0) + " Pixels");
                }
                calculated = true;
            }
        });

        buttonPane.getAngle().setOnAction((ActionEvent) -> {
            if (imageLoaded && clicked) {
                if (3 == polylineCalculation.getAllCoordinates().size()) {
                    double calculatedAngle = polylineCalculation.calculateAngle();
                    resultPane.getResult().setText(String.valueOf(Math.round(calculatedAngle * 10.0) / 10.0) + " °");
                } else {
                    resultPane.getResult().setText("Only 2 Lines supported");
                }
                calculated = true;
            }
        });
    }

    private void deletePolyline(Polyline polylineOne, Polyline polylineTwo, Label result) {
        if (polylineCalculation != null) {
            polylineOne.getPoints().clear();
            polylineTwo.getPoints().clear();
            polylineCalculation.removeCoordinates();
            result.setText("");
            clicked = false;
            doubleClicked = false;
        }
    }
}