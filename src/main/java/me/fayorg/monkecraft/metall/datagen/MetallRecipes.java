package me.fayorg.monkecraft.metall.datagen;

import me.fayorg.monkecraft.metall.item.MetallItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class MetallRecipes extends RecipeProvider implements IConditionBuilder {
    public MetallRecipes(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MetallItems.SLIME_BOOTS.get()).pattern("S S").pattern("S S").define('S', Tags.Items.SLIMEBALLS).unlockedBy(getHasName(Items.SLIME_BALL), has(Tags.Items.SLIMEBALLS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MetallItems.SLINGSHOT.get()).pattern("SBS").pattern("L L").pattern(" L ").define('S', Tags.Items.STRING).define('B', Items.SLIME_BLOCK).define('L', Tags.Items.SLIMEBALLS).unlockedBy(getHasName(Items.SLIME_BALL), has(Tags.Items.SLIMEBALLS)).save(consumer);
    }
}
