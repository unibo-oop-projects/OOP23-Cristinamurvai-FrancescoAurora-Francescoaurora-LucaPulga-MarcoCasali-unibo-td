package it.unibo.model.defense;
import org.junit.jupiter.api.BeforeEach;
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

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TestTargetStrategy {

    private DistanceBasedTargetSelection targetSelection;
    private Tower tower;
    private Set<Enemy> enemies;
    private Enemy enemy0;
    private Enemy enemy1;

    @BeforeEach
    void setUp() {
        targetSelection = new DistanceBasedTargetSelection();
        tower = new BasicTower(1, "Tower1", "Type1", "tower_img", new Position2D(0, 0), null, 100, 1, 100, Set.of(new WeaponImpl(0, "Weapon1", "Gun", "weapons/bow.png", 2)), new WeaponImpl(0, "Weapon1", "Gun", "weapons/bow.png", 2), new SingleTargetAttack(), new DistanceBasedTargetSelection());
        enemies = new HashSet<>();
        
        Position2D position = new Position2D(0, 0);
        Vector2D direction = new Vector2D(1, 0);
        Position2D pathEndPosition = new Position2D(10, 10);
        int lp = 100;
        int reward = 50;

        enemy0 = new EnemyImpl(1, "Enemy1", "Type1", "img1", position, direction, pathEndPosition, lp, reward);
        enemy1 = new EnemyImpl(1, "Enemy1", "Type1", "img1", pathEndPosition, direction, pathEndPosition, lp, reward);    
    }

    @Test
    void testSelectTarget_WithValidEnemyWithinRange() {
        Enemy enemyWithinRange = enemy0;
        enemies.add(enemyWithinRange);

        Optional<Enemy> targetEnemy = targetSelection.selectTarget(tower, enemies);
        assertTrue(targetEnemy.isPresent());
        assertEquals(1, targetEnemy.get().getId());
    }

    @Test
    void testSelectTarget_WithNoEnemyWithinRange() {
        Enemy enemyOutsideRange1 = enemy0;
        Enemy enemyOutsideRange2 = enemy1;
        enemies.add(enemyOutsideRange1);
        enemies.add(enemyOutsideRange2);

        Optional<Enemy> targetEnemy = targetSelection.selectTarget(tower, enemies);
        assertTrue(targetEnemy.isPresent());
    }

    @Test
    void testSelectTarget_WithEmptyEnemiesSet() {
        Optional<Enemy> targetEnemy = targetSelection.selectTarget(tower, enemies);
        assertFalse(targetEnemy.isPresent());
    }
}
