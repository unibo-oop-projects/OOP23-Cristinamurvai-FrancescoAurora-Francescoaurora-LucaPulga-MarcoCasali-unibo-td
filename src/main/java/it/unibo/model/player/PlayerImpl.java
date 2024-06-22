package it.unibo.model.player;

/**
 * Player implementation.
 */
public class PlayerImpl implements Player {

    private int money; //money of player
    private static final int MAX_LIVES = 10; //max life of player
    private static final int MONEY_START = 200; //money for star game
    private int lives;

    /**
     * Instructor method, constructs the player object with start game values.
     */
    public PlayerImpl() {
        lives = MAX_LIVES; //lives to star game
        money = MONEY_START; //money get player to start
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxLives() {
        return MAX_LIVES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRemainingLives() {
        return lives;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loseLives(final int damage) {
        lives = lives - damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restoreLives(final int numberLives) {
        loseLives(-numberLives);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMoney() {
        return money;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMoney(final int cash) {
        money = money + cash;
    }
}
