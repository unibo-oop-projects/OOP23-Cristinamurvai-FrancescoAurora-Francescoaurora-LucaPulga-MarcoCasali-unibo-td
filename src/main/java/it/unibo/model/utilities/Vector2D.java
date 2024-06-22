package it.unibo.model.utilities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 2D Vector class.
 */
public class Vector2D {

    private final int x;
    private final int y;

    /**
     * Vector's coordinates.
     *
     * @param x
     * @param y
     */
    @JsonCreator
    public Vector2D(@JsonProperty("x") final int x,
            @JsonProperty("y") final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * X coordinate.
     *
     * @return X coordinate.
     */
    public int x() {
        return x;
    }

    /**
     * Y coordinate.
     *
     * @return Y coordinate.
     */
    public int y() {
        return y;
    }

    /**
     * Vector length.
     *
     * @return Vector length.
     */
    public int length() {
        return (int) Math.sqrt(x * x + y * y);
    }

    /**
     * Vector normalization.
     *
     * @return 2D Vector normalized.
     */
    public Vector2D normalize() {
        int length = length();
        return new Vector2D(x / length, y / length);
    }

    /**
     * Scalar-vector multiplication.
     *
     * @param scalar
     * @return new multiplied vector.
     */
    public Vector2D multiply(final int scalar) {
        return new Vector2D(x * scalar, y * scalar);
    }
}
