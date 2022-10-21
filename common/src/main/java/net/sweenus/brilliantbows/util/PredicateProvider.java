package net.sweenus.brilliantbows.util;


import dev.architectury.registry.item.ItemPropertiesRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.sweenus.brilliantbows.registry.ItemsRegistry;

public class PredicateProvider {

    public static void registerModModels() {
        registerBow(ItemsRegistry.LONGBOW.get());
        registerBow(ItemsRegistry.TRIBOW.get());
        registerBow(ItemsRegistry.RAINBOW.get());
        registerBow(ItemsRegistry.CUSTOM_2_BOW.get());

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
    public static void registerBow(Item bow) {
        ItemPropertiesRegistry.register(bow, new Identifier("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0f;
                    }
                    if (entity.getActiveItem() != stack) {
                        return 0.0f;
                    }
                    return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0f;
                });

        ItemPropertiesRegistry.register(bow, new Identifier("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem()
                        && entity.getActiveItem() == stack ? 1.0f : 0.0f);
    }
}

