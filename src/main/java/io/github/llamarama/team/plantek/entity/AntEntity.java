package io.github.llamarama.team.plantek.entity;

import com.google.common.collect.Lists;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.List;

public class AntEntity extends SpiderEntity {

    public AntEntity(EntityType<? extends SpiderEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.4F));
        this.goalSelector.add(4, new AntEntity.AttackGoal(this));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(2, new RevengeGoal(this));
        this.targetSelector.add(2, new AntEntity.TargetGoal<>(this, AnimalEntity.class));
        this.targetSelector.add(1, new AntEntity.StealFoodGoal());
        this.targetSelector.add(1, new AntEntity.StealCropsGoal());
    }
    static class AttackGoal extends MeleeAttackGoal {
        public AttackGoal(AntEntity antEntity) {
            super(antEntity, 1.0D, true);
        }

        public boolean canStart() {
            return super.canStart();
        }

        public boolean shouldContinue() {
            float f = this.mob.getBrightnessAtEyes();
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
                this.mob.setTarget(null);
                return false;
            } else {
                return super.shouldContinue();
            }
        }

        protected double getSquaredMaxAttackDistance(LivingEntity entity) {
            return 4.0F + entity.getWidth();
        }
    }

    static class TargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
        public TargetGoal(AntEntity antEntity, Class<T> class_) {
            super(antEntity, class_, true);
        }

        public boolean canStart() {
            float f = this.mob.getBrightnessAtEyes();
            return !(f >= 0.5F) && super.canStart();
        }
    }

    class StealFoodGoal extends Goal {

        private BlockPos targetPos;

        public StealFoodGoal() {
            this.setControls(EnumSet.of(Goal.Control.MOVE, Control.LOOK));
        }

        @Override
        public boolean canStart() {
            BlockPos eatPos = findFood();
            if (eatPos != null) {
                targetPos = eatPos;
                return true;
            }

            return false;
        }

        @Override
        public void tick() {
            if (squaredDistanceTo(targetPos.getX() + 0.5F, targetPos.getY() + 0.5F, targetPos.getZ() + 0.5F) > 2F) {
                getMoveControl().moveTo(targetPos.getX() + 0.1F, targetPos.getY() + 0.8F, targetPos.getZ() + 0.1F, 0.1F);
            }
            getLookControl().lookAt(targetPos.getX() + 1.0F, targetPos.getY() + 0.0F, targetPos.getZ() + 0.0F);
            BlockState state = world.getBlockState(targetPos);
            Block stateToBlock = state.getBlock();

            if (state.isOf(Blocks.CHEST)) {
                Inventory inventory = ((ChestBlock) stateToBlock).getInventory((ChestBlock) stateToBlock, state, world, targetPos, true);
                if (inventory != null) {
                    for (int i = 0; i < 27; i++) {
                        if (inventory.getStack(i).isFood()) {
                            inventory.getStack(i).decrement(1);
                            ItemScatterer.spawn(world, targetPos.getX(), targetPos.getY() + 1, targetPos.getZ(), inventory.getStack(i));
                        }
                    }
                }
            }
        }

        private BlockPos findFood() {
            BlockPos pos1 = getBlockPos();
            List<BlockPos> blocks = Lists.newArrayList();
            BlockPos.Mutable m = new BlockPos.Mutable(pos1.getX(), pos1.getY(), pos1.getZ());

            for (int x = pos1.getX() - 2; x < pos1.getX() + 2; x++) {
                for (int z = pos1.getZ() - 2; z < pos1.getZ() + 2; z++) {
                    m.set(x, pos1.getY(), z);
                    BlockState checkState = world.getBlockState(m);
                    if (checkState.isOf(Blocks.CHEST)) {
                        blocks.add(new BlockPos(m.getX(), m.getY(), m.getZ()));
                    }
                }
            }

            if (blocks.isEmpty()) {
                return null;
            }

            return blocks.get(world.random.nextInt(blocks.size()));
        }
    }
    class StealCropsGoal extends Goal {

        private BlockPos targetPos;

        public StealCropsGoal() {
            this.setControls(EnumSet.of(Goal.Control.MOVE, Control.LOOK));
        }

        @Override
        public boolean canStart() {
            BlockPos eatPos = findCrop();
            if (eatPos != null) {
                targetPos = eatPos;
                return true;
            }

            return false;
        }

        @Override
        public void tick() {
            if (squaredDistanceTo(targetPos.getX() + 0.5F, targetPos.getY() + 0.5F, targetPos.getZ() + 0.5F) > 2F) {
                getMoveControl().moveTo(targetPos.getX() + 0.1F, targetPos.getY() + 0.8F, targetPos.getZ() + 0.1F, 0.1F);
            }
            getLookControl().lookAt(targetPos.getX() + 1.0F, targetPos.getY() + 0.0F, targetPos.getZ() + 0.0F);
            BlockState state = world.getBlockState(targetPos);

            if (state.isOf(Blocks.WHEAT)) {
                world.breakBlock(targetPos, false);
                world.playSound(targetPos.getX(), targetPos.getY(), targetPos.getZ(), SoundEvents.ENTITY_PANDA_EAT, SoundCategory.HOSTILE, 1.0f, 1.0f, true);
                }
            }
        }

        private BlockPos findCrop() {
            BlockPos pos1 = getBlockPos();
            List<BlockPos> blocks = Lists.newArrayList();
            BlockPos.Mutable m = new BlockPos.Mutable(pos1.getX(), pos1.getY(), pos1.getZ());

            for (int x = pos1.getX() - 2; x < pos1.getX() + 2; x++) {
                for (int z = pos1.getZ() - 2; z < pos1.getZ() + 2; z++) {
                    m.set(x, pos1.getY(), z);
                    BlockState checkState = world.getBlockState(m);
                    if (checkState.isOf(Blocks.WHEAT)) {
                        blocks.add(new BlockPos(m.getX(), m.getY(), m.getZ()));
                    }
                }
            }

            if (blocks.isEmpty()) {
                return null;
            }

            return blocks.get(world.random.nextInt(blocks.size()));
    }
}