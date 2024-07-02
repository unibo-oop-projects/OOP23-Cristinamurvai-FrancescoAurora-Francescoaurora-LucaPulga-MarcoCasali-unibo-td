package it.unibo.model.defense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.entities.enemies.EnemyImpl;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;
import it.unibo.model.entities.defense.bullet.BulletImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for BulletImpl.
 */
class TestBulletImpl {

    private static final int BULLET_ID = 1;
    private static final String BULLET_NAME = "Bullet";
    private static final String BULLET_TYPE = "Basic";
    private static final String BULLET_IMG_PATH = "bullet/img/bullet.png";
    private static final Position2D INITIAL_POSITION = new Position2D(0, 0);
    private static final Vector2D INITIAL_DIRECTION = new Vector2D(1, 1);
    private static final double BULLET_SPEED = 0.1;
    private static final int BULLET_DAMAGE = 10;
    private static final Position2D ENEMY_POSITION = new Position2D(10, 10);
    private static final Vector2D ENEMY_DIRECTION = new Vector2D(0, 0);
    private static final Position2D PATH_END_POSITION = new Position2D(10, 10);
    private static final int ENEMY_HEALTH = 100;
    private static final int ENEMY_DAMAGE = 10;
    private static final Position2D POSITION_5_5 = new Position2D(5, 5);
    private static final Vector2D DIRECTION_0_MINUS1 = new Vector2D(0, -1);
    private static final Position2D POSITION_25_25 = new Position2D(25, 25);
    private static final Position2D POSITION_MINUS1_25 = new Position2D(-1, 25);
    private static final Position2D POSITION_25_MINUS1 = new Position2D(25, -1);
    private static final Position2D POSITION_51_25 = new Position2D(51, 25);
    private static final Position2D POSITION_25_51 = new Position2D(25, 51);

    private BulletImpl bullet;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    void setUp() {
        final Enemy targetEnemy = new EnemyImpl(1, "Enemy", "Basic", "enemies/img/gobby_jump.gif", 
            ENEMY_POSITION, ENEMY_DIRECTION, PATH_END_POSITION, ENEMY_HEALTH, ENEMY_DAMAGE);
        bullet = new BulletImpl(BULLET_ID, BULLET_NAME, BULLET_TYPE, BULLET_IMG_PATH, 
            INITIAL_POSITION, INITIAL_DIRECTION, BULLET_SPEED, BULLET_DAMAGE, targetEnemy);
    }

    /**
     * Tests the constructors and getters of BulletImpl.
     */
    @Test
    void testConstructorsAndGetters() {
        assertEquals(BULLET_ID, bullet.getId());
        assertEquals(BULLET_NAME, bullet.getName());
        assertEquals(BULLET_TYPE, bullet.getType());
        assertEquals(BULLET_IMG_PATH, bullet.getPath());
        assertEquals(INITIAL_POSITION, bullet.getPosition());
        assertEquals(INITIAL_DIRECTION, bullet.getDirection());
        assertEquals(BULLET_SPEED, bullet.getSpeed());
        assertEquals(BULLET_DAMAGE, bullet.getDamage());
    }

    /**
     * Tests the setters of BulletImpl.
     */
    @Test
    void testSetters() {
        bullet.setPosition(POSITION_5_5);
        bullet.setDirection(DIRECTION_0_MINUS1);

        assertEquals(POSITION_5_5, bullet.getPosition());
        assertEquals(DIRECTION_0_MINUS1, bullet.getDirection());
    }

    /**
     * Tests if the bullet has reached its target.
     */
    @Test
    void testHasReachedTarget() {
        bullet.setPosition(ENEMY_POSITION);
        assertTrue(bullet.hasReachedTarget());

        bullet.setPosition(POSITION_5_5);
        assertFalse(bullet.hasReachedTarget());
    }

    /**
     * Tests if the bullet is out of bounds.
     */
    @Test
    void testIsOutOfBounds() {
        bullet.setPosition(POSITION_25_25);
        assertFalse(bullet.isOutOfBounds());

        bullet.setPosition(POSITION_MINUS1_25);
        assertTrue(bullet.isOutOfBounds());

        bullet.setPosition(POSITION_25_MINUS1);
        assertTrue(bullet.isOutOfBounds());

        bullet.setPosition(POSITION_51_25);
        assertTrue(bullet.isOutOfBounds());

        bullet.setPosition(POSITION_25_51);
        assertTrue(bullet.isOutOfBounds());
    }
}
