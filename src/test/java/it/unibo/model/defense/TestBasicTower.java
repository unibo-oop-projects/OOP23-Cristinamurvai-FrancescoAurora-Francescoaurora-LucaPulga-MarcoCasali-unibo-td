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

public class TestBasicTower {

    @Test
    public void testTowerGetters() throws IOException {
        final int testId = 1;
        final String testName = "Basic Tower";
        final String testType = "Base";
        final String testImgPath = "towers/img/tower1.png";
        final Position2D testPosition = new Position2D(10, 10);
        final Vector2D testDirection = new Vector2D(0, 1);
        final int testCost = 50;
        final int testLevel = 1;
        final int testRange = 10;
        final WeaponImpl testWeapon = new WeaponImpl(1, "Weapon1", "Gun", "weapons/bow.png", 2);
        final Set<WeaponImpl> testWeapons = Set.of(testWeapon);
        final AttackStrategy testAttackStrategy = new SingleTargetAttack();
        final TargetSelectionStrategy testTargetSelectionStrategy = new DistanceBasedTargetSelection();

        final Tower result = new BasicTower(
                testId,
                testName,
                testType,
                testImgPath,
                testPosition,
                testDirection,
                testCost,
                testLevel,
                testRange,
                testWeapons,
                testWeapon,
                testAttackStrategy,
                testTargetSelectionStrategy
        );

        // Assertions
        assertEquals(testId, result.getId());
        assertEquals(testName, result.getName());
        assertEquals(testType, result.getType());
        assertEquals(testImgPath, result.getPath());
        assertEquals(testPosition, result.getPosition());
        assertEquals(testDirection, result.getDirection());
        assertEquals(testCost, result.getCost());
        assertEquals(testLevel, result.getLevel());
        assertEquals(testRange, result.getRange());
        assertEquals(testWeapons, result.getWeapons());
        assertEquals(testWeapon, result.getCurrentWeapon());
        assertEquals(testAttackStrategy, result.getAttackStrategy());
        assertEquals(testTargetSelectionStrategy, result.getTargetSelectionStrategy());
    }

    @Test
    public void testTowerSetters() throws IOException {
        final int testId = 1;
        final String testName = "Basic Tower";
        final String testType = "Base";
        final String testImgPath = "towers/img/tower1.png";
        final Position2D testPosition = new Position2D(10, 10);
        final Vector2D testDirection = new Vector2D(0, 1);
        final int testCost = 50;
        final int testLevel = 1;
        final int testRange = 10;
        final WeaponImpl testWeapon = new WeaponImpl(1, "Weapon1", "Gun", "weapons/bow.png", 2);
        final Set<WeaponImpl> testWeapons = Set.of(testWeapon);
        final AttackStrategy testAttackStrategy = new SingleTargetAttack();
        final TargetSelectionStrategy testTargetSelectionStrategy = new DistanceBasedTargetSelection();

        final Tower result = new BasicTower(
                testId,
                testName,
                testType,
                testImgPath,
                testPosition,
                testDirection,
                testCost,
                testLevel,
                testRange,
                testWeapons,
                testWeapon,
                testAttackStrategy,
                testTargetSelectionStrategy
        );

        assertEquals(testPosition, result.getPosition());
        assertEquals(testDirection, result.getDirection());

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
