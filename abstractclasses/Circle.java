/**
 * Circle class represents a geometric circle with a certain radius.
 * This class extends the GeometricObject class and implements methods
 * to calculate the area and perimeter of the circle.
 *
 * @author Samriddhi Matharu
 */
public class Circle extends GeometricObject {
    /**
     * The radius of the circle.
     */
    private double radius;

    /**
     * Constructs a Circle object with the specified radius.
     *
     * @param radius The radius of the circle. Must be greater than 0.
     * @throws RuntimeException if the given radius is not greater than 0.
     */
    public Circle(double radius) {
        // Check if the radius is valid
        if (radius <= 0) {
            throw new RuntimeException("Invalid radius for Circle. Radius must be greater than 0.");
        }

        // Initialize the radius
        this.radius = radius;
    }

    /**
     * Calculates and returns the area of the circle.
     *
     * @return The area of the circle.
     */
    @Override
    public double getArea() {
       
        return Math.PI * Math.pow(radius, 2);
    }

    /**
     * Calculates and returns the perimeter (circumference) of the circle.
     *
     * @return The perimeter of the circle.
     */
    @Override
    public double getPerimeter() {
       
        return 2 * Math.PI * radius;
    }
}