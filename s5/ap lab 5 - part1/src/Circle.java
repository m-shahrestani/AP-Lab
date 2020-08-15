/**
 * A class to hold Circle.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Circle {
    //radius
    private double radius;

    /**
     * Create a new Circle with a given radius.
     *
     * @param radius The radius of circle.
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * get The radius.
     * @return radius field.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * calculate perimeter of shape.
     * @return perimeter
     */
    public double calculatePerimeter() {
        return (2 * Math.PI * radius);
    }

    /**
     * calculate area of shape.
     * @return area
     */
    public double calculateArea() {
        return (Math.PI * (radius * radius));
    }

    /**
     * Print the Shape and Perimeter and Area to the output terminal.
     */
    public void draw() {
        System.out.print("Shape is Circle... ");
        System.out.println("Perimeter: " + calculatePerimeter() + " Area: " + calculateArea());
    }

    /**
     * Determine whether the given circle equals the circle.
     * @param o The circle to be checked.
     * @return true if the given circle is valid, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle)) return false;

        Circle circle = (Circle) o;

        return Double.compare(circle.getRadius(), getRadius()) == 0;
    }

    /**
     * Make a hashcode depends on radius.
     * @return code
     */
    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(getRadius());
        return (int) (temp ^ (temp >>> 32));
    }

    /**
     * get a String for circle and radius.
     * @return a String.
     */
    @Override
    public String toString() {
        return "Shape is Circle\n" +
                "radius is : " + radius;
    }
}
