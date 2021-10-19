package io.github.llamarama.team.plantek.client.render.entity;

import io.github.llamarama.team.plantek.client.PlanTekClient;
import io.github.llamarama.team.plantek.client.render.entity.model.AntEntityModel;
import io.github.llamarama.team.plantek.entity.AntEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

import java.util.Random;

public class AntEntityRenderer extends MobEntityRenderer<AntEntity, AntEntityModel> {

    public AntEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new AntEntityModel(context.getPart(PlanTekClient.ANT_RENDER_LAYER)), 0.5f);
        this.addFeature(new AntEyesFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(AntEntity entity) {
        return new Identifier("plantek", "textures/entity/ant/bark.png");
    }

    private class AntEyesFeatureRenderer extends EyesFeatureRenderer {

        private static final RenderLayer EYES = RenderLayer.getEyes(new Identifier("plantek", "textures/entity/ant/bark.png"));

        public AntEyesFeatureRenderer(FeatureRendererContext featureRendererContext) {
            super(featureRendererContext);
        }

        @Override
        public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Entity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
            Random random = new Random();
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.getEyesTexture());
            if (entity.getCustomName() != null) {
                String customName = entity.getCustomName().asString();
                float r = (((entity.age % ((50 * random.nextInt(4)) + 1)) / 99F) + (random.nextInt(2 % 150) / 99F)) + (((entity.age % 150) / 99F) + (random.nextInt(2 % 150) / 99F));
                float g = ((entity.age % ((50 * random.nextInt(4)) + 1)) / 99F) - (random.nextInt(2 % 150) / 99F) + ((entity.age % 150) / 99F) - (random.nextInt(2 % 150) / 99F);
                float b = ((entity.age % ((50 * random.nextInt(4)) + 1)) / 99F) + (random.nextInt(2 % 150) / 99F) + ((entity.age % 150) / 99F) + (random.nextInt(2 % 150) / 99F);

                if (customName.equals("Dancing Queen")) {
                    this.getContextModel().render(matrices, vertexConsumer, 15728640, OverlayTexture.DEFAULT_UV,
                            r, g, b, 1.0f);
                }
            }
        }

        @Override
        public RenderLayer getEyesTexture() {
            return EYES;
        }
    }
}


