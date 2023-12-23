package me.fayorg.monkecraft.metall.datagen;

import me.fayorg.monkecraft.metall.Metall;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

public class DataGenerator {

    public static void gatherData(GatherDataEvent event) {

        net.minecraft.data.DataGenerator gen = event.getGenerator();
        ExistingFileHelper efh = event.getExistingFileHelper();

        gen.addProvider(event.includeClient(), new MetallItemModels(event.getGenerator().getPackOutput(), Metall.MODID, efh));

    }

}