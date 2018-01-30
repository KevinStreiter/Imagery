package calculation;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class PolylineCalculation {
    private static List<Coordinates> allCoordinates = new LinkedList<>();
    private Double xCoordinate;
    private Double yCoordinate;
    private double[] imageRatio;

    public PolylineCalculation(Double xCoordinate, Double yCoordinate, double[] imageRatio) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.imageRatio = imageRatio;
    }

    public List<Coordinates> getAllCoordinates() {
        return allCoordinates;
    }

    public void addCoordinates() {
        Coordinates coordinates = new Coordinates(xCoordinate, yCoordinate);
        allCoordinates.add(coordinates);
    }

    public void removeCoordinates() {
        allCoordinates.clear();
    }

    public void removeLastCoordinate() {
        allCoordinates.remove(allCoordinates.size() - 1);
    }

    public double calculateLengthPixels() {
        double lengthX;
        double lengthY;
        double lengthTotal = 0.0;
        for (int i = 0; i < (allCoordinates.size() - 1); i++) {
            lengthX = (Math.abs(allCoordinates.get(i).getX() - allCoordinates.get(i + 1).getX())) * imageRatio[0];
            lengthY = (Math.abs(allCoordinates.get(i).getY() - allCoordinates.get(i + 1).getY())) * imageRatio[1];
            lengthTotal += Math.sqrt((lengthX * lengthX) + (lengthY * lengthY));
        }
        return lengthTotal;
    }

    public double calculateAngle() {
        double x;
        double y;
        double alpha;
        Point2D.Double p1 = new Point2D.Double();
        Point2D.Double p2 = new Point2D.Double();
        Point2D.Double p3 = new Point2D.Double();
        for (int i = 0; i < allCoordinates.size(); i++) {
            x = allCoordinates.get(i).getX();
            y = allCoordinates.get(i).getY();
            if (i == 0) {
                p1 = new Point2D.Double(x, y);
            } else if (i == 1) {
                p2 = new Point2D.Double(x, y);
            } else if (i == 2) {
                p3 = new Point2D.Double(x, y);
            }
        }
        Point2D.Double vek1 = new Point2D.Double(p1.getX() - p2.getX(), p1.getY() - p2.getY());
        Point2D.Double vek2 = new Point2D.Double(p3.getX() - p2.getX(), p3.getY() - p2.getY());

        double length1 = Math.abs(Math.sqrt(vek1.getX() * vek1.getX() + vek1.getY() * vek1.getY()));
        double length2 = Math.abs(Math.sqrt(vek2.getX() * vek2.getX() + vek2.getY() * vek2.getY()));

        alpha = Math.toDegrees(
                Math.acos((vek1.getX() * vek2.getX() + vek1.getY() * vek2.getY()) / (length1 * length2)));
        return alpha;
    }
}