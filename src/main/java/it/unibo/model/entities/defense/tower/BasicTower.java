package it.unibo.model.entities.defense.tower;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Optional;
import it.unibo.model.entities.defense.tower.attack.AttackStrategy;
import it.unibo.model.entities.defense.tower.target.TargetSelectionStrategy;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

public class BasicTower extends AbstractTower {

    @JsonCreator
    public BasicTower(@JsonProperty("id") int id, 
                      @JsonProperty("name") String name, 
                      @JsonProperty("type") String type, 
                      @JsonProperty("position2d") Position2D position2d, 
                      @JsonProperty("direction2d") Vector2D direction2d, 
                      @JsonProperty("cost") int cost, 
                      @JsonProperty("level") int level, 
                      @JsonProperty("range") int range, 
                      @JsonProperty("weapons") Set<Weapon> weapons, 
                      @JsonProperty("currentWeapon") Weapon currentWeapon,
                      @JsonProperty("attackStrategy") AttackStrategy attackStrategy,
                      @JsonProperty("targetSelectionStrategy") TargetSelectionStrategy targetSelectionStrategy) {
        super(id, name, type, position2d, direction2d, cost, level, range, weapons, currentWeapon, attackStrategy, targetSelectionStrategy);
    }

    public Optional<Set<Enemy>> target(Set<Enemy> enemies){
        Optional<Set<Enemy>> targets = this.targetSelectionStrategy.selectTarget(this, enemies);
        return targets;
    }

    public void attack(Set<Enemy> enemies) {
        if (!enemies.isEmpty()){
            this.attackStrategy.attack(this, this.target(enemies));
        }
        else{
            System.out.println("No enemies to attack");
        }
    }
}
