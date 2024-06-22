package it.unibo.model.utilities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 2D position class.
 */
public class Position2D {

    private final int x;
    private final int y;

    /**
     * Position's coordinates.
     *
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
}
