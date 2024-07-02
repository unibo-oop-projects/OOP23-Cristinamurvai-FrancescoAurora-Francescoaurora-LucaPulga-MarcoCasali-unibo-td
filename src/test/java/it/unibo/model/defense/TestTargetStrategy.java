package it.unibo.model.defense;

import org.junit.jupiter.api.Test;

import it.unibo.model.entities.defense.tower.BasicTower;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.tower.attack.SingleTargetAttack;
import it.unibo.model.entities.defense.tower.target.DistanceBasedTargetSelection;
import it.unibo.model.entities.defense.weapon.WeaponImpl;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.entities.enemies.EnemyImpl;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for testing the target selection strategy.
 */
class TestTargetStrategy {

    private static final int TOWER_ID = 1;
    private static final String TOWER_NAME = "Tower1";
    private static final String TOWER_TYPE = "Type1";
    private static final String TOWER_IMAGE = "tower_img";
    private static final int TOWER_COST = 100;
    private static final int TOWER_LEVEL = 1;
    private static final int TOWER_RANGE = 100;
    private static final int WEAPON_ID = 0;
    private static final String WEAPON_NAME = "Weapon1";
    private static final String WEAPON_TYPE = "Gun";
    private static final String WEAPON_PATH = "weapons/bow.png";
    private static final int WEAPON_FREQUENCY = 2;
    private static final int ENEMY_ID = 1;
    private static final String ENEMY_NAME = "Enemy1";
    private static final String ENEMY_TYPE = "Type1";
    private static final String ENEMY_IMAGE = "img1";
    private static final int ENEMY_LP = 100;
    private static final int ENEMY_REWARD = 50;

    private final DistanceBasedTargetSelection targetSelection;
    private final Tower tower;
    private final Set<Enemy> enemies;
    private final Enemy enemy0;
    private final Enemy enemy1;

    /**
     * Constructor to initialize the test environment.
     */
    TestTargetStrategy() {
        targetSelection = new DistanceBasedTargetSelection();
        tower = new BasicTower(
                TOWER_ID, TOWER_NAME, TOWER_TYPE, TOWER_IMAGE,
                new Position2D(0, 0), null, TOWER_COST, TOWER_LEVEL, TOWER_RANGE,
                Set.of(new WeaponImpl(WEAPON_ID, WEAPON_NAME, WEAPON_TYPE, WEAPON_PATH, WEAPON_FREQUENCY)),
                new WeaponImpl(WEAPON_ID, WEAPON_NAME, WEAPON_TYPE, WEAPON_PATH, WEAPON_FREQUENCY),
                new SingleTargetAttack(),
                new DistanceBasedTargetSelection()
        );
        enemies = new HashSet<>();

        final Position2D position = new Position2D(0, 0);
        final Vector2D direction = new Vector2D(1, 0);
        final Position2D pathEndPosition = new Position2D(10, 10);

        enemy0 = new EnemyImpl(ENEMY_ID, ENEMY_NAME, ENEMY_TYPE, ENEMY_IMAGE, position, direction, pathEndPosition,
                ENEMY_LP, ENEMY_REWARD);
        enemy1 = new EnemyImpl(ENEMY_ID, ENEMY_NAME, ENEMY_TYPE, ENEMY_IMAGE, pathEndPosition, direction, pathEndPosition,
                ENEMY_LP, ENEMY_REWARD);
    }

    /**
     * Test target selection with a valid enemy within range.
     */
    @Test
    void testSelectTargetWithValidEnemyWithinRange() {
        enemies.add(enemy0);

        final Optional<Enemy> targetEnemy = targetSelection.selectTarget(tower, enemies);
        assertTrue(targetEnemy.isPresent());
        assertEquals(ENEMY_ID, targetEnemy.get().getId());
    }

    /**
     * Test target selection with no enemy within range.
     */
    @Test
    void testSelectTargetWithNoEnemyWithinRange() {
        enemies.add(enemy0);
        enemies.add(enemy1);

        final Optional<Enemy> targetEnemy = targetSelection.selectTarget(tower, enemies);
        assertTrue(targetEnemy.isPresent());
    }

    /**
     * Test target selection with an empty set of enemies.
     */
    @Test
    void testSelectTargetWithEmptyEnemiesSet() {
        final Optional<Enemy> targetEnemy = targetSelection.selectTarget(tower, enemies);
        assertFalse(targetEnemy.isPresent());
    }
}
