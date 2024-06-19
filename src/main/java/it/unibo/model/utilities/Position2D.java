package it.unibo.model.utilities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.model.entities.defense.tower.Tower;

/**
 * 2D position class.
 */
public class Position2D {
    private int x;
    private int y;

    /**
     * Position's coordinates.
     * @param x
     * @param y
     */
    @JsonCreator
    public Position2D(@JsonProperty("x") final int x, 
                      @JsonProperty("y") final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * X coordinate.
     * @return X coordinate.
     */
    public int x() {
        return x;
    }

    /**
     * Y coordinate.
     * @return Y coordinate.
     */
    public int y() {
        return y;
    }

    /**
     * Index to {@link Position2D}.
     * @return Index converted.
     */    
    public static Position2D IntToPos2D(final int i, final int columns) {
        return new Position2D(i % columns, i / columns);
    }

    /**
     * {@link Position2D} to index.
     * @return Position2d converted.
     */    
    public static int Pos2DtoInt(final Position2D pos, final int columns) {
        return pos.x() + pos.y() * columns;
    }
}
