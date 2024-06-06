/**
 * Rectangle class represents a geometric rectangle with certain width and height.
 * This class extends the GeometricObject class and implements methods
 * to calculate the area and perimeter of the rectangle.
 *
 * @author Samriddhi Matharu
 */
public class Rectangle extends GeometricObject {
    /**
     * The width of the rectangle.
     */
    private double width;

    /**
     * The height of the rectangle.
     */
    private double height;

    /**
     * Constructs a Rectangle object with the specified width and height.
     *
     * @param width  The width of the rectangle. Must be greater than 0.
     * @param height The height of the rectangle. Must be greater than 0.
     * @throws RuntimeException if the given width or height is not greater than 0.
     */
    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new RuntimeException("Invalid dimensions for Rectangle");
        }

        this.width = width;
        this.height = height;
    }

    /**
     * Calculates and returns the area of the rectangle.
     *
     * @return The area of the rectangle.
     */
    @Override
    public double getArea() {
        return width * height;
    }

    /**
     * Calculates and returns the perimeter of the rectangle.
     *
     * @return The perimeter of the rectangle.
     */
    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }
}