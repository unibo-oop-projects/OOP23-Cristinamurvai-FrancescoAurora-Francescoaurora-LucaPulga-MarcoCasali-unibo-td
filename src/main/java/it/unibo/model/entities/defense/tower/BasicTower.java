package it.unibo.model.entities.defense.tower;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.bullet.BulletImpl;
import it.unibo.model.entities.defense.tower.attack.AttackStrategy;
import it.unibo.model.entities.defense.tower.target.TargetSelectionStrategy;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.defense.weapon.WeaponImpl;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

public class BasicTower extends AbstractTower {

    private Set<Bullet> bullets;

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
        this.bullets = new HashSet<>();
    }

    @Override
    public Optional<Enemy> target(Set<Enemy> enemies) {
        return this.targetSelectionStrategy.selectTarget(this, enemies);
    }

    @Override
    public void attack(Set<Enemy> enemies) {
        if (!enemies.isEmpty()) {
            Optional<Enemy> chosenEnemy = this.target(enemies);
            chosenEnemy.ifPresent(enemy -> {
                this.attackStrategy.attack(this, chosenEnemy);
                createBullet(enemy);
            });
        }
    }

    private void createBullet(Enemy target) {
        Bullet bullet = new BulletImpl(
            this.getId(), 
            this.getName() + " bullet", 
            "bullet", 
            "bullet/img/bullet.png",
            this.getPosition(), 
            new Vector2D(0, 0), 
            10,  
            100, 
            target
        );
        this.bullets.add(bullet);
    }

    @Override
    public void setTargetSelectionStrategy(TargetSelectionStrategy targetSelectionStrategy) {
        this.targetSelectionStrategy = targetSelectionStrategy;
    }

    @Override
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    @Override
    public Set<Bullet> getBullets() {
        return this.bullets;
    }

    @Override
    public void clearBullets() {
        this.bullets.clear();
    }
}
