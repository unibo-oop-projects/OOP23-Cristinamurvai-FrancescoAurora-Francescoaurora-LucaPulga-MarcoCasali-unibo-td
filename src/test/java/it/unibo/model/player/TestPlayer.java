package it.unibo.model.player;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test {@link PlayerImpl}.
 */
public class TestPlayer {

    private PlayerImpl player;
    private static final int MONEY_TEST = 50;

    /**
     * Sets up a new instance of {@link PlayerImpl} before each test.
     */
    @BeforeEach
    public void setUp() {
        player = new PlayerImpl();
    }

    /**
     * Test case for initial values of the player.
     *
     * @throws NoSuchFieldException if a field on the class or interface could
     * not be found.
     * @throws IllegalAccessException if the underlying field is inaccessible.
     */
    @Test
    public void testInitialValues() throws NoSuchFieldException, IllegalAccessException {
        assertEquals(getFieldValue(PlayerImpl.class, "MAX_LIVES"), player.getMaxLives());
        assertEquals(getFieldValue(PlayerImpl.class, "MAX_LIVES"), player.getRemainingLives());
        assertEquals(getFieldValue(PlayerImpl.class, "MONEY_START"), player.getMoney());
    }

    /**
     * Test case for losing lives by the player.
     *
     * @throws NoSuchFieldException if a field on the class or interface could
     * not be found.
     * @throws IllegalAccessException if the underlying field is inaccessible.
     */
    @Test
    public void testLoseLives() throws NoSuchFieldException, IllegalAccessException {
        player.loseLives(3);
        assertEquals(getFieldValue(PlayerImpl.class, "MAX_LIVES") - 3, player.getRemainingLives());
    }

    /**
     * Test case for restoring lives to the player.
     *
     * @throws NoSuchFieldException if a field on the class or interface could
     * not be found.
     * @throws IllegalAccessException if the underlying field is inaccessible.
     */
    @Test
    public void testRestoreLives() throws NoSuchFieldException, IllegalAccessException {
        player.loseLives(3);
        player.restoreLives(2);
        assertEquals(getFieldValue(PlayerImpl.class, "MAX_LIVES") - 1, player.getRemainingLives());
    }

    /**
     * Test case for setting money to the player.
     *
     * @throws NoSuchFieldException if a field on the class or interface could
     * not be found.
     * @throws IllegalAccessException if the underlying field is inaccessible.
     */
    @Test
    public void testSetMoney() throws NoSuchFieldException, IllegalAccessException {
        player.setMoney(MONEY_TEST);
        assertEquals(getFieldValue(PlayerImpl.class, "MONEY_START") + MONEY_TEST, player.getMoney());
    }

    /**
     * Test case for setting negative money to the player.
     *
     * @throws NoSuchFieldException if a field on the class or interface could
     * not be found.
     * @throws IllegalAccessException if the underlying field is inaccessible.
     */
    @Test
    public void testSetMoneyNegative() throws NoSuchFieldException, IllegalAccessException {
        player.setMoney(-MONEY_TEST);
        assertEquals(getFieldValue(PlayerImpl.class, "MONEY_START") - MONEY_TEST, player.getMoney());
    }

    /**
     * Retrieves the value of a private field in a class or interface using
     * reflection.
     *
     * @param clazz the class or interface containing the field.
     * @param fieldName the name of the field to retrieve.
     * @return the current value of the field.
     * @throws NoSuchFieldException if a field with the specified name is not
     * found.
     * @throws IllegalAccessException if the underlying field is inaccessible.
     */
    private static int getFieldValue(final Class<?> clazz, final String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (int) field.get(null); // Use null for static fields
    }
}
