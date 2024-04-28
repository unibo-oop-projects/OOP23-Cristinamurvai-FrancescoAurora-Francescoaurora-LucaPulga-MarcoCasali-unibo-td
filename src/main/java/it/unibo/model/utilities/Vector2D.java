package it.unibo.model.utilities;

/**
 * 2D Vector class.
 */
public class Vector2D {
    private double x;
    private double y;

    /**
     * Vector's coordinates.
     * @param x
     * @param y
     */
    public Vector2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * X coordinate.
     * @return X coordinate.
     */
    public double x() {
        return x;
    }

    /**
     * Y coordinate.
     * @return Y coordinate.
     */
    public double y() {
        return y;
    }

    /**
     * Vector length.
     * @return Vector length.
     */
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Vector normalization.
     * @return 2D Vector normalized.
     */
    public Vector2D normalize() {
        double length = length();
        return new Vector2D(x / length, y / length);
    }
    /**
     * Scalar-vector multiplication.
     * @param scalar
     * @return new multiplied vector.
     */
    public Vector2D multiply(final double scalar) {
        return new Vector2D(x * scalar, y * scalar);
    }
}
