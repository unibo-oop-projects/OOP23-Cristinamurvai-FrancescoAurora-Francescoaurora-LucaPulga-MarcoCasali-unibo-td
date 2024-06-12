package it.unibo.model.entities.defense.weapon;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.model.entities.AbstractEntity;
/**
 * Weapon implementation.
 */
public class WeaponImpl extends AbstractEntity implements Weapon {

    private int frequency; // Tempo in millisecondi tra due bullet.
    private long lastShotTime; // Tempo dell'ultimo sparo.

    @JsonCreator
    public WeaponImpl(@JsonProperty("id")final int id, 
                      @JsonProperty("name")final String name, 
                      @JsonProperty("type")final String type, 
                      @JsonProperty("imgPath")final String imgPath, 
                      @JsonProperty("frequency")final int frequency) {
        super(id, name, type, imgPath);
        this.frequency = frequency;
        this.lastShotTime = 0;
    }

    /**
     * Weapoon hit frequnecy.
     * @return weapoon hit frequnecy. 
    */
    @Override
    public int getFrequency() {
        return this.frequency;
    }

    @Override
    public long getLastShotTime() {
        return lastShotTime;
    }

    @Override
    public void setLastShotTime(final long lastShotTime) {
        this.lastShotTime = lastShotTime;
    }
}
