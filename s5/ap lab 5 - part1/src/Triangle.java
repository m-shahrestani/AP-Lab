import java.util.ArrayList;
import static java.lang.Math.sqrt;

/**
 * A class to hold Triangle.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Triangle {
    // An ArrayList for storing the sides.
    private ArrayList<Double> sides;

    /**
     * Create a new Triangle with a given sides.
     *
     * @param a The first side of triangle.
     * @param b The second side of triangle.
     * @param c The Third side of triangle.
     */
    public Triangle(double a, double b, double c) {
        sides = new ArrayList<>();
        sides.add(a);
        sides.add(b);
        sides.add(c);
    }

    /**
     * get The sides collection.
     * @return sides field.
     */
    public ArrayList<Double> getSides() {
        return sides;
    }

    /**
     * Determine equilateral condition of triangle.
     * @return true if the triangle is equilateral, false otherwise.
     */
    public boolean isEquilateral() {
        if(sides.get(0).equals(sides.get(1)) && sides.get(1).equals(sides.get(2))) {
            return true;
        }
        return false;
    }

    /**
     * calculate perimeter of shape.
     * @return perimeter
     */
    public double calculatePerimeter() {
        return  (sides.get(0) + sides.get(1) + sides.get(2));
    }

    /**
     * calculate area of shape.
     * @return area
     */
    public double calculateArea() {
        double p = calculatePerimeter() / 2;
        return (sqrt(p*(p - sides.get(0))*(p - sides.get(1))*(p - sides.get(2))));
    }

    /**
     * Print the Shape and Perimeter and Area to the output terminal.
     */
    public void draw() {
        System.out.print("Shape is Triangle... ");
        System.out.println("Perimeter: " + calculatePerimeter() + " Area: " + calculateArea());
    }

    /**
     * Determine whether the given triangle equals the triangle.
     * @param o The triangle to be checked.
     * @return true if the given triangle is valid, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;

        Triangle triangle = (Triangle) o;

        return getSides() != null ? getSides().equals(triangle.getSides()) : triangle.getSides() == null;
    }

    /**
     * Make a hashcode depends on sides.
     * @return code
     */
    @Override
    public int hashCode() {
        return getSides() != null ? getSides().hashCode() : 0;
    }

    /**
     * get a String for triangle and sides.
     * @return a String.
     */
    @Override
    public String toString() {
        return "Shape is Triangle\n" +
                "sides : " + sides;
    }
}
