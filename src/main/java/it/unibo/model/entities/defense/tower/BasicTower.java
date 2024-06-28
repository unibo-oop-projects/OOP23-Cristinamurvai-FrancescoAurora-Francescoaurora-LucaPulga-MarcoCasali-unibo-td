package it.unibo.model.entities.defense.tower;

import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.model.entities.defense.tower.attack.AttackStrategy;
import it.unibo.model.entities.defense.tower.target.TargetSelectionStrategy;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.defense.weapon.WeaponImpl;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

public class BasicTower extends AbstractTower {

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
     * {@link Tower}'s target method to identify the target {@link Enemy}.
     * Target @param enemies available on the map.
     */
    @Override
    public Optional<Enemy> target(final Set<Enemy> enemies) {
        return this.getTargetSelectionStrategy().selectTarget(this, enemies);
    }

    /**
     * {@link Tower}'s attack method to attack {@link Enemy}.
     *
     * @param enemies available on the map.
     */
    @Override
    public void attack(final Set<Enemy> enemies) {
        if (!enemies.isEmpty()) {
            Optional<Enemy> chosenEnemy = this.target(enemies);
            chosenEnemy.ifPresent(enemy -> {
                this.getAttackStrategy().attack(this, chosenEnemy);
            });
        }
        updateBullets();
    }
}
