package me.fayorg.monkecraft.metall.datagen;

import me.fayorg.monkecraft.metall.Metall;
import me.fayorg.monkecraft.metall.item.MetallItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class MetallItemModels extends ItemModelProvider {
    public MetallItemModels(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(MetallItems.CHOCOLATE);
        // simpleItem(MetallItems.PIEROGI);
        simpleItem(MetallItems.SLINGSHOT);
        simpleItem(MetallItems.SLIME_BOOTS);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated")).texture("layer0", new ResourceLocation(Metall.MODID, "item/" + item.getId().getPath()));
    }
}
