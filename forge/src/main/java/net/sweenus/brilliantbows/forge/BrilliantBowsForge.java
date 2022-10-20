package net.sweenus.brilliantbows.forge;

import dev.architectury.platform.forge.EventBuses;
import net.sweenus.brilliantbows.BrilliantBows;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BrilliantBows.MOD_ID)
public class BrilliantBowsForge {
    public BrilliantBowsForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(BrilliantBows.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        BrilliantBows.init();
    }
}