package it.unibo.model.entities;

/**
 * Represents an entity in the game, that is, something that has name, position
 * and direction.
 */
public interface IEntity {

    /**
     * Entity's id.
     *
     * @return Entity's id.
     */
    int getId();

    /**
     * Entity's name.
     *
     * @return Entity's name.
     */
    String getName();

    /**
     * Entity's type.
     *
     * @return Entity's type.
     */
    String getType();

    String getPath();
}
