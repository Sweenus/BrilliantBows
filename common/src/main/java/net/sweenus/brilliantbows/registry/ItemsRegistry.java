package net.sweenus.brilliantbows.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.sweenus.brilliantbows.BrilliantBows;
import net.sweenus.brilliantbows.config.BrilliantBowsConfig;
import net.sweenus.brilliantbows.item.custom.CustomBow;
import net.sweenus.brilliantbows.item.custom.ExplosiveBow;
import net.sweenus.brilliantbows.item.custom.TrishotBow;

public class ItemsRegistry {

    static float wood_modifier = (int) BrilliantBowsConfig.getWeaponAttributes("iron_damage_modifier");
    static float iron_modifier = (int) BrilliantBowsConfig.getWeaponAttributes("iron_damage_modifier");
    static float gold_modifier = (int)BrilliantBowsConfig.getWeaponAttributes("gold_damage_modifier");
    static float diamond_modifier = (int)BrilliantBowsConfig.getWeaponAttributes("diamond_damage_modifier");
    static float netherite_modifier = (int)BrilliantBowsConfig.getWeaponAttributes("netherite_damage_modifier");
    static float runic_modifier = (int)BrilliantBowsConfig.getWeaponAttributes("runic_damage_modifier");

    static float wood_durability = 900;

    static float longbow_positive_modifier = BrilliantBowsConfig.getWeaponAttributes("longbow_positive_damage_modifier");
    static float longbow_negative_modifier = BrilliantBowsConfig.getWeaponAttributes("longbow_negative_damage_modifier");
    static float longbow_attackspeed = BrilliantBowsConfig.getWeaponAttributes("longbow_attackspeed");


    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(BrilliantBows.MOD_ID, Registry.ITEM_KEY);

    public static final RegistrySupplier<ExplosiveBow> LONGBOW = ITEM.register( "longbow", () ->
            new ExplosiveBow(new Item.Settings().group(BrilliantBows.BRILLIANTBOWS).rarity(Rarity.EPIC).maxDamage((int) wood_durability)));

    public static final RegistrySupplier<TrishotBow> TRIBOW = ITEM.register( "tribow", () ->
            new TrishotBow(new Item.Settings().group(BrilliantBows.BRILLIANTBOWS).rarity(Rarity.UNCOMMON).maxDamage((int) wood_durability)));


}