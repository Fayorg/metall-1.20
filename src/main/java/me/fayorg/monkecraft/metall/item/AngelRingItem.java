package me.fayorg.monkecraft.metall.item;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AngelRingItem extends Item {
    public AngelRingItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        EnergyItem energyItem = new EnergyItem(stack, 1000000);

        return new ICapabilityProvider() {
            @Override
            public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
                if(capability == ForgeCapabilities.ENERGY) {
                    return LazyOptional.of(() -> energyItem).cast();
                }
                return LazyOptional.empty();
            }
        };
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(!(pEntity instanceof Player player)) return;
        if(pLevel.isClientSide) return;
        if(player.getAbilities().instabuild) return;
        IEnergyStorage energy = pStack.getCapability(ForgeCapabilities.ENERGY, null).orElse(null);

        if(energy.getEnergyStored() > 0) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
            energy.extractEnergy(1, false);
        } else {
            player.getAbilities().flying = false;
            player.getAbilities().mayfly = false;
            player.onUpdateAbilities();
        }
    }


    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        player.getAbilities().flying = false;
        player.getAbilities().mayfly = false;
        player.onUpdateAbilities();
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        IEnergyStorage energy = stack.getCapability(ForgeCapabilities.ENERGY, null).orElse(null);
        return (energy.getEnergyStored() < energy.getMaxEnergyStored());
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return stack.getCapability(ForgeCapabilities.ENERGY, null)
                .map(e -> Math.min(13 * e.getEnergyStored() / e.getMaxEnergyStored(), 13))
                .orElse(0);
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return 0x3CFE9A;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        IEnergyStorage energyStorage = pStack.getCapability(ForgeCapabilities.ENERGY).orElse(null);
        pTooltipComponents.add(Component.literal("Energy: " + energyStorage.getEnergyStored() + "/" + energyStorage.getMaxEnergyStored()));
    }
}
