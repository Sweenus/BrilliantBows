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

}