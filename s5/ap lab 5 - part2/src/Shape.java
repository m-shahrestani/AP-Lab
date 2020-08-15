/**
 * An abstract class to hold abstract of shape.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public abstract class Shape {

    /**
     * calculate perimeter of shape.
     * @return perimeter
     */
    public abstract double calculatePerimeter();

    /**
     * calculate area of shape.
     * @return area
     */
    public abstract double calculateArea();

    /**
     * Print the Shape and Perimeter and Area to the output terminal.
     */
    public void draw() {
        System.out.println("Shape is " + this.getClass().getName());
        System.out.println("Perimeter: " + calculatePerimeter() + " Area: " + calculateArea());
    }

    /**
     * Determine whether the given shape equals the shape.
     * @param obj The polygon to be checked.
     * @return true if the given shape is valid, false otherwise.
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * get a String for Shape and sides or radius.
     * @return a String.
     */
    @Override
    public abstract String toString();

}
