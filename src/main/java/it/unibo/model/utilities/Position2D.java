package it.unibo.model.utilities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 2D position class.
 */
public class Position2D {

    private final double x;
    private final double y;

    /**
     * Position's coordinates.
     *
     * @param x coordinate.
     * @param y coordinate.
     */
    @JsonCreator
    public Position2D(@JsonProperty("x") final double x, @JsonProperty("y") final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * X coordinate.
     *
     * @return X coordinate.
     */
    public double x() {
        return x;
    }

    /**
     * X coordinate int.
     *
     * @return X coordinate.
     */
    public int xInt() {
        return (int) x;
    }

    /**
     * Y coordinate.
     *
     * @return Y coordinate.
     */
    public double y() {
        return y;
    }

    /**
     * Y coordinate int.
     *
     * @return Y coordinate.
     */
    public int yInt() {
        return (int) y;
    }

    /**
     * Index to {@link Position2D}.
     *
     * @param i index
     * @param columns
     * @return Index converted.
     */
    public static Position2D intToPos2D(final double i, final double columns) {
        return new Position2D(i % columns, i / columns);
    }

    /**
     * {@link Position2D} to index.
     *
     * @param pos position
     * @param columns
     * @return Position2d converted.
     */
    public static int pos2DtoInt(final Position2D pos, final double columns) {
        return (int) (pos.x() + pos.y() * columns);
    }

    /**
     * Adds a vector to this position.
     *
     * @param vec Vector to add.
     * @return New Position2D after addition.
     */
    public Position2D add(final Vector2D vec) {
        return new Position2D(this.x + vec.x(), this.y + vec.y());
    }

    /**
     * Subtracts another position from this position.
     *
     * @param other Position to subtract.
     * @return New Vector2D representing the difference.
     */
    public Vector2D subtract(final Position2D other) {
        return new Vector2D(this.x - other.x(), this.y - other.y());
    }

    /**
     * Calculates the distance to another position.
     *
     * @param other Position to which distance is calculated.
     * @return Distance to the other position.
     */
    public double distanceTo(final Position2D other) {
        return Math.sqrt(Math.pow(this.x - other.x(), 2) + Math.pow(this.y - other.y(), 2));
    }

    /**
     * Calculates the distance between two positions.
     *
     * @param initialPosition2d Starting position.
     * @param endingPosition2d Ending position.
     * @return Distance between the two positions.
     */
    public static double calculateDistance(final Position2D initialPosition2d, final Position2D endingPosition2d) {
        double deltaX = initialPosition2d.x() - endingPosition2d.x();
        double deltaY = initialPosition2d.y() - endingPosition2d.y();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
