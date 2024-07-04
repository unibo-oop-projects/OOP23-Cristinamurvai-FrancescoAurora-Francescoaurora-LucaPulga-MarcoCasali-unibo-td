package it.unibo.model.defense;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.model.entities.EntityFactory;
import it.unibo.model.entities.EntityFactoryImpl;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.tower.attack.SingleTargetAttack;
import it.unibo.model.entities.defense.tower.target.DistanceBasedTargetSelection;
import it.unibo.model.entities.defense.weapon.Weapon;

/**
 * Test class for the DefenseFactory.
 */
class TestDefenseFactory {

    private static final String TOWER_JSON_PATH = "towers/json/tower1.json";
    private static final int EXPECTED_TOWER_ID = 1;
    private static final String EXPECTED_TOWER_NAME = "Archery Tower";
    private static final String EXPECTED_TOWER_TYPE = "Base";
    private static final String EXPECTED_TOWER_PATH = "towers/img/tower1.png";
    private static final int EXPECTED_TOWER_COST = 50;
    private static final int EXPECTED_TOWER_LEVEL = 1;
    private static final int EXPECTED_TOWER_RANGE = 10;
    private static final int EXPECTED_WEAPON_ID = 1;
    private static final String EXPECTED_WEAPON_NAME = "Bow";
    private static final String EXPECTED_WEAPON_TYPE = "Gun";
    private static final String EXPECTED_WEAPON_PATH = "weapons/bow.png";
    private static final int EXPECTED_WEAPON_FREQUENCY = 2;

    /**
     * Tests loading a tower from a JSON file.
     * 
     * @throws IOException if an I/O error occurs
     */
    @Test
    void testLoadTowerFromJson() throws IOException {
        final EntityFactory towerFactory = new EntityFactoryImpl();
        final Tower tower = towerFactory.loadTower(TOWER_JSON_PATH);

        assertNotNull(tower);
        assertEquals(EXPECTED_TOWER_ID, tower.getId());
        assertEquals(EXPECTED_TOWER_NAME, tower.getName());
        assertEquals(EXPECTED_TOWER_TYPE, tower.getType());
        assertEquals(EXPECTED_TOWER_PATH, tower.getPath());
        assertNull(tower.getPosition());
        assertNull(tower.getDirection());
        assertEquals(EXPECTED_TOWER_COST, tower.getCost());
        assertEquals(EXPECTED_TOWER_LEVEL, tower.getLevel());
        assertEquals(EXPECTED_TOWER_RANGE, tower.getRange());
        assertNotNull(tower.getWeapons());
        assertEquals(1, tower.getWeapons().size());

        final Weapon weapon = tower.getWeapons().iterator().next();
        assertEquals(EXPECTED_WEAPON_ID, weapon.getId());
        assertEquals(EXPECTED_WEAPON_NAME, weapon.getName());
        assertEquals(EXPECTED_WEAPON_TYPE, weapon.getType());
        assertEquals(EXPECTED_WEAPON_PATH, weapon.getPath());
        assertEquals(EXPECTED_WEAPON_FREQUENCY, weapon.getFrequency());

        final Weapon currentWeapon = tower.getCurrentWeapon();
        assertNotNull(currentWeapon);
        assertEquals(EXPECTED_WEAPON_ID, currentWeapon.getId());
        assertEquals(EXPECTED_WEAPON_NAME, currentWeapon.getName());
        assertEquals(EXPECTED_WEAPON_TYPE, currentWeapon.getType());
        assertEquals(EXPECTED_WEAPON_PATH, currentWeapon.getPath());
        assertEquals(EXPECTED_WEAPON_FREQUENCY, currentWeapon.getFrequency());
        assertTrue(tower.getAttackStrategy() instanceof SingleTargetAttack);
        assertTrue(tower.getTargetSelectionStrategy() instanceof DistanceBasedTargetSelection);
    }
}
