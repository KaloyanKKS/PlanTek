package io.github.llamarama.team.plantek.mixin;

import io.github.llamarama.team.plantek.common.register.PBlocks;
import io.github.llamarama.team.plantek.common.register.PTags;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.LightType;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(SpreadableBlock.class)
public abstract class MixinSpreadableBlock extends SnowyBlock {

    protected MixinSpreadableBlock(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "randomTick", cancellable = true)
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if ((random.nextInt(50) + 1) % (random.nextInt(50) + 1) == 0 && world.isNight()) {
            if (world.getBlockState(pos).isOf(Blocks.GRASS_BLOCK) && world.getBlockState(pos.up()).isOf(Blocks.GRASS)) {
                world.setBlockState(pos, PBlocks.LOOSE_SOIL.getDefaultState());
            }
        }
    }
}
