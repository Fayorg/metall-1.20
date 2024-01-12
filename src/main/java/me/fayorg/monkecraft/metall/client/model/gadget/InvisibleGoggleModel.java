package me.fayorg.monkecraft.metall.client.model.gadget;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Vector3f;

public class InvisibleGoggleModel extends HumanoidModel<LivingEntity> {

    protected final ResourceLocation TEXTURE = new ResourceLocation("metall", "textures/models/gadget/invisible_goggle.png");
    protected final ModelPart goggles;
    public InvisibleGoggleModel(ModelPart root) {
        super(root, RenderType::entityCutoutNoCull);
        goggles = root.getChild("main");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, 0.0F));

        PartDefinition head = main.addOrReplaceChild("head", CubeListBuilder.create().texOffs(10, 0).addBox(-3.5F, -1.0F, -4.5F, 7.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(3.5F, -1.0F, -4.05F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(10, 5).addBox(-3.5F, -1.75F, -4.05F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-4.5F, -1.0F, -4.05F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 18).addBox(-4.5F, -0.5F, -0.4F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.1F))
                .texOffs(18, 14).addBox(3.5F, -0.5F, -0.4F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.1F))
                .texOffs(10, 14).addBox(-3.45F, 0.75F, -4.25F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 5).addBox(0.45F, 0.75F, -4.25F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(3.25F, -0.5F, -3.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.25F, -0.5F, -3.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(10, 10).addBox(-3.5F, -1.0F, 3.6F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, float v, float v1, float v2, float v3) {
        this.getRoot().render(poseStack, vertexConsumer, i, i1, v, v1, v2, v3);
    }

    public ModelPart getRoot() {
        return goggles;
    }

    public ResourceLocation getTexture() {
        return TEXTURE;
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(head);
    }
}
