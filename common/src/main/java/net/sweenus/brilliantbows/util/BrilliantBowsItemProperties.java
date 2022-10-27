package net.sweenus.brilliantbows.util;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.sweenus.brilliantbows.registry.ItemsRegistry;

public class BrilliantBowsItemProperties {
    public static void addBrilliantBowsItemProperties() {
        ClientLifecycleEvent.CLIENT_SETUP.register(instance -> register());

    }

    public static void register() {
        makeBows(ItemsRegistry.LONGBOW.get(), 20f);
        makeBows(ItemsRegistry.HEAVENSBOW.get(), 40f);
        makeBows(ItemsRegistry.RAINBOW.get(), 20f);
        makeBows(ItemsRegistry.TRIBOW.get(), 120f);
    }

    public static void makeBows(Item item, float drawSpeed) {
        ItemPropertiesRegistry.register(item, new Identifier("pull"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                int useTicks = itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft();
                return livingEntity.getActiveItem() != itemStack ? 0.0F : (float) useTicks / drawSpeed;
            }
        });
        ItemPropertiesRegistry.register(item, new Identifier("bowmagic"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                     int selection = itemStack.getOrCreateNbt().getInt("bowmagic_selection");
                     return livingEntity.getActiveItem() != itemStack ? 0.0F : (float) selection / 10;
            }
        });
        ItemPropertiesRegistry.register(item, new Identifier("pulling"), (itemStack, clientWorld, livingEntity, seed) ->
                livingEntity != null
                        && livingEntity.isUsingItem()
                        && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F);
    }
}
