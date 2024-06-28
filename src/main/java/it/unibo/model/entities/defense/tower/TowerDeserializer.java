package it.unibo.model.entities.defense.tower;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import it.unibo.model.entities.defense.tower.attack.AttackStrategy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;
import it.unibo.model.entities.defense.tower.attack.SingleTargetAttack;
import it.unibo.model.entities.defense.tower.target.DistanceBasedTargetSelection;
import it.unibo.model.entities.defense.tower.target.TargetSelectionStrategy;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.defense.weapon.WeaponImpl;
import it.unibo.model.entities.defense.tower.attack.AreaAttack;

import java.util.Set;

/**
 * Custom {@link Tower}'s JSON deserializer.
 */
public class TowerDeserializer<T extends Tower> extends StdDeserializer<T> {

    private final Class<T> towerClass;
    private final ObjectMapper mapper;

    /**
     * Constructor.
     *
     * @param towerClass (generally BasicTower)
     */
    public TowerDeserializer(final Class<T> towerClass) {
        super(towerClass);
        this.towerClass = towerClass;
        this.mapper = new ObjectMapper();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        int id = node.get("id").asInt();
        String name = node.get("name").asText();
        String type = node.get("type").asText();
        String imgPath = node.get("imgPath").asText();
        Position2D position2d = mapper.treeToValue(node.get("position2d"), Position2D.class);
        Vector2D direction2d = mapper.treeToValue(node.get("direction2d"), Vector2D.class);
        int cost = node.get("cost").asInt();
        int level = node.get("level").asInt();
        int range = node.get("range").asInt();
        Set<WeaponImpl> weapons = mapper.readValue(node.get("weapons").traverse(), new TypeReference<Set<WeaponImpl>>() {
        });
        Weapon currentWeapon = mapper.treeToValue(node.get("currentWeapon"), WeaponImpl.class);

        String attackStrategyName = node.get("attackStrategy").asText();
        AttackStrategy attackStrategy;

        if (attackStrategyName.equals("SingleTargetAttack")) {
            attackStrategy = new SingleTargetAttack();
        } else {
            attackStrategy = new AreaAttack();
        }

        String targetSelectionStrategyName = node.get("targetSelectionStrategy").asText();
        TargetSelectionStrategy targetSelectionStrategy;

        if (targetSelectionStrategyName.equals("DistanceBasedTargetSelection")) {
            targetSelectionStrategy = new DistanceBasedTargetSelection();
        } else {
            targetSelectionStrategy = new DistanceBasedTargetSelection();
        }

        if (towerClass.equals(BasicTower.class)) {
            Tower tower = new BasicTower(id, name, type, imgPath, position2d, direction2d, cost, level, range, weapons,
                    currentWeapon, attackStrategy, targetSelectionStrategy);
            return (T) tower;
        }
        return null;
    }
}
