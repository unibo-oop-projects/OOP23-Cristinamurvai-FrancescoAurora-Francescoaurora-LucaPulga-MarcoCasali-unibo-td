package it.unibo.model.entities.defense.tower;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import it.unibo.model.entities.defense.tower.attack.AttackStrategy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;
import it.unibo.model.entities.defense.tower.attack.SingleTargetAttack;
import it.unibo.model.entities.defense.tower.target.DistanceBasedTargetSelection;
import it.unibo.model.entities.defense.tower.target.TargetSelectionStrategy;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.defense.tower.attack.AreaAttack;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TowerDeserializer<T extends Tower> extends StdDeserializer<T>  {

    private Class<T> towerClass;

    public TowerDeserializer(Class<T> towerClass) {
        super(towerClass);
        this.towerClass = towerClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        
        int id = node.get("id").asInt();
        String name = node.get("name").asText();
        String type = node.get("type").asText();
        
        String imgPath = node.get("imgPath").asText();
        
        Position2D position2d = ctxt.readValue(node.get("position2d").traverse(), Position2D.class);
        Vector2D direction2d = ctxt.readValue(node.get("direction2d").traverse(), Vector2D.class);
        
        int cost = node.get("cost").asInt();
        int level = node.get("level").asInt();
        int range = node.get("range").asInt();

        JsonNode weaponsNode = node.get("weapons");
        TypeFactory typeFactory = ctxt.getTypeFactory();
        CollectionType weaponsType = typeFactory.constructCollectionType(Set.class, Weapon.class);
        Set<Weapon> weapons = ctxt.readValue(weaponsNode.traverse(), weaponsType);
        
        Weapon currentWeapon = ctxt.readValue(node.get("currentWeapon").traverse(), Weapon.class);

        Map<String, Class<? extends AttackStrategy>> attackStrategies = new HashMap<>();
        attackStrategies.put("SingleTargetAttack", SingleTargetAttack.class);
        attackStrategies.put("AreaAttack", AreaAttack.class);
        
        String attackStrategyName = node.get("attackStrategy").asText();
        Class<? extends AttackStrategy> attackStrategyClass = attackStrategies.get(attackStrategyName);
        
        AttackStrategy attackStrategy = null;
        if (attackStrategyClass != null) {
            try {
                attackStrategy = attackStrategyClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Map<String, Class<? extends TargetSelectionStrategy>> targetSelectionStrategies = new HashMap<>();
        targetSelectionStrategies.put("DistanceBasedTargetSelection", DistanceBasedTargetSelection.class);

        String targetSelectionStrategyName = node.get("targetSelectionStrategy").asText();
        Class<? extends TargetSelectionStrategy> targetSelectionStrategyClass = targetSelectionStrategies.get(targetSelectionStrategyName);
        
        TargetSelectionStrategy targetSelectionStrategy = null;
        try {
            targetSelectionStrategy = targetSelectionStrategyClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (towerClass.equals(BasicTower.class)) {
            Tower tower = new BasicTower(id, name, type, imgPath, position2d, direction2d, cost, level, range, weapons, currentWeapon, null, null);
            tower.setAttackStrategy(attackStrategy);
            tower.setTargetSelectionStrategy(targetSelectionStrategy);
            return (T) tower;
        }
        return null;
    }
}
