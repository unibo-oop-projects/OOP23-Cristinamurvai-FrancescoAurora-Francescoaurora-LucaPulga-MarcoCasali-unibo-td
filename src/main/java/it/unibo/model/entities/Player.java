package it.unibo.model.entities;
/**
 * Player Interface.
 */
public interface Player {

    /**
     * @return Returns the maximum number of lives the player can have
     */
    int getMaxLives();

    /**
     * @return Number of lives the player has left
     */
    int getRemainingLives();

    /**
     * Damage the player's screw.
     * @param damage Number of lives the player has lost
     */
    void loseLives(int damage);

    /**
     * Restoring the player's screw.
     * @param numberLives Number of lives to be restored
     */
    void restoreLives(int numberLives);

    /**
     * @return money currently held
     */
    int getMoney();

    /**
     * the cash will be added to the money.
     * @param cash in the case of a reward the value stored in the money will increase, 
     * - however in the case of a construction cost the past value will be negative so it will decrease
     */
    void setMoney(int cash);

}
