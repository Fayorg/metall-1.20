package me.fayorg.monkecraft.metall.api;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyUtil {

    public static int getMaxEnergyStored(ItemStack stack) {
        return stack.getCapability(ForgeCapabilities.ENERGY).map(IEnergyStorage::getMaxEnergyStored).orElse(0);
    }

    public static int getEnergyStored(ItemStack stack) {
        return stack.getCapability(ForgeCapabilities.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    public static boolean hasEnergy(ItemStack stack, int amount) {
        return stack.getCapability(ForgeCapabilities.ENERGY).map(storage -> storage.getEnergyStored() >= amount).orElse(false);
    }

    public static boolean hasCapability(ItemStack stack) {
        return stack.getCapability(ForgeCapabilities.ENERGY).isPresent();
    }

    public static void setFull(ItemStack stack) {
        stack.getCapability(ForgeCapabilities.ENERGY).ifPresent(energyStorage -> energyStorage.receiveEnergy(energyStorage.getMaxEnergyStored(), false));
    }

    public static void setEmpty(ItemStack stack) {
        stack.getCapability(ForgeCapabilities.ENERGY).ifPresent(energyStorage -> energyStorage.extractEnergy(energyStorage.getEnergyStored(), false));
    }

    public static void set(ItemStack stack, int energy) {
        stack.getCapability(ForgeCapabilities.ENERGY).ifPresent(energyStorage -> {
            int delta = energy - energyStorage.getEnergyStored();
            if (delta < 0) {
                energyStorage.extractEnergy(-delta, false);
            } else {
                energyStorage.receiveEnergy(delta, false);
            }
        });
    }

    public static int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate) {
        return stack.getCapability(ForgeCapabilities.ENERGY).map(energyStorage -> energyStorage.receiveEnergy(maxReceive, simulate)).orElse(0);
    }

    public static int extractEnergy(ItemStack stack, int maxExtract, boolean simulate) {
        return stack.getCapability(ForgeCapabilities.ENERGY).map(energyStorage -> energyStorage.extractEnergy(maxExtract, simulate)).orElse(0);
    }

}
