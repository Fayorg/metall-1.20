package me.fayorg.monkecraft.metall;

import com.mojang.logging.LogUtils;
import me.fayorg.monkecraft.metall.datagen.DataGenerator;
import me.fayorg.monkecraft.metall.events.FallEvent;
import me.fayorg.monkecraft.metall.events.PlayerTickEvent;
import me.fayorg.monkecraft.metall.item.MetallItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Metall.MODID)
public class Metall {
    public static final String MODID = "metall";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Metall() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(DataGenerator::gatherData);

        MetallItems.ITEMS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(FallEvent::onFallEvent);
        MinecraftForge.EVENT_BUS.addListener(FallEvent::onPlayerFlyFallEvent);
        MinecraftForge.EVENT_BUS.addListener(PlayerTickEvent::onPlayerTick);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        // ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
