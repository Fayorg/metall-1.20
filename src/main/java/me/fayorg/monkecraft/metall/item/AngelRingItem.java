package me.fayorg.monkecraft.metall.item;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
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
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class AngelRingItem extends Item implements ICurioItem {

    private final int ENERGY_PER_TICK = 20;
    private final int MAX_ENERGY = 3600000; // 20 * 60 * 60 * 2.5 * ENERGY_PER_TICK = 3600000 -> 2.5 hours of flight
    public AngelRingItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        EnergyItem energyItem = new EnergyItem(stack, MAX_ENERGY);

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
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if(!(slotContext.entity() instanceof Player player)) return;
        if(player.getAbilities().instabuild) return;
        if(player.level().isClientSide) return;
        IEnergyStorage energy = stack.getCapability(ForgeCapabilities.ENERGY, null).orElse(null);
        if(energy.getEnergyStored() > 0) {
            enableFlight(player);
            if(player.getAbilities().flying) {
                energy.extractEnergy(ENERGY_PER_TICK, false);
            }
        } else {
            disableFlight(player);
        }
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public void onEquipFromUse(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().playSound(SoundEvents.ARMOR_EQUIP_ELYTRA, 1.0F, 1.0F);
    }

    private void enableFlight(Player player) {
        player.getAbilities().mayfly = true;
        player.onUpdateAbilities();
    }

    private void disableFlight(Player player) {
        player.getAbilities().mayfly = false;
        player.getAbilities().flying = false;
        player.onUpdateAbilities();
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
        pTooltipComponents.add(Component.literal("Energy: " + energyStorage.getEnergyStored() / 1000000 + " MFE /" + energyStorage.getMaxEnergyStored() / 1000000 + " MFE"));
        pTooltipComponents.add(Component.literal("Flight time: " + (energyStorage.getEnergyStored() / ENERGY_PER_TICK / 20) + " seconds"));
        pTooltipComponents.add(Component.literal("Energy Usage: " + ENERGY_PER_TICK + " FE/tick"));
    }
}
