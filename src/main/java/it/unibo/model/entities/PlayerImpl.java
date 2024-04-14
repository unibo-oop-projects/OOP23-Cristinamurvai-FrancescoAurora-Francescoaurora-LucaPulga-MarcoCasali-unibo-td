package it.unibo.model.entities;

public class PlayerImpl implements Player{

    private int money; //money of player
    private final int maxLives = 3; //max life of player
    private int lives;

    public PlayerImpl(){
        lives=1; //lives to star game
        money=1; //money get player to start
    }

    public int getMaxLives(){
        return maxLives;
    }

    public int getRemainingLives(){
        return lives;
    }

    public void loseLives(int damage){
        lives=lives-damage;
        return;
    }

    public void restoreLives(int numberLives){
        lives=lives+numberLives;
        return;
    }
    
    public int getMoney(){
        return money;
    }
}
