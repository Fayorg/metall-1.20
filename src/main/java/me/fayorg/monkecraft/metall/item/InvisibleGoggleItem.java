package me.fayorg.monkecraft.metall.item;

import me.fayorg.monkecraft.metall.api.EnergyUtil;
import me.fayorg.monkecraft.metall.api.item.EnergizedItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class InvisibleGoggleItem extends EnergizedItem implements ICurioItem {
    public InvisibleGoggleItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 70000, 500, 500, 0);
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public void onEquipFromUse(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().playSound(SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 1.0F);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if(EnergyUtil.hasCapability(stack) && EnergyUtil.hasEnergy(stack, 1)) {
            EnergyUtil.extractEnergy(stack, 1, false);
        }
    }
}
