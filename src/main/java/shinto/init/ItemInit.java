package shinto.init;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit {
    public static final Item ITEM_GLOVES = new Item(new Item.Settings());

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("shinto", "magic_gloves"), ITEM_GLOVES);
    }
}
