package me.fayorg.monkecraft.metall.api.item;

import me.fayorg.monkecraft.metall.api.EnergyUtil;
import me.fayorg.monkecraft.metall.api.MetallCapabilityProvider;
import me.fayorg.monkecraft.metall.api.text.EnumComponentColor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnergizedItem extends Item implements IMetallCapabilityProvider {

    private final int capacity;
    private final int maxReceive;
    private final int maxExtract;
    private final int energy;

    public EnergizedItem(Properties pProperties, int capacity, int maxReceive, int maxExtract, int energy) {
        super(pProperties);
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = energy;
    }

    @Override
    public @Nullable MetallCapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt, MetallCapabilityProvider provider) {
        provider.add(ForgeCapabilities.ENERGY, LazyOptional.of(() -> new EnergyItem(stack, capacity, maxReceive, maxExtract, energy)));
        return provider;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return stack
                .getCapability(ForgeCapabilities.ENERGY)
                .map(energyStorage -> Math.round(energyStorage.getEnergyStored() * 13.0F / energyStorage.getMaxEnergyStored()))
                .orElse(0);
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return 0x3CFE9A;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        addStoredEnergy(pStack, pTooltipComponents, false);
    }

    // TODO: Improve display for big numbers and change to translatable components
    private void addStoredEnergy(@NotNull ItemStack stack, @NotNull List<Component> tooltip, boolean showMissingCap) {
        if(EnergyUtil.hasCapability(stack) || showMissingCap) {
            tooltip.add(Component.literal(EnumComponentColor.BRIGHT_GREEN + "Stored Energy: " + EnumComponentColor.GRAY + EnergyUtil.getEnergyStored(stack) + " FE/" + EnergyUtil.getMaxEnergyStored(stack) + " FE"));
        }
    }
}
