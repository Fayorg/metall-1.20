package me.fayorg.monkecraft.metall.event;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.fayorg.monkecraft.metall.item.InvisibleGoggleItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.awt.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class RenderPlayer {

    @SubscribeEvent(receiveCanceled = true)
    public static void renderPlayer(RenderPlayerEvent.Pre event) {
        if(event.getEntity() == Minecraft.getInstance().player) return;
        Player player = event.getEntity();
        ItemStack google = getInvisibleGoogleStack(Minecraft.getInstance().player);
        if(!google.isEmpty()) {
            VoxelShape shape = Shapes.create(player.getBoundingBox().inflate(0.1D));
            Color color = new Color(28, 152, 26);
            RenderSystem.lineWidth(Math.max(2.5F, (float) Minecraft.getInstance().getWindow().getWidth() / 1920.0F * 2.5F));
            VertexConsumer builder = event.getMultiBufferSource().getBuffer(RenderType.lines());
            LevelRenderer.renderLineBox(event.getPoseStack(), builder, shape.bounds().move(-player.getX(), -player.getY(), -player.getZ()), (float) color.getRed() / 255f, (float) color.getGreen() / 255f,(float) color.getBlue() / 255f, 1.0F);
            google.getCapability(ForgeCapabilities.ENERGY).ifPresent(energy ->
            {
                energy.extractEnergy(1, false);
            });
        }
    }

    private static ItemStack getInvisibleGoogleStack(Player player)
    {
        AtomicReference<ItemStack> goggle = new AtomicReference<>(ItemStack.EMPTY);
        LazyOptional<ICuriosItemHandler> optional = CuriosApi.getCuriosInventory(player);
        optional.ifPresent(itemHandler ->
        {
            Optional<ICurioStacksHandler> stacksOptional = itemHandler.getStacksHandler("head");
            stacksOptional.ifPresent(stacksHandler ->
            {
                ItemStack stack = stacksHandler.getStacks().getStackInSlot(0);
                if(stack.getItem() instanceof InvisibleGoggleItem)
                {
                    goggle.set(stack);
                }
            });
        });
        return goggle.get();
    }

}
