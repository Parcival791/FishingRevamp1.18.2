package net.parcival.fishingrevamp.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.parcival.fishingrevamp.FishingRevamp;
import org.lwjgl.system.CallbackI;

public class ModItems {

    public static final Item NET_ROD = registerItem("net_rod", new NetRodItem(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1).maxDamage(64)));
    public static final Item FISHING_NET = registerItem("fishing_net", new Item(new FabricItemSettings().group(ItemGroup.MISC).maxCount(64)));
    public static final Item REINFORCED_STRING = registerItem("reinforced_string", new Item(new FabricItemSettings().group(ItemGroup.MISC).maxCount(64)));
    public static final Item STRING_HOOK = registerItem("string_hook", new Item(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));



    private static Item registerItem ( String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(FishingRevamp.MOD_ID, name), item);
    }

    public static void registerModItems() {
        FishingRevamp.LOGGER.info("Registering ModItems for " + FishingRevamp.MOD_ID);
    }
}
