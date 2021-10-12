package io.github.llamarama.team.plantek.common.util;

import io.github.llamarama.team.plantek.PlanTek;
import net.minecraft.util.Identifier;

public final class IdBuilder {

    public static Identifier mod(String path) {
        return new Identifier(PlanTek.MODID, path);
    }

}
