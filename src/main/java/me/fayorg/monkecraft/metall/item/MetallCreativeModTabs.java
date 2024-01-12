package me.fayorg.monkecraft.metall.item;

import me.fayorg.monkecraft.metall.api.EnergyUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MetallCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "metall");

    public static final RegistryObject<CreativeModeTab> METALL_TAB = CREATIVE_MODE_TABS.register("metall", () -> CreativeModeTab.builder().icon(() -> new ItemStack(MetallItems.PIEROGI.get())).title(Component.translatable("itemGroup.metall")).displayItems((pParameters, pOutput) -> {
        // Tools
        pOutput.accept(MetallItems.SLINGSHOT.get());
        pOutput.accept(setItemStackMaxCharge(MetallItems.INVISIBLE_GOGGLES.get()));

        // Armors
        pOutput.accept(MetallItems.SLIME_BOOTS.get());

        // Foods
        pOutput.accept(MetallItems.CHOCOLATE.get());
        pOutput.accept(MetallItems.DOUGH.get());
        pOutput.accept(MetallItems.FLOUR.get());

        ItemStack pierogi = new ItemStack(MetallItems.PIEROGI.get());
        PierogiItem.setFoodProperties(pierogi, 6, 1.0F, false);
        pOutput.accept(pierogi);

        // Blocks
        pOutput.accept(MetallItems.FERTILIZER_BLOCK_ITEM.get());
    }).build());

    private static ItemStack setItemStackMaxCharge(ItemLike item) {
        ItemStack stack = new ItemStack(item);
        EnergyUtil.setFull(stack);
        return stack;
    }


}
