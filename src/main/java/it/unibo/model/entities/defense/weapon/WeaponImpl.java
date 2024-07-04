package it.unibo.model.entities.defense.weapon;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.model.entities.AbstractEntity;
import it.unibo.model.entities.defense.bullet.Bullet;

/**
 * Represents the Weapon implementation.
 */
public class WeaponImpl extends AbstractEntity implements Weapon {

    private final int frequency;
    private long lastShotTime;

    /**
     * Constructor.
     *
     * @param id
     * @param name
     * @param type
     * @param imgPath
     * @param frequency
     */
    @JsonCreator
    public WeaponImpl(@JsonProperty("id") final int id,
            @JsonProperty("name") final String name,
            @JsonProperty("type") final String type,
            @JsonProperty("imgPath") final String imgPath,
            @JsonProperty("frequency") final int frequency) {
        super(id, name, type, imgPath);
        this.frequency = frequency;
        this.lastShotTime = 0;
    }

    /**
     * Represents the frequency with which the {@link Weapon} can fire a
     * {@link Bullet}.
     *
     * @return the frequency with which the weapon can fire a {@link Bullet}.
     */
    @Override
    public int getFrequency() {
        return this.frequency;
    }

    /**
     * Represents the last {@link Bullet}'s time fired by the {@link Weapon}.
     *
     * @return the last {@link Bullet}'s time fired by the {@link Weapon} in
     * millis.
     */
    @Override
    public long getLastShotTime() {
        return lastShotTime;
    }

    /**
     * Sets the last {@link Bullet}'s time fired by the {@link Weapon}.
     *
     * @param lastShotTime sets the last {@link Bullet}'s time fired by the
     * {@link Weapon} in millis.
     */
    @Override
    public void setLastShotTime(final long lastShotTime) {
        this.lastShotTime = lastShotTime;
    }
}
