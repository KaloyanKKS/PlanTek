package io.github.llamarama.team.plantek.common.register;

import io.github.llamarama.team.plantek.entity.AntEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PEntities {
    // TODO Joe this is very crude, you can probably spice this up with a few methods
    public static final EntityType<AntEntity> ANT = Registry.register(Registry.ENTITY_TYPE, new Identifier("plantek", "ant"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AntEntity::new).dimensions(EntityDimensions.fixed(0.5f, 0.8f)).build()
    );

    public PEntities() {
        FabricDefaultAttributeRegistry.register(ANT, AntEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D));
    }

    public static void init() {
        new PEntities();
    }
}
