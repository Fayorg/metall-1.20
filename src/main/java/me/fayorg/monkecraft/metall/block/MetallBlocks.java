package me.fayorg.monkecraft.metall.block;

import me.fayorg.monkecraft.metall.Metall;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MetallBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Metall.MODID);

    public static final RegistryObject<Block> FERTILIZER_BLOCK = BLOCKS.register("fertilizer_block", FertilizerBlock::new);

}
