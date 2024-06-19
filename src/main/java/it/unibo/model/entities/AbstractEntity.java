package it.unibo.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Abstract implementation of a general abstract entity such as an enemy, a tower, a weapon or a bullet.
 */
public abstract class AbstractEntity implements IEntity {

    @JsonProperty("id")
    protected final int id;

    @JsonProperty("name")
    protected final String name;

    @JsonProperty("type")
    protected final String type;

    @JsonProperty("imgPath")
    protected final String imgPath;

    /**
     * Costructor.
     * @param id
     * @param name
     * @param type
     * @param imgPath
     */
    public AbstractEntity(final int id, final String name, final String type, final String imgPath) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.type = Objects.requireNonNull(type);
        this.imgPath = Objects.requireNonNull(imgPath); 
    }

    /**
     * Entity id.
     * @return Entity id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Entity name.
     * @return Entity name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Entity type.
     * @return Entity type.
     */
    public String getType() {
        return this.type;
    }

    public String getPath() {
        return this.imgPath;
    }
}
