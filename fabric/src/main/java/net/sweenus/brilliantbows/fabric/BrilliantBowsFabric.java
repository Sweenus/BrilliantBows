package net.sweenus.brilliantbows.fabric;

import net.fabricmc.api.ModInitializer;
import net.sweenus.brilliantbows.BrilliantBows;

public class BrilliantBowsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BrilliantBows.init();
    }
}