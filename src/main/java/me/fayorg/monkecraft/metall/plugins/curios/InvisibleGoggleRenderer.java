package me.fayorg.monkecraft.metall.plugins.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import me.fayorg.monkecraft.metall.client.ClientHandler;
import me.fayorg.monkecraft.metall.client.model.gadget.InvisibleGoggleModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class InvisibleGoggleRenderer implements ICurioRenderer {

    protected InvisibleGoggleModel model;
    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack itemStack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource multiBufferSource, int i, float v, float v1, float v2, float v3, float v4, float v5) {
        if(model == null) {
            model = new InvisibleGoggleModel(Minecraft.getInstance().getEntityModels().bakeLayer(ClientHandler.INVISIBLE_GOGGLE_LAYER));
        }
        ICurioRenderer.followBodyRotations(slotContext.entity(), model);
        ICurioRenderer.followHeadRotations(slotContext.entity(), model.getRoot());
        model.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(model.getTexture())), i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
