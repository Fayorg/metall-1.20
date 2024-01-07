package me.fayorg.monkecraft.metall.item;

import me.fayorg.monkecraft.metall.Metall;
import me.fayorg.monkecraft.metall.block.MetallBlocks;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MetallItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Metall.MODID);

    public static final RegistryObject<Item> CHOCOLATE = ITEMS.register("chocolate", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(2).saturationMod(0.1F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 2, false, false), 1.0f).build())));
    public static final RegistryObject<Item> PIEROGI = ITEMS.register("pierogi", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(1.0F).build())));
    public static final RegistryObject<Item> SLINGSHOT = ITEMS.register("slingshot", SlingshotItem::new);
    public static final RegistryObject<Item> SLIME_BOOTS = ITEMS.register("slime_boots", () -> new SlimeArmor(ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> INVISIBLE_GOGGLES = ITEMS.register("invisible_goggle", InvisibleGoggleItem::new);
    // public static final RegistryObject<Item> ANGEL_RING = ITEMS.register("angel_ring", AngelRingItem::new);

    public static final RegistryObject<Item> FERTILIZER_BLOCK_ITEM = ITEMS.register("fertilizer_block", () -> new BlockItem(MetallBlocks.FERTILIZER_BLOCK.get(), new Item.Properties()));
}
