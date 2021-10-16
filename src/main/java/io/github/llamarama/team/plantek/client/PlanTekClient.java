package io.github.llamarama.team.plantek.client;

import io.github.llamarama.team.plantek.client.render.entity.AntEntityRenderer;
import io.github.llamarama.team.plantek.client.render.entity.model.AntEntityModel;
import io.github.llamarama.team.plantek.common.register.PEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class PlanTekClient implements ClientModInitializer {

    public static final EntityModelLayer ANT_RENDER_LAYER = new EntityModelLayer(new Identifier("plantek", "ant"), "ant_render_layer");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(PEntities.ANT, AntEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ANT_RENDER_LAYER, AntEntityModel::getTexturedModelData);
    }
}
