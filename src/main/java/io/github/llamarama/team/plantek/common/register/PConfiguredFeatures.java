package io.github.llamarama.team.plantek.common.register;

import io.github.llamarama.team.plantek.block.RootBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

public class PConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> ROOTS = register("roots", Feature.SIMPLE_BLOCK.configure(
            new SimpleBlockFeatureConfig(
                    new WeightedBlockStateProvider(pool()
                            .add(Blocks.ROOTED_DIRT.getDefaultState(), 10).add(PBlocks.ROOT_BLOCK.getDefaultState().with(RootBlock.LIT, false), 7)
                            .add(Blocks.MOSS_CARPET.getDefaultState(), 25).add(Blocks.DIRT.getDefaultState(), 50)
                            .add(Blocks.ROOTED_DIRT.getDefaultState(), 10)))));

    public static final ConfiguredFeature<?, ?> ROOT_SPREAD = register("root_spread", Feature.VEGETATION_PATCH.configure(
            new VegetationPatchFeatureConfig(BlockTags.MOSS_REPLACEABLE.getId(), new SimpleBlockStateProvider(PBlocks.ROOT_BLOCK.getDefaultState().with(RootBlock.LIT, false)), () -> ROOTS,
                    VerticalSurfaceType.FLOOR, ConstantIntProvider.create(1), 0.0F, 5, 0.6F, UniformIntProvider.create(1, 2), 0.75F)));

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
    }

    private static DataPool.Builder<BlockState> pool() {
        return DataPool.builder();
    }

    public static void init() {
        new PConfiguredFeatures();
    }
}


