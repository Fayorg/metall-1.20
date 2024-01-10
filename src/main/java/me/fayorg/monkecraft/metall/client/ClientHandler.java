package me.fayorg.monkecraft.metall.client;

import me.fayorg.monkecraft.metall.Metall;
import me.fayorg.monkecraft.metall.client.model.gadget.InvisibleGoggleModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ClientHandler {

    public static final ModelLayerLocation INVISIBLE_GOGGLE_LAYER = new ModelLayerLocation(new ResourceLocation(Metall.MODID, "invisible_goggle"), "main");
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(INVISIBLE_GOGGLE_LAYER, InvisibleGoggleModel::createLayer);
    }

}
