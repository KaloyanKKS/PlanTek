package io.github.llamarama.team.plantek.common.register;

import io.github.llamarama.team.plantek.common.util.IdBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public final class PBlocks {

    private static final Map<String, Block> REGISTRY = new HashMap<>();

    private static <T extends Block> T register(String id, T block) {
        PItems.register(id, new BlockItem(block, new FabricItemSettings()));

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
