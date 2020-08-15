import java.util.ArrayList;

/**
 * A class to hold Shapes.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Paint {
    // An ArrayList for storing the shapes.
    private ArrayList<Shape> shapes;

    /**
     * Create a Paint.
     */
    public Paint() {
        shapes = new ArrayList<>();
    }

    /**
     * Add a shape to the shapes collection.
     * @param shape The shape to be added.
     */
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    /**
     * Print Shapes and Perimeter and Area to the output terminal.
     */
    public void drawAll() {
        for(Shape temp: shapes) {
            temp.draw();
        }
    }

    /**
     * Print Shapes and sides or radius to the output terminal.
     */
    public void printAll() {
        for(Shape temp: shapes) {
            System.out.println(temp.toString());
        }
    }

    /**
     * Determine whether the given shape has equalSides.
     */
    public void describeEqualSides() {
        for(Shape shape : shapes) {
            if (shape instanceof Triangle)
                if (((Triangle) shape).isEquilateral())
                    System.out.println(shape + "****yes it is equilateral");
            if(shape instanceof Rectangle)
                if(((Rectangle) shape).isSquare())
                    System.out.println(shape + "****yes it is Square");
        }
    }
}
