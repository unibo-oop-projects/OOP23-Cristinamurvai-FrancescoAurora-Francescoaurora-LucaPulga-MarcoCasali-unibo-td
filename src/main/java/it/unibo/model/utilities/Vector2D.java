package it.unibo.model.utilities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 2D Vector class.
 */
public class Vector2D {

    private final double x;
    private final double y;

    /**
     * Vector's coordinates.
     * @param x coordinate.
     * @param y coordinate.
     */
    @JsonCreator
    public Vector2D(@JsonProperty("x") final double x, @JsonProperty("y") final double y) {
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
     * X coordinate int.
     * @return X coordinate.
     */
    public int xInt() {
        return (int) x;
    }

    /**
     * Y coordinate.
     * @return Y coordinate.
     */
    public double y() {
        return y;
    }

    /**
     * Y coordinate int.
     * @return Y coordinate.
     */
    public int yInt() {
        return (int) y;
    }

    /**
     * Scalar-vector multiplication.
     * @param scalar
     * @return new multiplied vector.
     */
    public Vector2D multiply(final double scalar) {
        return new Vector2D(x * scalar, y * scalar);
    }

    public Vector2D scale(final double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    public Vector2D normalize() {
        double length = Math.sqrt(x * x + y * y);
        return new Vector2D(this.x / length, this.y / length);
    }

    public static Vector2D calculateDirection(Position2D initiPosition2d, Position2D endingPosition2d) {
        double dx = endingPosition2d.x() - initiPosition2d.x();
        double dy = endingPosition2d.y() - initiPosition2d.y();
        return new Vector2D(dx, dy).normalize();
    }
}
