package net.sweenus.brilliantbows.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BrilliantBowsConfig {
    private static final HashMap<String, Boolean> BOOLEAN_OPTIONS = new LinkedHashMap<>();
    private static final HashMap<String, Float> GENERAL_OPTIONS = new LinkedHashMap<>();
    private static final HashMap<String, Float> FLOAT_OPTIONS = new LinkedHashMap<>();
    private static final HashMap<String, Float> WEAPON_OPTIONS = new LinkedHashMap<>();

    public static boolean getBooleanValue(String key) {
        if (!BOOLEAN_OPTIONS.containsKey(key)) {
            System.out.println(key);
        }
        return BOOLEAN_OPTIONS.getOrDefault(key, null);
    }

    public static float getGeneralSettings(String key) {
        if (!GENERAL_OPTIONS.containsKey(key)) {
            System.out.println(key);
        }
        return GENERAL_OPTIONS.getOrDefault(key, null);
    }

    public static float getFloatValue(String key) {
        if (!FLOAT_OPTIONS.containsKey(key)) {
            System.out.println(key);
        }
        return FLOAT_OPTIONS.getOrDefault(key, null);
    }

    public static float getWeaponAttributes(String key) {
        if (!WEAPON_OPTIONS.containsKey(key)) {
            System.out.println(key);
        }
        return WEAPON_OPTIONS.getOrDefault(key, null);
    }

    public static void init() {

        //FLOAT_OPTIONS.put("speed_chance", 15f);
        //FLOAT_OPTIONS.put("speed_duration", 300f);


        GENERAL_OPTIONS.put("standard_loot_table_weight", 0.08f);
        GENERAL_OPTIONS.put("rare_loot_table_weight", 0.01f);
        GENERAL_OPTIONS.put("unique_loot_table_weight", 0.004f);


        BOOLEAN_OPTIONS.put("add_weapons_to_loot_tables", true);


        WEAPON_OPTIONS.put("longbow_positive_damage_modifier", 0f);

        WEAPON_OPTIONS.put("longbow_negative_damage_modifier", 0f);

        WEAPON_OPTIONS.put("wood_damage_modifier", 2f);
        WEAPON_OPTIONS.put("iron_damage_modifier", 3f);
        WEAPON_OPTIONS.put("gold_damage_modifier", 2f);
        WEAPON_OPTIONS.put("diamond_damage_modifier", 3f);
        WEAPON_OPTIONS.put("netherite_damage_modifier", 3f);
        WEAPON_OPTIONS.put("runic_damage_modifier", 3f);

        WEAPON_OPTIONS.put("longbow_attackspeed", -2.4f);

    }

    public static void loadConfig() {
        JsonObject json;
        json = Config.getJsonObject(Config.readFile(new File("config/brilliantbows/booleans.json5")));
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            BOOLEAN_OPTIONS.put(entry.getKey(), entry.getValue().getAsBoolean());
        }

        json = Config.getJsonObject(Config.readFile(new File("config/brilliantbows/general_config.json5")));
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            GENERAL_OPTIONS.put(entry.getKey(), entry.getValue().getAsFloat());
        }

        json = Config.getJsonObject(Config.readFile(new File("config/brilliantbows/effects_config.json5")));
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            FLOAT_OPTIONS.put(entry.getKey(), entry.getValue().getAsFloat());
        }

        json = Config.getJsonObject(Config.readFile(new File("config/brilliantbows/weapon_attributes.json5")));
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            WEAPON_OPTIONS.put(entry.getKey(), entry.getValue().getAsFloat());
        }

    }


    public static void generateConfigs(boolean overwrite) {
        StringBuilder config = new StringBuilder("{\n");
        int i = 0;
        for (String key : BOOLEAN_OPTIONS.keySet()) {
            config.append("  \"").append(key).append("\": ").append(BOOLEAN_OPTIONS.get(key));
            ++i;
            if (i < BOOLEAN_OPTIONS.size()) {
                config.append(",");
            }
            config.append("\n");
        }
        config.append("}");
        Config.createFile("config/brilliantbows/booleans.json5", config.toString(), overwrite);

        config = new StringBuilder("{\n");
        i = 0;
        for (String item : GENERAL_OPTIONS.keySet()) {
            if (i == 0) {
                config.append("// -- GENERAL CONFIGURATION -- \n");
                config.append("\n");
                config.append("// -- Loot Table Weights -- \n");
                config.append("// The chances of loot appearing in chests. \n");
                config.append("// 1 = 100% chance \n");
                config.append("// Values are very sensitive - recommend keeping changes small \n");
                config.append("// Brilliant Bows loot has a chance to appear in any chest vanilla or modded, except villager chests \n");
                config.append("// ------------------------ \n");
                config.append("\n");
                config.append("// Standard Loot: Iron > Gold Weapons. Default: 0.08 \n");
            }
            if (i == 1) {
                config.append("\n");
                config.append("// Rare Loot: Diamond & Runic Weapons. Default: 0.008 \n");
            }
            if (i == 2) {
                config.append("\n");
                config.append("// Unique Loot: Unique Weapons. Default: 0.002 \n");
            }
            config.append("  \"").append(item).append("\": ").append(GENERAL_OPTIONS.get(item));
            ++i;
            if (i < GENERAL_OPTIONS.size()) {
                config.append(",");
            }
            config.append("\n");
        }
        config.append("}");
        Config.createFile("config/brilliantbows/general_config.json5", config.toString(), overwrite);

        config = new StringBuilder("{\n");
        i = 0;
        for (String item : FLOAT_OPTIONS.keySet()) {
            if (i == 0) {
                config.append("// -- EFFECTS CONFIGURATION -- \n");
                config.append("\n");
                config.append("//Chance range 0-100, where 100 = 100% chance to occur\n");
                config.append("//Radius is measured in blocks\n");
                config.append("//Duration in ticks, where 20 is equivalent to one second \n");
                config.append("\n");
                config.append("// -- Runic Power: Swiftness -- \n");
                config.append("// ---------------------------- \n");
            }
            config.append("  \"").append(item).append("\": ").append(FLOAT_OPTIONS.get(item));
            ++i;
            if (i < FLOAT_OPTIONS.size()) {
                config.append(",");
            }
            config.append("\n");
        }
        config.append("}");
        Config.createFile("config/brilliantbows/effects_config.json5", config.toString(), overwrite);

        config = new StringBuilder("{\n");
        i = 0;
        for (String item : WEAPON_OPTIONS.keySet()) {
            if (i == 0) {
                config.append("// -- WEAPON ATTRIBUTES CONFIGURATION -- \n");
                config.append("// These values should be THE SAME ON BOTH CLIENT AND SERVER, otherwise damage tooltips will display incorrect on the client \n");
                config.append("// The damage values of weapons can be modified by adjusting their weights \n");
                config.append("// This is not the outputted damage value you see in game, but it affects it directly \n");
                config.append("// Calculation: vanilla tool material damage + base_modifier + positive_modifier - negative_modifier = actual modifier \n");
                config.append("\n");
                config.append("// -- Positive Damage Modifiers -- \n");
                config.append("// Example use-case: Adding 3 to a value below will INCREASE the in-game damage of that weapon type by 3 \n");
                config.append("// ------------------------------- \n");
            }
            if (i == 1) {
                config.append("\n");
                config.append("// -- Negative Damage Modifiers -- \n");
                config.append("// Example use-case: Adding 3 to a value below will DECREASE the in-game damage of that weapon type by 3 \n");
                config.append("// ------------------------------- \n");
            }
            if (i == 2) {
                config.append("\n");
                config.append("// -- Base Damage Modifiers -- \n");
                config.append("// Positive & Negative damage modifiers scale off these base values \n");
                config.append("// --------------------------- \n");
            }
            if (i == 8) {
                config.append("\n");
                config.append("// -- Attack Speed Modifiers -- \n");
                config.append("// Recommended range: -1.0 to -3.7, with -1.0 being fast and -3.7 being slow \n");
                config.append("// ---------------------------- \n");
            }
            config.append("  \"").append(item).append("\": ").append(WEAPON_OPTIONS.get(item));
            ++i;
            if (i < WEAPON_OPTIONS.size()) {
                config.append(",");
            }
            config.append("\n");
        }
        config.append("}");
        Config.createFile("config/brilliantbows/weapon_attributes.json5", config.toString(), overwrite);

    }
}