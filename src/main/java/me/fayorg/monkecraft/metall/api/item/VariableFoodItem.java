package me.fayorg.monkecraft.metall.api.item;

import me.fayorg.monkecraft.metall.api.NBTKey;
import me.fayorg.monkecraft.metall.api.text.EnumComponentColor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VariableFoodItem extends Item {

    private final int DEFAULT_NUTRITION = 1;
    private final float DEFAULT_SATURATION = 0.0F;
    public VariableFoodItem(Properties pProperties) {
        super(pProperties.food(new FoodProperties.Builder().build()));
    }

    public static void setFoodProperties(ItemStack pStack, int pNutrition, float pSaturation, boolean pFastEating) {
        pStack.getOrCreateTag().putInt(NBTKey.NUTRITION, pNutrition);
        pStack.getOrCreateTag().putFloat(NBTKey.SATURATION, pSaturation);
        pStack.getOrCreateTag().putBoolean(NBTKey.FAST_EATING, pFastEating);
    }

    @Override
    public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        CompoundTag tag = stack.getOrCreateTag();
        FoodProperties.Builder builder = new FoodProperties.Builder();
        if (tag.contains(NBTKey.NUTRITION) && tag.contains(NBTKey.SATURATION) && tag.contains(NBTKey.FAST_EATING)) {
            int nutrition = tag.getInt(NBTKey.NUTRITION);
            float saturation = tag.getFloat(NBTKey.SATURATION);
            boolean fastEating = tag.getBoolean(NBTKey.FAST_EATING);
            builder.nutrition(nutrition).saturationMod(saturation);
            if(fastEating) {
                builder.fast();
            }
            return builder.build();
        }
        return builder.nutrition(DEFAULT_NUTRITION).saturationMod(DEFAULT_SATURATION).build();
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        CompoundTag tag = pStack.getOrCreateTag();
        if (tag.contains(NBTKey.NUTRITION) && tag.contains(NBTKey.SATURATION) && tag.contains(NBTKey.FAST_EATING)) {
            int nutrition = tag.getInt(NBTKey.NUTRITION);
            float saturation = tag.getFloat(NBTKey.SATURATION);
            pTooltipComponents.add(Component.literal(EnumComponentColor.BRIGHT_GREEN + "Nutrition: " + EnumComponentColor.GRAY + nutrition));
            pTooltipComponents.add(Component.literal(EnumComponentColor.PURPLE + "Saturation: " + EnumComponentColor.GRAY + saturation));
        }
    }
}
