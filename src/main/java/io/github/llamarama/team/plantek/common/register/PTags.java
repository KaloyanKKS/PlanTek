package io.github.llamarama.team.plantek.common.register;

import io.github.llamarama.team.plantek.common.util.IdBuilder;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;

public class PTags {

    public static void init() {
        Blocks.init();
    }

    private static <E> Tag<E> register(String path, TagFactory<E> factory) {
        return factory.create(IdBuilder.mod(path));
    }

    public static final class Blocks {

        public static final Tag<Block> DESERT_CROP = register("desert_crops", TagFactory.BLOCK);
        public static final Tag<Block> FOREST_CROP = register("forest_crops", TagFactory.BLOCK);
        public static final Tag<Block> MOUNTAINS_CROP = register("mountains_crops", TagFactory.BLOCK);
        public static final Tag<Block> PLAINS_CROP = register("plains_crops", TagFactory.BLOCK);

        public static void init() {

        }

    }

}
