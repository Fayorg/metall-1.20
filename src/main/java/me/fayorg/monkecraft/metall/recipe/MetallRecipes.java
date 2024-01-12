package me.fayorg.monkecraft.metall.recipe;

import me.fayorg.monkecraft.metall.Metall;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MetallRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Metall.MODID);

    public static final RegistryObject<RecipeSerializer<?>> PIEROGI_RECIPE = RECIPES.register("pierogi_recipe", () -> PierogiRecipe.SERIALIZER);

}
