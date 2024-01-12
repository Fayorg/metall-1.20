package me.fayorg.monkecraft.metall.item;

import me.fayorg.monkecraft.metall.api.item.VariableFoodItem;
import net.minecraft.world.food.FoodProperties;
public class PierogiItem extends VariableFoodItem {
    public PierogiItem(Properties pProperties) {
        super(pProperties.food(new FoodProperties.Builder().build()));
    }
}
