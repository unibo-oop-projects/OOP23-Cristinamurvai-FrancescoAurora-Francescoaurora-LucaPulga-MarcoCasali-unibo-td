package it.unibo.model.defense;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import it.unibo.model.entities.defense.tower.BasicTower;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.tower.attack.AttackStrategy;
import it.unibo.model.entities.defense.tower.attack.SingleTargetAttack;
import it.unibo.model.entities.defense.tower.target.DistanceBasedTargetSelection;
import it.unibo.model.entities.defense.tower.target.TargetSelectionStrategy;
import it.unibo.model.entities.defense.weapon.WeaponImpl;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Test class for BasicTower.
 */
class TestBasicTower {

    private static final int TEST_ID = 1;
    private static final String TEST_NAME = "Basic Tower";
    private static final String TEST_TYPE = "Base";
    private static final String TEST_IMG_PATH = "towers/img/tower1.png";
    private static final Position2D TEST_POSITION = new Position2D(10, 10);
    private static final Vector2D TEST_DIRECTION = new Vector2D(0, 1);
    private static final int TEST_COST = 50;
    private static final int TEST_LEVEL = 1;
    private static final int TEST_RANGE = 10;
    private static final WeaponImpl TEST_WEAPON = new WeaponImpl(1, "Weapon1", "Gun", "weapons/bow.png", 2);
    private static final Set<WeaponImpl> TEST_WEAPONS = Set.of(TEST_WEAPON);
    private static final AttackStrategy TEST_ATTACK_STRATEGY = new SingleTargetAttack();
    private static final TargetSelectionStrategy TEST_TARGET_SELECTION_STRATEGY = new DistanceBasedTargetSelection();

    /**
     * Tests the getters of the BasicTower class.
     * 
     * @throws IOException if an I/O error occurs
     */
    @Test
    void testTowerGetters() throws IOException {
        final Tower result = new BasicTower(
                TEST_ID,
                TEST_NAME,
                TEST_TYPE,
                TEST_IMG_PATH,
                TEST_POSITION,
                TEST_DIRECTION,
                TEST_COST,
                TEST_LEVEL,
                TEST_RANGE,
                TEST_WEAPONS,
                TEST_WEAPON,
                TEST_ATTACK_STRATEGY,
                TEST_TARGET_SELECTION_STRATEGY
        );

        // Assertions
        assertEquals(TEST_ID, result.getId());
        assertEquals(TEST_NAME, result.getName());
        assertEquals(TEST_TYPE, result.getType());
        assertEquals(TEST_IMG_PATH, result.getPath());
        assertEquals(TEST_POSITION, result.getPosition());
        assertEquals(TEST_DIRECTION, result.getDirection());
        assertEquals(TEST_COST, result.getCost());
        assertEquals(TEST_LEVEL, result.getLevel());
        assertEquals(TEST_RANGE, result.getRange());
        assertEquals(TEST_WEAPONS, result.getWeapons());
        assertEquals(TEST_WEAPON, result.getCurrentWeapon());
        assertEquals(TEST_ATTACK_STRATEGY, result.getAttackStrategy());
        assertEquals(TEST_TARGET_SELECTION_STRATEGY, result.getTargetSelectionStrategy());
    }

    /**
     * Tests the setters of the BasicTower class.
     * 
     * @throws IOException if an I/O error occurs
     */
    @Test
    void testTowerSetters() throws IOException {
        final Tower result = new BasicTower(
                TEST_ID,
                TEST_NAME,
                TEST_TYPE,
                TEST_IMG_PATH,
                TEST_POSITION,
                TEST_DIRECTION,
                TEST_COST,
                TEST_LEVEL,
                TEST_RANGE,
                TEST_WEAPONS,
                TEST_WEAPON,
                TEST_ATTACK_STRATEGY,
                TEST_TARGET_SELECTION_STRATEGY
        );

        assertEquals(TEST_POSITION, result.getPosition());
        assertEquals(TEST_DIRECTION, result.getDirection());

        // New values for setters.
        final Position2D newPosition = new Position2D(10, 11);
        final Vector2D newDirection = new Vector2D(1, 1);

        // Set new values.
        result.setPosition(newPosition);
        result.setDirection(newDirection);

        // Assertions for new values.
        assertEquals(newPosition, result.getPosition());
        assertEquals(newDirection, result.getDirection());
    }
}
