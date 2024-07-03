package it.unibo.model.entities.defense.tower;

import java.io.IOException;
import java.util.Set;

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
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.defense.weapon.WeaponImpl;
import it.unibo.model.entities.defense.tower.attack.AreaAttack;

/**
 * Custom {@link Tower}'s JSON deserializer.
 * @param <T> type of tower
 */
public class TowerDeserializer<T extends Tower> extends StdDeserializer<T> {
    private static final long serialVersionUID = 1L;
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

    /**
     * Custom deserialize for tower.
     */
    @Override
    public T deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
        final JsonNode node = jp.getCodec().readTree(jp);

        final int id = node.get("id").asInt();
        final String name = node.get("name").asText();
        final String type = node.get("type").asText();
        final String imgPath = node.get("imgPath").asText();
        final Position2D position2d = mapper.treeToValue(node.get("position2d"), Position2D.class);
        final Vector2D direction2d = mapper.treeToValue(node.get("direction2d"), Vector2D.class);
        final int cost = node.get("cost").asInt();
        final int level = node.get("level").asInt();
        final int range = node.get("range").asInt();
        final Set<WeaponImpl> weapons = mapper.readValue(node.get("weapons").traverse(), 
                                                        new TypeReference<Set<WeaponImpl>>() { });
        final Weapon currentWeapon = mapper.treeToValue(node.get("currentWeapon"), WeaponImpl.class);

        final String attackStrategyName = node.get("attackStrategy").asText();
        final AttackStrategy attackStrategy;

        if ("SingleTargetAttack".equals(attackStrategyName)) {
            attackStrategy = new SingleTargetAttack();
        } else {
            attackStrategy = new AreaAttack();
        }

        if (towerClass.isAssignableFrom(BasicTower.class)) {
            return towerClass.cast(new BasicTower(id, name, type, imgPath, position2d, direction2d, cost, 
                level, range, weapons, currentWeapon, attackStrategy, new DistanceBasedTargetSelection()));
        }
        return null;
    }
}
