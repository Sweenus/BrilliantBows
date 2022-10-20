package net.sweenus.brilliantbows.fabric;

import net.sweenus.brilliantbows.BrilliantBows;
import net.fabricmc.api.ModInitializer;

public class BrilliantBowsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BrilliantBows.init();
    }
}