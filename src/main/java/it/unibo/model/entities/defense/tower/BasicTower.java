package it.unibo.model.entities.defense.tower;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Optional;

import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.tower.attack.AttackStrategy;
import it.unibo.model.entities.defense.tower.target.TargetSelectionStrategy;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.defense.weapon.WeaponImpl;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

public class BasicTower extends AbstractTower {

    private Set<Bullet> bullets = new HashSet<>();

    @JsonCreator
    public BasicTower(@JsonProperty("id") final int id, 
                      @JsonProperty("name") final String name, 
                      @JsonProperty("type") final String type, 
                      @JsonProperty("imgPath") final String imgPath,
                      @JsonProperty("position2d") final Position2D position2d, 
                      @JsonProperty("direction2d") final Vector2D direction2d, 
                      @JsonProperty("cost") final int cost, 
                      @JsonProperty("level") final int level, 
                      @JsonProperty("range") final int range, 
                      @JsonProperty("weapons") final Set<WeaponImpl> weapons, 
                      @JsonProperty("currentWeapon") final Weapon currentWeapon,
                      @JsonProperty("attackStrategy") final AttackStrategy attackStrategy,
                      @JsonProperty("targetSelectionStrategy") final TargetSelectionStrategy targetSelectionStrategy) {
        super(id, name, type, imgPath, position2d, direction2d, cost, level, range, weapons, currentWeapon, 
                attackStrategy, targetSelectionStrategy);
    }

    /**
     * .
     */
    public Optional<Set<Enemy>> target(final Set<Enemy> enemies) {
        Optional<Set<Enemy>> targets = this.targetSelectionStrategy.selectTarget(this, enemies);
        return targets;
    }

    /**
     * .
     */
    public void attack(final Set<Enemy> enemies) {
        if (!enemies.isEmpty()) {
            this.attackStrategy.attack(this, this.target(enemies));
        } else {
            System.out.println("No enemies to attack");
        }
    }

    @Override
    public void setTargetSelectionStrategy(final TargetSelectionStrategy targetSelectionStrategy) {
        this.targetSelectionStrategy = targetSelectionStrategy;
    }

    @Override
    public void setAttackStrategy(final AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    // @Override
    // public Set<Bullet> getBullets(){
    //     this.bullets;
    // }
}
