package it.unibo.model.entities;

public interface Player {

    
    int getMaxLives();

    int getRemainingLives();

    void loseLives(int damage);

    void restoreLives(int numberLives);
    
    int getMoney();
}
