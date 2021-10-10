package io.github.llamarama.team.plantek.init;

import io.github.llamarama.team.plantek.PlanTek;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class PTags {
    public static final Tag<Block> DESERT_CROP = register("desert_crops", TagRegistry::block);
    public static final Tag<Block> FOREST_CROP = register("forest_crops", TagRegistry::block);
    public static final Tag<Block> MOUNTAINS_CROP = register("mountains_crops", TagRegistry::block);
    public static final Tag<Block> PLAINS_CROP = register("plains_crops", TagRegistry::block);

    public static void init() {
        new PBlocks();
    }

    private static <E>Tag<E> register(String path, Function<Identifier, Tag<E>> tags) {
        return tags.apply(new Identifier(PlanTek.MODID, path));
    }
}
