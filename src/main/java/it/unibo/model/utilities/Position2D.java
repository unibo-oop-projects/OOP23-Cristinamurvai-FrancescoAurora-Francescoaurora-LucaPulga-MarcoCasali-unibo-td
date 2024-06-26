package it.unibo.model.utilities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.model.entities.defense.tower.Tower;

/**
 * 2D position class.
 */
public class Position2D {

    private final double x;
    private final double y;

    /**
     * Position's coordinates.
     *
     * @param x
     * @param y
     */
    @JsonCreator
    public Position2D(@JsonProperty("x") final double x,
            @JsonProperty("y") final double y) {
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
     * Y coordinate.
     *
     * @return Y coordinate.
     */
    public double y() {
        return y;
    }

    /**
     * Index to {@link Position2D}.
     * @return Index converted.
     */    
    public static Position2D IntToPos2D(final double i, final double columns) {
        return new Position2D(i % columns, i / columns);
    }

    /**
     * {@link Position2D} to index.
     * @return Position2d converted.
     */    
    public static int Pos2DtoInt(final Position2D pos, final double columns) {
        return (int)(pos.x() + pos.y() * columns);
    }

    public Position2D add(Vector2D vec) {
        return new Position2D(this.x + vec.x(), this.y + vec.y());
    }

    public Vector2D subtract(Position2D other) {
        return new Vector2D(this.x - other.x(), this.y - other.y());
    }

    public double distanceTo(Position2D other) {
        return Math.sqrt(Math.pow(this.x - other.x(), 2) + Math.pow(this.y - other.y(), 2));
    }
}
