package me.fayorg.monkecraft.metall.item;

import me.fayorg.monkecraft.metall.Metall;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

public class SlimeArmorMaterial implements ArmorMaterial {

    public static final SlimeArmorMaterial INSTANCE = new SlimeArmorMaterial();
    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        // 150 for boots, 0 for everything else since we don't have any other armor pieces
        return type == ArmorItem.Type.BOOTS ? 150 : 0;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return type == ArmorItem.Type.BOOTS ? 1 : 0;
    }

    @Override
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public SoundEvent getEquipSound() {
        // TODO: Change this to a slime sound
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(Tags.Items.SLIMEBALLS);
    }

    @Override
    public String getName() {
        return Metall.MODID + ":slime";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
