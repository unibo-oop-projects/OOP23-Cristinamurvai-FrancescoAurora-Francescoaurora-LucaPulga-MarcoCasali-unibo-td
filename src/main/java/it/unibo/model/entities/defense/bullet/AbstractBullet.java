package it.unibo.model.entities.defense.bullet;

import it.unibo.model.entities.AbstractEntity;

public abstract class AbstractBullet extends AbstractEntity implements Bullet {

    public AbstractBullet(int id, String name, String type) {
        super(id, name, type);
    }
    
}
