package it.unibo.model.entities;

import java.util.Objects;

/**
 * Abstract Entity.
 */
public abstract class AbstractEntity implements Entity {
    protected final int id;
    protected final String name;
    protected final String type;

    /**
     * @param id
     * @param name
     * @param type
     * @param position2d
     * @param direction2d
     */
    public AbstractEntity(final int id, final String name, final String type) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.type = Objects.requireNonNull(type); 
    }
            
    /**
     * .Entity id.
     * @return Entity id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * .Entity name.
     * @return Entity name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * .Entity type.
     * @return Entity type.
     */
    public String getType() {
        return this.type;
    }
}
