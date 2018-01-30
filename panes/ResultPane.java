package panes;

import metadata.SearchMetadata;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ResultPane extends StackPane {
    private Label result = new Label("");
    private Label imageDescription = new Label("");
    private Label imageName = new Label("");
    private Label imageResolution = new Label("");
    private Label imageMeasurements = new Label("");
    private String[] fileMetadata;

    String[] getFileMetadata() {
        return fileMetadata;
    }

    Label getResult() {
        return result;
    }

    Label getImageDescription() {
        return imageDescription;
    }

    Label getImageMeasurements() {
        return imageMeasurements;
    }

    ResultPane() {
        Label labelResult = new Label("Result:");
        Label labelImageDescription = new Label("Description:");
        Label labelImageResolution = new Label("Resolution:");
        Label labelImageName = new Label("Image Name:");
        Label labelImageMeasurements = new Label("Measurements:");

        HBox hBoxResult = new HBox();
        HBox hBoxImageDescription = new HBox();
        HBox hBoxImageName = new HBox();
        HBox hBoxImageResolution = new HBox();
        HBox hBoxImageMeasurements = new HBox();
        HBox hBoxLabelResult = new HBox();
        HBox hBoxLabelImageDescription = new HBox();
        HBox hBoxLabelImageName = new HBox();
        HBox hBoxLabelImageResolution = new HBox();
        HBox hBoxLabelImageMeasurements = new HBox();
        HBox hBoxResults = new HBox();

        VBox vBoxResults = new VBox();
        VBox vBoxLabelResults = new VBox();

        hBoxResult.getChildren().add(result);
        hBoxImageDescription.getChildren().add(imageDescription);
        hBoxImageName.getChildren().add(imageName);
        hBoxImageResolution.getChildren().add(imageResolution);
        hBoxLabelResult.getChildren().add(labelResult);
        hBoxImageMeasurements.getChildren().add(imageMeasurements);
        hBoxLabelImageDescription.getChildren().add(labelImageDescription);
        hBoxLabelImageName.getChildren().add(labelImageName);
        hBoxLabelImageResolution.getChildren().add(labelImageResolution);
        hBoxLabelImageMeasurements.getChildren().add(labelImageMeasurements);
        hBoxResults.getChildren().addAll(vBoxLabelResults, vBoxResults);

        hBoxResult.setAlignment(Pos.CENTER_LEFT);
        hBoxImageDescription.setAlignment(Pos.CENTER_LEFT);
        hBoxImageName.setAlignment(Pos.CENTER_LEFT);
        hBoxImageResolution.setAlignment(Pos.CENTER_LEFT);
        hBoxLabelResult.setAlignment(Pos.CENTER_LEFT);
        hBoxLabelImageDescription.setAlignment(Pos.CENTER_LEFT);
        hBoxLabelImageName.setAlignment(Pos.CENTER_LEFT);
        hBoxLabelImageResolution.setAlignment(Pos.CENTER_LEFT);
        hBoxLabelImageMeasurements.setAlignment(Pos.CENTER_LEFT);
        hBoxResults.setAlignment(Pos.CENTER);

        hBoxResults.setPadding(new Insets(10, 0, 10, 0));

        vBoxResults.getChildren().addAll(hBoxResult, hBoxImageDescription, hBoxImageName, hBoxImageResolution,
                hBoxImageMeasurements);
        vBoxLabelResults.getChildren().addAll(hBoxLabelResult, hBoxLabelImageDescription, hBoxLabelImageName,
                hBoxLabelImageResolution, hBoxLabelImageMeasurements);

        vBoxLabelResults.setPadding(new Insets(0, 10, 0, 0));

        getChildren().add(hBoxResults);
    }

    void setImageText(File selectedFile) throws IOException, SAXException, ParserConfigurationException {
        boolean empty = false;
        SearchMetadata searchMetadata = new SearchMetadata(selectedFile);
        fileMetadata = searchMetadata.getMetadata();
        for (int i = 0; i < (fileMetadata.length - 2); i++) {
            if (fileMetadata[i] == null) {
                empty = true;
            }
        }
        if (!empty) {
            imageDescription.setText(fileMetadata[0]);
            imageName.setText(fileMetadata[1]);
            if (fileMetadata[2] != null && fileMetadata[3] != null) {
                imageResolution.setText(fileMetadata[2] + fileMetadata[3]);
            }
        } else {
            imageDescription.setText("Missing Metadata, please try again (.txt, .xml)");
            imageName.setText("");
            imageResolution.setText("");
            imageMeasurements.setText("");
        }
    }
}