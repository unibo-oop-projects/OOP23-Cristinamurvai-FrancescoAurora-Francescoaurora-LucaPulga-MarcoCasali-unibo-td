package it.unibo.model.defense;
import static org.junit.jupiter.api.Assertions.*;

import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.entities.enemies.EnemyImpl;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;
import it.unibo.model.entities.defense.bullet.BulletImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBulletImpl {

    private BulletImpl bullet;
    private Enemy targetEnemy;

    @BeforeEach
    public void setUp() {
        Position2D enemyPosition = new Position2D(10, 10);
        Vector2D enemyDirection = new Vector2D(0, 0);
        Position2D pathEndPosition = new Position2D(10, 10);
        targetEnemy = new EnemyImpl(1, "Enemy", "Basic", "enemies/img/gobby_jump.gif", enemyPosition, enemyDirection, pathEndPosition, 100, 10);
        bullet = new BulletImpl(1, "Bullet", "Basic", "bullet/img/bullet.png", new Position2D(0, 0), new Vector2D(1, 1), 0.1, 10, targetEnemy);
    }

    @Test
    void testConstructorsAndGetters() {
        assertEquals(1, bullet.getId());
        assertEquals("Bullet", bullet.getName());
        assertEquals("Basic", bullet.getType());
        assertEquals("bullet/img/bullet.png", bullet.getPath());
        assertEquals(new Position2D(0, 0), bullet.getPosition());
        assertEquals(new Vector2D(1, 1), bullet.getDirection());
        assertEquals(0.1, bullet.getSpeed());
        assertEquals(10, bullet.getDamage());
    }

    @Test
    void testSetters() {
        Position2D newPosition = new Position2D(5, 5);
        Vector2D newDirection = new Vector2D(0, -1);
        bullet.setPosition(newPosition);
        bullet.setDirection(newDirection);

        assertEquals(newPosition, bullet.getPosition());
        assertEquals(newDirection, bullet.getDirection());
    }

    @Test
    void testHasReachedTarget() {
        bullet.setPosition(new Position2D(10, 10));
        assertTrue(bullet.hasReachedTarget());

        bullet.setPosition(new Position2D(5, 5));
        assertFalse(bullet.hasReachedTarget());
    }

    @Test
    void testIsOutOfBounds() {
        bullet.setPosition(new Position2D(25, 25));
        assertFalse(bullet.isOutOfBounds());

        bullet.setPosition(new Position2D(-1, 25));
        assertTrue(bullet.isOutOfBounds());

        bullet.setPosition(new Position2D(25, -1));
        assertTrue(bullet.isOutOfBounds());

        bullet.setPosition(new Position2D(51, 25));
        assertTrue(bullet.isOutOfBounds());

        bullet.setPosition(new Position2D(25, 51));
        assertTrue(bullet.isOutOfBounds());
    }
}
