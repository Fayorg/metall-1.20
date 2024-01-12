package me.fayorg.monkecraft.metall.api.text;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

public enum EnumComponentColor {

    BLACK("§0", "Black", "black", new int[]{64, 64, 64}, DyeColor.BLACK),
    DARK_BLUE("§1", "Blue", "blue", new int[]{54, 107, 208}, DyeColor.BLUE),
    DARK_GREEN("§2", "Green", "green", new int[]{89, 193, 95}, DyeColor.GREEN),
    DARK_AQUA("§3", "Cyan", "cyan", new int[]{0, 243, 208}, DyeColor.CYAN),
    DARK_RED("§4", "Dark Red", "dark_red", new int[]{201, 7, 31}, MapColor.NETHER, null),
    PURPLE("§5", "Purple", "purple", new int[]{164, 96, 217}, DyeColor.PURPLE),
    ORANGE("§6", "Orange", "orange", new int[]{255, 161, 96}, DyeColor.ORANGE),
    GRAY("§7", "Light Gray", "light_gray", new int[]{207, 207, 207}, DyeColor.LIGHT_GRAY),
    DARK_GRAY("§8", "Gray", "gray", new int[]{122, 122, 122}, DyeColor.GRAY),
    INDIGO("§9", "Light Blue", "light_blue", new int[]{85, 158, 255}, DyeColor.LIGHT_BLUE),
    BRIGHT_GREEN("§a", "Lime", "lime", new int[]{117, 255, 137}, DyeColor.LIME),
    AQUA("§b", "Aqua", "aqua", new int[]{48, 255, 249}, MapColor.COLOR_LIGHT_BLUE, null),
    RED("§c", "Red", "red", new int[]{255, 56, 60}, DyeColor.RED),
    PINK("§d", "Magenta", "magenta", new int[]{213, 94, 203}, DyeColor.MAGENTA),
    YELLOW("§e", "Yellow", "yellow", new int[]{255, 221, 79}, DyeColor.YELLOW),
    WHITE("§f", "White", "white", new int[]{255, 255, 255}, DyeColor.WHITE),
    //Extras for dye-completeness
    BROWN("§6", "Brown", "brown", new int[]{161, 118, 73}, DyeColor.BROWN),
    BRIGHT_PINK("§d", "Pink", "pink", new int[]{255, 188, 196}, DyeColor.PINK);

    private static final EnumComponentColor[] COLORS = values();

    public final String code;

    private int[] rgbCode;
    private TextColor color;
    private final String englishName;
    private final String registryPrefix;
    @Nullable
    private final DyeColor dyeColor;
    private final MapColor mapColor;

    EnumComponentColor(String s, String englishName, String registryPrefix, int[] rgbCode, DyeColor dyeColor) {
        this(s, englishName, registryPrefix, rgbCode, dyeColor.getMapColor(), dyeColor);
    }

    EnumComponentColor(String code, String englishName, String registryPrefix, int[] rgbCode, MapColor mapColor, @Nullable DyeColor dyeColor) {
        this.code = code;
        this.englishName = englishName;
        this.dyeColor = dyeColor;
        this.registryPrefix = registryPrefix;
        this.mapColor = mapColor;
    }

    @Override
    public String toString() {
        return code;
    }
}
