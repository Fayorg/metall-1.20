package me.fayorg.monkecraft.metall.datagen;

import me.fayorg.monkecraft.metall.Metall;
import me.fayorg.monkecraft.metall.block.MetallBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MetallBlockStates extends BlockStateProvider {
    public MetallBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Metall.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // simpleBlock(MetallBlocks.FERTILIZER_BLOCK.get());
    }
}
