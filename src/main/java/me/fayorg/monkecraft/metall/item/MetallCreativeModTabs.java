package me.fayorg.monkecraft.metall.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MetallCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "metall");

    public static final RegistryObject<CreativeModeTab> METALL_TAB = CREATIVE_MODE_TABS.register("metall", () -> CreativeModeTab.builder().icon(() -> new ItemStack(MetallItems.SLINGSHOT.get())).title(Component.translatable("itemGroup.metall")).displayItems((pParameters, pOutput) -> {
        pOutput.accept(MetallItems.SLINGSHOT.get());
        pOutput.accept(MetallItems.CHOCOLATE.get());
        pOutput.accept(MetallItems.PIEROGI.get());
        pOutput.accept(MetallItems.SLIME_BOOTS.get());
    }).build());


}
