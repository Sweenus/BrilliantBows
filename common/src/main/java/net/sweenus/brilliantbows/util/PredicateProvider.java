package net.sweenus.brilliantbows.util;


import dev.architectury.registry.item.ItemPropertiesRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.sweenus.brilliantbows.registry.ItemsRegistry;

public class PredicateProvider {

    public static void registerModModels() {
        registerBow(ItemsRegistry.LONGBOW.get(), 80f);
        registerBow(ItemsRegistry.TRIBOW.get(), 20f);
        registerBow(ItemsRegistry.RAINBOW.get(), 20f);
        registerBow(ItemsRegistry.CUSTOM_2_BOW.get(), 20f);

    }

    /*
    public static void registerBow(Item bow) {
        ModelPredicateProviderRegistry.register(bow, new Identifier("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0f;
                    }
                    if (entity.getActiveItem() != stack) {
                        return 0.0f;
                    }
                    return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0f;
                });

        ModelPredicateProviderRegistry.register(bow, new Identifier("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem()
                        && entity.getActiveItem() == stack ? 1.0f : 0.0f);
    }
}

 */
    public static void registerBow(Item bow, Float pullspeed) {
        ItemPropertiesRegistry.register(bow, new Identifier("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0f;
                    }
                    if (entity.getActiveItem() != stack) {
                        return 0.0f;
                    }
                    return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / pullspeed; // Default pullspeed is 20.0f
                });

        ItemPropertiesRegistry.register(bow, new Identifier("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem()
                        && entity.getActiveItem() == stack ? 1.0f : 0.0f);
    }
}

