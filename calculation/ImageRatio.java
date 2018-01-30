package calculation;

import javafx.geometry.Bounds;

public class ImageRatio {
    private Bounds fittedImageSize;
    private double trueImageWidth;
    private double trueImageHeight;

    public ImageRatio(Bounds fittedImageSize, double trueImageWidth, double trueImageHeight) {
        this.fittedImageSize = fittedImageSize;
        this.trueImageWidth = trueImageWidth;
        this.trueImageHeight = trueImageHeight;
    }

    public Bounds getFittedImageSize() {
        return fittedImageSize;
    }

    public double[] calculateImageRatio() {
        double[] ratio = new double[2];
        ratio[0] = trueImageWidth / fittedImageSize.getWidth();
        ratio[1] = trueImageHeight / fittedImageSize.getHeight();
        return ratio;
    }
}