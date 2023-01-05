package net.sweenus.brilliantbows.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.sweenus.brilliantbows.BrilliantBows;
import net.sweenus.brilliantbows.item.custom.*;

public class ItemsRegistry {

    static float wood_durability = 900;


    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(BrilliantBows.MOD_ID, Registry.ITEM_KEY);

    public static final RegistrySupplier<CustomBow> LONGBOW = ITEM.register( "longbow", () ->
            new CustomBow(new Item.Settings().group(BrilliantBows.BRILLIANTBOWS).rarity(Rarity.EPIC).maxDamage((int) wood_durability)));

}
