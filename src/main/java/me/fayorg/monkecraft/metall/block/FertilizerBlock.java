package me.fayorg.monkecraft.metall.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class FertilizerBlock extends Block {
    public FertilizerBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.FARMLAND));
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if(pRandom.nextInt(6) != 0) return;
        if(pLevel.getBlockState(pPos.above()).getBlock() instanceof BonemealableBlock plant) {
            if(plant.isValidBonemealTarget(pLevel, pPos.above(), pLevel.getBlockState(pPos.above()), pLevel.isClientSide)) {
                plant.performBonemeal(pLevel, pRandom, pPos.above(), pLevel.getBlockState(pPos.above()));
                // pLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, pPos.getX() + .5, pPos.getY() + 1.5, pPos.getZ() + .5, 10, 0, 0, 0, 0.15);
            }
        }
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return plantable.getPlantType(world, pos) == PlantType.CROP;
    }
}
