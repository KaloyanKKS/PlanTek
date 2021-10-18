package io.github.llamarama.team.plantek.common.register;

import io.github.llamarama.team.plantek.block.RootBlock;
import io.github.llamarama.team.plantek.common.util.IdBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.impl.content.registry.FlammableBlockRegistryImpl;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public final class PBlocks {

    private static final Map<String, Block> REGISTRY = new HashMap<>();

    public static final Block ROOT_BLOCK = registerDeco("roots", new RootBlock(AbstractBlock.Settings.of(Material.PLANT)
                    .breakInstantly()
                    .ticksRandomly()
                    .nonOpaque()
    ));

    private static <T extends Block> T registerDeco(String id, T block) {
        PItems.register(id, new BlockItem(block, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

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
