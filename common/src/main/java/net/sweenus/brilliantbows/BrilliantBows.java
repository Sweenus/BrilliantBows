package net.sweenus.brilliantbows;

import com.google.gson.JsonObject;
import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.sweenus.brilliantbows.config.BrilliantBowsConfig;
import net.sweenus.brilliantbows.config.Config;
import net.sweenus.brilliantbows.registry.ItemsRegistry;
import net.sweenus.brilliantbows.registry.SoundRegistry;
import net.sweenus.brilliantbows.util.BrilliantBowsItemProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class BrilliantBows {
    public static final String MOD_ID = "brilliantbows";

    public static final ItemGroup BRILLIANTBOWS = CreativeTabRegistry.create(new Identifier(MOD_ID, "brilliantbows"), () ->
            new ItemStack(ItemsRegistry.LONGBOW.get()));

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {

        //CONFIG

        BrilliantBowsConfig.init();

        String defaultConfig = """
                {
                  "regen_brilliantbows_config_file": false
                }""";

        File configFile = Config.createFile("config/brilliantbows/backupconfig.json", defaultConfig, false);
        JsonObject json = Config.getJsonObject(Config.readFile(configFile));

        BrilliantBowsConfig.generateConfigs(json == null || !json.has("regen_brilliantbows_config_file") || json.get("regen_brilliantbows_config_file").getAsBoolean());
        BrilliantBowsConfig.loadConfig();

        ItemsRegistry.ITEM.register();
        SoundRegistry.SOUND.register();
        BrilliantBowsItemProperties.addBrilliantBowsItemProperties();



    }
}