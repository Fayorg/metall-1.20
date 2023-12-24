package me.fayorg.monkecraft.metall;

import com.mojang.logging.LogUtils;
import me.fayorg.monkecraft.metall.block.MetallBlocks;
import me.fayorg.monkecraft.metall.datagen.DataGenerator;
import me.fayorg.monkecraft.metall.events.FallEvent;
import me.fayorg.monkecraft.metall.events.PlayerTickEvent;
import me.fayorg.monkecraft.metall.item.MetallCreativeModTabs;
import me.fayorg.monkecraft.metall.item.MetallItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Metall.MODID)
public class Metall {
    public static final String MODID = "metall";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Metall() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(DataGenerator::gatherData);
        modEventBus.addListener(this::onBuildContent);
        modEventBus.addListener(this::enqueueIMC);

        MetallItems.ITEMS.register(modEventBus);
        MetallCreativeModTabs.CREATIVE_MODE_TABS.register(modEventBus);
        MetallBlocks.BLOCKS.register(modEventBus);

        MinecraftForge.EVENT_BUS.addListener(FallEvent::onFallEvent);
        MinecraftForge.EVENT_BUS.addListener(FallEvent::onPlayerFlyFallEvent);
        MinecraftForge.EVENT_BUS.addListener(PlayerTickEvent::onPlayerTick);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        // ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SuppressWarnings("deprecation")
    private void enqueueIMC(final InterModEnqueueEvent event) {
        // TODO: Find the correct way to do it in 1.20.1
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().build());
    }

    public void onBuildContent(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        if(tab.equals(CreativeModeTabs.COMBAT)) {
            event.accept(MetallItems.SLINGSHOT);
            event.accept(MetallItems.SLIME_BOOTS);
        }
    }
}
