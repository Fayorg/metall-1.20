package me.fayorg.monkecraft.metall.recipe;

import com.google.common.collect.Lists;
import me.fayorg.monkecraft.metall.api.item.VariableFoodItem;
import me.fayorg.monkecraft.metall.item.MetallItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PierogiRecipe extends CustomRecipe {

    public static RecipeSerializer<PierogiRecipe> SERIALIZER = new SimpleCraftingRecipeSerializer<>(PierogiRecipe::new);

    public PierogiRecipe(ResourceLocation pId, CraftingBookCategory pCategory) {
        super(pId, pCategory);
    }

    private boolean isFood(ItemStack item) {
        return item.getItem().isEdible();
    }

    private boolean matches(List<ItemStack> list) {
        return list.size() == 3;
    }

    private ItemStack getResult(List<ItemStack> list) {
        int nutrition = 1;
        float saturation = 0.0f;
        ItemStack result = new ItemStack(MetallItems.PIEROGI.get());
        for(ItemStack item : list) {
            if (item.getItem().isEdible()) {
                nutrition += item.getFoodProperties(null).getNutrition();
                saturation += item.getFoodProperties(null).getSaturationModifier();
            }
        }
        VariableFoodItem.setFoodProperties(result, nutrition, saturation, false);
        return result;
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        List<ItemStack> foods = Lists.newArrayList();
        int dough = 0;
        for (int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack itemstack = pContainer.getItem(i);
            if (!itemstack.isEmpty()) {
                if (itemstack.is(MetallItems.DOUGH.get())) {
                    ++dough;
                } else if (!isFood(itemstack)) {
                    return false;
                } else {
                    foods.add(itemstack);
                }
            }
        }
        return matches(foods) && dough == 1;
    }

    @Override
    public @NotNull ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        List<ItemStack> list = Lists.newArrayList();
        int dough = 0;
        for (int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack itemstack = pContainer.getItem(i);
            if (!itemstack.isEmpty()) {
                if (itemstack.is(MetallItems.DOUGH.get())) {
                    ++dough;
                } else if (!isFood(itemstack)) {
                    return ItemStack.EMPTY;
                } else {
                    list.add(itemstack);
                }
            }
        }
        if (dough > 1) {
            return ItemStack.EMPTY;
        }
        return getResult(list);
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 4;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }
}
