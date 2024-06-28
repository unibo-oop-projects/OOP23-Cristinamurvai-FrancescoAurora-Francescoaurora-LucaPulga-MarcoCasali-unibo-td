package it.unibo.model.defense;


import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unibo.model.entities.EntityFactory;
import it.unibo.model.entities.EntityFactoryImpl;
import it.unibo.model.entities.defense.tower.BasicTower;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.tower.attack.SingleTargetAttack;
import it.unibo.model.entities.defense.tower.target.DistanceBasedTargetSelection;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

public class TestDefenseFactory {

    @Test
    public void testLoadTowerFromJson() throws IOException {
        EntityFactory towerFactory = new EntityFactoryImpl();
        Tower tower = towerFactory.loadTower("towers/json/tower1.json");
        assertNotNull(tower);
        assertEquals(1, tower.getId());
        assertEquals("Tower1", tower.getName());
        assertEquals("Base", tower.getType());
        assertEquals("towers/img/tower1.png", tower.getPath());
        assertNull(tower.getPosition());
        assertNull(tower.getDirection());
        assertEquals(50, tower.getCost());
        assertEquals(1, tower.getLevel());
        assertEquals(10, tower.getRange());
        assertNotNull(tower.getWeapons());
        assertEquals(1, tower.getWeapons().size());

        Weapon weapon = tower.getWeapons().iterator().next();
        assertEquals(1, weapon.getId());
        assertEquals("Weapon1", weapon.getName());
        assertEquals("Gun", weapon.getType());
        assertEquals("weapons/bow.png", weapon.getPath());
        assertEquals(2, weapon.getFrequency());
        
        Weapon currentWeapon = tower.getCurrentWeapon();
        assertNotNull(currentWeapon);
        assertEquals(1, currentWeapon.getId());
        assertEquals("Weapon1", currentWeapon.getName());
        assertEquals("Gun", currentWeapon.getType());
        assertEquals("weapons/bow.png", currentWeapon.getPath());
        assertEquals(2, currentWeapon.getFrequency());
        assertTrue(tower.getAttackStrategy() instanceof SingleTargetAttack);
        assertTrue(tower.getTargetSelectionStrategy() instanceof DistanceBasedTargetSelection);
    }
}
