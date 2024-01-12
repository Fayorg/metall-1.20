package me.fayorg.monkecraft.metall;

import me.fayorg.monkecraft.metall.block.MetallBlocks;
import me.fayorg.monkecraft.metall.client.ClientHandler;
import me.fayorg.monkecraft.metall.datagen.DataGenerator;
import me.fayorg.monkecraft.metall.event.FallEvent;
import me.fayorg.monkecraft.metall.event.PlayerTickEvent;
import me.fayorg.monkecraft.metall.event.RenderPlayer;
import me.fayorg.monkecraft.metall.item.MetallCreativeModTabs;
import me.fayorg.monkecraft.metall.item.MetallItems;
import me.fayorg.monkecraft.metall.plugin.curios.InvisibleGoggleRenderer;
import me.fayorg.monkecraft.metall.recipe.MetallRecipes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Metall.MODID)
public class Metall {
    public static final String MODID = "metall";

    public Metall() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(DataGenerator::gatherData);
        modEventBus.addListener(this::onBuildContent);
        modEventBus.addListener(this::clientSetup);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(ClientHandler::onRegisterLayers);
        });

        MetallItems.ITEMS.register(modEventBus);
        MetallCreativeModTabs.CREATIVE_MODE_TABS.register(modEventBus);
        MetallBlocks.BLOCKS.register(modEventBus);

        MetallRecipes.RECIPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.addListener(FallEvent::onFallEvent);
        MinecraftForge.EVENT_BUS.addListener(FallEvent::onPlayerFlyFallEvent);
        MinecraftForge.EVENT_BUS.addListener(PlayerTickEvent::onPlayerTick);
        MinecraftForge.EVENT_BUS.register(RenderPlayer.class);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        // ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public void clientSetup(final FMLClientSetupEvent event) {
        CuriosRendererRegistry.register(MetallItems.INVISIBLE_GOGGLES.get(), InvisibleGoggleRenderer::new);
    }

    public void onBuildContent(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        if(tab.equals(CreativeModeTabs.COMBAT)) {
            event.accept(MetallItems.SLINGSHOT);
            event.accept(MetallItems.SLIME_BOOTS);
        }
    }
}
