package io.github.llamarama.team.plantek.block;

import io.github.llamarama.team.plantek.common.register.PBlocks;
import io.github.llamarama.team.plantek.common.register.PEntities;
import io.github.llamarama.team.plantek.entity.AntEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class LooseSoilBlock extends Block {

    public LooseSoilBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            if (random.nextInt(50) == 0) {
                AntEntity ant = new AntEntity(PEntities.ANT, world);
                double jumpHeight = random.nextDouble() + 0.1;
                world.spawnEntity(ant);
                world.setBlockState(pos, Blocks.DIRT.getDefaultState());
                ant.setVelocity(0.0, jumpHeight, 0.0);
                ant.updatePosition(pos.getX(), pos.getY() + 1, pos.getZ());
                ant.getJumpControl().tick();
                ant.setPersistent();
            }
        }

        if (world.getBlockState(pos.up()).isOf(Blocks.WATER)) {
            world.setBlockState(pos, PBlocks.FERTILE_SOIL.getDefaultState());
            if (random.nextInt(20) == 0) {
                world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
            }
        }
        super.scheduledTick(state, world, pos, random);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getStackInHand(hand).isOf(Items.WATER_BUCKET)) {
            world.setBlockState(pos, PBlocks.FERTILE_SOIL.getDefaultState());
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
