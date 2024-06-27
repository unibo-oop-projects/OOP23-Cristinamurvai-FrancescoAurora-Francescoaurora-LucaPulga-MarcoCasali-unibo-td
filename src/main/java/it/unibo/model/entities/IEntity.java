package it.unibo.model.entities;

/**
 * Represents an entity in the game, that is, something that has name, position
 * and direction.
 */
public interface IEntity {

    /**
     * {@link IEntity}'s id.
     * @return {@link IEntity}'s id.
     */
    int getId();

    /**
     * {@link IEntity}'s name.
     * @return {@link IEntity}'s name.
     */
    String getName();

    /**
     * {@link IEntity}'s type.
     * @return {@link IEntity}'s type.
     */
    String getType();

    /**
     * {@link IEntity}'s image path.
     * @return {@link IEntity}'s image path.
     */
    String getPath();
}
