package io.github.llamarama.team.plantek.common.register;

import io.github.llamarama.team.plantek.block.FertileSoilBlock;
import io.github.llamarama.team.plantek.block.LooseSoilBlock;
import io.github.llamarama.team.plantek.block.RootBlock;
import io.github.llamarama.team.plantek.common.util.IdBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public final class PBlocks {

    private static final Map<String, Block> REGISTRY = new HashMap<>();

    public static final Block ROOT_BLOCK = registerDeco("roots", new RootBlock(FabricBlockSettings.of(Material.PLANT)
            .breakInstantly()
            .ticksRandomly()
            .nonOpaque()
    ));

    public static final Block LOOSE_SOIL = registerBuild("loose_soil", new LooseSoilBlock(AbstractBlock.Settings.of(Material.SOIL)
            .ticksRandomly()
    ));

    public static final Block FERTILE_SOIL = registerBuild("fertile_soil", new FertileSoilBlock(AbstractBlock.Settings.of(Material.SOIL)
            .ticksRandomly()
    ));

    private static <T extends Block> T registerDeco(String id, T block) {
        PItems.register(id, new BlockItem(block, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

        return registerNoItem(id, block);
    }

    private static <T extends Block> T registerBuild(String id, T block) {
        PItems.register(id, new BlockItem(block, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        return registerNoItem(id, block);
    }

    private static <T extends Block> T registerNoItem(String id, T block) {
        REGISTRY.put(id, block);

        return block;
    }

    public static void init() {
        REGISTRY.forEach((id, item) ->
                Registry.register(Registry.BLOCK, IdBuilder.mod(id), item)
        );
    }
}
