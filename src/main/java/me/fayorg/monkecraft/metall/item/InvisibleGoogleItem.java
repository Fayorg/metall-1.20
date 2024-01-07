package me.fayorg.monkecraft.metall.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.awt.*;

public class InvisibleGoogleItem extends Item implements ICurioItem {
    public InvisibleGoogleItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.RARE));
    }



}
