package me.fayorg.monkecraft.metall.api.item;

import me.fayorg.monkecraft.metall.api.MetallCapabilityProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.extensions.IForgeItem;
import org.jetbrains.annotations.Nullable;

public interface IMetallCapabilityProvider extends IForgeItem {

    @Override
    @Nullable
    default ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return initCapabilities(stack, nbt, new MetallCapabilityProvider());
    }

    @Nullable
    MetallCapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt, MetallCapabilityProvider provider);
}
