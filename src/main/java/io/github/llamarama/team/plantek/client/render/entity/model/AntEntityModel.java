package io.github.llamarama.team.plantek.client.render.entity.model;

import io.github.llamarama.team.plantek.entity.AntEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;

public class AntEntityModel extends SinglePartEntityModel<AntEntity> {
    private final ModelPart root;

	public AntEntityModel(ModelPart root) {
        this.root = root;
		ModelPart thiccus_maximus = root.getChild("thiccus_maximus");
		ModelPart body = root.getChild("body");
		ModelPart head = root.getChild("head");
		ModelPart mandible1 = head.getChild("mandible1");
		ModelPart mandible2 = head.getChild("mandible2");
		ModelPart legs1 = root.getChild("legs1");
		ModelPart legs2 = root.getChild("legs2");
		ModelPart legs3 = root.getChild("legs3");
		ModelPart legs4 = root.getChild("legs4");
    }

    public static TexturedModelData getTexturedModelData() {

        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        ModelPartData thiccus_maximus = root.addChild(
		    "thiccus_maximus",
		    ModelPartBuilder.create()
		        .uv(0, 0)
		        .mirrored(false)
		        .cuboid(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 9.0F, new Dilation(-0.25F)),
		    ModelTransform.of(0.0F, 18.0F, 7.0F, 0.1745F, 0.0F, 0.0F)
		);

		ModelPartData body = root.addChild(
		    "body",
		    ModelPartBuilder.create()
		        .uv(0, 17)
		        .mirrored(false)
		        .cuboid(-3.0F, -7.0F, -4.0F, 6.0F, 5.0F, 10.0F, new Dilation(-0.25F)),
		    ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F)
		);

		ModelPartData head = root.addChild(
		    "head",
		    ModelPartBuilder.create()
		        .uv(26, 26)
		        .mirrored(false)
		        .cuboid(-4.0F, -7.0F, -8.0F, 8.0F, 5.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 13)
		        .mirrored(false)
		        .cuboid(-2.0F, -9.0F, -10.0F, 0.0F, 3.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0)
		        .mirrored(false)
		        .cuboid(2.0F, -9.0F, -10.0F, 0.0F, 3.0F, 4.0F, new Dilation(0.0F)),
		    ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F)
		);

		ModelPartData mandible1 = head.addChild(
		    "mandible1",
		    ModelPartBuilder.create()
		        .uv(22, 10)
		        .mirrored(false)
		        .cuboid(0.0F, -1.0F, -7.0F, 0.0F, 2.0F, 7.0F, new Dilation(0.0F))
		.uv(29, 17)
		        .mirrored(false)
		        .cuboid(0.0F, -1.0F, -7.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)),
		    ModelTransform.of(-4.0F, -4.0F, -6.0F, 0.0F, 0.0F, 0.0F)
		);

		ModelPartData mandible2 = head.addChild(
		    "mandible2",
		    ModelPartBuilder.create()
		        .uv(22, 12)
		        .mirrored(false)
		        .cuboid(0.0F, -1.0F, -7.0F, 0.0F, 2.0F, 7.0F, new Dilation(0.0F))
		.uv(31, 17)
		        .mirrored(false)
		        .cuboid(-2.0F, -1.0F, -7.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)),
		    ModelTransform.of(4.0F, -4.0F, -6.0F, 0.0F, 0.0F, 0.0F)
		);

		ModelPartData legs1 = root.addChild(
		    "legs1",
		    ModelPartBuilder.create()
		        .uv(23, 20)
		        .mirrored(false)
		        .cuboid(0.0F, -1.0F, -3.0F, 0.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(23, 20)
		        .mirrored(false)
		        .cuboid(0.0F, -1.0F, 1.0F, 0.0F, 6.0F, 1.0F, new Dilation(0.0F)),
		    ModelTransform.of(-2.0F, 21.0F, 1.0F, 0.0F, 0.0F, 0.7854F)
		);

		ModelPartData legs2 = root.addChild(
		    "legs2",
		    ModelPartBuilder.create()
		        .uv(23, 20)
		        .mirrored(false)
		        .cuboid(0.0F, -1.0F, 0.0F, 0.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(22, 20)
		        .mirrored(false)
		        .cuboid(0.0F, -1.0F, -2.0F, 0.0F, 6.0F, 1.0F, new Dilation(0.0F)),
		    ModelTransform.of(2.0F, 21.0F, 0.0F, 0.0F, 0.0F, -0.7854F)
		);

		ModelPartData legs3 = root.addChild(
		    "legs3",
		    ModelPartBuilder.create()
		        .uv(23, 20)
		        .mirrored(false)
		        .cuboid(0.0F, -1.0F, -2.0F, 0.0F, 6.0F, 1.0F, new Dilation(0.0F)),
		    ModelTransform.of(-2.0F, 21.0F, 2.0F, 0.0F, 0.0F, 0.7854F)
		);

		ModelPartData legs4 = root.addChild(
		    "legs4",
		    ModelPartBuilder.create()
		        .uv(23, 20)
		        .mirrored(false)
		        .cuboid(0.0F, -1.0F, -1.0F, 0.0F, 6.0F, 1.0F, new Dilation(0.0F)),
		    ModelTransform.of(2.0F, 21.0F, 3.0F, 0.0F, 0.0F, -0.7854F)
		);

        return TexturedModelData.of(data, 64, 64);
    }

    @Override
    public void setAngles(AntEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        // TODO Add cool animations
    }

	@Override
	public void animateModel(AntEntity entity, float limbAngle, float limbDistance, float tickDelta) {
		// TODO Add cool animations
	}

	@Override
    public ModelPart getPart() {
        return this.root;
    }

    
}