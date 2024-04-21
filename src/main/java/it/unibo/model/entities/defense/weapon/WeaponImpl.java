package it.unibo.model.entities.defense.weapon;

import it.unibo.model.entities.AbstractEntity;
/**
 * Weapon implementation.
 */
public class WeaponImpl extends AbstractEntity implements Weapon{

    private int frequnecy; // Tempo in millisecondi tra due bullet.
    private long lastShotTime; // Tempo dell'ultimo sparo.

    public WeaponImpl(int id, String name, String type, int frequnecy) {
        super(id, name, type);
        this.frequnecy = frequnecy;
        this.lastShotTime = 0;
    }

    /**
     * Weapoon hit frequnecy.
     * @return weapoon hit frequnecy. 
    */
    @Override
    public int getFrequency() {
        return this.frequnecy;
    }

    @Override
    public long getLastShotTime() {
        return lastShotTime;
    }

    @Override
    public void setLastShotTime(long lastShotTime) {
        this.lastShotTime = lastShotTime;
    }
}
