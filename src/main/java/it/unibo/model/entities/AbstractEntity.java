package it.unibo.model.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Abstract implementation of a general abstract entity such as an enemy, a
 * tower, a weapon or a bullet.
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
     * {@link AbstractEntity}'s @param id
     * {@link AbstractEntity}'s @param name
     * {@link AbstractEntity}'s @param type
     * {@link AbstractEntity}'s @param imgPath
     */
    public AbstractEntity(final int id, final String name, final String type, final String imgPath) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.type = Objects.requireNonNull(type);
        this.imgPath = Objects.requireNonNull(imgPath);
    }

    /**
     * {@link AbstractEntity}'s id.
     * @return {@link AbstractEntity}'s id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * {@link AbstractEntity}'s name.
     * @return {@link AbstractEntity}'s name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * {@link AbstractEntity}'s type.
     * @return {@link AbstractEntity}'s type.
     */
    public String getType() {
        return this.type;
    }

    /**
     * {@link AbstractEntity}'s image path.
     * @return {@link AbstractEntity}'s image path.
     */
    public String getPath() {
        return this.imgPath;
    }
}
