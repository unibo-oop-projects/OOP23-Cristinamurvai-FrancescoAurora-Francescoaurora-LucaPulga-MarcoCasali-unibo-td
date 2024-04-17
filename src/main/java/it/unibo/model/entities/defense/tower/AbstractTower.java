package it.unibo.model.entities.defense.tower;

import it.unibo.model.entities.AbstractEntity;

public abstract class AbstractTower extends AbstractEntity implements Tower {

    public AbstractTower(int id, String name, String type) {
        super(id, name, type);
    }
    
}
