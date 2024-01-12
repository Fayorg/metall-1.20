package me.fayorg.monkecraft.metall.datagen;

import me.fayorg.monkecraft.metall.Metall;
import me.fayorg.monkecraft.metall.block.MetallBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MetallBlockStateProvider extends BlockStateProvider {
    public MetallBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Metall.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(MetallBlocks.FERTILIZER_BLOCK.get(), new ModelFile.UncheckedModelFile(modLoc("block/fertilizer_block")));
    }
}
