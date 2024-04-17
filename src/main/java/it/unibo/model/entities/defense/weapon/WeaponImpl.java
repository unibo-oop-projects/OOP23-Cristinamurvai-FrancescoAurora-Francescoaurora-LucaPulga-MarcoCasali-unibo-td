package it.unibo.model.entities.defense.weapon;

import it.unibo.model.entities.AbstractEntity;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Weapon implementation.
 */
public class WeaponImpl extends AbstractEntity implements Weapon{

    public WeaponImpl(int id, String name, String type) {
        super(id, name, type);
    }

    /**
     * weapoon hit frequnecy.
     * @return weapoon hit frequnecy. 
    */
    @Override
    public int getFrequency() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFrequency'");
    }

    @Override
    public Position2D getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public Vector2D getDirection() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDirection'");
    }
}
