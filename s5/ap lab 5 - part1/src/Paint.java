import java.util.ArrayList;

/**
 * A class to hold Shapes.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Paint {
    // An ArrayList for storing the circles.
    private ArrayList<Circle> circles;
    // An ArrayList for storing the triangles.
    private ArrayList<Triangle> triangles;
    // An ArrayList for storing the rectangles.
    private ArrayList<Rectangle> rectangles;

    /**
     * Create a Paint.
     */
    public Paint() {
        circles = new ArrayList<>();
        triangles = new ArrayList<>();
        rectangles = new ArrayList<>();
    }

    /**
     * Add a circle to the circles collection.
     * @param circle The circle to be added.
     */
    public void addCircle(Circle circle) {
        circles.add(circle);
    }

    /**
     * Add a rectangle to the rectangles collection.
     * @param rectangle The rectangle to be added.
     */
    public void addRectangle(Rectangle rectangle) {
        rectangles.add(rectangle);
    }

    /**
     * Add a triangle to the triangles collection.
     * @param triangle The triangle to be added.
     */
    public void addTriangle(Triangle triangle) {
        triangles.add(triangle);
    }

    /**
     * Print Shapes and Perimeter and Area to the output terminal.
     */
    public void drawAll() {
        for(Rectangle temp: rectangles) {
            temp.draw();
        }
        for(Triangle temp: triangles) {
            temp.draw();
        }
        for(Circle temp: circles) {
            temp.draw();
        }
    }

    /**
     * Print Shapes and sides or radius to the output terminal.
     */
    public void printAll() {
        for(Rectangle temp: rectangles) {
            System.out.println(temp.toString());
        }
        for(Triangle temp: triangles) {
            System.out.println(temp.toString());
        }
        for(Circle temp: circles) {
            System.out.println(temp.toString());
        }
    }
}
