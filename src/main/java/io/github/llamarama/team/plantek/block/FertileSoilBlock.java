package io.github.llamarama.team.plantek.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class FertileSoilBlock extends Block {
    public FertileSoilBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBlockState(pos.up()).isIn(BlockTags.CROPS) && world.getBlockState(pos.down()).isOf(Blocks.AIR) && random.nextInt(100) == 0) {
            world.setBlockState(pos.down(), Blocks.HANGING_ROOTS.getDefaultState());
        }
    }
}
