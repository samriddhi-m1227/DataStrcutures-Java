/**
 * Triangle class represents a geometric triangle with certain side lengths.
 * This class extends the GeometricObject class and implements methods
 * to calculate the area and perimeter of the triangle.
 *
 * @author Samriddhi Matharu
 */
public class Triangle extends GeometricObject {
    /**
     * The length of side1 of the triangle.
     */
    private double side1;

    /**
     * The length of side2 of the triangle.
     */
    private double side2;

    /**
     * The length of side3 of the triangle.
     */
    private double side3;

    /**
     * Constructs a Triangle object with the specified side lengths.
     *
     * @param side1 The length of side1. Must be greater than 0.
     * @param side2 The length of side2. Must be greater than 0.
     * @param side3 The length of side3. Must be greater than 0.
     * @throws RuntimeException if any side length is not greater than 0, or if the
     *                          sum of any two sides is not greater than the third side.
     */
    public Triangle(double side1, double side2, double side3) {
        // Check for valid side lengths and the triangle inequality theorem
        if (side1 <= 0 || side2 <= 0 || side3 <= 0 ||
                side1 + side2 <= side3 || side1 + side3 <= side2 || side2 + side3 <= side1) {
            throw new RuntimeException("Invalid side lengths for Triangle");
        }

        // Assign side lengths to instance variables
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    /**
     * Calculates and returns the area of the triangle using Heron's formula.
     *
     * @return The area of the triangle.
     */
    @Override
    public double getArea() {
        // Calculate the semi-perimeter of the triangle
        double semiPer = (side1 + side2 + side3) / 2;
        // Use a formula to calculate the area
        return Math.sqrt(semiPer * (semiPer - side1) * (semiPer - side2) * (semiPer - side3));
    }

    /**
     * Calculates and returns the perimeter of the triangle.
     *
     * @return The perimeter of the triangle.
     */
    @Override
    public double getPerimeter() {
        // Sum of all three sides
        return side1 + side2 + side3;
    }
}