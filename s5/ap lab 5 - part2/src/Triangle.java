/**
 * A class to hold Triangle.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Triangle extends Polygon{

    /**
     * Create a new Triangle with a given sides.
     *
     * @param sides The Third side of triangle.
     */
    public Triangle(double... sides) {
        super(sides);
    }

    /**
     * Determine equilateral condition of triangle.
     * @return true if the triangle is equilateral, false otherwise.
     */
    public boolean isEquilateral() {
        return sides.get(0).equals(sides.get(1)) && sides.get(1).equals(sides.get(2));
    }
}
