package io.github.llamarama.team.plantek.common.register;

import io.github.llamarama.team.plantek.common.util.IdBuilder;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public final class PItems {

    private static final Map<String, Item> REGISTRY = new HashMap<>();

    static <T extends Item> T register(String id, T item) {
        REGISTRY.put(id, item);

        return item;
    }

    public static void init() {
        REGISTRY.forEach((id, item) ->
                Registry.register(Registry.ITEM, IdBuilder.mod(id), item)
        );
    }

}
