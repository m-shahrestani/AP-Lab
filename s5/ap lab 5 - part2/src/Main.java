/**
 * A class to drive program.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
     //   write your code here
        Shape tri = new Triangle(3,4,5);
        Shape tri2 = new Triangle(3,3,4);
        Triangle tri3 = new Triangle(3,3,3);
        Triangle tri33 = new Triangle(3,3,3);
        System.err.println("Are they equal?: " + tri33.equals(tri3));
        Circle circ = new Circle(4);
        Triangle tri4 = new Triangle(5,5,5);
        Rectangle rect = new Rectangle(5,6);
        Rectangle rect3 = new Rectangle(6,6);
        System.err.println("Are they equal?: " + rect.equals(rect3));
        Paint paint = new Paint();
        paint.addShape(tri);
        paint.addShape(tri2);
        paint.addShape(tri3);
        paint.addShape(circ);
        paint.addShape(rect);
        paint.addShape(rect3);
        paint.addShape(tri4);
        rect = rect3;
        paint.addShape(rect);
        System.out.println("draw all---------------------------------");
        paint.drawAll();
        System.out.println("print all---------------------------------");
        paint.printAll();
        System.out.println("EqualSides all---------------------------------");
        paint.describeEqualSides();
    }
}