import java.util.ArrayList;
import static java.lang.Math.sqrt;

/**
 * A class to hold Polygon.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Polygon extends Shape{
    // An ArrayList for storing the sides.
    protected ArrayList<Double> sides;

    /**
     * Create a new Rectangle with a given sides.
     *
     * @param sides The sides of polygon.
     */
    public Polygon(double... sides) {
        this.sides = new ArrayList<>();
        for (double temp : sides) {
            this.sides.add(temp);
        }
    }

    /**
     * get The sides collection.
     * @return sides field.
     */
    public ArrayList<Double> getSides() {
        return sides;
    }

    /**
     * calculate perimeter of polygon.
     * @return perimeter
     */
    @Override
    public double calculatePerimeter() {
        double perimeter = 0;
        for(double temp : sides) {
            perimeter += temp;
        }
        return ((this.getClass().getName().equals("Rectangle")) ? ( 2 * perimeter) : perimeter);
    }

    /**
     * calculate area of polygon.
     * @return area
     */
    @Override
    public double calculateArea() {
        if(this.getClass().getName().equals("Triangle")) {
            double p = calculatePerimeter() / 2;
            return (sqrt(p*(p - sides.get(0))*(p - sides.get(1))*(p - sides.get(2))));
        }
        else {
            return (sides.get(0) * sides.get(1));
        }
    }

    /**
     * Determine whether the given polygon equals the polygon.
     * @param o The polygon to be checked.
     * @return true if the given polygon is valid, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polygon)) return false;

        Polygon polygon = (Polygon) o;

        return getSides() != null ? getSides().equals(polygon.getSides()) : polygon.getSides() == null;
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
     * get a String for polygon and sides.
     * @return a String.
     */
    @Override
    public String toString() {
        return "Shape is " + this.getClass().getName() + "\n sides : " + sides;
    }
}
