package me.fayorg.monkecraft.metall.client.model.gadget;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class InvisibleGoggleModel extends HumanoidModel {

    protected final ResourceLocation TEXTURE = new ResourceLocation("metall", "textures/models/gadget/invisible_goggle.png");
    protected final ModelPart goggles;
    public InvisibleGoggleModel(ModelPart root) {
        super(root);
        this.goggles = root.getChild("main");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(15, 11).mirror().addBox(-11.45F, -4.25F, 3.75F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(15, 17).addBox(-7.55F, -4.25F, 3.75F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(7, 13).addBox(-4.75F, -5.5F, 4.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(7, 13).addBox(-12.25F, -5.5F, 4.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(1, 13).mirror().addBox(-11.5F, -6.0F, 11.6F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(8.0F, 24.0F, -8.0F));

        PartDefinition visor = main.addOrReplaceChild("visor", CubeListBuilder.create().texOffs(0, 9).addBox(-11.5F, -6.0F, 3.5F, 7.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(15, 17).addBox(-4.5F, -6.0F, 3.95F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(15, 11).addBox(-11.5F, -6.75F, 3.95F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(11, 18).addBox(-12.5F, -6.0F, 3.95F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(4, 5).addBox(-12.5F, -5.5F, 7.6F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.1F))
                .texOffs(13, 19).mirror().addBox(-4.5F, -5.5F, 7.6F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

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

}
