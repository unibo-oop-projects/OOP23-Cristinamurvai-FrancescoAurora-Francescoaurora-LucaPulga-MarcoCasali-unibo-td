package it.unibo.model.entities.defense.bullet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.model.core.GameObserver;
import it.unibo.model.core.GameState;
import it.unibo.model.entities.AbstractMovableEntity;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * .
 */
public class BulletImpl extends AbstractMovableEntity implements Bullet, GameObserver {

    private int damage;
    private int speed;
    private Enemy targetEnemy;

    /**
     * Constructor.
     * @param id
     * @param name
     * @param type
     * @param imgPath
     * @param initialPosition
     * @param direction2d
     * @param speed
     * @param damage
     * @param enemy
     */
    @JsonCreator
    public BulletImpl(
                @JsonProperty("id") final int id, 
                @JsonProperty("name")final String name, 
                @JsonProperty("type")final String type, 
                @JsonProperty("imgPath")final String imgPath, 
                @JsonProperty("initialPosition")final Position2D initialPosition, 
                @JsonProperty("direction2d")final Vector2D direction2d, 
                @JsonProperty("speed")final int speed, 
                @JsonProperty("damage")final int damage, 
                @JsonProperty("enemy")final Enemy enemy) {
        super(id, name, type, imgPath, initialPosition, direction2d);
        this.speed = speed;
        this.targetEnemy = enemy;
        this.damage = damage;
    }

    /**
     * 
     * @return
     */
    public boolean hasReachedTarget() {
        return this.position2d.equals(targetEnemy.getPosition());
    }

    /**
     * .
     */
    public Position2D getPosition() {
        return this.position2d;
    }


    @Override
    public void update(GameState gameState) {
        while (!hasReachedTarget()) {
            // Calcola la direzione per inseguire il nemico.
            Vector2D directionVector = calculateDirection(this.position2d, targetEnemy.getPosition());

            // Moltiplica il vettore direzione per la velocità per ottenere la spostamento
            Vector2D movementVector = directionVector.multiply(speed);

            // Aggiorna la posizione della Bullet
            this.position2d = new Position2D(this.position2d.x() + (int) movementVector.x(), this.position2d.y() 
            + (int) movementVector.y());
        }

        // Quando il Bullet raggiunge il nemico, infliggi danni al nemico.
        this.targetEnemy.getDamage(damage);
    }

    // Metodo per calcolare la direzione per inseguire il nemico.
    // private double calculateDirection(Position2D currentPosition, Position2D targetPosition) {
    //     // Calcola la differenza tra le coordinate x e y della posizione attuale e del nemico.
    //     int deltaX = targetPosition.x() - currentPosition.x();
    //     int deltaY = targetPosition.y() - currentPosition.y();

    //     // Calcola l'angolo in radianti tra l'asse x positivo e la direzione del nemico.
    //     return Math.atan2(deltaY, deltaX);
    // }

    /**
     * 
     * @param currentPosition
     * @param targetPosition
     * @return
     */
    private Vector2D calculateDirection(final Position2D currentPosition, final Position2D targetPosition) {
        // Calcola il vettore direzione dal currentPosition al targetPosition
        int deltaX = targetPosition.x() - currentPosition.x();
        int deltaY = targetPosition.y() - currentPosition.y();
        Vector2D directionVector = new Vector2D(deltaX, deltaY);

        // Normalizza il vettore direzione per ottenere un vettore con lunghezza 1
        return directionVector.normalize();
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }
}
