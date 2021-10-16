package io.github.llamarama.team.plantek.client.render.entity;

import io.github.llamarama.team.plantek.client.PlanTekClient;
import io.github.llamarama.team.plantek.client.render.entity.model.AntEntityModel;
import io.github.llamarama.team.plantek.entity.AntEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class AntEntityRenderer extends MobEntityRenderer<AntEntity, AntEntityModel> {
    public AntEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new AntEntityModel(context.getPart(PlanTekClient.ANT_RENDER_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(AntEntity entity) {
        return new Identifier("plantek", "textures/entity/ant/bark.png");
    }
}
