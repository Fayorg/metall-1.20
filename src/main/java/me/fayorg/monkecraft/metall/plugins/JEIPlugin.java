package me.fayorg.monkecraft.metall.plugins;

import me.fayorg.monkecraft.metall.Metall;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Metall.MODID, "jei_plugin");
    }
}
