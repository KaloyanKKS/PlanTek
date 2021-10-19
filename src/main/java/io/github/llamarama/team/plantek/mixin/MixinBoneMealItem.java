package io.github.llamarama.team.plantek.mixin;

import io.github.llamarama.team.plantek.block.RootBlock;
import io.github.llamarama.team.plantek.common.register.PBlocks;
import io.github.llamarama.team.plantek.common.register.PTags;
import net.minecraft.block.*;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BoneMealItem.class)
public abstract class MixinBoneMealItem extends Item {

    @Shadow
    public static void createParticles(WorldAccess world, BlockPos pos, int count) {
    }

    protected MixinBoneMealItem(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        Random random = new Random();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        if (world.getBlockState(pos).isOf(Blocks.HANGING_ROOTS)) {
            createParticles(world, pos, random.nextInt(100));
            if (random.nextInt(10) == 0) {
                world.setBlockState(pos, PBlocks.ROOT_BLOCK.getDefaultState().with(RootBlock.LIT, false));
            }
        }

    }
}
