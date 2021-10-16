package io.github.llamarama.team.plantek.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.world.World;

public class AntEntity extends SpiderEntity {

    // TODO Add actual AI - this is temporary

    public AntEntity(EntityType<? extends SpiderEntity> entityType, World world) {
        super(entityType, world);
    }
}
