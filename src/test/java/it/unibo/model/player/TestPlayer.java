package it.unibo.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test {@link PlayerImpl}.
 */
class TestPlayer {

    private PlayerImpl player;
    private static final int MONEY_TEST = 50;
    private static final int MAX_LIVES = 10;
    private static final int MONEY_START = 200;

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
    void testInitialValues() throws NoSuchFieldException, IllegalAccessException {
        assertEquals(MAX_LIVES, player.getMaxLives());
        assertEquals(MAX_LIVES, player.getRemainingLives());
        assertEquals(MONEY_START, player.getMoney());
    }

    /**
     * Test case for losing lives by the player.
     *
     * @throws NoSuchFieldException if a field on the class or interface could
     * not be found.
     * @throws IllegalAccessException if the underlying field is inaccessible.
     */
    @Test
    void testLoseLives() throws NoSuchFieldException, IllegalAccessException {
        player.loseLives(3);
        assertEquals(MAX_LIVES - 3, player.getRemainingLives());
    }

    /**
     * Test case for restoring lives to the player.
     *
     * @throws NoSuchFieldException if a field on the class or interface could
     * not be found.
     * @throws IllegalAccessException if the underlying field is inaccessible.
     */
    @Test
    void testRestoreLives() throws NoSuchFieldException, IllegalAccessException {
        player.loseLives(3);
        player.restoreLives(2);
        assertEquals(MAX_LIVES - 1, player.getRemainingLives());
    }

    /**
     * Test case for setting money to the player.
     *
     * @throws NoSuchFieldException if a field on the class or interface could
     * not be found.
     * @throws IllegalAccessException if the underlying field is inaccessible.
     */
    @Test
    void testSetMoney() throws NoSuchFieldException, IllegalAccessException {
        player.setMoney(MONEY_TEST);
        assertEquals(MONEY_START + MONEY_TEST, player.getMoney());
    }

    /**
     * Test case for setting negative money to the player.
     *
     * @throws NoSuchFieldException if a field on the class or interface could
     * not be found.
     * @throws IllegalAccessException if the underlying field is inaccessible.
     */
    @Test
    void testSetMoneyNegative() throws NoSuchFieldException, IllegalAccessException {
        player.setMoney(-MONEY_TEST);
        assertEquals(MONEY_START - MONEY_TEST, player.getMoney());
    }
}
