package net.sweenus.brilliantbows.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sweenus.brilliantbows.BrilliantBows;

public class SoundRegistry {

    public static final DeferredRegister<SoundEvent> SOUND = DeferredRegister.create(BrilliantBows.MOD_ID, Registry.SOUND_EVENT_KEY);

    public static final RegistrySupplier<SoundEvent> SWING_SMALL = SOUND.register("swing_small", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "swing_small")));
    public static final RegistrySupplier<SoundEvent> SWING_SCIFI = SOUND.register("swing_scifi", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "swing_scifi")));
    public static final RegistrySupplier<SoundEvent> SWING_NORMAL = SOUND.register("swing_normal", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "swing_normal")));
    public static final RegistrySupplier<SoundEvent> SWING_WOOSH = SOUND.register("swing_woosh", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "swing_woosh")));
    public static final RegistrySupplier<SoundEvent> THROW_MJOLNIR = SOUND.register("throw_mjolnir", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "throw_mjolnir")));
    public static final RegistrySupplier<SoundEvent> THROW_MJOLNIR_SHORT = SOUND.register("throw_mjolnir_short", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "throw_mjolnir_short")));
    public static final RegistrySupplier<SoundEvent> THROW_MJOLNIR_LONG = SOUND.register("throw_mjolnir_long", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "throw_mjolnir_long")));
    public static final RegistrySupplier<SoundEvent> SWING_OMEN_ONE = SOUND.register("swing_omen_one", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "swing_omen_one")));
    public static final RegistrySupplier<SoundEvent> SWING_OMEN_TWO = SOUND.register("swing_omen_two", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "swing_omen_two")));
    public static final RegistrySupplier<SoundEvent> HOLY_SUMMON = SOUND.register("holy_summon", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "holy_summon")));
    public static final RegistrySupplier<SoundEvent> HOLY_HIT = SOUND.register("holy_hit", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "holy_hit")));
    public static final RegistrySupplier<SoundEvent> HOLY_CHARGE = SOUND.register("holy_charge", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "holy_charge")));
    public static final RegistrySupplier<SoundEvent> HOLY_CHARGE_LOOP = SOUND.register("holy_charge_loop", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "holy_charge_loop")));
    public static final RegistrySupplier<SoundEvent> HOLY_CHARGE_LOOP_2 = SOUND.register("holy_charge_loop_2", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "holy_charge_loop_2")));
    public static final RegistrySupplier<SoundEvent> ARCANE_HIT = SOUND.register("arcane_hit", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "arcane_hit")));
    public static final RegistrySupplier<SoundEvent> TARGET_ACQUIRED = SOUND.register("target_acquired", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "target_acquired")));
    public static final RegistrySupplier<SoundEvent> BOW_PULL_LONG = SOUND.register("bow_pull_long", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "bow_pull_long")));
    public static final RegistrySupplier<SoundEvent> BOW_PULL_LONG_2 = SOUND.register("bow_pull_long_2", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "bow_pull_long_2")));
    public static final RegistrySupplier<SoundEvent> BOW_PULL_LONG_3 = SOUND.register("bow_pull_long_3", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "bow_pull_long_3")));
    public static final RegistrySupplier<SoundEvent> BOW_SHOOT = SOUND.register("bow_shoot", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "bow_shoot")));
    public static final RegistrySupplier<SoundEvent> BOW_SHOOT_2 = SOUND.register("bow_shoot_2", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "bow_shoot_2")));
    public static final RegistrySupplier<SoundEvent> BOW_SHOOT_3 = SOUND.register("bow_shoot_3", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "bow_shoot_3")));
    public static final RegistrySupplier<SoundEvent> BOW_SHOOT_4 = SOUND.register("bow_shoot_4", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "bow_shoot_4")));

    public static final RegistrySupplier<SoundEvent> DARK_SWORD_ATTACK_01 = SOUND.register("dark_sword_attack_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_attack_01")));

    public static final RegistrySupplier<SoundEvent> DARK_SWORD_ATTACK_02 = SOUND.register("dark_sword_attack_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_attack_02")));
    public static final RegistrySupplier<SoundEvent> DARK_SWORD_ATTACK_03 = SOUND.register("dark_sword_attack_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_attack_03")));
    public static final RegistrySupplier<SoundEvent> DARK_SWORD_ATTACK_WITH_BLOOD_01 = SOUND.register("dark_sword_attack_with_blood_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_attack_with_blood_01")));
    public static final RegistrySupplier<SoundEvent> DARK_SWORD_ATTACK_WITH_BLOOD_02 = SOUND.register("dark_sword_attack_with_blood_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_attack_with_blood_02")));
    public static final RegistrySupplier<SoundEvent> DARK_SWORD_ATTACK_WITH_BLOOD_03 = SOUND.register("dark_sword_attack_with_blood_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_attack_with_blood_03")));
    public static final RegistrySupplier<SoundEvent> DARK_SWORD_BLOCK = SOUND.register("dark_sword_block", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_block")));
    public static final RegistrySupplier<SoundEvent> DARK_SWORD_BREAKS = SOUND.register("dark_sword_breaks", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_breaks")));
    public static final RegistrySupplier<SoundEvent> DARK_SWORD_ENCHANT = SOUND.register("dark_sword_enchant", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_enchant")));
    public static final RegistrySupplier<SoundEvent> DARK_SWORD_PARRY = SOUND.register("dark_sword_parry", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_parry")));
    public static final RegistrySupplier<SoundEvent> DARK_SWORD_SPELL = SOUND.register("dark_sword_spell", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_spell")));
    public static final RegistrySupplier<SoundEvent> DARK_SWORD_UNFOLD = SOUND.register("dark_sword_unfold", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_unfold")));
    public static final RegistrySupplier<SoundEvent> DARK_SWORD_WHOOSH_01 = SOUND.register("dark_sword_whoosh_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_whoosh_01")));
    public static final RegistrySupplier<SoundEvent> DARK_SWORD_WHOOSH_02 = SOUND.register("dark_sword_whoosh_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_whoosh_02")));
    public static final RegistrySupplier<SoundEvent> DARK_SWORD_WHOOSH_03 = SOUND.register("dark_sword_whoosh_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_whoosh_03")));
    public static final RegistrySupplier<SoundEvent> DARK_SWORD_WHOOSH_04 = SOUND.register("dark_sword_whoosh_04", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "dark_sword_whoosh_04")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_EARTH_SHOOT_FLYBY_01 = SOUND.register("elemental_bow_earth_shoot_flyby_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_earth_shoot_flyby_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_EARTH_SHOOT_FLYBY_02 = SOUND.register("elemental_bow_earth_shoot_flyby_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_earth_shoot_flyby_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_EARTH_SHOOT_FLYBY_03 = SOUND.register("elemental_bow_earth_shoot_flyby_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_earth_shoot_flyby_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_EARTH_SHOOT_IMPACT_01 = SOUND.register("elemental_bow_earth_shoot_impact_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_earth_shoot_impact_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_EARTH_SHOOT_IMPACT_02 = SOUND.register("elemental_bow_earth_shoot_impact_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_earth_shoot_impact_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_EARTH_SHOOT_IMPACT_03 = SOUND.register("elemental_bow_earth_shoot_impact_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_earth_shoot_impact_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_FIRE_SHOOT_FLYBY_01 = SOUND.register("elemental_bow_fire_shoot_flyby_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_fire_shoot_flyby_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_FIRE_SHOOT_FLYBY_02 = SOUND.register("elemental_bow_fire_shoot_flyby_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_fire_shoot_flyby_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_FIRE_SHOOT_FLYBY_03 = SOUND.register("elemental_bow_fire_shoot_flyby_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_fire_shoot_flyby_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_FIRE_SHOOT_IMPACT_01 = SOUND.register("elemental_bow_fire_shoot_impact_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_fire_shoot_impact_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_FIRE_SHOOT_IMPACT_02 = SOUND.register("elemental_bow_fire_shoot_impact_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_fire_shoot_impact_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_FIRE_SHOOT_IMPACT_03 = SOUND.register("elemental_bow_fire_shoot_impact_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_fire_shoot_impact_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_HOLY_SHOOT_FLYBY_01 = SOUND.register("elemental_bow_holy_shoot_flyby_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_holy_shoot_flyby_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_HOLY_SHOOT_FLYBY_02 = SOUND.register("elemental_bow_holy_shoot_flyby_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_holy_shoot_flyby_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_HOLY_SHOOT_FLYBY_03 = SOUND.register("elemental_bow_holy_shoot_flyby_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_holy_shoot_flyby_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_HOLY_SHOOT_IMPACT_01 = SOUND.register("elemental_bow_holy_shoot_impact_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_holy_shoot_impact_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_HOLY_SHOOT_IMPACT_02 = SOUND.register("elemental_bow_holy_shoot_impact_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_holy_shoot_impact_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_HOLY_SHOOT_IMPACT_03 = SOUND.register("elemental_bow_holy_shoot_impact_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_holy_shoot_impact_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_ICE_SHOOT_FLYBY_01 = SOUND.register("elemental_bow_ice_shoot_flyby_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_ice_shoot_flyby_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_ICE_SHOOT_FLYBY_02 = SOUND.register("elemental_bow_ice_shoot_flyby_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_ice_shoot_flyby_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_ICE_SHOOT_FLYBY_03 = SOUND.register("elemental_bow_ice_shoot_flyby_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_ice_shoot_flyby_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_ICE_SHOOT_IMPACT_01 = SOUND.register("elemental_bow_ice_shoot_impact_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_ice_shoot_impact_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_ICE_SHOOT_IMPACT_02 = SOUND.register("elemental_bow_ice_shoot_impact_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_ice_shoot_impact_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_ICE_SHOOT_IMPACT_03 = SOUND.register("elemental_bow_ice_shoot_impact_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_ice_shoot_impact_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_POISON_ATTACK_01 = SOUND.register("elemental_bow_poison_attack_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_poison_attack_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_POISON_ATTACK_02 = SOUND.register("elemental_bow_poison_attack_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_poison_attack_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_POISON_ATTACK_03 = SOUND.register("elemental_bow_poison_attack_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_poison_attack_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_RECHARGE = SOUND.register("elemental_bow_recharge", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_recharge")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_SCIFI_SHOOT_FLYBY_01 = SOUND.register("elemental_bow_sci-fi_shoot_flyby_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_sci-fi_shoot_flyby_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_SCIFI_SHOOT_FLYBY_02 = SOUND.register("elemental_bow_sci-fi_shoot_flyby_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_sci-fi_shoot_flyby_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_SCIFI_SHOOT_FLYBY_03 = SOUND.register("elemental_bow_sci-fi_shoot_flyby_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_sci-fi_shoot_flyby_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_SCIFI_SHOOT_IMPACT_01 = SOUND.register("elemental_bow_sci-fi_shoot_impact_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_sci-fi_shoot_impact_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_SCIFI_SHOOT_IMPACT_02 = SOUND.register("elemental_bow_sci-fi_shoot_impact_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_sci-fi_shoot_impact_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_SCIFI_SHOOT_IMPACT_03 = SOUND.register("elemental_bow_sci-fi_shoot_impact_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_sci-fi_shoot_impact_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_THUNDER_SHOOT_FLYBY_01 = SOUND.register("elemental_bow_thunder_shoot_flyby_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_thunder_shoot_flyby_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_THUNDER_SHOOT_FLYBY_02 = SOUND.register("elemental_bow_thunder_shoot_flyby_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_thunder_shoot_flyby_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_THUNDER_SHOOT_FLYBY_03 = SOUND.register("elemental_bow_thunder_shoot_flyby_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_thunder_shoot_flyby_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_THUNDER_SHOOT_IMPACT_01 = SOUND.register("elemental_bow_thunder_shoot_impact_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_thunder_shoot_impact_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_THUNDER_SHOOT_IMPACT_02 = SOUND.register("elemental_bow_thunder_shoot_impact_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_thunder_shoot_impact_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_THUNDER_SHOOT_IMPACT_03 = SOUND.register("elemental_bow_thunder_shoot_impact_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_thunder_shoot_impact_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_WATER_SHOOT_FLYBY_01 = SOUND.register("elemental_bow_water_shoot_flyby_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_water_shoot_flyby_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_WATER_SHOOT_FLYBY_02 = SOUND.register("elemental_bow_water_shoot_flyby_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_water_shoot_flyby_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_WATER_SHOOT_FLYBY_03 = SOUND.register("elemental_bow_water_shoot_flyby_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_water_shoot_flyby_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_WATER_SHOOT_IMPACT_01 = SOUND.register("elemental_bow_water_shoot_impact_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_water_shoot_impact_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_WATER_SHOOT_IMPACT_02 = SOUND.register("elemental_bow_water_shoot_impact_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_water_shoot_impact_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_WATER_SHOOT_IMPACT_03 = SOUND.register("elemental_bow_water_shoot_impact_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_water_shoot_impact_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_WIND_SHOOT_FLYBY_01 = SOUND.register("elemental_bow_wind_shoot_flyby_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_wind_shoot_flyby_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_WIND_SHOOT_FLYBY_02 = SOUND.register("elemental_bow_wind_shoot_flyby_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_wind_shoot_flyby_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_WIND_SHOOT_FLYBY_03 = SOUND.register("elemental_bow_wind_shoot_flyby_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_wind_shoot_flyby_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_WIND_SHOOT_IMPACT_01 = SOUND.register("elemental_bow_wind_shoot_impact_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_wind_shoot_impact_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_WIND_SHOOT_IMPACT_02 = SOUND.register("elemental_bow_wind_shoot_impact_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_wind_shoot_impact_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_BOW_WIND_SHOOT_IMPACT_03 = SOUND.register("elemental_bow_wind_shoot_impact_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_bow_wind_shoot_impact_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_EARTH_ATTACK_01 = SOUND.register("elemental_sword_earth_attack_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_earth_attack_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_EARTH_ATTACK_02 = SOUND.register("elemental_sword_earth_attack_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_earth_attack_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_EARTH_ATTACK_03 = SOUND.register("elemental_sword_earth_attack_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_earth_attack_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_FIRE_ATTACK_01 = SOUND.register("elemental_sword_fire_attack_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_fire_attack_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_FIRE_ATTACK_02 = SOUND.register("elemental_sword_fire_attack_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_fire_attack_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_FIRE_ATTACK_03 = SOUND.register("elemental_sword_fire_attack_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_fire_attack_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_HOLY_ATTACK_01 = SOUND.register("elemental_sword_holy_attack_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_holy_attack_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_HOLY_ATTACK_02 = SOUND.register("elemental_sword_holy_attack_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_holy_attack_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_HOLY_ATTACK_03 = SOUND.register("elemental_sword_holy_attack_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_holy_attack_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_ICE_ATTACK_01 = SOUND.register("elemental_sword_ice_attack_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_ice_attack_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_ICE_ATTACK_02 = SOUND.register("elemental_sword_ice_attack_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_ice_attack_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_ICE_ATTACK_03 = SOUND.register("elemental_sword_ice_attack_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_ice_attack_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_SCIFI_ATTACK_01 = SOUND.register("elemental_sword_sci-fi_attack_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_sci-fi_attack_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_SCIFI_ATTACK_02 = SOUND.register("elemental_sword_sci-fi_attack_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_sci-fi_attack_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_SCIFI_ATTACK_03 = SOUND.register("elemental_sword_sci-fi_attack_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_sci-fi_attack_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_THUNDER_ATTACK_01 = SOUND.register("elemental_sword_thunder_attack_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_thunder_attack_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_THUNDER_ATTACK_02 = SOUND.register("elemental_sword_thunder_attack_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_thunder_attack_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_THUNDER_ATTACK_03 = SOUND.register("elemental_sword_thunder_attack_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_thunder_attack_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_WATER_ATTACK_01 = SOUND.register("elemental_sword_water_attack_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_water_attack_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_WATER_ATTACK_02 = SOUND.register("elemental_sword_water_attack_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_water_attack_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_WATER_ATTACK_03 = SOUND.register("elemental_sword_water_attack_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_water_attack_03")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_WIND_ATTACK_01 = SOUND.register("elemental_sword_wind_attack_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_wind_attack_01")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_WIND_ATTACK_02 = SOUND.register("elemental_sword_wind_attack_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_wind_attack_02")));
    public static final RegistrySupplier<SoundEvent> ELEMENTAL_SWORD_WIND_ATTACK_03 = SOUND.register("elemental_sword_wind_attack_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "elemental_sword_wind_attack_03")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_CHARGE_LONG_VERSION = SOUND.register("magic_bow_charge_long_version", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_charge_long_version")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_CHARGE_SHORT_VERSION = SOUND.register("magic_bow_charge_short_version", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_charge_short_version")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_PULL_BACK_LONG_VERSION_01 = SOUND.register("magic_bow_pull_back_long_version_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_pull_back_long_version_01")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_PULL_BACK_LONG_VERSION_02 = SOUND.register("magic_bow_pull_back_long_version_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_pull_back_long_version_02")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_PULL_BACK_LONG_VERSION_03 = SOUND.register("magic_bow_pull_back_long_version_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_pull_back_long_version_03")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_PULL_BACK_SHORT_VERSION_01 = SOUND.register("magic_bow_pull_back_short_version_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_pull_back_short_version_01")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_PULL_BACK_SHORT_VERSION_02 = SOUND.register("magic_bow_pull_back_short_version_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_pull_back_short_version_02")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_PULL_BACK_SHORT_VERSION_03 = SOUND.register("magic_bow_pull_back_short_version_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_pull_back_short_version_03")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_SHOOT_FLYBY_01 = SOUND.register("magic_bow_shoot_flyby_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_shoot_flyby_01")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_SHOOT_FLYBY_02 = SOUND.register("magic_bow_shoot_flyby_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_shoot_flyby_02")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_SHOOT_FLYBY_03 = SOUND.register("magic_bow_shoot_flyby_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_shoot_flyby_03")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_SHOOT_IMPACT_01 = SOUND.register("magic_bow_shoot_impact_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_shoot_impact_01")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_SHOOT_IMPACT_02 = SOUND.register("magic_bow_shoot_impact_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_shoot_impact_02")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_SHOOT_IMPACT_03 = SOUND.register("magic_bow_shoot_impact_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_shoot_impact_03")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_SHOOT_MISS_01 = SOUND.register("magic_bow_shoot_miss_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_shoot_miss_01")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_SHOOT_MISS_02 = SOUND.register("magic_bow_shoot_miss_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_shoot_miss_02")));
    public static final RegistrySupplier<SoundEvent> MAGIC_BOW_SHOOT_MISS_03 = SOUND.register("magic_bow_shoot_miss_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_bow_shoot_miss_03")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_ATTACK_01 = SOUND.register("magic_sword_attack_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_attack_01")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_ATTACK_02 = SOUND.register("magic_sword_attack_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_attack_02")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_ATTACK_03 = SOUND.register("magic_sword_attack_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_attack_03")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_ATTACK_04 = SOUND.register("magic_sword_attack_04", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_attack_04")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_ATTACK_WITH_BLOOD_01 = SOUND.register("magic_sword_attack_with_blood_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_attack_with_blood_01")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_ATTACK_WITH_BLOOD_02 = SOUND.register("magic_sword_attack_with_blood_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_attack_with_blood_02")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_ATTACK_WITH_BLOOD_03 = SOUND.register("magic_sword_attack_with_blood_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_attack_with_blood_03")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_ATTACK_WITH_BLOOD_04 = SOUND.register("magic_sword_attack_with_blood_04", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_attack_with_blood_04")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_BLOCK_01 = SOUND.register("magic_sword_block_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_block_01")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_BLOCK_02 = SOUND.register("magic_sword_block_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_block_02")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_BREAKS = SOUND.register("magic_sword_breaks", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_breaks")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_PARRY_01 = SOUND.register("magic_sword_parry_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_parry_01")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_PARRY_02 = SOUND.register("magic_sword_parry_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_parry_02")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_PARRY_03 = SOUND.register("magic_sword_parry_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_parry_03")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_PARRY_04 = SOUND.register("magic_sword_parry_04", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_parry_04")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_PARRY_VARIOUS_HITS = SOUND.register("magic_sword_parry_various_hits", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_parry_various_hits")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_SPELL_01 = SOUND.register("magic_sword_spell_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_spell_01")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_SPELL_02 = SOUND.register("magic_sword_spell_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_spell_02")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_SPELL_03 = SOUND.register("magic_sword_spell_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_spell_03")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_WHOOSH_01 = SOUND.register("magic_sword_whoosh_01", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_whoosh_01")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_WHOOSH_02 = SOUND.register("magic_sword_whoosh_02", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_whoosh_02")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_WHOOSH_03 = SOUND.register("magic_sword_whoosh_03", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_whoosh_03")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_WHOOSH_04 = SOUND.register("magic_sword_whoosh_04", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_whoosh_04")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_WHOOSH_05 = SOUND.register("magic_sword_whoosh_05", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_whoosh_05")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_WHOOSH_06 = SOUND.register("magic_sword_whoosh_06", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_whoosh_06")));
    public static final RegistrySupplier<SoundEvent> MAGIC_SWORD_WHOOSH_07 = SOUND.register("magic_sword_whoosh_07", () ->
            new SoundEvent(new Identifier(BrilliantBows.MOD_ID, "magic_sword_whoosh_07")));

}