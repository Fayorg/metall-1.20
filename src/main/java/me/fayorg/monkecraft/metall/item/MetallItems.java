package me.fayorg.monkecraft.metall.item;

import me.fayorg.monkecraft.metall.Metall;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MetallItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Metall.MODID);

    public static final RegistryObject<Item> CHOCOLATE = ITEMS.register("chocolate", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(2).saturationMod(0.1F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100), 1.0f).build())));

}
